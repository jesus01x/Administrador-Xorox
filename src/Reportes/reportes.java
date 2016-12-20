/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Reportes;

import Clases1.Conexion;
import Clases1.Obtener_Fecha;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Administrador
 */
public class reportes extends javax.swing.JInternalFrame {

    /**
     * Creates new form reportes
     */
   
    Connection conn;
    Obtener_Fecha fecha = new Obtener_Fecha();
    String Carrera="";
    String Aula="";
    
    //Control de los reportes de Uso del Equipo por carrera
    boolean modo_tiempo = false;//Verifica si se ha elejido un tiempo en especifico
    boolean modo_mes = false;//Define el periodo del reporte de acuerdo al mes y año elegidos
    boolean modo_libre = false;//Define el rango de tiempo que el usuario haya seleccionado y en base a ello obtener el reporte
    boolean modo_general = false; //Define que no hay tiempo definido por lo que leera toda la informacion que se encuentre en la base de datos

    //Control de los reportes de Uso del equipo por aula
    boolean modo_tiempo2 = false;//Verifica si se ha elejido un tiempo en especifico
    boolean modo_mes2 = false;//Define el periodo del reporte de acuerdo al mes y año elegidos
    boolean modo_libre2 = false;//Define el rango de tiempo que el usuario haya seleccionado y en base a ello obtener el reporte
    boolean modo_general2 = false;//Define que no hay tiempo definido por lo que leera toda la informacion que se encuentre en la base de datos

    
    
    public reportes() {
        initComponents();
        jScrollPane1.setOpaque(false);
        jcb_Carrera.setEnabled(false);
        jcbAula.setEnabled(false);
        
        //Action Listener para seleccionar la carrera por la que se realizara el reporte
        jcb_rep_uso_por_Carreras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcb_rep_uso_por_Carreras.getSelectedIndex()==0)
                {
                  //Desactiva el combox de las carreras
                     jcb_Carrera.setEnabled(false);  
                  //Desactiva la seleccion de un tiempo debido a que no ha seleccionado
                 //ningun reporte
                 jcb_modo_por_tiempo.setEnabled(false);
                }
                if(jcb_rep_uso_por_Carreras.getSelectedIndex()==1)
                {
                    //Activa el combox de las carreras
                     jcb_Carrera.setEnabled(true);
                     //Desactiva la seleccion de un tiempo debido a que no ha seleccionado
                     //ningun reporte
                     jcb_modo_por_tiempo.setEnabled(true);
                }
                if(jcb_rep_uso_por_Carreras.getSelectedIndex()==2)
                {
                    //Desactiva el combox de las carreras
                     jcb_Carrera.setEnabled(false);
                     //Desactiva la seleccion de un tiempo debido a que no ha seleccionado
                     //ningun reporte
                     jcb_modo_por_tiempo.setEnabled(true);
                }
            }
        });
        
        //Action Lister para los reportes de uso por Aula
        jcb_rep_uso_por_Aulas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            if(jcb_rep_uso_por_Aulas.getSelectedIndex()==0)
                {
                  //Desactiva el combox de las carreras
                     jcbAula.setEnabled(false);  
                  //Desactiva la seleccion de un tiempo debido a que no ha seleccionado
                 //ningun reporte
                 jcb_modo_tiempo1.setEnabled(false);
                }
                if(jcb_rep_uso_por_Aulas.getSelectedIndex()==1)
                {
                    //Activa el combox de las carreras
                     jcbAula.setEnabled(true);
                     //Desactiva la seleccion de un tiempo debido a que no ha seleccionado
                     //ningun reporte
                     jcb_modo_tiempo1.setEnabled(true);
                }
                if(jcb_rep_uso_por_Aulas.getSelectedIndex()==2)
                {
                    //Desactiva el combox de las carreras
                     jcbAula.setEnabled(false);
                     //Desactiva la seleccion de un tiempo debido a que no ha seleccionado
                     //ningun reporte
                     jcb_modo_tiempo1.setEnabled(true);
                }
                
            }
        });
        
        //Action Listener de tiempo del reporte por Carrera
        jcb_modo_por_tiempo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jcb_modo_por_tiempo.getSelectedIndex() == 0) {
                    //Desactiva el combox de las carreras
                    jcb_Carrera.setEnabled(false);
                    //Desactiva todos los rangos  
                    jcb_mes1.setEnabled(false);
                    jcb_año1.setEnabled(false);
                    jDateChooser4.setEnabled(false);
                    jDateChooser5.setEnabled(false);
                    modo_tiempo = false;
                    modo_mes = false;
                    modo_libre = false;
                    modo_general = false;
                 
                }
                if (jcb_modo_por_tiempo.getSelectedIndex() == 1) {
                    //Activa el combox de las carreras
                    jcb_Carrera.setEnabled(false);
                    //Desactiva los rangos  
                    jcb_mes1.setEnabled(false);
                    jcb_año1.setEnabled(false);
                    jDateChooser4.setEnabled(false);
                    jDateChooser5.setEnabled(false);
                    modo_tiempo = true;
                    modo_general = true;
                    modo_mes = false;
                    modo_libre = false;

                }
                if (jcb_modo_por_tiempo.getSelectedIndex() == 2) {
                    //Desactiva el combox de las carreras
                    jcb_Carrera.setEnabled(false);
                    //Activa los rangos por mes 
                    jcb_mes1.setEnabled(true);
                    jcb_año1.setEnabled(true);

                    //Desactiva los rangos libres
                    jDateChooser4.setEnabled(false);
                    jDateChooser5.setEnabled(false);
                    modo_tiempo = true;
                    modo_mes = true;
                    modo_libre = false;
                    modo_general = false;
                }
                if (jcb_modo_por_tiempo.getSelectedIndex() == 3) {
                   //Desactiva el combox de las carreras
                    jcb_Carrera.setEnabled(false);
                    //Activa los ranglos libres  
                    jDateChooser4.setEnabled(true);
                    jDateChooser5.setEnabled(true);

                    //Desactiva los rangos por mes
                    jcb_mes1.setEnabled(false);
                    jcb_año1.setEnabled(false);
                    modo_tiempo = true;
                    modo_libre = true;
                    modo_mes = false;
                    modo_general = false;
                }
            }
        });
        
         //Action Listener de tiempo del reporte por Aula   
        jcb_modo_tiempo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jcb_modo_tiempo1.getSelectedIndex() == 0) {
                    //Desactiva el combox de las carreras
                     jcbAula.setEnabled(false);
                    //Desactiva todos los rangos  
                    jcb_mes.setEnabled(false);
                    jcb_año.setEnabled(false);
                    jDateChooser1.setEnabled(false);
                    jDateChooser2.setEnabled(false);
                    modo_tiempo2 = false;
                    modo_mes2 = false;
                    modo_libre2 = false;
                    modo_general2 = false;
                }
                if (jcb_modo_tiempo1.getSelectedIndex() == 1) {
                    //Desactiva el combox de las Aulas
                     jcbAula.setEnabled(false);
                    //Desactiva los rangos por mes  
                    jcb_mes.setEnabled(false);
                    jcb_año.setEnabled(false);
                    //desactiva los rangos libres
                    jDateChooser1.setEnabled(false);
                    jDateChooser2.setEnabled(false);
                    //Se activan los tiempo a modo general
                    modo_tiempo2 = true;
                    modo_general2 = true;
                    modo_mes2 = false;
                    modo_libre2 = false;

                }
                if (jcb_modo_tiempo1.getSelectedIndex() == 2) {
                    //Desactiva el combox de las carreras
                    jcbAula.setEnabled(false);
                    
                    //Activa los rangos por mes 
                    jcb_mes.setEnabled(true);
                    jcb_año.setEnabled(true);
                    
                    //Desactiva los rangos libres
                    jDateChooser2.setEnabled(false);
                    jDateChooser1.setEnabled(false);
                    //activa el modo tiempo por mes
                    modo_tiempo = true;
                    modo_mes2 = true;
                    modo_libre2 = false;
                    modo_general2 = false;
                }
                if (jcb_modo_tiempo1.getSelectedIndex() == 3) {
                    //Desactiva el combox de las carreras
                    jcbAula.setEnabled(false);                   
                    
                    //Desactiva el combox de las carreras
                    jcb_Carrera.setEnabled(false);
                    
                    //Activa los ranglos libres  
                    jDateChooser2.setEnabled(true);
                    jDateChooser1.setEnabled(true);

                    //Desactiva los rangos por mes
                    jcb_mes.setEnabled(false);
                    jcb_año.setEnabled(false);
                    //activa el modo tiempo por rango libre
                    modo_tiempo2 = true;
                    modo_libre2 = true;
                    modo_mes2 = false;
                    modo_general2 = false;
                }
            }
        });
        
        //Carga las Carreras registradas en la base de datos
        Consultar_Carreras();
        Consultar_Aulas();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void Consultar_Carreras()
    {
        try
        {
        //limpia el jcombox y lo vuelve a llenar
        jcb_Carrera.removeAllItems();
        jcb_Carrera.addItem("Seleccione");
        String consultar="SELECT * FROM xorox.carreras;";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consultar);
        while(rs.next())
        {
          jcb_Carrera.addItem(rs.getString("Carrera"));
        }
        conn.close();
        }catch(SQLException ez)
        {
        JOptionPane.showMessageDialog(null, ez.getMessage());
                    
        }
    }
    
    public void Consultar_Aulas()
    {
        try
        {
        //limpia el jcombox y lo vuelve a llenar
        jcbAula.removeAllItems();
        jcbAula.addItem("Seleccione");
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
        }catch(SQLException ez)
        {
        JOptionPane.showMessageDialog(null, ez.getMessage());
                    
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jcb_rep_uso_por_Carreras = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jcb_modo_por_tiempo = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jcb_mes1 = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jcb_año1 = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jDateChooser4 = new com.toedter.calendar.JDateChooser();
        jDateChooser5 = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jcb_rep_uso_por_Aulas = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jcb_modo_tiempo1 = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jcb_mes = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jcb_año = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jDateChooser2 = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jcb_Carrera = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jcbAula = new javax.swing.JComboBox<>();
        jSeparator1 = new javax.swing.JSeparator();

        setTitle("Reporte de Uso del Equipo");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setForeground(new java.awt.Color(180, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setText("Elija el Reporte:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 380, -1, -1));

        jcb_rep_uso_por_Carreras.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Uso del Equipo por Carrera", "Concentrado del uso del Equipo por las Carreras" }));
        jPanel1.add(jcb_rep_uso_por_Carreras, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 380, 250, -1));

        jLabel17.setText("Tiempo:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 440, -1, -1));

        jcb_modo_por_tiempo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Sin tiempo", "Por Mes", "Rango Libre" }));
        jPanel1.add(jcb_modo_por_tiempo, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 300, -1));

        jLabel18.setText("Rango por mes:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, -1, -1));

        jLabel19.setText("Mes:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 480, -1, -1));

        jcb_mes1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre" }));
        jPanel1.add(jcb_mes1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 480, -1, -1));

        jLabel20.setText("Año:");
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 480, -1, -1));

        jcb_año1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        jPanel1.add(jcb_año1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 480, -1, -1));

        jLabel21.setText("Rango Libre:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 520, -1, -1));

        jButton1.setText("Visualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 350, -1));

        jLabel22.setText("Desde:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, -1, 20));

        jLabel23.setText("Hasta");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 550, -1, -1));

        jDateChooser4.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 550, 124, -1));

        jDateChooser5.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser5, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 550, 134, -1));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/estudiantes_214533102.png"))); // NOI18N
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 240, 280));

        jLabel9.setText("Elija el Reporte:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 390, -1, -1));

        jcb_rep_uso_por_Aulas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Uso del Equipo por Aula", "Concentrado del Uso del Equipo por Aula" }));
        jPanel1.add(jcb_rep_uso_por_Aulas, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 390, 307, -1));

        jLabel10.setText("Tiempo:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 450, -1, -1));

        jcb_modo_tiempo1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Sin tiempo", "Por Mes", "Rango Libre" }));
        jPanel1.add(jcb_modo_tiempo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 450, 300, -1));

        jLabel11.setText("Rango por mes:");
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 480, -1, -1));

        jLabel12.setText("Mes:");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, -1, -1));

        jcb_mes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Noviembre", "Diciembre" }));
        jPanel1.add(jcb_mes, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, -1, -1));

        jLabel13.setText("Año:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 480, -1, -1));

        jcb_año.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026", "2027", "2028", "2029", "2030" }));
        jPanel1.add(jcb_año, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 480, -1, -1));

        jLabel14.setText("Rango Libre:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 520, -1, -1));

        jLabel15.setText("Desde:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 550, -1, 20));

        jLabel16.setText("Hasta");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 550, -1, -1));

        jButton4.setText("Visualizar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 590, 390, -1));

        jDateChooser1.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 550, 130, -1));

        jDateChooser2.setDateFormatString("dd/MM/yyyy");
        jPanel1.add(jDateChooser2, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 550, 140, -1));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/centroc.png"))); // NOI18N
        jLabel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 100, 340, -1));

        jLabel3.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        jLabel3.setText("Uso del Equipo por Aula");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Microsoft JhengHei UI", 1, 14)); // NOI18N
        jLabel4.setText("Uso del Equipo por Carrera");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        jLabel6.setText("Seleccione la Carrera:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 410, -1, -1));

        jcb_Carrera.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel1.add(jcb_Carrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 150, -1));

        jLabel7.setText("Aula:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 420, -1, -1));

        jcbAula.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        jPanel1.add(jcbAula, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 420, 200, -1));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 20, 20, 770));

        jScrollPane1.setViewportView(jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 985, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public String Obtener_Acumulado_de_Horas_por_Carrera(int seleccionado,String Carrera)
    {
          String horas="";
        switch(seleccionado)
        {
            case 0:
              //No hara nada debido a que no ha seleccionado un tiempo definido 
              //por lo que el programa le dara aviso que seleccione un tiempo.
                break;
            
            case 1://En caso de que haya seleccionado un mes en especifico
         String mes=jcb_mes1.getSelectedItem().toString();
         String año=jcb_año1.getSelectedItem().toString();
         
       //Me regresa la siguiente cadena---> año, numero del mes, dia limite del mes 
             Object info[] = Obtener_fechas(mes, año);
       
              //Fecha de empiezo
              String fecha1=info[0]+"/"+info[1]+"/01";
              //Fecha del final
              String fecha2=info[0]+"/"+info[1]+"/"+info[2];                                
        try
        {
        String consulta="SELECT sec_to_time(sum(time_to_sec(a.Duracion))) "
        + "FROM uso_del_equipo a, alumnos b where a.NO_MATRICULA=b.NO_MATRICULA"
        + " and b.Carrera='"+Carrera+"' and str_to_date(Fecha, '%d/%m/%Y')"
        + " between cast('"+fecha1+"' as date) and cast('"+fecha2+"' as date);";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consulta);
      
        while(rs.next())
        {
          horas=rs.getString("Duracion");
        }
        conn.close();
        }catch(SQLException ez)
        {
        JOptionPane.showMessageDialog(null, ez.getMessage());
                    
        }
        break;
         
          case 2://En caso de que haya seleccionado un rango en especifo
                
          //Obtengo el valor de las fechas seleccionadas    
          String fecha1b=fecha.getFecha(jDateChooser4);
          String fecha2b=fecha.getFecha(jDateChooser5);
          
          //Separo sus valores en base a las diagonales
          String fecha1b_a[]=fecha1b.split("/");
          String fecha2b_a[]=fecha2b.split("/");
          
          //Reordeno la fecha para realizar la consulta
          //En un arreglo de tipo cadena con el siguiente orden
          //  año, numero del mes, dia limite de ese mes
          fecha1b=fecha1b_a[2]+"/"+fecha1b_a[1]+"/01";
          fecha2b=fecha2b_a[2]+"/"+fecha2b_a[1]+"/"+fecha2b_a[0];
          
          try
        {
        String consulta="SELECT sec_to_time(sum(time_to_sec(a.Duracion))) "
        + "FROM uso_del_equipo a, alumnos b where a.NO_MATRICULA=b.NO_MATRICULA "
        + "and b.Carrera='"+Carrera+"' and str_to_date(Fecha, '%d/%m/%Y') "
        + "between cast('"+fecha1b+"' as date) and cast('"+fecha2b+"' as date);";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consulta);      
        while(rs.next())
        {
          horas=rs.getString("Duracion");
        }
        conn.close();
        }catch(SQLException ez)
        {
        JOptionPane.showMessageDialog(null, ez.getMessage());
                    
        }
            break;
        }
//        String consulta="SELECT sec_to_time(sum(time_to_sec(a.Duracion))) "
//        + "FROM uso_del_equipo a, alumnos b where str_to_date(Fecha, '%d/%m/%Y') "
//        + "between cast('2016/02/01' as date) and cast('2016/02/28' as date)"
//        + " and b.Carrera='LSCA';";
        return horas;
    }
    
    public String Obtener_Acumulado_de_Horas_por_Aula(int seleccionado,String Aula)
    {
          String horas="";
        switch(seleccionado)
        {
            case 0:
              //No hara nada debido a que no ha seleccionado un tiempo definido 
              //por lo que el programa le dara aviso que seleccione un tiempo.
                break;
            
            case 1://En caso de que haya seleccionado un mes en especifico
         String mes=jcb_mes1.getSelectedItem().toString();
         String año=jcb_año1.getSelectedItem().toString();
         
       //Me regresa la siguiente cadena---> año, numero del mes, dia limite del mes 
             Object info[] = Obtener_fechas(mes, año);
       
              //Fecha de empiezo
              String fecha1=info[0]+"/"+info[1]+"/01";
              //Fecha del final
              String fecha2=info[0]+"/"+info[1]+"/"+info[2];                                
        try
        {
        String consulta="SELECT sec_to_time(sum(time_to_sec(a.Duracion))) "
        + "FROM uso_del_equipo a, alumnos b where a.NO_MATRICULA=b.NO_MATRICULA "
        + "str_to_date(Fecha, '%d/%m/%Y')between cast('"+fecha1+"' as date) "
        + "and cast('"+fecha2+"' as date)and a.Aula='"+Aula+"';";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consulta);      
        while(rs.next())
        {
          horas=rs.getString("Duracion");
        }
        conn.close();
        }catch(SQLException ez)
        {
        JOptionPane.showMessageDialog(null, ez.getMessage());                    
        }
        break;
         
          case 2://En caso de que haya seleccionado un rango en especifo
                
          //Obtengo el valor de las fechas seleccionadas    
          String fecha1b=fecha.getFecha(jDateChooser4);
          String fecha2b=fecha.getFecha(jDateChooser5);
          
          //Separo sus valores en base a las diagonales
          String fecha1b_a[]=fecha1b.split("/");
          String fecha2b_a[]=fecha2b.split("/");
          
          //Reordeno la fecha para realizar la consulta
          //En un arreglo de tipo cadena con el siguiente orden
          //  año, numero del mes, dia limite de ese mes
          fecha1b=fecha1b_a[2]+"/"+fecha1b_a[1]+"/01";
          fecha2b=fecha2b_a[2]+"/"+fecha2b_a[1]+"/"+fecha2b_a[0];
          
          try
        {
        String consulta="SELECT sec_to_time(sum(time_to_sec(a.Duracion))) "
        + "FROM uso_del_equipo a, alumnos b where a.NO_MATRICULA=b.NO_MATRICULA "
        + "str_to_date(Fecha, '%d/%m/%Y')between cast('"+fecha1b+"' as date) "
        + "and cast('"+fecha2b+"' as date)and a.Aula='"+Aula+"';";
        Conexion miconexion=new Conexion();
        Connection conn= miconexion.getConnection();
        Statement pst2=conn.createStatement();
        ResultSet rs=pst2.executeQuery(consulta);      
        while(rs.next())
        {
          horas=rs.getString("Duracion");
        }
        conn.close();
        }catch(SQLException ez)
        {
        JOptionPane.showMessageDialog(null, ez.getMessage());
                    
        }
            break;
        }
//        String consulta="SELECT sec_to_time(sum(time_to_sec(a.Duracion))) "
//        + "FROM uso_del_equipo a, alumnos b where str_to_date(Fecha, '%d/%m/%Y') "
//        + "between cast('2016/02/01' as date) and cast('2016/02/28' as date)"
//        + " and b.Carrera='LSCA';";
        return horas;
    }
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int elegido = jcb_rep_uso_por_Carreras.getSelectedIndex();

        //Reportes por Carrera
        if (modo_tiempo) {
            //Si el usuario selecciono un tiempo  ahora solo preguntara en cual debe caer
            //Si mensual, por rango, o no definido
            if (modo_general) {
           //Reporte sin tiempo definido
                try {
                    //Reporte Acuumulado
                    Reporte_Elegido_Por_Carrera(elegido);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

            }
            if (modo_mes) {

                try {
                    //Reporte por mes
                    Reporte_Elegido_Por_Carrera_Tiempo_Mes(elegido);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            if (modo_libre) {

                try {
                    //Reporte con Rango Libre
                    Reporte_Elegido_Por_Carrera_Tiempo_Libre(elegido);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else {
            //En caso de no hacer ningun pase de parametros
            //        Reporte_Elegido_Prestamos(elegido);
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun modo de tiempo");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        int elegido = jcb_rep_uso_por_Aulas.getSelectedIndex();

        if (modo_tiempo2) {
            if (modo_general2) {
                try {
                    //Reporte Acuumulado
                    Reporte_Elegido_Por_Aula(elegido);
                } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            if (modo_mes2) {
                try {
                    //Reporte por mes
                     Reporte_Elegido_Por_Aula_Tiempo_Mes(elegido);
                } catch (Exception ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
            if (modo_libre2) {
                try {
                    //Reporte con Rango Libre
                   Reporte_Elegido_Por_Aula_Tiempo_Libre(elegido);
                } catch (Exception ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        } else {
            //En caso de no hacer ningun pase de parametros
            //        Reporte_Elegido_Prestamos(elegido);
            JOptionPane.showMessageDialog(null, "No ha seleccionado ningun modo de tiempo");
        }

    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser2;
    private com.toedter.calendar.JDateChooser jDateChooser4;
    private com.toedter.calendar.JDateChooser jDateChooser5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JComboBox<String> jcbAula;
    private javax.swing.JComboBox<String> jcb_Carrera;
    private javax.swing.JComboBox<String> jcb_año;
    private javax.swing.JComboBox<String> jcb_año1;
    private javax.swing.JComboBox<String> jcb_mes;
    private javax.swing.JComboBox<String> jcb_mes1;
    private javax.swing.JComboBox<String> jcb_modo_por_tiempo;
    private javax.swing.JComboBox<String> jcb_modo_tiempo1;
    private javax.swing.JComboBox<String> jcb_rep_uso_por_Aulas;
    private javax.swing.JComboBox<String> jcb_rep_uso_por_Carreras;
    // End of variables declaration//GEN-END:variables

    private void Reporte_Elegido_Por_Aula(int elegido) {
      switch (elegido) {
            case 0:
                JOptionPane.showMessageDialog(null, "No ha Elegido ningun Reporte");
                break;

            case 1:
                 Conexion b4=new Conexion();
                conn = b4.getConnection();
                if(jcbAula.getSelectedItem().equals("Seleccione"))
                {
                   JOptionPane.showMessageDialog(null, "No ha seleccionado ningun Aula");
                }
                else
                {
                Aula=jcbAula.getSelectedItem().toString();
                File file11 = new File("Reportes_Jasper/Uso_del_Equipo_por_Aula.jasper");
                JasperReport jr11 = null;
                File dir11 = new File("Reportes_Jasper");
                if (!dir11.exists()) {
                    JOptionPane.showMessageDialog(null, "La ruta: " + file11.getAbsolutePath() + " no existe");
                }
                try {
                   Map parametros13=new HashMap();
                   parametros13.put("aula1", Aula);
                    jr11 = (JasperReport) JRLoader.loadObjectFromFile(file11.getAbsolutePath());
                    JasperPrint jp11 = JasperFillManager.fillReport(jr11, parametros13, conn);
                    JasperViewer jv11 = new JasperViewer(jp11,false);
                    jv11.setVisible(true);
                   b4.desconectar();
                } catch (JRException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                }
                break;

            case 2:
                 Conexion b5=new Conexion();
                conn = b5.getConnection();
                File file12 = new File("Reportes_Jasper/Uso_del_Equipo_Concentrado_por_Aula.jasper");
                JasperReport jr12 = null;
                File dir12 = new File("Reportes_Jasper");
                if (!dir12.exists()) {
                    JOptionPane.showMessageDialog(null, "La ruta: " + file12.getAbsolutePath() + " no existe");
                }
                try {
                    jr12 = (JasperReport) JRLoader.loadObjectFromFile(file12.getAbsolutePath());
                    JasperPrint jp12 = JasperFillManager.fillReport(jr12, null, conn);
                    JasperViewer jv12 = new JasperViewer(jp12,false);
                    jv12.setVisible(true);
                    b5.getConnection();
                } catch (JRException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void Reporte_Elegido_Por_Aula_Tiempo_Mes(int elegido) {
        switch (elegido) {
            case 0:
                JOptionPane.showMessageDialog(null, "No ha Elegido ningun Reporte");
                break;

            case 1:
                 Conexion b4=new Conexion();
                conn = b4.getConnection();
                String mes = jcb_mes.getSelectedItem().toString();
                String año = jcb_año.getSelectedItem().toString();
                String datos1[] = Obtener_fechas(mes, año);

                //Estructura:
                //Año , Mes , Limite de ese mes
                String fecha2 = "" + datos1[0] + "/" + datos1[1] + "/" + datos1[2] + "";
                String fecha1 = "" + datos1[0] + "/" + datos1[1] + "/01";
                if (jcb_mes.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ningun mes");
                } else {
                      Aula=jcbAula.getSelectedItem().toString();
                    try {
                            File file7 = new File("Reportes_Jasper/Uso_del_Equipo_por_Aula_Tiempo_Mensual.jasper");
                        File dir7 = new File("Reportes_Jasper");
                        if (!dir7.exists()) {
                            JOptionPane.showMessageDialog(null, "La ruta: " + file7.getAbsolutePath() + " no existe");
                        }
                        JasperReport jr7 = null;
                        jr7 = (JasperReport) JRLoader.loadObjectFromFile(file7.getAbsolutePath());
                        Map parametros7 = new HashMap();
                        parametros7.put("fecha7", fecha1);
                        parametros7.put("fecha8", fecha2);
                        parametros7.put("mes3", mes);
                        parametros7.put("año3", año);
                        parametros7.put("aula2", Aula);
                        JasperPrint jp7 = JasperFillManager.fillReport(jr7, parametros7, conn);
                        JasperViewer jv7 = new JasperViewer(jp7,false);
                        jv7.setVisible(true);
                        b4.desconectar();
                    } catch (JRException ex) {
                       JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                }
                break;

            case 2:
                 Conexion b5=new Conexion();
                conn = b5.getConnection();
                String mes1 = jcb_mes.getSelectedItem().toString();
                String año1 = jcb_año.getSelectedItem().toString();
                String datos2[] = Obtener_fechas(mes1, año1);

                //Estructura:
                //Año , Mes , Limite de ese mes
                String fecha4 = "" + datos2[0] + "-" + datos2[1] + "-" + datos2[2] + "";
                String fecha3 = "" + datos2[0] + "-" + datos2[1] + "-01";

                if (jcb_mes.getSelectedIndex() == 0) {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado ningun mes");
                } else {
                    Aula=jcbAula.getSelectedItem().toString();
                    try {
                        File file8 = new File("Reportes_Jasper/Uso_del_Equipo_Concentrado_por_Aula_Tiempo_Mensual.jasper");
                        JasperReport jr8 = null;
                        File dir8 = new File("Reportes_Jasper");
                        if (!dir8.exists()) {
                            JOptionPane.showMessageDialog(null, "La ruta: " + file8.getAbsolutePath() + " no existe");
                        }
                        jr8 = (JasperReport) JRLoader.loadObjectFromFile(file8.getAbsolutePath());
                        Map parametros8 = new HashMap();
                        parametros8.put("fecha11", fecha3);
                        parametros8.put("fecha12", fecha4);
                        parametros8.put("mes4", mes1);
                        parametros8.put("año4", año1);
                        parametros8.put("aula3", Aula);
                        JasperPrint jp8 = JasperFillManager.fillReport(jr8, parametros8, conn);
                        JasperViewer jv8 = new JasperViewer(jp8,false);
                        jv8.setVisible(true);
                        b5.desconectar();
                    } catch (JRException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }

                }
                break;
        } 
    }

    private void Reporte_Elegido_Por_Aula_Tiempo_Libre(int elegido) {
       switch (elegido) {
            case 0:
                JOptionPane.showMessageDialog(null, "No ha Elegido ningun Reporte");
                break;

            case 1:
                 Conexion b3=new Conexion();
                conn = b3.getConnection();
                //Primero se Obtiene la fecha de los Jdatechooser 
                String fecha3 = fecha.getFecha(jDateChooser2);
                String fecha4 = fecha.getFecha(jDateChooser1);

                //Despues se Desfragmenta en arreglos quitando la diagonal de por medio     
                String cad3[] = fecha3.split("/");
                String cad4[] = fecha4.split("/");

                //Ambas fechas se ensamblan en el orden que la base de datos reconoce las fechas
                //Año-Mes-Dia;    
                String fecha3_mod1 = cad3[2] + "-" + cad3[1] + "-" + cad3[0];
                String fecha4_mod1 = cad4[2] + "-" + cad4[1] + "-" + cad4[0];

                try {
                    File file3 = new File("Reportes_Jasper/Uso_del_Equipo_por_Aula_Tiempo_Libre.jasper");
                    File dir2 = new File("Reportes_Jasper");
                    if (!dir2.exists()) {
                        JOptionPane.showMessageDialog(null, "La ruta: " + file3.getAbsolutePath() + " no existe");
                    }
                    Aula=jcbAula.getSelectedItem().toString();
                    JasperReport jr3 = null;
                    jr3 = (JasperReport) JRLoader.loadObjectFromFile(file3.getAbsolutePath());
                    Map parametros3 = new HashMap();
                    parametros3.put("fecha9", fecha4_mod1);
                    parametros3.put("fecha10", fecha3_mod1);
                    parametros3.put("fecha9_mod", fecha4);
                    parametros3.put("fecha10_mod", fecha3);
                    parametros3.put("aula4", Aula);
                    JasperPrint jp3 = JasperFillManager.fillReport(jr3, parametros3, conn);
                    JasperViewer jv3 = new JasperViewer(jp3,false);
                    jv3.setVisible(true);
                    jv3.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    b3.desconectar();
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }

                break;

            case 2:
                 Conexion b5=new Conexion();
                conn = b5.getConnection();
                //Primero se Obtiene la fecha de los Jdatechooser 
                String fecha2 = fecha.getFecha(jDateChooser2);
                String fecha1 = fecha.getFecha(jDateChooser1);

                //Despues se Desfragmenta en arreglos quitando la diagonal de por medio     
                String cad1[] = fecha1.split("/");
                String cad2[] = fecha2.split("/");

                //Ambas fechas se ensamblan en el orden que la base de datos reconoce las fechas
                //Año-Mes-Dia;    
                String fecha1_mod = cad1[2] + "-" + cad1[1] + "-" + cad1[0];
                String fecha2_mod = cad2[2] + "-" + cad2[1] + "-" + cad2[0];

                try {
                    File file4 = new File("Reportes_Jasper/Uso_del_Equipo_Concentrado_por_Aula_Tiempo_Libre.jasper");
                    File dir4 = new File("Reportes_Jasper");
                    if (!dir4.exists()) {
                        JOptionPane.showMessageDialog(null, "La ruta: " + file4.getAbsolutePath() + " no existe");
                    }
                   
                    JasperReport jr4 = null;
                    jr4 = (JasperReport) JRLoader.loadObjectFromFile(file4.getAbsolutePath());
                    Map parametros4 = new HashMap();
                    parametros4.put("fecha13", fecha1_mod);
                    parametros4.put("fecha14", fecha2_mod);
                    parametros4.put("fecha13_mod", fecha1);
                    parametros4.put("fecha14_mod", fecha2);
                    JasperPrint jp4 = JasperFillManager.fillReport(jr4, parametros4, conn);
                    JasperViewer jv4 = new JasperViewer(jp4,false);
                    jv4.setVisible(true);
                    jv4.setDefaultCloseOperation(HIDE_ON_CLOSE);
                    b5.desconectar();
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;

        }
    }

    private void Reporte_Elegido_Por_Carrera(int elegido) {
       switch (elegido) {
            case 0:
                JOptionPane.showMessageDialog(null, "No ha Elegido ningun Reporte");
                break;

            case 1:
                Conexion b1=new Conexion();
                if(jcb_Carrera.getSelectedIndex()!=0)
                {
                    
                Carrera=jcb_Carrera.getSelectedItem().toString();
                
                conn =b1.getConnection();
                File file = new File("Reportes_Jasper/Uso_del_Equipo_por_Carrera(Sin_tiempo).jasper");
                File dir = new File("Reportes_Jasper");
//                if (!dir.exists()) {
//                    JOptionPane.showMessageDialog(null, "La ruta: " + file.getAbsolutePath() + " no existe");
//                }
                JasperReport jr = null;

                try {
                    //Parametros que se le pasaran al reporte
                     Map parametros1 = new HashMap();
                     parametros1.put("Carrera1",Carrera);                  
                    jr = (JasperReport) JRLoader.loadObjectFromFile(file.getAbsolutePath());
                    JasperPrint jp = JasperFillManager.fillReport(jr, parametros1, conn);
                    JasperViewer jv = new JasperViewer(jp,false);
                    jv.setVisible(true);
                    b1.desconectar();
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, "error:"+ex.getMessage());
                }
                
       }
       else
       {
         JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna carrera");
        }
                
                break;

            case 2:
                Conexion b2=new Conexion();
                   conn = b2.getConnection();
                File file2 = new File("Reportes_Jasper/Uso_del_Equipo_Concentrado_por_Carrera(Sin_tiempo).jasper");
                JasperReport jr2 = null;
                File dir2 = new File("Reportes_Jasper");
                if (!dir2.exists()) {
                    JOptionPane.showMessageDialog(null, "La ruta: " + file2.getAbsolutePath() + " no existe");
                }

                try {
                    jr2 = (JasperReport) JRLoader.loadObjectFromFile(file2.getAbsolutePath());
                    JasperPrint jp2 = JasperFillManager.fillReport(jr2, null, conn);
                    JasperViewer jv2 = new JasperViewer(jp2,false);
                    jv2.setVisible(true);
                    b2.desconectar();
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void Reporte_Elegido_Por_Carrera_Tiempo_Mes(int elegido) {
       switch (elegido) {
            case 0:
                JOptionPane.showMessageDialog(null, "No ha Elegido ningun Reporte");
                break;

            case 1:
                Conexion b1=new Conexion();
                conn = b1.getConnection();
               
                if(jcb_Carrera.getSelectedIndex()==0)
                {
                 JOptionPane.showMessageDialog(null, "No ha seleccionado una carrera");
                }
                else
                {
                 Carrera=jcb_Carrera.getSelectedItem().toString();    
                String mes = jcb_mes1.getSelectedItem().toString();
                String año = jcb_año1.getSelectedItem().toString();
                String datos1[] = Obtener_fechas(mes, año);

                //Estructura:
                //Año , Mes , Limite de ese mes
                String fecha2 = "" + datos1[0] + "/" + datos1[1] + "/" + datos1[2] + "";
                //Estructura
                //Año, Mes , Inicio de ese mes
                String fecha1 = "" + datos1[0] + "/" + datos1[1] + "/01";
                File file9 = new File("Reportes_Jasper/Uso_del_Equipo_por_Carrera_Tiempo_Mensual.jasper");
                JasperReport jr9 = null;
                File dir9 = new File("Reportes_Jasper");
                if (!dir9.exists()) {
                    JOptionPane.showMessageDialog(null, "La ruta: " + file9.getAbsolutePath() + " no existe");
                }
                try {

                    jr9 = (JasperReport) JRLoader.loadObjectFromFile(file9.getAbsolutePath());
                    Map parametros9 = new HashMap();
                    parametros9.put("fecha3", fecha1);
                    parametros9.put("fecha4", fecha2);
                    parametros9.put("mes1", mes);
                    parametros9.put("año1", año);
                    parametros9.put("Carrera3", Carrera);
                    JasperPrint jp9 = JasperFillManager.fillReport(jr9, parametros9, conn);
                    JasperViewer jv9 = new JasperViewer(jp9,false);
                    jv9.setVisible(true);
                    b1.desconectar();
                } catch (JRException ex) {
                    JOptionPane.showMessageDialog(null, "error: " + ex);
                }
                }
                break;

            case 2:
                Conexion b2=new Conexion();
                conn = b2.getConnection();
                String mes1 = jcb_mes1.getSelectedItem().toString();
                String año1 = jcb_año1.getSelectedItem().toString();
                String datos2[] = Obtener_fechas(mes1, año1);

                //Estructura:
                //Año , Mes , Limite de ese mes
                String fecha16 = "" + datos2[0] + "/" + datos2[1] + "/" + datos2[2] + "";
                String fecha17 = "" + datos2[0] + "/" + datos2[1] + "/01";
                File file10 = new File("Reportes_Jasper/Uso_del_Equipo_Concentrado_por_Carrera_Tiiempo_Mensual.jasper");
                JasperReport jr10 = null;
                File dir10 = new File("Reportes_Jasper");
                if (!dir10.exists()) {
                    JOptionPane.showMessageDialog(null, "La ruta: " + file10.getAbsolutePath() + " no existe");
                }
                try {

                    jr10 = (JasperReport) JRLoader.loadObjectFromFile(file10.getAbsolutePath());
                    Map parametros10 = new HashMap();
                    parametros10.put("fecha17", fecha17);
                    parametros10.put("fecha18", fecha16);
                    parametros10.put("mes5", mes1);
                    parametros10.put("año5", año1);
                    JasperPrint jp10 = JasperFillManager.fillReport(jr10, parametros10, conn);
                    JasperViewer jv10 = new JasperViewer(jp10,false);
                    jv10.setVisible(true);
                    b2.desconectar();
                } catch (JRException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;
        }
    }

    private void Reporte_Elegido_Por_Carrera_Tiempo_Libre(int elegido) {
       switch (elegido) {
            case 0:
                JOptionPane.showMessageDialog(null, "No ha Elegido ningun Reporte");
                break;

            case 1:
                Conexion b1=new Conexion();
                if(jcb_Carrera.getSelectedIndex()==0)
                {
                    JOptionPane.showMessageDialog(null, "No ha seleccionado una Carrera");
                }
                else
                {               
                Carrera=jcb_Carrera.getSelectedItem().toString();
                conn = b1.getConnection();
                //Primero se Obtiene la fecha de los Jdatechooser 
                String fecha11 = fecha.getFecha(jDateChooser4);
                String fecha12 = fecha.getFecha(jDateChooser5);

                //Despues se Desfragmenta en arreglos quitando la diagonal de por medio     
                String cad13[] = fecha11.split("/");
                String cad14[] = fecha12.split("/");

                //Ambas fechas se ensamblan en el orden que la base de datos reconoce las fechas
                //Año-Mes-Dia;    
                String fecha11_mod = cad13[2] + "/" + cad13[1] + "/" + cad13[0];
                String fecha12_mod = cad14[2] + "/" + cad14[1] + "/" + cad14[0];

                try {
                    File file5 = new File("Reportes_Jasper/Uso_del_Equipo_por_Carrera_Tiempo_Libre.jasper");
                    File dir5 = new File("Reportes_Jasper");
                    if (!dir5.exists()) {
                        JOptionPane.showMessageDialog(null, "La ruta: " + file5.getAbsolutePath() + " no existe");
                    }
                    JasperReport jr5 = null;
                    jr5 = (JasperReport) JRLoader.loadObjectFromFile(file5.getAbsolutePath());
                    Map parametros5 = new HashMap();  
                    parametros5.put("fecha1", fecha11_mod);
                    parametros5.put("fecha2", fecha12_mod);
                    parametros5.put("fecha1_mod", fecha11);
                    parametros5.put("fecha2_mod", fecha12);
                    parametros5.put("Carrera2", Carrera);
                    JasperPrint jp5 = JasperFillManager.fillReport(jr5, parametros5, conn);
                    JasperViewer jv5 = new JasperViewer(jp5,false);
                    jv5.setVisible(true);
                    b1.desconectar();
                } catch (JRException ex) {
                 JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                }
                break;

            case 2:
              Conexion b2=new Conexion();
                conn = b2.getConnection();
                //Primero se Obtiene la fecha de los Jdatechooser 
                String fecha2 = fecha.getFecha(jDateChooser5);
                String fecha1 = fecha.getFecha(jDateChooser4);

                //Despues se Desfragmenta en arreglos quitando la diagonal de por medio     
                String cad1[] = fecha1.split("/");
                String cad2[] = fecha2.split("/");

                //Ambas fechas se ensamblan en el orden que la base de datos reconoce las fechas
                //Año-Mes-Dia;    
                String fecha1_mod = cad1[2] + "-" + cad1[1] + "-" + cad1[0];
                String fecha2_mod = cad2[2] + "-" + cad2[1] + "-" + cad2[0];

                try {

                    File file6 = new File("Reportes_Jasper/Uso_del_Equipo_Concentrado_por_Carrera_Tiiempo_Libre.jasper");
                    File dir6 = new File("Reportes_Jasper");
                    if (!dir6.exists()) {
                        JOptionPane.showMessageDialog(null, "La ruta: " + file6.getAbsolutePath() + " no existe");
                    }
                    JasperReport jr6 = null;
                    jr6 = (JasperReport) JRLoader.loadObjectFromFile(file6.getAbsolutePath());
                    Map parametros6 = new HashMap();
                    parametros6.put("fecha15", fecha1_mod);
                    parametros6.put("fecha16", fecha2_mod);
                    parametros6.put("fecha15_mod", fecha1);
                    parametros6.put("fecha16_mod", fecha2);
                    JasperPrint jp6 = JasperFillManager.fillReport(jr6, parametros6, conn);
                    JasperViewer jv6 = new JasperViewer(jp6,false);
                    jv6.setVisible(true);
                    b2.desconectar();
                } catch (JRException ex) {
                   JOptionPane.showMessageDialog(null, ex.getMessage());
                }
                break;

        }
    }
    
    //Devuelve la siguiente cadena segun sea el caso que se haya seleccionado en los jcombo box
          //año, numero del mes,dia limite de ese mes
    public String[] Obtener_fechas(String mes, String año) {

        String cadena[] = new String[3];
        cadena[0] = año;

        String lim = "";
        switch (mes) {
            case "Enero":
                lim = "31";
                cadena[1] = "01";
                cadena[2] = lim;
                break;
            case "Febrero":
                lim = "29";
                cadena[1] = "02";
                cadena[2] = lim;
                break;
            case "Marzo":
                lim = "31";
                cadena[1] = "03";
                cadena[2] = lim;
                break;

            case "Abril":
                lim = "30";
                cadena[1] = "04";
                cadena[2] = lim;
                break;

            case "Mayo":
                lim = "31";
                cadena[1] = "05";
                cadena[2] = lim;
                break;

            case "Junio":
                lim = "30";
                cadena[1] = "06";
                cadena[2] = lim;
                break;

            case "Julio":
                lim = "31";
                cadena[1] = "07";
                cadena[2] = lim;
                break;

            case "Agosto":
                lim = "31";
                cadena[1] = "08";
                cadena[2] = lim;
                break;

            case "Septiembre":
                lim = "30";
                cadena[1] = "09";
                cadena[2] = lim;
                break;

            case "Octubre":
                lim = "31";
                cadena[1] = "10";
                cadena[2] = lim;
                break;

            case "Noviembre":
                lim = "30";
                cadena[1] = "11";
                cadena[2] = lim;
                break;

            case "Diciembre":
                lim = "31";
                cadena[1] = "12";
                cadena[2] = lim;
                break;
        }
        return cadena;
    }
}
