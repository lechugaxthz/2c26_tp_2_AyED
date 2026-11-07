package aed.tp2.props.sub_class;

public class PromoValor {
    public double factor;
    public double tope;
    public boolean tiene_tope;
    public boolean activa;

    public PromoValor(
            double factor,
            double tope,
            boolean tiene_tope,
            boolean activa) {
        this.factor = factor;
        this.tope = tope;
        this.tiene_tope = tiene_tope;
        this.activa = activa;
    }
}
