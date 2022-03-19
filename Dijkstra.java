import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstra { //인접 행렬 방식
	
	static Scanner sc =new Scanner(System.in); 
	static int graph[][];  //인접행렬용 그래프
	static int V, E; // 정점과 간선
	static int D[]; // 최소 거리 담는 배열
	static int start ; // 시작 정점
	static List<Integer> proc = new ArrayList<>(); //DFS 순서
	
	public static void main(String[] args) {
		/*
		 * System.out.print("정점의 개수를 입력해 주세요 >> "); V = sc.nextInt(); // 정점의 개수
		 * 
		 * System.out.print("간선의 개수를 입력해 주세요 >> "); E = sc.nextInt(); // 간선의 개수
		 * 
		 * //방문 배열 생성 boolean visit[] =new boolean[V];
		 * 
		 * // 그래프&거리 배열 생성 graph =new int[V][V]; D = new int[V];
		 * 
		 * // 그래프& 거리 배열 초기화 for(int i=0; i<V; i++) { Arrays.fill(graph[i],
		 * Integer.MAX_VALUE); } Arrays.fill(D, Integer.MAX_VALUE);
		 * 
		 * // 간선과 가중치 입력받음 // 인접행렬 구성 for (int i = 0; i < E; i++) {
		 * System.out.print("정점1 정점2 가중치 입력 >> "); int v1 = sc.nextInt(); int v2 =
		 * sc.nextInt(); int w = sc.nextInt(); graph[v1][v2] = w; graph[v2][v1] = w; }
		 * System.out.println("그래프 생성 완료\n");
		 * 
		 * System.out.print("시작할 정점 입력 >> "); start = sc.nextInt();
		 * 
		 * // 시작 정점값 초기화 D[start]=0; visit[start]=true;
		 * System.out.println("\n"+start+"정점에서 시작합니다");
		 * 
		 * //다익스트라 알고리즘(인접행렬) for(int i=0; i<V; i++) { int min = Integer.MAX_VALUE; // 최소 비교하기
		 * 위해 int index = 0; // 인덱스 저장 변수
		 * 
		 * for(int j=0; j<V; j++) { if(!visit[j] && (D[j] < min)) { // 방문x 정점 중 최소비용 찾기
		 * min=D[i]; //최소값 바꿔줌 index = j; } } // 최소비용 정점 방문 visit[index]=true;
		 * proc.add(index); System.out.println("방문 = "+proc);
		 * 
		 * 
		 * // 해당 정점 기준 인접노드들 비용 확인 for(int k=0; k<V; k++) { if(!visit[k] &&
		 * (graph[index][k] != Integer.MAX_VALUE) ) { // 비용을 확인 가능하고 방문x 인접노드 중에서
		 * if(D[index] + graph[index][k] < D[k]) { //해당 노드를 거쳐가는게 빠르면 D[k] = D[index] +
		 * graph[index][k]; // 그 노드까지의 비용을 갱신함 } } }
		 * 
		 * // 출력용 for(int p =0; p<V; p++) { if(D[p]==Integer.MAX_VALUE) {
		 * System.out.println("정점"+p+" 까지의 거리 = ∞"); }else {
		 * System.out.println("정점"+p+" 까지의 거리 = "+D[p]); } }
		 * System.out.println("------------------------\n\n"); }
		 * 
		 * sc.close();
		 */
		//우선순위 큐 구현------------------------------------------------------------------------------------//		
	
		PriorityQueue<Node> queue =new PriorityQueue<>();// 노드거리 저장 우선순위 큐 
	
		System.out.print("정점의 개수를 입력해 주세요 >> "); 
		V = sc.nextInt(); // 정점의 개수
		 
		System.out.print("간선의 개수를 입력해 주세요 >> "); 
		E = sc.nextInt(); // 간선의 개수
	
		// 그래프 생성 
		graph = new int[V][V]; 
		D = new int[V];
		
		//방문 배열 생성 
		boolean visit[] = new boolean[V];
		
		// 그래프 초기화 
		for(int i=0; i<V; i++) { 
			Arrays.fill(graph[i],Integer.MAX_VALUE); 
		}
		Arrays.fill(D, Integer.MAX_VALUE);

		// 간선과 가중치 입력받음
		for (int i = 0; i < E; i++) {
			System.out.print("정점1 정점2 가중치 입력 >> "); 
			int v1 = sc.nextInt(); 
			int v2 = sc.nextInt(); 
			int w = sc.nextInt(); 
			graph[v1][v2] = w; 
			graph[v2][v1] = w; 
		}
		System.out.println("그래프 생성 완료\n");
		
		System.out.print("시작할 정점 입력 >> "); 
		start = sc.nextInt();
	
		//시작 노드 초기화.
		queue.add(new Node(start, 0));
		D[start]=0;
		visit[start]=true;
		
		System.out.println("\n"+start+"정점에서 시작합니다");
		
		//다익스트라 알고리즘(우선순위 큐)
		while(!queue.isEmpty()) { //큐가 비어있을 때까지
			//노드 최소값 꺼냄
			
			Node qurrentNode = queue.poll();
			int min = qurrentNode.weight;
			int index = qurrentNode.index;
			
			// 원래는 큐가 빌 때까지 출력해서 더 출력됨
			// 이 코드 추가시 인접행렬과 같은 진행상황만 출력 가능
			if(D[index]<min) {continue;} 
			visit[index] = true;
			
			//다른 노드를 거쳐가는게 비용이 더 적은지 확인
			for(int i=0; i<V; i++) {
				if(!visit[i] && (graph[index][i] < Integer.MAX_VALUE )) {
					if(D[index] + graph[index][i]<D[i]) {
						D[i] = D[index] + graph[index][i];
						queue.add(new Node(i, D[i]));
					}
				}
			}
		
			for(int p =0; p<V; p++) {
				if(D[p]==Integer.MAX_VALUE) {
					System.out.println("정점"+p+" 까지의 거리 = ∞");
				}else {
					System.out.println("정점"+p+" 까지의 거리 = "+D[p]);
				}
			}
			System.out.println("------------------------\n\n");
		}
	}

	static class Node implements Comparable<Node>{
		int index, weight; //다음 인덱스, 비용
		public Node(int i, int w){
			this.index =i;
			this.weight=w;
		}
		@Override
		public int compareTo(Node node) {
			return Integer.compare(this.weight, node.weight);
		}
	}
	
}
