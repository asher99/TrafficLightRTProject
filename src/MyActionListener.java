import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

import static java.lang.Thread.sleep;

/*
 * Created on Tevet 5770 
 */

/**
 * @author
 */


public class MyActionListener implements ActionListener
{
	Event64 m_pressedQueue;

	public MyActionListener(Event64 q){
		m_pressedQueue =q;

	}

	public void actionPerformed(ActionEvent e) 
	{
		JRadioButton butt=(JRadioButton)e.getSource();
		if(!butt.getName().equals("16")){
			m_pressedQueue.sendEvent(butt);
		}
		else{
			System.out.println("shabbos Button pressed");
		}
	}


}
