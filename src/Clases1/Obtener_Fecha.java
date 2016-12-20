/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases1;

import com.toedter.calendar.JDateChooser;
import java.text.SimpleDateFormat;



/**
 *
 * @author Hp Pavilion g4
 */
public class Obtener_Fecha {
    SimpleDateFormat formato=new SimpleDateFormat("dd/MM/yyyy");
  public String getFecha(JDateChooser jd)
{
if(jd.getDate()!=null)
{
return formato.format(jd.getDate());
}
else
{
    return null;
}
}
}
