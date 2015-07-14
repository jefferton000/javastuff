package cs220lab1;

/**
 * 2D predator-prey simulator.
 * Output is displayed as characters.
 *
 */

import java.util.Scanner;

public class Simulator
{

    public static final int NUM_SNAILS = 5;		//100;
    public static final int NUM_PIGEONS = 1;	//10;
    public static final int NUM_CATS = 1;		//3;

    public static void main(String[] args)
    {
        World w = new World();

        /* 
         * Randomly place snails
         */
        int snailCount = 0;
        while (snailCount < NUM_SNAILS)
        {
            int x = (int) (Math.random() * World.HEIGHT);
            int y = (int) (Math.random() * World.WIDTH);
            if (w.get(x,y) == null)		// place only if empty
            {
                snailCount++;
                Snail s = new Snail(w,x,y);
            }
        }

        /* 
         * Randomly place pigeons
         */
        int pigeonCount = 0;
        while (pigeonCount < NUM_PIGEONS)
        {
            int x = (int) (Math.random() * World.HEIGHT);
            int y = (int) (Math.random() * World.WIDTH);
            if (w.get(x,y) == null) // place only if empty
            {
                pigeonCount++;
                Pigeon p = new Pigeon(w,x,y);
            }
        }

        /* 
         * Randomly place cats
         */
        int catCount = 0;
        while (catCount < NUM_CATS)
        {
        	int x = (int) (Math.random() * World.HEIGHT);
        	int y = (int) (Math.random() * World.WIDTH);
        	if (w.get(x,y) == null)	// place only if empty
        	{
        		catCount++;
        		Cat c = new Cat(w,x,y);
        	}
        }

        /*
         * Run simulation forever, until user cancels
         */
        Scanner keyboard = new Scanner(System.in);
        while (true)
        {
        	w.display();
        	w.simulateStep();
        	System.out.println("Press enter to continue");
        	keyboard.nextLine();
        }
    }
}