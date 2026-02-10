package br.com.ScreenMusic.principal;

import br.com.ScreenMusic.model.Artista;
import br.com.ScreenMusic.model.Musica;
import br.com.ScreenMusic.model.TipoArtista;
import br.com.ScreenMusic.repository.ArtistaRepository;
import br.com.ScreenMusic.repository.MusicaRepository;
import br.com.ScreenMusic.service.DescreveArtista;

import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    Scanner sc = new Scanner(System.in);
    private Artista artista = new Artista();
    private Musica musica = new Musica();
    private DescreveArtista descreveArtista = new DescreveArtista();
    private MusicaRepository musicaRepository;
    private ArtistaRepository artistaRepository;

    public Principal(MusicaRepository musicaRepository, ArtistaRepository artistaRepository) {
        this.musicaRepository = musicaRepository;
        this.artistaRepository = artistaRepository;
    }


    public void exibeMenu() {
        var opcao = -1;
        while (opcao != 0) {
            var menu = """
                    *** Screen Sound Músicas ***
                    
                    
                    1- Cadastrar artista
                    2- Cadastrar música
                    3- Listar músicas
                    4- Buscar musicas por artista
                    5- Pesquisar dados sobre um artista
                    
                    0 - Sair                                 
                    """;

            System.out.println(menu);
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    cadastroArtista();
                    break;
                case 2:
                    cadastroMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscaMusicaArtista();
                    break;
                case 5:
                    pesquisaArtista();
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida");

            }
        }

    }

    private Artista cadastroArtista() {
        var opcao = "S";
        while (!opcao.equalsIgnoreCase("N")) {
            System.out.println("Informe o nome do artista: ");
            artista.setNome(sc.nextLine());

            System.out.println("Informe o tipo desse artista: (Solo, Dupla, Banda)");
            var tipoDoArtista = sc.nextLine();

            TipoArtista tipoArtista = TipoArtista.fromString(tipoDoArtista);
            artista.setGenero(tipoArtista);


            System.out.println("Deseja cadastrar outro artista (S/N)");
            opcao = sc.nextLine();



        }
        return artistaRepository.save(artista);
    }


    private Musica createMusica() {
        System.out.println("Digite o nome da Música: ");
        musica.setNome(sc.nextLine());

        System.out.println("Digite o tempo da música: ");
        int duracao = sc.nextInt();
        Duration duration = Duration.ofSeconds(duracao);
        musica.setDuracao(duration);
        sc.nextLine();
        System.out.println("Digite o Gênero da música:");
        musica.setGenero(sc.nextLine());


        return musica;
    }

    private void cadastroMusicas() {
        System.out.println("Digite o nome do artista:");
        String nomeArtista = sc.nextLine();

        Optional<Artista> optionalArtista =
                artistaRepository.findByNomeIgnoreCase(nomeArtista);

        if (optionalArtista.isEmpty()) {
            System.out.println("Artista não encontrado!");
            return;
        }

        Artista artista = optionalArtista.get();

        Musica musica = createMusica();

        // relacionamento bidirecional
        musica.setArtista(artista);
        artista.getMusicas().add(musica);

        artistaRepository.save(artista);

        System.out.println("Música adicionada ao artista com sucesso!");
    }

    private void listarMusicas() {
    musicaRepository.findAll().forEach(System.out::println);

    }

    private void buscaMusicaArtista() {
        System.out.println("Digite o nome do Artista: ");
        var nomeArtista = sc.nextLine();
        List<Musica> musicas = musicaRepository.buscarMusicasPorArtista(nomeArtista);
        musicas.forEach(m-> System.out.println("Nome das músicas:\n"+m.getNome()));
    }

    private void pesquisaArtista() {
        System.out.println("Digite o nome do Artista: ");
        var nomeArtista = sc.nextLine();
        System.out.println(DescreveArtista.obterInformacao(nomeArtista).trim());
    }

}
