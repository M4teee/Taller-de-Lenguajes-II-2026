package modelo;

/**
 * Contenido musical con año de lanzamiento.
 * Hereda los datos comunes de un contenido reproducible.
 */
public class Cancion extends Contenido {
    /** Año en que se lanzó la canción. */
    private int anioLanzamiento;

    /**
     * Construye una canción.
     *
     * @param titulo título de la canción
     * @param duracionSegundos duración positiva en segundos
     * @param genero género musical
     * @param creador creador responsable
     * @param exclusivo indica si requiere un plan pago
     * @param anioLanzamiento año de lanzamiento
     */
    public Cancion(String titulo, int duracionSegundos, Genero genero, Creador creador,
            boolean exclusivo, int anioLanzamiento) {
        super(titulo, duracionSegundos, genero, creador, exclusivo);
        this.anioLanzamiento = anioLanzamiento;
    }

    /**
     * Devuelve el año de lanzamiento.
     *
     * @return año de lanzamiento
     */
    public int getAnioLanzamiento() {
        return anioLanzamiento;
    }

    /**
     * Cambia el año de lanzamiento.
     *
     * @param anioLanzamiento nuevo año
     */
    public void setAnioLanzamiento(int anioLanzamiento) {
        this.anioLanzamiento = anioLanzamiento;
    }

    /**
     * Devuelve los datos comunes y el año de lanzamiento.
     *
     * @return representación textual completa de la canción
     */
    @Override
    public String toString() {
        return "Canción: " + getTitulo() + ", creador: " + getCreador().getNombreUsuario()
                + ", género: " + getGenero() + ", duración: " + getDuracionSegundos()
                + " segundos, exclusivo: " + isExclusivo() + ", me gusta: "
                + getCantidadMeGusta() + ", año de lanzamiento: " + anioLanzamiento;
    }
}
