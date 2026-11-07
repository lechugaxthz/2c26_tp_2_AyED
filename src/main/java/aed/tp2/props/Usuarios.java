package aed.tp2.props;

import java.util.ArrayList;
import java.util.Comparator;

import aed.tp2.props.comparable.UsuarioComparable;
import aed.tp2.props.enums.Categoria;
import aed.tp2.props.enums.Transaccion;
import aed.tp2.props.estructuras.DiccionarioABB;
import aed.tp2.props.estructuras.Heap;
import aed.tp2.props.handlers.HeapUsuarioHandler;
import aed.tp2.props.records.Contraparte;
import aed.tp2.props.records.HistorialUsuario;
import aed.tp2.props.sub_class.UsuarioValor;

public class Usuarios {

    private DiccionarioABB<Integer, UsuarioValor> abbUsuarioPorID;
    private Heap<Double, UsuarioValor> heapUsuarioMaxMillas;
    private UsuarioValor usuarioMaxPromos;
    private UsuarioValor usuarioMaxMillasAcumuladas;

    private Comparator<UsuarioValor> cmpPromos;
    private Comparator<UsuarioValor> cmpMillasAcumuladas;

    public Usuarios() {
        this.abbUsuarioPorID = new DiccionarioABB<>();
        this.heapUsuarioMaxMillas = new Heap<>();
        this.usuarioMaxPromos = null;
        this.usuarioMaxMillasAcumuladas = null;
        this.cmpPromos = new UsuarioComparable.UsuarioCmpPromosCanjeadas();
        this.cmpMillasAcumuladas = new UsuarioComparable.UsuarioCmpMillasAcumuladas();
    }

    public UsuarioValor agregarUsuario(int id) {

        UsuarioValor usr = new UsuarioValor(
                id,
                new ArrayList<>(),
                0d,
                0d,
                0d,
                null,
                true);
        this.abbUsuarioPorID.insertar(id, usr);

        this.heapUsuarioMaxMillas.agregar(
                new HeapUsuarioHandler(usr));

        // los iniciamos asi ya no tenemos porque validarlos despues al agregar millas
        if (this.usuarioMaxMillasAcumuladas == null)
            this.usuarioMaxMillasAcumuladas = usr;
        if (this.usuarioMaxPromos == null)
            this.usuarioMaxPromos = usr;

        return usr;
    }

    public void agregarConReferido(int idNew, int idRef) {
        UsuarioValor usrNew = agregarUsuario(idNew);
        UsuarioValor usrRef = abbUsuarioPorID.obtener(idRef);

        HistorialUsuario hUsrNew = new HistorialUsuario(
                Transaccion.REFERIDO,
                0d,
                500d,
                null,
                1d,
                new Contraparte(
                        usrRef,
                        usrRef.getHistorialLength()));

        HistorialUsuario hUsrRef = new HistorialUsuario(
                Transaccion.REFERIDO,
                0d,
                500d,
                null,
                1d,
                new Contraparte(
                        usrNew,
                        0));

        usrNew.addNewHistorialUsuario(hUsrNew);
        usrRef.addNewHistorialUsuario(hUsrRef);

        heapUsuarioMaxMillas.onUpdate(usrNew.getHandler());
        heapUsuarioMaxMillas.onUpdate(usrRef.getHandler());
    }

    public double agregarVuelo(int id, double distancia, double promo_factor, String promo_nombre) {

        UsuarioValor usr = abbUsuarioPorID.obtener(id);

        double factor_usuario = usr.getCategoria().getValor();
        double factor_transaccion = factor_usuario * promo_factor;
        double millas_adquiridas = distancia * factor_transaccion;

        HistorialUsuario hUsr = new HistorialUsuario(
                Transaccion.VUELO,
                distancia,
                millas_adquiridas,
                promo_nombre,
                promo_factor,
                null);

        usr.addNewHistorialUsuario(hUsr);

        heapUsuarioMaxMillas.onUpdate(usr.getHandler());

        if (this.cmpMillasAcumuladas.compare(usr, this.usuarioMaxMillasAcumuladas) > 0) {
            this.usuarioMaxMillasAcumuladas = usr;
        }

        return hUsr.millas_adquiridas();
    }

    public void canjeaMillas(int id, double cant) {

        UsuarioValor usr = abbUsuarioPorID.obtener(id);

        HistorialUsuario hUsr = new HistorialUsuario(
                Transaccion.PROMO,
                0d,
                cant,
                null,
                1d,
                null);

        usr.addNewHistorialUsuario(hUsr);

        heapUsuarioMaxMillas.onUpdate(usr.getHandler());

        if (this.cmpPromos.compare(usr, this.usuarioMaxPromos) > 0) {
            this.usuarioMaxMillasAcumuladas = usr;
        }
    }

    public void transferir(int usrOg, int usrDest, double monto) {

        UsuarioValor usrOrigen = abbUsuarioPorID.obtener(usrOg);
        UsuarioValor usrDestino = abbUsuarioPorID.obtener(usrDest);

        HistorialUsuario hUsrOg = new HistorialUsuario(
                Transaccion.EMISION,
                0d,
                monto,
                null,
                1d,
                new Contraparte(usrDestino, usrDestino.getHistorialLength()));

        HistorialUsuario hUsrDest = new HistorialUsuario(
                Transaccion.RECEPCION,
                0d,
                monto,
                null,
                1d,
                new Contraparte(usrOrigen, usrOrigen.getHistorialLength()));

        usrOrigen.addNewHistorialUsuario(hUsrOg);
        usrDestino.addNewHistorialUsuario(hUsrDest);
    }

    public double getMillasDisponibles(int id) {
        return abbUsuarioPorID.obtener(id).getMillasDisponibles();
    }

    public String getCategoria(int id) {
        return abbUsuarioPorID.obtener(id).getCategoria().name();
    }

    public int[] rankMillasDisponibles(int n) {
        n = n >= heapUsuarioMaxMillas.longitud()
                ? n
                : heapUsuarioMaxMillas.longitud();
        int[] rank = new int[n];

        for (int i = 0; i < rank.length; i++) {
            rank[i] = heapUsuarioMaxMillas.obtener(i).getVal().getId();
        }

        return rank;
    }

    public int usuarioMaxAcumulaciones(){
        return usuarioMaxMillasAcumuladas.getId();
    }

    public int usuarioMaxPromos(){
        return usuarioMaxPromos.getId();
    }

    public int[] paresExclusivos(){
        ArrayList<ArrayList<Integer>> pares = new ArrayList<>();;

        return pares.toArray();
    }
}
