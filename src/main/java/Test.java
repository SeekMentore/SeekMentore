
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		FuncInterface fobj = (int x)->System.out.println(2*x); 
		  
        // This calls above lambda expression and prints 10. 
        fobj.abstractFun(5); 
        fobj.normalFun(); 
	}

}

interface FuncInterface 
{ 
    // An abstract function 
    void abstractFun(int x); 
  
    // A non-abstract (or default) function 
    default void normalFun() 
    { 
       System.out.println("Hello"); 
    } 
} 
