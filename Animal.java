package PredPreySim;

/**
 * The Animal class allows animals a place in the world.
 * Each animal sees and lives in their own "world" by referencing the world.
 * One world holds all the animals by reference, while each animal references the world.
 */
public abstract class Animal
{
	protected int x, y;			// position in the world (width, height)
	protected boolean hasMoved;	// indicates if the animals has moved
	protected int breedCount;	// steps since breeding
	protected World world;		// world reference for updating
	
	/**
	 * Constructor for child classes
	 * Initializes the animal at the given position in the world
	 * Set the moved flag and breed count.
	 */
	protected Animal(World world, int x, int y)
	{
		this.x = x;
		this.y = y;
		hasMoved = false;
		breedCount = 0;
		world.set(x, y, (Animal) this); // Update World Reference with the Animal 
		this.world = world;				// Initialize the Animal's World Reference with current World reference.
										//  needed or get null pointer.
	}
	
	public boolean getMoved()
	{
		return hasMoved;
	}
	
	public void setMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
	}
	
	/**
	 * Randomly move directions
	 * 0 = up
	 * 1 = down
	 * 2 = left
	 * 3 = right
	 * Animal updates it's reference. World needs to update also.
	 */
	public void move()
	{
		int direction = (int) (Math.random() * 4);
		
		switch (direction)
		{
		case 0: // attempt to move up
			if (x > 0 && world.get(x-1, y) == null) {
				//System.out.println("Moving UP...");
				world.set(x-1, y, this);
				//System.out.println("Moving to " + this.x + "," + this.y);
				world.set(x, y, null);		// old position is empty, null
				//System.out.println("Moving from " + this.x + "," + this.y + " -> null.");
				--x;
				//System.out.println("Moved to..." + this.x + "," + this.y);
			}
			break;
		
		case 1: // attempt to move down
			if (x < World.HEIGHT-1 && world.get(x+1, y) == null) {
				//System.out.println("Moving DOWN...");
				world.set(x+1, y, this);
				//System.out.println("Moving to " + this.x + "," + this.y);
				world.set(x, y, null);
				//System.out.println("Moving from " + this.x + "," + this.y + " -> null.");
				++x;
				//System.out.println("Moved to..." + this.x + "," + this.y);
			}
			break;
		
		case 2: // attempt to move left
			if (y > 0 && world.get(x, y-1) == null) {
				//System.out.println("Moving LEFT...");
				world.set(x, y-1, this);
				//System.out.println("Moving to " + this.x + "," + this.y);
				world.set(x, y, null);				
				//System.out.println("Moving from " + this.x + "," + this.y + " -> null.");
				--y;
				//System.out.println("Moved to..." + this.x + "," + this.y);
			}
			break;
		
		case 3: // attempt to move right
			if (y < World.WIDTH-1 && world.get(x, y+1) == null) {
				//System.out.println("Moving RIGHT...");
				world.set(x, y+1, this);
				//System.out.println("Moving to " + this.x + "," + this.y);
				world.set(x, y, null);				
				//System.out.println("Moving from " + this.x + "," + this.y + " -> null.");
				++y;
				//System.out.println("Moved to..." + this.x + "," + this.y);
			}
			break;
		}
		breedCount++;
	}
	
	public abstract void breed();
	
	public abstract boolean starve();
	
	public abstract String getChar();
	
}
