package aed.tp2.props.estructuras;
import java.util.ArrayList;

import aed.tp2.props.UsuarioValor;

public class Heap<T> {

    private ArrayList<T> heapArrayList; // Disponibles
    
    private void intercambiar( Nodo i, Nodo j){

    }

    private void subirNodo( Nodo i){

    }

    private void bajarNodo( Nodo i){

    }

    public void Heap(){
        this.heapArrayList = new ArrayList<>();
    }

    public T insertar(T elem){

    }

    public T sacar(T elem){

    }

    // 
    public T proximo(T elem){

    }

    // Puede modificar la prioridad
    public T actualizar(T elem){
        // Se debe implementar el cambio de millas de un usuario
        // usar el idx para ubicarlo
    }

    // obtener i-esimo
    public int[] ranking (){
        // Retornamos el top n ranking de usarios ids ordenados por millas disponibles
        return new int[0];
    }

    public boolean estaVacio(){
        return true;
    }

    public int longitud(){
        return 0;
    }
    
}
