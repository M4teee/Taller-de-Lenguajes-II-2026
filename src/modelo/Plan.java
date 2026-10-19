package modelo;

/**
 * Define las consultas comunes de una suscripción.
 * Las subclases determinan la publicidad y la posibilidad de descarga.
 */
public abstract class Plan {
    /** Precio mensual fijado al construir el plan. */
    private double precioMensual;

    /**
     * Construye un plan con su precio mensual.
     *
     * @param precioMensual precio del plan en moneda local
     */
    protected Plan(double precioMensual) {
        this.precioMensual = precioMensual;
    }

    /**
     * Devuelve el precio mensual.
     *
     * @return precio mensual
     */
    public double getPrecioMensual() {
        return precioMensual;
    }

    /**
     * Indica si el plan incluye publicidad.
     *
     * @return true si incluye publicidad
     */
    public abstract boolean tienePublicidad();

    /**
     * Indica si el plan permite descargar contenidos.
     *
     * @return true si permite descargas
     */
    public abstract boolean permiteDescarga();

    /**
     * Indica si el plan permite acceder al contenido.
     *
     * @param contenido contenido consultado
     * @return true si el plan permite el acceso
     */
    public boolean puedeAcceder(Contenido contenido) {
        return true;
    }

    /**
     * Arma una descripción a partir del precio y los beneficios del plan.
     *
     * @return descripción de los beneficios
     */
    public String describirBeneficios() {
        String publicidad = tienePublicidad() ? "con publicidad" : "sin publicidad";
        String descarga = permiteDescarga() ? "permite descarga" : "sin descarga";
        return "Precio mensual: " + precioMensual + "; " + publicidad + "; " + descarga;
    }
}
