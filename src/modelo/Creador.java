package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Consumidor que además crea álbumes y publica contenidos.
 * El tipo de creador se deduce de los contenidos que publica.
 */
public class Creador extends Consumidor {
    /** Álbumes creados por este usuario. */
    private List<Album> albumes;

    /**
     * Construye un creador con una colección de álbumes vacía.
     *
     * @param nombreUsuario nombre de usuario
     * @param email correo electrónico
     * @param plan plan inicial
     */
    public Creador(String nombreUsuario, String email, Plan plan) {
        super(nombreUsuario, email, plan);
        this.albumes = new ArrayList<>();
    }

    /**
     * Devuelve una copia de los álbumes.
     *
     * @return copia de los álbumes del creador
     */
    public List<Album> getAlbumes() {
        return new ArrayList<>(albumes);
    }

    /**
     * Crea, guarda y devuelve un álbum propio.
     *
     * @param titulo título del álbum
     * @return álbum creado
     */
    public Album crearAlbum(String titulo) {
        Album album = new Album(titulo);
        albumes.add(album);
        return album;
    }

    /**
     * Agrega al álbum una canción de este creador.
     *
     * @param album álbum que recibirá la canción
     * @param cancion canción que se quiere agregar
     * @return false si alguno no pertenece a este creador o si se repite
     */
    public boolean agregarCancionAAlbum(Album album, Cancion cancion) {
        if (album == null || !albumes.contains(album) || cancion == null
                || cancion.getCreador() != this) {
            return false;
        }
        return album.agregarCancion(cancion);
    }

    /**
     * Agrega los álbumes a la descripción heredada del consumidor.
     *
     * @return descripción legible del creador
     */
    @Override
    public String toString() {
        String resultado = super.toString() + "\nÁlbumes:";
        for (Album album : albumes) {
            resultado += "\n" + album.getTitulo() + " (" + album.getCanciones().size()
                    + " canciones)";
        }
        return resultado;
    }
}
