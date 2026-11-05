package aed.tp2.props.estructuras;

import aed.tp2.props.sub_class.PromoValor;

public class DiccionarioTrie {

    private DiccionarioTrie[] abcTries;

    private PromoValor valor;

    public DiccionarioTrie(){
            this.abcTries = new DiccionarioTrie[27*2];    // almacenamos mayusculas y minusculas
            this.valor = null;
        }

    public DiccionarioTrie toAbcTries(String str, int _i) {
        if (str.length() < _i)
            return this;

        int idx = str.charAt(_i) - 'A';

        return this.abcTries[idx] != null
                ? (this.abcTries[idx]).toAbcTries(str, _i++)
                : (this.abcTries[idx] = new DiccionarioTrie()).toAbcTries(str, _i++);
    }

    public void setValor(PromoValor valor) {
        this.valor = valor;
    }

    public PromoValor getValor() {
        return valor;
    }

}
