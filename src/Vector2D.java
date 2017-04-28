public class Vector2D {
	public double x;
	public double y;
	public Vector2D(){ //konstruktor domyœlny
		x=0;
		y=0;
	}
	public Vector2D(double x, double y){ //konstruktor z parametrami
		this.x=x;
		this.y=y;
	}
	public Vector2D sum(Vector2D v) //suma wektorów
	{
		double xS=this.x+v.x;
		double yS=this.y+v.y;
		Vector2D sum1 = new Vector2D(xS,yS);
		return sum1;
	}
	public Vector2D diff(Vector2D v) //ró¿nica wektorów
	{
		double xD=this.x-v.x;
		double yD=this.y-v.y;
		Vector2D diff1 = new Vector2D(xD,yD);
		return diff1;
	}
	public Vector2D mult(double a) //wektor razy sta³a
	{
		double xM=this.x*a;
		double yM=this.y*a;
		Vector2D mult1 = new Vector2D(xM,yM);
		return mult1;
	}
	public double length(){ //d³ugoœæ wektora
		double l=Math.sqrt(x*x+y*y);
		return l;
	}
	public Vector2D norm() //wektor znormalizowany
	{
		double xN=this.x/length();
		double yN=this.y/length();
		Vector2D norm1 = new Vector2D(xN,yN);
		return norm1;
	}
	//konwersja wspó³rzêdnych wektora z double na int
	public int xInt() {
		int x=Math.toIntExact(Math.round(this.x));
		return x;
	}
	public int yInt() {
		int y=Math.toIntExact(Math.round(this.y));
		return y;
	}
	public void changeV(double x, double y){
		this.x=x;
		this.y=y;
	}
	public void changeV(Vector2D a){
		this.x=a.x;
		this.y=a.y;
	}
}
