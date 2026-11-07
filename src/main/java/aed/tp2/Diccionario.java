package aed.tp2;

public interface Diccionario<K, V> {
     /**
     * Agrega una clave y valor al diccionario
     * 
     */
    public void insertar(K clave, V valor);
    /**
     * Devuelve el valor del diccionario correspondiente a la clave
     *
     */
    public V obtener(K clave);


}
