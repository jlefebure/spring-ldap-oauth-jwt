package com.jlefebure.ldapsso.configuration.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jlefebure.ldapsso.model.sso.ApplicativeUser;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class CustomJwtAccessTokenConverter extends JwtAccessTokenConverter {
    @Override
    public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication authentication = super.extractAuthentication(map);
        Authentication userAuthentication = authentication.getUserAuthentication();

        if (userAuthentication != null) {
            LinkedHashMap userDetails = (LinkedHashMap) map.get("userDetails");
            if (userDetails != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                ApplicativeUser applicativeUser = objectMapper.convertValue(userDetails, ApplicativeUser.class);
                Collection<? extends GrantedAuthority> authorities = userAuthentication.getAuthorities();
                userAuthentication = new UsernamePasswordAuthenticationToken(applicativeUser, userAuthentication.getCredentials(), authorities);
            }
        }
        return new OAuth2Authentication(authentication.getOAuth2Request(), userAuthentication);
    }
}