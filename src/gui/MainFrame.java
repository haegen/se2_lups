/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JPanel;

import lups.Controller;

/**
 *
 * @author nvettera
 */
public class MainFrame extends javax.swing.JFrame {
	private javax.swing.GroupLayout layout;
	private Controller moon;
	private JPanel panel;
	/**
     * Creates new form MainFrame
     */
    public MainFrame(Controller moon) {
    	this.moon=moon;
    	initComponents();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Dmio NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
    	panel=new LoginPanel(this);
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public Controller getController(){
    	return moon;
    }
    public void setGUI(int id){
        switch(id){
        case 0:layout.replace(panel,panel=new AdminMainPanel(this));break;
        case 1:layout.replace(panel,panel=new PruefenderMainPanel(this));break;
        case 2:layout.replace(panel,panel=new LehrenderMainPanel(this));break;
        case 3:layout.replace(panel,panel=new StudentMainPanel(this));break;
        default:layout.replace(panel,panel=new LoginPanel(this));
        }            
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
