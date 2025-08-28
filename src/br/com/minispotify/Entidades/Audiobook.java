package br.com.minispotify.Entidades;

public class Audiobook extends Midia {
    public Audiobook(String titulo, String artista, int duracao) {
        super(titulo, artista, duracao);
    }

    @Override
    public String toString() {
        return "Tipo da mídia: Audiobook\n" +
                "Título = '" + titulo + "'\n" +
                "Artista = '" + artista + "'\n" +
                "Duração = " + duracaoFormatada();
    }
}
