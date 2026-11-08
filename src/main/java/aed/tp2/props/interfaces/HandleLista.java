package aed.tp2.props.interfaces;

public interface HandleLista<K extends Comparable<K> , V> {
    K getKey();
    V getVal();
    void eliminar();
}
