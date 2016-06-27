package root;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import root.TableWithRowHeader;

public final class MainClass extends JFrame implements Runnable {
	// tmp
	JTextArea text = new JTextArea("Здесь будет граф");

	// контент
	JButton butNext = new JButton("Начать выполнение");
	JMenuBar mBar = new JMenuBar();
	JMenu mCreate = new JMenu("Задать граф");
	JMenuItem mFile = new JMenuItem("Указать файл");
	JMenuItem mHand = new JMenuItem("Ввести вручную");

	// панели окна
	JPanel rightPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel matrixPanel = new JPanel();
	JPanel buttonPanel = new JPanel();

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new MainClass());
	}

	public void startGUI() {
		this.setBackground(Color.WHITE);
		setTitle("Warshall");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// настройка меню
		setJMenuBar(mBar);
		mBar.add(mCreate);
		mCreate.setPreferredSize(new Dimension(118, 20));
		mCreate.add(mFile);
		mCreate.add(mHand);

		// левая панель
		setLayout(new BorderLayout(1, 1));
		add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BorderLayout(1, 1));
		leftPanel.setPreferredSize(new Dimension(394, 600));
		leftPanel.setBackground(Color.WHITE);

		// матрица
		leftPanel.add(matrixPanel);
		matrixPanel.setPreferredSize(new Dimension(375, 300));
		matrixPanel.setBorder(new TitledBorder("Матрица смежности"));
		matrixPanel.setBackground(Color.WHITE);
		showMatrix();

		// кнопка
		leftPanel.add(buttonPanel, BorderLayout.SOUTH);
		butNext.setEnabled(false);
		buttonPanel.add(butNext, BorderLayout.WEST);
		buttonPanel.setPreferredSize(new Dimension(200, 100));
		buttonPanel.setBackground(Color.WHITE);

		// граф
		add(rightPanel, BorderLayout.EAST);
		rightPanel.add(text);
		rightPanel.setBackground(Color.WHITE);
		rightPanel.setPreferredSize(new Dimension(600, 600));
		rightPanel.setBorder(new TitledBorder("Граф"));

		// Главное окно
		setResizable(false);
		setSize(1000, 600);
		setVisible(true);
	}

	public void run() {
		startGUI();

		// активность кнопки
		butNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showAlgo();
			}
		});

		// активность меню
		mFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				open();
			}
		});
	}

	public void open() {
		boolean sucsess = true;
		int someNum = 1;
		Integer[][] someMas = { { 0 } };

		JFileChooser chooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Text files *.txt", "txt");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {

			System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

			try {
				BufferedReader bufRdr;
				bufRdr = new BufferedReader(new FileReader(chooser.getSelectedFile()));

				if (bufRdr.ready()) {
					Scanner in = new Scanner(new FileReader(chooser.getSelectedFile()));
					// int someNum;
					// Integer[][] someMas;
					if (in.hasNextInt()) {
						someNum = in.nextInt();
						someMas = new Integer[someNum][someNum];
						for (int i = 0; i < someNum; i++) {
							for (int j = 0; j < someNum; j++) {
								if (in.hasNextInt()) {
									someMas[i][j] = in.nextInt();
								} else {
									in.close();
									throw new IOException();
								}
							}
						}
					} else {
						in.close();
						throw new IOException();
					}
					in.close();
				}
				bufRdr.close();
			} catch (IOException e1) {
				sucsess = false;
				JOptionPane.showMessageDialog(this, "Некорректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
			} finally {
				if (sucsess) {
					prepareAlgo(someNum, someMas);
				}
			}
		}

	}

	public void prepareAlgo(int num, Integer[][] matr) {
		Integer head[] = new Integer[num];
		for (int i = 0; i < num; i++) {
			head[i] = i + 1;
		}
		Graph graph = new Graph(num);
		graph.arg1 = head;
		graph.matrix = matr;
		showMatrix(graph.matrix, graph.arg1);
		butNext.setText("Начать выполнение");
		butNext.setEnabled(true);
	}

	public void showAlgo() {
		butNext.setText("Далее");
		// что-то делается
		// а потом кнопка снова не работает
	}

	public void showMatrix(Object[][] data, Object[] headers) {

		DefaultTableModel model = new DefaultTableModel(data, headers);
		JTable gMatrix = new JTable(model);
		JScrollPane gmScroll = new JScrollPane(gMatrix, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JTable lineTable = new TableWithRowHeader(gMatrix);
		gmScroll.setRowHeaderView(lineTable);

		gMatrix.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// gMatrix.setShowGrid(false);
		gMatrix.setEnabled(false);
		gMatrix.setVisible(true);
		gMatrix.setPreferredScrollableViewportSize(new Dimension(310, 300));
		gMatrix.setBackground(matrixPanel.getBackground());
		gMatrix.getTableHeader().setReorderingAllowed(false);
		gmScroll.setRowHeader(new JViewport());

		matrixPanel.removeAll();
		matrixPanel.add(gmScroll);
		matrixPanel.validate();
	}

	public void showMatrix() {
		Integer data[][] = { { 0, 0 }, { 0, 0 } };
		Integer headers[] = { 1, 2 };

		DefaultTableModel model = new DefaultTableModel(data, headers);
		JTable gMatrix = new JTable(model);
		JScrollPane gmScroll = new JScrollPane(gMatrix, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JTable lineTable = new TableWithRowHeader(gMatrix);
		gmScroll.setRowHeaderView(lineTable);

		/*
		 * JTable gMatrix = new JTable(data, headers); JScrollPane gmScroll =
		 * new JScrollPane(gMatrix,
		 * ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		 * ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		 */
		gMatrix.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

		// gMatrix.setShowGrid(false);
		gMatrix.setEnabled(false);
		gMatrix.setVisible(true);
		gMatrix.setPreferredScrollableViewportSize(new Dimension(310, 300));
		gMatrix.setBackground(matrixPanel.getBackground());
		gMatrix.getTableHeader().setReorderingAllowed(false);
		gmScroll.setRowHeader(new JViewport());
		matrixPanel.add(gmScroll);
	}
}
