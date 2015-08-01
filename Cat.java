package javastuff;

import java.io.ObjectInputStream.GetField;

public class Cat extends Animal
{
	public static final int STEPS_UNTIL_BREED = 12;
	public static final int STEPS_UNTIL_STARVE = 6;
	public static final int MAX_ATTEMPTS = 3;
	private int attempts;
	private int hunger;

	public Cat(World w, int x, int y)
	{
		super(w,x,y);
		hunger = 0;
		attempts = 0;
	}
	
	public void breed()
	{
		if (breedCount == STEPS_UNTIL_BREED)
		{
			System.out.println("CAT BREEDING");
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
	
	/**
	 * If a cat has not eaten for six steps, it starves to death.
	 * If a cat eats a pigeon, survives for 6 more steps
	 * If a cat eats a snail, survives for 1 more step
	 * 
	 * Example: if a cat can survive for 3 more steps before starving
	 * and eats a snail, it can then survive for four steps instead
	 * of three.
	 * 
	 * Note that a cat starves after six steps, not after moving from cell to cell six times. 
	 * Because a cat can move up to four times: 
	 *  - 3 times attempt to eat pigeons, 
	 *  - 1 more time if there's a snail nearby 
	 */
	public boolean starve()
	{
		if (hunger == STEPS_UNTIL_STARVE) {
			System.out.println("Cat died of starvation.");
			return true;
		}
		else
			return false;	
	}
	
	public String getChar()
	{
		return "C";
	}
	
	/**
	 * If there is a pigeon in an adjacent cell, it moves into that cell and eats the pigeon. 
	 * If there are no pigeons in adjacent cells, it moves same manner as snail.
	 * 
	 * It attempts to move up to three times unless it is unable to or has found a pigeon to eat. 
	 * If it has attempted to move three times and still has not found a pigeon to eat and
	 * there is a snail in an adjacent cell, it moves into that cell and eats snail.
	 */
	public void move()
	{
		boolean found = false;
		
		hunger++;
		
		//Attempt to find Pigeons 3 times
		for (attempts = 0; attempts < MAX_ATTEMPTS; attempts++) {
			found = checkPigeons("P");
			if (found == false)
				super.move();
		}
		
		if (attempts == 3) {
			System.out.print("bc:" + breedCount + " h:" + hunger);
			System.out.print(" C" + this.hashCode() + " could not find Pigeon. Looking for Snails.\n");
		}
		//Attempt to find Snails		
		checkSnails("S");
	}

	
	private boolean checkPigeons(String pigeon)
	{
		boolean found = false;
		
		if (x > 0 && world.get(x-1, y) != null)
		if (world.get(x-1, y).getChar() == "P") {
			System.out.println("Cat Moving UP to Pigeon...");
		
			//Eat the Snail by moving to it's spot
			world.set(x-1, y, this);	//Animal
			world.set(x, y, null);		//previous location to null
			
			//Update self
			--x;						//Update location
			this.setMoved(true);		//Update move status,
			
			hunger = 0;
			breedCount++;
		}
		else if (x < World.HEIGHT-1 && world.get(x+1, y) != null) 
		if (world.get(x+1, y).getChar() == "P") {
			System.out.println("Cat Moving DOWN to Pigeon...");
			
			world.set(x+1, y, this);
			world.set(x, y, null);
			++x;
			this.setMoved(true);
			hunger = 0;
			breedCount++;
		}
		else if (y > 0 && world.get(x, y-1) != null) 
		if (world.get(x, y-1).getChar() == "P") {
			System.out.println("Cat Moving LEFT to Pigeon...");

			world.set(x, y-1, this);
			world.set(x, y, null);				
			--y;
			this.setMoved(true);
			hunger = 0;
			breedCount++;
		}
		else if (y < World.WIDTH-1 && world.get(x, y+1) != null) 
		if (world.get(x, y+1).getChar() == "P") {
			System.out.println("Cat Moving RIGHT to Pigeon...");

			world.set(x, y+1, this);
			world.set(x, y, null);				
			++y;
			this.setMoved(true);
			hunger = 0;
			breedCount++;
		}
		
		if (found)
			return true;
		else
			return false;
	}
	
	private boolean checkSnails(String snails)
	{
		boolean found = false;
		
		if (x > 0 && world.get(x-1, y) != null)
			if (world.get(x-1, y).getChar() == "S") {
				System.out.println("Cat Moving UP to Snail...");
			
				//Eat the Snail by moving to it's spot
				world.set(x-1, y, this);	//Animal
				world.set(x, y, null);		//previous location to null
				
				//Update self
				--x;						//Update location
				this.setMoved(true);		//Update move status,
				if (hunger > 0)
					hunger--;
				breedCount++;
			}
		if (x < World.HEIGHT-1 && world.get(x+1, y) != null) 
			if (world.get(x+1, y).getChar() == "S") {
				System.out.println("Cat Moving DOWN to Snail...");
				
				world.set(x+1, y, this);
				world.set(x, y, null);
				++x;
				this.setMoved(true);
				if (hunger > 0)
					hunger--;
				breedCount++;
		}
		if (y > 0 && world.get(x, y-1) != null) 
			if (world.get(x, y-1).getChar() == "S") {
				System.out.println("Cat Moving LEFT to Snail...");

				world.set(x, y-1, this);
				world.set(x, y, null);				
				--y;
				this.setMoved(true);
				if (hunger > 0)
					hunger--;
				breedCount++;
		}
		if (y < World.WIDTH-1 && world.get(x, y+1) != null) 
			if (world.get(x, y+1).getChar() == "S") {
				System.out.println("Cat Moving RIGHT to Snail...");

				world.set(x, y+1, this);
				world.set(x, y, null);				
				++y;
				this.setMoved(true);
				if (hunger > 0)
					hunger--;
				breedCount++;
		}
		
		if (found)
			return true;
		else
			return false;
	}
	 
}
