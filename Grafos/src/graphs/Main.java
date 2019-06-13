/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphs;

import javax.swing.JOptionPane;


/**
 *
 * @author alexis
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   /*Datos configurables para la generación del nodo:
        
        Para generar un gráfo dirigido colocar 1 en la variable dirigido, un cero representa un grafo no dirigdo.
        Para generar un gráfo con ciclo colocar 1 en la variable ciclo, un cero representa un grafo sin ciclos.
        
   */
   
    
    int nNodos = 10, nAristas = 16, dirigido = 0, ciclo = 0, dAristas = 5;
    double p = 0.01, disr = 0.3;
    String auxDirigido, auxCiclo;
   
 /*
 +--------------------------------------------------------------------------------------+
 | NOTA: No todos los parámetros son usados por todos los métodos de creación de grafos.|
 |                                                                                      |
 +--------------------------------------------------------------------------------------+
  */     
       
   Metodo grafo = new Metodo ();

   /*----------------------------------------------------------------------+
   | Nota: Comentar los métodos que no se quieran gráficar, éste programa  |
   | sólo puede graficar un método a la vez.                               |
   +-----------------------------------------------------------------------*/
    
   JOptionPane.showMessageDialog(null, "Ejecutando generador de grafos", "Atención", JOptionPane.INFORMATION_MESSAGE);
   
   if (dirigido == 0)
       auxDirigido = "No";
   else
       auxDirigido = "Sí";
   
   
   if (ciclo == 0)
       auxCiclo = "No";
   else
       auxCiclo = "Sí";
   
   
   
    /*Método Erdos
                ^
                |*/
    JOptionPane.showMessageDialog(null, "Método Erdös y Rényi:\n\nNo. nodos: "+ nNodos +"\nNo. aristas: "+ nAristas +"\nGrafo dirigido: "+ auxDirigido +"\nGrafo con ciclos: "+auxCiclo, "Atención", JOptionPane.INFORMATION_MESSAGE);
    grafo.Erdos(nNodos, nAristas, dirigido, ciclo); //<--invocarMetodo
    grafo.generaGrafo(grafo, dirigido, "Erdos");   //<--Exportar archivo .gv


    /*Método Gilbert
                ^
                |*/
    //JOptionPane.showMessageDialog(null, "Método Gilbert:\n\nNo. nodos: "+ nNodos +"\nProbabilidad: "+ p +"\nGrafo dirigido: "+ auxDirigido +"\nGrafo con ciclos: "+auxCiclo, "Atención", JOptionPane.INFORMATION_MESSAGE);
    //grafo.Gilbert(nNodos, p, dirigido, ciclo);      //<--invocarMetodo
    //grafo.generaGrafo(grafo, dirigido, "Gilbert"); //<--Exportar archivo .gv
    
    
    /*Método Barabási-Albert
                ^
                |*/
    //JOptionPane.showMessageDialog(null, "Método Barabási-Albert:\n\nNo. nodos: "+ nNodos +"\nGrafo dirigido: "+ auxDirigido +"\nGrafo con ciclos: "+auxCiclo, "Atención", JOptionPane.INFORMATION_MESSAGE);
    //grafo.BarabasiAlbert(nNodos, dAristas, dirigido, ciclo); //<--invocarMetodo
    //grafo.generaGrafo(grafo, dirigido, "Barabasi-Albert"); //<--Exportar archivo .gv
    
    /*Método Geogáfico Simple
                ^
                |*/
    //JOptionPane.showMessageDialog(null, "Método Geográfico Simple:\n\nNo. nodos: "+ nNodos +"\nDistancia de la arista: "+ disr +"\nGrafo dirigido: "+ auxDirigido +"\nGrafo con ciclos: "+auxCiclo, "Atención", JOptionPane.INFORMATION_MESSAGE);
    //grafo.geograficoSimple(nNodos, disr,dirigido, ciclo); //<--invocarMetodo
    //grafo.generaGrafo(grafo, dirigido, "Geogáfico Simple"); //<--Exportar archivo .gv
    

    //Métdos de busqueda
    
    /*
 +--------------------------------------------------------------------------------------+
 | NOTA: El entero raíz declarado abajo, es el valor del nodo fuente para la generación |
 |        del árbol binario de busqueda, por default tiene un valor de cero.            |
 +--------------------------------------------------------------------------------------+
  */   
    int raiz=0;
       /*Método BFS
                ^
                |*/    
   //JOptionPane.showMessageDialog(null, "Método de búsqueda BFS:\n\nNodo raíz: "+ raiz, "Atención", JOptionPane.INFORMATION_MESSAGE);    
   //grafo.BFS(grafo, raiz);
   
   
   
   /*Método DFS Iterativo
                ^
                |*/    
   //JOptionPane.showMessageDialog(null, "Método de búsqueda DFS Iterativo:\n\nNodo raíz: "+ raiz, "Atención", JOptionPane.INFORMATION_MESSAGE);    
   //grafo.DFS_I(grafo, raiz);   
   
   
   /*Método DFS Recursivo
                ^
                |*/    
   //JOptionPane.showMessageDialog(null, "Método de búsqueda DFS Recursivo:\n\nNodo raíz: "+ raiz, "Atención", JOptionPane.INFORMATION_MESSAGE);    
   //grafo.AuxDFS_R();
   //grafo.DFS_R(grafo, raiz); 
   
   
   /*
 +--------------------------------------------------------------------------------------+
 | NOTA: Configuración de los valores máximos y mínimos de los pesos de las aristas.    |
 |        Los valores pordefecto son mínimo = 0 y máximo = 10.                              |
 +--------------------------------------------------------------------------------------+
  */
   
   float min = 1, max = 8;
   String entero = "si";
   
   JOptionPane.showMessageDialog(null, "Gráfo con pesos", "Atención", JOptionPane.INFORMATION_MESSAGE);    
   grafo.pesosAristas(grafo, min, max, entero);
   //grafo.Dijkstra (grafo, raiz);
   //grafo.Kruskal_D (grafo);
   grafo.Kruskal_I (grafo);
 
    }
    
}
