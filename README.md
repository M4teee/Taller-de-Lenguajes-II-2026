# Trabajo Integrador — Plataforma de Streaming

**Materia:** Taller de Lenguajes II  
**Grupo:** 3  
**Integrantes:** Mateo Rivas Aramburu y Valentino Areco

## Resumen

En este trabajo armamos, en Java, una plataforma sencilla para escuchar música, podcasts y audiolibros. Se pueden registrar usuarios con distintos planes, publicar contenidos, crear playlists, dar "me gusta" y simular la reproducción con una fila. La aplicación de ejemplo carga los datos desde el propio `main`, así que no hace falta ingresar nada por consola.

`src/app` tiene el `main`, que prepara algunos usuarios y contenidos y muestra las operaciones principales. `src/modelo` contiene las clases del sistema: la plataforma, sus usuarios, los contenidos, los planes y el reproductor. En `documentación` está el diagrama UML del grupo. `bin` guarda los `.class` que genera Java y `out/javadoc` la documentación; esas carpetas se pueden volver a generar.
