# ldap-test

A Java client to view the time spent for creating LDAP connections and querying. In the meantime, it can be used to view the results for a given query. 

##### 1. You have to configure following parameters. Refer to you USER_STORE configurations XML file for the information needed.

            String USERNAME = "john€";
            String PASSWORD = "john€";
            String SEARCH_BASE = "ou=Users,dc=wso2,dc=org";
            String LDAP_URL = "ldap://localhost:10389";
            String LDAP_REFERRAL = "follow"; //follow or ignore
            String KEYSTORE = "/home/nipun/.wum3/products/wso2is-km/5.3.0/full/wso2is-km-5.3.0/repository/resources/security/client-truststore.jks";
            String KEYSTORE_PASSWORD = "wso2carbon";
        
##### 2. Compile the LDAPTest.java using javac.
```javac LDAPTest.java```

##### 3. Run the test
Some examples on specifying the parameters.
```
java LDAPTest
```

---
Derived initial version from http://soasecurity.org and improved later.
