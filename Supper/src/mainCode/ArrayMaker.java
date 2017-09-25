package mainCode;

import java.util.Random;

public class ArrayMaker {
	 public final byte LENGTH=30;
	    public final byte HEIGTH=16;
	    public final byte BOMBS=100;

	    public byte[][] getArray() {
	        return array;
	    }

	    public void setArray(byte[][] array) {
	        this.array = array;
	    }

	    byte[][] array;
	    byte[][][] bombArray;
	    public ArrayMaker() {
	        arrayMaker();
	        randomizerOfArray();
	        numerStander();
	       show();
	    }
	    private void show() {
	        for(byte i=0; i<array.length; i++){
	            for(byte j=0; j<array[0].length; j++){
	                System.out.print("\t"+array[i][j]);
	            }
	            System.out.println();
	        }
	    }

	    private void arrayMaker() {
	        array= new byte[LENGTH][HEIGTH];
	        for(byte i=0; i<array.length; i++){
	            for(byte j=0; j<array[i].length; j++){
	                array[i][j]=0;
	            }
	        }
	    }

	    private void randomizerOfArray() {
	        bombArray= new byte[BOMBS][1][1];
	        for(byte i=0; i<BOMBS; i++){
	            byte[][] cord=randomCord();
	            while(array[cord[0][0]][cord[1][0]]==20){
	                cord=randomCord();
	            }
	            array[cord[0][0]][cord[1][0]]=20;
	            bombArray[i]=cord;
	        }
	    }

	    private byte[][] randomCord() {
	        byte[][] cord=new byte[2][1];
	        Random randX=new Random();
	        Random randY=new Random();
	        cord[0][0]=(byte)randX.nextInt(array.length-1);
	        cord[1][0]=(byte)randY.nextInt(array[0].length-1);
	        return cord;
	    }

	    private void numerStander() {
	        for(byte[][] cord:bombArray){
	            byte x=cord[0][0];
	            byte y=cord[1][0];
	            for (byte X = (byte) (x - 1); X < (byte) (x + 2); X++) {
	                for (byte Y = (byte) (y - 1); Y < (byte) (y + 2); Y++) {
	                    try {
	                        if(array[X][Y]!=20) {
	                            array[X][Y]++;
	                        }
	                    }catch (IndexOutOfBoundsException ex){

	                    }
	                }
	            }




	        }
	    }
}
