package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Coordina usuarios, catálogo y registros de reproducción.
 * Valida las operaciones que dependen del estado global de la plataforma.
 */
public class Plataforma {
    /** Nombre visible de la plataforma. */
    private String nombre;
    /** Usuarios registrados. */
    private List<Usuario> usuarios;
    /** Contenidos publicados. */
    private List<Contenido> catalogo;
    /** Reproducciones iniciadas mediante la plataforma. */
    private List<Reproduccion> reproducciones;

    /**
     * Construye una plataforma con sus colecciones vacías.
     *
     * @param nombre nombre de la plataforma
     */
    public Plataforma(String nombre) {
        this.nombre = nombre;
        this.usuarios = new ArrayList<>();
        this.catalogo = new ArrayList<>();
        this.reproducciones = new ArrayList<>();
    }

    /**
     * Devuelve el nombre de la plataforma.
     *
     * @return nombre de la plataforma
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre de la plataforma.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve una copia de los usuarios registrados.
     *
     * @return copia de los usuarios
     */
    public List<Usuario> getUsuarios() {
        return new ArrayList<>(usuarios);
    }

    /**
     * Devuelve una copia del catálogo.
     *
     * @return copia de los contenidos publicados
     */
    public List<Contenido> getCatalogo() {
        return new ArrayList<>(catalogo);
    }

    /**
     * Devuelve una copia de los registros de reproducción.
     *
     * @return copia de las reproducciones
     */
    public List<Reproduccion> getReproducciones() {
        return new ArrayList<>(reproducciones);
    }

    /**
     * Registra un usuario si no es null y su nombre aún no existe.
     *
     * @param usuario usuario que se quiere registrar
     * @return false si es null o tiene un nombre de usuario repetido
     */
    public boolean agregarUsuario(Usuario usuario) {
        if (usuario == null) {
            return false;
        }
        String nombreUsuario = usuario.getNombreUsuario();
        for (Usuario registrado : usuarios) {
            String nombreRegistrado = registrado.getNombreUsuario();
            if (nombreUsuario == null ? nombreRegistrado == null
                    : nombreUsuario.equals(nombreRegistrado)) {
                return false;
            }
        }
        usuarios.add(usuario);
        return true;
    }

    /**
     * Crea un reproductor asociado al consumidor y a esta plataforma.
     *
     * @param consumidor consumidor del reproductor
     * @return reproductor nuevo, sin validación ni almacenamiento
     */
    public Reproductor crearReproductor(Consumidor consumidor) {
        return new Reproductor(consumidor, this);
    }

    /**
     * Publica un contenido de un creador registrado.
     *
     * @param contenido contenido que se quiere publicar
     * @return false si es null, el creador no está registrado o ya está publicado
     */
    public boolean publicarContenido(Contenido contenido) {
        if (contenido == null || !usuarios.contains(contenido.getCreador())
                || catalogo.contains(contenido)) {
            return false;
        }
        contenido.setFechaPublicacion(LocalDateTime.now());
        catalogo.add(contenido);
        return true;
    }

    /**
     * Agrega al catálogo un contenido en una playlist propia del consumidor.
     *
     * @param consumidor consumidor propietario
     * @param playlist playlist de ese consumidor
     * @param contenido contenido publicado que se quiere agregar
     * @return false si falla alguna pertenencia o si el contenido ya estaba
     */
    public boolean agregarAPlaylist(Consumidor consumidor, Playlist playlist,
            Contenido contenido) {
        if (!usuarios.contains(consumidor) || playlist == null
                || !consumidor.getPlaylists().contains(playlist)
                || !catalogo.contains(contenido)) {
            return false;
        }
        return playlist.agregar(contenido);
    }

    /**
     * Registra el me gusta de un consumidor sobre un contenido publicado.
     *
     * @param consumidor consumidor que da me gusta
     * @param contenido contenido que recibe el me gusta
     * @return false si alguno no está registrado/publicado o si ya le gustaba
     */
    public boolean darMeGusta(Consumidor consumidor, Contenido contenido) {
        if (!usuarios.contains(consumidor) || !catalogo.contains(contenido)) {
            return false;
        }
        return contenido.darMeGusta(consumidor);
    }

    /**
     * Registra el inicio de reproducción si el usuario y el acceso son válidos.
     * Constructor de paquete: crear solo desde Reproductor.
     *
     * @param consumidor consumidor que escucha
     * @param contenido contenido que se inicia
     * @return false si el consumidor, el contenido o el acceso no son válidos
     */
    boolean reproducir(Consumidor consumidor, Contenido contenido) {
        if (!usuarios.contains(consumidor) || !catalogo.contains(contenido)
                || consumidor.getPlan() == null
                || !consumidor.getPlan().puedeAcceder(contenido)) {
            return false;
        }
        reproducciones.add(new Reproduccion(consumidor, contenido, LocalDateTime.now()));
        return true;
    }

    /**
     * Cuenta las reproducciones del contenido indicado.
     *
     * @param contenido contenido consultado
     * @return cantidad de reproducciones
     */
    public int cantidadReproducciones(Contenido contenido) {
        int cantidad = 0;
        for (Reproduccion reproduccion : reproducciones) {
            if (reproduccion.getContenido() == contenido) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /**
     * Devuelve las reproducciones iniciadas por un consumidor.
     *
     * @param consumidor consumidor consultado
     * @return lista nueva con su historial
     */
    public List<Reproduccion> historialDe(Consumidor consumidor) {
        List<Reproduccion> historial = new ArrayList<>();
        for (Reproduccion reproduccion : reproducciones) {
            if (reproduccion.getConsumidor() == consumidor) {
                historial.add(reproduccion);
            }
        }
        return historial;
    }

    /**
     * Devuelve los contenidos del catálogo publicados por el creador.
     *
     * @param creador creador consultado
     * @return lista nueva de sus contenidos publicados
     */
    public List<Contenido> contenidosPublicadosPor(Creador creador) {
        List<Contenido> publicados = new ArrayList<>();
        for (Contenido contenido : catalogo) {
            if (contenido.getCreador() == creador) {
                publicados.add(contenido);
            }
        }
        return publicados;
    }

    /**
     * Cuenta publicaciones del creador desde la fecha indicada, inclusive.
     *
     * @param creador creador consultado
     * @param desde fecha mínima incluida
     * @return cantidad de publicaciones desde esa fecha
     */
    public int publicacionesDesde(Creador creador, LocalDateTime desde) {
        int cantidad = 0;
        for (Contenido contenido : catalogo) {
            LocalDateTime fecha = contenido.getFechaPublicacion();
            if (contenido.getCreador() == creador && fecha != null && !fecha.isBefore(desde)) {
                cantidad++;
            }
        }
        return cantidad;
    }

    /**
     * Devuelve los contenidos que recibieron un me gusta del consumidor.
     *
     * @param consumidor consumidor consultado
     * @return lista nueva de contenidos que le gustan
     */
    public List<Contenido> meGustaDe(Consumidor consumidor) {
        List<Contenido> favoritos = new ArrayList<>();
        for (Contenido contenido : catalogo) {
            if (contenido.leGustaA(consumidor)) {
                favoritos.add(contenido);
            }
        }
        return favoritos;
    }
}
