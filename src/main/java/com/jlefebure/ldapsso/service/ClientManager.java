package com.jlefebure.ldapsso.service;


import com.jlefebure.ldapsso.model.sso.Client;
import com.jlefebure.ldapsso.configuration.ClientsProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ClientManager implements ClientDetailsService {

    @Autowired
    private ClientsProperties clients;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientManager.class);

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        Client client = clients.getClient(clientId);
        if (client == null) {
            throw new NoSuchClientException("Client " + clientId + " does not exists");
        }

        BaseClientDetails baseClientDetails = new BaseClientDetails(client.getClientId(), null, null, null, null);
        if (client.getClientSecret() != null)
            baseClientDetails.setClientSecret(client.getClientSecret());
        if (client.getScopes() != null)
            baseClientDetails.setScope(client.getScopes());
        if (client.getRefreshTokenValidity() != 0)
            baseClientDetails.setRefreshTokenValiditySeconds(client.getRefreshTokenValidity());
        if (client.getAccessTokenValidity() != 0)
            baseClientDetails.setAccessTokenValiditySeconds(client.getAccessTokenValidity());
        if (client.getRedirectUri() != null)
            baseClientDetails.setRegisteredRedirectUri(Set.of(client.getRedirectUri()));
        if (client.getGrantTypes() != null) {
            baseClientDetails.setAuthorizedGrantTypes(client.getGrantTypes());
        }

        return baseClientDetails;
    }

    public Client getClient(String clientId) {
        return clients.getClient(clientId);
    }
}
