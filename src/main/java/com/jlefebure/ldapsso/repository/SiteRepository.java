package com.jlefebure.ldapsso.repository;

import com.jlefebure.ldapsso.model.ldap.LDAPSite;
import org.springframework.data.ldap.repository.LdapRepository;

import java.util.List;

public interface SiteRepository extends LdapRepository<LDAPSite> {
    List<LDAPSite> findAll();
}
