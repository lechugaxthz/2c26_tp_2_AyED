package aed.tp2.props.comparable;

import java.util.Comparator;

import aed.tp2.props.sub_class.UsuarioValor;

public class UsuarioComparable {

    public static class UsuarioCmpMillasDisponible implements Comparator<UsuarioValor> {
        @Override
        public int compare(UsuarioValor a, UsuarioValor b) {
            return Double.compare(a.getMillasDisponibles(), b.getMillasDisponibles());
        }
    }

    public static class UsuarioCmpMillasAcumuladas implements Comparator<UsuarioValor> {
        @Override
        public int compare(UsuarioValor a, UsuarioValor b) {
            return Double.compare(a.getMillasAcumuladas(), b.getMillasAcumuladas());
        }
    }

    public static class UsuarioCmpPromosCanjeadas implements Comparator<UsuarioValor> {
        @Override
        public int compare(UsuarioValor a, UsuarioValor b) {
            return Double.compare(a.getMillasCanjeadas(), b.getMillasCanjeadas());
        }
        
    }

}
