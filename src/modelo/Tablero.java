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
        List<Celda> parejas = generarParejasAleatorias();
        int index = 0;

        for (int fila = 0; fila < filas; fila++) {
            for (int columna = 0; columna < columnas; columna++) {
                celdas[fila][columna] = parejas.get(index);
                index++;
            }
        }
    }

    private List<Celda> generarParejasAleatorias() {
        List<Celda> parejas = new ArrayList<>();
        int cantidadParejas = (filas * columnas) / 2;
        String[] nombresEnIngles = {"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten"};

        for (int i = 1; i <= cantidadParejas; i++) {
            String valor = String.valueOf(i);
            String nombre = nombresEnIngles[i - 1];

            // Crear celdas que contienen números o nombres directamente
            parejas.add(new Celda(valor)); // Celda con número
            parejas.add(new Celda(nombre)); // Celda con nombre en inglés
        }

        Collections.shuffle(parejas); // Mezclar celdas para que se coloquen aleatoriamente
        return parejas;
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
            if (!primeraSeleccionada.esPareja(celda)) {
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

    public int quedanParejasPorEncontrar() {
        int parejasRestantes = 0;
        for (Celda[] fila : celdas) {
            for (Celda celda : fila) {
                if (!celda.estaRevelada() && !celda.estaDeshabilitada()) {
                    parejasRestantes++;
                }
            }
        }
        return parejasRestantes / 2;
    }
}