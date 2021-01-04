import java.awt.*;

import javax.swing.*;

import java.util.List;

import java.util.LinkedList;

import java.util.ArrayList;

import java.io.File;

import java.io.FileNotFoundException;

import java.util.Scanner;

// @SupressWarnings("serial");
public class NBody extends JPanel{
    // public static final int SPACE_WIDTH = 400;
    // public static final int SPACE_HEIGHT = 140;
    // public static final Color CANVAS_BACKROUND = Color.BLACK;
    // private static final long serialVersionUID = 0L;
    // private static Space space;

    //public NBody() {
        // space = new Space();
        // space.setPreferredSize(new Dimension(SPACE_WIDTH, SPACE_HEIGHT));
    //}
    List<CBody> cbodies;

    NBody (String filename) throws FileNotFoundException{
    	File file = new File(filename);
    	Scanner filein = new Scanner(file);
    	String datatype = filein.nextLine().trim();
    	if (datatype.equals("ArrayList")){
    		cbodies = new ArrayList<>();
    	} else if (datatype.equals("LinkedList")){
    		cbodies = new LinkedList<>();
    	}

    	double scale = Double.parseDouble(filein.nextLine().trim());

    	while (filein.hasNextLine()){
    		String[] data = filein.nextLine().split(",", 0);
            for (int i = 0; i<data.length; i++){
                System.out.println(data[i]);
            }
    		cbodies.add(new CBody(data[0].trim(), Double.parseDouble(data[1].trim()), Integer.parseInt(data[2].trim()),
    			Integer.parseInt(data[3].trim()), Double.parseDouble(data[4].trim()), Double.parseDouble(data[5].trim()),
    				Integer.parseInt(data[6].trim()), scale,
    				new Color((100 + Integer.parseInt(data[3].trim()))%156,
    					(100+Integer.parseInt(data[2].trim()))%156,
    					(100+(Integer.parseInt(data[2].trim())+Integer.parseInt(data[3].trim()))%156)) ));
    	}
    	filein.close();
    }


    // public void paintComponent(Graphics g)
    // {
    //     super.paintComponent(g);
    //     g.setColor(Color.RED);
    //     g.fillRect(100, 10, 30, 40);
    // }

    private void move() {
      for (CBody cbody: cbodies){
      	for (CBody other: cbodies){
      		if (cbody != other){
      			cbody.addForce(other);
      		}
      	}
      }
     for (CBody cbody: cbodies){
     	cbody.calcPosition();
     }


    }

    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    	for (CBody cbody: cbodies){
    		if (cbody.getX() < 0 || cbody.getX() >= 768 || cbody.getY() < 0 || cbody.getY()>= 768){
    			continue;
    		}
    		g.setColor(cbody.getColor());
    		g2d.fillOval(cbody.getX(), cbody.getY(), cbody.pixels(), cbody.pixels());
    	}
    }

    public static void main(String[] args) throws InterruptedException, FileNotFoundException
    {
        JFrame frame = new JFrame("N Bodies");
        NBody sim = new NBody("CBodiesText.txt");
        frame.add(sim);
        frame.setSize(768,768);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        while(true) {
            sim.move();
            sim.repaint();
            Thread.sleep(1L);
        }
        // SwingUtilities.invokeLater(new Runnable(){
        //    @Override
        //    public void run() {
        //        new NBody();
        //    } 
        // });
    //     NBody t = new​ NBody();

    //     JFrame jf​ = new​ JFrame();

    //     jf​.setTitle(​"Tutorial"​);
    //     jf​.setSize(​t​.​maxX​, ​t​.​maxY​); ​// Window size defined in the class jf​.add(​t​); ​// This appears below "setVisible" in the video jf​.setVisible(​true​); jf​.setDefaultCloseOperation(JFrame.​EXIT_ON_CLOSE)​;
    //     jf.setVisible(true);
    //     jf.setDefaultCloseOperation(JFrame.​EXIT_ON_CLOSE);
    //     jf.add(t);
    }





}