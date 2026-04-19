# Tic Tac Toe en Java 17

## Descripción del proyecto

Este proyecto corresponde a una implementación del juego **Tic Tac Toe** (conocido también como **Tres en Línea** o **Triqui**) desarrollada en **Java 17** para ejecutarse por consola.

El sistema permite que dos jugadores participen de forma local usando los símbolos **X** y **O**, respetando las reglas clásicas del juego. Además, incluye validaciones de entrada, detección de ganador, detección de empate, reinicio de partidas y un marcador acumulado.

La solución fue construida en una única clase `Main`, manteniendo buenas prácticas de desarrollo de software como:

- separación de responsabilidades mediante métodos pequeños,
- nombres descriptivos,
- validación de entradas,
- documentación con **Javadoc**,
- legibilidad y mantenibilidad del código.

---

## Objetivo del juego

El objetivo del juego es lograr colocar **tres símbolos iguales en línea** antes que el otro jugador.

La línea ganadora puede ser:

- horizontal,
- vertical,
- diagonal principal,
- diagonal secundaria.

---

## Reglas del juego

Las reglas del juego son las siguientes:

1. El juego se desarrolla en un tablero de **3x3**.
2. Participan **dos jugadores**.
3. Un jugador utiliza el símbolo **X** y el otro el símbolo **O**.
4. Los jugadores juegan por turnos.
5. En cada turno, un jugador debe seleccionar una casilla vacía.
6. No está permitido jugar en una casilla ya ocupada.
7. Gana el jugador que logre alinear **tres símbolos iguales** en una fila, columna o diagonal.
8. Si se completan las 9 casillas y no hay ganador, la partida termina en **empate**.
9. Al finalizar una partida, el sistema permite iniciar una nueva.
10. El sistema lleva un marcador acumulado de victorias y empates.

---

## Características implementadas

El programa incluye las siguientes funcionalidades:

- inicio de una nueva partida,
- visualización del tablero en consola,
- ingreso de jugadas por parte de los jugadores,
- validación de entradas numéricas,
- validación de rango permitido de posiciones,
- validación de casillas disponibles,
- alternancia automática de turnos,
- detección de victoria,
- detección de empate,
- finalización de la partida,
- reinicio del juego,
- marcador acumulado.

---

## Requisitos técnicos

Para ejecutar este proyecto se requiere:

- **Java Development Kit (JDK) 17** o superior,
- consola o terminal del sistema operativo,
- compilador `javac`,
- intérprete `java`.

Puedes verificar la instalación de Java con los siguientes comandos:

```bash
javac -version
java -version
```

---

## Compilación del proyecto

Ubícate en la carpeta donde se encuentra el archivo `Main.java` y ejecuta:

```bash
javac Main.java
```

Si la compilación es correcta, se generará el archivo:

```bash
Main.class
```

---

## Ejecución del proyecto

Para ejecutar el juego, usa el siguiente comando:

```bash
java Main
```

---

## Instrucciones de uso

Cuando el programa inicia, se mostrará un mensaje de bienvenida con las reglas del juego.

Durante cada turno:

1. El sistema mostrará el tablero.
2. El jugador actual deberá ingresar una posición del **1 al 9**.
3. Cada número representa una casilla del tablero.

La distribución de posiciones es la siguiente:

```text
1 | 2 | 3
---------
4 | 5 | 6
---------
7 | 8 | 9
```

### Ejemplo de uso

Si el jugador **X** ingresa `1`, su símbolo se ubicará en la esquina superior izquierda.

Si el jugador **O** ingresa `5`, su símbolo se ubicará en el centro del tablero.

---

## Ejemplo visual del tablero

Al iniciar una partida, el tablero se verá así:

```text
1 | 2 | 3
---+---+---
4 | 5 | 6
---+---+---
7 | 8 | 9
```

Después de algunas jugadas, podría verse así:

```text
X | 2 | O
---+---+---
4 | X | 6
---+---+---
7 | 8 | O
```

---

## Validaciones realizadas por el sistema

El programa valida correctamente los siguientes casos:

### 1. Entrada no numérica
Si el usuario ingresa texto o caracteres no válidos, el sistema muestra un mensaje de error y vuelve a solicitar la entrada.

### 2. Número fuera del rango permitido
Si el jugador ingresa un número menor que 1 o mayor que 9, el sistema rechaza la jugada.

### 3. Casilla ocupada
Si el jugador intenta marcar una posición ya utilizada, el sistema no permite la acción y solicita una nueva jugada.

### 4. Continuidad de juego
Una vez que existe un ganador o se produce un empate, la partida termina y no se permiten más movimientos en esa ronda.

---

## Flujo general del programa

El flujo de ejecución del sistema es el siguiente:

1. Se muestra un mensaje de bienvenida.
2. Se inicializa el tablero vacío.
3. Se asigna el turno inicial al jugador **X**.
4. Se muestra el tablero.
5. El jugador actual ingresa una posición.
6. El sistema valida la jugada.
7. Si la jugada es válida, se actualiza el tablero.
8. El sistema verifica si hay un ganador.
9. Si no hay ganador, verifica si hay empate.
10. Si la partida no termina, cambia el turno.
11. Al finalizar, se actualiza el marcador.
12. El sistema pregunta si se desea jugar otra partida.
13. Si la respuesta es afirmativa, se reinicia el tablero.
14. Si la respuesta es negativa, el programa finaliza.

---

## Documentación técnica

## Lenguaje y versión

- **Lenguaje:** Java
- **Versión utilizada:** Java 17

---

## Tipo de aplicación

- Aplicación de consola.
- Ejecución local.
- Interacción mediante entrada estándar (`Scanner`).

---

## Diseño de la solución

Aunque el requerimiento solicitó que todo estuviera contenido en una única clase `Main`, el código fue organizado internamente siguiendo principios de diseño limpio mediante métodos específicos y responsabilidades separadas.

### Responsabilidades cubiertas por la clase `Main`

La clase principal se encarga de:

- iniciar la aplicación,
- gestionar el tablero,
- controlar el flujo del juego,
- validar entradas,
- registrar jugadas,
- verificar victorias,
- verificar empates,
- alternar turnos,
- reiniciar partidas,
- mostrar mensajes en consola,
- mantener el marcador acumulado.

---

## Estructura lógica del código

La solución está dividida conceptualmente en los siguientes bloques:

### 1. Constantes
Se definen constantes para:

- tamaño del tablero,
- símbolo de celda vacía,
- configuraciones básicas del juego.

### 2. Variables globales controladas
Se utilizan atributos estáticos para almacenar:

- el tablero,
- el jugador actual,
- el marcador de victorias,
- el número de empates,
- el lector de consola.

### 3. Métodos de inicialización
Estos métodos preparan una nueva partida:

- inicializar tablero,
- reiniciar estado,
- mostrar bienvenida.

### 4. Métodos de interacción con el usuario
Encargados de:

- mostrar el tablero,
- solicitar movimientos,
- imprimir mensajes,
- preguntar si se desea continuar.

### 5. Métodos de validación
Permiten verificar:

- si la entrada es numérica,
- si la posición está en el rango correcto,
- si la casilla está disponible.

### 6. Métodos de lógica del juego
Implementan:

- registrar movimiento,
- cambio de turno,
- validación de victoria,
- validación de empate.

### 7. Métodos de cierre y continuidad
Gestionan:

- actualización de marcador,
- decisión de reiniciar,
- finalización del programa.

---

## Buenas prácticas aplicadas

Aunque se trata de una solución sencilla, se aplicaron varias buenas prácticas de desarrollo de software:

### 1. Métodos pequeños y con una responsabilidad clara
Cada método realiza una tarea específica, lo que facilita la lectura, comprensión y mantenimiento.

### 2. Nombres descriptivos
Los nombres de variables y métodos fueron definidos en inglés, de forma clara y coherente con su propósito.

### 3. Separación lógica de responsabilidades
Aunque existe una sola clase, la lógica está separada por funciones bien delimitadas.

### 4. Validación robusta de entradas
El sistema maneja entradas inválidas sin romper la ejecución.

### 5. Legibilidad del código
Se priorizó una estructura ordenada y fácil de entender.

### 6. Documentación Javadoc
El código incluye documentación técnica con Javadoc para la clase y para los métodos principales.

### 7. Mantenibilidad
La organización del código permite agregar futuras mejoras con bajo impacto.

---

## Métodos principales del sistema

A nivel conceptual, el programa incluye métodos para:

- iniciar el programa,
- mostrar mensaje de bienvenida,
- iniciar una nueva partida,
- inicializar tablero,
- imprimir tablero,
- leer jugada válida,
- convertir posición a coordenadas,
- validar disponibilidad de casilla,
- registrar jugada,
- verificar si un jugador ganó,
- verificar filas,
- verificar columnas,
- verificar diagonales,
- validar empate,
- cambiar turno,
- actualizar marcador,
- imprimir marcador,
- preguntar si se desea jugar otra vez,
- finalizar programa.

---

## Lógica para detectar ganador

La lógica de victoria evalúa si el jugador actual ha completado una de las siguientes combinaciones:

### Filas
- fila 1 completa,
- fila 2 completa,
- fila 3 completa.

### Columnas
- columna 1 completa,
- columna 2 completa,
- columna 3 completa.

### Diagonales
- diagonal principal,
- diagonal secundaria.

Si alguna de estas condiciones se cumple, el sistema declara al jugador como ganador.

---

## Lógica para detectar empate

El sistema declara empate cuando:

- todas las casillas del tablero están ocupadas,
- y ningún jugador ha ganado.

---

## Mapeo de posiciones

El programa permite que el usuario juegue con posiciones del 1 al 9. Internamente, estas posiciones se convierten a coordenadas de fila y columna.

Correspondencia:

```text
Posición 1 -> fila 0, columna 0
Posición 2 -> fila 0, columna 1
Posición 3 -> fila 0, columna 2
Posición 4 -> fila 1, columna 0
Posición 5 -> fila 1, columna 1
Posición 6 -> fila 1, columna 2
Posición 7 -> fila 2, columna 0
Posición 8 -> fila 2, columna 1
Posición 9 -> fila 2, columna 2
```

---

## Consideraciones de mantenibilidad

Aunque la implementación solicitada está en una sola clase, en una versión más escalable del proyecto sería recomendable separar la solución en varias clases, por ejemplo:

- `Main`: punto de entrada,
- `Game`: control del flujo de partida,
- `Board`: gestión del tablero,
- `Player`: representación de los jugadores,
- `InputValidator`: validación de entradas,
- `GameResult`: control del estado del juego.

Esta separación permitiría una arquitectura más limpia, reutilizable y fácil de probar.

---

## Posibles mejoras futuras

El sistema puede ampliarse con funcionalidades como:

- modo jugador contra máquina,
- niveles de dificultad,
- interfaz gráfica,
- persistencia de resultados,
- historial de partidas,
- temporizador por turno,
- selección de nombres de jugadores,
- pruebas unitarias automatizadas,
- separación en múltiples clases,
- aplicación de patrones de diseño.

---

## Limitaciones de la versión actual

Las limitaciones actuales del sistema son:

- solo permite dos jugadores humanos,
- funciona únicamente por consola,
- no guarda partidas en archivos o base de datos,
- no permite personalizar nombres de jugadores,
- no tiene interfaz gráfica,
- toda la lógica está contenida en una única clase.

---

## Ejemplo de ejecución

```text
==========================================
      BIENVENIDO AL JUEGO TIC TAC TOE
==========================================

Tablero actual:

 1 | 2 | 3
---+---+---
 4 | 5 | 6
---+---+---
 7 | 8 | 9

Jugador X, ingrese una posición (1-9): 1

Tablero actual:

 X | 2 | 3
---+---+---
 4 | 5 | 6
---+---+---
 7 | 8 | 9
```

---

## Conclusión

Este proyecto representa una implementación sólida y clara del juego Tic Tac Toe en Java 17, adecuada para fines académicos, de práctica o de aprendizaje de fundamentos de programación.

A pesar de haberse desarrollado en una sola clase, la solución mantiene principios importantes de calidad de software, como la modularidad interna, la validación de entradas, la claridad del flujo lógico y la documentación técnica.

Es una base adecuada para evolucionar hacia versiones más robustas y escalables.

---

## Autor

Proyecto desarrollado como ejercicio académico y práctico en Java 17.

