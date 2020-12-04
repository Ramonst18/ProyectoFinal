/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import proyectofinal.Atributos;

/**
 *
 * @author Ramon
 */
public class tablaDatosSQL extends javax.swing.JFrame {

    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private String nombreEntidad;
    public tablaDatosSQL(Atributos[] atributos) {
        initComponents();
        //dividimos el modelo relacional en espacios
        
        //creamos el codigo SQL
        crearCodigoSQL(atributos);
    }

    
    
    private void crearCodigoSQL(Atributos[] atributos){
        String codigoSQL = "CREATE TABLE \"" + this.nombreEntidad + "\" \n(\n";
        
        //recorremos todos los atributos
        ArrayList<String> columnasPrimarias = new ArrayList<>();
        for (int i = 0; i < atributos.length; i++) {
            //obtenemos el nombre
            String name = atributos[i].getName();
            codigoSQL += "\t " + name +" ";
            
            String tipo = atributos[i].getData_type();
            if (tipo.toLowerCase().equals("integer")) {
                codigoSQL += tipo.toLowerCase() + " ";
            } else if (tipo.toLowerCase().equals("varying")) {
                int longitud = Integer.parseInt(atributos[i].getLength());
                codigoSQL += tipo.toLowerCase() + "("+longitud+") ";
            }
            
            //checmos si es nulo
            boolean noNulo = atributos[i].isNot_null();
            if (noNulo == true) {
                codigoSQL += "NOT NULL";
            }
            
            //checamos si es llave primaria
            boolean primaryKey = atributos[i].isPrimary_key();
            if (primaryKey == true) {
                columnasPrimarias.add(name);
            }
            
            
        }
        areaSQL.setText(codigoSQL);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaSQL = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        areaModeloRelacional = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 540, 110));

        areaSQL.setColumns(20);
        areaSQL.setRows(5);
        jScrollPane2.setViewportView(areaSQL);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 0, 210, 166));

        areaModeloRelacional.setColumns(20);
        areaModeloRelacional.setRows(5);
        jScrollPane3.setViewportView(areaModeloRelacional);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 540, 60));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(tablaDatosSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(tablaDatosSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(tablaDatosSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(tablaDatosSQL.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new tablaDatosSQL(null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaModeloRelacional;
    private javax.swing.JTextArea areaSQL;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
