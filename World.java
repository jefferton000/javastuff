package PredPreySim;

/**
 * Represents and implements the world where all the animals reside.
 */
public class World {
	protected static final int HEIGHT = 20;
	protected static final int WIDTH = 20;
	private Animal[][] world;		// 2D array for animals to play in.
	
	/**
	 * Starts with empty world. 
	 */
	public World()
	{
		world = new Animal[WIDTH][HEIGHT];
		for (int i = 0; i < HEIGHT; i++)
			for (int j = 0; j < WIDTH; j++)
				world [i][j] = null;
	}
	
	/**
	 * If position is out-of-bounds or no animal is located there, return null.
	 * Otherwise, returns the animal at that position (x,y).   
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
	
	/**
	 * Set the animal position (x,y) within the world's bounds.
	 * Returns true is successful, otherwise false.
	 */
	public boolean set(int x, int y, Animal a)
	{
		boolean isSuccessful = false;
		
		if ((x > WIDTH) && (x < 0))
			isSuccessful = false;
		else if ((y > HEIGHT) && (y < 0))
			isSuccessful = false;
		else
		{
			world[x][y] = a;	//World reference accepts Animal reference
			isSuccessful = true;
		}
		
		return isSuccessful;
	}
	

	/**
	 * Display the inhabitants of the world.
	 */
	public void display()
	{
		for (int i = 0; i < World.WIDTH; i++) {
			for (int j = 0; j < World.HEIGHT; j++) {
				if (world[i][j] == null)
					System.out.print("-");
				else
					System.out.print(world[i][j].getChar());
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	/**
	 * 1. Each Animal attempts to move; (1)Cat, (2)Pigeon, (3)Snail, etc.
	 * 2. Check if any animals have starved.
	 * 3. Each Animal attempts to breed; (1)Cat, (2)Pigeon, (3)Snail, etc.
	 * 
	 * Tests to keep in mind after move()
	 * ----------------------------------
	 * 	if (world[i][j] == null)
	 * 		System.out.println("World is null at "+ i + ", " + j);
	 *  else
	 *  	System.out.println("Animal didn't move.");
	 *  System.out.println();
	 *  ----------------------------------
	 *  System.out.println(i +","+ j +" ("+ world[i][j].getChar() + ") MOVED.");
	 *  ----------------------------------
	 */
	public void simulateStep()
	{
		/**
		// Move the Cats
		for (int i = 0; i < World.WIDTH; i++)
			for (int j = 0; j < World.WIDTH; j++)
				if (world[i][j] != null && world[i][j].getMoved() == false)
					if (world[i][j].getChar() == "C")	
						world[i][j].move();
		*/
		
		// Move the Pigeons		
		for (int i = 0; i < World.WIDTH; i++)
			for (int j = 0; j < World.WIDTH; j++)
				if (world[i][j] != null && world[i][j].getMoved() == false)
					if (world[i][j].getChar() == "P")	
						world[i][j].move();
		
		// Move the Snails
		for (int i = 0; i < World.WIDTH; i++)
			for (int j = 0; j < World.WIDTH; j++)
				if (world[i][j] != null && world[i][j].getMoved() == false)
					if (world[i][j].getChar() == "S")	
						world[i][j].move();			
		
		//Check for starvation
		for (int i = 0; i < World.WIDTH; i++)
			for (int j = 0; j < World.WIDTH; j++)
				if (world[i][j] != null && world[i][j].getMoved() == true) {
					if (world[i][j].starve()) {
						world[i][j] = null;
						//System.out.println("Pigeon just starved. That means it died.");
					}
					else {
						//System.out.println("Still alive..");
						world[i][j].breed();	//test move()/starve() first
						world[i][j].setMoved(false);
					}
				}
		
	}
}
