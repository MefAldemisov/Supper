package mainCode;

import java.awt.Choice;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame {

	private JTextField le;
	private JTextField he;
	private JTextField bom;

	private Choice choiceB;
	private Choice choiceS;
	private int bomb, length, height;
	private static boolean selected1;

	public SettingsWindow() {
		super("settings");
		selected1 = true;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 500);
		draw();
		setVisible(true);
	}

	private void draw() {
		getContentPane().setBackground(SystemColor.text);
		getContentPane().setForeground(SystemColor.activeCaption);
		getContentPane().setLayout(null);

		JLabel txtpnWelcomeToSupper = new JLabel("Welcome to supper!");
		txtpnWelcomeToSupper.setFont(new Font("Ubuntu", Font.PLAIN, 25));
		txtpnWelcomeToSupper.setBounds(350, 50, 400, 60);
		getContentPane().add(txtpnWelcomeToSupper);

		JButton btnOk = new JButton("OK");
		btnOk.setBackground(new Color(173, 216, 250));
		btnOk.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		btnOk.setBounds(375, 350, 150, 50);
		btnOk.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					creationOfMainWind();
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
		btnOk.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				creationOfMainWind();
			}
		});
		btnOk.setFocusable(true);
		getContentPane().add(btnOk);

		JLabel lblBombs = new JLabel("Bombs:");
		lblBombs.setFont(new Font("Ubuntu", Font.BOLD, 20));
		lblBombs.setBounds(120, 250, 100, 20);
		getContentPane().add(lblBombs);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Ubuntu", Font.BOLD, 20));
		lblSize.setBounds(120, 275, 100, 20);
		getContentPane().add(lblSize);

		drawFirst();

		JLabel lblChooseSttingsFor = new JLabel("Choose settings for new game:");
		lblChooseSttingsFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseSttingsFor.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblChooseSttingsFor.setBounds(300, 100, 325, 26);
		getContentPane().add(lblChooseSttingsFor);

		JButton btnElse = new JButton("Else");

		btnElse.setBackground(new Color(173, 216, 250));
		btnElse.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		btnElse.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
		btnElse.setFont(new Font("Ubuntu", Font.PLAIN, 20));
		btnElse.setBounds(375, 250, 150, 50);
		btnElse.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (selected1) {
					getContentPane().remove(choiceB);
					getContentPane().remove(choiceS);
					bom = new JTextField(20);
					bom.setFont(new Font("Ubuntu", Font.PLAIN, 16));
					bom.setBounds(590, 250, 50, 20);
					getContentPane().add(bom);
					he = new JTextField(20);
					he.setFont(new Font("Ubuntu", Font.PLAIN, 16));
					he.setBounds(590, 275, 50, 20);
					getContentPane().add(he);
					le = new JTextField(20);
					le.setFont(new Font("Ubuntu", Font.PLAIN, 16));
					le.setBounds(640, 275, 50, 20);
					getContentPane().add(le);
					selected1 = false;
					update(getGraphics());
				} else {
					getContentPane().remove(bom);
					getContentPane().remove(he);
					getContentPane().remove(le);
					drawFirst();
					selected1 = true;

					update(getGraphics());
				}

			}
		});
		getContentPane().add(btnElse);
		setVisible(true);

	}

	private void drawFirst() {
		choiceB = new Choice();

		choiceB.add("100");
		choiceB.add("10");
		choiceB.add("25");
		choiceB.add("35");
		choiceB.add("50");
		choiceB.add("80");
		choiceB.setFont(new Font("Ubuntu", Font.BOLD, 16));
		choiceB.setForeground(new Color(173, 216, 250));

		choiceB.setBounds(230, 250, 80, 20);
		getContentPane().add(choiceB);

		choiceS = new Choice();
		choiceS.add("16 x 30");
		choiceS.add("10 x 10");
		choiceS.add("20 x 20");
		choiceS.add("20 x 30");
		choiceS.add("30 x 40");
		choiceS.add("30 x 60");
		choiceS.setFont(new Font("Ubuntu", Font.BOLD, 16));

		choiceS.setForeground(new Color(173, 216, 250));

		choiceS.setBounds(230, 275, 80, 20);
		getContentPane().add(choiceS);
	}

	private void readSecond() {
		try {
			bomb = Integer.parseInt(bom.getText());
			length = Integer.parseInt(le.getText());
			height = Integer.parseInt(he.getText());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "It isn't number! /n Change your input!", "Waring",
					JOptionPane.ERROR_MESSAGE);
		}
	}

	private void readFirst() {
		try {
			bomb = Integer.parseInt(choiceB.getSelectedItem());
			length = Integer.parseInt(choiceS.getSelectedItem().substring(0, 2));
			height = Integer.parseInt(choiceS.getSelectedItem().substring(5));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "You are wrong!(", "Waring", JOptionPane.ERROR_MESSAGE);
		}
	}

	private void creationOfMainWind() {
		// TODO Auto-generated method stub
		if (selected1) {
			readFirst();
		} else {
			readSecond();
		}
		if (bomb < length * height) {
			GameWindow game = new GameWindow(bomb, height, length);
			game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			dispose();
		} else {
			JOptionPane.showMessageDialog(null,
					"Number of bombs is more than the size of the field! /n Change your input!", "Waring",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
