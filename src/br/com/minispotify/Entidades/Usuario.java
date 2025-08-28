package br.com.minispotify.Entidades;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nome;
    private String email;
    private List<Playlist> listaPlaylists;

    public Usuario(String nome, String email) {
        this.nome = nome;
        this.email = email;
        this.listaPlaylists = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Playlist> getListaPlaylists() {
        return listaPlaylists;
    }

    public void setListaPlaylists(List<Playlist> listaPlaylists) {
        this.listaPlaylists = listaPlaylists;
    }

    public void criarPlaylist(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Informe um nome válido!");
        }
        Playlist novaPlaylist = new Playlist(nome, this);
        listaPlaylists.add(novaPlaylist);
    }


    public void listPlaylists() {
        if (listaPlaylists.isEmpty()) {
            System.out.println("Não há playlists cadastradas!");
        } else {
            for (Playlist listaPlaylist : listaPlaylists) {
                System.out.println(listaPlaylist.getCodigo() + " - " + listaPlaylist.getNome() + "\n");

            }
        }
    }
}