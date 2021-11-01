package demolition;

/**Interface that is implemented by all movingobjects which could move in specific cardinal directions */
public interface Movable {
    
    /**Specifies what the object should do when trying to move up */
    public void moveUp();

    /**Specifies what the object should do when trying to move right */
    public void moveRight();

    /**Specifies what the object should do when trying to move down */
    public void moveDown();

    /**Specifies what the object should do when trying to move left */
    public void moveLeft();
}
