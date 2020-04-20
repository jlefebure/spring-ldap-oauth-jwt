package com.jlefebure.ldapsso.service.ldap;

import javax.naming.Name;

public class LDAPUtils {

    private LDAPUtils() {
    }


    public static void removeBaseDn(Name dn) {
        if (dn == null) {
            return;
        }
        try {
            dn.remove(0);
            dn.remove(0);
        } catch (Exception e) {
            //ignore
            e.printStackTrace();
        }
    }
}
