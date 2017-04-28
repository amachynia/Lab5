public class SimEngine {
	private double m;
	private double k;
	private double c;
	private Vector2D z; 
	private Vector2D pm = new Vector2D(0,0);
	private Vector2D pm0;
	private Vector2D g;
	private double l0;
	private double k11, k12, k13, k14, k21, k22, k23, k24;
	private Vector2D v;
	public double getM(){ //akcesor
		return m;
	}
	public double changeM(double m){ //zmiana masy
		this.m = m;
		return this.m;
	}
	public double getK(){ //akcesor
		return k;
	}
	public double changeK(double k){ //zmiana wspó³czynnika sprê¿ystoœci
		this.k = k;
		return this.k;
	}
	public double getC(){ //akcesor
		return c;
	}
	public double changeC(double c){ //zmiana wspó³czynnika t³umienia
		this.c = c;
		return this.c;
	}
	public Vector2D getZ(){ //akcesor
		return z;
	}
	public Vector2D changeZ(Vector2D z){ //zmiana punktu zaczepienia
		this.z = z;
		return this.z;
	}
	public Vector2D getPM(){ //akcesor
		return pm;
	}
	public Vector2D changePM(Vector2D pm){ //zmiana po³o¿enia masy 
		this.pm = pm;
		return this.pm;
	}public Vector2D getPM0(){ //akcesor
		return pm0;
	}
	public Vector2D changePM0(Vector2D pm0){ //zmiana po³o¿enia pocz¹tkowego masy 
		this.pm0 = pm0;
		return this.pm0;
	}
	public Vector2D getG(){ //akcesor
		return g;
	}
	public Vector2D changeG(Vector2D g){ //zmiana przyspieszenia grawitacyjnego 
		this.g = g;
		return this.g;
	}
	public double getl0(){ //akcesor
		return l0;
	}
	public double changel0(double l0){ //zmiana d³ugoœci sprê¿yny 
		this.l0 = l0;
		return this.l0;
	}
	public Vector2D getV(){ //akcesor
		return v;
	}
	public Vector2D changeV(Vector2D v){ //zmiana prêdkoœci
		this.v = v;
		return this.v;
	}
	public SimEngine(double m, double k, double c, Vector2D z, Vector2D pm, Vector2D g, double l0, Vector2D v){ //konstruktor
		this.m=m;
		this.k=k;
		this.c=c;
		this.z=z;
		this.pm=pm;
		this.g=g;
		this.l0=l0;
		this.v=v;
		//pm.x=pm0.x-z.x;
		//pm.y=z.y-pm0.y;
	}
	public SimEngine(){ //konstruktor
		this.m=2;
		this.k=3;
		this.c=0.1;
		this.z = new Vector2D(0,0);
		this.pm=new Vector2D(0,0);
		this.l0=1;
		this.v = new Vector2D(0,0);
		this.g = new Vector2D(0, -9.81);
		//pm.x=pm0.x-z.x;
		//pm.y=z.y-pm0.y;
	}
	public void sim(double t){ //obliczanie kolejnych po³o¿eñ i szybkoœci, metoda Rungego-Kutty IV rzêdu
		//System.out.println("pm x " + pm.x);
		//System.out.println("pm y " + pm.y);
		//System.out.println("v1 " + v.y);
		
		k11=t*v.y;
		k21=t*((-k/m*pm.y)-(c/m*v.y)+g.y);
		//System.out.println("k11 " + k11);
		//System.out.println("k21 " + k21);
		k12=t*(v.y+k21/2);
		k22=t*((-k/m*(pm.y+k11/2))-(c/m*(v.y+k21/2))+g.y);
		//System.out.println("k12 " + k12);
		//System.out.println("k22 " + k22);
		k13=t*(v.y+k22/2);
		k23=t*((-k/m*(pm.y+k12/2))-(c/m*(v.y+k22/2))+g.y);
		//System.out.println("k13 " + k13);
		//System.out.println("k23 " + k23);
		k14=t*(v.y+k23);
		k24=t*((-k/m*(pm.y+k13))-(c/m*(v.y+k23))+g.y);
		//System.out.println("k14 " + k14);
		//System.out.println("k24 " + k24);
		pm.y=pm.y+(k11+2*k12+2*k13+k14)/6;
		v.y=v.y+(k21+2*k22+2*k23+k24)/6;
		//System.out.println("pm " + pm.y);
		//System.out.println("v2 " + v.y);
		
		
		k11=t*v.x;
		k21=t*((-k/m*pm.x)-(c/m*v.x)+g.x);
		//System.out.println("k11 " + k11);
		//System.out.println("k21 " + k21);
		k12=t*(v.x+k21/2);
		k22=t*((-k/m*(pm.x+k11/2))-(c/m*(v.x+k21/2))+g.x);
		//System.out.println("k12 " + k12);
		//System.out.println("k22 " + k22);
		k13=t*(v.x+k22/2);
		k23=t*((-k/m*(pm.x+k12/2))-(c/m*(v.x+k22/2))+g.x);
		//System.out.println("k13 " + k13);
		//System.out.println("k23 " + k23);
		k14=t*(v.x+k23);
		k24=t*((-k/m*(pm.x+k13))-(c/m*(v.x+k23))+g.x);
		//System.out.println("k14 " + k14);
		//System.out.println("k24 " + k24);
		pm.x=pm.x+(k11+2*k12+2*k13+k14)/6;
		v.x=v.x+(k21+2*k22+2*k23+k24)/6;
		//System.out.println("pm " + pm.x);	
		
	}
	public void reset(){
		v.changeV(0,0);
		//pm.x=pm0.x-z.x;
		//pm.y=z.y-pm0.y;
		
	}
	public int conv(double a) {
		int b=Math.toIntExact(Math.round(a));
		return b;
	}
}
