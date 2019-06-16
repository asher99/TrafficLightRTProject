import javax.swing.JPanel;

/*
 * Created on Tevet 5770 
 */

/**
 * @author
 */

public class CarsMaker extends Thread
{
	JPanel myPanel;
	private ShloshaAvot myRamzor;
	int key;
	int num;
	public CarsMaker(JPanel myPanel,ShloshaAvot myRamzor, int key) 
	{
		this.myPanel=myPanel;
		this.myRamzor=myRamzor;
		this.key=key;
		num=1;
		setDaemon(true);
		start();
	}

	public void run()
	{
		try {
			while (true)
			{
				sleep(300);
				if ( !myRamzor.isStop())
				{
					new CarMoovingWithNum(myPanel,myRamzor,key,num++);
				}
				else{num =1;}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
