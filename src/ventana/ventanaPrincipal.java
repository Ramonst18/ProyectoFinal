/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import java.awt.Dimension;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import proyectofinal.Atributos;
import proyectofinal.ERDParser;

/**
 *
 * @author Ramon
 */
public class ventanaPrincipal extends javax.swing.JFrame {
    Hashtable<String,ArrayList> hashEntidades;
    Hashtable<String,ArrayList> hashEntidadesDebiles;
    String direccionArchivo;
    /**
     * Creates new form ventanaPrincipal
     */
    /*private DefaultComboBoxModel<Ejercicio4_persona> modelo = new DefaultComboBoxModel<Ejercicio4_persona>();*/
    //se crea un modelo para la tabla
        
    //ventana interior
    
    
    public ventanaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        panelEscritorio = new javax.swing.JDesktopPane();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        itemAbrir = new javax.swing.JMenuItem();
        itemGuardar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        itemSalir = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));

        javax.swing.GroupLayout panelEscritorioLayout = new javax.swing.GroupLayout(panelEscritorio);
        panelEscritorio.setLayout(panelEscritorioLayout);
        panelEscritorioLayout.setHorizontalGroup(
            panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        panelEscritorioLayout.setVerticalGroup(
            panelEscritorioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        jMenu3.setText("Archivo");

        itemAbrir.setText("Abrir");
        itemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAbrirActionPerformed(evt);
            }
        });
        jMenu3.add(itemAbrir);

        itemGuardar.setText("Guardar");
        jMenu3.add(itemGuardar);
        jMenu3.add(jSeparator1);

        itemSalir.setText("Salir");
        itemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSalirActionPerformed(evt);
            }
        });
        jMenu3.add(itemSalir);

        jMenuBar2.add(jMenu3);

        jMenu4.setText("Ayuda");

        jMenuItem1.setText("Creditos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem1);

        jMenuBar2.add(jMenu4);

        setJMenuBar(jMenuBar2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEscritorio)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelEscritorio)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemSalirActionPerformed
        //salimos del programa
        System.exit(0);
    }//GEN-LAST:event_itemSalirActionPerformed

    private void itemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAbrirActionPerformed
        //nuestro panel escritorio es un JDesltop pane el cual nos permite poner mas ventanas dentro de el
            
        //obtenemos la direccion del archivo
        //abrimos la ventana de busqueda
        JFileChooser jf = new JFileChooser();
        jf.showOpenDialog(this);
        
        //obtenemos la direccion del archivo
        File archivo = jf.getSelectedFile();
        
        //si archivo no esta vacio, mandamos la direccion a la variable direccionArchivo
        if (archivo!= null) {
            direccionArchivo = archivo.getAbsolutePath();
        }
        
        //comprobamos la extencion del archivo
        if (direccionArchivo.endsWith(".json")) {
            //pasamos la direccion del arc0hivo
            ERDParser tabla = new ERDParser(direccionArchivo);

            //obtenemos el hash de entidades primarias
            hashEntidades = tabla.crearHashEntidades();
            
            if (hashEntidades!= null) {
                //para entidades fuertes
                //obtener llaves primarias de las entidades    
                ArrayList<String> keysEntidades = tabla.keysEntidades();
                
                
                ArrayList<Atributos> atributos;
                String tipoEntidad;
                
                //recorre el arreglo con los atributos
                for (String key : keysEntidades) {
                    tipoEntidad = "entidad";
                    //obtenemos el arrayList del hashTable
                    atributos = hashEntidades.get(key);
                    System.out.println(key);

                    
                    //creamos el panel interno
                    ventanaDatos vd = new ventanaDatos(key, atributos, tipoEntidad);
                    vd.setTabla(tabla);
                    vd.setHashEntidades(hashEntidades);
                    panelEscritorio.add(vd);
                    vd.show();

                    //pasamos el arrayList con la key que es la entidad y el array atributos q
                    System.out.println("----------------------");
                }
                
                
                //entidades debiles, se ocupan las fuertes primero antes de las debiles
                hashEntidadesDebiles = tabla.crearHashEntidadesDebiles();
                
                //si no esta vacio
                if (hashEntidadesDebiles!= null) {
                    ArrayList<String> keysEntidadesDebiles = tabla.keysEntidadesDebiles();
                    
                    //recorremos las keys de las entidades debiles
                    for (String keysDebiles : keysEntidadesDebiles) {
                        tipoEntidad = "entidadDebil";
                        
                        //obtenemos el array
                        atributos = hashEntidadesDebiles.get(keysDebiles);
                        System.out.println(keysDebiles);
                        
                        //creamos el panel interno
                        ventanaDatos vd = new ventanaDatos(keysDebiles, atributos, tipoEntidad);
                        panelEscritorio.add(vd);
                        vd.show();
                        System.out.println("-------------------------------");
                    }
                }
            }
            

            
        } else {
            JOptionPane.showMessageDialog(null, "Archivo incompatible");
        }
        
        
        
    }//GEN-LAST:event_itemAbrirActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       //para añadir los comentarios 
       Creditos creditos = new Creditos(this, rootPaneCheckingEnabled);
       creditos.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

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
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ventanaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ventanaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem itemAbrir;
    private javax.swing.JMenuItem itemGuardar;
    private javax.swing.JMenuItem itemSalir;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JDesktopPane panelEscritorio;
    // End of variables declaration//GEN-END:variables
}
