package modelo;

/**
 * Contenido de podcast con nombre, temporada y número de episodio.
 * Hereda los datos comunes de un contenido reproducible.
 */
public class EpisodioPodcast extends Contenido {
    /** Nombre del podcast al que pertenece. */
    private String nombrePodcast;
    /** Temporada del episodio. */
    private int temporada;
    /** Número del episodio dentro de la temporada. */
    private int numeroEpisodio;

    /**
     * Construye un episodio de podcast.
     *
     * @param titulo título del episodio
     * @param duracionSegundos duración positiva en segundos
     * @param genero género del episodio
     * @param creador creador responsable
     * @param exclusivo indica si requiere un plan pago
     * @param nombrePodcast nombre del podcast
     * @param temporada temporada del episodio
     * @param numeroEpisodio número del episodio
     */
    public EpisodioPodcast(String titulo, int duracionSegundos, Genero genero, Creador creador,
            boolean exclusivo, String nombrePodcast, int temporada, int numeroEpisodio) {
        super(titulo, duracionSegundos, genero, creador, exclusivo);
        this.nombrePodcast = nombrePodcast;
        this.temporada = temporada;
        this.numeroEpisodio = numeroEpisodio;
    }

    /**
     * Devuelve el nombre del podcast.
     *
     * @return nombre del podcast
     */
    public String getNombrePodcast() {
        return nombrePodcast;
    }

    /**
     * Cambia el nombre del podcast.
     *
     * @param nombrePodcast nuevo nombre
     */
    public void setNombrePodcast(String nombrePodcast) {
        this.nombrePodcast = nombrePodcast;
    }

    /**
     * Devuelve la temporada.
     *
     * @return temporada del episodio
     */
    public int getTemporada() {
        return temporada;
    }

    /**
     * Cambia la temporada.
     *
     * @param temporada nueva temporada
     */
    public void setTemporada(int temporada) {
        this.temporada = temporada;
    }

    /**
     * Devuelve el número del episodio.
     *
     * @return número del episodio
     */
    public int getNumeroEpisodio() {
        return numeroEpisodio;
    }

    /**
     * Cambia el número del episodio.
     *
     * @param numeroEpisodio nuevo número
     */
    public void setNumeroEpisodio(int numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    /**
     * Devuelve los datos comunes y propios del episodio.
     *
     * @return representación textual completa del episodio
     */
    @Override
    public String toString() {
        return "Episodio de podcast: " + getTitulo() + ", creador: "
                + getCreador().getNombreUsuario() + ", género: " + getGenero() + ", duración: "
                + getDuracionSegundos() + " segundos, exclusivo: " + isExclusivo()
                + ", me gusta: " + getCantidadMeGusta() + ", podcast: " + nombrePodcast
                + ", temporada: " + temporada + ", episodio: " + numeroEpisodio;
    }
}
