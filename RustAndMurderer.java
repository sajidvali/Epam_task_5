import java.util.Scanner;
import java.util.Arrays;
import java.util.Queue;
import java.util.ArrayDeque;

class Solution{
    static int[][] adjacency(int n, int[] x, int[] y){
        int[] count=new int[n];
        for(int i:x) ++count[i];
        for(int i:y) ++count[i];
        int[][] adj=new int[n][];
        for(int i=0;i<n;++i) adj[i]=new int[count[i]];
        for(int i=0;i<x.length;++i){
            adj[x[i]][--count[x[i]]]=y[i];
            adj[y[i]][--count[y[i]]]=x[i];
        }
        for(int i=0;i<n;++i) Arrays.sort(adj[i]);
        return adj;
    }
    static int[] distance(int s, int[][] adj){
        int n=adj.length, dist[]=new int[n];
        Queue<Integer> bfs=new ArrayDeque<>();
        bfs.add(s);
        int count=1;
        while(count!=n){
            int x=bfs.remove(),i=0;
            for(int j=0;j<n;++j){
                if(i!=adj[x].length && j==adj[x][i]) ++i;
                else if(j!=s && dist[j]==0){
                    ++count;
                    dist[j]=dist[x]+1;
                    bfs.add(j);
                }
            }
        }
        return dist;
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int t=sc.nextInt();
        while(t-- != 0){
            int n=sc.nextInt(), m=sc.nextInt();
            int[] x=new int[m], y=new int[m];
            for(int i=0;i<m;++i){
                x[i]=sc.nextInt()-1;
                y[i]=sc.nextInt()-1;
            }
            int[][] adj=adjacency(n,x,y);
            int s=sc.nextInt()-1;
            int[] dist=distance(s,adj);
            StringBuilder sb=new StringBuilder();
            for(int i=0;i<n;++i){
                if(i!=s) sb.append(dist[i]+" ");
            }
            System.out.println(sb);
        }
        sc.close();
    }
}