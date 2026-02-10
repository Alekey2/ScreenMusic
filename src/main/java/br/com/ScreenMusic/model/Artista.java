package br.com.ScreenMusic.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "artistas")
public class Artista {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nome;

    @Enumerated(EnumType.STRING)
    private TipoArtista genero;

    @OneToMany(mappedBy = "artista", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    List<Musica> musicas;

    public Artista() {
    }

    public Artista(Long id, String nome, TipoArtista genero, List<Musica> musicas) {
        this.id = id;
        this.nome = nome;
        this.genero = genero;
        this.musicas = musicas;
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

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public TipoArtista getGenero() {
        return genero;
    }

    public void setGenero(TipoArtista genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "[Dados do Artista: " +
                "Nome: " + nome +
                " | Gênero: " + genero + "]" ;
    }
}
