package root;

public class Graph {

	private int numberVertex;
	private int matrix[][];

	public Graph(int number) {
		// по-хорошему, здесь нужно считать из файла либо получить иначе
		numberVertex = number;
		matrix = new int[numberVertex][numberVertex];
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
