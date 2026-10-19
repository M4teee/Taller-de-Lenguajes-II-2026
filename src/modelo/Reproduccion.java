package modelo;

import java.time.LocalDateTime;

/**
 * Registro inmutable de un inicio de reproducción.
 * Guarda quién escuchó qué contenido y en qué momento.
 */
public class Reproduccion {
    /** Consumidor que inició la reproducción. */
    private Consumidor consumidor;
    /** Contenido que se empezó a reproducir. */
    private Contenido contenido;
    /** Momento en que comenzó la reproducción. */
    private LocalDateTime fechaHora;

    /**
     * Construye un registro de reproducción.
     *
     * @param consumidor consumidor que escucha
     * @param contenido contenido iniciado
     * @param fechaHora momento del inicio
     */
    Reproduccion(Consumidor consumidor, Contenido contenido, LocalDateTime fechaHora) {
        this.consumidor = consumidor;
        this.contenido = contenido;
        this.fechaHora = fechaHora;
    }

    /**
     * Devuelve el consumidor del registro.
     *
     * @return consumidor que inició la reproducción
     */
    public Consumidor getConsumidor() {
        return consumidor;
    }

    /**
     * Devuelve el contenido del registro.
     *
     * @return contenido iniciado
     */
    public Contenido getContenido() {
        return contenido;
    }

    /**
     * Devuelve el momento de inicio.
     *
     * @return fecha y hora del inicio
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
