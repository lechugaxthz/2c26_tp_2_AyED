package aed.tp2.props.estructuras;
import java.util.ArrayList;

import aed.tp2.props.UsuarioValor;

public class Heap<T> {

    private ArrayList<T> heapArrayList; // Disponibles
    
    private void intercambiar( int i, int j ){
        this.heapArrayList.set(i, this.heapArrayList.get(j) );
        this.heapArrayList.set(j, this.heapArrayList.get(i));
    }

    private void subir(int i){
        
    }

    private void bajar(T i){

    }

    public void Heap(){
        this.heapArrayList = new ArrayList<T>();
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
