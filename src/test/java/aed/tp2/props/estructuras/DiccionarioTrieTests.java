package aed.tp2.props.estructuras;

import org.junit.jupiter.api.Test;
import aed.tp2.props.sub_class.PromoValor;
import static org.junit.jupiter.api.Assertions.*;

public class DiccionarioTrieTests {

    private PromoValor nuevaPromo(float factor, float tope, boolean activa) {
        PromoValor promo = new PromoValor();
        promo.factor = factor;
        promo.tope = tope;
        promo.tiene_tope = true;
        promo.activa = activa;
        return promo;
    }

    @Test
    void guardarYRecuperarValor() {
        DiccionarioTrie<PromoValor> trie = new DiccionarioTrie<>();
        PromoValor promo = nuevaPromo(1.5f, 100f, true);

        trie.insertar("CASA", promo);

        assertEquals(promo, trie.obtener("CASA"));
    }

    @Test
    void valorInicialEsNulo() {
        DiccionarioTrie<PromoValor> trie = new DiccionarioTrie<>();

        assertNull(trie.obtener("XY"));
    }

    @Test
    void prefijoNoTieneValor() {
        DiccionarioTrie<PromoValor> trie = new DiccionarioTrie<>();
        trie.insertar("CASA", nuevaPromo(1.5f, 100f, true));

        assertNull(trie.obtener("CA"));
    }

    @Test
    void clavesDistintasNoSePisan() {
        DiccionarioTrie<PromoValor> trie = new DiccionarioTrie<>();
        PromoValor a = nuevaPromo(1.5f, 100f, true);
        PromoValor b = nuevaPromo(2f, 50f, false);

        trie.insertar("CASA", a);
        trie.insertar("CAPO", b);

        assertEquals(a, trie.obtener("CASA"));
        assertEquals(b, trie.obtener("CAPO"));
    }

    @Test
    void insertarSobreescribeValor() {
        DiccionarioTrie<PromoValor> trie = new DiccionarioTrie<>();
        PromoValor a = nuevaPromo(1.5f, 100f, true);
        PromoValor b = nuevaPromo(2f, 50f, false);

        trie.insertar("CASA", a);
        trie.insertar("CASA", b);

        assertEquals(b, trie.obtener("CASA"));
    }
}
