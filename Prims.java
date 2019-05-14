import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Node {
    int vertex;
    int weight;
    Node(int v, int w){
        vertex = v;
        weight = w;
    }
}

public class Prims {

    static int minCostVertex(boolean[] visited, int[] dist, int n){
        int minCost = Integer.MAX_VALUE;
        int min_index = -1;
        for(int i=1;i<=n;i++){
            if(!visited[i] && dist[i]<minCost){
                minCost = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }

    static int prims(int n, int[][] edges, int start) {
        ArrayList<ArrayList<Node>> path = new ArrayList<ArrayList<Node>>();
        for(int i=0;i<=n;i++)
            path.add(new ArrayList<Node>());
        for(int i=0;i<edges.length;i++){
            path.get(edges[i][0]).add(new Node(edges[i][1], edges[i][2]));
            path.get(edges[i][1]).add(new Node(edges[i][0], edges[i][2]));
        }
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        int[] parent = new int[n+1];
        for(int i=0;i<n+1;i++)
            dist[i] = Integer.MAX_VALUE;
        visited[start] = true;
        dist[start] = 0;
        parent[start] = -1;
        int cost = 0;
        for(int i=0;i<path.get(start).size();i++)
            dist[path.get(start).get(i).vertex] = path.get(start).get(i).weight;
        for(int i=0;i<n-1;i++){
            int u = minCostVertex(visited, dist, n);
            visited[u] = true;
            cost+=dist[u];
            for(int j=0;j<path.get(u).size();j++){
                Node temp = path.get(u).get(j);
                if(!visited[temp.vertex] && temp.weight<dist[temp.vertex]){
                    dist[temp.vertex] = temp.weight;
                    parent[temp.vertex] = u;
                }
            }
        } 
        // for(int i=1;i<=n;i++)
        //     System.out.print(cost[i]+" ");
        return cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] edges = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] edgesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int edgesItem = Integer.parseInt(edgesRowItems[j]);
                edges[i][j] = edgesItem;
            }
        }

        int start = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int result = prims(n, edges, start);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}