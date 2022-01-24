import java.util.Arrays;
import java.util.Scanner;

public class Prim {
	
	static boolean visit[];
	static final int MAX_VALUE = 100; //무한대를 편의상 100으로 설정했음
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weightSum = 0; // 가중치 합
		int count = 0; // Prim은 정점을 기준으로 하기 때문에 선택된 정점 갯수
		int V, E; // 정점과 간선
		int D[]; // 최소 거리 담는 배열

		System.out.print("정점의 개수를 입력해 주세요 >> ");
		V = sc.nextInt(); // 정점의 개수
		
		System.out.print("간선의 개수를 입력해 주세요 >> ");
		E = sc.nextInt(); // 간선의 개수
		
		// 방문 배열 
		visit = new boolean[V];
		
		// 입력받은 간선 개수만큼 가중치를 입력받기 위한 배열 생성
		int graph[][] = new int[V][V];
				
		// 간선과 가중치 입력받음
		// 인접행렬 구성
		for (int i = 0; i < E; i++) {
			System.out.print("정점1 정점2 가중치 입력 >> ");
			int v1 = sc.nextInt() - 1;
			int v2 = sc.nextInt() - 1;
			int w = sc.nextInt();
			graph[v1][v2] = w;
			graph[v2][v1] = w; 
		}
		
		D = new int[V]; // 선택된 정점들로부터 가능한 최소거리 저장 배열
		Arrays.fill(D,MAX_VALUE); // 아직 거리를 모르므로 최대값으로 설정
		D[0] = 0; // 첫 번째 정점은 거리가 0
		
		while(true) {
			int min = MAX_VALUE; // 비교할 최소값을 처음에 무한대(이 코드에선 100)로 설정
			int index = 0; //인덱스 저장 변수
			
			for(int i =0; i < V; i++) {
				if( !visit[i] && (D[i] < min) ) { 
					min=D[i]; //최소값 바꿔줌
					index = i;
				}
			}
			
			visit[index]=true; // 선택된 정점은 방문했다고 바꿈
			weightSum += min; // 가중치 더해짐
			System.out.println((index+1)+"정점을 방문함(거리="+D[index]+", 총합="+weightSum+")");
			count++; // 선택된 정점 개수 증가
			
			if(count == V) break; //선택된 정점의 갯수가 정점갯수와 같아지면 종료
			
			// 선택된 정점을 포함하여 다른 정점의 간선정보를 갱신
			for(int i = 0; i < V ; i++) {
				//i정점을 방문하지 않았고, index 정점에서 i정점까지 의 거리가 저장된 최소값보다 작고
				//i정점과 index 정점이 연결되어 있으면
				if(!visit[i] && (graph[index][i] < D[i]) && graph[index][i] > 0 ) {  
					D[i] = graph[index][i]; // index 노드와 i 노드의 가중치를 최소값으로 설정
				}
			}
		}
		
		System.out.println("간선의 총합은 "+weightSum+"입니다.");
	}
}


/* 우선순위 큐로 구현시 사용
 * class Vertex { int from ,to , weight; public Vertex(int f, int t ,int w) {
 * this.from = f; this.to = t; this.weight = w; } }
 */