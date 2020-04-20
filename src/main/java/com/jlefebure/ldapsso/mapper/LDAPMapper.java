package com.jlefebure.ldapsso.mapper;


import com.jlefebure.ldapsso.model.ldap.LDAPPerson;
import com.jlefebure.ldapsso.model.ldap.LDAPSite;
import com.jlefebure.ldapsso.model.sso.ApplicativeUser;
import com.jlefebure.ldapsso.model.sso.Site;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface LDAPMapper {

    @Mappings({
            @Mapping(target = "label", source = "label")
    })
    Site map(LDAPSite site);

    @Mappings({
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "login", source = "login"),
            @Mapping(target = "site", source = "site"),
            @Mapping(target = "username", source = "login"),
            @Mapping(target = "email", source = "email"),
    })
    ApplicativeUser map(LDAPPerson person);
}
