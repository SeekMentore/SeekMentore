import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * Demonstrates how to retrieve all attributes of a named object.
 * 
 * usage: java GetAllAttrs
 */
class GetAllAttrs {
  static void printAttrs(Attributes attrs) {
    if (attrs == null) {
      System.out.println("No attributes");
    } else {
      /* Print each attribute */
      try {
        for (NamingEnumeration ae = attrs.getAll(); ae.hasMore();) {
          Attribute attr = (Attribute) ae.next();
          System.out.println("attribute: " + attr.getID());

          /* print each value */
          for (NamingEnumeration e = attr.getAll(); e.hasMore(); System.out
              .println("value: " + e.next()))
            ;
        }
      } catch (NamingException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {

    // Set up the environment for creating the initial context
    Hashtable<String, Object> env = new Hashtable<String, Object>(11);
    env
        .put(Context.INITIAL_CONTEXT_FACTORY,
            "com.sun.jndi.ldap.LdapCtxFactory");
    env.put(Context.PROVIDER_URL, "ldaps://xldap2.int.omnitracs.com:636/cn=roadminx,o=services");

    try {
      // Create the initial context
      DirContext ctx = new InitialDirContext(env);

      // Get all the attributes of named object
      Attributes answer = ctx.getAttributes("cn=adamatspeedy@gmail.com,ou=people,ou=unity,ou=xomni,o=consumer,dc=omnitracs,dc=com");

      // Print the answer
      printAttrs(answer);

      // Close the context when we're done
      ctx.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}