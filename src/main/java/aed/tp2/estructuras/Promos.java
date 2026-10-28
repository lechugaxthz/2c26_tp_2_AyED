package aed.tp2.estructuras;

import aed.tp2.estructuras.records.PromoValor;

public class Promos {

    private Tries pTries;
    
    
    private class Tries {

        private Tries[] abcTries;
        
        private PromoValor valor;

        public Tries(){
            this.abcTries = new Tries[27*2];    // almacenamos mayusculas y minusculas
            this.valor = null;
        }

        public Tries toAbcTries(String str, int _i ) {
            if (str.length() < _i) return  this;

            int idx = str.charAt(_i) - 'A';

            return this.abcTries[idx] != null
                        ? (this.abcTries[idx]).toAbcTries(str, _i++)
                        : (this.abcTries[idx] = new Tries()).toAbcTries(str, _i++);
        }

        public void setValor(PromoValor valor) {
            this.valor = valor;
        }
        
        public PromoValor getValor() {
            return valor;
        }
    }


    public Promos() {
        this.pTries = new Tries();
    }

    public void setpTriesValor(String str, PromoValor val) {
        this.pTries.toAbcTries(str, 0).setValor(val);
    }

    public PromoValor getpTriesValor(String string) {
        return this.pTries.toAbcTries(string, 0).getValor();
    }
}
