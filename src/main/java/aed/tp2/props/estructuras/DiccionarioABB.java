package aed.tp2.props.estructuras;

import java.util.ArrayList;

import aed.tp2.props.records.UsuarioValor;

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

    public Nodo insertar(K key) {
        UsuarioValor usr = new UsuarioValor(
                new ArrayList<>(),
                0,
                0,
                0,
                null,
                true,
                0);

        Nodo nuevoUsuario = new Nodo(key, (V) usr);

        if (root == null) {
            this.root = nuevoUsuario;
            return nuevoUsuario;
        }

        Nodo prev = this.root;
        Nodo act = this.root;
        int cmp = 0;

        while (act != null) {
            cmp = act.key.compareTo(nuevoUsuario.key);
            prev = act;

            if (cmp >= 0) {
                act = act.getNodoDerecho();
                continue;
            }
            act = act.getNodoIzquierdo();
        }

        if (cmp >= 0) {
            prev.setNodoDerecho(nuevoUsuario);
        } else {
            prev.setNodoIzquierdo(nuevoUsuario);
        }

        return nuevoUsuario;
    }

}
