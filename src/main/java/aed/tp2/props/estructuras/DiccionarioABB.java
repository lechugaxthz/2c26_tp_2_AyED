package aed.tp2.props.estructuras;

public class DiccionarioABB<K extends Comparable<K>, V> {

    private Nodo root;

    private class Nodo {
        private K key;
        private V value;
        private Nodo nodoIzquierdo;
        private Nodo nodoDerecho;

        public Nodo(K key, V val) {
            this.key = key;
            this.value = val;
            this.nodoIzquierdo = null;
            this.nodoDerecho = null;
        }

        public void setNodoIzquierdo(Nodo nodoIzquierdo) {
            this.nodoIzquierdo = nodoIzquierdo;
        }

        public void setNodoDerecho(Nodo nodoDerecho) {
            this.nodoDerecho = nodoDerecho;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
        
        public void setValue(V value) {
            this.value = value;
        }

        public Nodo getNodoIzquierdo() {
            return nodoIzquierdo;
        }

        public Nodo getNodoDerecho() {
            return nodoDerecho;
        }
    }

    public DiccionarioABB() {
        this.root = null;
    }

    public Nodo insertar(K key, V valor) {
        
        Nodo nuevoUsuario = new Nodo(key, valor);

        if (root == null) {
            this.root = nuevoUsuario;
            return nuevoUsuario;
        }

        Nodo act = obtener(key);
        int cmp = act.key.compareTo(key);

        if (cmp >= 0) {
            act.setNodoDerecho(nuevoUsuario);
        } else {
            act.setNodoIzquierdo(nuevoUsuario);
        }

        return nuevoUsuario;
    }

    public Nodo obtener(K key) {
        Nodo prev = this.root;
        Nodo act = this.root;
        int cmp = act.key.compareTo(key);

        while (act != null) {
            prev = act;
            
            act = (cmp >= 0) 
            ? act.getNodoDerecho()
            : act.getNodoIzquierdo();
            
            cmp = act.key.compareTo(key);
        }

        act = (act != null) 
        ? act 
        : prev;

        return act;
    }
}
