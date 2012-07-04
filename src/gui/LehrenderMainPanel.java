
package gui;

import lups.Controller;

public class LehrenderMainPanel extends Panel {

    public LehrenderMainPanel(MainFrame frame) {
        super(frame);
        initComponents();
    }

    
    private void initComponents() {
        menueLabel.setText("Lehrendermenü:");
        btn1.setText("Verwalte Vorlesung");
        btn2.setText("Benutzerdaten");
        btn3.setText("Vorlesungsübersicht");
        btn4.setText("Logout");
        btn5.setVisible(false);
        btn6.setVisible(false);
        mainlabel.setText("Willkommen Lehrender!");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });
    }                       

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                                 
    	VerwVorlesungPanel tmpPanel=new VerwVorlesungPanel(this);
    	tmpPanel.setData(frame.getController().geterstellteLV());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }                                                

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	BenutzerDatPanel tmpPanel=new BenutzerDatPanel(this);
    	tmpPanel.setData(frame.getController().getPers());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }                                         
    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	VeranstaltungsPanel tmpPanel=new VeranstaltungsPanel(this);
    	tmpPanel.setData(frame.getController().getallLV());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        frame.getController().logout();
        frame.setGUI(7);
    }                   
}
