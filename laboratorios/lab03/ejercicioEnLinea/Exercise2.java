import java.util.ArrayList;
import java.util.LinkedList;

/**
 *
 * Solution of point 2
 * @author Camila Barona - Felipe Sosa
 */
public class Exercise2 {

    public static int point2(Digraph g, int o) {
        boolean[] visit = new boolean[g.size()];
        int vertices = 0;
        int[] cost = new int[1];
        cost[0] = Integer.MAX_VALUE;
        int size = 0;
        point2Aux(g, o, o, visit, cost, size, vertices);
        return cost[0] == Integer.MAX_VALUE ? 0 : cost[o];
    }

    public static boolean point2Aux(Digraph g, int o, int d, boolean[] visit, int[] cost, int size, int vertices) {

        System.out.println("Size: " + size);
        System.out.println("Cost: " + cost[0]);
        System.out.println("Vertex: " + vertices);
        System.out.println("G: " + g.size());

        if (o == d && g.size() == vertices) {
            if (size < cost[0]) {
                cost[0] = size;
            }
            visit[o] = false;
            return true;
        }

        visit[o] = true;
        vertices++;
        return false;
    }

    private static int minCost(Digraph g, int o, int d, boolean[] visited) {
        visited[o] = true;
        int minCost = Integer.MAX_VALUE;
        int roadCost = 0;
        if (o == d) {
            return minCost;
        } else {
            ArrayList<Integer> branches = g.getSuccessors(o);
            for (Integer brach : branches) {
                if (!visited[brach]) {
                    visited[brach] = true;
                    int re = minCost(g, brach, d, visited);
                    if (re == Integer.MAX_VALUE) {
                        roadCost = re;
                    } else {
                        roadCost = g.getWeight(o, d) + re;
                    }
                    if (roadCost < minCost) {
                        minCost = roadCost;
                    }
                }
            }
            return minCost;
        }
    }
}