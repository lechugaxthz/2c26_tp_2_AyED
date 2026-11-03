package aed.tp2.props;

import java.util.ArrayList;

import aed.tp2.props.estructuras.DiccionarioABB;
import aed.tp2.props.estructuras.Heap;
import aed.tp2.props.records.UsuarioValor;

public class Usuarios {

    private DiccionarioABB abbUsuarioPorID;
    
    private Heap heapUsuarioMaxMillas;

    private UsuarioValor usuarioMaxPromos;

    private UsuarioValor usuarioMaxMillasDisponibles;


    public Usuarios() {
        this.abbUsuarioPorID = new DiccionarioABB();
        this.heapUsuarioMaxMillas = new Heap();
        this.usuarioMaxPromos = null; 
        this.usuarioMaxMillasDisponibles = null;
    }


}
