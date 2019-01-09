import org.apache.commons.lang.StringUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String foo = "$foo     $   $foo     $           $boo$";
		//String bar = StringUtils.substringBetween(foo, "$", "$");
		System.out.println(StringUtils.substringsBetween(foo, "$", "$"));
		String bara[] = StringUtils.substringsBetween(foo, "$", "$");
		//System.out.println(bar+"A");
		System.out.println(bara.length);
		for (String b:bara) {
			System.out.println(b+"A");
		}
		System.out.println(foo.replaceAll("\\$foo     \\$", "Hello"));
	}

}
