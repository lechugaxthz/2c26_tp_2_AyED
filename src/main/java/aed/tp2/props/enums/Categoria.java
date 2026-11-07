package aed.tp2.props.enums;

public enum Categoria {
    BRONCE(1d),
    PLATA(1.5d),
    ORO(2d),
    PLATINO(0d);

    private final double valor;

    Categoria(double valor){
        this.valor = valor;
    }

    public double getValor(){
        return this.valor;
    }
}
