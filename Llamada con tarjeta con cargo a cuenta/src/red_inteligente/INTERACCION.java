/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package red_inteligente;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author Alfonso
 */
public class INTERACCION {
    
    public void inter_user_sol(int op) {
        InputStream path; 
        if(op == 1){
            System.out.println("tarjeta incorrecta");
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File("C:\\tarjeta_incorrecta.wav")));
                clip.start();
                while (!clip.isRunning())
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                while (clip.isRunning())
                    Thread.sleep(10);
                clip.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op == 2){
            System.out.println("PIN incorrecto");
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File("C:\\pin_incorrecto.wav")));
                clip.start();
                while (!clip.isRunning())
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                while (clip.isRunning())
                    Thread.sleep(10);
                clip.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if(op == 3){
            //Error numero
            System.out.println("Numero incorrecto");
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File("C:\\numero_incorrecto.wav")));
                clip.start();
                while (!clip.isRunning())
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                while (clip.isRunning())
                    Thread.sleep(10);
                clip.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Terminar programa;
    }
    
    public String inter_user_ing(int op){
        String numerodes = "0";
        if(op == 1){
            //Ingresa pin
            System.out.println("Ingresa PIN");
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File("C:\\ingresa_pin.wav")));
                clip.start();
                while (!clip.isRunning())
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                while (clip.isRunning())
                    Thread.sleep(10);
                clip.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            }
            numerodes = "12345";
        }
        if(op == 2){
            //Ingresa numero
            System.out.println("Ingresa Numero");
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File("C:\\ingres_num.wav")));
                clip.start();
                while (!clip.isRunning())
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
                    }
                while (clip.isRunning())
                    Thread.sleep(10);
                clip.close();
            } catch (InterruptedException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(SRF.class.getName()).log(Level.SEVERE, null, ex);
            }
            numerodes = "015979822858";
        }
        //Terminar programa;
        
        return numerodes;
    }
    
    public void POR(int op){
        if(op == 1)
        {
            System.out.println("ERROR");
            System.exit(0);//terminar proceso
        }
        if(op == 2)
        {
            //Iniciar llamada
        }
    }
    
    
    
}
