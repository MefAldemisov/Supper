package addition;

import java.awt.Font;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import mainCode.SettingsWindow;

@SuppressWarnings("serial")
public class WinWind extends JFrame implements Serializable {
	private static Font font;
	private static ArrayList<String> list;
	private static int index;
	private static JList<String> team;
	private static Scanner sc;
	private static Formatter f;
	// private static DefaultListModel teamModel;

	// everyone has different intellect, and amount of time, so, index- is a rub
	// marker of it
	public WinWind(int time, int length, int height, int bombs) {
		super("Records");
		font = new Font("Ubuntu", Font.BOLD, 18);
		setBounds(100, 100, 500, 300);
		// list= new ArrayList<Player>();
		list = new ArrayList<String>();
		ser();
		int number = sort(time);
		
		ScrollPane pane = new ScrollPane();
		pane.setBounds(100, 140, 300, 100);
		team = new JList<>();
		final DefaultListModel<String> teamModel = new DefaultListModel<>();
		for (int i = 0; i < list.size(); i++) {
			teamModel.addElement(list.get(i));
		}
		team.setModel(teamModel);
		team.setFont(font);
		team.setBounds(110, 140, 300, 100);
		pane.add(team);
		getContentPane().add(pane);
		
		String end = "";
		switch (number) {
		case 1:
			end = "-st";
			break;
		case 2:
			end = "-nd";
			break;
		case 3:
			end = "-rd";
			break;
		default:
			end = "-th";
			break;
		}

		if (length * height > 300 && bombs >= 50) {
			index = 1;
			if (index > 800 && bombs > 100) {
				index = 2;
			}
		} else {
			index = 0;
		}
		getContentPane().setLayout(null);

		JLabel message = new JLabel(
				"Congretulations! \n You are the " + number + end + "! \nEnter your name to save the record: ");
		message.setFont(font);
		message.setBounds(60, 27, 380, 56);
		getContentPane().add(message);

		JLabel qwName = new JLabel("Name: ");
		qwName.setFont(font);
		qwName.setBounds(100, 80, 80, 20);
		getContentPane().add(qwName);

		JTextField nameSet = new JTextField("anonimus");
		nameSet.setFont(font);
		nameSet.setBounds(168, 80, 74, 20);
		nameSet.setSize(50, 20);
		getContentPane().add(nameSet);

		JButton saveb = new JButton("Save");
		saveb.setBounds(new Rectangle(267, 80, 80, 20));
		saveb.setFont(font);
		// save.setBounds(0, 0, 80, 20);
		saveb.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			@Override
			public void actionPerformed(ActionEvent e) {
				list.set(number - 1, nameSet.getText() + " " + time);
				deser(list);
				SettingsWindow w=new SettingsWindow();
				dispose();
			}
		});

		saveb.setSize(80, 20);
		getContentPane().add(saveb);

		setResizable(false);
		setVisible(true);
	}

	private static int sort(int time) {
		for (int i = 1; i < list.size(); i++) {
			if (i <= 10) {
			}
			String[] a = list.get(i).split(" ");
			System.out.println(a[1]);
			if (time <= Integer.parseInt(a[1])) {
				return i;
			}
		}
		return list.size() + 1;

	}

	private static void deser(Object obj) {
		try {
			f = new Formatter("src/addition/list" + index + ".out");
			for (int i = 0; i < list.size(); i++) {
				f.format(list.get(i) + " \n");
			}
		} catch (Exception e) {

		}
		f.close();
		System.out.println("Written" + index);
	}

	private static void ser() {

		try {
			sc = new Scanner(new File("src/addition/list" + index + ".out"));
		} catch (Exception e) {
		}

		while (sc.hasNext()) {
			list.add(sc.nextLine());
		}
	}

}
