/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pertemuan10;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author ACER
 */
public class ThreadServer extends javax.swing.JFrame implements Runnable {

    /**
     * Creates new form ThreadServer
     */
    private SimpleDateFormat ftTanggal;
    private SimpleDateFormat ftWaktu;
    private Timer timer;
    private Date date;
    Socket client; 
    ServerSocket server; 
    BufferedReader br;
    BufferedWriter bw;
    Thread thread;
    
    public ThreadServer() {
        initComponents();
        this.setTitle("Thread Server");
        serverKoneksi();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTanggal = new javax.swing.JLabel();
        lblWaktu = new javax.swing.JLabel();
        btnPause = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblTanggal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblTanggal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        lblWaktu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblWaktu.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        btnPause.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnPause.setText("Start");
        btnPause.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPauseActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnPause)
                .addGap(114, 114, 114))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblTanggal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblWaktu, javax.swing.GroupLayout.DEFAULT_SIZE, 280, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblTanggal, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblWaktu, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnPause)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnPauseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPauseActionPerformed
        // TODO add your handling code here:
        if (btnPause.getText().equals("Start")) {
            start();
            btnPause.setText("Pause");
        }
        else {
            stop();
            btnPause.setText("Start");
        }
    }//GEN-LAST:event_btnPauseActionPerformed
 
    public void serverKoneksi() {
        try {
            try {
                try {
                    server =  new ServerSocket(1111);
                    this.setTitle("Thread Server - Waiting...");
                } catch (IOException e) {
                    this.setTitle("Thread Server - Gagal Membuat Server");
                }
                client = server.accept();
                this.setTitle("Thread Server - Connected");
            } catch (IOException e) {
               this.setTitle("Thread Server - Akses Gagal");
            }
            br = new BufferedReader(new InputStreamReader(client.getInputStream()));
            bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
        } catch (IOException e) {
            this.setTitle("Thread Server - Akses Gagal");
        }
        thread = new Thread(this);
        thread.start();
    }
    
    public void start() {
        ActionListener set = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {  
            ftTanggal = new SimpleDateFormat("E, dd MMMM yyyy");
            ftWaktu = new SimpleDateFormat("HH:m:ss");
            date = new Date();
            date.setTime(System.currentTimeMillis());
            lblTanggal.setText(ftTanggal.format(date));
            lblWaktu.setText(ftWaktu.format(date));
            }
        };
        timer = new Timer(1000, set);
        timer.start();
    }
 
    public void stop() {
        timer.stop();
    }

    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThreadServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ThreadServer().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnPause;
    private javax.swing.JLabel lblTanggal;
    private javax.swing.JLabel lblWaktu;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while (true) {
            try {
                bw.write(lblWaktu.getText());
    //            bw.write(lblTanggal.getText());
                bw.newLine();
                bw.flush();
            } catch (IOException e) {
               
            }
        }
//        start();
    }
}