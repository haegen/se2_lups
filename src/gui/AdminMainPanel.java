
package gui;

import lups.Controller;

public class AdminMainPanel extends Panel{
    
    
    public AdminMainPanel(MainFrame frame){
        super(frame);
        initComponents();
    }
    
    private void initComponents(){
        this.menueLabel.setText("Adminmenü:");

        this.btn1.setText("Nutzer Verwalten");
        this.btn2.setText("Logs betrachten");
        this.btn3.setText("Benutzerdaten");
        this.btn4.setText("Logout");
        this.btn5.setVisible(false);
        this.btn6.setVisible(false);
        this.mainlabel.setText("<html>Willkommen!<br>Viel Spaß beim schalten, walten,<br>Kaffee trinken und Domuts essen.</html>");
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
    	AdminNutzerVerwaltenPanel tmpPanel=new AdminNutzerVerwaltenPanel(this);
        tmpPanel.setData(frame.getController().getMailList());
        layout.replace(subMainPanel,subMainPanel=tmpPanel); 
    }

    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {
        LogPanel tmpPanel=new LogPanel(this);
        tmpPanel.fillText(frame.getController().logEinsehen());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }

    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {
    	BenutzerDatPanel tmpPanel=new BenutzerDatPanel(this);
        tmpPanel.setData(frame.getController().getPers());
        layout.replace(subMainPanel,subMainPanel=tmpPanel);
    }

    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {
        frame.getController().logout();
        frame.setGUI(7);
    }
}
