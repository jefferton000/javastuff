package PredPreySim;


public class Pigeon extends Animal
{
	public static final int STEPS_UNTIL_BREED = 8;
	public static final int STEPS_UNTIL_STARVE = 3;
	private int steps;
	
	public Pigeon(World w, int x, int y)
	{
		super(w,x,y);
		steps = 0;
	}
	
	public void breed()
	{
		if (breedCount == STEPS_UNTIL_BREED)
		{
			//System.out.println("PIGEON BREEDING");
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
			
			breedCount = 0;
		}
	}

	//If pigeon hasn't eaten for 3 steps, it starved to death. Gets removed from world.
	public boolean starve()
	{
		if (steps == STEPS_UNTIL_STARVE) {
			//System.out.println("Pigeon died of starvation.");
			return true;
		}
		else
			return false;
	}
	
	public String getChar()
	{
		return "P";
	}

	//If there is a snail in an adjacent cell, the pigeon eats it and moves to that cell.
	//Otherwise, pigeon moved like the snail.
	public void move()
	{//consider using a case statement to avoid over-moving.
		if (x > 0 && world.get(x-1, y) != null)
			if (world.get(x-1, y).getChar() == "S") {
				//System.out.println("Pigeon Moving UP to Snail...");
			
				//Eat the Snail by moving to it's spot
				world.set(x-1, y, this);	//Animal
				world.set(x, y, null);		//previous location to null
				
				//Update self
				--x;						//Update location
				this.setMoved(true);		//Update move status,
				steps = 0;
				breedCount++;
			}
		if (x < World.HEIGHT-1 && world.get(x+1, y) != null) 
			if (world.get(x+1, y).getChar() == "S") {
				//System.out.println("Pigeon Moving DOWN to Snail...");
				
				world.set(x+1, y, this);
				world.set(x, y, null);
				++x;
				this.setMoved(true);
				steps = 0;
				breedCount++;
		}
		if (y > 0 && world.get(x, y-1) != null) 
			if (world.get(x, y-1).getChar() == "S") {
				//System.out.println("Pigeon Moving LEFT to Snail...");

				world.set(x, y-1, this);
				world.set(x, y, null);				
				--y;
				this.setMoved(true);
				steps = 0;
				breedCount++;
		}
		if (y < World.WIDTH-1 && world.get(x, y+1) != null) 
			if (world.get(x, y+1).getChar() == "S") {
				//System.out.println("Pigeon Moving RIGHT to Snail...");

				world.set(x, y+1, this);
				world.set(x, y, null);				
				++y;
				this.setMoved(true);
				steps = 0;
				breedCount++;
		}
		
		if (getMoved() == false) {
			super.move();
			steps++;
		}
		//System.out.println("breedcount:" + breedCount);
		//System.out.println("steps:" + steps);
	}
	
}
