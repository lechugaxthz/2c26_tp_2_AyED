package aed.tp2.props.estructuras;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import aed.tp2.props.enums.Categoria;
import aed.tp2.props.handlers.HeapUsuarioHandler;
import aed.tp2.props.sub_class.UsuarioValor;

import static org.junit.jupiter.api.Assertions.*;

public class HeapTests {

    private HeapUsuarioHandler nuevoHandle(float millasIniciales) {
        UsuarioValor usr = new UsuarioValor(
                new ArrayList<>(),
                millasIniciales,
                millasIniciales,
                0,
                null,
                true,
                0,
                Categoria.BRONCE);

        return new HeapUsuarioHandler(usr);
    }

    @Test
    void agregarYObtenerPorIndice() {
        Heap<Float, UsuarioValor> heap = new Heap<>();
        HeapUsuarioHandler handle = nuevoHandle(0);

        heap.agregar(handle);

        assertEquals(handle, heap.obtener(handle.getIdx()));
    }

    @Test
    void agregarVariosYObtenerPorIndice() {
        Heap<Float, UsuarioValor> heap = new Heap<>();
        HeapUsuarioHandler a = nuevoHandle(0);
        HeapUsuarioHandler b = nuevoHandle(500);

        heap.agregar(a);
        heap.agregar(b);

        assertEquals(a, heap.obtener(a.getIdx()));
        assertEquals(b, heap.obtener(b.getIdx()));
    }

    @Test
    void elDeMasMillasQuedaArriba() {
        Heap<Float, UsuarioValor> heap = new Heap<>();
        HeapUsuarioHandler a = nuevoHandle(500);
        HeapUsuarioHandler b = nuevoHandle(0);

        heap.agregar(a);
        heap.agregar(b);

        assertEquals(a, heap.obtener(1));
        assertEquals(b, heap.obtener(2));

        b.getVal().setMillasDisponibles(1000);
        heap.onUpdate(b);

        assertEquals(a, heap.obtener(2));
        assertEquals(2, a.getIdx());
        assertEquals(b, heap.obtener(1));
        assertEquals(1, b.getIdx());
    }

    @Test
    void bajarMillasDelMaximoLoHundeEnElHeap() {
        Heap<Float, UsuarioValor> heap = new Heap<>();
        HeapUsuarioHandler a = nuevoHandle(500);
        HeapUsuarioHandler b = nuevoHandle(500);

        heap.agregar(a);
        heap.agregar(b);

        a.getVal().setMillasDisponibles(1000);
        heap.onUpdate(a);

        a.getVal().setMillasDisponibles(0);
        heap.onUpdate(a);

        assertEquals(a, heap.obtener(2));
        assertEquals(2, a.getIdx());
        assertEquals(b, heap.obtener(1));
        assertEquals(1, b.getIdx());
    }
}
