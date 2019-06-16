import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControlFrame extends Frame {
    private JFrame frame;
    private ControlActionListener controlListener;
    private JComboBox junctionList;
    private JCheckBox shabasMode;
    private JButton phase1;
    private JButton phase2;
    private JButton phase3;

    ArrayList<Dialog770> clients;
    private int counter;
    private HashMap<String, Dialog770> clientMap;

    /**
     * Create the application.
     */
    public ControlFrame() {
        counter = 1;
        controlListener = new ControlActionListener();
        clients = new ArrayList<Dialog770>();
        clientMap = new HashMap<String, Dialog770>();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    public void addClient(Dialog770 client) {
        clients.add(client);
        String newClient = "Junction" + counter++;
        clientMap.put(newClient, client);
        junctionList.addItem(newClient);

    }

    private void initialize() {

        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setTitle("Controller");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        String[] junctionStrings = {"All"};

        junctionList = new JComboBox(junctionStrings);
        junctionList.setSelectedIndex(0);
        junctionList.setBounds(150, 20, 133, 25);
        //junctionList.addActionListener(controlListener);
        frame.getContentPane().add(junctionList);

        JLabel lblName = new JLabel("Select:");
        lblName.setBounds(100, 25, 46, 14);
        frame.getContentPane().add(lblName);

        phase1 = new JButton("Activate Phase 1");
        phase1.addActionListener(controlListener);
        phase1.setBounds(135, 50, 150, 25);
        phase1.setName("Activate Phase 1");
        frame.getContentPane().add(phase1);

        phase2 = new JButton("Activate Phase 2");
        phase2.addActionListener(controlListener);
        phase2.setBounds(135, 80, 150, 25);
        phase2.setName("Activate Phase 2");
        frame.getContentPane().add(phase2);

        phase3 = new JButton("Activate Phase 3");
        phase3.addActionListener(controlListener);
        phase3.setBounds(135, 110, 150, 25);
        phase3.setName("Activate Phase 3");
        frame.getContentPane().add(phase3);

        shabasMode = new JCheckBox("Shabas Mode");
        shabasMode.setSelected(false);
        shabasMode.setBounds(150, 200, 130, 25);
        shabasMode.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    disableView();
                    // activate shabas mode
                    for(Dialog770 cur : clients ) {
                        cur.bufferSocketOut.println("Enter Shabbos");
                    }
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    // deactivate shabas mode
                    for(Dialog770 cur : clients ) {
                        cur.bufferSocketOut.println("Enter Weekday");
                    }
                }
                enableView();
            }
        });

        frame.getContentPane().add(shabasMode);

        frame.setVisible(true);
    }

    /**
     * disables all buttons on frame
     */
    public void disableView() {
        phase1.setEnabled(false);
        phase2.setEnabled(false);
        phase3.setEnabled(false);

    }

    /**
     * enables all buttons on frame
     */
    public void enableView() {
        phase1.setEnabled(true);
        phase2.setEnabled(true);
        phase3.setEnabled(true);
    }

    /**
     * the action controller finds which state is selected in the comboBox
     * and sends commands to that junction due to what button is pressed
     */
    public class ControlActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.out.println("Button Pressed");
            JButton butt = (JButton) e.getSource();
            String buttonName = butt.getName();
            String currentJunction = junctionList.getSelectedItem().toString();

            // Activate all junctions
            if (currentJunction.equals("All")) {
                if (buttonName.equals("Activate Phase 1")) {
                    // Activate Phase 1
                    for(Dialog770 cur : clients ){
                        cur.bufferSocketOut.println("A");
                    }
                } else if (buttonName.equals("Activate Phase 2")) {
                    // Activate Phase 2
                    for(Dialog770 cur : clients ){
                        cur.bufferSocketOut.println("B");
                    }
                } else if (buttonName.equals("Activate Phase 3")) {
                    // Activate Phase 3
                    for(Dialog770 cur : clients ){
                        cur.bufferSocketOut.println("C");
                    }
                }
            } else {
                Dialog770 currentDialog = clientMap.get(currentJunction);
                if (buttonName.equals("Activate Phase 1")) {
                    // Activate Phase 1
                    currentDialog.bufferSocketOut.println("A");
                } else if (buttonName.equals("Activate Phase 2")) {
                    // Activate Phase 2
                    currentDialog.bufferSocketOut.println("B");
                } else if (buttonName.equals("Activate Phase 3")) {
                    // Activate Phase 3
                    currentDialog.bufferSocketOut.println("C");
                }
            }


        }
    }
}