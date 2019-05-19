import javax.swing.*;

public class PedestrianMoving extends Thread {

    JLabel myLabel;
    JPanel myPanel;
    private ShneyLuchot myRamzor;
    private int key;
    int x, dx;
    int y, dy;
    ImageIcon imageIcon;
    boolean first2=true;
    public PedestrianMoving(JPanel myPanel, ShneyLuchot myRamzor,int key)
    {
        this.myPanel=myPanel;
        this.myRamzor=myRamzor;
        this.key=key;
        setPedestrianLocationAndMooving();
        imageIcon = getImageIcon();
        myLabel= new JLabel(imageIcon);
        myLabel.setOpaque(false);
        myPanel.add(myLabel);
        setDaemon(true);
        start();
    }
    private void setPedestrianLocationAndMooving()
    {
        switch (key)
        {
            case 1:
                x=570;  dx=0;
                y=0;  dy=50;
                break;
            case 2:
                x=570;  dx=0;
                y=170;  dy=40;
                break;
            case 3:
                x=570;  dx=-50;
                y=440;  dy=0;
                break;
            case 4:
                x=200;  dx=-50;
                y=440;  dy=90;
                break;

            case 5:
                x=200;  dx=0;
                y=440;  dy=-40;
                break;
            case 6:
                x=200;  dx=0;
                y=250;  dy=-40;
                break;
            default:
                x=900;  dx=-50;
                y=100;  dy=0;
                break;
        }
    }
    public void run()
    {
        myLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());

        while (!finish())
        {
            if (myRamzor.isStop() && toStop())
                ;
            else
            {
                x +=dx;
                y +=dy;
                myLabel.setBounds(x, y, imageIcon.getIconWidth(), imageIcon.getIconHeight());
            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            myPanel.repaint();
        }

    }
    private boolean finish()
    {
        switch (key)
        {
            case 1:
                return y>170;
            case 2:
                return y>440;
            case 3:
               return  x<200;
            case 4:
                return x<100 && y<600;
            case 5:
                return y<250;
            case 6:
                return y<0;
        }
        return false;
    }
    private boolean toStop()
    {
        switch (key)
        {
            case 1:
                return y>170;
            case 2:
                return y>440;
            case 3:
                return x<200;
            case 4:
                return x<100 && y<600;
            case 5:
                return y<250;
            case 6:
                return y<0;
        }
        return false;
    }
    private ImageIcon getImageIcon()
    {
        switch (key)
        {
            case 1:
                return  new ImageIcon("Images/walkWithdog.png");
            case 2:
                return  new ImageIcon("Images/walkWithdog.png");
            case 3:
                return  new ImageIcon("Images/walkWithdog.png");
            case 4:
                return  new ImageIcon("Images/walkWithdog.png");
            case 5:
                return  new ImageIcon("Images/walkWithdog.png");
            case 6:
                return  new ImageIcon("Images/walkWithdog.png");
            default:
                break;
        }
        return null;
    }


}
