
public class klTest {
	public static void main(String[] args) {
		Vector2D a = new Vector2D(5,10);
		Vector2D b = new Vector2D(-2,-5);
		Vector2D sumAB = new Vector2D();
		sumAB = a.sum(b);
		Vector2D diffAB = new Vector2D();
		diffAB = a.diff(b);
		Vector2D normA = new Vector2D();
		normA = a.norm();
		Vector2D normB = new Vector2D();
		normB = b.norm();
		double lA;
		lA = a.length();
		double lB;
		lB = b.length();
		Vector2D c = new Vector2D();
		c = a.mult(5);
		
		System.out.println("Wektor a: x: " + a.x + " y: " + a.y);
		System.out.println("Wektor b: x: " + b.x + " y: " + b.y);
		System.out.println("Suma x: " + sumAB.x + " y: " + sumAB.y);
		System.out.println("Ró¿nica x: " + diffAB.x + " y: " + diffAB.y);
		System.out.println("Norma z wektora a: " + normA.x + " y: " + normA.y);
		System.out.println("Norma z wektora b: " + normB.x + " y: " + normB.y);
		System.out.println("D³ugoœæ wektora a: " + lA);
		System.out.println("D³ugoœæ wektora b: " + lB);
		System.out.println("Wektor a pomno¿ony przez sta³¹ równ¹ 5: x: " + c.x + " y: " + c.y);
	}
}
