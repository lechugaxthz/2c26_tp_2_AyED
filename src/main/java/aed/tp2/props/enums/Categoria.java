package aed.tp2.props.enums;

public enum Categoria {
    Bronce(1d),
    Plata(1.5d),
    Oro(2d),
    Platino(0d);

    private final double valor;

    Categoria(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }
}
