package mainCode;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class Gide extends JFrame {
	public Gide() {
		setBackground(Color.BLACK);
		setBounds(100, 100, 1200, 500);
		JLabel lab = new JLabel(new ImageIcon("src/images/desc.png"));
		add(lab);
		setVisible(true);

	}

}
