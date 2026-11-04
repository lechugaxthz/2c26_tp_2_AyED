package aed.tp2.props.estructuras;

public class Nodo<K extends Comparable<K>, V> {
        
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

        public Nodo getNodoIzquierdo() {
            return nodoIzquierdo;
        }

        public Nodo getNodoDerecho() {
            return nodoDerecho;
        }
         
    public int compareTo(Nodo<K,V> otro) {
        if (otro == null) {
            throw new IllegalArgumentException("No podemos comparar con null");
        }
        return this.key.compareTo(otro.key);
    }
}
