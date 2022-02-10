/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package perceptron;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextField;

/**
 *
 * @author galle
 */
public class Perceptron_simple {

    public float W[], learning_rate;
    public int epocas_maximas;

    public Perceptron_simple(float learning_rate, int epocas) {
        this.W = new float[]{26.0f, 2f, -3f};
        this.learning_rate = learning_rate;
        this.epocas_maximas = epocas;
    }

    public void Dibujar_linea(JPanel El_panel, Color pintura)//inclompleto
    {
        Graphics g;
        g = El_panel.getGraphics();
        Point primer_punto, segundo_punto;
        g.setColor(pintura);
        g.drawLine(0, (int) (W[0] / W[2]), 500, (int) ((-W[1] * 500 + W[0]) / W[2]));

        //System.out.println((int)((W[1]*-500f+W[0])/1f-W[2]));
        //System.out.println((int)((W[1]*1000f+W[0])/1f-W[2]));
        /*
        int Y = 0;
        int X = 0;
        int contador = 0;
        boolean noValido = true;
        
        while (X<500 && contador < this.epocas_maximas)
        {
            
        }
         */
        //g.drawLine(0, (int)(W[1]), epocas_maximas, epocas_maximas);
    }
    public boolean Pw_cambiado(float W[],float X[])
    {
        float suma = W[0]*2;
        for(int i = 1; i< W.length; i++)
        {
            suma = suma + W[i+1]*X[i] ;
        }
        if (suma>=0)
        return true;
        else
            return false;
        
    }
    
    public boolean Pw(Point coordenadas) {

        if (this.W[1] * (float) coordenadas.getX() + this.W[2] * (float) coordenadas.getY() >= this.W[0]) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Pw_con_dibujo(Point coordenadas, JPanel El_panel) {
        Graphics g;
        g = El_panel.getGraphics();
        float evaluacion;
        evaluacion = this.W[1] * (float) coordenadas.getX() + this.W[2] * (float) coordenadas.getY();

        //System.out.println("Evaluacion dio: "+evaluacion+" se activa con: "+W[0]);
        if (evaluacion >= this.W[0]) {
            g.setColor(Color.red);
            g.drawOval((int) coordenadas.getX() - 2, (int) coordenadas.getY() - 2, 5, 5);
            return true;
        } else {
            g.setColor(Color.BLUE);
            g.drawOval((int) coordenadas.getX() - 2, (int) coordenadas.getY() - 2, 5, 5);
            return false;
        }

    }

    public void Entrenar(List<Point> puntos_tipo_1, List<Point> puntos_tipo_0, List<Point> click, JTextField w0, JTextField w1, JTextField w2, JTextField learning_rateTXT, JTextField iterationsTXT,JPanel JPanel_principal) {
        W[1] = Float.parseFloat(w1.getText()); //PESO 1
        W[2] = Float.parseFloat(w2.getText()); //PESO 2
        W[0] = Float.parseFloat(w0.getText());//PESO 0
        learning_rate = Float.parseFloat(learning_rateTXT.getText()); //learning rate
        int iterMax = Integer.parseInt(iterationsTXT.getText()); // maximas iteraciones
        
        this.Dibujar_linea(JPanel_principal, Color.ORANGE);
        
        int contador_iteracion =0;
        int N = click.size();
        int error = 0;
        int Y = 0;
        int evalPw = 0;
        
        
        
        boolean done = false;
        while(!done && contador_iteracion<iterMax)
        {
            done = true;
            for(int i = 0; i< N; i++){
                
                if (puntos_tipo_1.contains(click.get(i)))
                {
                    Y = 1;
                }
                else
                {
                    Y = 0;
                }
                if (Pw(new Point(click.get(i))))
                {
                    evalPw=1;
                }
                else{
                    evalPw=0;
                }
                
                error = Y-evalPw;
                if(error != 0){
                    done = false;
                    W[0] = W[0]+learning_rate*(float)error;
                    W[1] = W[1]+learning_rate*(float)error*(float)click.get(i).getX();
                    W[2] = W[2]+learning_rate*(float)error*(float)click.get(i).getY();
                    this.Dibujar_linea(JPanel_principal, Color.pink);
                    
                }
            }
              System.out.println("Iteracion: " + contador_iteracion);
            contador_iteracion++;
          
        }
        
    }

}
