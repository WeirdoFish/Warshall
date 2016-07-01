package root;

import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Queue;


import javax.swing.JPanel;

import com.mxgraph.swing.mxGraphComponent;

import com.mxgraph.util.mxConstants;
import com.mxgraph.view.mxGraph;
import com.mxgraph.view.mxStylesheet;
import com.mxgraph.layout.mxCircleLayout;


public class Graph extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	mxGraph a = new mxGraph();

	private int numberVertex;
	protected Integer matrix[][];
	protected Integer arg1[];

	// отрисовка графа
	protected mxGraph gg;
	protected Object parent;
	protected Object v_gg[];
	//
	mxCircleLayout v_circle;
	mxGraphComponent graphComponent;
	String e_color_begin = "blue";
	String e_color_tmp = "red";
	String e_color_end = "green";

	Queue<Integer> e_queue; // для сохранения вершин

	public Graph(int number, Integer[] head, Integer[][] matr) {
		// super();
		numberVertex = number;
		matrix = new Integer[numberVertex][numberVertex];
		arg1 = new Integer[numberVertex];

		for (int i = 0; i < number; i++) {
			arg1[i] = i + 1;
		}

		// arg1 = head;
		for (int i = 0; i < numberVertex; i++) {
			for (int j = 0; j < numberVertex; j++) {
				if (matr[i][j] != 0) {
					matrix[i][j] = matr[i][j];
				} else {
					matrix[i][j] = 99999;
				}
			}
		}
		e_queue = new LinkedList<Integer>();

		// graph
		// определение нового графа
		gg = new mxGraph(); // определение графа
		v_gg = new Object[numberVertex]; // определение количества вершин

		double V_SIZE = 30;// для распределения вешин

		parent = gg.getDefaultParent();// определение родительского графа
		// определение стиля
		mxStylesheet stylesheet = gg.getStylesheet();
		Hashtable<String, Object> style = new Hashtable<String, Object>();
		style.put(mxConstants.STYLE_SHAPE, mxConstants.SHAPE_ELLIPSE);
		style.put(mxConstants.STYLE_OPACITY, 50);
		style.put(mxConstants.STYLE_FONTCOLOR, "#774400");
		stylesheet.putCellStyle("ROUNDED", style);

		// обновление графа
		gg.getModel().beginUpdate();
		try {
			// заполнение вершин
			for (int i = 0; i < numberVertex; i++) {
				v_gg[i] = gg.insertVertex(parent, null, Integer.toString(arg1[i]), 0, 0, V_SIZE, V_SIZE, "ROUNDED");
			}
			// заполнение ребер
			for (int i = 0; i < numberVertex; i++) {
				for (int j = 0; j < numberVertex; j++) {
					if (matrix[i][j] != 99999) {
						gg.insertEdge(parent, null, Integer.toString(matrix[i][j]), v_gg[i], v_gg[j],
								"strokeColor=" + e_color_begin);
					}

				}
			}

		} finally {
			gg.getModel().endUpdate();
		}

		v_circle = new mxCircleLayout(gg); // расположеие вершин по окружности
		v_circle.circle(v_gg, 230, 0, 0);

		graphComponent = new mxGraphComponent(gg);
		graphComponent.setEnabled(false);
		add(graphComponent);
	}

	protected void solve(int step) {
		// for (int k = 0; k < numberVertex; k++) {
		// ОЛЯ, тут вывод графа
		// создаем очередь
		Integer tmp = 0;
		// перекрашивание старых ребер
		recolor_edge_gg();
		for (int i = 0; i < numberVertex; i++) {
			for (int j = 0; j < numberVertex; j++) {
				tmp = Math.min(matrix[i][j], matrix[i][step] + matrix[step][j]);
				if (tmp < matrix[i][j] && i != j) {
					matrix[i][j] = tmp; // изменить значение матрицы

					gg.getModel().beginUpdate();
					try {
						// заполнение вершин

						gg.insertEdge(parent, null, Integer.toString(matrix[i][j]), v_gg[i], v_gg[j],
								"strokeColor=" + e_color_tmp);
						e_queue.add(i);
						e_queue.add(j);

					} finally {
						gg.getModel().endUpdate();
					}

				}
			}
		}
		// }
	}

	protected int getNum() {
		return numberVertex;
	}

	protected Integer[][] getMatrix() {
		return matrix;
	}

	protected Integer[] getArg1() {
		return arg1;
	}

	private void recolor_edge_gg() {
		Integer i = 0;
		Integer j = 0;
		gg.getModel().beginUpdate();
		try {
			while (!e_queue.isEmpty()) {
				i = e_queue.poll();
				j = e_queue.poll();
				gg.insertEdge(parent, null, Integer.toString(matrix[i][j]), v_gg[i], v_gg[j],
						"strokeColor=" + e_color_end);
			}

		} finally {
			gg.getModel().endUpdate();
		}
	}

}
