// The interface for the MyPlayer class to implement
public interface Player {
	//Player moves to right side
	void moveRight();

	//Player moves to left side
	void moveLeft();

	//Player moves to up side
	void moveUp();

	//Player moves to down side
	void moveDown();

	//goes to getPosition() method to return position
	Position getPosition();
}