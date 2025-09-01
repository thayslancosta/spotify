package br.com.minispotify.Exceptions;

public class PlaylistNaoEncontradaException extends Exception {
    public PlaylistNaoEncontradaException() {
        super("Playlist não encontrada!");
    }

    public PlaylistNaoEncontradaException(String messagem) {
        super(messagem);
    }
}

