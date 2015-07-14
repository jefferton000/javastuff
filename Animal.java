package cs220lab1;

/**
 *	The Animal class allows animals a place in the world.
 *	Hypothetically, there is only one world that all the animals live.
 *	However, each animal sees and lives in their own "world" by simply referring to the world.
 *	That is, one world references all animals, while each animal references their place in it.
 *	one-to-many, and many-to-one   
 */	
public abstract class Animal
{
	protected int x,y;				// position in the world (height, width)
	protected boolean hasMoved;		// indicates if animal has moved
	protected int breedCount;		// steps since breeding
	protected World world;	        // world reference for updating

	/* 
	 * Constructor for child classes 
	 * Initializes the animal at the given position in the world
	 * Sets the moved flag and breed count.
     */
	protected Animal(World world, int x, int y)
	{	
		this.x = x;
		this.y = y;
		hasMoved = false;
		breedCount = 0;
		world.set(x, y, (Animal) this);
		this.world = world;			// needed, or get null pointer
	}

  	/*
     * Returns true if the animal has attempted to move,
     * false otherwise.
 	 */
	public boolean getMoved()
	{
		return hasMoved;
	}

	public void setMoved(boolean hasMoved)
	{
		this.hasMoved = hasMoved;
	}
	
	/*
	 *  Directions: 0 = up, 1 = down, 2 = left, 3 = right
	 *  generated randomly
	 */
	public void move()
	{
		int direction = (int) (Math.random() * 4);

		switch (direction)
		{
		case 0:	// attempt to move up
			if (x > 0 && world.get(x-1, y) == null) {
				world.set(x-1, y, this);    // put animal in new location
				world.set(x, y, null);	    // null old position to empty
				--x;
			}
			break;
			
		case 1:	// attempt to move down
			if (x < World.HEIGHT-1 && world.get(x+1, y) == null) {
				world.set(x+1, y, this);
				world.set(x, y, null);
				++x;
			}
			break;
			
		case 2:	// attempt to move left
			if (y > 0 && world.get(x, y-1) == null) {
				world.set(x, y-1, this);
				world.set(x, y, null);
				--y;
			}
			break;
			
		case 3:	// attempt to move right
			if (y < World.WIDTH-1 && world.get(x, y+1) == null) {
				world.set(x, y+1, this);    // Move to new spot
				world.set(x, y, null);	    // Set current spot to empty
				++y;
			}
		}
		
		breedCount++;
    } // end move
	
	
	/* the animal attempts to breed */
	public abstract void breed();
	
	/* returns true if the animal has starved to death,
		false otherwise. */
	public abstract boolean starve();

	 /* returns the character representing the animal for
		console output. */
	public abstract String getChar();

} // end class Animal