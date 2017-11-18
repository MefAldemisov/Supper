package mainCode;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;

import addition.WinWind;

@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	static int bomb, length, height, mines, opened;
	private static boolean firstStep;

	public byte[][][] getMainArray() {
		return mainArray;
	}

	private ImageIcon[][] picturesArray;

	public ImageIcon[][] getPictures() {
		return picturesArray;
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

	private byte[][][] mainArray;
	private JTable table;
	private TableMaker model;
	private JButton smile;
	private JLabel lbBom;
	private static ImageIcon nul, bom, qw;
	private static int windLength;
	private static long startTime;

	@SuppressWarnings("static-access")
	public GameWindow(int bomb, int length, int height) {
		super("Sapper");
		firstStep = true;
		mines = 0;
		opened = 0;
		this.bomb = bomb;
		this.length = length;
		this.height = height;
		nul = new ImageIcon("src/images/null.png");
		bom = new ImageIcon("src/images/20.png");
		qw = new ImageIcon("src/images/f.png");

		if (length * 30 + 100 <= 1200 && height * 40 + 150 <= 1000) {
			setBounds(100, 100, length * 30 + 120, height * 30 + 100);
			windLength = length * 30 + 120;
		} else {
			setBounds(100, 100, 1200, 710);
			windLength = 1200;
		}

		setBackground(Color.WHITE);

		getContentPane().setLayout(null);
		setResizable(false);
		java.awt.Image im = this.getToolkit().getImage("src/images/boom.png");
		setIconImage(im);
		setVisible(true);
		drawer();
	}

	@SuppressWarnings("unused")
	private void addPicturesIn() {
		picturesArray = new ImageIcon[height][length];
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				picturesArray[i][j] = nul;
			}
		}
	}

	private void menu() {
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Ubuntu", Font.BOLD, 18));
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setBounds(1, 1, windLength, 20);

		JMenu help = new JMenu("Help");
		help.setFont(new Font("Ubuntu", Font.BOLD, 18));
		menuBar.add(help);

		JMenuItem hell = new JMenuItem("Gide");
		hell.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				Gide g = new Gide();
				g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			}
		});
		help.add(hell);

		JMenu mnSettings = new JMenu("Settings");
		mnSettings.setFont(new Font("Ubuntu", Font.BOLD, 18));
		menuBar.add(mnSettings);

		JMenuItem mntmChangeSettings = new JMenuItem("Change settings");
		mntmChangeSettings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				SettingsWindow set = new SettingsWindow();
				set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();

			}
		});

		mnSettings.add(mntmChangeSettings);

		JMenu mnExit = new JMenu("Exit");
		mnExit.setFont(new Font("Ubuntu", Font.BOLD, 18));
		menuBar.add(mnExit);

		JMenuItem mntmNewGame = new JMenuItem("New game");
		mntmNewGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsWindow set = new SettingsWindow();
				set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		mnExit.add(mntmNewGame);

		JMenuItem Restart = new JMenuItem("Restart");
		Restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				restart();
			}
		});
		mnExit.add(Restart);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(3);
			}
		});
		mnExit.add(mntmExit);
		getContentPane().add(menuBar);

	}

	private void drawer() {
		// Menu at the top of the frame
		menu();

		lbBom = new JLabel("Bomb: 0 / " + bomb);
		lbBom.setFont(new Font("Ubuntu", Font.BOLD, 18));
		smile = new JButton("");
		smile.setSize(45, 45);
		smile.setBackground(Color.white);
		smile.setIcon(new ImageIcon("src/images/smile|.png"));
		smile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				SettingsWindow set = new SettingsWindow();
				set.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}
		});
		getContentPane().add(smile);
		model = new TableMaker(length, height);
		picturesArray = TableMaker.getPicArr();

		table = new JTable(model);
		table.setColumnSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setSelectionForeground(Color.BLACK);
		table.setSelectionBackground(Color.BLACK);
		table.setBackground(Color.WHITE);
		for (int h = 0; h < length; h++) {
			table.getColumnModel().getColumn(h).setMaxWidth(30);
		}
		CustomTableRenderer ren = new CustomTableRenderer();
		setFocusable(true);
		table.setSurrendersFocusOnKeystroke(true);
		table.setRowHeight(30);
		table.setSize(length * 30, height * 30);
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@SuppressWarnings("static-access")
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				// first step
				if (firstStep) {
					ArrayMaker randomaizer = new ArrayMaker(bomb, height, length, (byte) table.getSelectedRow(),
							(byte) table.getSelectedColumn());
					mainArray = randomaizer.getArray();
					firstStep = false;
					Date d = new Date();
					startTime = d.getTime();
				}
				// opening
				if (SwingUtilities.isLeftMouseButton(e)) {
					open(table.getSelectedRow(), table.getSelectedColumn());
				}
				// opening the bomb
				if (e.getButton()==MouseEvent.BUTTON3) {
					if (mainArray[table.getSelectedRow()][table.getSelectedColumn()][1] != 1) {
						if (mainArray[table.getSelectedRow()][table.getSelectedColumn()][1] != 2) {
							mines++;
							mainArray[table.getSelectedRow()][table.getSelectedColumn()][1] = 2;
							picturesArray[table.getSelectedRow()][table.getSelectedColumn()] = bom;
							model.setPicArr(picturesArray);
							table.updateUI();
							lbBom.setText("Bomb: " + mines + " /" + bomb);
						} else {
							flag();
						}
					}
				}
				// opening the flag
				if (SwingUtilities.isMiddleMouseButton(e)) {
					flag();
				}

			}
		});
		table.addKeyListener(new KeyListener() {

			@SuppressWarnings("static-access")
			@Override
			public void keyTyped(KeyEvent e) {
				// first step
				if (firstStep) {
					ArrayMaker randomaizer = new ArrayMaker(bomb, height, length, (byte) table.getSelectedRow(),
							(byte) table.getSelectedColumn());
					mainArray = randomaizer.getArray();
					firstStep = false;
					Date d = new Date();
					startTime = d.getTime();
				}
				// opening
				if (e.getKeyChar() == KeyEvent.VK_MINUS || e.getKeyChar() == KeyEvent.VK_SPACE) {
					open(table.getSelectedRow(), table.getSelectedColumn());
				}
				// opening the bomb
				if (e.getKeyChar() == KeyEvent.VK_EQUALS || e.getKeyChar() == 'z' || e.getKeyChar() == 'Z'
						|| e.getKeyChar() == 'я' || e.getKeyChar() == 'Я') {
					if (mainArray[table.getSelectedRow()][table.getSelectedColumn()][1] != 1) {
						if (mainArray[table.getSelectedRow()][table.getSelectedColumn()][1] != 2) {
							mines++;
							mainArray[table.getSelectedRow()][table.getSelectedColumn()][1] = 2;
							picturesArray[table.getSelectedRow()][table.getSelectedColumn()] = bom;
							model.setPicArr(picturesArray);
							table.updateUI();
							lbBom.setText("Bomb: " + mines + " /" + bomb);
						} else {
							flag();
						}
					}
				}
				// opening the flag
				if (e.getKeyChar() == '/' || e.getKeyChar() == '?' || e.getKeyChar() == '.' || e.getKeyChar() == ',') {
					flag();
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				ren.getTableCellRendererComponent(table,
						picturesArray[table.getSelectedRow()][table.getSelectedColumn()], true, true,
						table.getSelectedRow(), table.getSelectedColumn());
			}
		});
		table.setFocusable(true);
		table.setCellSelectionEnabled(true);
		// addition like scroll pane
		JPanel panel = new JPanel();
		JScrollPane scrol = new JScrollPane();
		smile.setBounds(475, 25, 45, 45);
		getContentPane().add(smile);
		lbBom.setBounds(150, 30, 120, 20);
		getContentPane().add(lbBom);
		table.setBounds(20, 80, length * 30, height * 30);
		panel.add(table);
		panel.setBounds(20, 80, length * 30 + 30, height * 30 + 30);
		scrol.setViewportView(panel);
		scrol.setBounds(20, 80, length * 30 + 30, height * 30 + 30);
		getContentPane().add(scrol);
	}

	@SuppressWarnings("static-access")
	protected void flag() {
		mines--;
		picturesArray[table.getSelectedRow()][table.getSelectedColumn()] = qw;
		mainArray[table.getSelectedRow()][table.getSelectedColumn()][1] = 3;
		model.setPicArr(picturesArray);
		table.updateUI();
		lbBom.setText("Bomb: " + mines + " /" + bomb);

	}

	private void win() {
		Date d2 = new Date();
		long endTime = d2.getTime();
		smile.setIcon(new ImageIcon("src/images/smile).png"));
		JOptionPane.showMessageDialog(null, "You have won!", "Congretulatinons!", JOptionPane.INFORMATION_MESSAGE);
		WinWind win = new WinWind((int) (endTime - startTime) / 1000, length, height, bomb);
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dispose();

	}

	@SuppressWarnings("static-access")
	private void die() {
		for (int i = 0; i < picturesArray.length; i++) {
			for (int j = 0; j < picturesArray[0].length; j++) {
				if(mainArray[i][j][0]==20) {
				picturesArray[i][j] = new ImageIcon("src/images/dieIm.png");
				}
			}
		}
		model.setPicArr(picturesArray);
		table.updateUI();
		smile.setIcon(new ImageIcon("images/boom.png"));
		smile.setSize(50, 50);
		smile.updateUI();
		int i = JOptionPane.showConfirmDialog(null, "You have died, what would you like to restart?");
		if (i == 1) {
			System.exit(6);
		} else {
			restart();
		}
	}

	private void restart() {
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < length; j++) {
				picturesArray[i][j] = nul;
				mainArray[i][j][1] = 0;
			}
		}
		opened = 0;
		mines = 0;
		smile.setIcon(new ImageIcon("src/images/smile|.png"));
		TableMaker.setPicArr(picturesArray);
		table.updateUI();
	}

	@SuppressWarnings("static-access")
	private void open(int row, int col) {
		try {
			// yes, I'm lazy coder and I didn't know, how solve that problem fast...
			if (mainArray[row][col][1] != 1) {
				// if there is no bomb
				if (mainArray[row][col][0] != 20) {
					if (mainArray[row][col][1] == 2) {
						mines--;
					}
					// draw
					picturesArray[row][col] = new ImageIcon("src/images/" + mainArray[row][col][0] + ".png");
					model.setPicArr(picturesArray);
					table.updateUI();
					opened++;
					mainArray[row][col][1] = 1;
					// have you won?
					if (opened == height * length - bomb) {
						win();
					}
					// open around 0
					if (mainArray[row][col][0] == 0) {
						open(row + 1, col - 1);
						open(row + 1, col);
						open(row + 1, col + 1);
						open(row, col - 1);
						open(row, col + 1);
						open(row - 1, col - 1);
						open(row - 1, col);
						open(row - 1, col + 1);
					}
				} else {
					die();
					// what a pity
				}
			}
		} catch (IndexOutOfBoundsException ex) {
		}
	}

}
