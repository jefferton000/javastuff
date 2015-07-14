package cs220lab1;

/**
 *  Fields/Attributes of the Snail
 *  Does not:
 *	- Eat
 *	- Starve
 */
public class Snail extends Animal
{

	public static final int STEPS_TILL_BREED = 3;

    
	public Snail(World w, int x, int y)
    {
    	super(w,x,y);
    }

    public void move()
    {
    	super.move();
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


    public boolean starve()
    // Starve: Snails do not starve, returns false
    {
    	return false;
    }


    public String getChar()
    {
        return "S";
    }
}
