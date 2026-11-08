package aed.tp2.props.estructuras;

import aed.tp2.Iterador;
import aed.tp2.props.interfaces.HandleLista;

public class ListaEnlazada<K extends  Comparable<K> ,V> {

    private Nodo<K,V> primero;
    private Nodo<K,V> ultimo; 
    private int cantidad;

    public ListaEnlazada(){
        this.primero = null;
        this.ultimo = null;
        this.cantidad = 0;
    }


    // insertar
    public HandleLista<K,V> agregarAtras(K clave, V valor) {
        Nodo<K,V> nuevo = new Nodo<>(clave, valor);
        if (this.primero == null) {
            this.primero = nuevo;
        } else {
            nuevo.setNodoIzquierdo(this.ultimo);
            this.ultimo.setNodoDerecho(nuevo);
        }
        this.ultimo = nuevo;   // en los dos casos el nuevo pasa a ser el último
        this.cantidad++;
        return new HandleLE(nuevo);
    }
    // obtener
    public Nodo<K,V> obtener(K clave){
        if (this.cantidad == 0) {
            return null;
        }
        Nodo<K,V> actual = this.primero;
        Nodo<K,V> target = null;
        while (actual != null){
            if (actual.getKey().compareTo(clave) == 0){
                target = actual;
                break;
            }
            actual = actual.getNodoDerecho();
        }
        return target;

    }
    // eliminar
    public void eliminarNodo(Nodo<K,V> nodo){

        Nodo<K,V> ant = nodo.getNodoIzquierdo();
        Nodo<K,V> sig = nodo.getNodoDerecho();


        if (ant != null){
            ant.setNodoDerecho(sig);
        }else{
            this.primero = sig; // nodo era el primero
        }

        if(sig != null){
            sig.setNodoIzquierdo(ant);
        }else{
            this.ultimo = ant;
        }
        nodo.setNodoIzquierdo(null);
        nodo.setNodoDerecho(null);
        this.cantidad --;

    }

    public void eliminar (K clave) {
        Nodo<K,V> n = obtener(clave);
        if (n != null){
            eliminarNodo(n);
        }
    }

private class HandleLE implements HandleLista<K,V> {
    private Nodo<K,V> nodo;

    private HandleLE(Nodo<K,V> nodo) {
        this.nodo = nodo;
    }

    @Override
    public K getKey() {
        verificarVigente();
        return this.nodo.getKey();
    }

    @Override
    public V getVal() {
        verificarVigente();
        return this.nodo.getValue();
    }

    @Override
    public void eliminar() {
        verificarVigente();
        ListaEnlazada.this.eliminarNodo(this.nodo);
        this.nodo = null;   // evita eliminar dos veces
    }

    private void verificarVigente() {
        if (this.nodo == null) {
            throw new IllegalStateException("El elemento ya fue eliminado");
        }
    }
    }

    public int longitud(){
        return this.cantidad;
    }

    public class ListaIterador implements Iterador<V>{
        private Nodo<K,V> actual = primero;
        private int indiceProx = 0;

        public boolean haySiguiente() {
            return indiceProx < cantidad;
        }
        
        public boolean hayAnterior(){
            return indiceProx > 0;
        }

        public V siguiente() {
            V data = actual.getValue();
            actual = actual.getNodoDerecho();
            indiceProx++;
            return data;
        }

        public V anterior(){
            if (actual == null) {
                actual = ultimo;
            } else {
                actual = actual.getNodoIzquierdo();
            }
            indiceProx--;
            return actual.getValue();
        }
    }

    public Iterador<V> iterador() {
        return new ListaIterador();
    }


}
