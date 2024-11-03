package depthfirstpaths;

import java.util.Iterator;
import java.util.Stack;

public class NonrecursiveDirectedDFS {
    private boolean[] marked;  // marked[v] = is there an s->v path?

    public NonrecursiveDirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        validateVertex(s);

        // to be able to iterate over each adjacency list, keeping track of which
        // vertex in each adjacency list needs to be explored next
        Iterator<Integer>[] adj = (Iterator<Integer>[]) new Iterator[G.V()];
        for (int v = 0; v < G.V(); v++)
            adj[v] = G.adj(v).iterator();

        // depth-first search using an explicit stack
        Stack<Integer> stack = new Stack<>();
        marked[s] = true;
        stack.push(s);
        while (!stack.isEmpty()) {
            int v = stack.peek();
            if (adj[v].hasNext()) {
                int w = adj[v].next();
                // StdOut.printf("check %d\n", w);
                if (!marked[w]) {
                    // discovered vertex w for the first time
                    marked[w] = true;
                    // edgeTo[w] = v;
                    stack.push(w);
                    // StdOut.printf("dfs(%d)\n", w);
                }
            }
            else {
                // StdOut.printf("%d done\n", v);
                stack.pop();
            }
        }
    }

    /**
     * Is vertex {@code v} reachable from the source vertex {@code s}?
     * @param  v the vertex
     * @return {@code true} if vertex {@code v} is reachable from the source vertex {@code s},
     *         and {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public boolean marked(int v) {
        validateVertex(v);
        return marked[v];
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = marked.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }


    public static void main(String[] args) {
        // TODO code application logic here
        Digraph g = new Digraph(4);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(1, 3);
        
        int s = 1;
        NonrecursiveDirectedDFS dfs = new NonrecursiveDirectedDFS(g, s);
        for (int v = 0; v < g.V(); v++) {
            if (dfs.marked(v))
                System.out.print(v + " ");
        }
        System.out.println("");
    }

}