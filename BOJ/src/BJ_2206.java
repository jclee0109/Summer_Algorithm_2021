import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BJ_2206 {
    static int N,M;
    static boolean[][] visited;
    static char[][] map;
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input_NM = br.readLine().split(" ");
        M = Integer.parseInt(input_NM[0]);
        N = Integer.parseInt(input_NM[1]);
        map = new char[M][N];
        for(int i = 0; i<M;i++){
            map[i] = br.readLine().toCharArray();
        }
        visited = new boolean[M][N];
        for(int i = 0; i<M;i++){
            for(int j = 0; j<N;j++){
                visited[i][j] = false;
            }
        }
        int min = bfs();
        for(int i = 0; i<M;i++){
            for(int j = 0; j<N;j++){
                int curr;
                if(map[i][j] == '1') {
                    map[i][j] = '0';
                    curr = bfs();
                    map[i][j] = '1';
                    if(curr < min) min = curr;
                }
            }
        }
        if(min == Integer.MAX_VALUE) min=-1;
        System.out.println(min);
    }

    public static int bfs(){
        for(int i = 0; i<M;i++){
            for(int j = 0; j<N;j++){
                visited[i][j] = false;
            }
        }
        int result = Integer.MAX_VALUE;
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0,0,1));
        while(!q.isEmpty()){
            Node curr = q.poll();
            visited[curr.row][curr.col] = true;
            if (0<=curr.row+1 && curr.row+1 < M && !visited[curr.row+1][curr.col]) {
                if(map[curr.row+1][curr.col]=='0'){

                }
                q.add(new Node(curr.row+1, curr.col, curr.depth + 1));
            }
            //move down
            if (0<=curr.col+1 && curr.col+1 < N && !visited[curr.row][curr.col+1]) {
                if(map[curr.row+1][curr.col]=='0'){

                }
                q.add(new Node(curr.row, curr.col+1, curr.depth + 1));
            }
            //move left
            if (0<=curr.row-1 && curr.row-1 < M&&!visited[curr.row-1][curr.col]){
                if(map[curr.row+1][curr.col]=='0'){

                }
                q.add(new Node(curr.row-1, curr.col, curr.depth + 1));
            }
            //move up
            if (0<=curr.col-1 && curr.col-1 < N && !visited[curr.row][curr.col-1] && map[curr.row][curr.col-1]=='0') {
                q.add(new Node(curr.row, curr.col-1, curr.depth + 1));
            }
            if (visited[M-1][N-1]) {
                result = curr.depth;
                break;
            }
        }//queue is empty -> bfs completed
        return result;
    }
}

class Node{
    int row;
    int col;
    int depth;
    int drill;
    Node(int row, int col, int depth, int drill){
        this.row = row;
        this.col = col;
        this.depth = depth;
        this.drill = drill;
    }
}