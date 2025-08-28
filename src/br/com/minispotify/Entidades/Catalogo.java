package br.com.minispotify.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Catalogo {
    List<Midia> listaCatalogo;

    // Sem parâmetro para que a lista seja criada apenas nessa instância
    public Catalogo() {
        this.listaCatalogo = new ArrayList<>();
    }

    public void adicionarMidia (Midia midia) {
        if (midia == null) {
            throw new IllegalArgumentException("Mídia inválida");
        }
        listaCatalogo.add(midia);
    }

    public void listarCatalogo() {
        for (Midia catalogo : listaCatalogo) {
            System.out.println(catalogo);
            System.out.println("----------------");

        }
    }
    // TODO: Refatorar método
    public void exibirResultadoBusca(List<Midia> resultado, String criterio) {
        if (resultado.isEmpty()) {
            System.out.println("Nenhuma mídia encontrada com " + criterio);

        } else if (criterio.contains("artista")) {
            System.out.println("Mídias do " + criterio + ":");
            int numero = 1;
            for (Midia midia : resultado) {
                System.out.println(numero + "- " + midia.getTitulo());
                numero ++;
            }
        } else {
            System.out.println("Resultados da busca: ");
            for (int i = 0; i < resultado.size() ; i++) {
                System.out.println((i + 1) + "- " + resultado.get(i));
            }
        }

    }
    // TODO: Refatorar método
    public void buscarTitulo(String titulo) {
        if (titulo == null) {
            throw new IllegalArgumentException("Título inválido");
        } else {
            List<Midia> resultado = new ArrayList<>();
            for (Midia midia : listaCatalogo) {
                if (midia.getTitulo().toLowerCase().contains(titulo.toLowerCase())) {
                    resultado.add(midia);
                }
            }
            exibirResultadoBusca(resultado, "título" + titulo);
        }
    }
    //TODO  Refatorar método
    public void buscarArtista (String artista) {
        if (artista == null) {
            throw new IllegalArgumentException("Artista inválido!");
        } else {
            List<Midia> resultado = new ArrayList<>();
            for (Midia midia : listaCatalogo) {
                if (midia.getArtista().toLowerCase().contains(artista.toLowerCase())) {
                    resultado.add(midia);
                }
            }
            exibirResultadoBusca(resultado, "artista " + artista);
        }
    }
    // Metodo para buscar músicas por gênero
    public List<Midia> buscarGenero(Genero genero) {
        if (genero == null) {
            throw new IllegalArgumentException("Gênero não pode ser nulo!");
        }

        List<Midia> resultado = new ArrayList<>();

        for (Midia midia : listaCatalogo) {
            if (midia instanceof Musica) {
                Musica musica = (Musica) midia;
                if (musica.getGenero() == genero) {
                    resultado.add(midia);
                }
            }
        }
        return resultado;
    }
    // Metodo para exibir mídias por gênero
    public void exibirMidiasPorGenero(Genero genero) {
        List<Midia> resultado = buscarGenero(genero);

        if (resultado.isEmpty()) {
            System.out.println("Nenhuma música encontrada do gênero: " + genero);
        } else {
            System.out.println("\nLista de músicas com o gênero:  " + genero);
            for (int i = 0; i < resultado.size(); i++) {
                System.out.println((i + 1) + ". " + resultado.get(i)+"\n");
            }
        }
    }
}
