import java.awt.*;
import javax.swing.JApplet;

public class wektorApplet extends JApplet {
	public int arrowX=5;
	public int arrowY=10;
	public int oX=500;
	public int oY=450;
	public int axisL=400;
	public int axisScale=20;
	public int axisNumb=axisL/axisScale;
	public int axisScaleL=10;
	public int axisMarkDist=30;
	
	public int xF(Vector2D v) //metoda zwracaj¹ca wspó³rzêdn¹ X wektora w skali
	{
		int xF=oX+v.xInt()*axisScale;
		return xF;
	}
	public int yF(Vector2D v) //metoda zwracaj¹ca wspó³rzêdn¹ Y wektora w skali
	{
		int yF=oY-v.yInt()*axisScale;
		return yF;
	}
	
	public void drawVector(Vector2D v, Graphics g){ //metoda rysuj¹ca wektor, okr¹g jest zwrotem wektora
		g.drawLine(oX, oY, xF(v), yF(v));
		g.drawOval(xF(v)-5, yF(v)-5,10,10);
	}
	public void signVector(Vector2D v, Graphics g){ //metoda podpisuj¹ca wektor
		g.drawString("[" + v.x + "," + v.y + "]", oX+v.xInt()*axisScale/2+5, oY-v.yInt()*axisScale/2+5);
	}
	public void init()
	{
		setBackground(Color.white);
		setSize(1200, 900);
	}
	public void paint(Graphics g)
	{
		g.setColor(Color.black); //ustawienie koloru
		//Rysowanie uk³adu wspó³rzêdnych
		g.drawLine(oX-axisL, oY, oX+axisL, oY);
		g.drawLine(oX, oY+axisL, oX, oY-axisL);
		g.drawLine(oX, oY-axisL, oX-arrowX, oY-axisL+arrowY);
		g.drawLine(oX, oY-axisL, oX+arrowX, oY-axisL+arrowY);
		g.drawLine(oX+axisL, oY, oX+axisL-arrowY, oY-arrowX);
		g.drawLine(oX+axisL, oY, oX+axisL-arrowY, oY+arrowX);
		
		// Podzia³ka osi
		for(int i = 1; i < axisNumb; i++) {
			g.drawLine(oX + (i * axisScale), oY - axisScaleL, oX + (i * axisScale), oY + axisScaleL);
			g.drawString(Integer.toString(i), oX + (i * axisScale) - 3, oY + axisMarkDist);
			g.drawLine(oX - axisScaleL, oY - (i * axisScale), oX + axisScaleL, oY - (i * axisScale));
			g.drawString(Integer.toString(i), oX - axisMarkDist - 3, oY - (i * axisScale));
			g.drawLine(oX - (i * axisScale), oY - axisScaleL, oX - (i * axisScale), oY + axisScaleL);
			g.drawLine(oX - axisScaleL, oY + (i * axisScale), oX + axisScaleL, oY + (i * axisScale));
		  }  
		for(int i = 2; i < axisNumb; i++) {
			g.drawString("-"+Integer.toString(i), oX - (i * axisScale) - 3, oY + axisMarkDist);
			g.drawString("-"+Integer.toString(i), oX - axisMarkDist - 3, oY + (i * axisScale));
		  }  
		
		//Opis osi
		g.drawString("X", oX + axisL, oY + axisMarkDist/2);
		g.drawString("Y", oX - axisMarkDist/2, oY - axisL );
		g.drawString("(0, 0)", oX - axisMarkDist/2, oY + axisMarkDist/2);
		
		//Utworzenie i narysowanie ró¿nych wektorów
		g.setColor(Color.green);
		Vector2D a = new Vector2D(5,10);
		drawVector(a, g);
		signVector(a, g);
		
		Vector2D b = new Vector2D(-8,-6);
		drawVector(b, g);
		signVector(b, g);
		
		Vector2D sumAB = new Vector2D();
		sumAB = a.sum(b);
		Vector2D diffAB = new Vector2D();
		diffAB = a.diff(b);
		Vector2D c = new Vector2D();
		c = b.mult(2);
		g.setColor(Color.red);
		drawVector(sumAB, g);
		signVector(sumAB, g);
		g.setColor(Color.blue);
		drawVector(diffAB, g);
		signVector(diffAB, g);
		g.setColor(Color.gray);
		drawVector(c, g);
		signVector(c, g);
	}
}
