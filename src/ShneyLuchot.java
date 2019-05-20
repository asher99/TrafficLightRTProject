
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
	enum Modes {OFF,SHABBOS,WEEKDAY}
	enum State {RED,GREEN}
	Ramzor ramzor;
	JPanel panel;
	public Event64 m_stateQueue;
	public Event64 m_ackQueue;
	Modes m_currentMode;
	State m_currentState;
	private boolean stop = false;
	public ShneyLuchot( Ramzor ramzor,JPanel panel,Event64 stateQueue,Event64 ackQueue, int key)
	{
		this.ramzor=ramzor;
		this.panel=panel;
		//new PedestrianMaker(panel,this,key);
		new PedestrianMaker(panel,this,key);
		this.m_currentMode = Modes.OFF;
		this.m_currentState=State.RED;
		this.m_stateQueue = stateQueue;
		this.m_ackQueue = ackQueue;


		start();
	}

	public void run()
	{
		Events ev = Events.TURN_RED;
		try 
		{
			while (true)
			{
				switch(m_currentMode) {
					case WEEKDAY:
						while(m_currentMode == Modes.WEEKDAY) {
							switch (m_currentState) { // Switch deals with STATE-QUEUE
								case RED:
									stop = true;
									setLight(1, Color.RED);
									setLight(2, Color.GRAY);
									ev = (Events)m_stateQueue.waitEvent();
									switch(ev){
										case TURN_GREEN:
											sleep(2000);
											m_currentState = State.GREEN;
											break;

                                        case ENTER_SHABBOS_MODE:
                                            m_currentMode=Modes.SHABBOS;
                                            break;

                                        case TURN_RED:
                                            m_ackQueue.sendEvent();
                                            break;
									}
									break;


								case GREEN:
									setLight(1, Color.GRAY);
									setLight(2, Color.GREEN);
									stop = false;
									ev = (Events)m_stateQueue.waitEvent();
                                    switch(ev){
                                        case TURN_RED:
                                            m_currentState = State.RED;
                                            m_ackQueue.sendEvent();
                                            break;

                                        case ENTER_SHABBOS_MODE:
                                            m_currentMode=Modes.SHABBOS;
                                            break;
                                    }
                                    break;
							}
						}


					case SHABBOS:
						setLight(1, Color.GRAY);
						setLight(2, Color.GRAY);
						//while(!m_stateQueue.arrivedEvent() ) {}
						ev = (Events) m_stateQueue.waitEvent();

                        switch(ev){
                            case ENTER_WEEKDAY_MODE:
                                /*setLight(1, Color.RED);
                                setLight(2, Color.GRAY);*/
                                m_currentMode = Modes.WEEKDAY;
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
