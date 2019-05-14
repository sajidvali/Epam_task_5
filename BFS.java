import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Node {
    int vertex;
    int steps;
    Node(int v, int s){
        vertex = v;
        steps = s;
    }
}

public class BFS {

    // Complete the bfs function below.
    static int[] bfs(int n, int m, int[][] edges, int s) {
        ArrayList<ArrayList<Integer>> cost = new ArrayList<ArrayList<Integer>>();
        int[] sol = new int[n];
        int[] res = new int[n-1];
        boolean[] visited = new boolean[n+1];
        for(int i=0;i<=n;i++)
            cost.add(new ArrayList<Integer>());
        for(int i=0;i<m;i++){
            cost.get(edges[i][0]).add(edges[i][1]);
            cost.get(edges[i][1]).add(edges[i][0]);
        }
        Queue<Node> Q = new LinkedList<Node>();
        Q.add(new Node(s,0));
        visited[s] = true;
        while(Q.size()>0){
            Node curr = Q.poll();
            sol[curr.vertex-1] = curr.steps*6;
            for(int i=0;i<cost.get(curr.vertex).size();i++){
                int temp = cost.get(curr.vertex).get(i);
                if(!visited[temp]){
                    Q.add(new Node(temp, curr.steps+1));
                    visited[temp] = true;
                }
            }
        }
        int k=0;
        // for(int i=0;i<n-1;i++)
        //     res[i] = -1;
        for(int i=0;i<n;i++){
            if(i!=s-1)
                res[k++] = sol[i];
        }
        for(int i=0;i<n-1;i++)
            if(res[i]==0)
                res[i] = -1;
        return res;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nm = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nm[0]);

            int m = Integer.parseInt(nm[1]);

            int[][] edges = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] edgesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int edgesItem = Integer.parseInt(edgesRowItems[j]);
                    edges[i][j] = edgesItem;
                }
            }

            int s = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] result = bfs(n, m, edges, s);

            for (int i = 0; i < result.length; i++) {
                bufferedWriter.write(String.valueOf(result[i]));

                if (i != result.length - 1) {
                    bufferedWriter.write(" ");
                }
            }

            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}