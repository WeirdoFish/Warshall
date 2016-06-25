package root;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public final class MainClass extends JFrame implements Runnable {
	JSplitPane splitHor = new JSplitPane(); // динамическое деление панели
	JSplitPane splitVer = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
	JTextArea text = new JTextArea("Здесь будет граф");
	JButton butNext = new JButton("Далее");
	JTextArea graphMatrix = new JTextArea("Здесь будет матрица смежности");
	JMenuBar mBar = new JMenuBar();
	JMenuItem menuAdd = new JMenuItem("Задать граф");
	JDesktopPane dialog = new JDesktopPane(); //контейнер окон
	
	public static void main(String[] args) {
	SwingUtilities.invokeLater(new MainClass());
	}

	public void run() {
		setTitle("Warshall");
		setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		//setSize(600,600);
		setJMenuBar(mBar);
		mBar.add(menuAdd);
		setLayout(new BorderLayout());
		add(splitHor, BorderLayout.CENTER);
		add(dialog, BorderLayout.NORTH);
		splitHor.add(text, JSplitPane.RIGHT);
		splitHor.add(splitVer, JSplitPane.LEFT);
		splitVer.add(graphMatrix, JSplitPane.TOP);
		splitVer.add(butNext, JSplitPane.BOTTOM);
		
		menuAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
		//		dialog.add(new GraphAdd("Ввод графа"));
			}
		});
		
		pack();
		setVisible(true);
	}
}
