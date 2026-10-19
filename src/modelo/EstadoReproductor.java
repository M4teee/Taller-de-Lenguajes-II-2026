package modelo;

/**
 * Representa los estados excluyentes del reproductor.
 * En todo momento el reproductor tiene exactamente uno de estos estados.
 */
public enum EstadoReproductor {
    /** El reproductor no está ejecutando una reproducción. */
    DETENIDO,
    /** El reproductor está avanzando en el contenido actual. */
    REPRODUCIENDO,
    /** La reproducción está pausada y conserva su segundo actual. */
    PAUSADO
}
