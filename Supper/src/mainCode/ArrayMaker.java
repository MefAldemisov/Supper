package mainCode;

import java.util.Random;

public class ArrayMaker {
	public final int LENGTH;
	public final int HEIGTH;
	public final int BOMBS, x0, y0;

	public byte[][][] getArray() {
		return array;
	}

	public void setArray(byte[][][] array) {
		this.array = array;
	}

	byte[][][] array;
	byte[][][] bombArray;

	public ArrayMaker(int bomb, int length, int height, byte x0, byte y0) {
		BOMBS = bomb;
		LENGTH = length;
		HEIGTH = height;
		this.x0 = x0;
		this.y0 = y0;

		arrayMaker();
		randomizerOfArray();
		numerStander();
		// show();
	}

	@SuppressWarnings("unused")
	private void show() {
		for (byte i = 0; i < array.length; i++) {
			for (byte j = 0; j < array[0].length; j++) {
				System.out.print("\t" + array[i][j][0]);
			}
			System.out.println();
		}
	}

	private void arrayMaker() {
		array = new byte[LENGTH][HEIGTH][2];
		for (byte i = 0; i < array.length; i++) {
			for (byte j = 0; j < array[i].length; j++) {
				array[i][j][0] = 0;
			}
		}
	}

	private void randomizerOfArray() {
		bombArray = new byte[BOMBS][1][1];
		for (byte i = 0; i < BOMBS; i++) {
			byte[][] cord = randomCord();
			while (array[cord[0][0]][cord[1][0]][0] == 20
					|| (Math.abs(cord[0][0] - x0) <= 1 && Math.abs(cord[1][0] - y0) <= 1)) {
				cord = randomCord();
			}
			array[cord[0][0]][cord[1][0]][0] = 20;
			bombArray[i] = cord;
		}
	}

	private byte[][] randomCord() {
		byte[][] cord = new byte[2][1];
		Random randX = new Random();
		Random randY = new Random();
		cord[0][0] = (byte) randX.nextInt(array.length - 1);
		cord[1][0] = (byte) randY.nextInt(array[0].length - 1);
		return cord;
	}

	private void numerStander() {
		for (byte[][] cord : bombArray) {
			byte x = cord[0][0];
			byte y = cord[1][0];
			for (byte X = (byte) (x - 1); X < (byte) (x + 2); X++) {
				for (byte Y = (byte) (y - 1); Y < (byte) (y + 2); Y++) {
					try {
						if (array[X][Y][0] != 20) {
							array[X][Y][0]++;
						}
					} catch (IndexOutOfBoundsException ex) {

					}
				}
			}

		}
	}
}
