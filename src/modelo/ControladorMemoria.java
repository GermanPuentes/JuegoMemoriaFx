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

    public boolean manejarClickCelda(int fila, int columna) {
        return tablero.revelarCelda(fila, columna);
    }

    public boolean esParejaEncontrada() {
        return tablero.esParejaEncontrada();
    }

    public void sumarPuntoAJugadorActual() {
        if (jugadorActual == 1) {
            puntosJugadorUno++;
        } else {
            puntosJugadorDos++;
        }
    }

    public void cambiarTurno() {
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

    public boolean terminoJuego() {
        return tablero.quedanParejasPorEncontrar() == 0;
    }
}
