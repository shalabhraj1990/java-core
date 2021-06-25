package core.java.oops.inheritance;

public class Parent {
	// child can access this but according to oop's Encapsulation all fields need to
	// be private
	String name = "shalabh";

	public Parent() {
		System.out.println("this is parent constructor!!!");
	}
	
	public Parent(String msg) {
		System.out.println("parent constructor,called from "+msg);
	}

	public String getProfession() {
		return "Acting";
	}

	public String getAsset() {
		return "75 lakhs";
	}

//cannot be inherited
	private String getLove() {
		return "Love";
	}

	public static void sayHello() {
		System.out.println("Hello!!");
	}
}
