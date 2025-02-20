import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public Edge(int src, int dest, int weight) {
        this.src = src;
        this.dest = dest;
        this.weight = weight;
    }

    public int compareTo(Edge compareEdge) {
        return this.weight - compareEdge.weight; // Sorting edges by weight
    }
}

class Subset {
    int parent, rank;
}

public class KruskalAlgorithm {
    
    int V, E; 
    Edge[] edges;

    public KruskalAlgorithm(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
    }

    int find(Subset subsets[], int i) {
        if (subsets[i].parent != i)
            subsets[i].parent = find(subsets, subsets[i].parent);
        return subsets[i].parent;
    }

    void union(Subset subsets[], int x, int y) {
        int rootX = find(subsets, x);
        int rootY = find(subsets, y);

        if (subsets[rootX].rank < subsets[rootY].rank)
            subsets[rootX].parent = rootY;
        else if (subsets[rootX].rank > subsets[rootY].rank)
            subsets[rootY].parent = rootX;
        else {
            subsets[rootY].parent = rootX;
            subsets[rootX].rank++;
        }
    }

    void kruskalMST() {
        Edge[] result = new Edge[V - 1]; 
        int e = 0; 
        int i = 0; 
        Arrays.sort(edges);

        Subset subsets[] = new Subset[V];
        for (int v = 0; v < V; v++) {
            subsets[v] = new Subset();
            subsets[v].parent = v;
            subsets[v].rank = 0;
        }

        while (e < V - 1) {
            Edge nextEdge = edges[i++]; 

            int x = find(subsets, nextEdge.src);
            int y = find(subsets, nextEdge.dest);

            if (x != y) { 
                result[e++] = nextEdge;
                union(subsets, x, y);
            }
        }

        System.out.println("Edges in MST:");
        for (i = 0; i < e; i++)
            System.out.println(result[i].src + " - " + result[i].dest + " : " + result[i].weight);
    }

    public static void main(String[] args) {
        int V = 4, E = 5;
        KruskalAlgorithm graph = new KruskalAlgorithm(V, E);

        graph.edges[0] = new Edge(0, 1, 10);
        graph.edges[1] = new Edge(0, 2, 6);
        graph.edges[2] = new Edge(0, 3, 5);
        graph.edges[3] = new Edge(1, 3, 15);
        graph.edges[4] = new Edge(2, 3, 4);

        graph.kruskalMST();
    }
}
