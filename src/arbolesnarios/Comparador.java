package arbolesnarios;

import java.util.ArrayList;

public class Comparador {
    //Método principal que recibe dos árboles NArios, e imprime las coincidencias y los faltantes.
    public static void comparador(ArbolNArio<String> Propio, ArbolNArio<String> Otro) {
        ArrayList<Nodo<String>> PropioPreOrder = Propio.getPreOrder();
        ArrayList<Nodo<String>> OtroPreOrder = Otro.getPreOrder();
        ArrayList<Object> Coincidencias = new ArrayList<>();
        ArrayList<Object> Faltantes = new ArrayList<>();

        for (Object T : OtroPreOrder) {
            if (PropioPreOrder.contains(T)) {
                Coincidencias.add(T);
            } else {
                Faltantes.add(T);
            }
        }

        System.out.println("Las coincidencias entre los arboles NArios son:");
        for (Object T : Coincidencias) {
            System.out.println(T);
        }
        System.out.println("Los Faltantes del Arbol 'Otro' en 'Propio' son:");
        for (Object T : Faltantes) {
            System.out.println(T);
        }
    }

    public static void main(String[] args) {
        Nodo<String> Raiz = new Nodo<>("Luis");
        ArbolNArio<String> Propio = new ArbolNArio<>(Raiz);
        Nodo<String> nuevo = new Nodo<>("Roman");
        Raiz.agregarHijo(nuevo);
        nuevo.agregarHijo(new Nodo<>("Felipe"));

        Nodo<String> RaizOtro = new Nodo<>("Lucila");
        Nodo<String> nuevoOtro = new Nodo<>("Roman");
        ArbolNArio<String> Otro = new ArbolNArio<>(RaizOtro);
        RaizOtro.agregarHijo(nuevoOtro);
        nuevoOtro.agregarHijo(new Nodo<>("Miguel"));

        comparador(Propio, Otro);
    }
}