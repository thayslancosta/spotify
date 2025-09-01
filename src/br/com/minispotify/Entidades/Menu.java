package br.com.minispotify.Entidades;

import br.com.minispotify.Exceptions.EmailInvalidoException;
import br.com.minispotify.Exceptions.PlaylistNaoEncontradaException;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Catalogo catalogo;
    private Usuario usuarioAtual;

    //Construtor
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.catalogo = new Catalogo();
    }

    //Metodo com o Menu principal
    public void executar() {
        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    centralUsuario();
                    break;
                case 2:
                    acessarCatalogo();
                    break;
                case 3:
                    System.out.println("Saindo do Spotify...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }
    // Metodo com o menu principal
    private void exibirMenuPrincipal() {
        System.out.println("\nBem-vindo ao Spotify!");
        System.out.println("Escolha uma opção: ");
        System.out.println("1. Central do Usuário");
        System.out.println("2. Acessar Catálogo");
        System.out.println("3. Sair");
    }
    // Metodo para validar opção informada pelo user
    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    // Metodo com o menu da central do Usuário
    private void centralUsuario() {
        int opcao;
        do {
            System.out.println("\nBem-vindo à Central do Usuário!");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Criar Novo Usuário");
            System.out.println("2. Menu de Playlists");
            System.out.println("3. Retornar ao Menu Anterior");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    try {
                        criarNovoUsuario();
                    } catch (EmailInvalidoException e) {
                        System.out.println("Erro: " + e.getMessage());
                    }
                    break;
                case 2:
                    menuPlaylists();
                    break;
                case 3:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }
    //Metodo para criar novo usuário
    private void criarNovoUsuario () throws EmailInvalidoException {
        System.out.print("Digite o nome do usuário: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o email: ");
        String email = scanner.nextLine();

        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException();
        }

        try {
            usuarioAtual = new Usuario(nome, email);
            System.out.println("Usuário criado com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    // Metodo com o menu de playlists
    private void menuPlaylists() {

        //Conferindo se usuário está cadastrado
        if (usuarioAtual == null) {
            System.out.println("Nenhum usuário selecionado!");
            try {
                criarNovoUsuario();
            } catch (EmailInvalidoException e) {
                System.out.println("Erro! " + e.getMessage());
            }
            if (usuarioAtual == null) {
                return;
            }
        }

        int opcao;
        do {
            System.out.println("\nBem-vindo ao menu de playlists!");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Criar Playlist");
            System.out.println("2. Listar Playlists");
            System.out.println("3. Acessar Playlist por Código");
            System.out.println("4. Editar Playlist");
            System.out.println("5. Calcular Duração da Playlist");
            System.out.println("6. Retornar ao Menu Anterior");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    criarPlaylist();
                    break;
                case 2:
                    usuarioAtual.listPlaylists();
                    break;
                case 3:
                    try {
                        acessarPlaylistPorCodigo();
                    } catch (PlaylistNaoEncontradaException e) {
                        System.out.println("Erro! " + e.getMessage());
                    }
                    break;
                case 4:
                    try {
                        Playlist playlist = buscarPlaylistPorCodigo();
                        menuEditarPlaylist(playlist);
                    } catch (PlaylistNaoEncontradaException e) {
                        System.out.println("Erro! " + e.getMessage());
                    }
                    break;
                case 5:
                    try {
                        calcularDuracaoPlaylist();
                    } catch (PlaylistNaoEncontradaException e) {
                        System.out.println("Erro! " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("Retornando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }
    //Metodo para criar Playlists com sobrecarga
    private void criarPlaylist() {
        System.out.println("Informe o nome da playlist: ");
        String nomePlaylist = scanner.nextLine();

        try {
            usuarioAtual.criarPlaylist(nomePlaylist);
            int codigo = usuarioAtual.getListaPlaylists().getLast().getCodigo();
            System.out.println("Playlist criada com sucesso! Código: " + codigo);
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
    //Metodo para buscar playlist por codigo
    private Playlist buscarPlaylistPorCodigo() throws PlaylistNaoEncontradaException {
        System.out.print("Digite o código da playlist: ");
        int codigo = Integer.parseInt(scanner.nextLine());

        for (Playlist playlist : usuarioAtual.getListaPlaylists()) {
            if (playlist.getCodigo() == codigo) {
                return playlist;
            }
        }
        throw new PlaylistNaoEncontradaException();
    }

    //Metodo para acessar playlist por código
    private void acessarPlaylistPorCodigo() throws PlaylistNaoEncontradaException {
        Playlist playlist = buscarPlaylistPorCodigo();

            System.out.println("Playlist encontrada: " + playlist.getNome());
            playlist.listarMusicas();
    }

    //Metodo para calcular a duração da Playlist
    private void calcularDuracaoPlaylist() throws PlaylistNaoEncontradaException {
        Playlist playlist = buscarPlaylistPorCodigo();

        if (playlist != null) {
            String duracao = playlist.duracaoFormatada();
            System.out.println(duracao);
        } else {
            System.out.println("Playlist não encontrada!");
        }
    }

    // Metodo com o submenu de edição de playlists
    private void menuEditarPlaylist(Playlist playlist) {

        if (playlist == null) {
            System.out.println("Playlist não encontrada!");
            return;
        }

        int opcao;
        do {
            System.out.println("Menu de edição de playlists!");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Adicionar Música");
            System.out.println("2. Remover Música");
            System.out.println("3. Retornar ao Menu Anterior");


            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    adicionarMusica(playlist);
                    break;
                case 2:
                    removerMusica(playlist);
                    break;
                case 3:
                    System.out.println("Retornando...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 3);
    }
    //Metodo para adicionar música
    private void adicionarMusica(Playlist playlist){
        System.out.println("\nMenu de adicionar música!");
        System.out.println("Informe o título da música: ");
        String titulo = scanner.nextLine();

        System.out.println("Informe o artista: ");
        String artista = scanner.nextLine();

        System.out.println("Informe a duração da música em segundos: ");
        int duracao = Integer.parseInt(scanner.nextLine());

        Genero genero = selecionarGenero();

        try {
            Musica musica = new Musica(titulo, artista, duracao, genero);
            playlist.addMusica(musica);
            catalogo.adicionarMidia(musica);
            System.out.println("Música adicionada com sucesso!");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //Metodo para remover música
    private void removerMusica(Playlist playlist) {
        System.out.println("\nMenu de remover música!");

        List<Musica> musicas = playlist.getPlaylistMusicas();
        if (musicas.isEmpty()) {
            System.out.println("Playlist vazia! Nada para remover.");
            return;
        }
        // Listar as músicas da playlist com um número ao lado para que o usuário selecione a música a ser removida
        System.out.println("Músicas na playlist '" + playlist.getNome() + "':");
        for (int i = 0; i < musicas.size(); i++) {
            Musica musica = musicas.get(i);
            System.out.println((i + 1) + ". " + musica.getTitulo() + " - " + musica.getArtista());
        }

        System.out.print("Digite o número da música a remover: ");

        try {
            int numero = Integer.parseInt(scanner.nextLine());
            if (numero >= 1 && numero <= musicas.size()) {
                Musica musicaParaRemover = musicas.get(numero - 1);
                playlist.removerMusica(musicaParaRemover);
                System.out.println("Música '" + musicaParaRemover.getTitulo() + "' removida com sucesso!");
            } else {
                System.out.println("Número inválido! Digite um número entre 1 e " + musicas.size());
            }
        } catch (NumberFormatException e) {
            System.out.println("Digite um número válido!");
        }
    }

    //Metodo para obter gênero da música com o usuário
    private Genero selecionarGenero() {
        System.out.println("\nSelecione o gênero da música: ");
        Genero[] generos = Genero.values();

        // Exibe todos os gêneros com números
        for (int i = 0; i < generos.length; i++) {
            System.out.println((i + 1) + ". " + generos[i]);
        }
        System.out.print("Digite o número do gênero: ");

        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            if (opcao >= 1 && opcao <= generos.length) {
                return generos[opcao - 1];
            } else {
                System.out.println("Número inválido! Gênero selecionado: OUTRO");
                return Genero.OUTRO;
            }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida! Gênero selecionado: OUTRO");
            return Genero.OUTRO;
        }
    }

    //Menu do catálogo
    private void acessarCatalogo() {
        int opcao;
        do {
            System.out.println("\nBem-vindo ao menu do catálogo!");
            System.out.println("Escolha uma opção: ");
            System.out.println("1. Listar Mídias");
            System.out.println("2. Buscar Mídia por Título");
            System.out.println("3. Buscar por Artista");
            System.out.println("4. Buscar por Gênero");
            System.out.println("5. Adicionar nova Mídia");
            System.out.println("6. Retornar ao Menu Principal");

            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    catalogo.listarCatalogo();
                    break;
                case 2:
                    buscarMidiaPorTitulo();
                    break;
                case 3:
                    buscarPorArtista();
                    break;
                case 4:
                    buscarPorGenero();
                    break;
                case 5:
                    //TODO: criar método
                    //adicionarNovaMidia();
                    break;
                case 6:
                    System.out.println("Retornando ao menu principal...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 6);
    }
    // Metodo para buscar mídia por título
    private void buscarMidiaPorTitulo() {
        System.out.print("\nDigite o título para buscar: ");
        String titulo = scanner.nextLine();
        catalogo.buscarTitulo(titulo);
    }

    // Metodo para buscar por artista
    private void buscarPorArtista() {
        System.out.print("\nDigite o artista para buscar: ");
        String artista = scanner.nextLine();
        catalogo.buscarArtista(artista);
    }

    //Metodo para buscar mídias por gênero
    private void buscarPorGenero() {
        System.out.println("\nMenu de buscar mídias por gênero");
        Genero genero = selecionarGenero();
        catalogo.exibirMidiasPorGenero(genero);
    }
    //METODO MAIN
    public static void main(String[] args) {

        // Adicionando algumas mídias previamente
        Catalogo catalogoTeste = new Catalogo();
        Musica musica1 = new Musica("Bohemian Rhapsody", "Queen", 355, Genero.ROCK);
        Musica musica2 = new Musica("Bodysnatchers", "Radiohead", 280, Genero.ROCK);
        Musica musica3 = new Musica("Idioteque", "Radiohead", 330, Genero.ROCK);
        Podcast podcast1 = new Podcast("Tech Talk", "Tech Guy", 1800, 1);
        Audiobook audiobook1 = new Audiobook("1984", "George Orwell", 10800);
        catalogoTeste.adicionarMidia(musica1);
        catalogoTeste.adicionarMidia(musica2);
        catalogoTeste.adicionarMidia(musica3);
        catalogoTeste.adicionarMidia(audiobook1);
        catalogoTeste.adicionarMidia(podcast1);

        // Criando usuário, playlist e adicionando músicas
        Usuario usuario = new Usuario("João", "joao@email.com");
        usuario.criarPlaylist("Minha Playlist");
        Playlist playlist = usuario.getListaPlaylists().get(0);
        playlist.addMusica(musica1);
        playlist.addMusica(musica2);

        Menu menu = new Menu();
        menu.executar();
    }
}
