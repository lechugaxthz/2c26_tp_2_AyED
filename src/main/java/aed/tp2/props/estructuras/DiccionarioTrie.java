package aed.tp2.props.estructuras;

import aed.tp2.props.sub_class.PromoValor;

public class DiccionarioTrie<T> {

    private DiccionarioTrie<T>[] abcTries;
    private T valor;

    public DiccionarioTrie(){
            this.abcTries = new DiccionarioTrie[27*2];    // almacenamos mayusculas y minusculas
            this.valor = null;
        }
    
    // Devuelve el 
    public DiccionarioTrie<T> toAbcTries(String str, int _i) {
        if (str.length() < _i)
            return this;

        int idx = str.charAt(_i) - 'A';

        return this.abcTries[idx] != null
                ? (this.abcTries[idx]).toAbcTries(str, _i++)
                : (this.abcTries[idx] = new DiccionarioTrie<T>()).toAbcTries(str, _i++);
    }

    public void setValor(T valor) {
        this.valor = valor;
    }

    public T getValor() {
        return valor;
    }

}
