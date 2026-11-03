package aed.tp2.props;

import aed.tp2.props.estructuras.DiccionarioTrie;
import aed.tp2.props.records.PromoValor;

public class Promos {

    private DiccionarioTrie pTries;
    
    public Promos() {
        this.pTries = new DiccionarioTrie();
    }

    public void setpTriesValor(String str, PromoValor val) {
        this.pTries.toAbcTries(str, 0).setValor(val);
    }

    public PromoValor getpTriesValor(String string) {
        return this.pTries.toAbcTries(string, 0).getValor();
    }
}
