package modelo;

/**
 * Contenido narrado con narrador y cantidad de capítulos.
 * Hereda los datos comunes de un contenido reproducible.
 */
public class Audiolibro extends Contenido {
    /** Persona que narra el audiolibro. */
    private String narrador;
    /** Cantidad de capítulos del audiolibro. */
    private int cantidadCapitulos;

    /**
     * Construye un audiolibro.
     *
     * @param titulo título del audiolibro
     * @param duracionSegundos duración positiva en segundos
     * @param genero género del audiolibro
     * @param creador creador responsable
     * @param exclusivo indica si requiere un plan pago
     * @param narrador nombre del narrador
     * @param cantidadCapitulos cantidad de capítulos
     */
    public Audiolibro(String titulo, int duracionSegundos, Genero genero, Creador creador,
            boolean exclusivo, String narrador, int cantidadCapitulos) {
        super(titulo, duracionSegundos, genero, creador, exclusivo);
        this.narrador = narrador;
        this.cantidadCapitulos = cantidadCapitulos;
    }

    /**
     * Devuelve el narrador.
     *
     * @return nombre del narrador
     */
    public String getNarrador() {
        return narrador;
    }

    /**
     * Cambia el narrador.
     *
     * @param narrador nuevo narrador
     */
    public void setNarrador(String narrador) {
        this.narrador = narrador;
    }

    /**
     * Devuelve la cantidad de capítulos.
     *
     * @return cantidad de capítulos
     */
    public int getCantidadCapitulos() {
        return cantidadCapitulos;
    }

    /**
     * Cambia la cantidad de capítulos.
     *
     * @param cantidadCapitulos nueva cantidad de capítulos
     */
    public void setCantidadCapitulos(int cantidadCapitulos) {
        this.cantidadCapitulos = cantidadCapitulos;
    }

    /**
     * Devuelve los datos comunes y propios del audiolibro.
     *
     * @return representación textual completa del audiolibro
     */
    @Override
    public String toString() {
        return "Audiolibro: " + getTitulo() + ", creador: " + getCreador().getNombreUsuario()
                + ", género: " + getGenero() + ", duración: " + getDuracionSegundos()
                + " segundos, exclusivo: " + isExclusivo() + ", me gusta: "
                + getCantidadMeGusta() + ", narrador: " + narrador + ", capítulos: "
                + cantidadCapitulos;
    }
}
