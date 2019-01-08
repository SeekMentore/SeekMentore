import org.apache.commons.lang.StringUtils;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String foo = "<foo>foo</foo><foo>boo</foo>";
		String bar = StringUtils.substringBetween(foo, "<foo>", "</foo>");
		System.out.println(bar);
	}

}
