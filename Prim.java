import java.util.Arrays;
import java.util.Scanner;

public class Prim {
	
	static boolean visit[];
	static final int MAX_VALUE = 100; //���Ѵ븦 ���ǻ� 100���� ��������
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weightSum = 0; // ����ġ ��
		int count = 0; // Prim�� ������ �������� �ϱ� ������ ���õ� ���� ����
		int V, E; // ������ ����
		int D[]; // �ּ� �Ÿ� ��� �迭

		System.out.print("������ ������ �Է��� �ּ��� >> ");
		V = sc.nextInt(); // ������ ����
		
		System.out.print("������ ������ �Է��� �ּ��� >> ");
		E = sc.nextInt(); // ������ ����
		
		// �湮 �迭 
		visit = new boolean[V];
		
		// �Է¹��� ���� ������ŭ ����ġ�� �Է¹ޱ� ���� �迭 ����
		int graph[][] = new int[V][V];
				
		// ������ ����ġ �Է¹���
		// ������� ����
		for (int i = 0; i < E; i++) {
			System.out.print("����1 ����2 ����ġ �Է� >> ");
			int v1 = sc.nextInt() - 1;
			int v2 = sc.nextInt() - 1;
			int w = sc.nextInt();
			graph[v1][v2] = w;
			graph[v2][v1] = w; 
		}
		
		D = new int[V]; // ���õ� ������κ��� ������ �ּҰŸ� ���� �迭
		Arrays.fill(D,MAX_VALUE); // ���� �Ÿ��� �𸣹Ƿ� �ִ밪���� ����
		D[0] = 0; // ù ��° ������ �Ÿ��� 0
		
		while(true) {
			int min = MAX_VALUE; // ���� �ּҰ��� ó���� ���Ѵ�(�� �ڵ忡�� 100)�� ����
			int index = 0; //�ε��� ���� ����
			
			for(int i =0; i < V; i++) {
				if( !visit[i] && (D[i] < min) ) { 
					min=D[i]; //�ּҰ� �ٲ���
					index = i;
				}
			}
			
			visit[index]=true; // ���õ� ������ �湮�ߴٰ� �ٲ�
			weightSum += min; // ����ġ ������
			System.out.println((index+1)+"������ �湮��(�Ÿ�="+D[index]+", ����="+weightSum+")");
			count++; // ���õ� ���� ���� ����
			
			if(count == V) break; //���õ� ������ ������ ���������� �������� ����
			
			// ���õ� ������ �����Ͽ� �ٸ� ������ ���������� ����
			for(int i = 0; i < V ; i++) {
				//i������ �湮���� �ʾҰ�, index �������� i�������� �� �Ÿ��� ����� �ּҰ����� �۰�
				//i������ index ������ ����Ǿ� ������
				if(!visit[i] && (graph[index][i] < D[i]) && graph[index][i] > 0 ) {  
					D[i] = graph[index][i]; // index ���� i ����� ����ġ�� �ּҰ����� ����
				}
			}
		}
		
		System.out.println("������ ������ "+weightSum+"�Դϴ�.");
	}
}


/* �켱���� ť�� ������ ���
 * class Vertex { int from ,to , weight; public Vertex(int f, int t ,int w) {
 * this.from = f; this.to = t; this.weight = w; } }
 */