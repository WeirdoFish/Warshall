package root;

import com.mxgraph.swing.*;
import com.mxgraph.view.*;

public class Graph {
	mxGraph a = new mxGraph();
	
	private int numberVertex;
	protected Integer matrix[][];
	protected Integer arg1[];
	
	public Graph(int number) {
		numberVertex = number;
		matrix = new Integer[numberVertex][numberVertex];
		arg1 = new Integer[numberVertex];
		
		for (int i = 0; i<number; i++){
			arg1[i]=  i+1;
		}
		
		for (int i = 0; i<number; i++){
			for (int j = 0; j<number; j++){
				matrix[i][j]=10000;
			}
		}
	}

	protected void solve(int step) {
		//for (int k = 0; k < numberVertex; k++) {
		//ќЋя, тут вывод графа
		
			for (int i = 0; i < numberVertex; i++) {
				for (int j = 0; j < numberVertex; j++) {
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][step] + matrix[step][j]);
				}
			}
		//}
	}
	
	protected int getNum(){
		return numberVertex;
	}
	
	protected Integer[][] getMatrix(){
		return matrix;
	}
	protected Integer[] getArg1(){
		return arg1;
	}
}
