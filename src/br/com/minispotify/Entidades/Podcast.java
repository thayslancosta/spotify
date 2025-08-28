package br.com.minispotify.Entidades;

import java.util.Objects;

public class Podcast extends Midia {

    protected int episodio;

    public Podcast(String titulo, String artista, int duracao, int episodio) {
        super(titulo, artista, duracao);
        this.episodio = episodio;
    }

    public int getEpisodio() {
        return episodio;
    }

    public void setEpisodio(int episodio) {
        this.episodio = episodio;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Podcast podcast = (Podcast) o;
        return episodio == podcast.episodio;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), episodio);
    }

    @Override
    public String toString() {
        return "Tipo da mídia: Podcast\n" +
                "Episódio = " + episodio + "\n" +
                "Título = '" + titulo + "'\n" +
                "Artista = '" + artista + "'\n" +
                "Duração = " + duracaoFormatada();
    }
}
