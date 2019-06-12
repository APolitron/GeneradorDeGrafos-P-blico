/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import java.text.DecimalFormat;
import java.util.*;

/**
 *
 * @author alexis
 */
public class Arista {
    Nodo A;
    Nodo B;
    String datos;
    float peso;
    
    public Arista (){}
    
    public Arista (Nodo a, Nodo b){
        this.A = a;
        this.B = b;
        this.datos = datos;
    }
    
    public void nodos (Nodo a, Nodo b){
        A = a;
        B = b;
    }
  
    public HashMap<Integer, Nodo> obtenNodo() {
        HashMap<Integer, Nodo> hm = new HashMap<>();
        hm.put(1, A);
        hm.put(2, B);
        return hm;
    }
    
    public void datos (String Data) {
        datos = Data;
    }
    
    public String obtenDato(){
        return datos;
    }
    
    public void agregaPeso(float peso){
        this.peso = peso;
    }
    
    public float obtenPeso(){
        return peso;
    }
   
    public void aristaConPeso(Nodo a, Nodo b, float peso){
        this.A = a;
        this.B = b;
        this.datos = datos;
        this.peso = peso;
    }

}