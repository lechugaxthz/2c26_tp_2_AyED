package aed.tp2.props.sub_class;

import java.util.ArrayList;

import aed.tp2.props.enums.Categoria;
import aed.tp2.props.handlers.HeapUsuarioHandler;
import aed.tp2.props.records.HistorialUsuario;

public class UsuarioValor {

    private int id;
    private ArrayList<HistorialUsuario> historial;
    private Double millas_disponibles;
    private Double millas_acumuladas;
    private Double millas_canjeadas;
    private UsuarioValor par_exclusivo;
    private boolean exclusion_valida;
    private Categoria categoria;
    /* handler */
    private HeapUsuarioHandler hUsuario;

    public UsuarioValor(
            int id,
            Double millas_d,
            Double millas_a,
            Double millas_c,
            UsuarioValor par_exc,
            boolean exc_val) {
        this.id = id;
        this.historial = new ArrayList<>();
        this.millas_disponibles = millas_d;
        this.millas_acumuladas = millas_a;
        this.millas_canjeadas = millas_c;
        this.par_exclusivo = par_exc;
        this.exclusion_valida = exc_val;
        this.categoria = (millas_acumuladas > 1000000d)
                ? Categoria.Platino
                : (millas_acumuladas > 5000d)
                        ? Categoria.Oro
                        : (millas_acumuladas > 2000d)
                                ? Categoria.Plata
                                : Categoria.Bronce;
        this.hUsuario = new HeapUsuarioHandler(this);
    }

    public int getId() {
        return id;
    }

    public Double getMillasDisponibles() {
        return this.millas_disponibles;
    }

    public Double getMillasAcumuladas() {
        return this.millas_acumuladas;
    }

    public Categoria getCategoria() {
        return this.categoria;
    }

    public Double getMillasCanjeadas() {
        return this.millas_canjeadas;
    }

    public UsuarioValor getParExclusivo() {
        return this.par_exclusivo;
    }

    public boolean getExclusividad() {
        return this.exclusion_valida;
    }

    private void setMillasDisponibles(Double valor) {
        this.millas_disponibles += valor;
    }

    private void setMillasAcumuladas(Double valor) {
        this.millas_acumuladas += valor;
    }

    private void setMillasCanjeadas(Double valor) {
        this.millas_canjeadas += valor;
    }

    private void validaParExclusivo() {
        UsuarioValor otro = this.historial.get(getHistorialLength() - 1).contraparte().usuario();
        if (exclusion_valida && (par_exclusivo == otro || par_exclusivo == null)) {
            par_exclusivo = otro;
        } else {
            if (par_exclusivo != null) {
                par_exclusivo.par_exclusivo = null;
                par_exclusivo.exclusion_valida = false;
                par_exclusivo = null;
                exclusion_valida = false;
            }
        }
    }

    private void setExclusividad(boolean valor) {
        this.exclusion_valida = valor;
    }

    public void addNewHistorialUsuario(HistorialUsuario h) {
        this.historial.add(h);

        switch (h.transaccion()) {
            case EMISION:
                setMillasDisponibles(-h.millas_adquiridas());
                setMillasCanjeadas(h.millas_adquiridas());
                validaParExclusivo();
                break;
            case PROMO:
                setMillasCanjeadas(h.millas_adquiridas());
                setMillasDisponibles(-h.millas_adquiridas());
                break;
            case RECEPCION:
                setMillasDisponibles(h.millas_adquiridas());
                validaParExclusivo();
                break;
            case REFERIDO:
                setMillasDisponibles(h.millas_adquiridas());
                setMillasAcumuladas(h.millas_adquiridas());
                break;
            default: // VUELO
                setMillasDisponibles(h.millas_adquiridas());
                setMillasAcumuladas(h.millas_adquiridas());
                break;
        }

    }

    public int getHistorialLength() {
        return this.historial.size();
    }

    public HeapUsuarioHandler getHandler() {
        return this.hUsuario;
    }

}
