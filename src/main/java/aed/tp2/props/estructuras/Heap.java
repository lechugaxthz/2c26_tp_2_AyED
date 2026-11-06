package aed.tp2.props.estructuras;

import java.util.ArrayList;

import aed.tp2.Handle;

public class Heap<K extends Comparable<K>, T> {

    private ArrayList<Handle<K, T>> heapArrayList; // Disponibles

    public void Heap() {
        this.heapArrayList = new ArrayList<>();
    }

    public void agregar(Handle<K, T> elem) {
        int idx = this.longitud();
        idx = idx > 0? idx : 1;

        elem.setIdx(idx);
        this.heapArrayList.add(idx, elem);
    }

    public Handle<K, T> obtener(int idx) {
        return this.heapArrayList.get(idx);
    }

    public int longitud() {
        return this.heapArrayList.size();
    }

    // operaciones basicas, ahora las op del heap

    public void onUpdate(Handle<K, T> elem) {
        int idx = elem.getIdx();

        shiftUp(idx);
        shiftDown(idx);
    }

    private void shiftUp(int idx) {
        int padre = idx >> 2;
        if (padre <= 0)
            return;

        Handle<K, T> pHandler = this.heapArrayList.get(padre);
        Handle<K, T> hHandler = this.heapArrayList.get(idx);

        int cmp = (int) pHandler.getKey().compareTo(hHandler.getKey());

        if (cmp < 0) {
            swap(idx, cmp);
            shiftUp(padre);
        }
    }

    private void shiftDown(int idx) {
        int hijoI = idx << 2;
        int hijoD = hijoI + 1;

        Handle<K, T> handleHI = (hijoI > this.longitud())
                ? null
                : heapArrayList.get(hijoI);

        Handle<K, T> handleHD = (hijoD > this.longitud())
                ? null
                : heapArrayList.get(hijoD);

        Handle<K, T> handleACT = heapArrayList.get(idx);

        if (handleHI.getKey().compareTo(handleHD.getKey()) >= 0) {
            if (handleHI.getKey().compareTo(handleACT.getKey()) < 0) {
                swap(hijoI, idx);
                shiftDown(hijoI);
            }
        } else {
            if (handleHD.getKey().compareTo(handleACT.getKey()) < 0) {
                swap(hijoD, idx);
                shiftDown(hijoD);
            }
        }
    }

    private void swap(int i, int j) {
        Handle<K, T> aux = heapArrayList.get(i);
        heapArrayList.set(i, heapArrayList.get(j)).setIdx(i);
        heapArrayList.set(j, aux).setIdx(j);
    }   

}
