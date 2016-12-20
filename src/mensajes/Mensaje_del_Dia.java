/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajes;

import Clases1.Conexion;
import Clases1.Obtener_Fecha;
import Clases1.Validar_Campo_de_Texto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author LugoUAdeC
 */
public class Mensaje_del_Dia extends javax.swing.JFrame {

    /**
     * Creates new form Mensaje_del_Dia
     */
    DefaultTableModel md;
    String cabecera[]={"ID Mensaje","Mensaje","Fecha"};
    Obtener_Fecha fecha = new Obtener_Fecha();
    String datos[][];
    boolean editando;//nos permite saber si esta editando un registro.
    int idmensaje_mod=0;
    Validar_Campo_de_Texto validar=new Validar_Campo_de_Texto();
    //Atributos
    int idmensaje=0;
    String mensaje="";
    String fechas="";
    
    public Mensaje_del_Dia() {
        initComponents();
        md=new DefaultTableModel(datos, cabecera);
        jTable1.setModel(md);
        txtID_Mensaje.setEnabled(false);
        Desactivar_Botones();
        Desactivar_Campos();
        Consultas();
        validar.SNumeros(txtID_Mensaje);
    }

    //Metodos
        public void Consultas() 
    {
    //     limpiartabla(md);
    try {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.mensaje_del_dia;";
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[3];
        while(rs.next())
        {
            info[0]=rs.getInt("idmensaje_del_dia");
            info[1]=rs.getString("mensaje");
            info[2]=rs.getString("Fecha");            
          //  JOptionPane.showMessageDialog(null, "KDOFLFKF");
            md.addRow(info);
        }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex);
    }
    }
    
    public void limpiar_Campos()
    {
        txtMensaje.setText("");
    }
    
    public void Activar_Botones()
    {
        btnRegistrar.setEnabled(true);
        btncomprobar.setEnabled(true);
        btnEliminar.setEnabled(true); 
        btnCancelar.setEnabled(true);
        btneditar_y_eliminar.setEnabled(true);
        btnMostrar.setEnabled(true);
    }
    
    public void Desactivar_Botones()
    {
        btnRegistrar.setEnabled(false);
        btncomprobar.setEnabled(false);
        btnEliminar.setEnabled(false); 
        btnCancelar.setEnabled(false);
        btneditar_y_eliminar.setEnabled(false);
        btnMostrar.setEnabled(false);
    }
    
     public void Activar_Campos()
     {
        txtID_Mensaje.setEnabled(true);
        txtMensaje.setEnabled(true);
        jDateChooser1.setEnabled(true);
     }
     
     public void Desactivar_Campos()
     {
         txtID_Mensaje.setEnabled(false);
         txtMensaje.setEnabled(false);
         jDateChooser1.setEnabled(false);
     }
    
      public void Actualizar(int idmensaje,String mensaje,String fecha)
    {
        try
      {   
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
       
        //Segun la columna que haya seleccionado sera el valor que se modique     
        String actualizar="UPDATE `xorox`.`mensaje_del_dia` SET `mensaje`=?, `Fecha`=? WHERE `idmensaje_del_dia`=?;";
        PreparedStatement pst=conn.prepareStatement(actualizar);             
        pst.setString(1, mensaje);
        pst.setString(2,fecha);
        pst.setInt(3, idmensaje);
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
        txtID_Mensaje.setText("");
        txtMensaje.setText("");
    }
      
    public boolean Insertar()
    {
        boolean exito=false;     
         try
            {
         String registrar="INSERT INTO `xorox`.`mensaje_del_dia` (`mensaje`, `Fecha`) VALUES (?,?);";
          mensaje=txtMensaje.getText();
          fechas=this.fecha.getFecha(jDateChooser1);        
         Conexion miconexion=new Conexion();
         Connection conn= miconexion.getConnection();
         PreparedStatement pst=conn.prepareStatement(registrar);
         Object info[]={mensaje,fecha};       
         pst.setString(1, mensaje);
         pst.setString(2, fechas);
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
         idmensaje=Integer.parseInt(txtID_Mensaje.getText());
        String eliminar="DELETE FROM `xorox`.`mensaje_del_dia` WHERE `idmensaje_del_dia`=?;";
        PreparedStatement pst=conn.prepareStatement(eliminar);  
        pst.setInt(1, idmensaje);
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
       
    public Object[] Comprobar_IDMensaje(int idMensaje)
    {
        Object info[]=new Object[3];
        try
        {
        String consultar="SELECT * FROM xorox.mensaje_del_dia where idmensaje_del_dia="+idMensaje+";";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        
        while(rs.next())
        {
         info[0]=rs.getString("idmensaje_del_dia");
         info[1]=rs.getString("mensaje");
         info[2]=rs.getString("Fecha");
        }
        conn.close();
        btnEliminar.setEnabled(true);
        btncomprobar.setEnabled(true);
        btnCancelar.setEnabled(true);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());                    
        }
        return info;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtID_Mensaje = new javax.swing.JTextField();
        btncomprobar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        btneditar_y_eliminar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        btnRegistrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mensaje del dia");

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setText("ID Mensaje:");

        btncomprobar.setText("Comprobar");
        btncomprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprobarActionPerformed(evt);
            }
        });

        jLabel2.setText("Mensaje:");

        txtMensaje.setColumns(20);
        txtMensaje.setRows(5);
        jScrollPane1.setViewportView(txtMensaje);

        jDateChooser1.setDateFormatString("dd/MM/yyyy");

        jLabel3.setText("Fecha:");

        btneditar_y_eliminar.setText("Deseas editar o eliminar un mensaje?");
        btneditar_y_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_y_eliminarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtID_Mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btncomprobar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(152, 152, 152)
                        .addComponent(btneditar_y_eliminar)))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btneditar_y_eliminar)
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtID_Mensaje, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btncomprobar))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addContainerGap(39, Short.MAX_VALUE))
        );

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

        jLabel4.setFont(new java.awt.Font("Segoe UI Symbol", 0, 24)); // NOI18N
        jLabel4.setText("Programacion de Mensajes");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnNuevo)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEliminar)
                                    .addComponent(btnRegistrar))
                                .addGap(60, 60, 60)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSalir)
                                    .addComponent(btnMostrar)))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(95, 95, 95)
                                .addComponent(jLabel4))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(24, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(btnMostrar)
                        .addGap(18, 18, 18)
                        .addComponent(btnSalir))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(btnNuevo)
                                .addGap(7, 7, 7))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnRegistrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnEliminar)
                            .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:

        if(txtID_Mensaje.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingreso el ID del mensaje");
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
                Consultas();
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
        Consultas();
        btnCancelar.setEnabled(false);
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Desactiva el modo edicion
        editando=false;
        btnCancelar.setEnabled(false);
        //Desactiva los campos y los botones
        Desactivar_Botones();
        Desactivar_Campos();
        limpiar_Campos();
        //Activa el Boton de Nuevo
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        //Activa los campos y botones disponibles
        Activar_Campos();
        Activar_Botones();
        txtID_Mensaje.setEnabled(false);
        //Desactiva el boton Nuevo
        btnNuevo.setEnabled(false);
        btncomprobar.setEnabled(false);
        editando=false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        boolean exito=false;
        //Confirma que no hayan campos vacios
        if(txtMensaje.getText().equals("") )
        {
            JOptionPane.showMessageDialog(null, "No se permiten Campos Vacios");
        }
        else
        {
            //confirma si se encuentra en modo de edicion
            if(editando==false)
            {

                exito=Insertar();//Confirma que se realizo la Inserccion correctamente
                limpiartabla(md);
                Consultas();

                if(exito)
                {
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
                //Si esta se encuentra en la base de datos                 
                mensaje=txtMensaje.getText();
                fechas=fecha.getFecha(jDateChooser1);        
                //Actuliza un registro en especifico de acuerdo al parametro que le paso
                Actualizar(idmensaje_mod,mensaje,fechas);      
                limpiartabla(md);
                Consultas();
                //Desactiva los campos y botones
                Desactivar_Botones();
                Desactivar_Campos();
                //Limpia la informacion que se encuentra en los campos
                limpiar_Campos();
                //Activa el boton nuevo
                btneditar_y_eliminar.setEnabled(true);
                btnCancelar.setEnabled(false);
                btnNuevo.setEnabled(true);
                
                //Si no se encuentra mandara el siguiente mensaje
               
            }

        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btncomprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomprobarActionPerformed
        // TODO add your handling code here:
        
        //Comprueba que no existan campos vacios
        if(txtID_Mensaje.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingresado el ID del Ciclo Escolar");
        }
        else
        {
            editando=true;
            Activar_Campos();
            int idmensaje=Integer.parseInt(txtID_Mensaje.getText());
            //Guarda en una arreglo de tipo cadena los valores obtenidos de la base de datos
            Object valores1[]=Comprobar_IDMensaje(idmensaje);
            //Guarda el ID si esta en la base de datos.
            idmensaje=Integer.parseInt(""+valores1[0].toString());
            //Comprueba si no recibo informacion vacia o nula
            if(idmensaje==0)
            {
                JOptionPane.showMessageDialog(null,"El ID de Registro es Incorrecto");
            }
            else
            {
                //Pasa la informacion del arreglo de Objetos al los campos.
                txtID_Mensaje.setText(valores1[0].toString());
                txtMensaje.setText(valores1[1].toString());
                Date a=new Date(valores1[2].toString());
                jDateChooser1.setDate(a);
                        //Desactiva el boton de Edicion.
                btncomprobar.setEnabled(false);
                //Cambia al modo edicion.
                editando=true;
                //Activa el boton para Guardar los cammbios realizados
                btnRegistrar.setEnabled(true);
                idmensaje_mod=Integer.parseInt(valores1[0].toString());
                //Agrega la informacion al jtable
                // md.addRow(Valores1);
            }
        }
                    
    }//GEN-LAST:event_btncomprobarActionPerformed

    private void btneditar_y_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_y_eliminarActionPerformed
        // TODO add your handling code here:        
        btneditar_y_eliminar.setEnabled(false);        
        Desactivar_Botones();
        Desactivar_Campos();
        txtID_Mensaje.setEnabled(true);
        btnRegistrar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btncomprobar.setEnabled(true);
    }//GEN-LAST:event_btneditar_y_eliminarActionPerformed

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
            java.util.logging.Logger.getLogger(Mensaje_del_Dia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mensaje_del_Dia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mensaje_del_Dia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mensaje_del_Dia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mensaje_del_Dia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btncomprobar;
    private javax.swing.JButton btneditar_y_eliminar;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtID_Mensaje;
    private javax.swing.JTextArea txtMensaje;
    // End of variables declaration//GEN-END:variables
}
