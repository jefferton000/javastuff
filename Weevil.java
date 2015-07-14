package cs220lab1;
/**
 * Attempts to move a space
 * Breeds after three turns
 * However, it doesn't Eat or Starve
 */

public class Weevil extends Animal
{
    public static final int STEPS_TILL_BREED = 3;

    public Weevil(World w, int x, int y)
    {
    	super(w, x, y);
    }

    /* 
     *  Movement:  0 = up, 1 = down, 2 = left, 3 = right
     */
    public void move()
    {
    	// generate random int [0,3] for direction
    	int direction = (int) (Math.random() * 4);

    	switch (direction)
    	{
    	case 0:	// attempt to move up
    		if (x > 0 && world.get(x-1, y) == null) {
    			world.set(x-1, y, this);    // put weevil in new location
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
    }


    public void breed()
    {
    	if (breedCount == STEPS_TILL_BREED)
    	{
    		// attempt to make a new snail in an adjacent cell
    		if (x > 0 && world.get(x-1, y) == null) {
    			Snail s = new Snail(world, x-1, y);
    		}
    		else if (x < World.HEIGHT-1 && world.get(x+1, y) == null) {
    			Snail s = new Snail(world, x+1, y);
    		}
    		else if (y > 0 && world.get(x, y-1) == null) {
    			Snail s = new Snail(world, x, y-1);
    		}
    		else if (y < World.WIDTH-1 && world.get(x, y+1) == null) {
    			Snail s = new Snail(world, x, y+1);
    		}

    		breedCount = 0; // reset breed count
    	}
    }

    // Starve: Weevils do not starve, returns false
    public boolean starve()
    {
    	return false;
    }

    public String getChar()
    {
    	return "W";
    }
}
