/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * SettingsGUI.java
 *
 * Created on Nov 3, 2011, 9:13:40 PM
 */

package com.edinarobotics.gui;

import java.io.File;

/*
 * @author aoneill
 * @note Made by the Netbeans built-in GUI creator
 */


public class SettingsGUI extends javax.swing.JFrame
{

    private String defaultTeamFileDir;
    private String defaultCommentFileDir;
    private boolean logSetting;
    private boolean scoutBool = false;


    /** Creates new form SettingsGUI */
    public SettingsGUI()
    {
        initComponents();

        fileExplorer = new javax.swing.JFileChooser();
        fileExplorer.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        setVisible(true);


    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logCheckBox = new javax.swing.JCheckBox();
        scoutButton = new javax.swing.JButton();
        wDirPanel = new javax.swing.JPanel();
        teamDirLocation = new javax.swing.JTextField();
        chooseButton = new javax.swing.JButton();
        cDirPanel1 = new javax.swing.JPanel();
        commentDirLocation = new javax.swing.JTextField();
        chooseButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        logCheckBox.setText("Log Session");
        logCheckBox.setFocusable(false);

        scoutButton.setText("Scout!");
        scoutButton.setFocusable(false);
        scoutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoutButtonActionPerformed(evt);
            }
        });

        wDirPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Team Directory"));

        teamDirLocation.setColumns(50);
        teamDirLocation.setFont(new java.awt.Font("Lucida Grande", 0, 10));
        teamDirLocation.setFocusable(false);

        chooseButton.setText("Choose ...");
        chooseButton.setFocusable(false);
        chooseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout wDirPanelLayout = new org.jdesktop.layout.GroupLayout(wDirPanel);
        wDirPanel.setLayout(wDirPanelLayout);
        wDirPanelLayout.setHorizontalGroup(
            wDirPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(wDirPanelLayout.createSequentialGroup()
                .add(wDirPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(teamDirLocation, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 649, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, wDirPanelLayout.createSequentialGroup()
                        .addContainerGap(542, Short.MAX_VALUE)
                        .add(chooseButton)))
                .addContainerGap())
        );
        wDirPanelLayout.setVerticalGroup(
            wDirPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(wDirPanelLayout.createSequentialGroup()
                .add(teamDirLocation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chooseButton))
        );

        cDirPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Comments Directory"));

        commentDirLocation.setColumns(50);
        commentDirLocation.setFont(new java.awt.Font("Lucida Grande", 0, 10));
        commentDirLocation.setFocusable(false);

        chooseButton1.setText("Choose ...");
        chooseButton1.setFocusable(false);
        chooseButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chooseButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout cDirPanel1Layout = new org.jdesktop.layout.GroupLayout(cDirPanel1);
        cDirPanel1.setLayout(cDirPanel1Layout);
        cDirPanel1Layout.setHorizontalGroup(
            cDirPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(cDirPanel1Layout.createSequentialGroup()
                .addContainerGap(539, Short.MAX_VALUE)
                .add(chooseButton1)
                .addContainerGap())
            .add(cDirPanel1Layout.createSequentialGroup()
                .add(commentDirLocation, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 636, Short.MAX_VALUE)
                .add(27, 27, 27))
        );
        cDirPanel1Layout.setVerticalGroup(
            cDirPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(cDirPanel1Layout.createSequentialGroup()
                .add(commentDirLocation, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(chooseButton1))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(logCheckBox)
                        .add(158, 158, 158)
                        .add(scoutButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 124, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, wDirPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(org.jdesktop.layout.GroupLayout.LEADING, layout.createSequentialGroup()
                            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(cDirPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .add(wDirPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(cDirPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(logCheckBox)
                    .add(scoutButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 37, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void scoutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scoutButtonActionPerformed
        // TODO add your handling code here:
        scoutBool = true;
    }//GEN-LAST:event_scoutButtonActionPerformed

    private void chooseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButtonActionPerformed
        // TODO add your handling code here:
        fileExplorer.setCurrentDirectory(new File(System.getProperty("user.dir")));
        
        int returnVal = fileExplorer.showOpenDialog(this);
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            try
            {
                defaultTeamFileDir = fileExplorer.getSelectedFile().getPath();
                teamDirLocation.setText(defaultTeamFileDir);
            }
            catch(Exception e)
            {
                System.out.println("Could not set teamDirLocation Field!");
            }
        }
    }//GEN-LAST:event_chooseButtonActionPerformed

    private void chooseButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chooseButton1ActionPerformed
        // TODO add your handling code here:
        fileExplorer.setCurrentDirectory(new File(System.getProperty("user.dir")));

        int returnVal = fileExplorer.showOpenDialog(this);
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION)
        {
            try
            {
                defaultCommentFileDir = fileExplorer.getSelectedFile().getPath();
                commentDirLocation.setText(defaultCommentFileDir);
            }
            catch(Exception e)
            {
                System.out.println("Could not set teamDirLocation Field!");
            }
        }
    }//GEN-LAST:event_chooseButton1ActionPerformed

    public void setTeamDirField(String dirPath)
    {
        defaultTeamFileDir = dirPath;
        teamDirLocation.setText(defaultTeamFileDir);
    }

    public void setCommentsDirField(String dirPath)
    {
        defaultCommentFileDir = dirPath;
        commentDirLocation.setText(defaultCommentFileDir);
    }

    public void setLogBox(boolean state)
    {
        logSetting = state;
        logCheckBox.setSelected(logSetting);
    }

    public boolean getLogBox()
    {
        return logCheckBox.isSelected();
    }

    public String getTeamDirPath()
    {
        return defaultTeamFileDir;
    }

    public String getCommentDirPath()
    {
        return defaultCommentFileDir;
    }

    public boolean getScoutStatus() throws InterruptedException
    {
        Thread.sleep(10);
        return scoutBool;
    }
    /**
    * @param args the command line arguments
    */
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SettingsGUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel cDirPanel1;
    private javax.swing.JButton chooseButton;
    private javax.swing.JButton chooseButton1;
    private javax.swing.JTextField commentDirLocation;
    private javax.swing.JCheckBox logCheckBox;
    private javax.swing.JButton scoutButton;
    private javax.swing.JTextField teamDirLocation;
    private javax.swing.JPanel wDirPanel;
    // End of variables declaration//GEN-END:variables

    private javax.swing.JFileChooser fileExplorer;
}
