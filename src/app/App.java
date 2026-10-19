package app;

import java.time.LocalDateTime;

import modelo.Album;
import modelo.Audiolibro;
import modelo.Cancion;
import modelo.Consumidor;
import modelo.Contenido;
import modelo.Creador;
import modelo.EpisodioPodcast;
import modelo.Genero;
import modelo.PlanGratuito;
import modelo.PlanIndividual;
import modelo.PlanPremium;
import modelo.Plataforma;
import modelo.Playlist;
import modelo.Reproduccion;
import modelo.Reproductor;
import modelo.Usuario;

/**
 * Ejecuta una demostración de las operaciones del modelo de streaming.
 * Los datos se crean en el programa y esta clase es la única que imprime.
 */
public class App {
    /**
     * Evita instanciar la clase que solo contiene el punto de entrada estático.
     */
    private App() {
    }

    /**
     * Ejecuta los ejemplos de alta, publicación, escucha y consultas.
     *
     * @param args argumentos de ejecución, no utilizados
     */
    public static void main(String[] args) {
        Plataforma plataforma = new Plataforma("Plataforma de Streaming");

        Consumidor consumidorGratis = new Consumidor("Valentino", "valentino@gmail.com",
                new PlanGratuito());
        Consumidor consumidorIndividual = new Consumidor("Laura", "laura@gmail.com",
                new PlanIndividual());
        Creador creadorMusical = new Creador("Mateo", "mateo@gmail.com", new PlanPremium());
        Creador creadorPodcast = new Creador("Markos", "markos@gmail.com", new PlanIndividual());
        Consumidor usuarioRepetido = new Consumidor("Valentino", "valentino.otro@gmail.com",
                new PlanGratuito());

        System.out.println("1. Alta de usuarios");
        System.out.println("Alta Valentino: " + plataforma.agregarUsuario(consumidorGratis));
        System.out.println("Alta Laura: " + plataforma.agregarUsuario(consumidorIndividual));
        System.out.println("Alta Mateo: " + plataforma.agregarUsuario(creadorMusical));
        System.out.println("Alta Markos: " + plataforma.agregarUsuario(creadorPodcast));
        System.out.println("Alta con nombre repetido: " + plataforma.agregarUsuario(usuarioRepetido));

        Cancion cancionUno = new Cancion("Río de noche", 210, Genero.ROCK_NACIONAL,
                creadorMusical, false, 2024);
        Cancion cancionDos = new Cancion("Pulso", 185, Genero.TRAP, creadorMusical, false, 2025);
        Cancion cancionTres = new Cancion("Luz exclusiva", 200, Genero.POP, creadorMusical, true,
                2026);
        EpisodioPodcast episodioUno = new EpisodioPodcast("Historias del puerto", 1800,
                Genero.TRUE_CRIME, creadorPodcast, false, "Relatos", 1, 1);
        EpisodioPodcast episodioDos = new EpisodioPodcast("Una idea", 1500, Genero.EDUCATIVO,
                creadorPodcast, false, "Conversaciones", 1, 2);
        EpisodioPodcast episodioTres = new EpisodioPodcast("El viaje", 1600,
                Genero.CIENCIA_FICCION, creadorPodcast, true, "Mundos", 2, 1);
        Audiolibro audiolibroUno = new Audiolibro("Cuentos breves", 2400,
                Genero.MUSICA_INFANTIL, creadorPodcast, false, "Ana Ruiz", 8);
        Audiolibro audiolibroDos = new Audiolibro("La ciudad", 3600, Genero.FOLKLORE,
                creadorMusical, false, "Luis Paz", 12);
        Audiolibro audiolibroTres = new Audiolibro("Viaje sonoro", 3000, Genero.ELECTRONICA,
                creadorMusical, true, "Mara Sol", 10);
        Contenido fueraDelCatalogo = new Cancion("Sin publicar", 120, Genero.HUMOR,
                creadorMusical, false, 2026);

        Album album = creadorMusical.crearAlbum("Primeras canciones");
        creadorMusical.agregarCancionAAlbum(album, cancionUno);
        creadorMusical.agregarCancionAAlbum(album, cancionDos);

        System.out.println("\n2. Publicación de contenidos");
        System.out.println("Canción 1: " + plataforma.publicarContenido(cancionUno));
        System.out.println("Canción 2: " + plataforma.publicarContenido(cancionDos));
        System.out.println("Canción 3: " + plataforma.publicarContenido(cancionTres));
        System.out.println("Episodio 1: " + plataforma.publicarContenido(episodioUno));
        System.out.println("Episodio 2: " + plataforma.publicarContenido(episodioDos));
        System.out.println("Episodio 3: " + plataforma.publicarContenido(episodioTres));
        System.out.println("Audiolibro 1: " + plataforma.publicarContenido(audiolibroUno));
        System.out.println("Audiolibro 2: " + plataforma.publicarContenido(audiolibroDos));
        System.out.println("Audiolibro 3: " + plataforma.publicarContenido(audiolibroTres));
        System.out.println("Publicación duplicada: " + plataforma.publicarContenido(cancionUno));
        System.out.println("Duración del álbum: " + album.getDuracionTotal() + " segundos");

        Playlist playlistGratis = consumidorGratis.crearPlaylist("Para escuchar");
        Playlist playlistLaura = consumidorIndividual.crearPlaylist("Favoritos de Laura");
        System.out.println("\n3. Playlists");
        System.out.println("Agregar canción a playlist: "
                + plataforma.agregarAPlaylist(consumidorGratis, playlistGratis, cancionUno));
        System.out.println("Agregar episodio a playlist: "
                + plataforma.agregarAPlaylist(consumidorGratis, playlistGratis, episodioUno));
        System.out.println("Agregar exclusivo a playlist: "
                + plataforma.agregarAPlaylist(consumidorGratis, playlistGratis, cancionTres));
        System.out.println("Agregar duplicado: "
                + plataforma.agregarAPlaylist(consumidorGratis, playlistGratis, cancionUno));
        System.out.println("Agregar contenido fuera del catálogo: "
                + plataforma.agregarAPlaylist(consumidorGratis, playlistGratis, fueraDelCatalogo));
        System.out.println("Agregar a playlist de otro consumidor: "
                + plataforma.agregarAPlaylist(consumidorGratis, playlistLaura, cancionDos));
        System.out.println("Agregar canción a playlist de Laura: "
                + plataforma.agregarAPlaylist(consumidorIndividual, playlistLaura, cancionDos));

        System.out.println("\n4. Me gusta y publicaciones");
        System.out.println("Me gusta canción: " + plataforma.darMeGusta(consumidorGratis, cancionUno));
        System.out.println("Me gusta episodio: " + plataforma.darMeGusta(consumidorGratis, episodioUno));
        System.out.println("Me gusta duplicado: " + plataforma.darMeGusta(consumidorGratis, cancionUno));
        System.out.println("Me gusta de Valentino: " + plataforma.meGustaDe(consumidorGratis).size());
        System.out.println("Publicaciones de Mateo: "
                + plataforma.contenidosPublicadosPor(creadorMusical).size());
        System.out.println("Publicaciones de Mateo desde ayer: "
                + plataforma.publicacionesDesde(creadorMusical, LocalDateTime.now().minusDays(1)));
        System.out.println("Publicaciones de Mateo desde mañana: "
                + plataforma.publicacionesDesde(creadorMusical, LocalDateTime.now().plusDays(1)));

        Reproductor reproductor = plataforma.crearReproductor(consumidorGratis);
        System.out.println("\n5. Reproductor de Valentino");
        System.out.println("Contenidos cargados desde playlist: "
                + reproductor.cargarPlaylist(playlistGratis));
        System.out.println("Agregar contenido publicado a la fila: "
                + reproductor.agregarAFila(audiolibroUno));
        System.out.println("Agregar contenido fuera del catálogo a la fila: "
                + reproductor.agregarAFila(fueraDelCatalogo));
        System.out.println("Iniciar reproducción: " + reproductor.reproducir());
        reproductor.transcurrir(30);
        boolean pauso = reproductor.pausar();
        int segundoPausado = reproductor.getSegundoActual();
        boolean reanudo = reproductor.reanudar();
        boolean conservaSegundo = segundoPausado == reproductor.getSegundoActual();
        System.out.println("Pausar y reanudar: " + pauso + ", " + reanudo
                + "; conserva el segundo " + segundoPausado + ": " + conservaSegundo);
        boolean avanzo = reproductor.siguiente();
        boolean retrocedio = reproductor.anterior();
        System.out.println("Navegar a siguiente y anterior: " + avanzo + ", " + retrocedio);
        System.out.println("Quitar el próximo contenido de la fila: "
                + reproductor.quitarDeFila(1));
        System.out.println("Intentar reproducir el contenido exclusivo: "
                + reproductor.siguiente());
        System.out.println("Estado actual: " + reproductor.getEstado() + ", contenido: "
                + reproductor.getActual().getTitulo() + ", segundo: "
                + reproductor.getSegundoActual());
        System.out.println("Cantidad de reproducciones de Valentino: "
                + plataforma.historialDe(consumidorGratis).size());
        System.out.println("Historial de Valentino:");
        for (Reproduccion reproduccion : plataforma.historialDe(consumidorGratis)) {
            System.out.println(reproduccion.getContenido().getTitulo() + " - "
                    + reproduccion.getFechaHora());
        }

        System.out.println("\n6. Catálogo completo");
        for (Contenido contenido : plataforma.getCatalogo()) {
            System.out.println(contenido);
        }
        System.out.println("Usuarios registrados");
        for (Usuario usuario : plataforma.getUsuarios()) {
            System.out.println(usuario);
        }
        System.out.println("Playlists de Valentino");
        for (Playlist playlist : consumidorGratis.getPlaylists()) {
            System.out.println(playlist);
        }
        System.out.println("Playlists de Laura");
        for (Playlist playlist : consumidorIndividual.getPlaylists()) {
            System.out.println(playlist);
        }
    }
}
