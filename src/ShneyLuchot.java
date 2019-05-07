
import java.awt.Color;

import javax.swing.JPanel;

/*
 * Created on Mimuna 5767  upDate on Tevet 5770 
 */

/**
 * @author
 */

class ShneyLuchot extends Thread
{
	enum Modes {SHABBOS,WEEKDAY}
	enum State {RED,GREEN}
	Ramzor ramzor;
	JPanel panel;
	public Event64 m_modeQueue;
	public Event64 m_stateQueue;
	public Event64 m_ackQueue;
	Modes m_currentMode;
	State m_currentState;
	public ShneyLuchot( Ramzor ramzor,JPanel panel,Event64 modeQueue,Event64 stateQueue,Event64 ackQueue)
	{
		this.ramzor=ramzor;
		this.panel=panel;
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
						while(!m_modeQueue.arrivedEvent()) {
							switch (m_currentState) { // Switch deals with STATE-QUEUE
								case RED:
									setLight(1, Color.RED);
									setLight(2, Color.GRAY);
									m_stateQueue.waitEvent();
									sleep(1000);
									m_currentState = State.GREEN;
									break;

								case GREEN:
									setLight(1, Color.GRAY);
									setLight(2, Color.GREEN);
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
						setLight(1, Color.GRAY);
						setLight(2, Color.GRAY);
						while(!m_modeQueue.arrivedEvent());
						m_modeQueue.waitEvent();
						m_currentMode = Modes.WEEKDAY;
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
}
