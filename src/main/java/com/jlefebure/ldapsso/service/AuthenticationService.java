package com.jlefebure.ldapsso.service;

import com.jlefebure.ldapsso.model.sso.ApplicativeUser;

/**
 * Interface to be extended by authentication service.
 */
public interface AuthenticationService {
    /**
     * Handle the authentication, and return an applicative user if successful. Otherwise,
     * this method should throw an exception to handle other cases (account locked, disabled, non existing,
     * bad credentials, ...
     * @param username The username
     * @param password The clear text password
     * @param appName The appname, basically the client ID which has been sent by the server
     * @return An applicative user if the authentication is successful
     */
    ApplicativeUser authenticateUser(String username, String password, String appName);
}
