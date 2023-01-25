import javafx.geometry.Pos;

public class Position{
	private int x=0;
	private int y=0;
	Position post;
	int dist;
	

	//to match new coordinate X with previous X coordinate
	//to match new coordinate Y with previous Y coordinate
	Position(int newX , int newY){
		//Standard constructor for Position class
		x = newX;
		y = newY;
	}

	Position(int newX, int newY, int newDistance, Position newPost) {
		x = newX;
		y = newY;
		dist = newDistance;
		post = newPost;
	}

	//setter method for the X coordinate
	//to match new coordinate X with previous X coordinate
	public void setX(int newX){
		x=newX;
	}
	//setter method for the Y coordinate
	//to match new coordinate Y with previous Y coordinate
	public void setY(int newY){
		y=newY;
	}
	//getter method for the X coordinate
	//to return X's corrdinate
	public int getX(){ return x;}

	//getter method for the Y coordinate
	//to return Y's corrdinate
	public int getY(){return y;}
	//it check that with position's X coordinate with previous X coordinate
//it check that with position's Y coordinate with previous Y coordinate
	public boolean equals(Position pos) {
		return (this.x == pos.getX( ) && this.y == pos.getY( ));
	}
}
