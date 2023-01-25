import java.util.Scanner;
import java.io.FileReader;
import java.io.FileNotFoundException;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Map extends Pane{
	private int unit=50;//first line of txt
	private int size;//size of columns and rows
	private int[][] map;
	private Position start;

	//to find map1.txt and find that is this map1.txt define
	Map(String a) throws FileNotFoundException{
		//to add String d all rows of this map1.txt
		String d="";
		try{
			FileReader file =  new FileReader(a);
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine())
				d+=String.valueOf(sc.nextLine())+"\n";
		}catch(FileNotFoundException e) {
			e.printStackTrace();}
		// System.out.println(d);

		int counter=0;
		int con =0;
		String[] parts=d.split("\n");
		//parts divide all rows and add this divides to parts

		String f="";
		int t;
		t=Integer.valueOf(parts[0]);
		//map.getSize()=t;

		this.size=t;
		int map1[][]=new int[t][t];
		//number of map's rows and columns

		int counter2=0;
		//divides parts to the String list

		for(String e: parts){
			if(counter>0){
				f+=e+"\n";
				String par[]=e.split("\\s+");
				//took each numbers of columns and add to par

				for(String i:par){
					//to know each numbers in map

					con=Integer.valueOf(i);
					if(con==2){
						//if number is equal to 2 position start in this position
						start = new Position(counter2%t,counter-1);
						map1[counter2%t][counter-1]=0;
					}
					else{
						//if number is equal to one 1 or 0
						map1[counter2%t][counter-1]=con;}
					// System.out.println((counter-1)+" "+(counter2%t));
					counter2++;
				}}
			counter++;
		}
		this.map=map1;
		fillCell();}
	//to fill map with rectangles colors
	public void fillCell(){
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				//if number in map is equal to 0 or 2
				if(map[i][j]==0 || map[i][j]==2){
					//to fill empty rectangle
					Rectangle r = new Rectangle(i*unit,j*unit, unit, unit);//size of rectangle
					r.setStroke(Color.WHITE);
					r.setFill(Color.BLACK);
					super.getChildren().add(r);
				}
				//if number in map is equal to 1
				if(map[i][j]==1){
					//to fill rectangle with hindrance
					Rectangle r = new Rectangle(i*unit,j*unit, unit, unit);//size of rectangle
					r.setStroke(Color.BLACK);
					r.setFill(Color.BLUE);
					super.getChildren().add(r);
				}
			}
		}
	}
	//to return unit(line of text)
	public int getUnit(){
		return unit;
	}
	//to return size of map
	public int getSize(){
		return size;
	}
	//to return map in scene
	public int[][] getMap(){
		return map;
	}
	//getter method for map matrix
	public int getValueAt(int x,int y){
		return map[x][y];
	}
	//to return initial position player
	public Position getStartPosition(){
		return start;
	}
}