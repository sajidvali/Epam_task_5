import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class RoadsAndLibraries {

    static int count;
    
    static void dfs(ArrayList<ArrayList<Integer>> path, int n, int s, boolean[] visited){
        visited[s] = true;
        count++;
        for(Integer i : path.get(s)){
            if(!visited[i])
                dfs(path,n,i,visited);
        }
    }

    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
        // if(c_lib<c_road)
        //     return n*c_lib;
        boolean[] visited = new boolean[n+1];
        ArrayList<ArrayList<Integer>> path = new ArrayList<ArrayList<Integer>>();
        for(int i=0;i<=n;i++)
            path.add(new ArrayList<Integer>());
        for(int i=0;i<cities.length;i++){
            path.get(cities[i][0]).add(cities[i][1]);
            path.get(cities[i][1]).add(cities[i][0]);
        }
        System.out.println(path);
        long cost=0;
        long c1=0;
        for(int i=1;i<=n;i++){
            count = 0;
            if(!visited[i]){
                dfs(path,n,i,visited);
                c1 = c_road*(count-1) + c_lib;
                if(c1>c_lib*count)
                    cost += c_lib*count;
                else
                    cost += c1;
            }
        }
        return cost;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String[] nmC_libC_road = scanner.nextLine().split(" ");

            int n = Integer.parseInt(nmC_libC_road[0]);

            int m = Integer.parseInt(nmC_libC_road[1]);

            int c_lib = Integer.parseInt(nmC_libC_road[2]);

            int c_road = Integer.parseInt(nmC_libC_road[3]);

            int[][] cities = new int[m][2];

            for (int i = 0; i < m; i++) {
                String[] citiesRowItems = scanner.nextLine().split(" ");
                scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

                for (int j = 0; j < 2; j++) {
                    int citiesItem = Integer.parseInt(citiesRowItems[j]);
                    cities[i][j] = citiesItem;
                }
            }

            long result = roadsAndLibraries(n, c_lib, c_road, cities);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}