/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package pracaempiryczna;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.sound.sampled.*;

import java.util.Timer;
import java.util.TimerTask;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import java.util.*;
import java.text.SimpleDateFormat;

public class Capture extends JFrame {
  protected boolean running;
  ByteArrayOutputStream out;
  Container content;
  Matryca m1,m2,m3;
  MediaPanel mediaPanel;
  JButton matryca,next;
  Timer timer;
  GridBagConstraints c;

  String b,dir;
  //String filmy[] = {"video/00007.mpg","video/00019.mpg","video/00039.mpg","video/00047.mpg","video/00052.mpg"};
  String filmy[] = {"video/00007.mpg","video/00009.mpg","video/00039.mpg","video/00047.mpg","video/00052.mpg"};
  //int czas[] = {14,11,8,8,7};
  int czas[] = {14,21,8,8,7};
  int i,licznik=0,nr_matrycy=1,nr_badania=0,j=0;

  public Capture() {
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    int windowX = Math.max(0, (screenSize.width - 650) / 2);
    int windowY = Math.max(0, (screenSize.height - 100) / 2);
    final int windowX2 = Math.max(0, (screenSize.width - 800) / 2);
    final int windowY2 = Math.max(0, (screenSize.height - 600) / 2);
    setSize(screenSize);


    //setDefaultCloseOperation(EXIT_ON_CLOSE);
    content = getContentPane();
    content.setSize(800,600);

    content.setLayout(new BorderLayout());
    //setBackground(Color.white);
    content.setBackground(Color.white);
    matryca = new JButton("Jak skończysz mówić naciśnij mnie");
    final JButton start = new JButton("Start");
    final JButton stop = new JButton("Zakończ");
    next = new JButton("Jak będziesz gotów/owa naciśnij mnie");

    //init state
    start.setSize(650,100);
    start.setFont(new Font("Dialog", 1, 34));
    start.setLocation(windowX, windowY);
    next.setSize(650,100);
    next.setFont(new Font("Dialog", 1, 34));
    next.setLocation(windowX, windowY);
    matryca.setSize(650,100);
    matryca.setFont(new Font("Dialog", 1, 34));
    matryca.setLocation(windowX, windowY);
    stop.setSize(650,100);
    stop.setFont(new Font("Dialog", 1, 34));
    stop.setLocation(windowX, windowY);
    start.setVisible(true);
    stop.setVisible(false);
    matryca.setVisible(false);
    next.setVisible(false);

    ActionListener matrycaListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        matryca.setVisible(false);
        if (i<5)
        {
          m1 = new Matryca(nr_matrycy,nr_badania);
          m1.setLocation(windowX2, windowY2);
          content.add(m1);
          m1.setVisible(false);
          playVideo(filmy[i]);
          DelayVideoMatryca(czas[i]);
          i++;
          nr_matrycy++;
        }
        else
          stop.setVisible(true);
      }
    };
    matryca.addActionListener(matrycaListener);
    content.add(matryca);

    ActionListener startListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        //setContentPane(content);
        nr_badania++;
        nr_matrycy=1;
        try{
         dir ="wyniki/Badanie" + nr_badania;
         // Create one directory
         new File(dir).mkdir();

         }catch (Exception e1){//Catch exception if any
         System.err.println("Error: " + e1.getMessage());
         }
        i=0;
        start.setVisible(false);
        playVideo(filmy[i]);
        DelayVideo(czas[i]);
        i++;
        captureAudio();
      }
    };
    start.addActionListener(startListener);
    content.add(start);

    ActionListener nextListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        next.setVisible(false);
        
        if (i < 2) {
          playVideo(filmy[i]);
          DelayVideo(czas[i]);
          i++;
        }
        else
          matryca.doClick();
        
      }
    };
    next.addActionListener(nextListener);
    content.add(next);

    ActionListener stopListener =
        new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        stop.setVisible(false);
        Date now = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
        String formattedDate = formatter.format(now);
        b = dir + "/Badanie" + nr_badania + formattedDate + ".wav";
        running = false;
        playAudio(); //save audio
        start.setVisible(true);
      }
    };
    stop.addActionListener(stopListener);
    content.add(stop);
  }

  private void captureAudio() {
    try {
      final AudioFormat format = getFormat();
      DataLine.Info info = new DataLine.Info(
        TargetDataLine.class, format);
      final TargetDataLine line = (TargetDataLine)
        AudioSystem.getLine(info);
      line.open(format);
      line.start();
      Runnable runner = new Runnable() {
        int bufferSize = (int)format.getSampleRate()
          * format.getFrameSize();
        byte buffer[] = new byte[bufferSize];

        public void run() {
          out = new ByteArrayOutputStream();
          running = true;
          try {
            while (running) {
              int count =
                line.read(buffer, 0, buffer.length);
              if (count > 0) {
                out.write(buffer, 0, count);
              }
            }
            
            out.close();
          } catch (IOException e) {
            System.err.println("I/O problems: " + e);
            System.exit(-1);
          }
        }
      };
      Thread captureThread = new Thread(runner);
      captureThread.start();
    } catch (LineUnavailableException e) {
      System.err.println("Line unavailable: " + e);
      System.exit(-2);
    }
  }

  private void playAudio() {
    try {
      byte audio[] = out.toByteArray();
      InputStream input =
        new ByteArrayInputStream(audio);
      final AudioFormat format = getFormat();
      final AudioInputStream ais =
        new AudioInputStream(input, format,
        audio.length / format.getFrameSize());
      DataLine.Info info = new DataLine.Info(
        SourceDataLine.class, format);
      final SourceDataLine line = (SourceDataLine)
        AudioSystem.getLine(info);
      line.open(format);
      line.start();

      Runnable runner = new Runnable() {
        int bufferSize = (int) format.getSampleRate()
          * format.getFrameSize();
        byte buffer[] = new byte[bufferSize];

        public void run() {
          try {
            int count;
            File audioFile = new File(b);
            while ((count = ais.read(
                buffer, 0, buffer.length)) != -1) {
              if (count > 0) {
                line.write(buffer, 0, count);
                AudioSystem.write(ais,AudioFileFormat.Type.WAVE,audioFile);
              }
            }
            line.drain();
            line.close();
          } catch (IOException e) {
            System.err.println("I/O problems: " + e);
            System.exit(-3);
          }
        }
      };
      Thread playThread = new Thread(runner);
      playThread.start();
    } catch (LineUnavailableException e) {
      System.err.println("Line unavailable: " + e);
      System.exit(-4);
    }
  }

  public void playVideo (String f){
    File file=new File(f); // video/00007.mpg
    URL mediaURL = null;

    try {// get the file as URL
      mediaURL = file.toURL();
    } // end try
    catch ( MalformedURLException malformedURLException )
    {
      System.err.println( "Could not create URL for the file" );
    } // end catch

    if (mediaURL != null) // only display if there is a valid URL
    {
      mediaPanel = new MediaPanel(mediaURL);
      content.add(mediaPanel);
      mediaPanel.setVisible(true);
    }
  }

  

  private AudioFormat getFormat() {
    float sampleRate = 8000;
    int sampleSizeInBits = 8;
    int channels = 2;
    boolean signed = true;
    boolean bigEndian = true;
    return new AudioFormat(sampleRate,
      sampleSizeInBits, channels, signed, bigEndian);
  }

  public void Delay(int seconds) {
    timer = new Timer();
    timer.schedule(new RemindTask(), seconds * 1000);
  }

  class RemindTask extends TimerTask {
    public void run() {
         m1.setVisible(false);
        //else if (nr_matrycy == 2) m2.setVisible(false);
        //else if (nr_matrycy == 3) m3.setVisible(false);
      matryca.setVisible(true);
      timer.cancel();
       //Not necessary because we call System.exit
      //System.exit(0);
    }
  }

  public void DelayVideoMatryca(int seconds) {
    timer = new Timer();
    timer.schedule(new RemindTaskMatryca(), seconds * 1000);
  }

  class RemindTaskMatryca extends TimerTask {
    public void run() {
        mediaPanel.setVisible(false);
        m1.setVisible(true);
        //else if (nr_matrycy == 2) m2.setVisible(true);
        //else if (nr_matrycy == 3) m3.setVisible(true);

        timer.cancel(); //Not necessary because we call System.exit
        Delay(2);
         
    }
  }

  public void DelayVideo (int seconds) {
    timer = new Timer();
    timer.schedule(new RemindTaskVideo(), seconds * 1000);
  }

  class RemindTaskVideo extends TimerTask {
    public void run() {
        mediaPanel.setVisible(false);
        next.setVisible(true);
      timer.cancel(); //Not necessary because we call System.exit
       //System.exit(0);
    }
  }
}
