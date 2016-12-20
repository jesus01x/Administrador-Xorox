/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajes;

import Clases1.Conexion;
import Clases1.Obtener_Fecha;
import Clases1.Validar_Campo_de_Texto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
 * @author Hp Pavilion g4
 */
public class Mensaje_Personal extends javax.swing.JFrame {

    //Atributos
    DefaultTableModel md;
    DefaultTableModel md2;
    String cabecera[]={"No Matricula","Alumno","Carrera"};
    String cabecera2[]={"ID Mensaje P.","No Matricula","Mensaje","Visto","Fecha","Hora","Aula"};
    Obtener_Fecha fecha = new Obtener_Fecha();
    String datos[][];
    boolean editando;//nos permite saber si esta editando un registro.
    int idmensaje_mod=0;
    Validar_Campo_de_Texto validar=new Validar_Campo_de_Texto();
    //Atributos
    int No_Matricula=0;
    String Alumno="";
    int idmensaje=0;
    String mensaje="";
    
    public Mensaje_Personal() {
        initComponents();
        md=new DefaultTableModel(datos, cabecera);
        jTable1.setModel(md);
        md2=new DefaultTableModel(datos, cabecera2);
        jTable2.setModel(md2);
        
        //Controla el orden en que se muestran los alumnos
        jcb_Filtrado1_tipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //limpia la tabla si no hay filtro
                if(jcb_Filtrado1_tipo.getSelectedIndex()==0)
                {
                    limpiartabla(md);
                }
                if(jcb_Filtrado1_tipo.getSelectedIndex()==1)
                {
                    //Filtra los alumnos por orden alfabetico
                    limpiartabla(md);
                    Consulta_Por_Nombre();
                }
                if(jcb_Filtrado1_tipo.getSelectedIndex()==2)
                {
                    //Filtra los alumnos por numero de menor a mayor
                    limpiartabla(md);
                    Consulta_por_Matricula();
                }
            }
        });
        
        jcb_Filtro2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              if(jcb_Filtro2.getSelectedIndex()==0)
              {
                 txtNo_Matricula_Filtro.setEnabled(false);
                 btnActualizar_Lista.setEnabled(false);
              }
              if(jcb_Filtro2.getSelectedIndex()==1)
              {
                 Consultar_Mensajes_Personales();
                 txtNo_Matricula_Filtro.setEnabled(false);
                 btnActualizar_Lista.setEnabled(false);
              }
              if(jcb_Filtro2.getSelectedIndex()==2)
              {
                 txtNo_Matricula_Filtro.setEnabled(true);
                 btnActualizar_Lista.setEnabled(true);
              }
              
            }
        });
        
        txtID_Mensaje_Personal.setEnabled(false);
        txtNo_Matricula_Filtro.setEnabled(false);
        btnActualizar_Lista.setEnabled(false);
        Desactivar_Botones();
        Desactivar_Campos();
        Consultas();
        Consultar_Mensajes_Personales();
        txtAlumno.setEditable(false);
        validar.SNumeros(txtID_Mensaje_Personal);
        validar.SNumeros(txtNo_Matricula_Filtro);
    }
    
     public void Consulta_Por_Nombre() 
    {
    //     limpiartabla(md);
    try {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.alumnos order by Alumno asc;";
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[3];
        while(rs.next())
        {
            info[0]=rs.getInt("NO_MATRICULA");
            info[1]=rs.getString("Alumno");
            info[2]=rs.getString("Carrera");            
          //  JOptionPane.showMessageDialog(null, "KDOFLFKF");
            md.addRow(info);
        }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex);
    }
    }
     
      public void Consultas() 
    {
    //     limpiartabla(md);
    try {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.alumnos;";
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[3];
        while(rs.next())
        {
            info[0]=rs.getInt("NO_MATRICULA");
            info[1]=rs.getString("Alumno");
            info[2]=rs.getString("Carrera");            
          //  JOptionPane.showMessageDialog(null, "KDOFLFKF");
            md.addRow(info);
        }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex);
    }
    }
      
       public void Consultar_Mensajes_Personales() 
    {
         limpiartabla_2(md2);
    try {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.mensaje_personal order by NO_MATRICULA ASC;";
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[6];
        while(rs.next())
        {
            info[0]=rs.getInt("idmensaje_personal");
            info[1]=rs.getString("NO_MATRICULA");
            info[2]=rs.getString("Mensaje");
            info[3]=rs.getInt("Visto");
            int v=Integer.parseInt(info[3].toString());
            //converte el 0 en No
            if(v==0)
            {
               info[3]="No"; 
            }
            //convierte el 1 en Si
            if(v==1)
            {
                info[3]="Si";
            }
            info[4]=rs.getString("Fecha");
            info[5]=rs.getString("Hora");
                              
          //  JOptionPane.showMessageDialog(null, "KDOFLFKF");
            md2.addRow(info);
        }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex);
    }
    }
       
       public void Consultar_Mensajes_Personales_Filtro_Por_Matricula(int No_Matricula) 
    {
         limpiartabla_2(md2);
    try {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.mensaje_personal where NO_MATRICULA="+No_Matricula+";";
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[6];
        while(rs.next())
        {
            info[0]=rs.getInt("idmensaje_personal");
            info[1]=rs.getString("NO_MATRICULA");
            info[2]=rs.getString("Mensaje");
            info[3]=rs.getInt("Visto");
            int v=Integer.parseInt(info[3].toString());
            //converte el 0 en No
            if(v==0)
            {
               info[3]="No"; 
            }
            //convierte el 1 en Si
            if(v==1)
            {
                info[3]="Si";
            }
            info[4]=rs.getString("Fecha");
            info[5]=rs.getString("Hora");
                              
          //  JOptionPane.showMessageDialog(null, "KDOFLFKF");
            md2.addRow(info);
        }
    } catch (SQLException ex) {
      JOptionPane.showMessageDialog(null, ex);
    }
    }

     public void Consulta_por_Matricula() 
    {
    //     limpiartabla(md);
    try {
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection(); 
        String consultar="SELECT * FROM xorox.alumnos order by NO_MATRICULA asc;";
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info[]=new Object[3];
        while(rs.next())
        {
            info[0]=rs.getInt("NO_MATRICULA");
            info[1]=rs.getString("Alumno");
            info[2]=rs.getString("Carrera");            
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
        txtID_Mensaje_Personal.setEnabled(true);
        txtMensaje.setEnabled(true);
//        jDateChooser1.setEnabled(true);
     }
     
     public void Desactivar_Campos()
     {
         txtID_Mensaje_Personal.setEnabled(false);
         txtMensaje.setEnabled(false);
//         jDateChooser1.setEnabled(false);
     }
    
      public void Actualizar(int idmensaje,int No_matricula,String msn)
    {
        try
      {   
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
       
        //Segun la columna que haya seleccionado sera el valor que se modique     
        String actualizar="UPDATE `xorox`.`mensaje_personal` SET `No_Matricula`=?, `Mensaje`=?, `Visto`=0 WHERE `idmensaje_personal`=?;";
        PreparedStatement pst=conn.prepareStatement(actualizar);             
        pst.setInt(1, No_matricula);
        pst.setString(2,msn);
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
        txtID_Mensaje_Personal.setText("");
        txtMensaje.setText("");
    }
    
    
      
    public boolean Insertar()
    {
        boolean exito=false;     
         try
            {
         String registrar="INSERT INTO `xorox`.`mensaje_personal` (`No_Matricula`, `Mensaje`, `Visto`) VALUES (?,?, 0);";
         mensaje=txtMensaje.getText();
//          fechas=this.fecha.getFecha(jDateChooser1);        
         Conexion miconexion=new Conexion();
         Connection conn= miconexion.getConnection();
         PreparedStatement pst=conn.prepareStatement(registrar);
         Object info[]={No_Matricula,mensaje};       
         pst.setInt(1, No_Matricula);
         pst.setString(2, mensaje);
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
         idmensaje=Integer.parseInt(txtID_Mensaje_Personal.getText());
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
       
       public void limpiartabla_2(DefaultTableModel md)
    {
       //Recibo el modelo de la tabla para limpiarlo de manera correcta 
  for (int i = 0; i < jTable2.getRowCount(); i++) {
           md.removeRow(i);
           i-=1;
       }
    }
       
    public Object[] Comprobar_IDMensaje(int idMensaje)
    {
        JOptionPane.showMessageDialog(null, "ID:"+idMensaje);
        Object info2[]=new Object[3];
        try
        {
        String consultar="SELECT a.NO_MATRICULA,a.mensaje,b.Alumno FROM mensaje_personal a, alumnos b  where a.NO_MATRICULA=b.NO_MATRICULA and idmensaje_personal="+idMensaje+";";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);        
        while(rs.next())
        {
         info2[0]=rs.getInt("NO_MATRICULA");
         info2[1]=rs.getString("mensaje");
         info2[2]=rs.getString("Alumno");
        }
        conn.close();
        btnEliminar.setEnabled(true);
        btncomprobar.setEnabled(true);
        btnCancelar.setEnabled(true);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());                    
        }
        return info2;
    }
    
    public int Comprobar_No_Matricula(String alumno)
    {
        int mat=0;
        try
        {
        String consultar="SELECT NO_MATRICULA FROM xorox.alumnos where Alumno='"+alumno+"';";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);        
        while(rs.next())
        {
         mat=rs.getInt("NO_MATRICULA");
        }
        conn.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());                    
        }
        return mat;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jcb_Filtrado1_tipo = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtAlumno = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMensaje = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel4 = new javax.swing.JLabel();
        btnActualizar_Lista = new javax.swing.JButton();
        jcb_Filtro2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtID_Mensaje_Personal = new javax.swing.JTextField();
        btneditar_y_eliminar = new javax.swing.JButton();
        btncomprobar = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel13 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtNo_Matricula_Filtro = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Mensajes");

        jPanel1.setBackground(new java.awt.Color(255, 126, 59));
        jPanel1.setForeground(new java.awt.Color(204, 255, 255));

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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 18)); // NOI18N
        jLabel1.setText("Alumnos");

        jcb_Filtrado1_tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione ", "Por Nombre", "Por Matricula", " " }));

        jLabel2.setText("Filtrar por:");

        jLabel3.setText("Alumno:");

        txtAlumno.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        txtMensaje.setColumns(20);
        txtMensaje.setRows(5);
        jScrollPane2.setViewportView(txtMensaje);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(jTable2);

        jLabel4.setFont(new java.awt.Font("Sitka Heading", 1, 24)); // NOI18N
        jLabel4.setText("Control de los Mensajes");

        btnActualizar_Lista.setText("Actualizar Lista");
        btnActualizar_Lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizar_ListaActionPerformed(evt);
            }
        });

        jcb_Filtro2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Sin Filtro", "Por Matricula" }));

        jLabel5.setText("Filtrar por:");

        jLabel6.setText("ID Mensaje Personal:");

        btneditar_y_eliminar.setText("Deseas editar o eliminar un mensaje?");
        btneditar_y_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btneditar_y_eliminarActionPerformed(evt);
            }
        });

        btncomprobar.setText("Comprobar");
        btncomprobar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncomprobarActionPerformed(evt);
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

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/depositphotos_88824654-stock-illustration-male-mail-courier-icon.jpg"))); // NOI18N

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("Selecciona el Filtro por el que deseas que se muestren");

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("los mensajes.");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel13.setText("Filtro");

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Mensajes");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/user_ok.png"))); // NOI18N
        jButton1.setText("Seleccionar  Alumno");
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton1.setIconTextGap(2);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel15.setText("No Matricula:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(19, 19, 19)
                        .addComponent(jcb_Filtrado1_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 460, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jLabel14))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btneditar_y_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(txtID_Mensaje_Personal, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6))
                        .addGap(5, 5, 5)
                        .addComponent(btncomprobar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(1, 1, 1)
                        .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 428, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnNuevo)
                        .addGap(61, 61, 61)
                        .addComponent(btnRegistrar)
                        .addGap(61, 61, 61)
                        .addComponent(btnMostrar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(70, 70, 70)
                        .addComponent(btnEliminar)
                        .addGap(59, 59, 59)
                        .addComponent(btnSalir)))
                .addGap(10, 10, 10)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel11)
                            .addComponent(jLabel12))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel8))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 850, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 890, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(390, 390, 390)
                        .addComponent(jLabel13))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jLabel5)
                        .addGap(19, 19, 19)
                        .addComponent(jcb_Filtro2, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(jLabel15)
                        .addGap(7, 7, 7)
                        .addComponent(txtNo_Matricula_Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(260, 260, 260)
                        .addComponent(btnActualizar_Lista))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(9, 9, 9)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jcb_Filtrado1_tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jLabel14)
                        .addGap(23, 23, 23)
                        .addComponent(btneditar_y_eliminar)
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID_Mensaje_Personal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6)
                            .addComponent(btncomprobar))
                        .addGap(7, 7, 7)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(txtAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNuevo)
                            .addComponent(btnRegistrar)
                            .addComponent(btnMostrar))
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnEliminar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnSalir)))))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jLabel4)
                                .addGap(9, 9, 9)
                                .addComponent(jLabel11)
                                .addGap(5, 5, 5)
                                .addComponent(jLabel12))
                            .addComponent(jLabel8))
                        .addGap(22, 22, 22)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabel13)
                        .addGap(33, 33, 33)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jcb_Filtro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(txtNo_Matricula_Filtro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20)
                        .addComponent(btnActualizar_Lista))))
        );

        txtAlumno.getAccessibleContext().setAccessibleName("");

        jScrollPane4.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1013, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 769, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btneditar_y_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btneditar_y_eliminarActionPerformed
        // TODO add your handling code here:
        btneditar_y_eliminar.setEnabled(false);
        Desactivar_Botones();
        Desactivar_Campos();
        txtID_Mensaje_Personal.setEnabled(true);
        btnRegistrar.setEnabled(true);
        btnEliminar.setEnabled(true);
        btnCancelar.setEnabled(true);
        btncomprobar.setEnabled(true);
    }//GEN-LAST:event_btneditar_y_eliminarActionPerformed

    private void btncomprobarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncomprobarActionPerformed
        // TODO add your handling code here:

        //Comprueba que no existan campos vacios
        if(txtID_Mensaje_Personal.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingresado el ID del Ciclo Escolar");
        }
        else
        {
            editando=true;
            Activar_Campos();
            int idmensaje1=Integer.parseInt(txtID_Mensaje_Personal.getText());
            //Guarda en una arreglo de tipo cadena los valores obtenidos de la base de datos
            Object valores1[]=Comprobar_IDMensaje(idmensaje1);
            //Guarda el ID si esta en la base de datos.
            //Comprueba si no recibo informacion vacia o nula
            String auxAlumno=valores1[2].toString();
            mensaje=valores1[1].toString();
            if(mensaje.equals(""))
            {
                JOptionPane.showMessageDialog(null,"El ID de Registro es Incorrecto");
            }
            else
            {
                //Pasa la informacion del arreglo de Objetos al los campos.
                txtID_Mensaje_Personal.setText(""+idmensaje1);
                txtAlumno.setText(auxAlumno);
                txtMensaje.setText(mensaje);
                //Desactiva el boton de Edicion.
                btncomprobar.setEnabled(false);
                //Cambia al modo edicion.
                editando=true;
                //Activa el boton para Guardar los cammbios realizados
                btnRegistrar.setEnabled(true);
                idmensaje_mod=idmensaje1;
                //Agrega la informacion al jtable
                // md.addRow(Valores1);
            }
        }

    }//GEN-LAST:event_btncomprobarActionPerformed

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
                limpiartabla_2(md2);
                Consultar_Mensajes_Personales();

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
//                fechas=fecha.getFecha(jDateChooser1);
                //Actuliza un registro en especifico de acuerdo al parametro que le paso
                int aux_matricula=Comprobar_No_Matricula(txtAlumno.getText());
                Actualizar(idmensaje_mod,aux_matricula,mensaje);
                limpiartabla_2(md2);
                Consultar_Mensajes_Personales();
                //Desactiva los campos y botones
                Desactivar_Botones();
                Desactivar_Campos();
                //Limpia la informacion que se encuentra en los campos
                limpiar_Campos();
                //Activa el boton nuevo
                btneditar_y_eliminar.setEnabled(false);
                btnCancelar.setEnabled(false);
                btnNuevo.setEnabled(true);
               
                //Si no se encuentra mandara el siguiente mensaje

            }

        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:

        if(txtID_Mensaje_Personal.getText().equals(""))
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
        txtID_Mensaje_Personal.setEnabled(false);
        //Desactiva el boton Nuevo
        btnNuevo.setEnabled(false);
        btncomprobar.setEnabled(false);
        editando=false;
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jTable1.getSelectedRow()>-1)
        {
           int fila=jTable1.getSelectedRow();
           //Guardo la Matricula
           No_Matricula=Integer.parseInt(""+jTable1.getValueAt(fila, 0).toString());
           //Guardo y aplico el nombre al campo de texto
           Alumno=jTable1.getValueAt(fila, 1).toString();
          txtAlumno.setText(Alumno);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun alumno");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void btnActualizar_ListaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizar_ListaActionPerformed
        // TODO add your handling code here:
        //Filtra por el numero de matricula.
        int No_mat=Integer.parseInt(txtNo_Matricula_Filtro.getText());
        Consultar_Mensajes_Personales_Filtro_Por_Matricula(No_mat);
    }//GEN-LAST:event_btnActualizar_ListaActionPerformed

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
            java.util.logging.Logger.getLogger(Mensaje_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mensaje_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mensaje_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mensaje_Personal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mensaje_Personal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar_Lista;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btncomprobar;
    private javax.swing.JButton btneditar_y_eliminar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JComboBox<String> jcb_Filtrado1_tipo;
    private javax.swing.JComboBox<String> jcb_Filtro2;
    private javax.swing.JTextField txtAlumno;
    private javax.swing.JTextField txtID_Mensaje_Personal;
    private javax.swing.JTextArea txtMensaje;
    private javax.swing.JTextField txtNo_Matricula_Filtro;
    // End of variables declaration//GEN-END:variables
}
