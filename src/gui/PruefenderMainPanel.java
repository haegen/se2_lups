package gui;

import lups.Controller;

public class PruefenderMainPanel extends Panel {  
    
    public PruefenderMainPanel(MainFrame frame) {
	super(frame);
        initComponents();
    }
    
    private void initComponents() {
        menueLabel.setText("Prüfermenü:");
        btn1.setText("<html>Verwalte<br> Vorlesung</html>");
        btn2.setText("Verwalte Noten");
        btn3.setText("Benutzerdaten");
        btn4.setText("Vorlesungsübersicht");
        btn5.setText("Logout");
        btn6.setVisible(false);
        mainlabel.setText("Willkommen Prüfender!");
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
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });
    }                        

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                     
    	VerwVorlesungPanel tmpPanel=new VerwVorlesungPanel(this);
    	tmpPanel.setData(frame.getController().geterstellteLV());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }                                                

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                             
    	VerwalteNotenPanel tmpPanel=new VerwalteNotenPanel(this);
    	tmpPanel.setData(frame.getController().prueferliste());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }                                            

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	BenutzerDatPanel tmpPanel=new BenutzerDatPanel(this);
    	tmpPanel.setData(frame.getController().getPers());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }                                         
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                          
    	VeranstaltungsPanel tmpPanel=new VeranstaltungsPanel(this);
    	tmpPanel.setData(frame.getController().getallLV());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }
    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        frame.getController().logout();
        frame.setGUI(7);
    }                                                            
}
