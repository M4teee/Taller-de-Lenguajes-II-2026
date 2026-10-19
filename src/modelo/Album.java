package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Agrupa canciones de un creador y calcula su duración total.
 * Las canciones pueden existir fuera del álbum.
 */
public class Album {
    /** Título del álbum. */
    private String titulo;
    /** Canciones agregadas al álbum. */
    private List<Cancion> canciones;

    /**
     * Construye un álbum vacío.
     *
     * @param titulo título del álbum
     */
    Album(String titulo) {
        this.titulo = titulo;
        this.canciones = new ArrayList<>();
    }

    /**
     * Devuelve el título.
     *
     * @return título del álbum
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
     * Devuelve una copia de las canciones.
     *
     * @return copia de las canciones
     */
    public List<Cancion> getCanciones() {
        return new ArrayList<>(canciones);
    }

    /**
     * Suma la duración de todas las canciones del álbum.
     *
     * @return duración total en segundos
     */
    public int getDuracionTotal() {
        int total = 0;
        for (Cancion cancion : canciones) {
            total += cancion.getDuracionSegundos();
        }
        return total;
    }

    /**
     * Agrega una canción si no es nula ni está repetida.
     *
     * @param cancion canción que se quiere agregar
     * @return false si es nula o ya está en el álbum
     */
    boolean agregarCancion(Cancion cancion) {
        if (cancion == null || canciones.contains(cancion)) {
            return false;
        }
        canciones.add(cancion);
        return true;
    }
}
