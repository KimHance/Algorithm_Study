import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Dijkstra { //���� ��� ���
	
	static Scanner sc =new Scanner(System.in); 
	static int graph[][];
	static int V, E; // ������ ����
	static int D[]; // �ּ� �Ÿ� ��� �迭
	static int start ; // ���� ����
	static List<Integer> proc = new ArrayList<>(); //DFS ����
	
	public static void main(String[] args) {
		
		System.out.print("������ ������ �Է��� �ּ��� >> ");
		V = sc.nextInt(); // ������ ����
		
		System.out.print("������ ������ �Է��� �ּ��� >> ");
		E = sc.nextInt(); // ������ ����

		//�湮 �迭 ����
		boolean visit[] =new boolean[V];
		
		// �׷���&�Ÿ� �迭 ����
		graph =new int[V][V];
		D = new int[V];
		
		// �׷���& �Ÿ� �迭 �ʱ�ȭ
		for(int i=0; i<V; i++) {
			Arrays.fill(graph[i], Integer.MAX_VALUE); 
		}
		Arrays.fill(D, Integer.MAX_VALUE);
		
		// ������ ����ġ �Է¹���
		// ������� ����
		for (int i = 0; i < E; i++) {
			System.out.print("����1 ����2 ����ġ �Է� >> ");
			int v1 = sc.nextInt();
			int v2 = sc.nextInt();
			int w = sc.nextInt();
			graph[v1][v2] = w;
			graph[v2][v1] = w; 
		}
		System.out.println("�׷��� ���� �Ϸ�\n");
		
		System.out.print("������ ���� �Է� >> ");
		start = sc.nextInt();
		
		// ���� ������ �ʱ�ȭ
		D[start]=0;
		visit[start]=true;
		System.out.println("\n"+start+"�������� �����մϴ�");
		
		//���ͽ�Ʈ�� �˰���
		for(int i=0; i<V; i++) {
			int min = Integer.MAX_VALUE; // �ּ� ���ϱ� ����
			int index = 0; // �ε��� ���� ����
			
			for(int j=0; j<V; j++) {
				if(!visit[j] && (D[j] < min)) { // �湮x ���� �� �ּҺ�� ã��
					min=D[i]; //�ּҰ� �ٲ���
					index = j; 
				}
			}
			// �ּҺ�� ���� �湮
			visit[index]=true;
			proc.add(index);
			System.out.println("�湮 = "+proc);
			
			
			// �ش� ���� ���� �������� ��� Ȯ��  
			for(int k=0; k<V; k++) {
				if(!visit[k] && (graph[index][k] != Integer.MAX_VALUE) ) { // ����� Ȯ�� �����ϰ� �湮x ������� �߿��� 
					if(D[index] + graph[index][k] < D[k]) { //�ش� ��带 ���İ��°� ������
						D[k] = D[index] + graph[index][k]; // �� �������� ����� ������
					}
				}
			}
			
			// ��¿�
			for(int p =0; p<V; p++) {
				if(D[p]==Integer.MAX_VALUE) {
					System.out.println("����"+p+" ������ �Ÿ� = ��");
				}else {
					System.out.println("����"+p+" ������ �Ÿ� = "+D[p]);
				}
			}
			System.out.println("------------------------\n\n");
		}
		
		sc.close();
	}

}
