import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener{
    private SimEngine SimEngine1;
    private SimTask SimTask1;
    private Timer Timer1;
    private int height=900;
    private int width=1200;
    public int scale=20;
    private boolean mouseDragging;
    private int x;
    private int y;
    Vector2D z = new Vector2D();
	private Button resetButton;
	private TextField mField;
	private Label mLabel;
	private TextField kField;
	private Label kLabel;
	private TextField cField;
	private Label cLabel;
	private TextField gField;
	private Label gLabel;
	private TextField l0Field;
	private Label l0Label;
		
	@Override
    public void mouseClicked(MouseEvent e){
   	 
    }
	@Override
    public void mouseEntered(MouseEvent e){
   	 
    }
	@Override
    public void mouseExited(MouseEvent e){
   	 
    }
	@Override
    public void mousePressed(MouseEvent e){
    	x = e.getX();
    	y = e.getY();
    	//Sprawdzenie czy klikniêto w masê (lub bardzo blisko)
    	if(SimEngine1.getZ().xInt()+SimEngine1.getPM().xInt()*scale-scale < x
    			&& x < SimEngine1.getZ().xInt()+SimEngine1.getPM().xInt()*scale+scale
    			&& SimEngine1.getZ().yInt()-SimEngine1.getPM().yInt()*scale-scale < y
    			&& y < SimEngine1.getZ().yInt()-SimEngine1.getPM().yInt()*scale+scale){
    		//Zerowanie timera i symulacji
    		Timer1.cancel();
    		SimEngine1.reset();
    		mouseDragging = true;
    	}
    	e.consume();
    }
	@Override
    public void mouseReleased(MouseEvent e){
    	int x1 = e.getX();
    	int y1 = e.getY();
    	//Wywo³anie nowego timera (i symulacji) jeœli przeci¹gniêto mysz po wciœniêciu  w obêbie masy
    	if(x1 != x && y1 != y && mouseDragging){
    		Timer1 = new Timer();
    		SimTask1 = new SimTask(SimEngine1, this, 0.01);
    		Timer1.scheduleAtFixedRate(SimTask1, 0, 10);
    		mouseDragging = false;
    	}
    	e.consume();
    }
	@Override
    public void mouseDragged(MouseEvent e){
    	//Jeœli nastêpuje przei¹ganie kursora
    	if(mouseDragging){
    		//Pobranie aktualnej pozycji kursora
    		Vector2D npm1 = new Vector2D(e.getX(), e.getY());
    		Vector2D npm2 = new Vector2D();
    		//System.out.println("Wektor npm: x: " + npm1.x + " y: " + npm1.y);
    		//Przeliczenie nowych wspó³rzêdnych w skali
    		npm2.x=(npm1.x-z.x)/scale;
    		npm2.y=(z.y-npm1.y)/scale;
    		//Zmiana po³o¿enia masy
    		SimEngine1.changePM(npm2);
    		repaint();
    	}
    	e.consume();
    }
	
	@Override
    public void mouseMoved(MouseEvent e){
   	 
    }
    @Override
    public void actionPerformed(ActionEvent e){
    	Object source = e.getSource();
    	if(source == this.resetButton){
			Timer1.cancel();
    		SimEngine1.reset();
    		//Pobranie nowych wartoœci i zmiana parametrów symulacji, jeœli pola nie s¹ puste
    		if(! mField.getText().equals("")) {
    			SimEngine1.changeM(Double.valueOf(mField.getText()));	
    		}
    		if(! kField.getText().equals("")) {
    			SimEngine1.changeK(Double.valueOf(kField.getText()));	
    		}
    		if(! cField.getText().equals("")) {
    			SimEngine1.changeC(Double.valueOf(cField.getText()));	
    		}
    		if(! gField.getText().equals("")) {
    			Vector2D gNew = new Vector2D(0, Double.valueOf(gField.getText()));
        		SimEngine1.changeG(gNew);
        		}
    		if(! l0Field.getText().equals("")) {
    			SimEngine1.changel0(Double.valueOf(l0Field.getText()));	
    		}
    		//Pocz¹tkowe ustawienie masy
    		Vector2D PMreset = new Vector2D(0, 0);
    		SimEngine1.changePM(PMreset);
    		Timer1 = new Timer();
    		SimTask1 = new SimTask(SimEngine1, this, 0.01);
    		Timer1.scheduleAtFixedRate(SimTask1, 0, 10);
		}
    }
    
    @Override
    public void init()
    {    
    	mouseDragging = false;
	   	addMouseListener(this);
	   	addMouseMotionListener(this);
	   	//Inicjalizacja przycisku, pól tekstowych, etykiet nowymi obiektami
		resetButton = new Button("Reset");
		mLabel = new Label("Masa: ");
		mField = new TextField(6);
		kLabel = new Label("Wspó³czynnik sprê¿ystoœci: ");
		kField = new TextField(6);
		cLabel = new Label("Wspó³czynnik t³umienia: ");
		cField = new TextField(6);
		gLabel = new Label("Przyspieszenie grawitacyjne: ");
		gField = new TextField(6);
		l0Label = new Label("D³ugoœæ sprê¿yny: ");
		l0Field = new TextField(6);
		
		this.setLayout(new BorderLayout());
		//Utworzenie panelu
		Container bContainer = new Container();
		bContainer.setLayout(new FlowLayout());
		//Dodanie elementów GUI do panelu
		bContainer.add(resetButton);
		bContainer.add(mLabel);
		bContainer.add(mField);
		bContainer.add(kLabel);
		bContainer.add(kField);
		bContainer.add(cLabel);
		bContainer.add(cField);
		bContainer.add(gLabel);
		bContainer.add(gField);
		bContainer.add(l0Label);
		bContainer.add(l0Field);
		//Dodanie panelu do apletu
		this.add(bContainer, BorderLayout.SOUTH);
		
		//Dodanie "nas³uchiwacza" do obiektu reprezentuj¹cego przycisk
		resetButton.addActionListener(this);
		
	   	Vector2D z1 = new Vector2D(600,200);
	   	z.changeV(z1);
	   	Vector2D pm1 = new Vector2D(600,200);
	   	Vector2D pm2 = new Vector2D();
	   	
	   	pm2.x=(pm1.x-z1.x)/scale;
		pm2.y=(z1.y-pm1.y)/scale;
	   	
	   	Vector2D g1 = new Vector2D(0,-9.81);
	   	Vector2D v1 = new Vector2D(0,0);
	   	SimEngine1= new SimEngine(2, 3, 0.1, z, pm2, g1, 1, v1);
	   	SimTask1 = new SimTask(SimEngine1, this, 0.01);
	   	Timer1 = new Timer();
	   	Timer1.scheduleAtFixedRate(SimTask1, 0, 10);
	   	setBackground(Color.white);
	   	setSize(width, height);
	   	setVisible(true);
    }
    
    @Override
    public void paint(Graphics g)
    {
    	//Podwójne buforowanie: utworzenie obrazu przed wyœwietleniem
    	Graphics offg;
    	Image offscreen = null;
    	offscreen = createImage(width, height-50);
    	offg = offscreen.getGraphics();
   	 	offg.setColor(Color.black); //Ustawienie koloru
	   	offg.drawLine(SimEngine1.getZ().xInt()-3*scale, SimEngine1.getZ().yInt(), SimEngine1.getZ().xInt()+3*scale, SimEngine1.getZ().yInt());
	   	offg.drawLine(SimEngine1.getZ().xInt(), SimEngine1.getZ().yInt(), SimEngine1.getZ().xInt()+SimEngine1.getPM().xInt()*scale, SimEngine1.getZ().yInt()-SimEngine1.getPM().yInt()*scale);
	   	offg.fillOval(SimEngine1.getZ().xInt()+SimEngine1.getPM().xInt()*scale-scale, SimEngine1.getZ().yInt()-SimEngine1.getPM().yInt()*scale-scale, 2*scale, 2*scale);
	   	//Wyœwietlenie gotowego obrazu
	   	g.drawImage(offscreen, 0, 0, this);
    }
   
    
}


