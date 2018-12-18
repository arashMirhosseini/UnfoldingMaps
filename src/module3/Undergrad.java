package module3;

public class Undergrad extends Student {
	
	public void method2() {
		System.out.println("Undergrad 2");
	}
	public static void main(String[] args) {
		Person u;
		u = new Undergrad();
		u.method1();
	}

}
