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
// Time Complexity: O(V^2) where V is the number of vertices in the graph.
// Space Complexity: O(V^2) where V is the number of vertices in the graph.
// Sample Input/Output:
// Enter the number of vertices :
// 5
// Enter the number of edges:
// 7
// Enter the vertex 1:
// 0
// Enter the vertex 2:
// 1
// Enter the weight of the edge:
// 2
// Enter the vertex 1:
// 0
// Enter the vertex 2:
// 3
// Enter the weight of the edge:
// 3
// Enter the vertex 1:  
// 1
// Enter the vertex 2:  
// 2
// Enter the weight of the edge:
// 1
// Enter the vertex 1:
// 1
// Enter the vertex 2:
// 3    
// Enter the weight of the edge:
// 4
// Enter the vertex 1:
// 1
// Enter the vertex 2:
// 4
// Enter the weight of the edge:
// 5    
// Enter the vertex 1:
// 2
// Enter the vertex 2:
// 3
// Enter the weight of the edge:
// 6
// Enter the vertex 1:
// 3
// Enter the vertex 2:
// 4    
// Enter the weight of the edge:
// 7
// Edge 	Weight
// 0 - 1	2
// 1 - 2	1
// 0 - 3	3
// 1 - 4	5
// Total Weight: 11
//
// Note: The input graph is represented as an adjacency matrix. The weight of the edge between vertices v1 and v2 is stored in graph[v1][v2] and graph[v2][v1]. The weight of the edge is -1 if there is no edge between the vertices.  
// The output displays the edges in the Minimum Spanning Tree (MST) along with their weights and the total weight of the MST.
// The above code demonstrates the implementation of Prim's algorithm to find the Minimum Spanning Tree (MST) of a weighted undirected graph. The algorithm starts with an empty MST and repeatedly adds the edge with the minimum weight that connects a vertex in the MST to a vertex outside the MST. The process continues until all vertices are included in the MST. The algorithm uses an array to track the minimum edge weight for each vertex and another array to mark whether the vertex is in the MST. It also maintains an array to store the parent of each vertex in the MST. The time complexity of the algorithm is O(V^2), where V is the number of vertices in the graph. The space complexity is also O(V^2) due to the adjacency matrix representation of the graph. The code takes input for the number of vertices and edges in the graph, as well as the weight of each edge. It then computes and prints the edges in the MST along with their weights and the total weight of the MST.// Path: Algorithms/prims.java
