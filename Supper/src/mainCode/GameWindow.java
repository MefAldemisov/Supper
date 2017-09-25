package mainCode;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.JMenuBar;
import java.awt.BorderLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	static int bomb, length, height;

	public byte[][] getMainArray() {
		return MainArray;
	}

	private ImageIcon[][] pictures;

	public ImageIcon[][] getPictures() {
		return pictures;
	}

	public static void setBomb(int bomb) {
		GameWindow.bomb = bomb;
	}

	public static int getBomb() {
		return bomb;
	}

	public static int getLength() {
		return length;
	}

	public static int getHeigth() {
		return height;
	}

	private byte[][] MainArray;
	private JTable table;
	private TableMaker model;

	public GameWindow(int bomb, int length, int height) {
		this.bomb = bomb;
		this.length = length;
		this.height = height;
		ArrayMaker randomaizer = new ArrayMaker();
		System.out.println(bomb + " " + length + " " + height);
		MainArray = randomaizer.getArray();
		drawer();
		setBounds(100, 100, length * 35 + 10, height * 40 + 40);
		setResizable(false);
		setVisible(true);
	}

	private void addPicturesIn() {
		pictures = new ImageIcon[length][height];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < height; j++) {
				pictures[i][j] = new ImageIcon("images/1.png");
			}
		}
	}

	private void drawer() {
		model = new TableMaker();
		pictures=model.getPictures();
		for (int i = 0; i < length; i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(35);
		}
		setFocusable(true);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		getContentPane().add(menuBar, BorderLayout.NORTH);

		JMenu mnSettings = new JMenu("Settings");
		menuBar.add(mnSettings);

		JMenuItem mntmChangeSettings = new JMenuItem("Change settings");
		mntmChangeSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				SettingsWindow set = new SettingsWindow();
				set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();

			}
		});

		mnSettings.add(mntmChangeSettings);

		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);

		JMenuItem mntmRestart = new JMenuItem("Restart");
		mntmRestart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				SettingsWindow set = new SettingsWindow();
				set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		mnExit.add(mntmRestart);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(3);
			}
		});
		mnExit.add(mntmExit);
		table = new JTable(model);
		table.setRowSelectionAllowed(false);
		table.setForeground(Color.ORANGE);
		table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionForeground(Color.GREEN);
		table.setRowHeight(35);
		//table.setSurrendersFocusOnKeystroke(true);
		table.setSelectionBackground(Color.RED);
		table.setGridColor(Color.MAGENTA);
		table.setSize(length * 35, height * 35);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// open
					open();
				}
				if (e.getKeyCode() == KeyEvent.VK_PASTE) {
					System.out.println("bomb");
					// bomb
					pictures[table.getSelectedColumn()][table.getSelectedRow()] = new ImageIcon("images/20.png");
					table.updateUI();

				}
			}

			private void open() {
				if (MainArray[table.getSelectedColumn()][table.getSelectedRow()] != 20) {
					pictures[table.getSelectedColumn()][table.getSelectedRow()] = new ImageIcon(
							"images/" + MainArray[table.getSelectedColumn()][table.getSelectedRow()] + ".png");
					table.updateUI();
				} else {
					JOptionPane.showMessageDialog(null, "Game over");
					System.exit(6);
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});
		getContentPane().add(table);

	}

}
