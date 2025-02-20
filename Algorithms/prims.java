import java.util.*;

public class Prims {

    public static void primMST(int[][] graph, int V) {
        int[] key = new int[V]; // Array to track the minimum edge weight for each vertex
        boolean[] inMST = new boolean[V]; // Array to track whether the vertex is in the MST
        int[] parent = new int[V]; // Array to store the parent of each vertex

        // Initialize all keys as infinite and mark all vertices as not in MST
        Arrays.fill(key, Integer.MAX_VALUE);
        Arrays.fill(inMST, false);

        // Start with the first vertex
        key[0] = 0;
        parent[0] = -1; // First vertex has no parent

        for (int i = 0; i < V - 1; i++) {
            // Pick the minimum key vertex that is not yet included in the MST
            int minv = -1;

            for (int j = 0; j < V; j++) {
                if (!inMST[j] && (minv == -1 || key[j] < key[minv])) {
                    minv = j;
                }
            }

            // Include the picked vertex in the MST
            inMST[minv] = true;

            // Update the key and parent arrays for the adjacent vertices
            for (int v = 0; v < V; v++) {
                if (graph[minv][v] != -1 && !inMST[v] && graph[minv][v] < key[v]) {
                    key[v] = graph[minv][v];
                    parent[v] = minv;
                }
            }
        }

        // Print the MST
        System.out.println("Edge \tWeight");
        int totalWeight = 0;
        for (int i = 1; i < V; i++) {
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }
        System.out.println("Total Weight: " + totalWeight);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of vertices :");
        int v = sc.nextInt();

        System.out.println("Enter the number of edges:");
        int e = sc.nextInt();

        int[][] graph = new int[v][v];

        // Initializing the graph with -1 to indicate no edge
        for (int i = 0; i < v; i++) {
            Arrays.fill(graph[i], -1);
        }

        for (int i = 0; i < e; i++) {
            System.out.println("Enter the vertex 1:");
            int v1 = sc.nextInt();
            System.out.println("Enter the vertex 2:");
            int v2 = sc.nextInt();
            System.out.println("Enter the weight of the edge:");
            int wt = sc.nextInt();

            graph[v1][v2] = wt;
            graph[v2][v1] = wt;
        }

        primMST(graph, v);
    }
}