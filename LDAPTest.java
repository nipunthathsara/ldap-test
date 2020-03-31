import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

/**
 * https://github.com/rksk/ldap-test
 */

public class LDAPTest {

    public static void main(String[] args) {
        String USERNAME = "john€";
        String PASSWORD = "john€";
        String SEARCH_BASE = "ou=Users,dc=wso2,dc=org";
        String LDAP_URL = "ldap://localhost:10389";
        String LDAP_REFERRAL = "follow"; //follow or ignore
        String KEYSTORE = "/home/nipun/.wum3/products/wso2is-km/5.3.0/full/wso2is-km-5.3.0/repository/resources/security/client-truststore.jks";
        String KEYSTORE_PASSWORD = "wso2carbon";
        String dn = "uid=" + USERNAME + "," + SEARCH_BASE;

        // Setup environment for authenticating

        Hashtable<String, String> environment =
                new Hashtable<String, String>();
        environment.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.ldap.LdapCtxFactory");
        environment.put(Context.PROVIDER_URL, LDAP_URL);
        environment.put(Context.SECURITY_AUTHENTICATION, "simple");
        environment.put(Context.SECURITY_PRINCIPAL, dn);
        environment.put(Context.REFERRAL, LDAP_REFERRAL);
        environment.put(Context.SECURITY_CREDENTIALS, PASSWORD);
        System.setProperty("javax.net.ssl.trustStore", KEYSTORE);
        System.setProperty("javax.net.ssl.trustStorePassword", KEYSTORE_PASSWORD);

        try {
            DirContext authContext =
                    new InitialDirContext(environment);
            System.out.println("authenticated");
            // user is authenticated
        } catch (AuthenticationException ex) {
            System.out.println("failed");
            // Authentication failed
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
}
