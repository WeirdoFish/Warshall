package root;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.InputMismatchException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class CustomDialog extends JDialog {
	private static final long serialVersionUID = 1L;
	CustomDialog f = this;
	JTextArea mNum = new JTextArea();
	JTextArea mMas = new JTextArea();
	JButton apply = new JButton("Принять");
	JScrollPane mmScroll = new JScrollPane(mMas);
	int nums;
	Integer matr[][];
	boolean finished = false;

	public int getInt() {
		return nums;
	}

	public Integer[][] getMasInt() {
		return matr;
	}

	public boolean isReady() {
		return finished;
	}

	public CustomDialog(JFrame aFrame) {

		super(aFrame, "Ввод данных", true);
		getContentPane().add(createGUI());
		pack();
		setSize(getPreferredSize());
		setResizable(false);
		setLocationRelativeTo(aFrame);
		setVisible(true);
	}

	private JPanel createGUI() {
		// setSize(500,300);
		JPanel mainPanel = new JPanel(new BorderLayout()), upperPanel = new JPanel(new BorderLayout(5, 3)),
				downPanel = new JPanel(new BorderLayout(5, 3));

		mainPanel.add(upperPanel, BorderLayout.NORTH);
		upperPanel.add(mNum, BorderLayout.NORTH);
		mNum.setBorder(new TitledBorder("Количество вершин"));
		mNum.setPreferredSize(new Dimension(50, 40));
		mNum.setLineWrap(true);
		upperPanel.add(mmScroll, BorderLayout.SOUTH);
		mmScroll.setPreferredSize(new Dimension(200, 250));
		mMas.setPreferredSize(new Dimension(200, 250));
		mMas.setLineWrap(true);
		mMas.setWrapStyleWord(true);
		mMas.setBorder(new TitledBorder("Матрица смежности"));
		mMas.setCaretPosition(mMas.getDocument().getLength());
		mainPanel.add(downPanel, BorderLayout.SOUTH);
		downPanel.add(apply, BorderLayout.CENTER);
		downPanel.setPreferredSize(new Dimension(80, 30));

		apply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ввод и проверки
				String a;
				try {
					a = mNum.getText();
					nums = Integer.parseInt(a);
					System.out.println(a);
					a = "";
					a = mMas.getText();
					matr = parser(nums, a);
					finished = true;
					dispose();
				} catch (Exception z) {
					JOptionPane.showMessageDialog(f, "Некорректные данные", "Ошибка", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		return mainPanel;
	}

	private Integer[][] parser(int n, String str) {
		Integer intMas[][] = new Integer[n][n];
		String tmp[] = str.split("\n");
		if (tmp.length < n)
			throw new InputMismatchException();

		for (int i = 0; i < n; i++) {
			String line[] = tmp[i].split(" ");
			System.out.println(tmp[i]);

			if (line.length < n)
				throw new InputMismatchException();

			for (int j = 0; j < n; j++) {
				int parsed = Integer.parseInt(line[j]);
				intMas[i][j] = parsed;
				System.out.println(parsed);
			}
		}
		return intMas;
	}
}

