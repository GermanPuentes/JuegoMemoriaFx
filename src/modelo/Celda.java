package modelo;

public class Celda {
    private String valor;  // Puede ser un número o un nombre en inglés
    private boolean revelada;
    private boolean deshabilitada; // Nueva propiedad para indicar si está deshabilitada

    public Celda(String valor) {
        this.valor = valor;
        this.revelada = false;
        this.deshabilitada = false; // Inicialmente no está deshabilitada
    }

    // Obtiene el valor de la celda
    public String getValor() {
        return valor;
    }

    // Verifica si la celda está revelada
    public boolean estaRevelada() {
        return revelada;
    }

    // Revela la celda
    public void revelar() {
        revelada = true;
    }

    // Oculta la celda
    public void ocultar() {
        revelada = false;
    }

    // Verifica si la celda está deshabilitada
    public boolean estaDeshabilitada() {
        return deshabilitada;
    }

    // Deshabilita la celda (cuando pertenece a una pareja encontrada)
    public void deshabilitar() {
        deshabilitada = true;
    }

    // Verifica si dos celdas son pareja
    public boolean esPareja(Celda otra) {
        if (this.valor == null || otra.valor == null) {
            return false;
        }

        // Determinar si esta celda es un número y la otra es un nombre o viceversa
        if (esNumero(this.valor) && !esNumero(otra.valor)) {
            return obtenerNombreEnIngles().equalsIgnoreCase(otra.valor);
        } else if (!esNumero(this.valor) && esNumero(otra.valor)) {
            return this.valor.equalsIgnoreCase(otra.obtenerNombreEnIngles());
        }

        // Si ambas son números o ambas son nombres, no son pareja
        return false;
    }

    // Obtiene el nombre en inglés del número representado por la celda
    private String obtenerNombreEnIngles() {
        switch (valor) {
            case "1": return "One";
            case "2": return "Two";
            case "3": return "Three";
            case "4": return "Four";
            case "5": return "Five";
            case "6": return "Six";
            case "7": return "Seven";
            case "8": return "Eight";
            case "9": return "Nine";
            case "10": return "Ten";
            default: return null;
        }
    }

    // Verifica si el valor de la celda es un número
    private boolean esNumero(String valor) {
        try {
            Integer.parseInt(valor);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
