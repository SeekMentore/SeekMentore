import java.util.Hashtable;

import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DNSLookup {

	public static void main(String[] args) throws NamingException {
		//final String domains[] = {"schneider.com","seekmentore.com"};
		final String domains[] = {"seekmentore.com"};
		for (String domain : domains) {
			System.out.println(domain);
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put("java.naming.factory.initial",
					"com.sun.jndi.dns.DnsContextFactory");
			DirContext dirContext = new InitialDirContext(env);
			Attributes attrs = dirContext.getAttributes("schneider.com", new String[] { "TXT" });
			Attribute txt = attrs.get("TXT");
			NamingEnumeration e = txt.getAll();
			while (e.hasMore()) {
				System.out.println(e.next());
			}
		}
	}
}
