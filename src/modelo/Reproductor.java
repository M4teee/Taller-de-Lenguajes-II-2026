package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Simula una reproducción con fila, posición y estado.
 * El tiempo avanza únicamente cuando se llama a transcurrir.
 */
public class Reproductor {
    /** Consumidor que usa el reproductor. */
    private Consumidor consumidor;
    /** Plataforma que valida y registra cada inicio. */
    private Plataforma plataforma;
    /** Fila ordenada de contenidos; admite referencias repetidas. */
    private List<Contenido> fila;
    /** Índice actual en la fila, o -1 antes del primer inicio. */
    private int indiceActual;
    /** Segundo actual del contenido. */
    private int segundoActual;
    /** Estado actual de la simulación. */
    private EstadoReproductor estado;

    /**
     * Construye un reproductor detenido y con la fila vacía.
     * Constructor de paquete: crear solo desde Plataforma.crearReproductor.
     *
     * @param consumidor consumidor asociado
     * @param plataforma plataforma que registra los inicios
     */
    Reproductor(Consumidor consumidor, Plataforma plataforma) {
        this.consumidor = consumidor;
        this.plataforma = plataforma;
        this.fila = new ArrayList<>();
        this.indiceActual = -1;
        this.segundoActual = 0;
        this.estado = EstadoReproductor.DETENIDO;
    }

    /**
     * Devuelve el consumidor asociado.
     *
     * @return consumidor asociado
     */
    public Consumidor getConsumidor() {
        return consumidor;
    }

    /**
     * Devuelve una copia de la fila.
     *
     * @return copia de la fila de reproducción
     */
    public List<Contenido> getFila() {
        return new ArrayList<>(fila);
    }

    /**
     * Devuelve el índice actual.
     *
     * @return índice actual, o -1 antes del primer inicio
     */
    public int getIndiceActual() {
        return indiceActual;
    }

    /**
     * Devuelve el segundo actual.
     *
     * @return segundo actual del contenido
     */
    public int getSegundoActual() {
        return segundoActual;
    }

    /**
     * Devuelve el estado actual.
     *
     * @return estado del reproductor
     */
    public EstadoReproductor getEstado() {
        return estado;
    }

    /**
     * Devuelve el contenido actual o null si el índice no es válido.
     *
     * @return contenido actual, o null si no hay uno
     */
    public Contenido getActual() {
        if (indiceActual < 0 || indiceActual >= fila.size()) {
            return null;
        }
        return fila.get(indiceActual);
    }

    /**
     * Agrega al final un contenido publicado; se permiten repetidos.
     *
     * @param contenido contenido que se quiere encolar
     * @return false si es null o no pertenece al catálogo
     */
    public boolean agregarAFila(Contenido contenido) {
        if (contenido == null || !plataforma.getCatalogo().contains(contenido)) {
            return false;
        }
        fila.add(contenido);
        return true;
    }

    /**
     * Agrega a la fila los contenidos de una playlist.
     *
     * @param playlist playlist consultada
     * @return cantidad de contenidos agregados
     */
    public int cargarPlaylist(Playlist playlist) {
        int agregados = 0;
        if (playlist != null) {
            for (Contenido contenido : playlist.getContenidos()) {
                if (agregarAFila(contenido)) {
                    agregados++;
                }
            }
        }
        return agregados;
    }

    /**
     * Agrega al final de la fila las canciones publicadas de un álbum.
     * Se permiten canciones que ya estén en la fila.
     *
     * @param album álbum cuyas canciones se quieren encolar
     * @return cantidad de canciones agregadas; cero si el álbum es null
     */
    public int cargarAlbum(Album album) {
        int agregados = 0;
        if (album != null) {
            for (Cancion cancion : album.getCanciones()) {
                if (agregarAFila(cancion)) {
                    agregados++;
                }
            }
        }
        return agregados;
    }

    /**
     * Quita una posición posterior al contenido actual.
     *
     * @param posicion posición que se quiere quitar
     * @return false si no es posterior al índice actual o está fuera de rango
     */
    public boolean quitarDeFila(int posicion) {
        if (posicion <= indiceActual || posicion < 0 || posicion >= fila.size()) {
            return false;
        }
        fila.remove(posicion);
        return true;
    }

    /**
     * Inicia el contenido indicado después de registrar el inicio.
     *
     * @param indice índice del contenido en la fila
     * @return false si el índice es inválido o la plataforma rechaza el inicio
     */
    private boolean iniciar(int indice) {
        if (indice < 0 || indice >= fila.size()
                || !plataforma.reproducir(consumidor, fila.get(indice))) {
            return false;
        }
        indiceActual = indice;
        segundoActual = 0;
        estado = EstadoReproductor.REPRODUCIENDO;
        return true;
    }

    /** Detiene la simulación y reinicia el segundo actual. */
    private void detener() {
        estado = EstadoReproductor.DETENIDO;
        segundoActual = 0;
    }

    /**
     * Inicia el contenido actual o el primero de la fila.
     *
     * @return false si ya está activo, la fila está vacía o la plataforma rechaza
     */
    public boolean reproducir() {
        if (estado != EstadoReproductor.DETENIDO || fila.isEmpty()) {
            return false;
        }
        return iniciar(indiceActual == -1 ? 0 : indiceActual);
    }

    /**
     * Pausa la reproducción conservando el segundo actual.
     *
     * @return false salvo que estuviera reproduciendo
     */
    public boolean pausar() {
        if (estado != EstadoReproductor.REPRODUCIENDO) {
            return false;
        }
        estado = EstadoReproductor.PAUSADO;
        return true;
    }

    /**
     * Reanuda desde el segundo guardado sin registrar otro inicio.
     *
     * @return false salvo que estuviera pausado
     */
    public boolean reanudar() {
        if (estado != EstadoReproductor.PAUSADO) {
            return false;
        }
        estado = EstadoReproductor.REPRODUCIENDO;
        return true;
    }

    /**
     * Inicia el siguiente contenido de la fila.
     *
     * @return false si no existe el siguiente o la plataforma rechaza el inicio
     */
    public boolean siguiente() {
        return iniciar(indiceActual + 1);
    }

    /**
     * Inicia el contenido anterior de la fila.
     *
     * @return false si el índice actual es cero o menor, o se rechaza el inicio
     */
    public boolean anterior() {
        if (indiceActual <= 0) {
            return false;
        }
        return iniciar(indiceActual - 1);
    }

    /**
     * Avanza el tiempo simulado y pasa al siguiente al terminar el actual.
     *
     * @param segundos cantidad positiva de segundos transcurridos
     */
    public void transcurrir(int segundos) {
        if (estado != EstadoReproductor.REPRODUCIENDO || segundos <= 0) {
            return;
        }
        segundoActual += segundos;
        if (segundoActual >= getActual().getDuracionSegundos() && !siguiente()) {
            detener();
        }
    }
}
