/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import static java.util.Map.Entry.comparingByValue;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import static java.util.stream.Collectors.toMap;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import static java.util.stream.Collectors.*;
import static java.util.Map.Entry.*;


/**
 *
 * @author alexis
 */
public class Metodo implements Cloneable{
    HashMap<Integer, Nodo> Nodos = new HashMap<>();
    HashMap<Integer, Arista> Aristas = new HashMap<>();
    HashMap<Integer, Nodo> nuevosNodos = new HashMap<>();
    HashMap<Integer, Arista> nuevasAristas = new HashMap<>();
    LinkedList<String> visitadosR = new LinkedList();
    LinkedList<String> recorridosR = new LinkedList();
    LinkedList<String> visitadosRKI = new LinkedList();
    LinkedList<String> recorridosRKI = new LinkedList();
    int NaR = 0;
    HashMap<String, Float> pesoArista = new HashMap<>();
    
    
    public Metodo(){
    }
    public int crearNodo (int identificador, String nombre, String datos){
        Nodo nodo = new Nodo(identificador, nombre, datos);
        Nodos.put(identificador, nodo);
        return 0;
    }

    public int crearNuevoNodo (int identificador, String nombre, String datos){
        Nodo nodo = new Nodo(identificador, nombre, datos);
        nuevosNodos.put(identificador, nodo);
        return 0;
    }
    

    
    public int crearNodoCoordenado (int identificador, String nombre, double x, double y, String datos){
        Nodo nodo = new Nodo (identificador, nombre, x, y, datos);
        Nodos.put(identificador, nodo);
            return 0;
    }
    
    public int crearArista (int identificador, Nodo a, Nodo b, String datos){
        Arista arista = new Arista (a,b);
        Aristas.put(identificador, arista);
        a.aumentaGrado();
        b.aumentaGrado();
        a.nodosProximos(b);
        b.nodosProximos(a);
        return 0;
        
    }
    
    
    public int crearNuevaArista (int identificador, Nodo a, Nodo b, String datos){
        Arista arista = new Arista (a,b);
        nuevasAristas.put(identificador, arista);
        a.aumentaGrado();
        b.aumentaGrado();
        a.nodosProximos(b);
        b.nodosProximos(a);
        return 0;
        
    }
    
     
    
    public int obtenGradoNodo (Nodo nodo){
    return nodo.obtenGrado();
    }
    
    public void crearNodo (int n){
        for (int i = 0; i < n; i++){
            crearNodo (i, "Nodo"+i,"No hay datos");
        }
    
    }
    
    public void creaNodoCooredenado (int n){
        double x,y;
        Random aleatorio = new Random();
        for (int i = 0;i < n; i++){
            x = aleatorio.nextFloat();
            y = aleatorio.nextFloat();
            crearNodoCoordenado(i,"Nodo"+i,x,y,"No hay datos");
        }
    
    }
    
          
    public HashMap<Integer, Nodo> obtenNodoMetodo() {
        return Nodos;
    }
    
    public HashMap<Integer, Arista> obtenAristaMetodo() {
        return Aristas;
    }
    
    public HashMap<Integer, Arista> obtenNuevasAristaMetodo() {
        nuevasAristas.putAll(Aristas);
        return nuevasAristas;
    }
    
    public HashMap<Integer, Arista> obtenNuevasAristas() {
        return nuevasAristas;
    }
    
    
    public HashMap<Integer, Nodo> obtenNuevosNodoMetodo() {
        nuevosNodos.putAll(Nodos);
        return Nodos;
    }
    
    
    
    public int generaGrafo(Metodo grafo, int dirigido, String nombre) {
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
            HashMap<Integer, Arista> EG = grafo.obtenAristaMetodo();
            try (FileWriter fw = new FileWriter(nombre+".gv");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                if (dirigido == 0) { 
                    out.println("strict graph{");
                    out.flush();
                } else { 
                    out.println("strict digraph{");
                    out.flush();
                }
        
            Set setE = EG.entrySet();
            Iterator it = setE.iterator();
            while(it.hasNext()) {
                Map.Entry mentry = (Map.Entry)it.next();
                Arista arista = (Arista)mentry.getValue();
                HashMap<Integer, Nodo> anode = arista.obtenNodo();
                Nodo A = anode.get(1);
                Nodo B = anode.get(2);
             
                if (dirigido == 0) { 
                    out.println("   \"" + A.obtenIdentificador() + "," + A.obtenGrado() + "\"--\"" + B.obtenIdentificador() + "," + B.obtenGrado() + "\"");
                    out.flush();
                } else { 
                    out.println("   \"" + A.obtenIdentificador() + "," + A.obtenGrado() + "\"->\"" + B.obtenIdentificador() + "," + B.obtenGrado() + "\"");
                    out.flush();
                }
            }
            out.println("}");
            out.close();
            JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return 1;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);
                return 0;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return 1;
    }
    
    
  
    //Métodos de generación de grafos 
    
    public Metodo Erdos(int n, int m, int dirigido, int ciclo) {
        if (m > n*(n+1)/2) {
        return null;
        }
        Random rand = new Random();
        int j = 0, indexA, indexB, crear = 1;
        crearNodo(n);

        while(Aristas.size() < m) {
            indexA = rand.nextInt(n); 
            indexB = rand.nextInt(n); 
            if (ciclo == 0) { 
                while (indexA == indexB) {
                    indexB = rand.nextInt(n);
                }
            }
            Nodo a = Nodos.get(indexA); 
            Nodo b = Nodos.get(indexB); 
            Set set = Aristas.entrySet();
            Iterator iterator = set.iterator();
            while(iterator.hasNext()) { 
                Map.Entry mentry = (Map.Entry)iterator.next();
                mentry.getKey();
                Arista arista = (Arista)mentry.getValue();
                HashMap<Integer, Nodo> aristaNodos = arista.obtenNodo();
                Nodo A = aristaNodos.get(1);
                Nodo B = aristaNodos.get(2);
                crear = 1;
                if ((a == A && b == B) || (a == B && b == A && dirigido == 0)) { 
                    crear = 0;
                    break;
                }
            }
            if (crear != 0) {
                crearArista(j, a, b, "Nodo conectado " + a.obtenNombre()+" Nodo " + b.obtenNombre());
                crear = 1; 
            }
            j++;
        }
        return new Metodo();
    }
    
    
    
    public Metodo Gilbert(int n, double p, int dirigido, int ciclo) {
        if (p > 1) {
            return null;
        }
        
        Random rand = new Random();
        int i, j, k = 0, crear = 1;
        double probability;
        crearNodo(n);
   
        for(i = 0; i < n; i++) { 
            for(j = 0; j < n; j++){
                if (ciclo == 0 && i == j) { 
                    continue;
                }
                probability = rand.nextFloat();
                if (probability < p) { 
                    Nodo a = Nodos.get(i);
                    Nodo b = Nodos.get(j);
                    Set set = Aristas.entrySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()) { 
                        Map.Entry mentry = (Map.Entry)iterator.next();
                        mentry.getKey();
                        Arista arista = (Arista)mentry.getValue();
                        HashMap<Integer, Nodo> aristaNodos = arista.obtenNodo();
                        Nodo A = aristaNodos.get(1);
                        Nodo B = aristaNodos.get(2);
                        crear = 1;
                        if ((a == A && b == B) || (a == B && b == A && dirigido == 0)) { 
                            crear = 0; 
                            break;
                        }
                    }
                    if(crear != 0) {
                        crearArista(k++, a, b, " Nodo Conectado " + a.obtenIdentificador() +" Nodo " + b.obtenIdentificador());
                        crear = 1;
                   }
                }
            }
        }     
        return new Metodo();
    }
        
    
    public Metodo BarabasiAlbert(int n, int d, int dirigido, int ciclo) {
        int Cantidad = 0, auxNodo = 0, denominador = 0;
        double auxProba, proba;
        Random rand = new Random();
        for (int i = 0; i < n; i++) { 
            crearNodo(i, "Nodo " + i, " Sin datos ");
            denominador = 0;
            if (ciclo==0)
                Cantidad = Nodos.size()-1;
            else 
                Cantidad = Nodos.size();
         
            
            for (int k = 0; k < Cantidad; k++){
                    denominador += Nodos.get(k).obtenGrado();
                }
                        
            for (int j = 0; j < Cantidad; j++) {
                auxProba = rand.nextFloat();
                
                proba = Nodos.get(j).obtenGrado()/(double)denominador;//1 - Nodos.get(j).obtenGrado() / (double)g; 
                                              
                if (i < d) {
                    Nodo A = Nodos.get(i);
                    Nodo B = Nodos.get(j);
                    crearArista(auxNodo++, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());
                }
                if (i >= d && Nodos.get(j).obtenGrado() < d && proba > auxProba ) {
                    Nodo A = Nodos.get(i);
                    Nodo B = Nodos.get(j);
                    crearArista(auxNodo++, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());
                }
            }
        }
        return new Metodo();
    }
    
  
    public Metodo geograficoSimple(int n, double disr, int dirigido, int ciclo) {
        if (disr >= 1) {
            return null;
        }
        int i, j, k = 0, crear = 1;
        creaNodoCooredenado(n);
    
        List index = new ArrayList();
        List jndex = new ArrayList();
        for (int l = 0; l < n; l++) {
            index.add(l);
            jndex.add(l);
        }
        Collections.shuffle(index);
        Collections.shuffle(jndex);
        int c, d;
        
        for(i = 0; i < n; i++) { 
            c = (int)index.get(i);
            for(j = 0; j < n; j++){
                d = (int)jndex.get(j);
                if (ciclo == 0 && (c == d)) { 
                    continue;
                }
                Nodo a = Nodos.get(c);
                Nodo b = Nodos.get(d);
                double x1 = a.obtenX();
                double y1 = a.obtenY();
                double x2 = b.obtenX();
                double y2 = b.obtenY();
                double dis = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
                if (dis <= disr) { 
                    Set set = Aristas.entrySet();
                    Iterator iterator = set.iterator();
                    while(iterator.hasNext()) {
                        Map.Entry mentry = (Map.Entry)iterator.next();
                        mentry.getKey();
                        Arista arista = (Arista)mentry.getValue();
                        HashMap<Integer, Nodo> aristaNodo = arista.obtenNodo();
                        Nodo A = aristaNodo.get(1); 
                        Nodo B = aristaNodo.get(2); 
                        crear = 1;
                        if ((a == A && b == B) || (a == B && b == A && dirigido == 0)) {
                            crear = 0; 
                            break;
                        }
                    }
                    if(crear != 0) {
                        crearArista(k++, a, b, " Nodo Conectado " + a.obtenIdentificador() +" Nodo " + b.obtenIdentificador());
                        crear = 1;
                    }
                }
            }
        }
        return new Metodo();
    }  
    
    
    //Métodos de busqueda
          
       
     public int BFS (Metodo grafo, int s){    
         
        LinkedList<String> Visitados = new LinkedList();
        LinkedList<String> Q = new LinkedList();
        nuevasAristas.clear();  
        Aristas.clear();
        nuevosNodos.putAll(Nodos);
        int j = 0, l = 0;
        Nodo A = nuevosNodos.get(s);
        String nombre;
        
        while(A.obtenGrado() == 0){
            s++;
            A = nuevosNodos.get(s);
        }
        
        Q.add(""+A.obtenIdentificador());       
        crearNuevoNodo(j, "Nodo " +j, " Sin datos ");  
        
        while (!Q.isEmpty()){
            for (int i = 0; i < nuevosNodos.size(); i++){
                if(A.nodoProximo.containsKey(i) && !Q.contains(""+i))
                    if(!Visitados.contains(""+i)){
                        Q.add(""+i);
                        Nodo B = nuevosNodos.get(i);
                        crearNuevaArista(l++, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());
                    }                
            }

            if(!Q.isEmpty())
                j = Integer.parseInt(Q.pollFirst());
            Visitados.add(""+j);

            if(!Q.isEmpty()){
                A = nuevosNodos.get(Integer.parseInt(Q.get(0)));
            }            
        }
        System.out.println("La lista de visitados es: "+Visitados);
       
        
        //---Pinta---
        
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
            HashMap<Integer, Arista> EG = grafo.obtenNuevasAristaMetodo();
            try (FileWriter fw = new FileWriter(nombre+".gv");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                
                    out.println("strict graph{");
                    out.flush();
                
            Set setE = EG.entrySet();
            Iterator it = setE.iterator();
            while(it.hasNext()) {
                Map.Entry mentry = (Map.Entry)it.next();
                Arista arista = (Arista)mentry.getValue();
                HashMap<Integer, Nodo> anode = arista.obtenNodo();
                Nodo a = anode.get(1);
                Nodo b = anode.get(2);

                    out.println("   \"" + a.obtenIdentificador() + "\"--\"" + b.obtenIdentificador() + "\"");
                    out.flush();
            }
            out.println("}");
            out.close();
            JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return 1;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);
                return 0;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return 1;
  
    }
        
  
     public int DFS_I (Metodo grafo, int s){    
         
        LinkedList<String> visitados = new LinkedList();
        LinkedList<String> recorridos = new LinkedList();
        LinkedList<Integer>  conectados = null;
        nuevasAristas.clear();
        Aristas.clear();
        nuevosNodos.putAll(Nodos);
        
        int l = 0, flag = 0, Na = 0;   
        Nodo A = nuevosNodos.get(s);
        String nombre;
        
        if(A.obtenGrado() == 0){
            System.out.println("No existe el nodo raíz especificado");
            return 0;
        }
        
        recorridos.add(""+A.obtenIdentificador());

        while(!recorridos.isEmpty()){

            while (!recorridos.isEmpty()){


                conectados = new LinkedList (A.obtenerNodosProximos().keySet());
                
                //System.out.println("Tamaño "+conectados.size());
                if (conectados.size() == 1 && visitados.contains(""+conectados.get(0)))
                    break;
                //System.out.println("conectados"+conectados);
                while(!conectados.isEmpty()){
                    
                    flag = 0;
                    
                    if (!recorridos.contains(""+conectados.getFirst()) && !visitados.contains(""+conectados.getFirst())){
                        //System.out.println("A");
                        l = conectados.pollFirst();
                        recorridos.add(""+l);
                        A = nuevosNodos.get(l);
                        break;
                        }

                        else{
                            conectados.pollFirst();
                            //System.out.println("B");
                            flag = 1;
                        }
                }
                //System.out.println("Recorridos "+recorridos);
                
                if(conectados.isEmpty() && flag == 1)
                    break;
            }
            
            int x = Integer.parseInt(recorridos.pollLast());

            visitados.add(""+x);
            
            Nodo B = nuevosNodos.get(x);           
                        
            if (!recorridos.isEmpty()){
                int y = Integer.parseInt(recorridos.getLast());
                A = nuevosNodos.get(y);
                crearNuevaArista(Na, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());
                //System.out.println("Arista no: "+Na);
                Na++;
               // System.out.println("AristaCreada entre: "+A.obtenIdentificador()+"-->"+B.obtenIdentificador());
            }

            //crearNuevaArista(l++, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());
            
            if (recorridos.size() == 1 && Integer.parseInt(recorridos.getFirst()) == s){
                visitados.add(""+recorridos.pollFirst());
                break;
            }

        }
        System.out.println("Visitados "+visitados);

        //------   Pinta   -------
        
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
            HashMap<Integer, Arista> EG = grafo.obtenNuevasAristaMetodo();
            try (FileWriter fw = new FileWriter(nombre+".gv");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                
                    out.println("strict graph{");
                    out.flush();
                
            Set setE = EG.entrySet();
            Iterator it = setE.iterator();
            while(it.hasNext()) {
                Map.Entry mentry = (Map.Entry)it.next();
                Arista arista = (Arista)mentry.getValue();
                HashMap<Integer, Nodo> anode = arista.obtenNodo();
                Nodo a = anode.get(1);
                Nodo b = anode.get(2);

                    out.println("   \"" + a.obtenIdentificador() + "\"--\"" + b.obtenIdentificador() + "\"");
                    out.flush();
            }
            out.println("}");
            out.close();
            JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);
            return 1;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);
                return 0;
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
  
        return 1;

    }
    
    
    public void AuxDFS_R(){
        Aristas.clear();
        nuevosNodos.putAll(Nodos);
        recorridosR.clear();
        visitadosR.clear();
        nuevasAristas.clear();
        
    }
    
    public int DFS_R(Metodo grafo, int s) {
        LinkedList<Integer>  conectados = null;
        int l = 0, flag = 0, Na = 0;
        
        Nodo A = nuevosNodos.get(s);
        String nombre;
        
        if(A.obtenGrado() == 0){
            System.out.println("No existe el nodo raíz especificado");
            return 0;
        }
        
        recorridosR.add(""+A.obtenIdentificador());
        conectados = new LinkedList (A.obtenerNodosProximos().keySet());
        while(!recorridosR.isEmpty()){

            if (!recorridosR.contains(""+conectados.getFirst()) && !visitadosR.contains(""+conectados.getFirst())){
                l = conectados.pollFirst();
                A = nuevosNodos.get(l);
                DFS_R(grafo,l);
            }
            
            else{
                if(!conectados.isEmpty()){
                conectados.pollFirst();
                flag = 1;
                }
            }

            if(conectados.isEmpty() && flag == 1)
                break;
        }

            if (!recorridosR.isEmpty()){
                
                int x = Integer.parseInt(recorridosR.pollLast());
                visitadosR.add(""+x);
                Nodo B = nuevosNodos.get(x);           

                int y = Integer.parseInt(recorridosR.getLast());
                A = nuevosNodos.get(y);
                Na = nuevasAristas.size()+1;
                crearNuevaArista(Na, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());
                //System.out.println("AristaNo"+Na+"Creada entre: "+A.obtenIdentificador()+"-->"+B.obtenIdentificador());
                
            }
            
           
            if (recorridosR.size() == 1 && Integer.parseInt(recorridosR.getFirst()) == 0){
                visitadosR.add(""+recorridosR.pollFirst());
                                
                
                
                    //------   Pinta   -------

            JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
                HashMap<Integer, Arista> EG = grafo.obtenNuevasAristaMetodo();
                try (FileWriter fw = new FileWriter(nombre+".gv");
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {

                        out.println("strict graph{");
                        out.flush();

                Set setE = EG.entrySet();
                Iterator it = setE.iterator();
                while(it.hasNext()) {
                    Map.Entry mentry = (Map.Entry)it.next();
                    Arista arista = (Arista)mentry.getValue();
                    HashMap<Integer, Nodo> anode = arista.obtenNodo();
                    Nodo a = anode.get(1);
                    Nodo b = anode.get(2);

                        out.println("   \"" + a.obtenIdentificador() + "\"--\"" + b.obtenIdentificador() + "\"");
                        out.flush();
                }
                out.println("}");
                out.close();
                JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);
                return 1;
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return 0;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
     
            }

        return 1;
        
    }

    
    public Metodo pesosAristas(Metodo grafo, float min, float max, String entero) {
        nuevasAristas.clear();
        obtenNuevasAristaMetodo();
        Set set = nuevasAristas.entrySet();
        Iterator it = set.iterator();
        String nombre;
        
        while(it.hasNext()) {
            Map.Entry mentry = (Map.Entry)it.next();
            Arista arista = (Arista)mentry.getValue();
            
            if (entero == "si")
                arista.peso = (int)(min + (Math.random() * ((max - min) + 1)));
            else{
                float P = (float)(min + (Math.random() * ((max - min) + 1)));
                DecimalFormat df = new DecimalFormat();
                df.setMinimumFractionDigits(2);
                df.setMaximumFractionDigits(2);
                P = Float.parseFloat(df.format(P));
                arista.peso = P;
            }           
        }
 
            //---Pinta---
       
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
            HashMap<Integer, Arista> EG = grafo.obtenNuevasAristaMetodo();
            try (FileWriter fw = new FileWriter(nombre+".gv");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                
                    out.println("strict graph{");
                    out.flush();
                
            Set setE = EG.entrySet();
            Iterator itera = setE.iterator();
            while(itera.hasNext()) {
                Map.Entry mentry = (Map.Entry)itera.next();
                Arista arista = (Arista)mentry.getValue();
                HashMap<Integer, Nodo> anode = arista.obtenNodo();
                Nodo a = anode.get(1);
                Nodo b = anode.get(2);
                
                pesoArista.put( Integer.toString(a.obtenIdentificador())+"-"+Integer.toString(b.obtenIdentificador()), arista.obtenPeso());
                   
                    out.println("   \"" + a.obtenIdentificador() + "\"--\"" + b.obtenIdentificador() + "\"[label = \""+ arista.obtenPeso() + "\"]");
                    out.flush();
            }
            out.println("}");
            out.close();
            JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);
            
            
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);
            
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }   
        
        return new Metodo();
    }
    
    
    public int Dijkstra (Metodo grafo, int s){
        System.out.print(pesoArista);
        nuevosNodos.putAll(Nodos);
        nuevasAristas.clear();
        Nodo A = nuevosNodos.get(s);
        HashMap<Integer, Float> pesoNodo = new HashMap<>();
        LinkedList<String> visitados = new LinkedList();
        LinkedList<String> recorridos = new LinkedList();
        LinkedList<Integer>  conectados = null;
        String nombre;
        //int N = -1;
        float pesoActual = 0, pesoNuevo = 0;
        Nodo C = null;
        System.out.println("A");
    //Inicializar con pesos infinitos
        Set setB = grafo.obtenNuevosNodoMetodo().entrySet();
        Iterator iterador1 = setB.iterator();
        while(iterador1.hasNext()){
            Map.Entry me =(Map.Entry)iterador1.next();
            pesoNodo.put((int)me.getKey(), Float.MAX_VALUE);
        }
          
        pesoNodo.put(A.obtenIdentificador(), (float)0.0);
        recorridos.add(""+A.obtenIdentificador());      
        System.out.println(recorridos);
        
        conectados = new LinkedList (A.obtenerNodosProximos().keySet());
        
         while(conectados.isEmpty()){
            s++;
            A = nuevosNodos.get(s);
        }
        
        while(!recorridos.isEmpty()){
  
            A=nuevosNodos.get(Integer.parseInt(recorridos.pollFirst()));
            visitados.add(""+A.obtenIdentificador());
            conectados = new LinkedList (A.obtenerNodosProximos().keySet());          
            
            System.out.println("Nodo actual: "+A.obtenIdentificador());
            System.out.println("Conectados: -->"+conectados);
            
            for (int a = 0; a < nuevosNodos.size();a++){
                if (conectados.contains(a)){
                String prueba =Integer.toString(A.obtenIdentificador())+"-"+ Integer.toString(a);
                    if (pesoArista.get(prueba) != null){                 
                        pesoActual = pesoArista.get(prueba);
                        pesoNuevo = pesoActual+pesoNodo.get(A.obtenIdentificador());
                        
                        if (pesoNodo.get(a) > pesoNuevo){
                            pesoNodo.put(a, (float)pesoNuevo);
                            C = nuevosNodos.get(a);
                            //Integer.parseInt(prueba)
                            crearNuevaArista(a, A, C, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + C.obtenIdentificador());  
                        }
                            
                        if (!recorridos.contains(""+a) && !visitados.contains(""+a))
                            recorridos.add(""+a);
                    }

                    if (pesoArista.get(prueba) == null){
                        prueba = Integer.toString(a)+"-"+ A.obtenIdentificador();                 
                        pesoActual = pesoArista.get(prueba);
                        pesoNuevo = pesoActual+pesoNodo.get(A.obtenIdentificador());
                        if (pesoNodo.get(a) > pesoNuevo){
                            pesoNodo.put(a, (float)pesoNuevo);
                            C = nuevosNodos.get(a);
                            System.out.println("a: "+a);
                            //Integer.parseInt(prueba)
                            crearNuevaArista(a, A, C, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + C.obtenIdentificador());
                        }
                        if (!recorridos.contains(""+a) && !visitados.contains(""+a))
                            recorridos.add(""+a);                        
                    }
                }
            }
            System.out.println("Recorridos: "+recorridos);
            
        }

            //---Pinta---
       
        JFileChooser fc = new JFileChooser();
        if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
            HashMap<Integer, Arista> EG = grafo.obtenNuevasAristas();
            try (FileWriter fw = new FileWriter(nombre+".gv");
                    BufferedWriter bw = new BufferedWriter(fw);
                    PrintWriter out = new PrintWriter(bw)) {
                
                    out.println("strict graph{");
                    out.flush();
                
            Set setE = EG.entrySet();
            Iterator itera = setE.iterator();
            while(itera.hasNext()) {
                Map.Entry mentry = (Map.Entry)itera.next();
                Arista arista = (Arista)mentry.getValue();
                HashMap<Integer, Nodo> anode = arista.obtenNodo();
                Nodo a = anode.get(1);
                Nodo b = anode.get(2);
                
                String oPeso = Integer.toString(a.obtenIdentificador())+"-"+ Integer.toString(b.obtenIdentificador());
                if (pesoArista.get(oPeso) != null)
                    out.println("   \"" + a.obtenIdentificador() +", "+pesoNodo.get((int)a.obtenIdentificador())+ "\"--\"" + b.obtenIdentificador() +", "+pesoNodo.get((int)b.obtenIdentificador())+ "\"[label = \""+ pesoArista.get(oPeso) + "\"]");
                else{
                    oPeso = Integer.toString(b.obtenIdentificador())+"-"+ Integer.toString(a.obtenIdentificador());
                    out.println("   \"" + a.obtenIdentificador() +", "+pesoNodo.get((int)a.obtenIdentificador())+ "\"--\"" + b.obtenIdentificador() +", "+pesoNodo.get((int)b.obtenIdentificador())+ "\"[label = \""+ pesoArista.get(oPeso) + "\"]");
                }
                out.flush();
            }
            out.println("}");
            out.close();
            JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);
            
            
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);
            
                return 1;
                
            }
        } else {
            JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
        }
        return 1;
    }
    
    public int Kruskal_D (Metodo grafo){
        nuevosNodos.putAll(Nodos);
        nuevasAristas.clear();
        //Nodo A = nuevosNodos.get(a);
        HashMap<String, Float> pesoAristaKruskal = new HashMap<>();
        LinkedList<String> recupera = new LinkedList();
        LinkedList<String> recorridos = new LinkedList();
        //LinkedList<Integer>  conectados = null;
        int nArista = -1, x = 0, y = 0, pesoTotal = 0;
        String nombre;
     
        
        pesoAristaKruskal.putAll(pesoArista);
     //Ordena pesos   
        Map<String, Float> pesoAscendente = pesoAristaKruskal
        .entrySet()
        .stream()
        .sorted(comparingByValue())
        .collect( 
                toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                LinkedHashMap::new));
        
        System.out.println("Orden de las aristas: "+pesoAscendente);
        Set setF = pesoAscendente.entrySet();
        Iterator iterador1 = setF.iterator();
        while(iterador1.hasNext()){
            Map.Entry me =(Map.Entry)iterador1.next();
            recupera.add(""+me.getKey());
            String aux = recupera.pollFirst();
            
            System.out.println ("Auxiliar : "+aux);
            String[] parte = aux.split("-");
            x = Integer.parseInt(parte[0]);
            y = Integer.parseInt(parte[1]);
            //if(!recorridos.contains(""+aux%10) && !recorridos.contains(""+aux/10)){
           
                Nodo A = nuevosNodos.get(x);
                Nodo B = nuevosNodos.get(y);
                if(!recorridos.contains(""+x) && !recorridos.contains(""+y)){
                    crearNuevaArista(nArista++, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());  
                    recorridos.add(""+x);
                    recorridos.add(""+y);
                    System.out.println("Los recorridos "+recorridos);
                    // System.out.println("AA");
                }
                else if (recorridos.contains(""+x) && !recorridos.contains(""+y)){
                    crearNuevaArista(nArista++, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());  
                    recorridos.add(""+x);
                    recorridos.add(""+y);
                    System.out.println("Los recorridos "+recorridos);
                    // System.out.println("BB");
                }
                
                else if (!recorridos.contains(""+x) && recorridos.contains(""+y)){
                    crearNuevaArista(nArista++, A, B, " Nodo conectado " + A.obtenIdentificador() + " Nodo " + B.obtenIdentificador());  
                    recorridos.add(""+x);
                    recorridos.add(""+y);
                    System.out.println("Los recorridos "+recorridos);                    
                     //System.out.println("CC");
                }   
                
               
            //}
        }

        //------------------Pinta-------------------------------------
        JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
                HashMap<Integer, Arista> EG = grafo.obtenNuevasAristas();
                try (FileWriter fw = new FileWriter(nombre+".gv");
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {

                        out.println("strict graph{");
                        out.flush();

                Set setE = EG.entrySet();
                Iterator itera = setE.iterator();
                while(itera.hasNext()) {
                    Map.Entry mentry = (Map.Entry)itera.next();
                    Arista arista = (Arista)mentry.getValue();
                    HashMap<Integer, Nodo> anode = arista.obtenNodo();
                    Nodo a = anode.get(1);
                    Nodo b = anode.get(2);

                    String oPeso = Integer.toString(a.obtenIdentificador())+"-"+ Integer.toString(b.obtenIdentificador());
                    if (pesoAristaKruskal.get(oPeso) != null)
                        out.println("   \"" + a.obtenIdentificador()+"\"--\"" + b.obtenIdentificador()+ "\"[label = \""+ pesoArista.get(oPeso) + "\"]");
                    else{
                        oPeso = Integer.toString(b.obtenIdentificador())+"-"+ Integer.toString(a.obtenIdentificador());
                        out.println("   \"" + a.obtenIdentificador()+"\"--\"" + b.obtenIdentificador() +"\"[label = \""+ pesoArista.get(oPeso) + "\"]");
                    }
                    pesoTotal += pesoArista.get(oPeso);
                    out.flush();
                }
                out.println("labelloc=\"t\"\nlabel=\"Peso total Kruskal= "+pesoTotal+"\"");
                out.println("}");
                out.close();
                JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);


                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);

                    return 1;

                }
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        return 1;
    }
    
 
    
    public int BFS_Kruskal_I (Metodo grafo, int s){
        LinkedList<String> Visitados = new LinkedList();
        LinkedList<String> Q = new LinkedList();
        //nuevasAristas.clear();  
        Aristas.clear();
        nuevosNodos.putAll(Nodos);
        int j = 0, l = 0;
        Nodo A = nuevosNodos.get(s);
        while(A.obtenGrado() == 0){
            s++;
            A = nuevosNodos.get(s);
        }
        Q.add(""+A.obtenIdentificador());       
        crearNuevoNodo(j, "Nodo " +j, " Sin datos ");  
        while (!Q.isEmpty()){
            for (int i = 0; i < nuevosNodos.size(); i++){
                if(A.nodoProximo.containsKey(i) && !Q.contains(""+i))
                    if(!Visitados.contains(""+i)){
                        Q.add(""+i);                       
                    }                
            }
            if(!Q.isEmpty())
                j = Integer.parseInt(Q.pollFirst());
            Visitados.add(""+j);
            if(!Q.isEmpty()){
                A = nuevosNodos.get(Integer.parseInt(Q.get(0)));
            }            
        }
        //System.out.println("La lista de visitados es: "+Visitados);
        if (Visitados.size()== Nodos.size())
            return 1;    
        return 0;
    }
    
    
    
    

public int Kruskal_I (Metodo grafo) {
    int conectado = grafo.BFS_Kruskal_I(grafo,0);
    if (conectado == 0){
        System.out.println("Grafo desconectado");
        JOptionPane.showMessageDialog(null, "Grafo desconectado", "Error.", JOptionPane.WARNING_MESSAGE);
        return 0;  
    }
        nuevosNodos.putAll(Nodos);
        //nuevasAristas.clear();
        //Nodo A = nuevosNodos.get(a);
        HashMap<String, Float> pesoAristaKruskalInv = new HashMap<>();
        LinkedList<String> recupera = new LinkedList();
        LinkedList<String> recorridos = new LinkedList();
        //LinkedList<Integer>  conectados = null;
        int nArista = -1, x = 0, y = 0, pesoTotal = 0;
        String nombre;

        pesoAristaKruskalInv.putAll(pesoArista);
        
        //Ordena Descendente
        Map<String, Float> pesoDescendente = pesoAristaKruskalInv
         .entrySet()
         .stream()
         .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
         .collect(
             toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                 LinkedHashMap::new));
              
         Set setG = pesoDescendente.entrySet();
        Iterator iterador1 = setG.iterator();
        while(iterador1.hasNext()){
            Map.Entry me =(Map.Entry)iterador1.next();
            recupera.add(""+me.getKey());
            String aux = recupera.pollFirst();
            //System.out.println ("Auxiliar : "+aux);
            String[] parte = aux.split("-");
            x = Integer.parseInt(parte[0]);
            y = Integer.parseInt(parte[1]);
            //if(!recorridos.contains(""+aux%10) && !recorridos.contains(""+aux/10)){
                Nodo A = nuevosNodos.get(x);
                Nodo B = nuevosNodos.get(y);
                
                A.nodoProximo.remove(A.obtenIdentificador());
                B.nodoProximo.remove(B.obtenIdentificador());
                
                conectado = grafo.BFS_Kruskal_I(grafo,A.obtenIdentificador());
                if (conectado == 1){
                    //System.out.println("Quitar");
                }
                else {
                    A.nodoProximo.put(B.obtenIdentificador(), B);
                    B.nodoProximo.put(A.obtenIdentificador(), A);
                    //System.out.println("No quitar");
                }  
        }
  
        //------------------Pinta-------------------------------------
        JFileChooser fc = new JFileChooser();
            if (fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                nombre = fc.getCurrentDirectory().toString() + "\\" + fc.getSelectedFile().getName();
                HashMap<Integer, Arista> EG = grafo.obtenNuevasAristas();
                try (FileWriter fw = new FileWriter(nombre+".gv");
                        BufferedWriter bw = new BufferedWriter(fw);
                        PrintWriter out = new PrintWriter(bw)) {

                        out.println("strict graph{");
                        out.flush();

                Set setE = EG.entrySet();
                Iterator itera = setE.iterator();
                while(itera.hasNext()) {
                    Map.Entry mentry = (Map.Entry)itera.next();
                    Arista arista = (Arista)mentry.getValue();
                    HashMap<Integer, Nodo> anode = arista.obtenNodo();
                    Nodo a = anode.get(1);
                    Nodo b = anode.get(2);

                    String oPeso = Integer.toString(a.obtenIdentificador())+"-"+ Integer.toString(b.obtenIdentificador());
                    if (pesoAristaKruskalInv.get(oPeso) != null)
                        out.println("   \"" + a.obtenIdentificador()+"\"--\"" + b.obtenIdentificador()+ "\"[label = \""+ pesoArista.get(oPeso) + "\"]");
                    else{
                        oPeso = Integer.toString(b.obtenIdentificador())+"-"+ Integer.toString(a.obtenIdentificador());
                        out.println("   \"" + a.obtenIdentificador()+"\"--\"" + b.obtenIdentificador() +"\"[label = \""+ pesoArista.get(oPeso) + "\"]");
                    }
                    pesoTotal += pesoArista.get(oPeso);
                    out.flush();
                }
                out.println("labelloc=\"t\"\nlabel=\"Peso total Kruskal= "+pesoTotal+"\"");
                out.println("}");
                out.close();
                JOptionPane.showMessageDialog(null, "El grafo se guardará en: " + nombre + ".gv", "Atención", JOptionPane.INFORMATION_MESSAGE);


                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, "No es posible guardar el grafo en la ruta: " + nombre + ".gv", "Error", JOptionPane.INFORMATION_MESSAGE);

                    return 1;

                }
            } else {
                JOptionPane.showMessageDialog(null, "No fue posible guardar el grafo.", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
        return 1;
    }
    
    
    @Override
    public Object clone(){
         Metodo copia = new Metodo ();
         return copia;
    }
}