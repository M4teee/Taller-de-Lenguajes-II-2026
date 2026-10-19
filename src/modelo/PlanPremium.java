package modelo;

/**
 * Suscripción premium sin publicidad y con descargas.
 * Permite acceder a los contenidos no nulos del catálogo.
 */
public class PlanPremium extends Plan {
    /** Construye el plan premium con su precio mensual. */
    public PlanPremium() {
        super(6000.0);
    }

    /**
     * Indica que el plan no incluye publicidad.
     *
     * @return siempre false
     */
    @Override
    public boolean tienePublicidad() {
        return false;
    }

    /**
     * Indica que el plan permite descargas.
     *
     * @return siempre true
     */
    @Override
    public boolean permiteDescarga() {
        return true;
    }
}
