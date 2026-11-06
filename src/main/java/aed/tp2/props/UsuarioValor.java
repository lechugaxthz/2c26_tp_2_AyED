package aed.tp2.props;

import java.util.ArrayList;

import aed.tp2.props.enums.Categoria;
import aed.tp2.props.records.HistorialUsuario;

public class UsuarioValor{

    private ArrayList<HistorialUsuario> historial; 
    private float millas_disponibles;
    private float millas_acumuladas; 
    private float millas_canjeadas;
    private UsuarioValor par_exclusivo; 
    private boolean exclusion_valida;
    private int idx;
    private Categoria categoria;


    public UsuarioValor(ArrayList<HistorialUsuario> h,
                        float millas_d,
                        float millas_a,
                        float millas_c,
                        UsuarioValor par_exc,
                        boolean exc_val,
                        int idx,
                        Categoria cat){

                            this.historial = h;
                            this.millas_disponibles = millas_d;
                            this.millas_acumuladas = millas_a;
                            this.millas_canjeadas = millas_c;
                            this.par_exclusivo = par_exc;
                            this.exclusion_valida = exc_val;
                            this.idx = idx;
                            this.categoria = cat;
                        }
    

    public float getMillasDisponibles(){
        return this.millas_disponibles;
    }

    public float getMillasAcumuladas(){
        return this.millas_acumuladas;
    }

    public Categoria getCategoria(){
        return this.categoria;
    }

    public int compareTo(UsuarioValor otro){
        if (otro == null){
            String msj = "No podemos comparar con null";
            throw new IllegalArgumentException(msj);
        }
        return  Float.compare(otro.millas_disponibles, this.millas_disponibles);
        }

}
