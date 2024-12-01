package modelo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {
    private Celda[][] celdas;
    private int filas;
    private int columnas;
    private Celda primeraSeleccionada;

    public Tablero(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        celdas = new Celda[filas][columnas];
    }

    public void llenarTableroConValoresAleatorios() {
        List<String> valores = generarValoresAleatorios();
        int index = 0;

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                celdas[fila][columna] = new Celda(valores.get(index));
                index++;
            }
        }
    }

    private List<String> generarValoresAleatorios() {
        List<String> valores = new ArrayList<>();
        int cantidadParejas = (filas * columnas) / 2;

        for (int i = 1; i <= cantidadParejas; i++) {
            valores.add(String.valueOf(i));
            valores.add(String.valueOf(i));
        }

        Collections.shuffle(valores);
        return valores;
    }

    public boolean revelarCelda(int fila, int columna) {
        Celda celda = celdas[fila][columna];
        if (celda.estaRevelada()) {
            return false;
        }

        celda.revelar();

        if (primeraSeleccionada == null) {
            primeraSeleccionada = celda;
        } else {
            if (!primeraSeleccionada.esIgual(celda)) {
                primeraSeleccionada.ocultar();
                celda.ocultar();
            }
            primeraSeleccionada = null;
        }

        return true;
    }

    public boolean esParejaEncontrada() {
        return primeraSeleccionada == null;
    }

    public Celda getCelda(int fila, int columna) {
        return celdas[fila][columna];
    }
}
