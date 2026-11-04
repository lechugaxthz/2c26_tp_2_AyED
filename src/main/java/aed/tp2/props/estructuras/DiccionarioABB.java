package aed.tp2.props.estructuras;

public class DiccionarioABB<K extends Comparable<K>, V> {

    private Nodo root;

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
        int cmp = act.getKey().compareTo(key);

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
        int cmp = act.getKey().compareTo(key);

        while (act != null) {
            prev = act;
            
            act = (cmp >= 0) 
            ? act.getNodoDerecho()
            : act.getNodoIzquierdo();
            
            cmp = act.getKey().compareTo(key);
        }

        act = (act != null) 
        ? act 
        : prev;

        return act;
    }
}
