package aed.tp2.props.handlers;

import aed.tp2.props.interfaces.Handle;
import aed.tp2.props.sub_class.UsuarioValor;

public class HeapUsuarioHandler implements Handle<Float, UsuarioValor> {

    private final UsuarioValor usr;
    private int idx;

    public HeapUsuarioHandler(UsuarioValor usr) {
        this.usr = usr;
        this.idx = 0;
    }
    
    @Override
    public Float getKey() {
        return this.usr.getMillasDisponibles();
    }
    
    @Override
    public int getIdx() {
        return this.idx;
    }

    @Override
    public void setIdx(int idx) {
        this.idx = idx;        
    }

    @Override
    public UsuarioValor getVal() {
        return this.usr;
    }

}