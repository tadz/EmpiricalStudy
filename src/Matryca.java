package pracaempiryczna;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.geom.*;
import java.awt.BasicStroke;
import javax.swing.JPanel;
import java.util.*;
import java.text.SimpleDateFormat;

public class Matryca extends JPanel {
  Shape circle = new Ellipse2D.Double(100, 50, 200, 200);
  Shape square = new Rectangle2D.Double(450, 50, 300, 200);
  Polygon triangle = new Polygon(new int[] {200, 75, 325}, new int[]{350,550,550},3);
  Polygon rhomb = new Polygon(new int[] {550,450,650,750}, new int[]{350,550,550,350},4);

  Stroke stroke = new BasicStroke(2,BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,new float[] { 12, 12 }, 0);
  Stroke dashed = new BasicStroke(5,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,new float[] {1, 20},0);
  Stroke solidBold = new BasicStroke(7);
  Stroke solid = new BasicStroke(2);

  Shape figura1[] = {circle,square,circle,square};
  Polygon figura2[] = {triangle,rhomb,triangle,rhomb};
  Color kolor[] = {Color.red,Color.yellow,Color.blue,Color.green};
  Stroke ramka[] = {solidBold,solid,dashed,stroke};
  String back[] = {"images/paski.png","images/fale.png","images/kulka.png","images/kratka.png"};

  int figury[] = {0,0,0,0};
  int kolory[] = {0,0,0,0};
  int ramki[] = {0,0,0,0};
  int backi[] = {0,0,0,0};

  int i,j,nr;
  double x,y;
  String file_name;
  Date now;

  Matryca (int a, int b){
    setBackground(Color.white);
    setLayout(new BorderLayout());
    setSize(800,600);
    nr = a;
    now = new Date();
    SimpleDateFormat formatter = new SimpleDateFormat("_yyyy-MM-dd_HH-mm-ss");
    String formattedDate = formatter.format(now);
    file_name = "wyniki/Badanie" + b + "/Badanie_" + b + "_Matryca_" + nr + formattedDate + ".jpg";
    losuj();
  }
    BufferedImage buff;
    @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2a = (Graphics2D)g;
    AffineTransform transformer = new AffineTransform(); 

  if (buff==null)
  {

    int width = this.getWidth();
    int height = this.getHeight();
    buff = (BufferedImage)(this.createImage(width,height));
    Graphics2D ga = buff.createGraphics();

    ga.setBackground(Color.white);
    ga.clearRect(0, 0, 800, 600);
    ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
    ga.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);

    for (int h=0;h<4;h++){
      BufferedImage img = null;
      try {
        img = ImageIO.read(new File(back[backi[h]]));
      } catch (IOException e) {
      }

      // Create a round rectangle.
      // Create a texture rectangle the same size as the texture image.
      Rectangle2D tr = new Rectangle2D.Double(0, 0,img.getWidth(), img.getHeight());
      // Create the TexturePaint.
      TexturePaint tp = new TexturePaint(img, tr);
      // Now fill the round rectangle.

      if (figury[h] == 0) //Shape
      {
        // circle
        if (h == 1) {transformer.translate(400,0); ga.setTransform(transformer);}
        else if (h == 2) {transformer.translate(0,300); ga.setTransform(transformer);}
        else if (h == 3) {transformer.translate(400,300); ga.setTransform(transformer);}
        else {transformer.translate(0,0); ga.setTransform(transformer);}

        ga.setPaint(kolor[kolory[h]]);
        ga.fill(circle);
        ga.setPaint(tp);
        ga.fill(circle);
        ga.setPaint(Color.black);
        ga.setStroke(ramka[ramki[h]]);
        ga.draw(circle);
        
        
        if (h == 1) {transformer.translate(-400,0); ga.setTransform(transformer);}
        else if (h == 2) {transformer.translate(0,-300); ga.setTransform(transformer);}
        else if (h == 3) {transformer.translate(-400,-300); ga.setTransform(transformer);}
        else {transformer.translate(0,0); ga.setTransform(transformer);}
      }
      else  if (figury[h] == 1) //Shape
      {
        //square
        if (h == 0) {transformer.translate(-400,0); ga.setTransform(transformer);}
        else if (h == 2) {transformer.translate(-400,300); ga.setTransform(transformer);}
        else if (h == 3) {transformer.translate(0,300); ga.setTransform(transformer);}
        else {transformer.translate(0,0); ga.setTransform(transformer);}

        ga.setPaint(kolor[kolory[h]]);
        ga.fill(square);
        ga.setPaint(tp);
        ga.fill(square);
        ga.setPaint(Color.black);
        ga.setStroke(ramka[ramki[h]]);
        ga.draw(square);

        if (h == 0) {transformer.translate(400,0); ga.setTransform(transformer);}
        else if (h == 2) {transformer.translate(400,-300); ga.setTransform(transformer);}
        else if (h == 3) {transformer.translate(0,-300); ga.setTransform(transformer);}
        else {transformer.translate(0,0); ga.setTransform(transformer);}
      }
      else if (figury[h] == 2) //Polygon
      {
        // triangle
        if (h == 0) {triangle.translate(0, -300);}
        else if (h == 1) {triangle.translate(400, -300);}
        else if (h == 3) {triangle.translate(400, 0);}
        else triangle.translate(0, 0);

        ga.setPaint(kolor[kolory[h]]);
        ga.fillPolygon(triangle);
        ga.setPaint(tp);
        ga.fill(triangle);
        ga.setPaint(Color.black);
        ga.setStroke(ramka[ramki[h]]);
        ga.drawPolygon(triangle);
      }
      else if (figury[h] == 3) //Polygon
      {
        // rhomb
        if (h == 0) {rhomb.translate(-400, -300);}
        else if (h == 1) {rhomb.translate(0, -300);}
        else if (h == 2) {rhomb.translate(-400, 0);}
        else rhomb.translate(0, 0);

        ga.setPaint(kolor[kolory[h]]);
        ga.fillPolygon(rhomb);
        ga.setPaint(tp);
        ga.fill(rhomb);
        ga.setPaint(Color.black);
        ga.setStroke(ramka[ramki[h]]);
        ga.drawPolygon(rhomb);
      }
    }
    ga.setPaint(Color.black);
    ga.setStroke(solid);
    ga.drawLine(0,300,800,300);
    ga.drawLine(400,0,400,600);
    ga.dispose();
  }
    //RenderedImage rendImage = buff;
    try {
      File file = new File(file_name);
      ImageIO.write(buff, "jpg", file);
    } catch (IOException e) {
    }

    //g2a.translate(-800, -600);
    g2a.drawImage(buff, null, 0,0);

    }

    public void losuj(){
    Random random = new Random();
    //losujemy kolejność figur
    i=0; j=0;
    while (j < 4)
    {
      i = showRandomInteger(0, 3, random);
      if (j == 0) {figury[j] = i; j++;}
      else if (j == 1 && figury[j-1] != i) {figury[j] = i; j++;}
      else if (j == 2 && figury[j-1] != i && figury[j-2] != i) {figury[j] = i; j++;}
      else if (j == 3 && figury[j-1] != i && figury[j-2] != i && figury[j-3] != i) {figury[j] = i; j++;}
    }
    //losujemy kolejność kolorow
    i=0; j=0;
    while (j < 4)
    {
      i = showRandomInteger(0, 3, random);
      if (j == 0) {kolory[j] = i; j++;}
      else if (j == 1 && kolory[j-1] != i) {kolory[j] = i; j++;}
      else if (j == 2 && kolory[j-1] != i && kolory[j-2] != i) {kolory[j] = i; j++;}
      else if (j == 3 && kolory[j-1] != i && kolory[j-2] != i && kolory[j-3] != i) {kolory[j] = i; j++;}
    }
    //losujemy kolejność ramek
    i=0; j=0;
    while (j < 4)
    {
      i = showRandomInteger(0, 3, random);
      if (j == 0) {ramki[j] = i; j++;}
      else if (j == 1 && ramki[j-1] != i) {ramki[j] = i; j++;}
      else if (j == 2 && ramki[j-1] != i && ramki[j-2] != i) {ramki[j] = i; j++;}
      else if (j == 3 && ramki[j-1] != i && ramki[j-2] != i && ramki[j-3] != i) {ramki[j] = i; j++;}
    }
    //losujemy kolejność backow
    i=0; j=0;
    while (j < 4)
    {
      i = showRandomInteger(0, 3, random);
      if (j == 0) {backi[j] = i; j++;}
      else if (j == 1 && backi[j-1] != i) {backi[j] = i; j++;}
      else if (j == 2 && backi[j-1] != i && backi[j-2] != i) {backi[j] = i; j++;}
      else if (j == 3 && backi[j-1] != i && backi[j-2] != i && backi[j-3] != i) {backi[j] = i; j++;}
    }

    }

    private static int showRandomInteger(int aStart, int aEnd, Random aRandom){
    if ( aStart > aEnd ) {
      throw new IllegalArgumentException("Start cannot exceed End.");
    }
    //get the range, casting to long to avoid overflow problems
    long range = (long)aEnd - (long)aStart + 1;
    // compute a fraction of the range, 0 <= frac < range
    long fraction = (long)(range * aRandom.nextDouble());
    int randomNumber =  (int)(fraction + aStart);
    return randomNumber;
  }
}
