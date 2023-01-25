import javafx.geometry.Pos;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class MyPlayer implements Player{
	private Circle ball;
	protected Map map;
	private Position position;
	private Pane ballPane;
	private int size;
	private int unit;

	MyPlayer(Map map){
		this.map=map;
		this.size=map.getSize();
		this.unit=map.getUnit();
		this.position=map.getStartPosition();
		this.ballPane=new Pane();
		map.getChildren().add(ballPane);
		ball=new Circle(map.getStartPosition().getX()* map.getUnit() +map.getUnit()/2,map.getStartPosition().getY()*map.getUnit()+map.getUnit()/2, Math.min(150, 150) * 0.8 * 0.5 * 0.4,Color.FIREBRICK);
		ball.toFront();
		ballPane.getChildren().add(ball);
	}

	@Override
	//to move right side
	public void moveRight(){
		//Checks if there is is a way and if there is no walls on the down side of the player => it moves
		if(position.getX()+1<map.getSize() && map.getValueAt(position.getX()+1,position.getY())==0){
			position.setX(position.getX()+1);
			// System.out.println(position.getX()+" "+position.getY());
			this.ball.setCenterX(ball.getCenterX() <(map.getSize()-1)*map.getUnit() ? ball.getCenterX() + map.getUnit() : ball.getCenterX());
		}
		else {
			//If there is no way => print " Invalid Position !"
			System.out.println(" Invalid Position !");
		}
	}
	@Override
	//to move left side
	public void moveLeft(){
		//Checks if there is is a way and if there is no walls on the down side of the player => it moves
		if(position.getX()-1>=0 && map.getValueAt(position.getX()-1,position.getY())==0){
			position.setX(position.getX()-1);
			// System.out.println(position.getX()+" "+position.getY());
			this.ball.setCenterX(ball.getCenterX() > map.getUnit()/2 ? ball.getCenterX() - map.getUnit() : ball.getCenterX());}
		else {
			//If there is no way => print " Invalid Position !"
			System.out.println(" Invalid Position !");
		}
	}
	@Override
	//to move down side
	public void moveDown(){
		//Checks if there is is a way and if there is no walls on the down side of the player => it moves
		if(position.getY()+1<map.getSize() && map.getValueAt(position.getX(),position.getY()+1)==0){
			position.setY(position.getY()+1);
			// System.out.println(position.getX()+" "+position.getY());
			this.ball.setCenterY(ball.getCenterY() < (map.getSize()-1)*map.getUnit() ? ball.getCenterY() + map.getUnit() : ball.getCenterY());}
		else {
			//If there is no way => print " Invalid Position !"

			System.out.println(" Invalid Position !");
		}
	}
	@Override
	//to move up side
	public void moveUp(){
		//Checks if there is is a way and if there is no walls on the down side of the player => it moves
		if(position.getY()-1>=0 && map.getValueAt(position.getX(),position.getY()-1)==0){
			position.setY(position.getY()-1);
			// System.out.println(position.getX()+" "+position.getY());
			this.ball.setCenterY(ball.getCenterY() > map.getUnit()/2 ? ball.getCenterY() - map.getUnit() : ball.getCenterY()); }
		else {
			//If there is no way => print " Invalid Position !"
			System.out.println(" Invalid Position !");
		}}

	@Override
	//to return position of player
	public Position getPosition(){
		return position;
	}
}