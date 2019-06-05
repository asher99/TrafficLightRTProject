
import javax.swing.*;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/*
 * Created on Mimuna 5767  upDate on Addar 5772 
 */

/**
 * @author
 */
public class BuildTrafficLight {
	enum Modes {SHABBOS, WEEKDAY}


	public  void startJunction() {
		final int numOfLights = 4 + 12 + 1;
		Client770 myClient = new Client770();

		Ramzor ramzorim[] = new Ramzor[numOfLights];
		ramzorim[0] = new Ramzor(3, 40, 430, 110, 472, 110, 514, 110);
		ramzorim[1] = new Ramzor(3, 40, 450, 310, 450, 352, 450, 394);
		ramzorim[2] = new Ramzor(3, 40, 310, 630, 280, 605, 250, 580);
		ramzorim[3] = new Ramzor(3, 40, 350, 350, 308, 350, 266, 350);

		ramzorim[4] = new Ramzor(2, 20, 600, 18, 600, 40);
		ramzorim[5] = new Ramzor(2, 20, 600, 227, 600, 205);
		ramzorim[6] = new Ramzor(2, 20, 600, 255, 600, 277);
		ramzorim[7] = new Ramzor(2, 20, 600, 455, 600, 433);
		ramzorim[8] = new Ramzor(2, 20, 575, 475, 553, 475);
		ramzorim[9] = new Ramzor(2, 20, 140, 608, 150, 590);
		ramzorim[10] = new Ramzor(2, 20, 205, 475, 193, 490);
		ramzorim[11] = new Ramzor(2, 20, 230, 475, 250, 475);
		ramzorim[12] = new Ramzor(2, 20, 200, 453, 200, 433);
		ramzorim[13] = new Ramzor(2, 20, 200, 255, 200, 277);
		ramzorim[14] = new Ramzor(2, 20, 200, 227, 200, 205);
		ramzorim[15] = new Ramzor(2, 20, 200, 18, 200, 40);

		ramzorim[16] = new Ramzor(1, 30, 555, 645);

		TrafficLightFrame tlf = new TrafficLightFrame("  installation of traffic lights", ramzorim);

		ShloshaAvot zero = new ShloshaAvot(ramzorim[0], tlf.myPanel, 1,  new Event64(), new Event64());
		ShloshaAvot one = new ShloshaAvot(ramzorim[1], tlf.myPanel, 2,  new Event64(), new Event64());
		ShloshaAvot two = new ShloshaAvot(ramzorim[2], tlf.myPanel, 3,  new Event64(), new Event64());
		ShloshaAvot three = new ShloshaAvot(ramzorim[3], tlf.myPanel, 4, new Event64(), new Event64());

		ShneyLuchot four = new ShneyLuchot(ramzorim[4], tlf.myPanel, new Event64(), new Event64(),1);
		ShneyLuchot five = new ShneyLuchot(ramzorim[5], tlf.myPanel, new Event64(), new Event64(),1);
		ShneyLuchot six = new ShneyLuchot(ramzorim[6], tlf.myPanel,  new Event64(), new Event64(),2);
		ShneyLuchot seven = new ShneyLuchot(ramzorim[7], tlf.myPanel,new Event64(), new Event64(),2);
		ShneyLuchot eight = new ShneyLuchot(ramzorim[8], tlf.myPanel, new Event64(), new Event64(),3);
		ShneyLuchot nine = new ShneyLuchot(ramzorim[9], tlf.myPanel,  new Event64(), new Event64(),4);
		ShneyLuchot ten = new ShneyLuchot(ramzorim[10], tlf.myPanel,  new Event64(), new Event64(),4);
		ShneyLuchot eleven = new ShneyLuchot(ramzorim[11], tlf.myPanel,  new Event64(), new Event64(),3);
		ShneyLuchot twelve = new ShneyLuchot(ramzorim[12], tlf.myPanel,  new Event64(), new Event64(),5);
		ShneyLuchot thirteen = new ShneyLuchot(ramzorim[13], tlf.myPanel, new Event64(), new Event64(),5);
		ShneyLuchot fourteen = new ShneyLuchot(ramzorim[14], tlf.myPanel, new Event64(), new Event64(),6);
		ShneyLuchot fifteen = new ShneyLuchot(ramzorim[15], tlf.myPanel, new Event64(), new Event64(),6);

		Echad sixteen = new Echad(ramzorim[16], tlf.myPanel);

		Modes currentMode = Modes.WEEKDAY;
		Phase currentPhase = Phase.INIT;

		ArrayList<Integer> int_to_phaseA = new ArrayList<Integer>();
		int_to_phaseA.add(0);
		int_to_phaseA.add(6);
		int_to_phaseA.add(7);
		int_to_phaseA.add(9);
		int_to_phaseA.add(10);
		int_to_phaseA.add(12);
		int_to_phaseA.add(13);

		ArrayList<Integer> int_to_phaseB = new ArrayList<Integer>();
		int_to_phaseB.add(1);
		int_to_phaseB.add(4);
		int_to_phaseB.add(5);
		int_to_phaseB.add(6);
		int_to_phaseB.add(7);
		int_to_phaseB.add(9);
		int_to_phaseB.add(10);
		int_to_phaseB.add(12);
		int_to_phaseB.add(13);

		ArrayList<Integer> int_to_phaseC = new ArrayList<Integer>();
		int_to_phaseC.add(2);
		int_to_phaseC.add(3);
		int_to_phaseC.add(4);
		int_to_phaseC.add(5);
		int_to_phaseC.add(8);
		int_to_phaseC.add(11);
		int_to_phaseC.add(14);
		int_to_phaseC.add(15);


		ArrayList<Object> phaseA = new ArrayList<Object>();
		phaseA.add(zero);
		phaseA.add(six);
		phaseA.add(seven);
		phaseA.add(nine);
		phaseA.add(ten);
		phaseA.add(twelve);
		phaseA.add(thirteen);

		ArrayList<Object> phaseB = new ArrayList<Object>();
		phaseB.add(one);
		phaseB.add(four);
		phaseB.add(five);
		phaseB.add(six);
		phaseB.add(seven);
		phaseB.add(nine);
		phaseB.add(ten);
		phaseB.add(twelve);
		phaseB.add(thirteen);

		ArrayList<Object> phaseC = new ArrayList<Object>();
		phaseC.add(two);
		phaseC.add(three);
		phaseC.add(four);
		phaseC.add(five);
		phaseC.add(eight);
		phaseC.add(eleven);
		phaseC.add(fourteen);
		phaseC.add(fifteen);

		ArrayList<ArrayList<Object>> phaseList = new ArrayList<ArrayList<Object>>();
		phaseList.add(phaseA);
		phaseList.add(phaseB);
		phaseList.add(phaseC);

		Event64 buttonQueue = new Event64();
		Event64 evq = new Event64();
		MyActionListener myListener = new MyActionListener(evq);
		ButtonHandler bth = new ButtonHandler(evq,buttonQueue);

		JRadioButton butt[] = new JRadioButton[13];
		for (int i = 0; i < butt.length - 1; i++) {
			butt[i] = new JRadioButton();
			butt[i].setName(Integer.toString(i + 4));
			butt[i].setOpaque(false);
			butt[i].addActionListener(myListener);
			tlf.myPanel.add(butt[i]);
		}
		bth.setButtons(butt);
		butt[0].setBounds(620, 30, 18, 18);
		butt[1].setBounds(620, 218, 18, 18);
		butt[2].setBounds(620, 267, 18, 18);
		butt[3].setBounds(620, 447, 18, 18);
		butt[4].setBounds(566, 495, 18, 18);
		butt[5].setBounds(162, 608, 18, 18);
		butt[6].setBounds(213, 495, 18, 18);
		butt[7].setBounds(240, 457, 18, 18);
		butt[8].setBounds(220, 443, 18, 18);
		butt[9].setBounds(220, 267, 18, 18);
		butt[10].setBounds(220, 218, 18, 18);
		butt[11].setBounds(220, 30, 18, 18);

		butt[12] = new JRadioButton();
		butt[12].setName(Integer.toString(16));
		butt[12].setBounds(50, 30, 55, 20);
		butt[12].setText("SHABBOS");
		butt[12].setOpaque(false);
		butt[12].addActionListener(myListener);
		tlf.myPanel.add(butt[12]);
		int butEv=0;
		try {
			while (true) {

				switch (currentMode) {

					case WEEKDAY:
						while (!butt[12].isSelected()) {
							switch (currentPhase) {
								case INIT:
									init(phaseList); //sets all to red and receives ack from all
									currentPhase = Phase.PHASE_A;
									break;
								case PHASE_A:
									turnGreen(phaseA);
									sleep(5000);
									turnRed(phaseA);
									if(buttonQueue.arrivedEvent()){
										butEv = (int)buttonQueue.waitEvent();
										if(int_to_phaseC.contains(butEv) ){
											currentPhase = Phase.PHASE_C;
										}
										else{
											currentPhase = Phase.PHASE_B;
										}
									}
									else{
									currentPhase = Phase.PHASE_B;
									}
									break;
								case PHASE_B:
									turnGreen(phaseB);
									sleep(5000);
									turnRed(phaseB);
									if(buttonQueue.arrivedEvent()){
										butEv = (int)buttonQueue.waitEvent();
										if(int_to_phaseA.contains(butEv) ){
											currentPhase = Phase.PHASE_A;
										}
										else{
											currentPhase = Phase.PHASE_C;
										}
									}
									else {
										currentPhase = Phase.PHASE_C;
									}
									break;
								case PHASE_C:
									turnGreen(phaseC);
									sleep(5000);
									turnRed(phaseC);
									if(buttonQueue.arrivedEvent()){
										butEv = (int)buttonQueue.waitEvent();
										if(int_to_phaseB.contains(butEv) ){
											currentPhase = Phase.PHASE_B;
										}
										else{
											currentPhase = Phase.PHASE_A; }
									}
									else {
										currentPhase = Phase.PHASE_A;
									}
									break;
							}
						}
						disableButtons(butt);
						enterShabbosMode(phaseList);
						currentMode = Modes.SHABBOS;
						break;

					case SHABBOS:
						if(!butt[12].isSelected()){
							enterWeekDayMode(phaseList);
							currentMode = Modes.WEEKDAY;
							currentPhase = Phase.PHASE_A;
							enableButtons(butt);
						}
						break;
				}
			}
		}catch (InterruptedException e){}
	}


	public  void init(ArrayList<ArrayList<Object>> phaseList) {
		turnRed(phaseList.get(0));
		turnRed(phaseList.get(1));
		turnRed(phaseList.get(2));
	}

	public  void turnGreen(ArrayList<Object> phase){
		for (Object r : phase) {
			if (r instanceof ShloshaAvot) {
				((ShloshaAvot) r).m_stateQueue.sendEvent(Events.TURN_GREEN);
			} else if (r instanceof ShneyLuchot) {
				((ShneyLuchot) r).m_stateQueue.sendEvent(Events.TURN_GREEN);
			}
		}
	}

	public  void turnRed(ArrayList<Object> phase){
			for (Object r : phase) {
				if (r instanceof ShloshaAvot) {
					((ShloshaAvot) r).m_stateQueue.sendEvent(Events.TURN_RED);
				} else if (r instanceof ShneyLuchot) {
					((ShneyLuchot) r).m_stateQueue.sendEvent(Events.TURN_RED);
				}
			}

		for (Object r : phase) {
			if (r instanceof ShloshaAvot) {
				((ShloshaAvot) r).m_ackQueue.waitEvent();
			} else if (r instanceof ShneyLuchot) {
				((ShneyLuchot) r).m_ackQueue.waitEvent();
			}
		}
	}


	public  void enterShabbosMode(ArrayList<ArrayList<Object>> phaseList) {
		turnRed(phaseList.get(0));
		turnRed(phaseList.get(1));
		turnRed(phaseList.get(2));

		try{
			sleep(3000);
		}catch (InterruptedException e){}

		((ShloshaAvot)(phaseList.get(0).get(0))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShloshaAvot)(phaseList.get(1).get(0))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShloshaAvot)(phaseList.get(2).get(0))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShloshaAvot)(phaseList.get(2).get(1))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);

		((ShneyLuchot)(phaseList.get(2).get(2))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(2).get(3))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(0).get(1))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(0).get(2))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(2).get(4))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(0).get(3))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(0).get(4))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(2).get(5))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(0).get(5))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(0).get(6))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(2).get(6))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);
		((ShneyLuchot)(phaseList.get(2).get(7))).m_stateQueue.sendEvent(Events.ENTER_SHABBOS_MODE);

	}

	public static void enterWeekDayMode(ArrayList<ArrayList<Object>> phaseList) {

		((ShloshaAvot)(phaseList.get(0).get(0))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShloshaAvot)(phaseList.get(1).get(0))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShloshaAvot)(phaseList.get(2).get(0))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShloshaAvot)(phaseList.get(2).get(1))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);

		((ShneyLuchot)(phaseList.get(2).get(2))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(2).get(3))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(0).get(1))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(0).get(2))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(2).get(4))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(0).get(3))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(0).get(4))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(2).get(5))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(0).get(5))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(0).get(6))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(2).get(6))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);
		((ShneyLuchot)(phaseList.get(2).get(7))).m_stateQueue.sendEvent(Events.ENTER_WEEKDAY_MODE);


		((ShloshaAvot)(phaseList.get(0).get(0))).m_ackQueue.waitEvent();
		((ShloshaAvot)(phaseList.get(1).get(0))).m_ackQueue.waitEvent();
		((ShloshaAvot)(phaseList.get(2).get(0))).m_ackQueue.waitEvent();
		((ShloshaAvot)(phaseList.get(2).get(1))).m_ackQueue.waitEvent();

		((ShneyLuchot)(phaseList.get(2).get(2))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(2).get(3))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(0).get(1))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(0).get(2))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(2).get(4))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(0).get(3))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(0).get(4))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(2).get(5))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(0).get(5))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(0).get(6))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(2).get(6))).m_ackQueue.waitEvent();
		((ShneyLuchot)(phaseList.get(2).get(7))).m_ackQueue.waitEvent();

		try{
			sleep(1000);
		}catch (InterruptedException e){}

	}

	public  void disableButtons(JRadioButton buttons[]){
		for (JRadioButton btn : buttons) {
			if(!btn.getName().equals("16")) {
				btn.setEnabled(false);
			}
		}
	}

	public  void enableButtons(JRadioButton buttons[]){
		for (JRadioButton btn : buttons) {
			btn.setEnabled(true);
		}
	}


	/*public static void main(String[] args) { //MAIN
		BuildTrafficLight controller = new BuildTrafficLight();
		controller.startJunction();
	}*/

}