package modelo;

/**
 * Suscripción individual paga sin publicidad ni descargas.
 * Permite acceder a los contenidos no nulos del catálogo.
 */
public class PlanIndividual extends Plan {
    /** Construye el plan individual con su precio mensual. */
    public PlanIndividual() {
        super(3500.0);
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
     * Indica que el plan no permite descargas.
     *
     * @return siempre false
     */
    @Override
    public boolean permiteDescarga() {
        return false;
    }
}
