package com.jlefebure.ldapsso.repository;

import com.jlefebure.ldapsso.model.ldap.LDAPPerson;
import org.springframework.data.ldap.repository.LdapRepository;

public interface PersonRepository extends LdapRepository<LDAPPerson> {
    LDAPPerson findByLogin(String login);
}
