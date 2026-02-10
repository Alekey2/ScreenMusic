package br.com.ScreenMusic;

import br.com.ScreenMusic.principal.Principal;
import br.com.ScreenMusic.repository.ArtistaRepository;
import br.com.ScreenMusic.repository.MusicaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenMusicApplication implements CommandLineRunner {

	@Autowired
	private MusicaRepository musicaRepository;

	@Autowired
	private ArtistaRepository artistaRepository;

	public static void main(String[] args) {
		SpringApplication.run(ScreenMusicApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(musicaRepository, artistaRepository);
		principal.exibeMenu();
	}
}
