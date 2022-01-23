import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Kruskal {
	
	public static int[] parent; //�θ��� �����ϴ� �迭
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int weightSum = 0; // ����ġ ��
		int count = 0; // ����� ���� ��
		int V, E; // ������ ����
		
		System.out.print("����� ������ �Է��� �ּ��� >> ");
		V = sc.nextInt(); //����� ����
		
		System.out.print("������ ������ �Է��� �ּ��� >> ");
		E = sc.nextInt(); //������ ����
				
		parent = new int[V+1]; //1������ �ε����� ����ϱ� ����
		
		// �Է¹��� ���� ������ŭ ����ġ�� �Է¹ޱ� ���� �迭 ����
		int graph[][] = new int[E][3];
		
		// ������ ����ġ �Է¹���
		for (int i = 0; i < E; i++) {
			System.out.print("����1 ����2 ����ġ �Է� >> ");
			graph[i][0] = sc.nextInt();	// ����1
			graph[i][1] = sc.nextInt();	// ����2
			graph[i][2] = sc.nextInt();	// ����ġ
		}
		
		// ������������ ����ġ ����
		Arrays.sort(graph, new Comparator<int []>() {
			
			@Override
			public int compare(int[] o1, int[] o2) {
				// �ε��� 2�� ����ġ�̹Ƿ� �ε���2 �������� ����
				return Integer.compare(o1[2], o2[2]);
			}
		});
		
		// �ڱ� �ڽ��� �ڽ��� �θ���� ����
		for (int i = 1; i < V ; i++) {
			parent[i] = i; 
		}
		
		// �����Ǽ���ŭ �ݺ�
		for(int i = 0; i < graph.length; i++) {
			//���� ������ ����� �� �ִ��� union�� ���� �Ǵ�
			if(union(graph[i][0], graph[i][1])) {
				weightSum += graph[i][2]; // ����ġ ��
				count++;
				System.out.println(graph[i][0] + "���� " + graph[i][1] + "��尡 �����");
			}
			//������ ���� ����-1 ��ŭ�̸� ��� ������ ����Ǿ����Ƿ� �����
			if(count == V-1) {break;}
		}
		sc.close();
		
		System.out.println("����� ������ ����ġ�� "+weightSum+" �Դϴ�.");
	}
	
	
	public static int find(int x) {
		if(x==parent[x]) return x; // �ʱ�ȭ�� ���¸� �ڱ� ������ �θ�
		parent[x]= find(parent[x]); // find �� ������ �θ��尡 �ֻ��� �θ���� ������
		return parent[x]; 
	}
	
	
	// cycle ���� ���Ῡ�� �Ǵ�
	// �� ������ root�� ���� ���·� ����Ǹ� cycle�� �����Ȱ�
	// �� ������ root�� �ٸ� ���·� ����Ǹ� cycle���� �����
	public static boolean union(int v1, int v2) {
		int v1Root = find(v1); //v1�� root
		int v2Root = find(v2); //v2�� root
		
		// ������ root�� ���� ������ cycle ���� x
		if(v1Root != v2Root) {
			parent[v2Root]=v1Root; // v2�� root�� v1�� root�� �ٲ�
			return true; // cycle ���� �����
		}else {
			return false; // cycle�� �����Ǿ� ������ �ȵ�
		}
		
	}

}
