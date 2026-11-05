package aed.tp2.props.estructuras;

import java.util.ArrayList;

import aed.tp2.props.sub_class.UsuarioValor;

public class Heap<T> {

    private ArrayList<T> heapArrayList; // Disponibles

    public void Heap() {
        this.heapArrayList = new ArrayList<>();
    }

    public void agregarNuevo(T elem) {
        this.heapArrayList.add(elem);
    }

    public T obtener(int idx) {
        return this.heapArrayList.get(idx);
    }




    // obtener i-esimo
    public int[] ranking() {
        // Retornamos el top n ranking de usarios ids ordenados por millas disponibles
        return new int[0];
    }

    public boolean estaVacio() {
        return true;
    }

    public int longitud() {
        return 0;
    }

}
