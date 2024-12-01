package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import modelo.Celda;
import modelo.ControladorMemoria;
import modelo.Tablero;

public class juegoMemoriaFXController {

    @FXML
    private GridPane gameBoard; // Tu GridPane para el tablero
    @FXML
    private Label playerOneScore;
    @FXML
    private Label playerTwoScore;
    @FXML
    private Label currentPlayerTurn;
    @FXML
    private Label welcomeLabel;

    private ControladorMemoria controlador;
    private Button primeraSeleccion;
    private Button segundaSeleccion;

    public void initialize() {
        // Inicializar lógica del juego
        controlador = new ControladorMemoria(4, 4); // Tablero 4x4 como ejemplo
        controlador.inicializar();
        generarTablero();
        actualizarInformacionJuego();
    }

    private void generarTablero() {
        gameBoard.getChildren().clear(); // Limpiar el tablero antes de generarlo

        Tablero tablero = controlador.getTablero(); // Obtener el tablero del controlador
        for (int fila = 0; fila < 4; fila++) {
            for (int columna = 0; columna < 4; columna++) {
                Button celdaButton = new Button();
                celdaButton.setPrefSize(100, 100); // Tamaño de celdas

                // Aplicar estilo CSS
                celdaButton.getStyleClass().add("button-cell");

                // Obtener la celda correspondiente del objeto Tablero
                Celda celda = tablero.getCelda(fila, columna);

                // Configurar el estado inicial del botón en función del estado de la celda
                if (celda.estaRevelada()) {
                    celdaButton.setText(celda.getValor()); // Mostrar el valor si la celda está revelada
                    celdaButton.setDisable(true); // Deshabilitar si ya está emparejada
                } else {
                    celdaButton.setText(""); // Ocultar el valor
                    celdaButton.setDisable(false); // Habilitar la celda
                }

                // Guardar la posición de la celda en el botón
                celdaButton.setUserData(new int[]{fila, columna});

                // Configurar evento de clic para manejar selecciones
                celdaButton.setOnAction(e -> manejarClick(celdaButton));

                // Agregar botón al tablero gráfico
                gameBoard.add(celdaButton, columna, fila);
            }
        }
    }
    private void manejarClick(Button celdaButton) {
        int[] posicion = (int[]) celdaButton.getUserData();
        int fila = posicion[0];
        int columna = posicion[1];

        if (primeraSeleccion == null) {
            // Primera celda seleccionada
            primeraSeleccion = celdaButton;
            mostrarValor(primeraSeleccion, fila, columna);
        } else if (segundaSeleccion == null && celdaButton != primeraSeleccion) {
            // Segunda celda seleccionada
            segundaSeleccion = celdaButton;
            mostrarValor(segundaSeleccion, fila, columna);

            // Verificar si es una pareja
            verificarPareja(fila, columna);
        }
    }

    private void mostrarValor(Button celdaButton, int fila, int columna) {
        String valor = controlador.getTablero().getCelda(fila, columna).getValor();
        celdaButton.setText(valor);
    }

    private void verificarPareja(int fila, int columna) {
        int[] posicionPrimera = (int[]) primeraSeleccion.getUserData();
        int filaPrimera = posicionPrimera[0];
        int columnaPrimera = posicionPrimera[1];

        // Obtener las celdas correspondientes
        Celda celdaPrimera = controlador.getTablero().getCelda(filaPrimera, columnaPrimera);
        Celda celdaSegunda = controlador.getTablero().getCelda(fila, columna);

        if (celdaPrimera.esPareja(celdaSegunda)) {
            // Deshabilitar las celdas en el modelo
            celdaPrimera.deshabilitar();
            celdaSegunda.deshabilitar();

            // Deshabilitar los botones en la interfaz
            primeraSeleccion.setDisable(true);
            segundaSeleccion.setDisable(true);

            // Sumar puntos al jugador actual
            controlador.sumarPuntoAJugadorActual();

            // Verificar si el juego ha terminado
            if (controlador.terminoJuego()) {
                actualizarInformacionJuego(); // Mostrar el mensaje final
                return; // Salir del método si el juego terminó
            }
        } else {
            // Ocultar valores si no son pareja
            ocultarCeldas();
            controlador.cambiarTurno(); // Cambiar turno
        }

        // Reiniciar selección
        primeraSeleccion = null;
        segundaSeleccion = null;

        // Actualizar la información del juego
        actualizarInformacionJuego();
    }
    
    private void ocultarCeldas() {
        Button primera = primeraSeleccion; // Guardar referencias locales
        Button segunda = segundaSeleccion;

        new Thread(() -> {
            try {
                Thread.sleep(1000); // Pausa breve antes de ocultar
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            // Ocultar valores en el hilo FX
            javafx.application.Platform.runLater(() -> {
                if (primera != null) {
                    primera.setText("");
                }
                if (segunda != null) {
                    segunda.setText("");
                }

                // Reiniciar referencias después de ocultar
                primeraSeleccion = null;
                segundaSeleccion = null;
            });
        }).start();
    }

    private void actualizarInformacionJuego() {
        // Actualizar el turno actual
        currentPlayerTurn.setText("Turno: Jugador " + controlador.getJugadorActual());

        // Actualizar los puntajes
        playerOneScore.setText("" + controlador.getPuntosJugadorUno());
        playerTwoScore.setText("" + controlador.getPuntosJugadorDos());

        // Verificar si el juego ha terminado
        if (controlador.terminoJuego()) {
            String mensajeFinal;
            if (controlador.getPuntosJugadorUno() > controlador.getPuntosJugadorDos()) {
                mensajeFinal = "¡Juego terminado!";
            } else if (controlador.getPuntosJugadorUno() < controlador.getPuntosJugadorDos()) {
                mensajeFinal = "¡Juego terminado!";
            } else {
                mensajeFinal = "¡Es un empate!";
            }

            // Mostrar mensaje final en la etiqueta de bienvenida
            welcomeLabel.setText(mensajeFinal);

        }
    }
}
