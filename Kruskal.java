import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	
	public static int[] parent; //부모노드 저장하는 배열
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weightSum = 0; // 가중치 합
		int count = 0; // 연결된 간선 수
		int V, E; // 정점과 간선
		
		System.out.print("노드의 개수를 입력해 주세요 >> ");
		V = sc.nextInt(); //노드의 개수
		
		System.out.print("간선의 개수를 입력해 주세요 >> ");
		E = sc.nextInt(); //간선의 개수
				
		parent = new int[V+1]; //1번부터 인덱스를 사용하기 위해
		
		// 입력받은 간선 개수만큼 가중치를 입력받기 위한 배열 생성
		int graph[][] = new int[E][3];
		
		// 간선과 가중치 입력받음
		for (int i = 0; i < E; i++) {
			System.out.print("정점1 정점2 가중치 입력 >> ");
			graph[i][0] = sc.nextInt();	// 정점1
			graph[i][1] = sc.nextInt();	// 정점2
			graph[i][2] = sc.nextInt();	// 가중치
		}
		
		// 오름차순으로 가중치 정렬
		Arrays.sort(graph, new Comparator<int []>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				// 인덱스 2가 가중치이므로 인덱스2 기준으로 정렬
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		// 자기 자신을 자신의 부모노드로 설정
		for (int i = 1; i < V ; i++) {
			parent[i] = i; 
		}
		
		// 간선의수만큼 반복
		for(int i = 0; i < graph.length; i++) {
			//먼저 정점간 연결될 수 있는지 union을 통해 판단
			if(union(graph[i][0], graph[i][1])) {
				weightSum += graph[i][2]; // 가중치 합
				count++;
				System.out.println(graph[i][0] + "노드와 " + graph[i][1] + "노드가 연결됨");
			}
			//간선의 수가 정점-1 만큼이면 모든 정점이 연결되었으므로 종료됨
			if(count == V-1) {break;}
		}
		sc.close();
		
		System.out.println("연결된 간선의 가중치는 "+weightSum+" 입니다.");
	}
	
	
	public static int find(int x) {
		if(x==parent[x]) return x; // 초기화된 상태면 자기 사진이 부모
		parent[x]= find(parent[x]); // find 할 때마다 부모노드가 최상의 부모노드로 설정됨
		return parent[x]; 
	}
	
	
	// cycle 없이 연결여부 판단
	// 두 정점의 root가 같은 상태로 연결되면 cycle이 형성된것
	// 두 정점의 root가 다른 상태로 연결되면 cycle없이 연결됨
	public static boolean union(int v1, int v2) {
		int v1Root = find(v1); //v1의 root
		int v2Root = find(v2); //v2의 root
		
		// 정점의 root가 같지 않으면 cycle 생성 x
		if(v1Root != v2Root) {
			parent[v2Root]=v1Root; // v2의 root를 v1의 root로 바꿈
			return true; // cycle 없이 연결됨
		}else {
			return false; // cycle이 생성되어 연결이 안됨
		}
		
	}

}
