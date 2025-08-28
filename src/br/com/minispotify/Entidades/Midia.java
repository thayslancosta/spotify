package br.com.minispotify.Entidades;

import java.util.Objects;

public abstract class Midia {

    protected String titulo;
    protected String artista;
    protected int duracao;

    public Midia(String titulo, String artista, int duracao) {
        this.titulo = titulo;
        this.artista = artista;
        this.duracao = duracao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getArtista() {
        return artista;
    }

    public void setArtista(String artista) {
        this.artista = artista;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    public String duracaoFormatada () {
        int totalSegundos = duracao;
        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;

        if (horas > 0) {
            return String.format("%d:%02d:%02d", horas, minutos, segundos);
        } else {
            return String.format("%d:%02d", minutos, segundos);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Midia midia = (Midia) o;
        return duracao == midia.duracao && Objects.equals(titulo, midia.titulo) && Objects.equals(artista, midia.artista);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titulo, artista, duracao);
    }

    @Override
    public String toString() {
        return "Midia{" +
                "titulo='" + titulo + '\'' +
                ", artista='" + artista + '\'' +
                ", duracao=" + duracaoFormatada() +
                '}';
    }
}
