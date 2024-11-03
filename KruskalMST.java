package depthfirstpaths;

import java.util.ArrayDeque;
import java.util.*;

public class KruskalMST {

    private double weight;                         // weight of MST
    private Deque<Edge> mst = new ArrayDeque<>();  // edges in MST

    /**
     * Compute a minimum spanning tree (or forest) of an edge-weighted graph.
     *
     * @param G the edge-weighted graph
     */
    public KruskalMST(EdgeWeightedGraph G) {

        // create array of edges, sorted by weight
        Edge[] edges = new Edge[G.E()];
        int t = 0;
        for (Edge e : G.edges()) {
            edges[t++] = e;
        }
        Arrays.sort(edges);

        // run greedy algorithm
        UF uf = new UF(G.V());
        for (int i = 0; i < G.E() && mst.size() < G.V() - 1; i++) {
            Edge e = edges[i];
            int v = e.either();
            int w = e.other(v);

            // v-w does not create a cycle
            if (uf.find(v) != uf.find(w)) {
                uf.union(v, w);     // merge v and w components
                mst.addLast(e);     // add edge e to mst
                weight += e.weight();
            }
        }
    }

    /**
     * Returns the edges in a minimum spanning tree (or forest).
     *
     * @return the edges in a minimum spanning tree (or forest) as an iterable
     * of edges
     */
    public Iterable<Edge> edges() {
        return mst;
    }

    /**
     * Returns the sum of the edge weights in a minimum spanning tree (or
     * forest).
     *
     * @return the sum of the edge weights in a minimum spanning tree (or
     * forest)
     */
    public double weight() {
        return weight;
    }

    /**
     * Unit tests the {@code KruskalMST} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        EdgeWeightedGraph g = new EdgeWeightedGraph(4);
        Edge e1 = new Edge(0, 1, 10);
        Edge e2 = new Edge(0, 2, 6);
        Edge e3 = new Edge(0, 3, 5);
        Edge e4 = new Edge(1, 3, 15);
        Edge e5 = new Edge(2, 3, 4);

        g.addEdge(e1);
        g.addEdge(e2);
        g.addEdge(e3);
        g.addEdge(e4);
        g.addEdge(e5);

        KruskalMST mst = new KruskalMST(g);
        for (Edge e : mst.edges()) {
            System.out.println(e);
        }
        System.out.println(mst.weight());
    }

}
