/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ventana;

import java.util.ArrayList;
import java.util.Hashtable;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import proyectofinal.Atributos;
import proyectofinal.ERDParser;

/**
 *
 * @author Ramon
 */

public class ventanaDatos extends javax.swing.JInternalFrame {

    private DefaultTableModel modeloTabla = new DefaultTableModel();
    private String tipoEntidad;
    private String key;
    private Atributos[] atributos;
    private ERDParser tabla;
    private Hashtable<String,ArrayList> hashEntidades;
    
    public void setTabla(ERDParser tabla) {
        this.tabla = tabla;
    }

    public void setHashEntidades(Hashtable<String, ArrayList> hashEntidades) {
        this.hashEntidades = hashEntidades;
    }
    
    
    /**
     * Creates new form ventanaDatos
     * @param key     
     * @param atributos     
     * @param tipoEntidad     
     */
    
    public ventanaDatos(String key,ArrayList<Atributos> atributos,String tipoEntidad) {
        //primero se crea el modelo de la tabla
        agregarModeloTabla();
        //se añaden los renglones
        addRows(atributos);
        initComponents();
        //despues de la finalizacion de la creacion se agrega lo siguiente
        modificarEntidad(key,tipoEntidad);
        this.tipoEntidad = tipoEntidad;
        this.key = key;
        //agregamos el comboBox
        addComboBox(1, this.jTable1);
        
        //agregamos las checkBox
        addCheckBox(4,this.jTable1);
        addCheckBox(5,this.jTable1);
        
        //metemos los atributos en nuestro arreglo de atributos
        this.atributos = new Atributos[atributos.size()];
        addAtributos(atributos);
    }
    
    private void obtenerSeleccion(int columna, int renglon,JTable table){
        System.out.println(columna + " " + renglon);
        Atributos at = (Atributos)table.getValueAt(renglon, columna);
        System.out.println(at.getName());
    }
    
    private void addAtributos(ArrayList<Atributos> atributos){
        int i = 0;
        
        //metemos los atributos
        for (Atributos atributo : atributos) {
            this.atributos[i] = atributo;
            i++;
        }
    }
    
    private void modificarEntidad(String key,String tipoEntidad){
        
        if (tipoEntidad.equals("entidad")) {//entidad fuerte
            etiquetaEntidad.setText("Entidad: "+key);
        } else{//entidad debil
            etiquetaEntidad.setText("Entidad debil: "+key);
        }
        
    }
    
    private void agregarModeloTabla(){
        //agregamos los nombres de las columnas a la tabla
        modeloTabla.addColumn("Name");
        modeloTabla.addColumn("Datatype");
        modeloTabla.addColumn("Length");
        modeloTabla.addColumn("Precision");
        modeloTabla.addColumn("Not NULL?");
        modeloTabla.addColumn("Primary Key?");
    }
    
    private void addRows(ArrayList<Atributos> atributos){
        
        //recorremos los atributos
        for (Atributos atributo : atributos) {
            Object[] atributosv2 = {atributo.getName(),atributo.getData_type(),atributo.getLength(),atributo.getPrecision(),atributo.isNot_null(),atributo.isPrimary_key()};
            modeloTabla.addRow(atributosv2);
        }
    }
    
    
    //metodo para añadir las casillas de seleccion
    private void addCheckBox(int column,JTable table){
        TableColumn td = table.getColumnModel().getColumn(column);
        
        td.setCellEditor(table.getDefaultEditor(Boolean.class));
        td.setCellRenderer(table.getDefaultRenderer(Boolean.class));
    }
    
    public boolean IsSelected(int row,int column,JTable table){
        //regresamos si esta seleccionado
        return table.getValueAt(row, column)!=null;
    }
    
    private void addComboBox(int column, JTable table){
        //obtenemos la columna de la tabla
        TableColumn td = table.getColumnModel().getColumn(column);
        
        //creamos el comboBox
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("SERIAL");
        comboBox.addItem("VARCHAR");
        comboBox.addItem("INTEGER");
        comboBox.addItem("DATE");
        
        //añadimos el combo box a la tabla
        td.setCellEditor(new DefaultCellEditor(comboBox));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelColumas = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        etiquetaEntidad = new javax.swing.JLabel();
        botonGuardar = new javax.swing.JButton();
        panelSQL = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        textoCodigoSQL = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        textoModeloRelacional = new javax.swing.JTextArea();

        panelColumas.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setModel(modeloTabla);
        jScrollPane1.setViewportView(jTable1);

        panelColumas.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 560, 270));

        etiquetaEntidad.setText("Entidad");
        panelColumas.add(etiquetaEntidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 240, 20));

        botonGuardar.setText("Crear tabla y modelo relacional");
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        panelColumas.add(botonGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, -1, -1));

        jTabbedPane1.addTab("Columnas", panelColumas);

        textoCodigoSQL.setColumns(20);
        textoCodigoSQL.setRows(5);
        jScrollPane2.setViewportView(textoCodigoSQL);

        textoModeloRelacional.setColumns(20);
        textoModeloRelacional.setRows(5);
        jScrollPane3.setViewportView(textoModeloRelacional);

        javax.swing.GroupLayout panelSQLLayout = new javax.swing.GroupLayout(panelSQL);
        panelSQL.setLayout(panelSQLLayout);
        panelSQLLayout.setHorizontalGroup(
            panelSQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
            .addComponent(jScrollPane3)
        );
        panelSQLLayout.setVerticalGroup(
            panelSQLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSQLLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("SQL", panelSQL);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 560, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonGuardarActionPerformed
        //recorremos la tabla por renglones
        String modeloRelacional = "";
        if (tipoEntidad.toLowerCase().equals("entidad")) {
            String[] palabras = etiquetaEntidad.getText().split(" ");
            modeloRelacional += palabras[1]+" (";
        }else{
            String[] palabras = etiquetaEntidad.getText().split(" ");
            modeloRelacional += palabras[2]+" (";
        }
        
        
        //recorremos la tabla para llenar los atributos
        for (int i = 0; i < modeloTabla.getRowCount(); i++) {
            String name = (String)modeloTabla.getValueAt(i, 0);//obtenemos el nombre del atributo
            String dataType = (String)modeloTabla.getValueAt(i, 1);//obtenemos el tipo de dato
            String length = (String)modeloTabla.getValueAt(i, 2);//obtenemos la longitud
            String precision = (String)modeloTabla.getValueAt(i, 3);//obtenemos la precision
            boolean not_null = (boolean)modeloTabla.getValueAt(i, 4);//obtenemos si es o no nulo
            boolean primary_key = (boolean)modeloTabla.getValueAt(i, 5);//obtenemos si es llave primaria o no
            
            //metemos los datos en nuestros atributos
            this.atributos[i].setData_type(dataType);
            this.atributos[i].setLength(length);
            this.atributos[i].setPrecision(precision);
            this.atributos[i].setNot_null(not_null);
            this.atributos[i].setPrimary_key(primary_key);
            
            boolean llaveForania = atributos[i].isForeign_key();
            
            if (primary_key == true && llaveForania == false) {
                modeloRelacional += name +"* , ";
            }else if (primary_key == true && llaveForania == true) {
                modeloRelacional +=" *" + name +"** , ";
            } else if (llaveForania == true) {
                modeloRelacional += name +"** , ";
            } else {
                //si la i al sumarse llegase a ser el numero de renglones
                if ((i+1) == modeloTabla.getRowCount()) {
                    modeloRelacional += name;
                }else{
                    modeloRelacional += name + " , ";
                }
            }
        }
        
        modeloRelacional += ")";//se añade el final
        textoModeloRelacional.setText(modeloRelacional);
        crearCodigoSQL();
    }//GEN-LAST:event_botonGuardarActionPerformed
    
    private void crearCodigoSQL() {
        String codigoSQL = "CREATE TABLE " + this.key + " \n(\n";

        //guardadmos las primarias y las foranias
        ArrayList<String> llavesPrimarias = new ArrayList<>();
        ArrayList<Atributos> llavesForanias = new ArrayList<>();
        
        //recorremos todos los atributos
        for (int i = 0; i < this.atributos.length; i++) {
            //obtenemos el nombre
            String name = this.atributos[i].getName();
            codigoSQL += "\t " + name + " ";

            String tipo = this.atributos[i].getData_type();
            try{
                if (tipo.toLowerCase().equals("integer")) {
                    codigoSQL += tipo.toLowerCase() + " ";
                } else if (tipo.toLowerCase().equals("varchar")) {
                    int longitud = Integer.parseInt(this.atributos[i].getLength());
                    codigoSQL += "character varying" + "(" + longitud + ") ";
                } else if (tipo.toLowerCase().equals("serial")) {
                    codigoSQL += "serial ";
                } else if (tipo.toLowerCase().equals("date")) {
                    codigoSQL += "date ";
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(null, "Dato vacio en el renglon " + (i+1));
            }
            //checmos si es nulo
            boolean noNulo = this.atributos[i].isNot_null();
            if (noNulo == true) {
                codigoSQL += "NOT NULL";
            }

            //checamos si es llave primaria
            boolean primaryKey = this.atributos[i].isPrimary_key();
            if (primaryKey == true) {
                llavesPrimarias.add(name);
            }
            
            //checamos si es llave foranea
            boolean llaveForania = this.atributos[i].isForeign_key();
            if (llaveForania == true) {
                llavesForanias.add(this.atributos[i]);
            }
            
            //preguntamos si es el ultimo valor
            if ((i+1)==this.atributos.length) {
                codigoSQL += ",";
            }else{
                codigoSQL += ",\n";
            }

        }
        
        
        
        //agregamos el sql sobre la llave primaria
        for (String llave : llavesPrimarias) {
            codigoSQL += "\n\tCONSTRAINT " + llave + "key PRIMARY KEY (" + llave + ")";
        }
        
        //obtenemos las relaciones
        ArrayList<String> relaciones = tabla.getRelaciones();
        ArrayList<String> relacionesEncontradas = new ArrayList<>();
        
        for (String relacione : relaciones) {
            System.out.println(relacione);
            
            if (relacione.contains(key)) {
                relacionesEncontradas.add(relacione);
            }
        }
        
        //buscamos las relaciones
        for (String re : relacionesEncontradas) {
            String[] tablitas = re.split(" ");
            String nombre = "";
            //si la relacion de la tabla es muchos
            if (tablitas[0].contains(key) && tablitas[1].equals("n")) {
                //si la relacion es a muchos
                nombre = tablitas[2];
            } else if (tablitas[2].contains(key) && tablitas[3].equals("n")) {
                nombre = tablitas[2];
            }
            
            ArrayList<Atributos> atributos = hashEntidades.get(nombre);
            for (Atributos atributo : atributos) {
                for (Atributos llavesForania : llavesForanias) {
                    if (atributo.getName().contains(llavesForania.getName())) {
                        llavesForania.setTablaRelacionada(nombre);
                    }
                }
            }
        }
        
        //agregamos el sql sobre la llave forania
        for (Atributos llaveForania : llavesForanias) {
            //se agrega los atributos de la tabla
            codigoSQL += "\n\tCONSTRAINT " + llaveForania.getName()+"FK FOREIGN KEY ("+llaveForania.getName()+")";
            
            //se agrega la relacion con la otra tabla
            codigoSQL += "\n\tREFERENCES "+llaveForania.getTablaRelacionada()+" ("+llaveForania.getName()+") "+ "MATCH SIMPLE";
            
            //se agrega la actualizacion
            codigoSQL += "\n\tON UPDATE CASCADE\n" + "\tON DELETE CASCADE";
            
        }
        
        codigoSQL+="\n);";
        textoCodigoSQL.setText(codigoSQL);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonGuardar;
    private javax.swing.JLabel etiquetaEntidad;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JPanel panelColumas;
    private javax.swing.JPanel panelSQL;
    private javax.swing.JTextArea textoCodigoSQL;
    private javax.swing.JTextArea textoModeloRelacional;
    // End of variables declaration//GEN-END:variables
}
