/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mantenimiento;

import java.util.Calendar;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Hp Pavilion g4
 */
public class mantenimiento extends javax.swing.JInternalFrame {
    
    String user="root";
    String bd="ine";
    String password="";
    String host="localhost";
    String puerto="3306";
    String format;
    Process proceso;
    ProcessBuilder constructor_proceso;
    String date;
    /**
     * Creates new form vista
     */
    FileNameExtensionFilter filtro=new FileNameExtensionFilter("Base de datos (sql)", "sql");
    String path=null;
     
    public mantenimiento() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_Respaldar = new javax.swing.JTextField();
        txt_Restaurar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnRespaldar = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        btnRestaurar = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Mantenimiento de la Base de Datos");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/DataBase-base-de-datos.jpg"))); // NOI18N

        jLabel1.setText("Respaldar:");

        jLabel3.setText("Restaurar:");

        btnRespaldar.setText("Respaldar");
        btnRespaldar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRespaldarActionPerformed(evt);
            }
        });

        jButton5.setText("...");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        btnRestaurar.setText("Restaurar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        jButton3.setText("Buscar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(131, 131, 131))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton3))
                            .addComponent(btnRestaurar))
                        .addGap(25, 25, 25))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRespaldar)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_Respaldar, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton5)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txt_Restaurar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnRestaurar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 148, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txt_Respaldar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnRespaldar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

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

    private void btnRespaldarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRespaldarActionPerformed
        // TODO add your handling code here:
        if(path.equals(""))
        {
            JOptionPane.showMessageDialog(null,"No ha seleccionado un lugar para guardar la base de datos \n Compruebe el nombre de la base de datos");
        }
        else
        {

            Process p=null;
            try {
                Runtime runtime = Runtime.getRuntime();
                p=runtime.exec("C:/Program Files/MySQL/MySQL Workbench 6.3 CE/mysqldump.exe --user="+user+" --host="+host+" --port="+puerto+" --add-drop-database -B "+bd+" -r"+path);

                int processComplete =p.waitFor();
                if (processComplete==0) {
                    jLabel13.setText("Backup Created Succuss");
                    JOptionPane.showMessageDialog(null, "Base de datos respaldada con exito");
                    txt_Respaldar.setText("");
                }else{
                    jLabel13.setText("Can't Create backup");
                    JOptionPane.showMessageDialog(null, "Error al intentar Respaldar la Base de Datos \n No se Permiten espacios en el nombre del respaldo");
                    txt_Respaldar.setText("");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnRespaldarActionPerformed

    
     public String[] Comprobar_Tiempo(int dia, int mes)
    {
        String datos[]=new String[2];
          if(dia<=10)
        {
            String dia1=String.valueOf("0"+dia);
            datos[0]=dia1;
        }
          else
          {
             datos[0]=String.valueOf(dia);  
          }
        
        if(mes<=10)
        {
            String mes1=String.valueOf("0"+mes);
             datos[1]=mes1;
        }
        else
        {
             datos[1]=String.valueOf(mes);  
          }
        return datos;
    }
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        txt_Respaldar.setText("");
        Calendar fecha=Calendar.getInstance();
        int dia=fecha.get(fecha.DAY_OF_MONTH);
        int año=fecha.get(fecha.YEAR);
        int mes=fecha.get(fecha.MONTH);
        String datos1[]=Comprobar_Tiempo(dia, mes);

        date=datos1[0]+"-"+datos1[1]+"-"+año;

        JFileChooser seleccionar=new JFileChooser();
        //Se pasa el filtro
        seleccionar.setFileFilter(filtro);
        //Se confirma si selecciono guardar
        int opcion=seleccionar.showSaveDialog(this);
        if(opcion==JFileChooser.APPROVE_OPTION)
        {
            //Se obtiene el nombre y direccion del archivo
            path=seleccionar.getSelectedFile().getPath();
            JOptionPane.showMessageDialog(null, "Ruta: "+path);
            //Se pasa la ruta al campo de texto
            //          path=path+"_"+date;
            //          txt_Respaldar.setText(path);
            if(!path.endsWith(".sql"))
            {
                path=path+"_"+date;
                path=path+".sql";
                txt_Respaldar.setText(path);
            }
            txt_Respaldar.setText(path);
        }

        else
        {
            //En caso se que haya seleccionado que no
            JOptionPane.showMessageDialog(null, "No respaldo la base de datos");
        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        // TODO add your handling code here:
        String dbUserName = "root";// username
        String dbPassword = "";//Password

        String[] restoreCmd = new String[]{"C:/Program Files/MySQL/MySQL Workbench 6.3 CE/mysql.exe ", "--user=" + dbUserName, "--password=" + dbPassword, "-e", "source " + path};
        Process runtimProcess;
        try {
            runtimProcess = Runtime.getRuntime().exec(restoreCmd);
            int proceCom = runtimProcess.waitFor();

            if (proceCom==0) {
                jLabel4.setText("Restored Succuss");
                JOptionPane.showMessageDialog(null, "Base de datos Restaurada con exito");
                JOptionPane.showMessageDialog(null, "Vuelva a Abrir la Aplicacion para ver los cambios efectuados");
                System.exit(0);
            }else{
                jLabel4.setText("Restored Failed");
                JOptionPane.showMessageDialog(null, "Error al intentar Restaurar la Base de Datos");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnRestaurarActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        txt_Restaurar.setText("");
        JFileChooser seleccionar=new JFileChooser();
        seleccionar.setFileFilter(filtro);

        //Se confirma si selecciono abrir
        int opcion=seleccionar.showOpenDialog(this);
        //En caso de que haya seleccionar abrir
        if(opcion==JFileChooser.APPROVE_OPTION)
        {
            path=seleccionar.getSelectedFile().getAbsolutePath();

            JOptionPane.showMessageDialog(null, "Ha seleccionado la base de datos"+path);
            txt_Restaurar.setText(path);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "No selecciono una base de datos");
        }
    }//GEN-LAST:event_jButton3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRespaldar;
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txt_Respaldar;
    private javax.swing.JTextField txt_Restaurar;
    // End of variables declaration//GEN-END:variables
}
