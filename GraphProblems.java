package graphproblems;

public class GraphProblems {

    public static void main(String[] args) {
        Graph g = new Graph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(0, 2);

        System.out.println(g.vertices + " vertices, " + g.edges + " edges.");
        g.graphTraverse();
        
    }

}
