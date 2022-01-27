import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class DFSandBFS {

	static boolean visitDFS[]; // DFS �湮Ȯ�� �迭
	static boolean visitBFS[]; // BFS �湮Ȯ�� �迭
	
	static int graph[][];
	
	static List<Integer> procDFS = new ArrayList<>(); //DFS ����
	static List<Integer> procBFS = new ArrayList<>(); //BFS ����
	
	static Queue<Integer> q = new LinkedList<>();
	
	static int V;// ���� ����
	static Scanner sc =new Scanner(System.in); 
	
	public static void main(String[] args) {
		
		System.out.print("������ ������ �Է��ϼ��� >> ");
		V = sc.nextInt(); //���� ���� �Է�
		
		visitDFS = new boolean[V]; //�湮 Ȯ�ο� �迭 ����
		visitBFS = new boolean[V];
		
		graph = new int[V][V]; //������� ����
		
		// ��� ����x �� �ʱ�ȭ
		for(int i=0; i<V; i++) {
			Arrays.fill(graph[i], 0); 
		}

		
		while(true) {
			// ���̻� ���ᰡ������ Ȯ��
			if(isFull(graph, V)) {
				System.out.println("�׷����� ��� ����Ǿ����ϴ�.");
				break;
			}
			
			System.out.print("����1 ����2 �Է�(-1�Է½� �׷��� ���� ����) >> ");
			int v1 = sc.nextInt();
			
			if(v1 == -1) { //-1 �Է½� while ����
				break;
			}
			
			int v2 = sc.nextInt();
			
			if(v1>=V || v2>=V) {
				System.out.println("������ �ʰ��Ͽ����ϴ�. �ٽ� �Է��ϼ���.");
				continue;
			}
			// �̹� ����Ǿ��d ��Ȯ��
			if(graph[v1][v2]==1 && graph[v2][v1]==1)
			{
				System.out.println("�̹� ����Ǿ����ϴ�.");
				continue;
			}else {
				graph[v1][v2]=1;
				graph[v2][v1]=1;
			}
		}
		
		System.out.println("�׷��� ���� �Ϸ�\n");
		System.out.print("���� ���� �Է�(0 ~ " + (V-1) +") >>");
		int i = sc.nextInt();
		
		dfs(i); //i���� Ž�� ���� DFS
		bfs(i); //i���� Ž�� ���� BFS
		
		System.out.println("DFS �湮 ����: " + procDFS);
		System.out.println("BFS �湮 ����: " + procBFS);
		
		sc.close();
		
	}
	
	// �׷����� �� ����Ǿ����� Ȯ��
	public static boolean isFull(int graph[][], int V) {
		int zero = 0;
		for(int i = 0; i<V; i++) {
			for(int j =0; j<V; j++) {
				if(graph[i][j] == 0) {
					zero++;
				}
			}
		}
		
		// 0�� ������ V����ŭ�̸� �� ����Ȱ�
		if(zero == V) { 
			return true;
		}else {
			return false;
		}
		
	}
	
	// DFS ���o
	public static void dfs(int i) // i���� ����
	{	
		visitDFS[i] = true;
		procDFS.add(i); // ���� �߰�
		
		for(int j=0; j<V ; j++) {
			if(graph[i][j] == 1 && visitDFS[j] == false) {
				dfs(j);
			}
		}
	}

	// BFS ���x
	public static void bfs(int i)  // i���� ����
	{	
		visitBFS[i] = true;
		q.offer(i); //ť�� ����
		
		while(!q.isEmpty()) { //ť�� �� ������
			int tmp = q.poll(); //ť���� ��(���� ����) ���н� null
			procBFS.add(tmp); //���� �߰�
			
			for(int j=0; j<V; j++) {
				if(graph[tmp][j] == 1 && visitBFS[j] == false) { //����� ��� ���� Ȯ��
					q.offer(j); //ť�� ����
					visitBFS[j] = true;
				}
			}
			
		}
	} 
}
