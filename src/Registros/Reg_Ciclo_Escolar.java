/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases1.Conexion;
import Clases1.Validar_Campo_de_Texto;
import Clases1.fondo_imagen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Hp Pavilion g4
 */
public class Reg_Ciclo_Escolar extends javax.swing.JInternalFrame {

 //Atributos
int id_Ciclo_esc=0;
int x=0;
int y=0;
int idCiclo_esc_mod=0;
boolean editando=false;
 Validar_Campo_de_Texto v=new Validar_Campo_de_Texto();

//Los paso como parametro de medidas para la imagen de fondo
fondo_imagen  p=new fondo_imagen(x,y,"/Imagenes/fondo10.png");    
    String cabecera[]={"ID Ciclo Escolar","Ciclo Escolar","Desde","Hasta","Estado"};
    Object datos[][];
    
    DefaultTableModel md;
    public Reg_Ciclo_Escolar() {
        JOptionPane.showMessageDialog(null, "¿INICIA?");
        initComponents();
         JOptionPane.showMessageDialog(null, "SI");
        x=this.getWidth();
        y=this.getHeight();
        jPanel1.setOpaque(false);
       
       //Se recrea el objeto para que pinte el frame con los nuevos
       //Valores de x,y
       //Se hace de esto debido a que a partir de este constructor los valores del 
       //jframe cambian.
        p=new fondo_imagen(x,y,"/Imagenes/fondo10.png");
        //Se agrega el nuevo panel al jframe
        this.add(p);
        //Se empaqueta para que este se quede estatico sin modificar la posiciones de los
        //demas paneles.
        this.pack();
         JOptionPane.showMessageDialog(null, "CARGA EL FONDO");
      
        md=new DefaultTableModel(datos, cabecera);
        jTable1.setModel(md);
         JOptionPane.showMessageDialog(null, "CARGA EL MODELO DE LA TABLA");
         Desactivar_Campos();
         Desactivar_Botones();
        //No permite al usuario editar el id
        //Para que este lo asigne automaticamente
               
        v.SNumeros(txtID_Ciclo_Esc);
//        v.SLETRAS(txtCicloEscolar); 
 JOptionPane.showMessageDialog(null, "LLEGA A LAS CONSULTAS");
 //Consultar();
  JOptionPane.showMessageDialog(null, "TERMINA LAS CONSULTAS");
    }
    
    //Actualiza la columna estado con valor de 'activo' a inactivo (de todos los registros) 
    public void Actualizar_Todo(int idCicloEsc)
    {
         try
      {
   
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        //actualiza la columna estado con valor activo a inactivo(de todos los registros)   
        String actualizar="UPDATE ciclo_escolar SET estado='inactivo' where estado='activo' and id_Ciclo_Escolar!=?;";
        PreparedStatement pst=conn.prepareStatement(actualizar);
        pst.setInt(1, idCicloEsc);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Ciclos Escolares Actualizados");
        conn.close();
      }catch(SQLException e)
      {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }  
    }

    public void Consultar() 
    {
    //     limpiartabla(md);
    try {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.ciclo_escolar;";
         String Ciclo_esc_desde;
         String Ciclo_esc_hasta;
         String estado="activo";
        
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[6];
        while(rs.next())
        {
            info[0]=rs.getInt("id_Ciclo_Escolar");
            info[1]=rs.getString("Ciclo_Escolarcol");
            info[2]=rs.getString("año");
            info[3]=rs.getString("desde");
            //El siguiente switch cambia el formato del atributo "desde"
            //01/07/año por Agosto-Diciembre año
//            switch(info[2].toString())//Atributo "desde"
//                {
//            case "01-01-": 
//                Ciclo_esc_aux_desde="Enero-Julio";
//                break;
//                
//            case "01-07": 
//                Ciclo_esc_aux_desde="Agosto-Diciembre";
//                break;
//                }
            info[4]=rs.getString("hasta");
         //El siguiente switch cambia el formato al atributo "hasta"
         //01/07/año por Agosto-Diciembre año            
//            switch(info[3].toString())//Atributo hasta 
//                {
//            case "31-07-": 
//                Ciclo_esc_aux_hasta="Enero-Julio";
//                break;
//                
//            case "31-12": 
//                Ciclo_esc_aux_hasta="Agosto-Diciembre";
//                break;
//                }
          //Descompongo la cadena en un arreglo
         //  String desdeaux[] =info[2].toString().split("-");
           //Encapsulo la cadena y le cambio el estilo
           //Formato de Fecha      dia   /    mes       /     año
          // Ciclo_esc_desde=desdeaux[0]+"-"+desdeaux[1]+"-"+desdeaux[2];
         
          //Descompongo la cadena en un arreglo     
        //  String hastaaux[]=info[3].toString().split("-");
          //Encapsulo la cadena y le cambio el estilo
          //Formato de Fecha      dia   /    mes       /     año
         // Ciclo_esc_hasta=hastaaux[0]+"-"+hastaaux[1]+"-"+hastaaux[2];   
           //   JOptionPane.showMessageDialog(null, "XLSX");
            info[5]=rs.getString("estado");            
          //  JOptionPane.showMessageDialog(null, "KDOFLFKF");
            md.addRow(info);
        }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex.getMessage());
    }
    }
    
    public void limpiar_Campos()
    {
        txtID_Ciclo_Esc.setText("");
        jcbAño.setSelectedItem("Seleccione");
        jcbCiclo_Esc.setSelectedItem("Seleccione");
    }
    
    public void Activar_Botones()
    {
        btnRegistrar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnEliminar.setEnabled(true); 
        btnCancelar.setEnabled(true);
    }
    
    public void Desactivar_Botones()
    {
        btnRegistrar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false); 
        btnCancelar.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtID_Ciclo_Esc = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jcbCiclo_Esc = new javax.swing.JComboBox<>();
        jcbAño = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        btnEditar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Registro Ciclo Escolar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Clave del Ciclo Escolar:");

        txtID_Ciclo_Esc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID_Ciclo_EscActionPerformed(evt);
            }
        });

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Ciclo Escolar:");

        btnNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/nuevo.png"))); // NOI18N
        btnNuevo.setText("Nuevo");
        btnNuevo.setAlignmentY(0.2F);
        btnNuevo.setBorder(null);
        btnNuevo.setBorderPainted(false);
        btnNuevo.setContentAreaFilled(false);
        btnNuevo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnNuevo.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnRegistrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Guardar.png"))); // NOI18N
        btnRegistrar.setText("Guardar");
        btnRegistrar.setBorder(null);
        btnRegistrar.setBorderPainted(false);
        btnRegistrar.setContentAreaFilled(false);
        btnRegistrar.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Eliminar.png"))); // NOI18N
        btnEliminar.setText("eliminar");
        btnEliminar.setBorder(null);
        btnEliminar.setBorderPainted(false);
        btnEliminar.setContentAreaFilled(false);
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnMostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/buscar.png"))); // NOI18N
        btnMostrar.setText("Mostrar");
        btnMostrar.setBorder(null);
        btnMostrar.setBorderPainted(false);
        btnMostrar.setContentAreaFilled(false);
        btnMostrar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMostrarActionPerformed(evt);
            }
        });

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/salir.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.setBorder(null);
        btnSalir.setBorderPainted(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        jcbCiclo_Esc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Enero-Julio", "Agosto-Diciembre" }));

        jcbAño.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));

        jLabel3.setText("Año:");

        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jcbAño, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtID_Ciclo_Esc)
                                    .addComponent(jcbCiclo_Esc, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnNuevo))
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addComponent(btnRegistrar))
                        .addGap(60, 60, 60)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSalir)
                            .addComponent(btnMostrar)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(385, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID_Ciclo_Esc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbCiclo_Esc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jcbAño, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNuevo)
                            .addComponent(btnRegistrar))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnEliminar)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnMostrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir)))
                .addContainerGap(309, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 564, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 422, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //Activa los campos y botones disponibles
        Activar_Campos();
        Activar_Botones();
        //Desactiva el boton Nuevo
        btnNuevo.setEnabled(false);
        editando=false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        boolean exito=false;
        //Confirma que no hayan campos vacios
        if(jcbAño.getSelectedItem().toString().equals("Seleccione") ||
            jcbCiclo_Esc.getSelectedItem().toString().equals("Seleccione") || 
            txtID_Ciclo_Esc.getText().equals("") )
        {
            JOptionPane.showMessageDialog(null, "Hay informacion sin seleccionar");
        }
        else
        {
            
            int idCiclo_Esc=Integer.parseInt(txtID_Ciclo_Esc.getText());
            //confirma si se encuentra en modo de edicion
            if(editando==false)
            {

                exito=Insertar(idCiclo_Esc);//Confirma que se realizo la Inserccion correctamente
                limpiartabla(md);
                Consultar();
                
                if(exito)
                {
                    Actualizar_Todo(idCiclo_Esc);
                    //Desactiva los campos y botones                
                    Desactivar_Botones();                  
                    Desactivar_Campos();                  
                    //Activa el boton nuevo
                    btnNuevo.setEnabled(true);
                    //Limpia la informacion que se encuentra en los campos                   
                    limpiar_Campos();                   
                }
            }
            else
            {
                //Activa los botones de Editar y Canclear
                btnEditar.setEnabled(false);                                
                idCiclo_esc_mod=Integer.parseInt(txtID_Ciclo_Esc.getText());
                //Si esta se encuentra en la base de datos
                if(idCiclo_esc_mod!=0)
                {
                    //Actuliza un registro en especifico de acuerdo al parametro que le paso
                    Actualizar(idCiclo_esc_mod);
                    //Parche por si alguna columna y fila estan seleccionadas
                    if(jTable1.getSelectedRow()>=0 ||jTable1.getSelectedColumn()>=0 )
                    {

                        //Esto permite actualizar correctamente la informacion en caso que este
                        //seleccionada alguna columna o fila
                        //Deselecciona la fila
                        md.setRowCount(-1);
                        //Deselecciona la columna
                        md.setColumnCount(-1);
                    }
                    limpiartabla(md);
                    Consultar();
                    //Desactiva los campos y botones
                    Desactivar_Botones();
                    Desactivar_Botones();
                    //Limpia la informacion que se encuentra en los campos
                    limpiar_Campos();
                    //Activa el boton nuevo
                    btnCancelar.setEnabled(false);
                    btnNuevo.setEnabled(true);
                }
                //Si no se encuentra mandara el siguiente mensaje
                else
                {
                    JOptionPane.showMessageDialog(null, "Valores Erroneos");
                }
            }

        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:

        if(txtID_Ciclo_Esc.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingreso el ID del Ciclo Escolar");
        }
        else
        {
            //Regresa un verdadero o un falso dependiendo de si se elimino
            //Correctamente o no.
            boolean exito=Eliminar();
            if(exito==true)
            {
                //En caso de que se haya eliminado
                Desactivar_Botones();
                //No permite editar los campos
                Desactivar_Campos();
                //Deja todo en la opcion seleccione
                limpiar_Campos();
                //Activa el boton nuevo
                btnNuevo.setEnabled(true);
                //Actualiza la tabla sin los registros eliminados
                limpiartabla(md);
                Consultar();
            }
        }

    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMostrarActionPerformed
        //Desactiva esos elementos
        Desactivar_Botones();
        Desactivar_Campos();
        //Limpia todos los campos
        limpiar_Campos();       
        //Muestra la informacion en la tabla.
        limpiartabla(md);
        Consultar();
        btnCancelar.setEnabled(false);
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Desactiva el boton de Cancelar
        btnCancelar.setEnabled(false);
        //Desactiva los campos y los botones
        Desactivar_Botones();
        Desactivar_Campos();
        limpiar_Campos();
        //Activa el Boton de Nuevo
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void txtID_Ciclo_EscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtID_Ciclo_EscActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtID_Ciclo_EscActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
          editando=true;
        //Comprueba que no existan campos vacios
        if(txtID_Ciclo_Esc.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingresado el ID del Ciclo Escolar");
        }
        else
        {
            
            id_Ciclo_esc=Integer.parseInt(txtID_Ciclo_Esc.getText());
            //Guarda en una arreglo de tipo cadena los valores obtenidos de la base de datos
            Object Valores1[]=Comprobar_Id_Ciclo_Esc(id_Ciclo_esc);
            //Guarda el ID si esta en la base de datos.
            id_Ciclo_esc=Integer.parseInt(""+Valores1[0].toString());          
            //Comprueba si no recibo informacion vacia o nula
            if(id_Ciclo_esc==0)
            {
                JOptionPane.showMessageDialog(null,"El ID de Registro es Incorrecto");
            }
            else
            {
                //Pasa la informacion del arreglo de Objetos al los campos.
                txtID_Ciclo_Esc.setText(Valores1[0].toString());
                jcbCiclo_Esc.setSelectedItem(Valores1[1].toString());
                jcbAño.setSelectedItem(Valores1[2].toString());
                //Desactiva el boton de Edicion.
                btnEditar.setEnabled(false);
                //Cambia al modo edicion.
                editando=true;
                //Activa el boton para Guardar los cammbios realizados
                btnRegistrar.setEnabled(true);
                //Agrega la informacion al jtable
               // md.addRow(Valores1);
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed

    
     public Object[] Comprobar_Id_Ciclo_Esc(int d)
    {
        Object valores3[]=new Object[5];
        int x=0;
        try
        {
        String consultar="SELECT * FROM xorox.ciclo_escolar where id_Ciclo_Escolar="+d+";";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);        
        while(rs.next())
        {
         valores3[0]=rs.getInt("id_Ciclo_Escolar");
         valores3[1]=rs.getString("Ciclo_Escolarcol");
         valores3[2]=rs.getString("desde");
         valores3[3]=rs.getString("hasta");
         valores3[4]=rs.getString("estado");
//         JOptionPane.showMessageDialog(null, datos[0]);
        }
        conn.close();
        
        btnEliminar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnCancelar.setEnabled(true);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());                    
        }
        return valores3;
    }
     
     public void Activar_Campos()
     {
         txtID_Ciclo_Esc.setEnabled(true);
         jcbCiclo_Esc.setEnabled(true);
         jcbAño.setEnabled(true);
     }
     
     public void Desactivar_Campos()
     {
         txtID_Ciclo_Esc.setEnabled(false);
         jcbCiclo_Esc.setEnabled(false);
         jcbAño.setEnabled(false);
     }
    
      public void Actualizar(int Ciclo_esc_Mod)
    {
         
        try
      {
   
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        //Segun la columna que haya seleccionado sera el valor que se modique
        
        String Ciclo_esc=jcbCiclo_Esc.getSelectedItem().toString();
        String Ciclo_esc_aux_desde="";
         String Ciclo_esc_aux_hasta="";
         String estado="activo";
         String año=jcbAño.getSelectedItem().toString();
        switch(Ciclo_esc)
                {
            case "Enero-Julio": 
                Ciclo_esc_aux_desde="01/01/";
                Ciclo_esc_aux_hasta="31/07/";
                break;
                
            case "Agosto-Diciembre": 
                Ciclo_esc_aux_desde="01/08/";
                Ciclo_esc_aux_hasta="31/12/";
                break;
                }
        String actualizar="UPDATE `xorox`.`ciclo_escolar`"
        + " SET `id_Ciclo_Escolar`=?, `Ciclo_Escolarcol`=?, `desde`=?, "
        + "`hasta`=?, `estado`=? WHERE `id_Ciclo_Escolar`=?;";
        PreparedStatement pst=conn.prepareStatement(actualizar);
        pst.setInt(1, Ciclo_esc_Mod);
        pst.setString(2, Ciclo_esc);
        pst.setString(3,Ciclo_esc_aux_desde+año);
        pst.setString(4, Ciclo_esc_aux_hasta+año);
        pst.setString(5, estado);
        //Id del cicli escolar a modificar  
        pst.setInt(6, id_Ciclo_esc);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cambio realizado con exito");
        conn.close();
      }catch(SQLException e)
      {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }  
    }
            
    public void limpiar()
    {
        txtID_Ciclo_Esc.setText("");
        jcbAño.setSelectedItem("Seleccione");
        jcbCiclo_Esc.setSelectedItem("Seleccione");
    }
      
    public boolean Insertar(int id_Ciclo_Esc)
    {
        boolean exito=false;     
         try
            {
         String registrar="INSERT INTO `xorox`.`ciclo_escolar` "
         + "(`id_Ciclo_Escolar`, `Ciclo_Escolarcol`, `año`, `desde`, `hasta`, `estado`) "
         + "VALUES (?,?,?,?,?,?);";
         //int idCiclo_Esc=Integer.parseInt(txtID_Ciclo_Esc.getText());
         String Ciclo_esc=jcbCiclo_Esc.getSelectedItem().toString();
         int año=Integer.parseInt(jcbAño.getSelectedItem().toString());
         String Ciclo_esc_aux_desde="";
         String Ciclo_esc_aux_hasta="";
         String estado="activo";
         //Cambio el formato al estilo de la base de datos
         switch(Ciclo_esc)
         {
         case "Enero-Julio": 
                Ciclo_esc_aux_desde="01/01/"+año;
                Ciclo_esc_aux_hasta="31/07/"+año;
                break;
                
        case "Agosto-Diciembre": 
                Ciclo_esc_aux_desde="01/07/"+año;
                Ciclo_esc_aux_hasta="31/12/"+año;
                break;
          }
         Conexion miconexion=new Conexion();
         Connection conn= miconexion.getConnection();
         PreparedStatement pst=conn.prepareStatement(registrar);
         Object info[]={id_Ciclo_Esc,Ciclo_esc,Ciclo_esc_aux_desde,Ciclo_esc_aux_hasta,estado};     
         //Se concatena el año a la cadena del ciclo
         pst.setInt(1, id_Ciclo_Esc);
         pst.setString(2, Ciclo_esc);
         //Formato de Fecha dia/mes/año desde que fecha empieza el Ciclo Escolar
         //Se concatena un slash para completar el formato de fecha
         pst.setInt(3, año);
         pst.setString(4, Ciclo_esc_aux_desde);
         //Formato de Fecha dia/mes/año hasta que fecha termina el Ciclo Escolar
         //Se concatena un slash para completar el formato de fecha
         pst.setString(5, Ciclo_esc_aux_hasta);
         pst.setString(6, estado);
         pst.executeUpdate();
         md.addRow(info);
         limpiar();
         JOptionPane.showMessageDialog(null, "Registro Exitoso");
         conn.close();
         btnNuevo.setEnabled(true);
         btnRegistrar.setEnabled(false);
         btnCancelar.setEnabled(false);
         exito=true;
        }catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
         return exito;
    }
    
    public boolean Eliminar()
    {
        boolean exito=false;
              btnEliminar.setEnabled(false);
            try
        {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        //Obtiene la fila a eliminar
        int idCiclo_Esc=Integer.parseInt(txtID_Ciclo_Esc.getText());      
        String eliminar="DELETE FROM `xorox`.`ciclo_escolar` WHERE `id_Ciclo_Escolar`=?;";
        PreparedStatement pst=conn.prepareStatement(eliminar);  
        pst.setInt(1, idCiclo_Esc);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Registro Eliminado");
        conn.close();  
        exito=true;
        btnCancelar.setEnabled(false);        
        }catch(SQLException e)
        {
           JOptionPane.showMessageDialog(null, e.getMessage());
        }
        return exito;
    }
    
       public void limpiartabla(DefaultTableModel md)
    {
       //Recibo el modelo de la tabla para limpiarlo de manera correcta 
  for (int i = 0; i < jTable1.getRowCount(); i++) {
           md.removeRow(i);
           i-=1;
       }
    }
       

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox<String> jcbAño;
    private javax.swing.JComboBox<String> jcbCiclo_Esc;
    private javax.swing.JTextField txtID_Ciclo_Esc;
    // End of variables declaration//GEN-END:variables
}
