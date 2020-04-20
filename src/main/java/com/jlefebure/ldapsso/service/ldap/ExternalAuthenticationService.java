package com.jlefebure.ldapsso.service.ldap;


import com.jlefebure.ldapsso.model.ldap.LDAPPerson;
import com.jlefebure.ldapsso.model.sso.ApplicativeUser;
import com.jlefebure.ldapsso.model.sso.Client;
import com.jlefebure.ldapsso.configuration.ClientsProperties;
import com.jlefebure.ldapsso.mapper.LDAPMapper;
import com.jlefebure.ldapsso.repository.PersonRepository;
import com.jlefebure.ldapsso.repository.SiteRepository;
import com.jlefebure.ldapsso.service.AuthenticationService;
import com.jlefebure.ldapsso.service.ClientManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;

import javax.naming.Name;

@Service
public class ExternalAuthenticationService implements AuthenticationService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SiteRepository siteRepository;

    @Autowired
    private LDAPMapper ldapMapper;

    @Autowired
    private ClientsProperties clientsProperties;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Autowired
    private ClientManager clientManager;

    @Override
    public ApplicativeUser authenticateUser(String username, String password, String appName) {
        Client clientDetails = clientManager.getClient(appName);

        LDAPPerson person = personRepository.findByLogin(username);

        if (person.getIsLocked() != null && !person.getIsLocked().isBlank() && !person.getIsLocked().equals("0")) {
            throw new LockedException("Your account is locked.");
        }

        if (!clientsProperties.isDegrade()) {
            boolean authenticate = ldapTemplate.authenticate(person.getId(), "(uid=" + username + ")", password);
            if (!authenticate) {
                throw new BadCredentialsException("Authentication failed. Please check your login or password");
            }
        }
        getSite(person);

        return this.ldapMapper.map(person);
    }


    private void getSite(LDAPPerson byLogin) {
        //map site
        Name siteDn = byLogin.getSiteDn();

        siteRepository
                .findById(siteDn)
                .ifPresent(byLogin::setSite);
    }

}
