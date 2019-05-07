import java.awt.Color;

import javax.swing.JPanel;

/*
 * Created on Mimuna 5767  upDate on Tevet 5770 
 */

/**
 * @author
 */

public class ShloshaAvot extends Thread
{
	enum Modes {SHABBOS,WEEKDAY}
	enum State {RED,YELLOW,GREEN}
	Ramzor ramzor;
	JPanel panel;
	Event64 m_modeQueue;
	Event64 m_stateQueue;
	Event64 m_ackQueue;
	Modes m_currentMode;
	State m_currentState;
	private boolean stop=true;
	public ShloshaAvot( Ramzor ramzor,JPanel panel,int key,Event64 modeQueue,Event64 stateQueue,Event64 ackQueue)
	{
		this.ramzor=ramzor;
		this.panel=panel;
		new CarsMaker(panel,this,key);
		this.m_currentMode = Modes.WEEKDAY;
		this.m_modeQueue = modeQueue;
		this.m_currentState=State.RED;
		this.m_stateQueue = stateQueue;
		this.m_ackQueue = ackQueue;
		start();
	}

	public void run()
	{
		try 
		{
			while (true)
			{
				switch(m_currentMode) {
					case WEEKDAY:
						while(!m_modeQueue.arrivedEvent()){
							switch(m_currentState) { // Switch deals with STATE-QUEUE
								case RED:
									setLight(3, Color.GRAY);
									setLight(1, Color.RED);
									setLight(2, Color.GRAY);
									m_stateQueue.waitEvent();
									sleep(1000);
									m_currentState = State.YELLOW;
									break;

								case YELLOW:
									setLight(1, Color.GRAY);
									setLight(2, Color.YELLOW);
									sleep(1000);
									m_currentState = State.GREEN;
									break;

								case GREEN:
									setLight(2, Color.GRAY);
									setLight(3, Color.GREEN);
									m_stateQueue.waitEvent();
									m_currentState = State.RED;
									m_ackQueue.sendSyncEvent();
									break;
							}
						}
						m_modeQueue.waitEvent();
						m_currentMode=Modes.SHABBOS;
						break;

					case SHABBOS:
						while(!m_modeQueue.arrivedEvent()){
							sleep(1000);
							setLight(1, Color.GRAY);
							sleep(1000);
							setLight(2, Color.YELLOW);
						}

						m_modeQueue.waitEvent();
						m_currentMode=Modes.WEEKDAY;
						m_currentState =State.RED;
						break;
				}
			}
		} catch (InterruptedException e) {}

	}
	public void setLight(int place, Color color)
	{
		ramzor.colorLight[place-1]=color;
		panel.repaint();
	}

	public boolean isStop()
	{
		return stop;
	}
}
