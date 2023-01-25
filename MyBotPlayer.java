import java.util.ArrayList;

//create MyBotPlayer class to work bot player
public class MyBotPlayer extends MyPlayer implements BotPlayer {
	private Food food;
	private Position pos1;
	private Position pos2;
	private boolean[][] isHaveWall;

	MyBotPlayer(Map map) {
		super(map);
	}

	@Override
	public void feed(Food f) {
		this.food = f;
		this.pos2 = getPosition( );
		this.pos1 = food.getPosition( );
	}

	// In a map without any walls
	// eats all food elements
	// by choosing the shortest array
	@Override
	public void eat() {
		new Thread(new Runnable( ) {
			@Override
			public void run() {
				while(food.getPosition( ) != null) {
					pos1 = food.getPosition( );
					for (int i = 0; i < Math.abs(pos2.getX( ) - pos1.getX( )); i++) {
						if ((pos2.getX( ) > pos1.getX( ))) {
							moveLeft( );
						} else {
							moveRight( );
						}
						try {
							Thread.sleep(150);
						} catch (InterruptedException ex) {
							ex.printStackTrace( );
						}
					}
					for (int j = 0; j < Math.abs(pos2.getY( ) - pos1.getY( )); j++) {
						if ((pos2.getY( ) > pos1.getY( ))) {
							moveUp( );
						} else {
							moveDown( );
						}
						try {
							Thread.sleep(150);
						} catch (InterruptedException ex) {
							ex.printStackTrace( );
						}
					}
					try {
						Thread.sleep(150);
					} catch (InterruptedException ex) {
						ex.printStackTrace( );
					}
				}
			}
		}).start( );
	}

	// In a customized map
	// finds a valid array to food and eats it
	@Override
	public void find(){
		//into this method we create map.getMap() method
		makeArrayOfWall(map.getMap());
		Thread find = new Thread(new Runnable(){
			public void run(){
				//while(true)
				while (food.getPosition()!=null){
					pos1=food.getPosition();
					//we call shortestPathWay method
					String array  = shortestPathWay();
					try{
					for(int i = 0; i<array.length();i++){
						if(array.charAt(i)=='W'){
							moveRight();}
						else if(array.charAt(i)=='B'){
							moveLeft();}
						else if(array.charAt(i)=='O'){
							moveDown();}
						else if(array.charAt(i)=='S'){
							moveUp();}
						try{Thread.sleep(200);}catch (InterruptedException ex){ex.printStackTrace();}
					}
					Thread.sleep(200);}
					catch(InterruptedException ex){ex.printStackTrace();}
				}
				}
		});
		find.start();
	}

	public void makeArrayOfWall(int[][] matrix){
		//equalize isHaveWall with map size[8][8]
		this.isHaveWall = new boolean[map.getSize()][map.getSize()];
		int b=0;
		while (b < map.getSize()){
			int a=0;
			while (a < map.getSize()){
				isHaveWall[a][b] = (matrix[a][b] == 1);
				a++;
				//collect the walls with position
			}
			b++;
			//add to into the map 
		}
	}
	//check that map have wall
	private boolean isHaveWall(int a, int b) {
		//return true if map have wall => in this situation {a==map.getSize() || b==map.getSize() || a>map.getSize() || b > map.getSize() || a<0 || b < 0}
		if(a==map.getSize() || b==map.getSize() || a>map.getSize() || b > map.getSize() || a<0 || b < 0){
			return true;}
		//return map that map doesn't have any walls on this way
		else{
			return isHaveWall[a][b];
		}
	}
	public String shortestPathWay() {
		//We create String array => size [8][8]
		String[][] array = new String[map.getSize( )][map.getSize( )];
		array[pos2.getX( )][pos2.getY( )] = " ";
		boolean ok = true;
		do{
			ok = false;
			for(int a=0;a<map.getSize();a++)
				for(int b=0;b<map.getSize();b++){
					//check a,b in map,if a,b position is in the walls position then continue with b++
					if (isHaveWall(a, b))
						continue;
					String waySoltustik = null, wayOntustik = null, wayBatys = null, wayWigis = null;
					String wayToMap = null;
					//check that Batys,Wigis,Ontustik,Soltustik have ways
					if (!isHaveWall(a - 1, b) && array[a - 1][b] != null)
						wayWigis = array[a - 1][b] + "W";
					if (!isHaveWall(a + 1, b) && array[a + 1][b] != null)
						wayBatys = array[a + 1][b] + "B";
					if (!isHaveWall(a, b - 1) && array[a][b - 1] != null)
						wayOntustik = array[a][b - 1] + "O";
					if (!isHaveWall(a, b + 1) && array[a][b + 1] != null)
						waySoltustik = array[a][b + 1] + "S";
					//find ways for Batys,Wigis,Ontustik,Soltustik
					if (wayWigis != null && (wayToMap == null || wayWigis.length( ) < wayToMap.length( )))
						wayToMap = wayWigis;
					if (wayBatys != null && (wayToMap == null || wayBatys.length( ) < wayToMap.length( )))
						wayToMap = wayBatys;
					if (wayOntustik != null && (wayToMap == null || wayOntustik.length( ) < wayToMap.length( )))
						wayToMap = wayOntustik;
					if (waySoltustik != null && (wayToMap == null || waySoltustik.length( ) < wayToMap.length( )))
						wayToMap = waySoltustik;

					if (wayToMap == null)
						continue;

					if (array[a][b] == null || wayToMap.length( ) < array[a][b].length( )) {
						array[a][b] = wayToMap;
						ok = true;
					}
			}
		}while (ok);
		//return food's position that ball must go to this position
		return array[pos1.getX( )][pos1.getY( )];
		}
}
