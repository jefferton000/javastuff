package cs220lab1;

/**
 *	This class is supposed to represent (and implement) the world
 *	in which all Animals reside. 
 */

public class World
{
    protected static final int HEIGHT = 20;
    protected static final int WIDTH = 20;
    private Animal[][] world;		// 2D array of animals
    		
   	/*
	 * default constructor.
	 * initializes all spaces to null.
 	 */
	public World()
	{
		world = new Animal[WIDTH][HEIGHT];
		for (int i = 0; i < HEIGHT; i++)
			for (int j=0; j < WIDTH; j++)
				world[i][j] = null;
	}

	/*
	 * Return the animal at position (x, y).
	 * If the position is out-of-bounds or no
	 * animal is at the location, return null.
     */
	public Animal get(int x, int y)
	{
		if ((x > WIDTH) && (x < 0))
			return null;
		else if ((y > HEIGHT) && (y < 0))
			return null;
		else
			return world[x][y];
	}

	/*
	 * Set the animal at position (x,y) if
     * the position is not out-of-bounds
     * Return true if successful,
     * false if not.
	 */
	public boolean set(int x, int y, Animal a)
	{
		boolean isSuccessful = false;
		
		if ((x > WIDTH) && (x < 0))
			isSuccessful = false;
		else if ((y > HEIGHT) && (y < 0))
			isSuccessful = false;
		else {
			this.world[x][y] = world[x][y];		
			isSuccessful = true;
		}
		
		return isSuccessful;
	}

	/*
	 * display the contents of the World as characters
	 */
	public void display()
	{
	    for (int i=0; i<World.WIDTH; i++) {
	    	for (int j=0; j<World.HEIGHT; j++) {
	    		if (world[i][j] == null)
	    			System.out.print(" ");
	    		else
	    			System.out.print(world[i][j].getChar());
		    }
		    System.out.println();
		}
		System.out.println();
		System.out.println();
	}

	/*
         * First, flag each animal as having not moved.
         * Next, have each animal attempt to move, in order of Cat, Pigeon, Snail.
         * Flag each one as having moved after attempting to move.
         * After that, check if any animal have starved to death.
         * Finally, have each animal breed if possible (in the order of movement).
	 */
	// to find what type of animal, use the getChar() method to get leter
	public void simulateStep()
	{
		for (int i=0; i<World.WIDTH; i++) {
	    	for (int j=0; j<World.HEIGHT; j++) {
	    		if(world[i][j] != null) {
	    			world[i][j].setMoved(false);
	    			world[i][j].move();
	    			world[i][j].hasMoved = true;
	    			world[i][j].starve();
	    			world[i][j].breed();
	    		}
	    	}
		}
		
	}

} // end class World
