package modelo;

public class Celda {
    private String valor;
    private boolean revelada;

    public Celda(String valor) {
        this.valor = valor;
        this.revelada = false;
    }

    public String getValor() {
        return valor;
    }

    public boolean estaRevelada() {
        return revelada;
    }

    public void revelar() {
        revelada = true;
    }

    public void ocultar() {
        revelada = false;
    }

    public boolean esIgual(Celda otraCelda) {
        return valor.equals(otraCelda.getValor());
    }
}