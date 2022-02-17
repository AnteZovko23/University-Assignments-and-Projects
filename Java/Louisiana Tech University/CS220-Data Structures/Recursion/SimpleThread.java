/**
 * Program generates two threads each of which displays its name 5 times then
 * quits. Used in CS346 (Operating Systems) Lab 4
 * 
 * (see The Java Tutorial, Lesson 15)
 * 
 * @author andrianoff
 * @version 15 Feb 2018
 */
public class SimpleThread extends Thread
{
	/**
	 * Constructs a SimpleThread with name name
	 * @param name name of thread
	 */
	public SimpleThread(String name)
	{
		super(name);
	}
	
	/**
	 * Thread's run method.<br>
	 * Displays name 5 times then declares "Done!"
	 */
	public void run()
	{
		for (int i = 0; i < 5; i++)
		{
			System.out.println(i + " " + getName());
			try
			{
				sleep((int) (Math.random() * 1000));
			} 
			catch (InterruptedException e)
			{}
		}
		System.out.println("Done! " + getName());
	}

	/**
	 * The main method creates two threads and launches them.
	 */
	public static void main(String[] args)
	{
//		SimpleThread st1 = new SimpleThread("Windows");
//		SimpleThread st2 = new SimpleThread("Linux");
		
		for(int i = 0; i < args.length; i++) {
			
			new SimpleThread(args[i]).start();
			
		}
		
//		st1.start();
//		st2.start();
	}
}

// 1. 
/*
0 Linux
1 Linux
1 Windows
2 Windows
2 Linux
3 Windows
3 Linux
4 Linux
4 Windows
Done! Windows
Done! Linux
*/ 
// The output is different each time because of the way the threads are 
// loaded into memory, depends on the location where they are stored so one thread
// might be accessed faster than the other


//2.
// a. Parent class: Thread
// b. Calls the constructor of the parent class to give each thread a name, in
// this case linux and windows
// c. The parent class -- Thread
// d. the run() method
// e. By calling the start method on the thread object

//3. 
/*
 * 0 Mac
0 Linux
0 Windows
0 Ubuntu
1 Mac
1 Ubuntu
2 Ubuntu
1 Linux
1 Windows
2 Mac
3 Mac
2 Linux
2 Windows
3 Ubuntu
3 Windows
3 Linux
4 Mac
4 Ubuntu
Done! Ubuntu
4 Windows
4 Linux
Done! Windows
Done! Mac
Done! Linux

*/

// 4.
