import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class dfsAlgo {

    // Complete the roadsAndLibraries function below.
    static long roadsAndLibraries(int n, int c_lib, int c_road, int[][] cities) {
     Long c_libb=new Long(c_lib);
     Long c_roads=new Long(c_road);   
/*   Adjacency matrix  
    int adj[][]=new int[n+1][n+1];
    for(int i=0;i<cities.length;i++){
            adj[cities[i][0]][cities[i][1]]=1;
            adj[cities[i][1]][cities[i][0]]=1;
    }*/

    //creating an adjancecy list for storing the roads or paths
    LinkedList<Integer> adj[];
    adj=new LinkedList[n+1];
    for(int i=1;i<=n;i++){
        adj[i]=new LinkedList();
    }
    for(int i=0;i<cities.length;i++){
        adj[cities[i][0]].add(cities[i][1]);
        adj[cities[i][1]].add(cities[i][0]);
    }
    //boolean array for keep track of visisted elements
    boolean visited[] = new boolean[n+1];
    for(int i=1;i<=n;i++){
        visited[i]=false;
    }
    //if road cost is greater than library cost we should place all cities with libraries
    if(c_roads>=c_libb ){
        return (long)c_libb*n;
    }
    else{
        int c=0;
        for(int i=1;i<=n;i++){
            if(!visited[i]){
            dfs(i,adj,visited);
            c=c+1;}
        }

        Long l=new Long((n-c)*c_roads+(c_libb*c));
        return l;
    }

    }
    //using depth first search for calculating the components
    static void dfs(int v,LinkedList<Integer> adj[],boolean visited[]){
        visited[v]=true;
        Iterator<Integer> itr = adj[v].listIterator();
        while(itr.hasNext()){
            int val=itr.next();
            if(!visited[val])
                dfs(val,adj,visited);
        } 
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
