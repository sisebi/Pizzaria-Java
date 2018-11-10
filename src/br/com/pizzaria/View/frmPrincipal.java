package br.com.pizzaria.View;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @Eliezer
 */
public class frmPrincipal extends javax.swing.JFrame {

    private frmCadPessoa formPessoa;
    private frmCadPedido formPedido;

    public frmPrincipal() {
        criarForms();
        initComponents();
    }

    private void criarForms() {
        if (this.formPessoa == null) {
            this.formPessoa = new frmCadPessoa();
        }
        if (this.formPedido == null){
            this.formPedido = new frmCadPedido();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pDesktop = new javax.swing.JDesktopPane();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemCadPessoa = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItemCadPedido = new javax.swing.JMenuItem();
        jMenuSair = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Principal");
        addWindowStateListener(new java.awt.event.WindowStateListener() {
            public void windowStateChanged(java.awt.event.WindowEvent evt) {
                formWindowStateChanged(evt);
            }
        });

        javax.swing.GroupLayout pDesktopLayout = new javax.swing.GroupLayout(pDesktop);
        pDesktop.setLayout(pDesktopLayout);
        pDesktopLayout.setHorizontalGroup(
            pDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 730, Short.MAX_VALUE)
        );
        pDesktopLayout.setVerticalGroup(
            pDesktopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 504, Short.MAX_VALUE)
        );

        jMenu1.setText("Cadastros");

        jMenuItemCadPessoa.setText("Pessoa");
        jMenuItemCadPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadPessoaActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemCadPessoa);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Vendas");

        jMenuItemCadPedido.setText("Pedido");
        jMenuItemCadPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCadPedidoActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItemCadPedido);

        jMenuBar1.add(jMenu2);

        jMenuSair.setText("Sair");
        jMenuSair.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuSairMouseClicked(evt);
            }
        });
        jMenuBar1.add(jMenuSair);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDesktop)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pDesktop)
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleDescription("");

        setSize(new java.awt.Dimension(766, 586));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemCadPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadPessoaActionPerformed
        pDesktop.add(this.formPessoa);
        formPessoa.setTitle("Tela Cadastro Pessoa");
        formPessoa.novo();        
        formPessoa.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadPessoaActionPerformed

    private void jMenuSairMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuSairMouseClicked
        JFrame frame = new JFrame();
        int resposta = JOptionPane.showConfirmDialog(frame,"Deseja sair do Sistema","Sistema Pizzaria", JOptionPane.YES_NO_OPTION);   
        if (resposta == JOptionPane.YES_OPTION) {    
            System.exit(0);
        }    
    }//GEN-LAST:event_jMenuSairMouseClicked

    private void formWindowStateChanged(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowStateChanged

    private void jMenuItemCadPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCadPedidoActionPerformed
        pDesktop.add(this.formPedido);
        formPedido.setTitle("Tela Cadastro Pedido");
        formPedido.setVisible(true);
    }//GEN-LAST:event_jMenuItemCadPedidoActionPerformed

   
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
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(frmPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new frmPrincipal().setVisible(true);
                frmPrincipal formPrincipal = new frmPrincipal();
                formPrincipal.setResizable(true);
                formPrincipal.setVisible(true);
                formPrincipal.setExtendedState(MAXIMIZED_BOTH);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItemCadPedido;
    private javax.swing.JMenuItem jMenuItemCadPessoa;
    private javax.swing.JMenu jMenuSair;
    private javax.swing.JDesktopPane pDesktop;
    // End of variables declaration//GEN-END:variables
}
