package modelo;

/**
 * Identidad común de las personas registradas.
 * El nombre de usuario es inmutable para conservar su unicidad.
 */
public abstract class Usuario {
    /** Nombre de usuario único dentro de una plataforma. */
    private String nombreUsuario;
    /** Dirección de correo asociada a la cuenta. */
    private String email;

    /**
     * Construye la identidad de un usuario.
     *
     * @param nombreUsuario nombre único elegido por la persona
     * @param email dirección de correo de la persona
     */
    protected Usuario(String nombreUsuario, String email) {
        this.nombreUsuario = nombreUsuario;
        this.email = email;
    }

    /**
     * Devuelve el nombre de usuario.
     *
     * @return nombre de usuario
     */
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    /**
     * Devuelve el correo electrónico.
     *
     * @return correo electrónico
     */
    public String getEmail() {
        return email;
    }

    /**
     * Cambia el correo electrónico.
     *
     * @param email nuevo correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Devuelve la representación textual específica del perfil.
     *
     * @return representación textual del usuario
     */
    @Override
    public abstract String toString();
}
