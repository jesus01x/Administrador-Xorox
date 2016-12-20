/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases1;

import javax.swing.JFrame;

/**
 *
 * @author Hp Pavilion g4
 */
public class Actualizarfondo extends Thread{
    int x=0;
    int y=0;
    JFrame frame;
    
    public Actualizarfondo(JFrame frame){
        this.frame=frame;
    }
    
    public void run()
    {
        while(true)
        {   
            System.out.println("Valor de x:"+frame.getWidth()+" Valor de y:"+frame.getHeight());
            fondo_imagen  p=new fondo_imagen(x,y,"/Imagenes/original.jpg");
            frame.add(p);
            frame.pack();   
   }
            
    }
}
