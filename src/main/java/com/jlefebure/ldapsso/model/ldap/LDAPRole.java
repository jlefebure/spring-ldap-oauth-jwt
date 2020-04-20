package com.jlefebure.ldapsso.model.ldap;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

import javax.naming.Name;
import java.io.Serializable;
import java.util.List;

@Entry(objectClasses = { "groupofuniquenames", "top" })
public class LDAPRole implements Serializable {

    @Id
    private Name id;

    @Attribute(name = "cn")
    private String label;

    @Attribute(name = "uniqueMember")
    private List<Name> membersDn;


    public Name getId() {
        return id;
    }

    public void setId(Name id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Name> getMembersDn() {
        return membersDn;
    }

    public void setMembersDn(List<Name> membersDn) {
        this.membersDn = membersDn;
    }
}
