package aed.tp2.props;

import aed.tp2.props.estructuras.DiccionarioTrie;
import aed.tp2.props.sub_class.PromoValor;

public class Promos {

    private DiccionarioTrie<PromoValor> pTries;
    
    public Promos() {
        this.pTries = new DiccionarioTrie<>();
    }

    public void setpTriesValor(String str, double factor, double tope, boolean tiene_tope) {
        
        this.pTries.insertar(
            str, 
            new PromoValor(
                factor,
                tope,
                tiene_tope,
                true
            ));
    }

    public PromoValor getValor(String string) {
        return this.pTries.obtener(string);
    }

    public void desactivarPromo(String promoString){
        this.pTries.obtener(promoString).activa = false;
    } 

}
