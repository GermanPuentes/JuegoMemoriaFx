package controlador;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import modelo.ControladorMemoria;

public class juegoMemoriaFXController {
    @FXML
    private Label welcomeLabel;

    @FXML
    private GridPane gameBoard;

    @FXML
    private Label playerOneScore;

    @FXML
    private Label playerTwoScore;

    @FXML
    private Label currentPlayerTurn;

    private ControladorMemoria controladorMemoria;

    @FXML
    public void initialize() {
        // Inicializa el controlador de la lógica del juego
        int filas = 3; // Ajusta el tamaño según lo que necesites
        int columnas = 4;
        controladorMemoria = new ControladorMemoria(filas, columnas);
        controladorMemoria.inicializar();

        // Configura valores iniciales en la interfaz
        welcomeLabel.setText("¡Bienvenido al juego de memoria!");
        playerOneScore.setText("0");
        playerTwoScore.setText("0");
        currentPlayerTurn.setText("Jugador 1");

        // Genera dinámicamente el tablero en la interfaz
        cargarTableroVisual(filas, columnas);
    }

    private void cargarTableroVisual(int filas, int columnas) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                // Crea celdas en el tablero
                Label celda = new Label("?");
                celda.setStyle("-fx-border-color: black; -fx-font-size: 20px; -fx-alignment: center;");
                celda.setPrefSize(100, 100);

                // Maneja los clics en las celdas
                int finalI = i;
                int finalJ = j;
                celda.setOnMouseClicked(event -> manejarClickCelda(finalI, finalJ, celda));

                // Agrega la celda al GridPane
                gameBoard.add(celda, j, i);
            }
        }
    }

    private void manejarClickCelda(int fila, int columna, Label celda) {
        controladorMemoria.manejarClickCelda(fila, columna);

        // Actualiza el tablero visual después del clic
        boolean revelada = controladorMemoria.getTablero().getCelda(fila, columna).estaRevelada();
        if (revelada) {
            celda.setText(controladorMemoria.getTablero().getCelda(fila, columna).getValor() + "");
        } else {
            celda.setText("?");
        }

        // Actualiza los puntajes y el turno actual
        playerOneScore.setText(String.valueOf(controladorMemoria.getPuntosJugadorUno()));
        playerTwoScore.setText(String.valueOf(controladorMemoria.getPuntosJugadorDos()));
        currentPlayerTurn.setText("Jugador " + controladorMemoria.getJugadorActual());
    }
}
