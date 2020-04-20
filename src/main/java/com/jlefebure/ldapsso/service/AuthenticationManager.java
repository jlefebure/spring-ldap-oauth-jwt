package com.jlefebure.ldapsso.service;


import com.jlefebure.ldapsso.model.sso.ApplicativeUser;
import com.jlefebure.ldapsso.service.ldap.ExternalAuthenticationService;
import io.micrometer.core.instrument.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.exceptions.InvalidScopeException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationManager implements org.springframework.security.authentication.AuthenticationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationManager.class);

    @Autowired
    private ExternalAuthenticationService externalAuthenticationService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getPrincipal() + "";
        String password = authentication.getCredentials() + "";
        String scope = ((Map<String, String>) authentication.getDetails()).get("scopes");
        if (scope == null) {
            throw new InvalidScopeException("Scope is not valid");
        }
        String appName = getClientId();
        if (appName == null || appName.isEmpty()) {
            throw new UsernameNotFoundException("Client id is null or empty");
        }

        ApplicativeUser applicativeUser;
        switch (scope) {
            case "scope1":
                applicativeUser = externalAuthenticationService.authenticateUser(username, password, appName);
                break;
            //Your other custom scope
            default:
                throw new InvalidScopeException("Scope " + scope + " is invalid. Allowed scopes are scope1, ...");
        }

        return new UsernamePasswordAuthenticationToken(applicativeUser, password, applicativeUser.getAuthorities());
    }

    private String getClientId() {
        final HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        final String authorizationHeaderValue = request.getHeader("Authorization");
        final String base64AuthorizationHeader = Optional.ofNullable(authorizationHeaderValue)
                .map(headerValue -> headerValue.substring("Basic ".length())).orElse("");

        if (StringUtils.isNotEmpty(base64AuthorizationHeader)) {
            String decodedAuthorizationHeader = new String(Base64.getDecoder().decode(base64AuthorizationHeader), StandardCharsets.UTF_8);
            return decodedAuthorizationHeader.split(":")[0];
        }

        return "";
    }


}
