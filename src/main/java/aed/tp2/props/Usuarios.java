package aed.tp2.props;

import java.util.ArrayList;

import aed.tp2.props.enums.Categoria;
import aed.tp2.props.estructuras.DiccionarioABB;
import aed.tp2.props.estructuras.Heap;
import aed.tp2.props.records.UsuarioValor;

public class Usuarios {

    private DiccionarioABB<Integer, UsuarioValor> abbUsuarioPorID;
    
    private Heap heapUsuarioMaxMillas;

    private UsuarioValor usuarioMaxPromos;

    private UsuarioValor usuarioMaxMillasDisponibles;


    public Usuarios() {
        this.abbUsuarioPorID = new DiccionarioABB<>();
        this.heapUsuarioMaxMillas = new Heap();
        this.usuarioMaxPromos = null; 
        this.usuarioMaxMillasDisponibles = null;
    }

    
    public void agregarUsuario(int id){
        
        UsuarioValor usr = new UsuarioValor(
            new ArrayList<>(), 
            0, 
            0, 
            0, 
            null, 
            true, 
            0, 
            Categoria.BRONCE
        );
        this.abbUsuarioPorID.insertar(id, usr);

        // agregar metodo para insertar en el Heap!

    }
    



}
