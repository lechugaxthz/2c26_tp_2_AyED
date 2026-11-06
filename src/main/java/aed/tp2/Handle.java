package aed.tp2;

public interface Handle<K extends Comparable<K>, T>{    
    /**
     * Devuelve el indice
     * 
     */
    K getKey();
    
    
    /**
     * 
     */
    int getIdx();

    /**
     * setea el index en el heap
     * @param idx
     */
        void setIdx(int idx);

    /**
     * 
     */
    T getVal();
}
