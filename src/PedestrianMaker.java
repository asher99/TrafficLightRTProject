import javax.swing.*;

public class PedestrianMaker extends Thread {

    JPanel myPanel;
    private ShneyLuchot myRamzor;
    int key;
    public PedestrianMaker(JPanel myPanel,ShneyLuchot myRamzor, int key)
    {
        this.myPanel=myPanel;
        this.myRamzor=myRamzor;
        this.key=key;
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
                    new PedestrianMoving(myPanel,myRamzor,key);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
