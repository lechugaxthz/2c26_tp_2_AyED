package aed.tp2;


public class AirMiles implements SistemaAirMiles {
    /*COMPLETAR*/
    
    /**
     * podemos implementar 
     *      * Usuarios
     *          * PriorityQueue -> porMillasAcumuladas
     *          * PriorityQueue -> porMillasCanjeadas
     *          * handler (AVL) -> porIds (handlers de los usuarios)
     *      
     *      * Promos
     *          * Tries
     * 
     * las implementaciones de los mismos iremos viendo si podemos o no usar ciertos tipos de datos
     * provistos por Java, hasta el momento parece ser que no, pero deje consulta en el Foro para
     * que nos confirmen si se puede implementar en alguna clase que hacemos
     * 
     * lo principal sera enfocarnos en las estructuras, hasta no tenerlas definidas, no nos
     * vale empezar con las funciones!
     */

    
    /**
     * 
     */
    public AirMiles() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void registrarUsuario(int id) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void registrarUsuarioConReferidor(int id, int ref) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void acumularMillas(int id, double distancia, String nombrePromo) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void canjearMillas(int id, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void transferirMillas(int origen, int destino, double cantidad) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public double consultarSaldo(int id) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public String consultarCategoria(int id) {
       throw new UnsupportedOperationException("Sin implementar");
    }

    public int[] rankingMillas(int n) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocion(String nombre, double factor) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void iniciarPromocionConTope(String nombre, double factor, double tope) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public void finalizarPromocion(String nombre) {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasAcumulador() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int elMasCanjeador() {
        throw new UnsupportedOperationException("Sin implementar");
    }

    public int[][] paresTransferidoresExclusivos() {
        throw new UnsupportedOperationException("Sin implementar");
    }
}
