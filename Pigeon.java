package cs220lab1;

/**
 *  Fields/Attributes of the Pigeon
 *  Does:
 *	- Eat
 *	- Starve
 */
public class Pigeon extends Animal
{
    public static final int STEPS_TILL_BREED = 8;

    public Pigeon(World w, int x, int y)
    {
    	super(w,x,y);
    }

    public void move()
    {
    	// move method
    }

    public void breed()
    {
    	if (breedCount == STEPS_TILL_BREED)
    	{
    		// attempt to make a new Pigeon in an adjacent cell
    		if (x > 0 && world.get(x-1, y) == null) {
    			Pigeon p = new Pigeon(world, x-1, y);
    		}
    		else if (x < World.HEIGHT-1 && world.get(x+1, y) == null) {
    			Pigeon p = new Pigeon(world, x+1, y);
    		}
    		else if (y > 0 && world.get(x, y-1) == null) {
    			Pigeon p = new Pigeon(world, x, y-1);
    		}
    		else if (y < World.WIDTH-1 && world.get(x, y+1) == null) {
    			Pigeon p = new Pigeon(world, x, y+1);
    		}

    		breedCount = 0; // reset breed count
    	}
    }


    public boolean starve()
    // Starve: Pigeons do not starve, returns false
    {
    	return true;
    }


    public String getChar()
    {
        return "P";
    }
} // end class Pigeon
