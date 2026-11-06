package aed.tp2.props.comparable;

import java.util.Comparator;

import aed.tp2.props.UsuarioValor;

public class UsuarioComparable {
    public class UsuarioCmpMillasDisponible implements Comparator<UsuarioValor>{
            
        @Override 
            public int compare(UsuarioValor a , UsuarioValor b){
                return Float.compare(a.getMillasDisponibles(), b.getMillasDisponibles());
            }

    }

public class UsuarioCmpMillasAcumuladas implements Comparator<UsuarioValor>{
            
        @Override 
            public int compare(UsuarioValor a , UsuarioValor b){
                return Float.compare(a.getMillasAcumuladas(), b.getMillasAcumuladas());
            }

    }
    





}
