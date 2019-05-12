import javax.swing.*;

public class ButtonHandler extends Thread {
    Event64 m_pressedQueue;
    Event64 m_ackQueue;
    JRadioButton m_butt[];

    public ButtonHandler(Event64 q,Event64 a){
        m_pressedQueue =q;
        m_ackQueue = a;
        start();
    }
    public void setButtons(JRadioButton[] b){m_butt = b;}

    private void disableButtons(){
        for (JRadioButton btn : m_butt) {
            if(!btn.getName().equals("16")){
                btn.setEnabled(false);
            }
        }
    }

    private void enableButtons(){
        for (JRadioButton btn : m_butt) {
            btn.setEnabled(true);
        }
    }

    public Phase getMatchingPhase(int index){

        return Phase.PHASE_A;
    }

    @Override
    public void run()
    {
        JRadioButton butt;
        try {
            while (true) {
                butt = (JRadioButton) m_pressedQueue.waitEvent();
                disableButtons();
                m_ackQueue.sendEvent( getMatchingPhase(Integer.parseInt(butt.getName())-4));
                sleep(1000);
                butt.setSelected(false);
                enableButtons();
            }

        }catch (Exception e) {}
    }
}
