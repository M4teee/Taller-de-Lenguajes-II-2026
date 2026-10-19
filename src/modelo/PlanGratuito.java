package modelo;

/**
 * Suscripción gratuita con publicidad y sin descargas.
 * No permite acceder a contenidos exclusivos.
 */
public class PlanGratuito extends Plan {
    /** Construye el plan gratuito, cuyo precio es cero. */
    public PlanGratuito() {
        super(0.0);
    }

    /**
     * Indica que el plan incluye publicidad.
     *
     * @return siempre true
     */
    @Override
    public boolean tienePublicidad() {
        return true;
    }

    /**
     * Indica que el plan no permite descargas.
     *
     * @return siempre false
     */
    @Override
    public boolean permiteDescarga() {
        return false;
    }

    /**
     * Rechaza contenidos exclusivos.
     *
     * @param contenido contenido consultado
     * @return false para null o contenido exclusivo; true en los demás casos
     */
    @Override
    public boolean puedeAcceder(Contenido contenido) {
        return contenido != null && !contenido.isExclusivo();
    }
}
