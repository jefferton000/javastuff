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
	
	//If a cat has not eaten for six steps, it starves to death.
	//will survive for 6 more steps if eats pigeon, 1 step if snail.
	//Example: if a cat can survive for 3 more steps before starving
	//and eats a snail, it can then survive for four steps instead
	//of three.
	//Note that a cat starves after six steps, not after moving from
	//cell to cell six times.	
	public boolean starve()
	{

		return false;
	}
	
	public String getChar()
	{
		return "C";
	}
	
	/**
	 * Every step, if there is a pigeon in an adjacent cell, it moves
	 * into that cell and eats the pigeon. If there are no pigeons in
	 * adjacent cells, it moves same manner as snail.
	 * It attempts to move up to three times unless it is unable to or
	 * has found a pigeon to eat. If it has attempted to move three
	 * times and still has not found a pigeon to eat and there is a 
	 * snail in an adjacent cell, it moves into that cell and eats snail.
	 */
	public void move()
	{
		//
		super.move();
	}
	
}
