package modelo;


public class ControladorMemoria {
    private Tablero tablero;
    private int jugadorActual;
    private int puntosJugadorUno;
    private int puntosJugadorDos;

    public ControladorMemoria(int filas, int columnas) {
        tablero = new Tablero(filas, columnas);
        jugadorActual = 1;
        puntosJugadorUno = 0;
        puntosJugadorDos = 0;
    }

    public void inicializar() {
        tablero.llenarTableroConValoresAleatorios();
    }

    public void manejarClickCelda(int fila, int columna) {
        if (tablero.revelarCelda(fila, columna)) {
            if (tablero.esParejaEncontrada()) {
                sumarPuntoAJugadorActual();
            } else {
                cambiarTurno();
            }
        }
    }

    private void sumarPuntoAJugadorActual() {
        if (jugadorActual == 1) {
            puntosJugadorUno++;
        } else {
            puntosJugadorDos++;
        }
    }

    private void cambiarTurno() {
        jugadorActual = (jugadorActual == 1) ? 2 : 1;
    }

    public Tablero getTablero() {
        return tablero;
    }

    public int getJugadorActual() {
        return jugadorActual;
    }

    public int getPuntosJugadorUno() {
        return puntosJugadorUno;
    }

    public int getPuntosJugadorDos() {
        return puntosJugadorDos;
    }
}