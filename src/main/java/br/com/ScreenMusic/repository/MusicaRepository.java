package br.com.ScreenMusic.repository;

import br.com.ScreenMusic.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    @Query("SELECT m FROM Musica m WHERE m.artista.nome = :nomeArtista")
    List<Musica> buscarMusicasPorArtista(@Param("nomeArtista") String nomeArtista);


}
