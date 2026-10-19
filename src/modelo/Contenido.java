package modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Datos y operaciones comunes de una unidad reproducible.
 * La duración es positiva y la fecha queda null hasta la publicación.
 */
public abstract class Contenido {
    /** Título del contenido. */
    private String titulo;
    /** Duración positiva del contenido, expresada en segundos. */
    private int duracionSegundos;
    /** Género asignado al contenido. */
    private Genero genero;
    /** Creador responsable del contenido. */
    private Creador creador;
    /** Indica si el contenido requiere un plan pago. */
    private boolean exclusivo;
    /** Momento de publicación; es null antes de publicar. */
    private LocalDateTime fechaPublicacion;
    /** Consumidores que dieron me gusta al contenido. */
    private List<Consumidor> meGusta;

    /**
     * Construye un contenido aún no publicado y sin me gusta.
     * La duración debe ser positiva.
     *
     * @param titulo título del contenido
     * @param duracionSegundos duración positiva en segundos
     * @param genero género del contenido
     * @param creador creador responsable
     * @param exclusivo indica si requiere un plan pago
     */
    protected Contenido(String titulo, int duracionSegundos, Genero genero, Creador creador,
            boolean exclusivo) {
        this.titulo = titulo;
        this.duracionSegundos = duracionSegundos;
        this.genero = genero;
        this.creador = creador;
        this.exclusivo = exclusivo;
        this.fechaPublicacion = null;
        this.meGusta = new ArrayList<>();
    }

    /**
     * Devuelve el título.
     *
     * @return título del contenido
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * Cambia el título.
     *
     * @param titulo nuevo título
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Devuelve la duración en segundos.
     *
     * @return duración positiva en segundos
     */
    public int getDuracionSegundos() {
        return duracionSegundos;
    }

    /**
     * Devuelve el género.
     *
     * @return género del contenido
     */
    public Genero getGenero() {
        return genero;
    }

    /**
     * Cambia el género.
     *
     * @param genero nuevo género
     */
    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    /**
     * Devuelve el creador responsable.
     *
     * @return creador del contenido
     */
    public Creador getCreador() {
        return creador;
    }

    /**
     * Indica si el contenido es exclusivo.
     *
     * @return true si es exclusivo
     */
    public boolean isExclusivo() {
        return exclusivo;
    }

    /**
     * Cambia la condición de exclusividad.
     *
     * @param exclusivo nueva condición de exclusividad
     */
    public void setExclusivo(boolean exclusivo) {
        this.exclusivo = exclusivo;
    }

    /**
     * Devuelve la fecha de publicación o null si aún no se publicó.
     *
     * @return fecha de publicación, posiblemente null
     */
    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    /**
     * Registra la fecha asignada por Plataforma al publicar.
     *
     * @param fechaPublicacion fecha y hora de publicación
     */
    void setFechaPublicacion(LocalDateTime fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    /**
     * Devuelve una copia de los consumidores que dieron me gusta.
     *
     * @return copia de consumidores que dieron me gusta
     */
    public List<Consumidor> getMeGusta() {
        return new ArrayList<>(meGusta);
    }

    /**
     * Devuelve la cantidad de me gusta registrada.
     *
     * @return cantidad de consumidores que dieron me gusta
     */
    public int getCantidadMeGusta() {
        return meGusta.size();
    }

    /**
     * Indica si el consumidor ya dio me gusta.
     *
     * @param consumidor consumidor consultado
     * @return true si ya dio me gusta
     */
    public boolean leGustaA(Consumidor consumidor) {
        return meGusta.contains(consumidor);
    }

    /**
     * Registra un me gusta si el consumidor no es null ni estaba registrado.
     *
     * @param consumidor consumidor que da me gusta
     * @return false si es null o ya había dado me gusta
     */
    boolean darMeGusta(Consumidor consumidor) {
        if (consumidor == null || meGusta.contains(consumidor)) {
            return false;
        }
        meGusta.add(consumidor);
        return true;
    }

    /**
     * Devuelve una representación textual completa del subtipo.
     *
     * @return representación textual del contenido
     */
    @Override
    public abstract String toString();
}
