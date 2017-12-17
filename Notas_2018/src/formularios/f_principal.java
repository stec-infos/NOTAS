/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package formularios;

import clases.AccesoUsuario;
import clases.AddForms;
import clases.ImagenFondo;
import static formularios.frmVenta.txtNit;
import static java.awt.Frame.MAXIMIZED_BOTH;
import javax.swing.ImageIcon;

/**
 *
 * @author Victor Pino
 */
public class f_principal extends javax.swing.JFrame {

    public String usuario;
    public Integer perfil;
    public String cargo;
    public Integer id;
    public String nombre;

    public f_principal() {
        initComponents();
        this.setExtendedState(MAXIMIZED_BOTH);
        this.setIconImage(new ImageIcon(getClass().getResource("/imagenes/pointofsale.png")).getImage());
        //panel_center.setBorder(new ImagenFondo("/imagenes/bgimg.jpg"));
    }

    public void setUsuario(String usuario, int id, int perfil, String cargo, String nombre) {
        this.usuario = usuario;
        this.id = id;
        this.perfil = perfil;
        this.cargo = cargo;
        this.nombre = nombre;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        bClientes = new javax.swing.JButton();
        bVentas = new javax.swing.JButton();
        bProductos = new javax.swing.JButton();
        bInventario = new javax.swing.JButton();
        bConfiguracion = new javax.swing.JButton();
        bCorte = new javax.swing.JButton();
        bReportes = new javax.swing.JButton();
        bSalir = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        panel_center = new javax.swing.JDesktopPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PUNTO DE VENTA");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel4.setOpaque(false);

        bClientes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/customers.png"))); // NOI18N
        bClientes.setText("F2 Apoderado");
        bClientes.setToolTipText("");
        bClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClientesActionPerformed(evt);
            }
        });

        bVentas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bVentas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/sale.png"))); // NOI18N
        bVentas.setText("F1 Colegio");
        bVentas.setToolTipText("");
        bVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bVentasActionPerformed(evt);
            }
        });

        bProductos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bProductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/product.png"))); // NOI18N
        bProductos.setText("F3 Bimestre");
        bProductos.setToolTipText("");
        bProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bProductosActionPerformed(evt);
            }
        });

        bInventario.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/inventory.png"))); // NOI18N
        bInventario.setText("F4 Inventario");
        bInventario.setToolTipText("");
        bInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bInventarioActionPerformed(evt);
            }
        });

        bConfiguracion.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/configuration.png"))); // NOI18N
        bConfiguracion.setText("Configuración");
        bConfiguracion.setToolTipText("");
        bConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bConfiguracionActionPerformed(evt);
            }
        });

        bCorte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bCorte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/corte.png"))); // NOI18N
        bCorte.setText(" Corte");
        bCorte.setToolTipText("");
        bCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bCorteActionPerformed(evt);
            }
        });

        bReportes.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bReportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/report.png"))); // NOI18N
        bReportes.setText("Reportes");
        bReportes.setToolTipText("");
        bReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bReportesActionPerformed(evt);
            }
        });

        bSalir.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        bSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/close.png"))); // NOI18N
        bSalir.setText(" Salir");
        bSalir.setToolTipText("");
        bSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bVentas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bClientes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bProductos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bInventario)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bConfiguracion)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bCorte)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bReportes)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 88, Short.MAX_VALUE)
                .addComponent(bSalir)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(bClientes)
                .addComponent(bVentas)
                .addComponent(bProductos)
                .addComponent(bInventario)
                .addComponent(bConfiguracion)
                .addComponent(bCorte)
                .addComponent(bReportes)
                .addComponent(bSalir))
        );

        panel_center.setBackground(new java.awt.Color(255, 255, 255));
        panel_center.setOpaque(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1020, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_center, javax.swing.GroupLayout.DEFAULT_SIZE, 1020, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 706, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(panel_center, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 706, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened


    }//GEN-LAST:event_formWindowOpened

    private void bClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClientesActionPerformed
        // TODO add your handling code here:
        f_apoderado newfrm = new f_apoderado();
        if (newfrm == null) {
            newfrm = new f_apoderado();
        }
        AddForms.adminInternalFrame(panel_center, newfrm);


    }//GEN-LAST:event_bClientesActionPerformed

    private void bProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bProductosActionPerformed
        // TODO add your handling code here:
        f_bimestre newfrm = new f_bimestre();
        if (newfrm == null) {
            newfrm = new f_bimestre();
        }
        AddForms.adminInternalFrame(panel_center, newfrm);

    }//GEN-LAST:event_bProductosActionPerformed

    private void bInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bInventarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bInventarioActionPerformed

    private void bConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bConfiguracionActionPerformed
        // TODO add your handling code here:
        config newfrm = new config();
        if (newfrm == null) {
            newfrm = new config();
        }
        AddForms.adminInternalFrame(panel_center, newfrm);
    }//GEN-LAST:event_bConfiguracionActionPerformed

    private void bCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bCorteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bCorteActionPerformed

    private void bReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bReportesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bReportesActionPerformed

    private void bSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_bSalirActionPerformed

    private void bVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bVentasActionPerformed
        // TODO add your handling code here:
        f_establecimiento newfrm = new f_establecimiento();
        if (newfrm == null) {
            newfrm = new f_establecimiento();
        }
        AddForms.adminInternalFrame(panel_center, newfrm);
        //txtNit.requestFocus();
    }//GEN-LAST:event_bVentasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(f_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(f_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(f_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(f_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new f_principal().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bClientes;
    private javax.swing.JButton bConfiguracion;
    private javax.swing.JButton bCorte;
    private javax.swing.JButton bInventario;
    private javax.swing.JButton bProductos;
    private javax.swing.JButton bReportes;
    private javax.swing.JButton bSalir;
    private javax.swing.JButton bVentas;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    public static javax.swing.JDesktopPane panel_center;
    // End of variables declaration//GEN-END:variables
}
