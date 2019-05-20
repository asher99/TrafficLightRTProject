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
	enum Modes {OFF,SHABBOS,WEEKDAY}
	enum State {RED,YELLOW,GREEN}
	Ramzor ramzor;
	JPanel panel;

	Event64 m_stateQueue;
	Event64 m_ackQueue;
	Modes m_currentMode;
	State m_currentState;
	private boolean stop = false;
	public ShloshaAvot( Ramzor ramzor,JPanel panel,int key,Event64 stateQueue,Event64 ackQueue)
	{
		this.ramzor=ramzor;
		this.panel=panel;
		new CarsMaker(panel,this,key);
		this.m_currentMode = Modes.OFF;
		this.m_currentState=State.RED;
		this.m_stateQueue = stateQueue;
		this.m_ackQueue = ackQueue;
		start();
	}

	public void run()
	{
		Events ev=Events.TURN_RED;
		try 
		{
			while (true)
			{
				switch(m_currentMode) {
					case WEEKDAY:
						while(m_currentMode == Modes.WEEKDAY){
							switch(m_currentState) { // Switch deals with STATE-QUEUE
								case RED:
									stop=true;
									setLight(3, Color.GRAY);
									setLight(1, Color.RED);
									setLight(2, Color.GRAY);
									ev = (Events)m_stateQueue.waitEvent();
									switch(ev){
										case TURN_GREEN:
											sleep(1000);
											m_currentState = State.YELLOW;
											break;

										case ENTER_SHABBOS_MODE:
											m_currentMode = Modes.SHABBOS;
											break;

										case TURN_RED:
											m_ackQueue.sendEvent();
											break;

									}
									break;

								case YELLOW:
									setLight(2, Color.YELLOW);
									sleep(1000);
									m_currentState = State.GREEN;
									break;

								case GREEN:
									setLight(1, Color.GRAY);
									setLight(2, Color.GRAY);
									setLight(3, Color.GREEN);
									stop=false;
									ev = (Events)m_stateQueue.waitEvent();
									switch(ev){
										case TURN_RED:
											m_currentState = State.RED;
											m_ackQueue.sendEvent();

											break;

										case ENTER_SHABBOS_MODE:
											m_currentMode = Modes.SHABBOS;
											break;
									}
									break;
							}
						}

					case SHABBOS:
						setLight(1,Color.GRAY);
						while(!m_stateQueue.arrivedEvent()){
						sleep(1000);
						setLight(2, Color.YELLOW);
						sleep(1000);
						setLight(2, Color.GRAY);
					}

						ev = (Events)m_stateQueue.waitEvent();
						switch(ev){
							case ENTER_WEEKDAY_MODE:
								setLight(2, Color.GRAY);
								setLight(1, Color.RED);
								m_currentMode=Modes.WEEKDAY;
								m_currentState =State.RED;
								m_ackQueue.sendEvent();
								break;
						}
						break;

					case OFF:
						m_stateQueue.waitEvent();
						m_currentMode = Modes.WEEKDAY;
						m_currentState =State.RED;
						m_ackQueue.sendEvent();
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
