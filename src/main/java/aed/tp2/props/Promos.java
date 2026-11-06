package aed.tp2.props;

import aed.tp2.props.estructuras.DiccionarioTrie;
import aed.tp2.props.sub_class.PromoValor;

public class Promos {

    private DiccionarioTrie<PromoValor> pTries;
    
    public Promos() {
        this.pTries = new DiccionarioTrie<>();
    }

    public void setpTriesValor(String str, PromoValor val) {
        this.pTries.toAbcTries(str, 0).setValor(val);
    }

    public PromoValor getValor(String string) {
        return this.pTries.toAbcTries(string, 0).getValor();
    }

    public void desactivarPromo(String promoString , boolean valor){
        this.pTries.toAbcTries(promoString, 0).getValor().activa = false;
    } 

}
