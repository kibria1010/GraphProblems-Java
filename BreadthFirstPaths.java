package graphproblems;

import java.util.*;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s-v path
    private int[] edgeTo;      // edgeTo[v] = previous edge on shortest s-v path
    private int[] distTo;      // distTo[v] = number of edges shortest s-v path


    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        validateVertex(s);
        bfs(G, s);
    }

    // breadth-first search from a single source
    private void bfs(Graph G, int s) {
        for (int v = 0; v < G.V(); v++) {
            distTo[v] = INFINITY;
        }
        distTo[s] = 0;
        marked[s] = true;
        
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(s);
        while (!q.isEmpty()) {
            int v = q.pollFirst();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    //System.out.print(w + " ");
                    q.addLast(w);
                }
            }
            //System.out.println("");
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return marked[v];
    }

    public int distTo(int v) {
        validateVertex(v);
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        
        Deque<Integer> path = new ArrayDeque<>();
        while (distTo[v] != 0) {            
            path.push(v);
            v = edgeTo[v];
        }
        path.push(v);
        
        return path;
    }
    
    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public static void main(String[] args) {
         // TODO code application logic here
        Graph g = new Graph(5);
        g.addEdge(0, 1);
        g.addEdge(1, 3);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        
        int s=0;
        BreadthFirstPaths bfs = new BreadthFirstPaths(g, s);
        
        for (int v = 0; v < g.V(); v++) {
            if (bfs.hasPathTo(v)) {
                System.out.print(s + " to " + v + " : " + "(" + bfs.distTo(v) + ") pths: ");
                for(int x : bfs.pathTo(v)) {
                    if (x == s) System.out.print(x);
                    else        System.out.print("-" + x);
                }
                System.out.println();
            }
            else {
                System.out.print(s + " to " + v + "(-):  not connected\n");
            }
        }
    }
}