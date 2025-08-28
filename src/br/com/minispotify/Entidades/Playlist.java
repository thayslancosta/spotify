package br.com.minispotify.Entidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Playlist {

    private int codigo;
    private static int contadorCodigos = 1;
    private String nome;
    private Usuario proprietario;
    private List<Musica> playlistMusicas;

    public Playlist(String nome, Usuario proprietario) {
        this.codigo = contadorCodigos++;
        this.nome = nome;
        this.proprietario = proprietario;
        this.playlistMusicas = new ArrayList<>();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public List<Musica> getPlaylistMusicas() {
        return playlistMusicas;
    }

    public void setPlaylistMusicas(List<Musica> playlistMusicas) {
        this.playlistMusicas = playlistMusicas;
    }

    public void addMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Música inválida!");
        }
        playlistMusicas.add(musica);
    }

    public void removerMusica(Musica musica) {
        if (musica == null) {
            throw new IllegalArgumentException("Música inválida!");
        }
        playlistMusicas.remove(musica);
    }

    public void listarMusicas() {
        if (!playlistMusicas.isEmpty()) {
            System.out.println("Lista de músicas na playlist: " + this.nome);
            for (Musica musica : playlistMusicas) {
                System.out.println("-" + musica.getTitulo());
            }
        } else {
                System.out.println("Playlist vazia!");
            }
        }

    public String duracaoFormatada() {
        int totalSegundos = calcularDuracao();
        int horas = totalSegundos / 3600;
        int minutos = (totalSegundos % 3600) / 60;
        int segundos = totalSegundos % 60;

        if (horas > 0) {
            return String.format("O tempo de duração da playlist é: %d:%02d:%02d", horas, minutos, segundos);
        } else {
            return String.format("O tempo de duração da playlist é: %d:%02d", minutos, segundos);
        }
    }

    public int calcularDuracao() {
        int duracaoTotal = 0;
        for (Musica musica : playlistMusicas) {
            duracaoTotal += musica.getDuracao();
        }
        return duracaoTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Playlist playlist = (Playlist) o;
        return codigo == playlist.codigo && Objects.equals(nome, playlist.nome) && Objects.equals(proprietario, playlist.proprietario) && Objects.equals(playlistMusicas, playlist.playlistMusicas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nome, proprietario, playlistMusicas);
    }

    @Override
    public String toString() {
        return nome + " (" + playlistMusicas.size() + " músicas, " + duracaoFormatada() + ")";
    }
}
