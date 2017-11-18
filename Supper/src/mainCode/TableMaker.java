package mainCode;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TableMaker implements TableModel {
	private static ImageIcon[][] picArr;

	public static ImageIcon[][] getPicArr() {
		return picArr;
	}

	public static void setPicArr(ImageIcon[][] picArr) {
		TableMaker.picArr = picArr;
	}

	public TableMaker(int length, int height) {
		picArr = new ImageIcon[height][length];
		for (int i = 0; i < height; i++) {
			for (byte j = 0; j < length; j++) {
				picArr[i][j] = new ImageIcon("src/images/null.png");
			}
		}
	}

	private static ArrayList<TableModelListener> list = new ArrayList<TableModelListener>();

	@Override
	public void addTableModelListener(TableModelListener e) {
		list.add(e);
	}

	@Override
	public Class<?> getColumnClass(int col) {
		return ImageIcon.class;
	}

	@Override
	public int getColumnCount() {
		return picArr[0].length;
	}

	@Override
	public String getColumnName(int name) {
		return null;
	}

	@Override
	public int getRowCount() {
		return picArr.length;
	}

	@Override
	public Object getValueAt(int x, int y) {
		return picArr[x][y];
	}

	@Override
	public boolean isCellEditable(int arg0, int arg1) {
		return false;
	}

	@Override
	public void removeTableModelListener(TableModelListener index) {
		list.remove(index);
	}

	@Override
	public void setValueAt(Object obj, int x, int y) {
		picArr[x][y] = (ImageIcon) obj;
	}

}
