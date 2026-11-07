package aed.tp2.props.estructuras;
import aed.tp2.props.interfaces.Diccionario;

public class DiccionarioTrie<V> implements Diccionario<String, V>{

    private DiccionarioTrie<V>[] abcTries;
    private V valor;

    public DiccionarioTrie(){
            this.abcTries = (DiccionarioTrie<V>[]) new DiccionarioTrie[27*2];    // almacenamos mayusculas y minusculas
            this.valor = null;
        }

    public void insertar(String clave, V valor) {
        this.toAbcTries(clave, 0).setValor(valor);
    }

    public V obtener(String clave) {
        return toAbcTries(clave, 0).getValor();
    }

    private DiccionarioTrie<V> toAbcTries(String str, int _i) {
        if (str.length() == _i)
            return this;

        int idx = str.charAt(_i) - 'A';

        return this.abcTries[idx] != null
                ? (this.abcTries[idx]).toAbcTries(str, _i + 1)
                : (this.abcTries[idx] = new DiccionarioTrie<V>()).toAbcTries(str, _i + 1);
    }

    public void setValor(V valor) {
        this.valor = valor;
    }

    public V getValor() {
        return valor;
    }

}
