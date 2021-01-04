import java.awt.*;

import javax.swing.*;

public class CBody extends JPanel{

private String name;
private double mass;
private double x;
private double y;
private double xv;
private double yv;
private int size;
private double cxv;
private double cyv;
private double scale;
private Color color;

public CBody(String name, double mass, int xposition, int yposition, double xvelocity, double yvelocity, int size, double scale, Color color){
	this.name = name;
	this.mass = mass;
	x = xposition;
	y = yposition;
	xv = xvelocity;
	yv = yvelocity;
	this.size = size;
	this.scale = scale;
	this.color = color;
	cxv = 0;
	cyv = 0;

}

public int getX(){
	return (int) x;
}

public int getY(){
	return (int) y;
}

public double getMass(){
	return mass;
}

public String getName(){
	return name;
}

public int pixels(){
	return size;
}

public Color getColor(){
	return color;
}

public void addForce(CBody oth){
	double dx = oth.getX()*scale-x*scale;
	double dy = oth.getY()*scale-y*scale;

	if (dx == 0 && dy == 0){
		return;
	}

	double distance = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));

	double force = 0.00000000006673 * mass * oth.getMass()/Math.pow(distance, 2);
	double fx = force*dx/(Math.abs(dx)+Math.abs(dy));
	double fy = force*dy/(Math.abs(dx)+Math.abs(dy));
	cxv += (fx/mass)/scale;
	cyv += (fx/mass)/scale;

}

public void calcPosition(){
	x += xv + cxv / 2;
	y += yv + cyv / 2;
	xv += cxv;
	yv += cyv;

	cxv = 0;
	cyv = 0;
}

















}