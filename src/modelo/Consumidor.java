package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Usuario que escucha contenidos y administra playlists.
 * Sus playlists se crean y conservan en esta instancia.
 */
public class Consumidor extends Usuario {
    /** Plan de suscripción vigente. */
    private Plan plan;
    /** Playlists creadas por este consumidor. */
    private List<Playlist> playlists;

    /**
     * Construye un consumidor sin playlists.
     *
     * @param nombreUsuario nombre de usuario
     * @param email correo electrónico
     * @param plan plan inicial
     */
    public Consumidor(String nombreUsuario, String email, Plan plan) {
        super(nombreUsuario, email);
        this.plan = plan;
        this.playlists = new ArrayList<>();
    }

    /**
     * Devuelve el plan vigente.
     *
     * @return plan del consumidor
     */
    public Plan getPlan() {
        return plan;
    }

    /**
     * Cambia el plan vigente.
     *
     * @param plan nuevo plan
     */
    public void cambiarPlan(Plan plan) {
        this.plan = plan;
    }

    /**
     * Devuelve una copia de las playlists.
     *
     * @return copia de las playlists del consumidor
     */
    public List<Playlist> getPlaylists() {
        return new ArrayList<>(playlists);
    }

    /**
     * Crea, guarda y devuelve una playlist propia.
     *
     * @param nombre nombre de la playlist
     * @return playlist creada
     */
    public Playlist crearPlaylist(String nombre) {
        Playlist playlist = new Playlist(nombre);
        playlists.add(playlist);
        return playlist;
    }

    /**
     * Describe la identidad, el plan y las playlists del consumidor.
     *
     * @return descripción legible del consumidor
     */
    @Override
    public String toString() {
        String resultado = "Consumidor: " + getNombreUsuario() + "\nEmail: " + getEmail()
                + "\n" + plan.describirBeneficios() + "\nPlaylists:";
        for (Playlist playlist : playlists) {
            resultado += "\n" + playlist.toString();
        }
        return resultado;
    }
}
