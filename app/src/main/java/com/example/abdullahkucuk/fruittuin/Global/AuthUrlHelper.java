package com.example.abdullahkucuk.fruittuin.Global;

/**
 * Created by pplom on 28-12-2016.
 */

public class AuthUrlHelper {

    final static String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    private static final String OAUTHSCOPEKEY = "scope";
    private static final String OAUTH2CLIENTID = "client_id";
    private static final String OAUTH2REDIRECTURI = "redirect_uri";
    private static final String OAUTH2ACCESSTYPE = "access_type";
    private static final String OAUTH2RESPONSETYPE = "response_type";
    private static final String OAUTH2APPROVALPROMPT = "approval_prompt";


    public static String buildAuthorizationUrl(OAuth2Parameters parameters) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AUTH_URL);

        stringBuilder.append(String.format("?%s=%s", OAUTH2RESPONSETYPE, parameters.responseType));
        stringBuilder.append(String.format("&%s=%s", OAUTH2CLIENTID, parameters.clientId));
        stringBuilder.append(String.format("&%s=%s", OAUTH2REDIRECTURI, parameters.redirectUri));
        stringBuilder.append(String.format("&%s=%s", OAUTHSCOPEKEY, parameters.scope));
        stringBuilder.append(String.format("&%s=%s", OAUTH2ACCESSTYPE, parameters.accessType));
        stringBuilder.append(String.format("&%s=%s", OAUTH2APPROVALPROMPT, parameters.approvalPrompt));

        return stringBuilder.toString();
    }

}
