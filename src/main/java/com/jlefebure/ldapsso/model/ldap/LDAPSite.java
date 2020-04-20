package com.jlefebure.ldapsso.model.ldap;


import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.io.Serializable;

@Entry(base = "ou=sites", objectClasses = { "top" })
public class LDAPSite implements Serializable {

    @Id
    private Name id;

    @Attribute(name = "cn")
    private String label;

    public LDAPSite(String label) {
        this.label = label;
    }

    public LDAPSite() {
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

}
