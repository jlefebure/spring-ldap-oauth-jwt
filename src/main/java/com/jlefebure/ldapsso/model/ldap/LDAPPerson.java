package com.jlefebure.ldapsso.model.ldap;


import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;
import org.springframework.ldap.odm.annotations.Transient;

import javax.naming.Name;

@Entry(base = "ou=persons,dc=jlefebure,dc=com", objectClasses = { "person" })
public class LDAPPerson {

    @Id
    private Name id;

    @Attribute(name = "uid")
    private String login;

    @Attribute(name = "locked")
    private String isLocked;

    @Attribute(name = "mail")
    private String email;

    @Attribute(name = "sn")
    private String lastName;

    // Others fields ...
    @Attribute(name = "site")
    private Name siteDn;

    @Transient
    private LDAPSite site;

    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getIsLocked() {
        return isLocked;
    }

    public void setIsLocked(String isLocked) {
        this.isLocked = isLocked;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Name getSiteDn() {
        return siteDn;
    }

    public void setSiteDn(Name siteDn) {
        this.siteDn = siteDn;
    }

    public LDAPSite getSite() {
        return site;
    }

    public void setSite(LDAPSite site) {
        this.site = site;
    }
}
