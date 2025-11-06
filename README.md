# ALED (Algorithms and Data Structures) Lab Session 1 - Solution

This repository contains the code for a Java application with a GUI that plots EEG sessions recorded as OpenBCI format CSV files. This code is the solution for this lab session: https://github.com/rgarciacarmona/ALED-lab1

## Respuestas a las preguntas

### Sección 1.4

- **¿Qué significan los símbolos “+”, “-” y “#” en el diagrama de clases?:** Que la visibilidad es `public`, `private` o `protected`, respectivamente.
- **¿Qué significa la flecha que conecta `EEGModel` con `Measurement` en el diagrama?:** Que `EEGModel` agrega una lista de `Measurements`.
- **Fíjese en el archivo de ejemplo de una grabación de EEG en el formato OpenBCI que aparecía en la sección 1.2. Piense en qué objetos concretos de las clases `EEGModel` y `Measurement` podrían representar este fichero. ¿De qué tamaño será la `List` `measurements` del objeto de la clase `EEGModel`? ¿De qué tamaño será el array `channels`  de los objetos de la clase `Measurement`?:** `measurements`` tendrá 11 elementos. `channels` tendrá tamaño 4.
- **¿Cuántos métodos tiene `EEGModel`? ¿Cuáles aún no están implementados?:** Tiene 16 métodos, de los cuales 4 todavía no están implementados.
- **¿Cuántos argumentos puede aceptar el método `main` de la clase `EEGModel`? ¿Cuántas formas de funcionamiento tiene este método y qué hace cada una de ellas?:** Acepta 0 o 1 argumento. Si no se le pasa ningún argumento, crea un archivo de muestras sintético. Si se le pasa un argumento, abrirá el archivo de muestras cuyo nombre es este argumento.

### Sección 2

- **¿Cuántos canales tiene cada uno de los archivos?:** Uno 11 canales y el otro 8.
- **¿Cuántas muestras tiene cada uno de los archivos?:** Uno 9.818 muestras y el otro 21.026.

### Sección 2.1

- **¿Cuántos canales tiene la sesión sintética? ¿En qué parte del código se controla este valor?:** 4. Se controla en el siguiente código del método `createSyntheticData()`:
```Java
Measurement m = createSyntheticMeasurement(4, 250, 1.0f);
```
- **¿Cuántas muestras tiene la sesión sintética? ¿En qué parte del código se controla este valor?:** 1000. Se controla en el siguiente código del método `main()`:
```Java
eeg.createSyntheticData(1000);
```
