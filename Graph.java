package graphproblems;


import java.util.*;

public class Graph {
    
    int vertices, edges;
    LinkedList<Integer>[] adj;

    public Graph(int vertices) {
        this.vertices = vertices;
        this.edges = 0;

        adj = (LinkedList<Integer>[]) new LinkedList[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    public int V() {
        return vertices;
    }

    public int E() {
        return edges;
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        edges++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (vertices - 1));
        }
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }
        
    public void graphTraverse() {
        Iterator<Integer> it;
        for (int v = 0; v < vertices; v++) {
            System.out.print(v + ": ");
            it = adj(v).iterator();
            while (it.hasNext()) {
                Integer next = it.next();
                System.out.print(next + " ");
            }
            System.out.println("");
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 2);
        
        System.out.println(g.vertices +" vertices, "+ g.edges + " edges.");
        g.graphTraverse();
        
    }
}
