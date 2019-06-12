/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;
import java.util.HashMap;

/**
 *
 * @author alexis
 */
public class Nodo {
    
    int identificador, grado = 0;
    String nombre, datos;
    double x,y;
    HashMap<Integer, Nodo> nodoProximo = new HashMap<>();
    
    public Nodo (){
}
    public Nodo (int identificador, String nombre, String datos){
        this.identificador = identificador;
        this.datos = datos;
        this.nombre = nombre;

    }
    public Nodo (int identificador, String nombre, double x, double y, String datos){
        this.identificador = identificador;
        this.nombre = nombre;
        this.x = x;
        this.y = y;
        this.datos = datos;  
    }
    
     public void nodosProximos(Nodo nodo) {
        nodoProximo.put(nodo.obtenIdentificador(), nodo);
    }
   
    public void identificadores(int Id) {
        identificador = Id;
    }
        
    public void nombres(String Name) {
        nombre = Name;
    }
        
    public void datos(String dato) {
        datos = dato;
    }
  
    public void coordenada(double x, double y) {
        this.x = x;
        this.y = y;
    }
        
    public void aumentaGrado() {
        this.grado += 1;
        //(this.grado)++;
    }
    
    public HashMap<Integer, Nodo> obtenerNodosProximos() {
        return nodoProximo;
    }
    
    public int obtenIdentificador() {
        return identificador;
    }
    
    public double obtenX() {
        return x;
    }
    
    public double obtenY() {
        return y;
    }
        
    public int obtenGrado() {
        return grado;
    }
    
    public String obtenNombre() {
        return nombre;
    }
    
    public String obtenDato() {
        return datos;
    }
    
}
