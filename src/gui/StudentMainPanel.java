package gui;

import lups.Controller;


public class StudentMainPanel extends Panel {

    public StudentMainPanel(MainFrame frame) {
        super(frame);
    	initComponents();
    }

                           
    private void initComponents() {
        menueLabel.setText("Studentenmenü");

        btn1.setText("<html>Belegte<br>Veranstaltungen</html>");
        btn2.setText("<html>Meine<br>Leistungen</html>");        
        btn3.setText("<html>Veranstaltungs-<br>übersicht</html>");
        btn4.setText("Benutzerdaten");
        btn6.setText("Logout");
        btn5.setText("Dokumente");
        mainlabel.setText("<html>Willkommen,<br>"+
            "in diesem Menü können Sie ihre Daten einsehen und gegebenenfalls verändern.<br>"
            + "Viel Vergnügen! =)</html>");
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
        btn6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn6ActionPerformed(evt);
            }
        });

           }                       

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
    	StudLeistungPanel tmpPanel=new StudLeistungPanel(this);
    	tmpPanel.setData(frame.getController().leistungsUebersicht());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }                                           

    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {                                              
    	StudEigVeranstPanel tmpPanel=new StudEigVeranstPanel(this);
        tmpPanel.setData(frame.getController().angemeldeteKurse());
    	layout.replace(subMainPanel,subMainPanel=tmpPanel);   
    }                                             

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	BenutzerDatPanel tmpPanel=new BenutzerDatPanel(this);
    	tmpPanel.setData(frame.getController().getPers());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }                                              

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {                                               
    	VeranstaltungsPanel tmpPanel=new VeranstaltungsPanel(this);
    	tmpPanel.setData(frame.getController().getallLV());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }                                              

    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {                                       
    	Print tmpPanel=new Print(this);
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }                                      

    private void btn6ActionPerformed(java.awt.event.ActionEvent evt) {                                          
        frame.getController().logout();
        frame.setGUI(7);
    }                                                          
}
