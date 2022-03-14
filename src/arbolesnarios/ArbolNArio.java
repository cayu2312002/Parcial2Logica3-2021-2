package arbolesnarios;

import java.util.ArrayList;

public class ArbolNArio<T> {
    private Nodo<T> raiz;

    public ArbolNArio(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public boolean vacio() {
        return raiz == null;
    }

    public Nodo<T> getRaiz() {
        return raiz;
    }

    public void setRaiz(Nodo<T> raiz) {
        this.raiz = raiz;
    }

    public boolean existe(T clave) {
        return encontrar(raiz, clave);
    }

    public int getNumeroNodos() {
        return getNumeroDescendientes(raiz) + 1;
    }

    public int getNumeroDescendientes(Nodo<T> nodo) {
        int n = nodo.getHijos().size();
        for (Nodo<T> hijo : nodo.getHijos())
            n += getNumeroDescendientes(hijo);
        return n;
    }

    private boolean encontrar(Nodo<T> nodo, T nodoClave) {
        boolean res = false;
        if (nodo.getDato().equals(nodoClave))
            return true;
        else {
            for (Nodo<T> hijo : nodo.getHijos())
                if (encontrar(hijo, nodoClave))
                    res = true;
        }
        return res;
    }

    private Nodo<T> encontrarNodo(Nodo<T> nodo, T nodoClave) {
        if (nodo == null)
            return null;
        if (nodo.getDato().equals(nodoClave))
            return nodo;
        else {
            Nodo<T> cnodo;
            for (Nodo<T> hijo : nodo.getHijos())
                if ((cnodo = encontrarNodo(hijo, nodoClave)) != null)
                    return cnodo;
        }
        return null;
    }

    public ArrayList<Nodo<T>> getPreOrder() {
        ArrayList<Nodo<T>> preOrder = new ArrayList<>();
        construirPreOrder(raiz, preOrder);
        return preOrder;
    }

    public ArrayList<Nodo<T>> getPostOrder() {
        ArrayList<Nodo<T>> postOrder = new ArrayList<>();
        construirPostOrder(raiz, postOrder);
        return postOrder;
    }

    private void construirPreOrder(Nodo<T> nodo, ArrayList<Nodo<T>> preOrder) {
        preOrder.add(nodo);
        for (Nodo<T> hijo : nodo.getHijos()) {
            construirPreOrder(hijo, preOrder);
        }
    }

    private void construirPostOrder(Nodo<T> nodo, ArrayList<Nodo<T>> postOrder) {
        for (Nodo<T> hijo : nodo.getHijos()) {
            construirPostOrder(hijo, postOrder);
        }
        postOrder.add(nodo);
    }

    public ArrayList<Nodo<T>> caminoMasLargo() {
        ArrayList<Nodo<T>> camino = null;
        int max = 0;
        for (ArrayList<Nodo<T>> ruta : getRamas()) {
            if (ruta.size() > max) {
                max = ruta.size();
                camino = ruta;
            }
        }
        return camino;
    }

    public int getCaminoMasLargo() {
        return caminoMasLargo().size();
    }

    public ArrayList<ArrayList<Nodo<T>>> getRamas() {
        ArrayList<ArrayList<Nodo<T>>> rutas = new
                ArrayList<>();
        ArrayList<Nodo<T>> camino = new ArrayList<>();
        getPath(raiz, camino, rutas);
        return rutas;
    }

    private void getPath(Nodo<T> nodo, ArrayList<Nodo<T>> camino,
                         ArrayList<ArrayList<Nodo<T>>> rutas) {
        if (camino == null)
            return;
        camino.add(nodo);
        if (nodo.getHijos().size() == 0) {
            rutas.add(clone(camino));
        }
        for (Nodo<T> hijo : nodo.getHijos())
            getPath(hijo, camino, rutas);
        int index = camino.indexOf(nodo);
        if (camino.size() > index) {
            camino.subList(index, camino.size()).clear();
        }
    }

    private ArrayList<Nodo<T>> clone(ArrayList<Nodo<T>> list) {
        ArrayList<Nodo<T>> lista = new ArrayList<>();
        for (Nodo<T> nodo : list)
            lista.add(new Nodo<>(nodo));
        return lista;
    }
}