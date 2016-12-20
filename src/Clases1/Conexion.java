/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Jesus
 */
public class Conexion {
public String bd = "xorox";
public String login = "root";
public String password = "";
public String url = "jdbc:mysql://localhost/"+bd;

Connection conn = null; 
    
   public Conexion() { 
      try{          
         Class.forName("com.mysql.jdbc.Driver");          
         conn = DriverManager.getConnection(url,login,password); 
//         if (conn!=null){ 
//            JOptionPane.showMessageDialog(null,"Conexión a base de datos "+bd+". listo"); 
//         } 
      }catch(SQLException e){ 
         System.out.println(e); 
      }catch(ClassNotFoundException e){ 
         System.out.println(e); 
      } 
   }
   
   //Te comprueba la conexion  
   public Connection getConnection(){ 
      return conn; 
   } 
 
   public void desconectar(){ 
      conn = null; 
   }
    
    }

