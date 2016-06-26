package root;


public class Graph {

	private int numberVertex;
	protected Integer matrix[][];
	protected Integer arg1[];
	protected String arg2[][];
	
	public Graph(int number) {
		// по-хорошему, здесь нужно считать из файла либо получить иначе
		numberVertex = number;
		matrix = new Integer[numberVertex][numberVertex];
		arg2 = new String[numberVertex][numberVertex];
		arg1 = new Integer[numberVertex];
		
		for (int i = 0; i<number; i++){
			arg1[i]=  i+1;
		}
		
		for (int i = 0; i<number; i++){
			for (int j = 0; j<number; j++){
				matrix[i][j]=0;
		//	arg2[i][j] = matrix[i][j];
			}
		}
	}

	protected void solve() {
		for (int k = 0; k < numberVertex; k++) {
			for (int i = 0; i < numberVertex; i++) {
				for (int j = 0; j < numberVertex; j++) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
				}
			}
		}
	}
}
