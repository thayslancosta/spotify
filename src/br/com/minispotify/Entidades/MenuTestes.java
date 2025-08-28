package br.com.minispotify.Entidades;

public class MenuTestes {
    public static void main(String[] args) {
        try {
            // Create catalog
            Catalogo catalogo = new Catalogo();

            catalogo.buscarArtista("Radiohead");
            // Create media
            Musica musica1 = new Musica("Bohemian Rhapsody", "Queen", 355, Genero.ROCK);
            Musica musica2 = new Musica("Bodysnatchers", "Radiohead", 280, Genero.ROCK);
            Musica musica3 = new Musica("Idioteque", "Radiohead", 330, Genero.ROCK);
            Podcast podcast1 = new Podcast("Tech Talk", "Tech Guy", 1800, 1);
            Audiobook audiobook1 = new Audiobook("1984", "George Orwell", 10800);

            // Add to catalog
            catalogo.adicionarMidia(musica1);
            catalogo.adicionarMidia(musica2);
            catalogo.adicionarMidia(musica3);
            catalogo.adicionarMidia(podcast1);
            catalogo.adicionarMidia(audiobook1);

            catalogo.buscarTitulo("1984");
            catalogo.buscarArtista("Radiohead");


            // Create user and playlist
            Usuario usuario = new Usuario("João", "joao@email.com");
            usuario.criarPlaylist("Minha Playlist");

            // Add music to playlist
            Playlist playlist = usuario.getListaPlaylists().get(0);
            playlist.addMusica(musica1);
            playlist.addMusica(musica2);

            playlist.listarMusicas();

//            List<Musica> listaTemp = playlist.getPlaylistMusicas();
//            for (Musica musica : listaTemp) {
//                System.out.println(musica);
//
//            }



//            usuario.listPlaylists();
//            System.out.println(playlist.duracaoFormatada());

//
//            // Display playlist information
//            System.out.println("Playlist: " + playlist.getNome());
//            System.out.println("Duração total: " + playlist.calcularDuracaoTotal() / 60.0 + " minutos");
//            System.out.println("Músicas na playlist:");
//            for (Musica m : playlist.getPlaylistMusicas()) {
//                System.out.println("- " + m.getTitulo() + " por " + m.getArtista());
//            }
//
//            // Search catalog
//            System.out.println("\nBusca por gênero ROCK:");
//            for (Midia m : catalogo.buscarPorGenero(Genero.ROCK)) {
//                System.out.println("- " + m.getTitulo());
//            }

        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    }
