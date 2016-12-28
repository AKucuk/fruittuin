package com.example.abdullahkucuk.fruittuin.Tasks;

import android.os.AsyncTask;

import Google.GData.Client;
import Google.GData.Spreadsheets;


import com.example.abdullahkucuk.fruittuin.Global.Auth;
import com.example.abdullahkucuk.fruittuin.Global.AuthUrlHelper;
import com.example.abdullahkucuk.fruittuin.Global.OAuth2Parameters;
import com.google.api.client.http.HttpResponse;

import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by pplom on 28-12-2016.
 */

public class AuthTask extends AsyncTask<String, Void, String> {

    private static final String REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob";
    private static final String SCOPE = "https://spreadsheets.google.com/feeds "
            + "https://docs.google.com/feeds "
            + "https://www.googleapis.com/auth/drive "
            + "https://www.googleapis.com/auth/drive.appdata "
            + "https://www.googleapis.com/auth/drive.file "
            + "https://www.googleapis.com/auth/drive.apps.readonly "
            + "https://www.googleapis.com/auth/drive.photos.readonly";

    private static final String OFFLINE_ACCESS_TYPE = "offline";

    public AuthTask() {
    }


    @Override
    protected String doInBackground(String... params) {
//        OAuth2Parameters parameter = params[0];
        String accessCode = params[0];
        return getRefreshToken(accessCode);
        return OAuthUtil.CreateOAuth2AuthorizationUrl(parameters);
        //return AuthUrlHelper.buildAuthorizationUrl(ac)
    }

    String getRefreshToken(String accessCode) throws Exception {
        OAuth2Parameters parameters = loadAuthorizationParameters();

        HttpClient client = HttpClients.createDefault();

        String url = String.format(
                "%soauth2/v3/token?client_id=%s&client_secret=%s&code=%s&grant_type=authorization_code&redirect_uri=%s",
                GOOGLE_API_HOST,
                parameters.clientId,
                parameters.clientSecret,
                accessCode,
                REDIRECT_URL
        );
        HttpPost post = new HttpPost(url);
        post.addHeader(ACCEPT_HEADER_NAME, "application/x-www-form-urlencoded");

        try {
            HttpResponse response = client.execute(post);

            JSONObject object = readJson(response);

            return object.getString("refresh_token");
        } finally {
            post.releaseConnection();
        }
    }

    OAuth2Parameters loadAuthorizationParameters() throws IOException {
        OAuth2Parameters parameters = new OAuth2Parameters();

        parameters.clientId = "mijn client id";
        parameters.clientSecret = "mijn secret";


        parameters.redirectUri = REDIRECT_URL;

        parameters.scope = SCOPE;

        parameters.accessType = OFFLINE_ACCESS_TYPE;

        parameters.responseType = "code";
        parameters.tokenType = "Bearer";
        parameters.approvalPrompt = "auto";

        return parameters;
    }


}
