/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registros;

import Clases1.Conexion;
import Clases1.Validar_Campo_de_Texto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Administrador
 */
public class Reg_Horarios extends javax.swing.JInternalFrame {

    //Atributos
    
    //Declaro los arreglos vacios porque se iran llenando
    boolean editando=false;
    int idRegistro_mod=0;
    //Retorno el indice necesario de los datos de todos los jcombobox
      Validar_Campo_de_Texto v=new Validar_Campo_de_Texto();
    DefaultTableModel md;
    String cabecera[]={"Ciclo Escolar","Maestro","Aula","Horario","Materia"};
    String datos[][];
    public Reg_Horarios() {
        initComponents();
//        txteditcamp1.setEnabled(false);
//        jcbEdit2.setEnabled(false);
        desactivarBotones();
        Desactivar_Campos();
        md=new DefaultTableModel(datos, cabecera);
        jTable1.setModel(md);
        //Rellena los JCombox con los registros previos de estos datos
        consultar_CicloEscolar();
        System.out.println("\n");
        consultar_Maestros();
        System.out.println("\n");
        consultar_Aulas();
        //Consultas(); modificar SQL
        consultar_Materias();
        v.SNumeros(txtID_Registro);
        Consultas();
      
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
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jcbAula = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jcb_Dia_Hora_uso = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jcbmaestro = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jcbMateria = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jcbCiclo_Escolar = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtID_Registro = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        btnEditar = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnRegistrar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnMostrar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Horarios");

        jScrollPane2.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane2.setBorder(null);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(100, 144, 219));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel7.setBackground(new java.awt.Color(255, 255, 255));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Aula:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 40, -1));

        jcbAula.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        jcbAula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbAulaActionPerformed(evt);
            }
        });
        jPanel2.add(jcbAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 120, -1));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID Registro", "Ciclo Escolar", "Aula", "Maestro", "Horario", "Materia"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 730, 130));

        jcb_Dia_Hora_uso.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione", "Lunes->10:30 am a 12:10 pm", "Lunes->12:10 pm a 1:00 pm", "Martes->8:50 am a 11:20 am", "Martes->1:00 pm a 1:50 pm", "Miercoles->10:30 am a 12:10 pm", "Jueves-> 9:40 am a 11:20 pm", "Jueves->11:20 am a 1:00 pm", "Jueves-> 1:00 pm a 1:50 pm", "Viernes->11:20 am a 12:10 pm", "Viernes->12:10 a 1:50 pm" }));
        jPanel2.add(jcb_Dia_Hora_uso, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 60, 190, -1));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Seleccione al Maestro:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 140, -1));

        jcbmaestro.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        jPanel2.add(jcbmaestro, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 210, -1));
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 94, -1, -1));

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Horario:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, 60, -1));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Materia:");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 90, 70, -1));

        jcbMateria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione" }));
        jPanel2.add(jcbMateria, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 90, 200, -1));

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Ciclo Escolar:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 90, -1));

        jcbCiclo_Escolar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel2.add(jcbCiclo_Escolar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 60, 100, -1));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("ID Registro:");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 100, -1));

        txtID_Registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtID_RegistroActionPerformed(evt);
            }
        });
        jPanel2.add(txtID_Registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 100, -1));
        jPanel2.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 770, 20));

        btnEditar.setText("Editar");
        btnEditar.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 20, 120, -1));

        jPanel3.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 30, 800, 310));

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
        jPanel3.add(btnNuevo, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 360, -1, -1));

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
        jPanel3.add(btnRegistrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 360, -1, -1));

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
        jPanel3.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 360, -1, -1));

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
        jPanel3.add(btnMostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 360, -1, -1));

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
        jPanel3.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 440, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel3.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 440, 100, 60));

        jScrollPane2.setViewportView(jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 915, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
  
    
    public void Consultas()
    {
     //Actualiza el modelo de la tabla para mostrar el ID de Registro
     //Se hace esto ya que el ID de Registro se asigna automaticamente por
     //La base de datos.
    
    DefaultTableModel ms = null;
    String cabecera[]={"ID Registro","Ciclo Escolar","Maestro","Aula","Horario","Materia"};
    String datos[][] = null;  
    ms=new DefaultTableModel(datos, cabecera);
    //Se aplica el nuevo modelo de tabla
    jTable1.setModel(ms);       
        try
        {
            limpiartabla(ms);
        String consultar="SELECT a.ID_Registro,b.Ciclo_Escolarcol,"
        + "a.Aula,a.Maestro,a.Dia_Hora_Uso,a.Materia FROM horario_separado a,"
        + " ciclo_escolar b where a.id_Ciclo_Escolar=b.id_Ciclo_Escolar;";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        Object info2[]=new Object[6];
        while(rs.next())
        {
                info2[0]=rs.getInt("ID_Registro");
                info2[1]=rs.getString("Ciclo_Escolarcol");
                info2[2]=rs.getString("Aula");
                info2[3]=rs.getString("Maestro");
                info2[4]=rs.getString("Dia_Hora_Uso");
                info2[5]=rs.getString("Materia");
                ms.addRow(info2);
        }
        conn.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());           
        }
    }
    
    public void consultar_Aulas()
    {
      
        try
        {
           
        String consultar="SELECT * FROM xorox.aulas;";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        
        while(rs.next())
        {
           jcbAula.addItem(rs.getString("Aula"));        
        }
        conn.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());           
        }
    }
    
    
    public void consultar_CicloEscolar()
    {
        int i=1;
             try
        {
            
        String consultar="SELECT * FROM xorox.ciclo_escolar;";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
      
        while(rs.next())
        { 
          //Obtiene las llaves primarias ya regristradas como foraneas.
         jcbCiclo_Escolar.addItem(rs.getString("Ciclo_Escolarcol"));
        }
        conn.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());           
        }
    }
    
    public void consultar_Maestros()
    { 
       
         try
        {
          
        String consultar="SELECT * FROM xorox.maestros;";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        while(rs.next())
        {
            //Obtiene las llaves primarias ya regristradas como foraneas.
       jcbmaestro.addItem(rs.getString("Maestro"));
        }
        conn.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
                    
        }
    }
    
       public void consultar_Materias()
    { 
       
         try
        {
          
        String consultar="SELECT * FROM xorox.materias;";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        while(rs.next())
        {
            //Obtiene las llaves primarias ya regristradas como foraneas.
       jcbMateria.addItem(rs.getString("Materia"));
        }
        conn.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
                    
        }
    }
    
        
  public boolean Insertar(int idCiclo)
  {
      boolean exito=false;
      String CicloEscolar=jcbCiclo_Escolar.getSelectedItem().toString();//Obtiene el nombre del Ciclo Escolar Seleccionado
       //Comprueba el indice del maestro seleccionado
      String Maestro=jcbmaestro.getSelectedItem().toString();
      String Aula=jcbAula.getSelectedItem().toString();
      String dia_hor_uso=jcb_Dia_Hora_uso.getSelectedItem().toString();
      String Materia=jcbmaestro.getSelectedItem().toString();
       //Comprueba el indice del Aula escolar seleccionada                             
       //Recibe los tres parametros y los convierte a String
      Object info[]={CicloEscolar,Aula,Maestro,dia_hor_uso}; 
     
      try
            {
         Conexion miconexion=new Conexion();
         Connection conn= miconexion.getConnection();         
         String insertar="INSERT INTO `xorox`.`horario_separado` "
         + "(`ID_Ciclo_Escolar`, `Aula`, `Maestro`, `Dia_Hora_Uso`, `Materia`)"
         + " VALUES (?,?,?,?,?);";
         PreparedStatement pst=conn.prepareStatement(insertar);
         
       //Comprueba el indice del ciclo escolar seleccionado
       //Inserccion de los valores correspondientes en la base de datos       
       pst.setInt(1, idCiclo);
       pst.setString(2, Aula);
       pst.setString(3, Maestro);
       pst.setString(4, dia_hor_uso);
       pst.setString(5, Materia);
       pst.executeUpdate();  
       md.addRow(info);
       exito=true;
       btnRegistrar.setEnabled(true);
      JOptionPane.showMessageDialog(null, "Registro Exitoso");
            }catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null, e.getMessage());
            }
      return exito;
  }
 
    public void desactivarBotones()
    {
        btnRegistrar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);        
    }
 
     public Object[] Comprobar_Id_Registro(int d)
    {
       Object info2[]=new Object[6];
        try
        {
        String consultar="SELECT a.ID_Registro,b.Ciclo_Escolarcol,a.Aula,"
        + "a.Maestro,a.Dia_Hora_Uso,a.Materia FROM horario_separado a, "
        + "ciclo_escolar b where a.id_Ciclo_Escolar=b.id_Ciclo_Escolar and ID_Registro="+d+";";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        
        while(rs.next())
        {
         info2[0]=rs.getInt("ID_Registro");
         info2[1]=rs.getString("Ciclo_Escolarcol");
         info2[2]=rs.getString("Aula");
         info2[3]=rs.getString("Maestro");
         info2[4]=rs.getString("Dia_Hora_Uso");
         info2[5]=rs.getString("Materia");
        }
        conn.close();
        btnEliminar.setEnabled(true);
        btnEditar.setEnabled(true);
        btnCancelar.setEnabled(true);
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());                    
        }
        return info2;
    }        
       
    public void Actualizar(int idRegistro,int idCicloEsc)
    {
      
        try
      {
   
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        //Segun la columna que haya seleccionado sera el valor que se modique
      
       
       //Comprueba el indice del maestro seleccionado
         
        String Ciclo_Esc=jcbCiclo_Escolar.getSelectedItem().toString();
        String Maestro=jcbmaestro.getSelectedItem().toString();
        String Aula=jcbAula.getSelectedItem().toString();
        String dia_hor_uso=jcb_Dia_Hora_uso.getSelectedItem().toString();
        String Materia=jcbmaestro.getSelectedItem().toString();
       //Comprueba el indice del Aula escolar seleccionada                             
       //Recibe los tres parametros y los convierte a String
      Object info[]={idCicloEsc,Aula,Maestro,dia_hor_uso}; 
        String actualizar="UPDATE `xorox`.`horario_separado` "
        + "SET `ID_Ciclo_Escolar`=?, `Aula`=?, "
        + "`Dia_Hora_Uso`=?, `Materia`=? "
        + "WHERE `ID_Registro`=?;";
        PreparedStatement pst=conn.prepareStatement(actualizar);
        pst.setInt(1, idCicloEsc);
        pst.setString(2, Aula);
        pst.setString(3, dia_hor_uso);
        pst.setString(4, Materia);
        pst.setInt(5, idRegistro);
        pst.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cambio realizado con exito");
        conn.close();
      }catch(SQLException e)
      {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }  
    } 
     
    public void Activar_Botones()
    {
       btnRegistrar.setEnabled(true);
       btnEditar.setEnabled(true);
       btnEliminar.setEnabled(true);
       btnMostrar.setEnabled(true); 
       btnCancelar.setEnabled(true);
    }
    
    public void Desactivar_Botones()
    {
        btnRegistrar.setEnabled(false);
        btnEditar.setEnabled(false);
        btnEliminar.setEnabled(false);
        btnMostrar.setEnabled(false); 
        btnCancelar.setEnabled(false);
    }
    
    public void Activar_Campos()
    {
        jcbAula.setEnabled(true);
        jcb_Dia_Hora_uso.setEnabled(true);
        jcbCiclo_Escolar.setEnabled(true);
        jcbmaestro.setEnabled(true);
        jcbMateria.setEnabled(true);
        txtID_Registro.setEnabled(true);
    }
    
    public void Desactivar_Campos()
    {
        jcbAula.setEnabled(false);
        jcb_Dia_Hora_uso.setEnabled(false);
        jcbCiclo_Escolar.setEnabled(false);
        jcbmaestro.setEnabled(false);
        jcbMateria.setEnabled(false);       
        txtID_Registro.setEnabled(false);
    }
    
    public int Obtener_ID_Ciclo_Esc(String Ciclo_Esc)
    {
        int id_ciclo_esc=0;
        try
        {
           
        String consultar="SELECT id_Ciclo_Escolar FROM xorox.ciclo_escolar where Ciclo_Escolarcol='"+Ciclo_Esc+"';";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        while(rs.next())
        {
                id_ciclo_esc=rs.getInt("ID_Ciclo_Escolar");
        }
        conn.close();
        }catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());           
        }
        return id_ciclo_esc;
    }
    
    public void limpiar_Campos()
    {
           jcbAula.setSelectedIndex(0);
           jcbCiclo_Escolar.setSelectedItem(0);
           jcbMateria.setSelectedIndex(0);
           jcb_Dia_Hora_uso.setSelectedIndex(0);
           jcbmaestro.setSelectedIndex(0);
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
        int idRegistro=Integer.parseInt(txtID_Registro.getText());      
        String eliminar="DELETE FROM `xorox`.`horario_separado` WHERE `ID_Registro`=?;";
        PreparedStatement pst=conn.prepareStatement(eliminar);  
        pst.setInt(1, idRegistro);
        pst.execute();
        exito=true;
        JOptionPane.showMessageDialog(null, "Registro Eliminado");
        conn.close();  
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
    
    private void jcbAulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbAulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jcbAulaActionPerformed

    private void txtID_RegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtID_RegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtID_RegistroActionPerformed

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
        if(jcbAula.getSelectedItem().toString().equals("Seleccione") || 
           jcbCiclo_Escolar.getSelectedItem().toString().equals("")  ||
           jcbMateria.getSelectedItem().toString().equals("Seleccione") ||
           jcb_Dia_Hora_uso.getSelectedItem().toString().equals("Seleccione") ||
           jcbmaestro.getSelectedItem().toString().equals("Seleccione"))
        {
            JOptionPane.showMessageDialog(null, "Hay sin informacion seleccionada");
        }else
        {
            String Ciclo_Esc=jcbCiclo_Escolar.getSelectedItem().toString();
            int idCiclo_Esc=Obtener_ID_Ciclo_Esc(Ciclo_Esc);
            //confirma si se encuentra en modo de edicion
            if(editando==false)
            {
               
                exito=Insertar(idCiclo_Esc);//Confirma que se realizo la Inserccion correctamente
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
                btnEditar.setEnabled(true);
                btnCancelar.setEnabled(false);
                //Variable auxiliar para el ID del Maestro
                int id_registro=Integer.parseInt(txtID_Registro.getText());
                Object valores[]=Comprobar_Id_Registro(id_registro);
                id_registro=Integer.parseInt(valores[0].toString());
               
                //Si esta se encuentra en la base de datos
                if(id_registro!=0 && idCiclo_Esc!=0)
                {
                    //Actuliza un registro en especifico de acuerdo al parametro que le paso
                    Actualizar(id_registro,idCiclo_Esc);
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
                    Consultas();
                    //Desactiva los campos y botones
                    Desactivar_Botones();
                    Desactivar_Botones();
                    //Limpia la informacion que se encuentra en los campos
                    limpiar_Campos();
                    //Activa el boton nuevo
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
        
        if(txtID_Registro.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingreso el ID del Registro");
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
        Consultas();
        btnCancelar.setEnabled(false);
    }//GEN-LAST:event_btnMostrarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        //Desactiva el boton de Cancelar
        JOptionPane.showMessageDialog(null, "Funciona");
        btnCancelar.setEnabled(false);
        //Desactiva los campos y los botones
        Desactivar_Botones();
        Desactivar_Campos();
        limpiar_Campos();
        //Activa el Boton de Nuevo
        btnNuevo.setEnabled(true);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:

        //Comprueba que no existan campos vacios
        if(txtID_Registro.getText().equals(""))
        {
            JOptionPane.showMessageDialog(null, "No ha ingresado el ID de Registro");
        }
        else
        {
            int id_registro_mod=Integer.parseInt(txtID_Registro.getText());
       
            //Guarda en una arreglo de tipo cadena los valores obtenidos de la base de datos
            Object Valores[]=Comprobar_Id_Registro(id_registro_mod);
            id_registro_mod=Integer.parseInt(Valores[0].toString());
            JOptionPane.showMessageDialog(null, id_registro_mod);
            //Comprueba si no recibo informacion vacia o nula
            if(id_registro_mod==0)
            {
                JOptionPane.showMessageDialog(null,"El ID de Registro es Incorrecto");
            }
            else
            {
                //Pasa la informacion del arreglo de Objetos al los campos.
                txtID_Registro.setText(Valores[0].toString());
                jcbCiclo_Escolar.setSelectedItem(Valores[1].toString());
                jcbAula.setSelectedItem(Valores[2].toString());
                jcbmaestro.setSelectedItem(Valores[3].toString());
                jcb_Dia_Hora_uso.setSelectedItem(Valores[4]);
                JOptionPane.showMessageDialog(null, Valores[5]);
                jcbMateria.setSelectedItem(Valores[5].toString());                         
                //Desactiva el boton de Edicion.
                btnEditar.setEnabled(false);
                //Cambia al modo edicion.
                editando=true;
                //Activa el boton para Guardar los cammbios realizados
                btnRegistrar.setEnabled(true);
                //Agrega la informacion al jtable
                md.addRow(Valores);
            }
        }
    }//GEN-LAST:event_btnEditarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnMostrar;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox jcbAula;
    private javax.swing.JComboBox<String> jcbCiclo_Escolar;
    private javax.swing.JComboBox jcbMateria;
    private javax.swing.JComboBox jcb_Dia_Hora_uso;
    private javax.swing.JComboBox jcbmaestro;
    private javax.swing.JTextField txtID_Registro;
    // End of variables declaration//GEN-END:variables
}
