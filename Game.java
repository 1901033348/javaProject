import javafx.application.Application;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;
import java.io.FileNotFoundException;
import java.util.List;

public class Game extends Application{
	private Map map;
	private MyBotPlayer player;
	private Food food;
	private Circle ball;
	// private Position pos;
	
	@Override
	public void start(Stage primaryStage) throws FileNotFoundException {
		//We use List to store ordered collection of elements
		List<String> arrayList = (List<String>) getParameters().getRaw();
		map=new Map(arrayList.get(0));//Displaying map.txt.
		player = new MyBotPlayer(map); //player object
		food = new Food(map, player); // food object

		// Pane pane=new Pane();
		BorderPane pane = new BorderPane();
		pane.setCenter(map);
		BorderPane.setAlignment(pane, Pos.TOP_CENTER);
		// pane.getChildren().add(map);


		Scene scene = new Scene(pane, map.getUnit()*map.getSize(), map.getUnit()*map.getSize());
 		primaryStage.setTitle("Game");//Title "Game"
		primaryStage.setScene(scene); // Place the Scene in the Stage
		primaryStage.show();//To show Game
		System.out.println(" Map size: " + map.getSize());

 		//Click the scene to work this cases => we use EventHandler
 		scene.setOnKeyPressed(e -> {
 			//This cases work when we clicked scene's case(Down,Up,Left,Right,E,F)
 		switch (e.getCode()) {
 			case DOWN: player.moveDown(); break;
 			case UP: player.moveUp(); break;
 			case LEFT: player.moveLeft(); break;
 			case RIGHT: player.moveRight(); break;
			case E:System.out.println(" E (eat) key pressed");
				player.feed(food);
				//goes to feed method
				player.eat();
				// 	When pressed "E" player eat food
				break;
			case F:System.out.println(" F (find) key pressed");
				player.feed(food);
				//goes to feed method
				player.find();
				// When pressed "F" player find and eat food
				break;
			case SPACE:
				//return that took points When clicked "Space"
				System.out.println(" Points: "+ food.getPoints());
 		}
				//We use break to work only one case of this cases
 		});
}
	public static void main(String[] args) {
		Application.launch(args);
	}
}