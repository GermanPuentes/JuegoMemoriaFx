# Juego de Memoria Educativo

Este es un proyecto desarrollado para la materia **Paradigmas de la Programación** de la carrera **Ingeniería de Software** en la **Universidad Politécnico Grancolombiano**. El objetivo del proyecto es crear un juego educativo sencillo para niños utilizando JavaFX y el paradigma de programación orientada a objetos (POO).

## Descripción del Proyecto

El juego consiste en emparejar números con sus nombres en inglés en un tablero interactivo. Está diseñado para dos jugadores, quienes toman turnos para seleccionar celdas y buscar las parejas correctas. Este proyecto busca enseñar los números en inglés de una forma divertida y visualmente atractiva.

### Características del Juego

- **Tablero interactivo:** Representado como una cuadrícula de 4x4 con celdas personalizables.
- **Dos jugadores:** Turnos alternados para encontrar las parejas.
- **Puntaje dinámico:** Se actualiza automáticamente cuando los jugadores encuentran una pareja.
- **Estilo atractivo:** Colores brillantes y diseño pensado para captar la atención de los niños.
- **Validación de parejas:** Los números deben coincidir con sus nombres en inglés (ejemplo: `1` con `One`).

### Capturas de Pantalla

![image](https://github.com/user-attachments/assets/a9f04d3f-bffe-4caf-9387-9c25da0ca3b2)

![image](https://github.com/user-attachments/assets/ed5f0bde-d6a3-4921-88ee-7abca0384b1b)

![image](https://github.com/user-attachments/assets/990b61a6-4e9a-44bd-9045-4e989bce53a4)


## Instalación y Ejecución

### Requisitos Previos

- **Java 8+**
- **Eclipse IDE** con soporte para JavaFX configurado.

### Configuración Inicial

1. Descarga o clona el repositorio:
   - `git clone https://github.com/GermanPuentes/juego-memoria-educativo.git`
2. Importa el proyecto en Eclipse:
   - Selecciona **File > Import > Existing Projects into Workspace**.
   - Navega a la carpeta donde descargaste el proyecto y selecciónalo.
3. Configura JavaFX en Eclipse siguiendo [este tutorial de YouTube](https://www.youtube.com/watch?v=MhGtkX0eIL8).
4. Ejecuta la clase principal `Main.java` para iniciar el juego.

## Estructura del Proyecto

El proyecto está organizado siguiendo los principios de programación orientada a objetos. La estructura es la siguiente:

- `src/`
  - `application/` - Recursos de configuración (CSS, inicialización)
  - `controlador/` - Lógica de la interfaz gráfica (controlador de eventos)
  - `modelo/` - Clases de lógica del juego (POO)
  - `vista/` - Interfaz gráfica definida en FXML

### Principales Clases

1. **`ControladorMemoria`**  
   Administra la lógica principal del juego. Verifica turnos, emparejamientos y finalización del juego.

2. **`Tablero`**  
   Representa el tablero de juego. Gestiona las celdas y el estado de cada una.

3. **`Celda`**  
   Representa una celda del tablero. Contiene el valor (número o nombre en inglés) y su estado (revelada o no).

4. **`juegoMemoriaFXController`**  
   Controlador principal que conecta la interfaz gráfica con la lógica del juego. Gestiona los eventos de clic y la actualización de la interfaz.

## Tecnologías Utilizadas

- **JavaFX** para la interfaz gráfica.
- **Eclipse IDE** para el desarrollo.
- **CSS** para la personalización visual.
- **Paradigma POO** para la lógica del juego.

## Créditos

**Configuración de JavaFX inicial basada en el [tutorial del canal: Cool IT Help en YouTube](https://www.youtube.com/watch?v=MhGtkX0eIL8)**.

