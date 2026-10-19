package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Colección ordenada de contenidos de un consumidor.
 * No contiene referencias duplicadas al mismo contenido.
 */
public class Playlist {
    /** Nombre visible de la playlist. */
    private String nombre;
    /** Contenidos agregados por la plataforma. */
    private List<Contenido> contenidos;

    /**
     * Construye una playlist vacía.
     *
     * @param nombre nombre de la playlist
     */
    Playlist(String nombre) {
        this.nombre = nombre;
        this.contenidos = new ArrayList<>();
    }

    /**
     * Devuelve el nombre.
     *
     * @return nombre de la playlist
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Cambia el nombre.
     *
     * @param nombre nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Devuelve una copia de los contenidos.
     *
     * @return copia de los contenidos
     */
    public List<Contenido> getContenidos() {
        return new ArrayList<>(contenidos);
    }

    /**
     * Agrega un contenido si no es nulo ni está repetido.
     *
     * @param contenido contenido que se quiere agregar
     * @return false si es nulo o ya estaba en la playlist
     */
    boolean agregar(Contenido contenido) {
        if (contenido == null || contenidos.contains(contenido)) {
            return false;
        }
        contenidos.add(contenido);
        return true;
    }

    /**
     * Describe la playlist mediante sus contenidos.
     *
     * @return nombre y contenidos de la playlist
     */
    @Override
    public String toString() {
        String resultado = "Playlist: " + nombre + "\nContenidos:";
        for (Contenido contenido : contenidos) {
            resultado += "\n- " + contenido.toString();
        }
        return resultado;
    }
}
