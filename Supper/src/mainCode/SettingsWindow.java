package mainCode;

import java.awt.Choice;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame {

	private Choice choiceB;
	private Choice choiceS;
	private int bomb, length, height;
	
	

	public SettingsWindow() {
		super("settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 500);
		draw();
		setVisible(true);
	}

	private void draw() {
		getContentPane().setBackground(SystemColor.text);
		getContentPane().setForeground(SystemColor.activeCaption);
		getContentPane().setLayout(null);

		JTextPane txtpnWelcomeToSupper = new JTextPane();
		txtpnWelcomeToSupper.setFont(new Font("Constantia", Font.PLAIN, 25));
		txtpnWelcomeToSupper.setText("Welcome to supper");
		txtpnWelcomeToSupper.setBounds(348, 47, 228, 57);
		getContentPane().add(txtpnWelcomeToSupper);

		JButton btnOk = new JButton("OK");
		btnOk.setBackground(SystemColor.activeCaption);
		btnOk.setFont(new Font("Constantia", Font.PLAIN, 20));
		btnOk.setForeground(SystemColor.BLUE);
		btnOk.setBounds(392, 350, 142, 50);
		btnOk.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				read();
				GameWindow game = new GameWindow(bomb, length, height);
				game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				dispose();
			}

			private void read() {
				try{
				bomb=Integer.parseInt(choiceB.getSelectedItem());
				length=Integer.parseInt(choiceS.getSelectedItem().substring(0, 2));
				height=Integer.parseInt(choiceS.getSelectedItem().substring(5));
				}catch(Exception e){
					JOptionPane.showMessageDialog(null, "You are wrong!(");
				}
			}
		});
		getContentPane().add(btnOk);

		JLabel lblBombs = new JLabel("Bombs:");
		lblBombs.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblBombs.setBounds(114, 182, 69, 20);
		getContentPane().add(lblBombs);
		
		choiceB = new Choice();
		choiceB.add("10");
		choiceB.add("25");
		choiceB.add("35");
		choiceB.add("50");
		choiceB.add("80");
		choiceB.add("100");

		choiceB.setBounds(200, 182, 69, 20);
		getContentPane().add(choiceB);

		JLabel lblSize = new JLabel("Size:");
		lblSize.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblSize.setBounds(114, 262, 69, 20);
		getContentPane().add(lblSize);
		
		choiceS = new Choice();
		choiceS.add("10 x 10");
		choiceS.add("20 x 20");
		choiceS.add("20 x 30");
		choiceS.add("30 x 40");
		choiceS.add("30 x 60");
		choiceS.setBounds(200, 262, 69, 20);
		getContentPane().add(choiceS);

		JLabel lblChooseSttingsFor = new JLabel("Choose sttings for new game:");
		lblChooseSttingsFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblChooseSttingsFor.setFont(new Font("Constantia", Font.PLAIN, 20));
		lblChooseSttingsFor.setBounds(299, 107, 301, 26);
		getContentPane().add(lblChooseSttingsFor);
		setVisible(true);

	}
}
