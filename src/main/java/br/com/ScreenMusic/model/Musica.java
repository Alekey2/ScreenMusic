package br.com.ScreenMusic.model;

import jakarta.persistence.*;

import java.time.Duration;

@Entity
@Table(name = "musicas")
public class Musica {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Duration duracao;


    private String genero;

    @ManyToOne

    private Artista artista;

    public Musica() {
    }

    public Musica(Long id, String nome, Duration duracao, String genero, Artista artista) {
        this.id = id;
        this.nome = nome;
        this.duracao = duracao;
        this.genero = genero;
        this.artista = artista;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Duration getDuracao() {
        return duracao;
    }

    public void setDuracao(Duration duracao) {
        this.duracao = duracao;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Artista getArtista() {
        return artista;
    }

    public void setArtista(Artista artista) {
        this.artista = artista;
    }

    @Override
    public String toString() {
        return "[ID: " + id +
                " | Nome: " + nome +
                " | Duração: " + duracao +
                " | Gênero: " + genero +
                " | Artista: " + artista.getNome() + "]";
    }
}
