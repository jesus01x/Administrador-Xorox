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
 * @author Administrador
 */
public class Reg_Materias extends javax.swing.JInternalFrame {

  //Atributos
     int x=0;
     int y=0;
     int id_Materia=0;
     String Materia="";
     int id_Materia_mod=0;
     boolean editando=false;
Validar_Campo_de_Texto v=new Validar_Campo_de_Texto();
//Los paso como parametro de medidas para la imagen de fondo
fondo_imagen  p=new fondo_imagen(x,y,"/Imagenes/fondo13.png");  
    int id_Aula;
    DefaultTableModel md;
    String datos[][];
    String cabecera[]={"ID_Materia","Materia"};
    
    public Reg_Materias()  {
        initComponents();
        md=new DefaultTableModel(datos, cabecera);
        jTable1.setModel(md);
         x=this.getWidth();
        y=this.getHeight();
        jPanel1.setOpaque(false);
       
       //Se recrea el objeto para que pinte el frame con los nuevos
       //Valores de x,y
       //Se hace de esto debido a que a partir de este constructor los valores del 
       //jframe cambian.
        p=new fondo_imagen(x,y,"/Imagenes/fondo13.png");
        //Se agrega el nuevo panel al jframe
        this.add(p);
        //Se empaqueta para que este se quede estatico sin modificar la posiciones de los
        //demas paneles.
        this.pack();
         try {
             Consultar();
         } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error: "+ex.getMessage());
         }
         v.SNumeros(txtID_Materia);
         v.SLETRAS_y_Espacios(txt_Materia);
         Desactivar_Botones();
         Desactivar_Campos();
    }

    
    //Metodos
    
     public void Activar_Campos()
     {
         txtID_Materia.setEnabled(true);
         txt_Materia.setEnabled(true);
     }
     
     public void Activar_Botones()
     {
      btnCancelar.setEnabled(true);
      btnEditar.setEnabled(true);
      btnEliminar.setEnabled(true);
      btnMostrar.setEnabled(true);
      btnRegistrar.setEnabled(true);
      btnSalir.setEnabled(true);
     }
    
     public void Desactivar_Botones()
     {
         
     }
     
     public void Desactivar_Campos()
     {
         txtID_Materia.setEnabled(false);
         txt_Materia.setEnabled(false);
     }          
     
     public void limpiar_Campos()
     {
         txtID_Materia.setText("");
         txt_Materia.setText("");
     }         
     
     public Object[] Comprobar_Id_Materias(int d)
     {
         Object info[]=new Object[2];
        try
        {
        String consultar="SELECT * FROM xorox.materias where id_Materias="+d+";";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        while(rs.next())
        {
         info[0]=rs.getInt("id_Materias");
         info[1]=rs.getString("Materia");
        }
        conn.close();
        btnEliminar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnCancelar.setEnabled(true);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());                    
        }
        return info;
     }
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txt_Materia = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtID_Materia = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Materias");

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(0, 153, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Materia:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 70, -1, -1));
        jPanel1.add(txt_Materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 70, 270, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Materia", "Materia"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, 450, 170));

        jLabel2.setText("ID Materia:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, -1, -1));
        jPanel1.add(txtID_Materia, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 40, 268, -1));

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
        jPanel1.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, -1, -1));

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
        jPanel1.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, -1, -1));

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
        jPanel1.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 320, -1, -1));

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
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 400, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel1.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 420, 90, 50));

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
        jPanel1.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, -1, -1));

        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel1.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, 80, -1));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 576, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed
        // TODO add your handling code here:
        boolean exito=false;
        //Confirma que no hayan campos vacios
        if(txtID_Materia.getText().equals("") || txt_Materia.getText().equals("") )
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
                //Activa los botones de Editar y Canclear
                btnEditar.setEnabled(false);
                id_Materia_mod=Integer.parseInt(txtID_Materia.getText());
                //Si esta se encuentra en la base de datos
                if(id_Materia_mod!=0)
                {
                    //Actuliza un registro en especifico de acuerdo al parametro que le paso
                    Actualizar(id_Materia_mod);
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
                    Consultas();
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

        if(txtID_Materia.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingreso el ID de la Carrera");
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
        //Desactiva el boton Nuevo
        btnNuevo.setEnabled(false);
        editando=false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        editando=true;
        //Comprueba que no existan campos vacios
        if(txtID_Materia.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingresado el ID del Ciclo Escolar");
        }
        else
        {

            id_Materia=Integer.parseInt(txtID_Materia.getText());
            //Guarda en una arreglo de tipo cadena los valores obtenidos de la base de datos
            Object Valores1[]=Comprobar_Id_Materias(id_Materia);
            //Guarda el ID si esta en la base de datos.
            id_Materia=Integer.parseInt(Valores1[0].toString());
            //Comprueba si no recibo informacion vacia o nula
            if(id_Materia==0)
            {
                JOptionPane.showMessageDialog(null,"El ID de Registro es Incorrecto");
            }
            else
            {
                //Pasa la informacion del arreglo de Objetos al los campos.
                txtID_Materia.setText(Valores1[0].toString());
                txt_Materia.setText(Valores1[1].toString());
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
    
    public void Consultas()
    {
        try
        {
        String consultar="SELECT * FROM xorox.materias;";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object datos[]=new Object[2];
        while(rs.next())
        {
         datos[0]=rs.getString("id_Materias");
         datos[1]=rs.getString("Materia");
         md.addRow(datos);
        }
        conn.close();
        btnEliminar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnCancelar.setEnabled(true);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());                    
        }
    }
    
                 //ID de Materia a modificar
    public void Actualizar(int d)
    {
        
        try
      {
   
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        //Segun la columna que haya seleccionado sera el valor que se modique
        int idAula=Integer.parseInt(txtID_Materia.getText());
        String aula=txt_Materia.getText();
        String actualizar="UPDATE `xorox`.`materias` SET `id_Materias`=?, `Materia`=? WHERE `id_Materias`="+d+";";
        PreparedStatement pst=conn.prepareStatement(actualizar);       
        pst.setString(1, aula);
        pst.setInt(2, idAula);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cambio realizado con exito");
        conn.close();
      }catch(SQLException e)
      {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }  
    }
            
    
    public boolean Insertar()
    {
        boolean exito=false;
       String registrar="INSERT INTO `xorox`.`materias` (`id_Materias`, `Materia`) VALUES (?,?);";
       id_Materia=Integer.parseInt(txtID_Materia.getText());
       Materia=txt_Materia.getText();
       
         try
            {
                
                Conexion miconexion=new Conexion();
                Connection conn= miconexion.getConnection();
                PreparedStatement pst=conn.prepareStatement(registrar);
                Object info[]={id_Materia,Materia};
                pst.setInt(1, id_Materia);        
                pst.setString(2, Materia);
                pst.executeUpdate();
                md.addRow(info);
                limpiar();
                JOptionPane.showMessageDialog(null, "Registro Exitoso");
                conn.close();
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
        int idMateria=Integer.parseInt(txtID_Materia.getText());      
        String eliminar="DELETE FROM `xorox`.`materias` WHERE `id_Materias`="+idMateria+";";
        PreparedStatement pst=conn.prepareStatement(eliminar);  
        pst.setInt(1, idMateria);
        pst.execute();
        JOptionPane.showMessageDialog(null, "Registro Eliminado");
        conn.close();  
        btnCancelar.setEnabled(false);
        exito=true;
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
    
    public void limpiar()
    {
        txtID_Materia.setText("");
        txt_Materia.setText("");
    }
    
    public void Consultar() throws SQLException
    {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.materias;";
         Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[2];
        while(rs.next())
        {       
          info[0]=rs.getInt("id_Materias");
          info[1]=rs.getString("Materia");
          md.addRow(info);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtID_Materia;
    private javax.swing.JTextField txt_Materia;
    // End of variables declaration//GEN-END:variables
}
