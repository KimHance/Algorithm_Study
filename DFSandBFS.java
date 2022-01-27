import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DFSandBFS {

	static boolean visitDFS[]; // DFS 방문확인 배열
	static boolean visitBFS[]; // BFS 방문확인 배열
	
	static int graph[][];
	
	static List<Integer> procDFS = new ArrayList<>(); //DFS 순서
	static List<Integer> procBFS = new ArrayList<>(); //BFS 순서
	
	static Queue<Integer> q = new LinkedList<>();
	
	static int V;// 정점 개수
	static Scanner sc =new Scanner(System.in); 
	
	public static void main(String[] args) {
		
		System.out.print("정점의 개수를 입력하세요 >> ");
		V = sc.nextInt(); //정점 개수 입력
		
		visitDFS = new boolean[V]; //방문 확인용 배열 생성
		visitBFS = new boolean[V];
		
		graph = new int[V][V]; //인접행렬 생성
		
		// 모두 연결x 로 초기화
		for(int i=0; i<V; i++) {
			Arrays.fill(graph[i], 0); 
		}

		
		while(true) {
			// 더이상 연결가능한지 확인
			if(isFull(graph, V)) {
				System.out.println("그래프가 모두 연결되었습니다.");
				break;
			}
			
			System.out.print("정점1 정점2 입력(-1입력시 그래프 생성 종료) >> ");
			int v1 = sc.nextInt();
			
			if(v1 == -1) { //-1 입력시 while 나옴
				break;
			}
			
			int v2 = sc.nextInt();
			
			if(v1>=V || v2>=V) {
				System.out.println("범위를 초과하였습니다. 다시 입력하세요.");
				continue;
			}
			// 이미 연결되었늕 ㅣ확인
			if(graph[v1][v2]==1 && graph[v2][v1]==1)
			{
				System.out.println("이미 연결되었습니다.");
				continue;
			}else {
				graph[v1][v2]=1;
				graph[v2][v1]=1;
			}
		}
		
		System.out.println("그래프 생성 완료\n");
		System.out.print("시작 정점 입력(0 ~ " + (V-1) +") >>");
		int i = sc.nextInt();
		
		dfs(i); //i부터 탐색 시작 DFS
		bfs(i); //i부터 탐색 시작 BFS
		
		System.out.println("DFS 방문 순서: " + procDFS);
		System.out.println("BFS 방문 순서: " + procBFS);
		
		sc.close();
		
	}
	
	// 그래프가 다 연결되었는지 확인
	public static boolean isFull(int graph[][], int V) {
		int zero = 0;
		for(int i = 0; i<V; i++) {
			for(int j =0; j<V; j++) {
				if(graph[i][j] == 0) {
					zero++;
				}
			}
		}
		
		// 0의 개수가 V개만큼이면 다 연결된거
		if(zero == V) { 
			return true;
		}else {
			return false;
		}
		
	}
	
	// DFS 재귀o
	public static void dfs(int i) // i부터 시작
	{	
		visitDFS[i] = true;
		procDFS.add(i); // 순서 추가
		
		for(int j=0; j<V ; j++) {
			if(graph[i][j] == 1 && visitDFS[j] == false) {
				dfs(j);
			}
		}
	}

	// BFS 재귀x
	public static void bfs(int i)  // i부터 시작
	{	
		visitBFS[i] = true;
		q.offer(i); //큐에 삽입
		
		while(!q.isEmpty()) { //큐가 빌 때까지
			int tmp = q.poll(); //큐에서 뺌(선입 선출) 실패시 null
			procBFS.add(tmp); //순서 추가
			
			for(int j=0; j<V; j++) {
				if(graph[tmp][j] == 1 && visitBFS[j] == false) { //연결된 모든 정점 확인
					q.offer(j); //큐에 삽입
					visitBFS[j] = true;
				}
			}
			
		}
	} 
}
