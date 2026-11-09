package aed.tp2.props.estructuras;

import java.util.ArrayList;

import aed.tp2.props.interfaces.Handle;

public class Heap<K extends Comparable<K>, T> {

    private ArrayList<Handle<K, T>> heapArrayList; // Disponibles

    public Heap() {
        this.heapArrayList = new ArrayList<>();
    }

    public void agregar(Handle<K, T> elem) {
        int idx = this.longitud();
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
        int padre = idx - 1 >> 1;
        if (padre < 0)
            return;

        Handle<K, T> pHandler = this.heapArrayList.get(padre);
        Handle<K, T> hHandler = this.heapArrayList.get(idx);

        int cmp = (int) pHandler.getKey().compareTo(hHandler.getKey());

        if (cmp < 0) {
            swap(idx, padre);
            shiftUp(padre);
        }
    }

    private void shiftDown(int idx) {
        int hijoI = (idx << 1) + 1;
        int hijoD = hijoI + 1;

        Handle<K, T> handleHI = (hijoI >= this.longitud())
                ? null
                : heapArrayList.get(hijoI);
        if (handleHI == null) {
            return;
        }
        Handle<K, T> handleHD = (hijoD >= this.longitud())
                ? null
                : heapArrayList.get(hijoD);

        Handle<K, T> handleACT = heapArrayList.get(idx);

        int mayor = idx;

        if (handleHI.getKey().compareTo(handleACT.getKey()) > 0) {
            mayor = hijoI;
        }
        if (handleHD != null && handleHD.getKey().compareTo(heapArrayList.get(mayor).getKey()) > 0) {
            mayor = hijoD;
        }

        if (mayor == idx)
            return;

        swap(idx, mayor);
        shiftDown(mayor);
    }

    private void swap(int i, int j) {
        Handle<K, T> elemI = heapArrayList.get(i);
        Handle<K, T> elemJ = heapArrayList.get(j);

        heapArrayList.set(i, elemJ);
        elemJ.setIdx(i);
        heapArrayList.set(j, elemI);
        elemI.setIdx(j);
    }

    private void shiftDownOnExtractMax(int idx) {
        int hijoI = (idx << 1) + 1;
        int hijoD = hijoI + 1;

        Handle<K, T> handleHI = (hijoI >= this.longitud())
                ? null
                : heapArrayList.get(hijoI);
        if (handleHI == null) {
            return;
        }
        Handle<K, T> handleHD = (hijoD >= this.longitud())
                ? null
                : heapArrayList.get(hijoD);

        Handle<K, T> handleACT = heapArrayList.get(idx);

        int mayor = idx;

        if (handleHI.getKey().compareTo(handleACT.getKey()) >= 0) {
            mayor = hijoI;
        }
        if (handleHD != null && handleHD.getKey().compareTo(heapArrayList.get(mayor).getKey()) >= 0) {
            mayor = hijoD;
        }

        if (mayor == idx)
            return;

        swap(idx, mayor);
        shiftDownOnExtractMax(mayor);
    }

    public Handle<K, T> extractMaxTemporal() {

        Handle<K, T> maxElem = this.heapArrayList.get(0);
        if (heapArrayList.size() == 1) {
            heapArrayList.remove(heapArrayList.size() - 1);
        } else if (heapArrayList.size() > 1) {
            Handle<K, T> ultimoElem = this.heapArrayList.remove(this.heapArrayList.size() - 1);

            this.heapArrayList.set(0, ultimoElem);

            ultimoElem.setIdx(0);

            shiftDownOnExtractMax(0);
        }
        return maxElem;

    }

}
