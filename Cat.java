package PredPreySim;

public class Cat extends Animal
{
	public static final int STEPS_UNTIL_BREED = 12;
	
	public Cat(World w, int x, int y)
	{
		super(w,x,y);
	}
	
	public void breed()
	{
		if (breedCount == STEPS_UNTIL_BREED)
		{
			// attempt to make a new Cat in an adjacent cell
			if (x > 0 && world.get(x-1, y) == null) {
				Cat c = new Cat(world, x-1, y);
			}
			else if (x < World.HEIGHT-1 && world.get(x+1, y) == null) {
				Cat c = new Cat(world, x+1, y);
			}
			else if (y > 0 && world.get(x, y-1) == null) {
				Cat c = new Cat(world, x, y-1);
			}
			else if (y < World.WIDTH-1 && world.get(x, y+1) == null) {
				Cat c = new Cat(world, x, y+1);
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
		return "C";
	}
	
	public void move()
	{
		super.move();
	}
	
}
