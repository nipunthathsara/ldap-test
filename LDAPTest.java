import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

/**
 * https://github.com/rksk/ldap-test
 */

public class LDAPTest {

    /**
     * This is to check if a tenant user exist in the primary LDAP.
     * @param args
     */
    public static void main(String[] args) {

        String CONNECTION_NAME = "uid=admin,ou=system";
        String PASSWORD = "admin";
        String LDAP_URL = "ldap://localhost:10389";
        String TENANT_SEARCH_BASE = "ou=users,ou=abc.com,dc=wso2,dc=org";
        String TENANT_USER = "admin";
        String LDAP_REFERRAL = "follow";

        // Setup environment for authenticating
        Hashtable<String, String> environment =
                new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, LDAP_URL);
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, CONNECTION_NAME);
        environment.put(Context.REFERRAL, LDAP_REFERRAL);
        environment.put(Context.SECURITY_CREDENTIALS, PASSWORD);

        try {
            // Authenticating
            DirContext authContext =
                    new InitialDirContext(environment);
            System.out.println("authenticated");
            // Searching
            SearchControls searchControls = new SearchControls();
            searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);
            System.out.println("Searching for : + " + TENANT_USER + " in the : " + TENANT_SEARCH_BASE);
            NamingEnumeration results = authContext.search(TENANT_SEARCH_BASE, "(&(objectClass=person)(uid=" + TENANT_USER + "))", searchControls);
            if(!results.hasMore()) {
                System.out.println("User not found.");
            }
            while (results.hasMore()) {
                SearchResult sr = (SearchResult) results.next();
                Attributes attrs = sr.getAttributes();
                System.out.println("User found : " + attrs.get("uid"));
            }
        } catch (AuthenticationException ex) {
            System.out.println("LDAP authentication failed");
            ex.printStackTrace();
            // Authentication failed
        } catch (NamingException ex) {
            System.out.println("Error occurred.");
            ex.printStackTrace();
        }
    }
}
