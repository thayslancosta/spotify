package br.com.minispotify.Entidades;

import java.util.Objects;

public class Musica extends Midia {

    protected Genero genero;

    public Musica(String titulo, String artista, int duracao, Genero genero) {
        super(titulo, artista, duracao);
        this.genero = genero;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Musica musica = (Musica) o;
        return genero == musica.genero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), genero);
    }

    @Override
    public String toString() {
        return "Tipo da mídia: música\n" +
                "Título = '" + titulo + "'\n" +
                "Artista = '" + artista + "'\n" +
                "Gênero = " + genero + "\n" +
                "Duração = " + duracaoFormatada();
    }
}
