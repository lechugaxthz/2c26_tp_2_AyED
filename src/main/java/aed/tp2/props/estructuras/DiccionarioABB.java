package aed.tp2.props.estructuras;
import aed.tp2.Diccionario;

public class DiccionarioABB<K extends Comparable<K>, V> implements Diccionario<K, V> {

    private Nodo<K, V> root;

    public DiccionarioABB() {
        this.root = null;
    }

    public void insertar(K clave, V valor) {
        insertarNodo(clave, valor);
    }

    private Nodo<K, V> insertarNodo(K key, V valor) {

        Nodo<K, V> nuevoNodo = new Nodo<K,V>(key, valor);

        if (root == null) {
            this.root = nuevoNodo;
            return nuevoNodo;
        }

        Nodo<K, V> act = obtenerNodo(key);
        int cmp = act.getKey().compareTo(key);

        if (cmp >= 0) {
            act.setNodoDerecho(nuevoNodo);
        } else {
            act.setNodoIzquierdo(nuevoNodo);
        }

        return nuevoNodo;
    }

    public V obtener(K key) {
        Nodo<K, V> nodo = this.obtenerNodo(key);
        return nodo.getValue();
    }

    public Nodo<K, V> obtenerNodo(K key) {
        Nodo<K, V> prev = this.root;
        Nodo<K, V> act = this.root;
        int cmp = act.getKey().compareTo(key);

        while (act != null) {
            prev = act;
            
            act = (cmp >= 0) 
            ? act.getNodoDerecho()
            : act.getNodoIzquierdo();
            
            cmp = act.getKey().compareTo(key);
        }

        act = (act != null) 
        ? act 
        : prev;

        return act;
    }
}
