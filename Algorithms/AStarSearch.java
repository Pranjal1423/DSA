import java.util.*;

class Node implements Comparable<Node> {
    int x, y;  // Coordinates in the grid
    int g, h;  // g = cost from start, h = heuristic (estimated cost to goal)
    Node parent;  // To trace back the path

    public Node(int x, int y, Node parent, int g, int h) {
        this.x = x;
        this.y = y;
        this.g = g;
        this.h = h;
        this.parent = parent;
    }

    public int getF() {
        return g + h;  // f = g + h
    }

    @Override
    public int compareTo(Node other) {
        return Integer.compare(this.getF(), other.getF());
    }
}

class AStar {
    static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}}; // Right, Down, Left, Up
    int[][] grid;  // 0 = walkable, 1 = obstacle
    int rows, cols;

    public AStar(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
    }

    int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2); // Manhattan distance
    }

    List<Node> findPath(int startX, int startY, int goalX, int goalY) {
        PriorityQueue<Node> openSet = new PriorityQueue<>();
        boolean[][] closedSet = new boolean[rows][cols];

        openSet.add(new Node(startX, startY, null, 0, heuristic(startX, startY, goalX, goalY)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalX && current.y == goalY) {
                return reconstructPath(current);
            }

            closedSet[current.x][current.y] = true;

            for (int[] dir : DIRECTIONS) {
                int newX = current.x + dir[0];
                int newY = current.y + dir[1];

                if (isValid(newX, newY, closedSet)) {
                    int gNew = current.g + 1;
                    int hNew = heuristic(newX, newY, goalX, goalY);
                    openSet.add(new Node(newX, newY, current, gNew, hNew));
                }
            }
        }
        return null;  // No path found
    }

    boolean isValid(int x, int y, boolean[][] closedSet) {
        return x >= 0 && x < rows && y >= 0 && y < cols && grid[x][y] == 0 && !closedSet[x][y];
    }

    List<Node> reconstructPath(Node node) {
        List<Node> path = new ArrayList<>();
        while (node != null) {
            path.add(node);
            node = node.parent;
        }
        Collections.reverse(path);
        return path;
    }
}

// Main method without a separate class
public class AStarSearch {
    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 1, 0},
            {1, 1, 0, 1, 0},
            {0, 0, 0, 0, 0}
        };

        AStar aStar = new AStar(grid);
        List<Node> path = aStar.findPath(0, 0, 4, 4);

        if (path != null) {
            System.out.println("Path found:");
            for (Node node : path) {
                System.out.println("(" + node.x + ", " + node.y + ")");
            }
        } else {
            System.out.println("No path found.");
        }
    }
}

// Output:
// Path found:
// (0, 0)
// (1, 0)
// (2, 0)
// (3, 0)
// (4, 0)
// (4, 1)
// (4, 2)
// (4, 3)
// (4, 4)
```
