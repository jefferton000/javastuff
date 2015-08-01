package javastuff;


public class Snail extends Animal
{
	public static final int STEPS_UNTIL_BREED = 3;
	
	public Snail(World w, int x, int y)
	{
		super(w,x,y);
	}
	
	public void breed()
	{
		if (breedCount == STEPS_UNTIL_BREED)
		{
			//System.out.println("SNAIL BREEDING");
			// attempt to make a new Snail in an adjacent cell
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
			
			breedCount = 0;
		}
	}
	
	public boolean starve()
	{
		return false;
	}
	
	public String getChar()
	{
		return "S";
	}
	
	public void move()
	{
		super.move();
	}

}
