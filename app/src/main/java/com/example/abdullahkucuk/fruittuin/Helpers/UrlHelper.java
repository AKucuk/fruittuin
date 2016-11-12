package com.example.abdullahkucuk.fruittuin.Helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by abdullah.kucuk on 15-10-2016.
 */
public class UrlHelper {
    public static String getUrlContent(String url) throws Exception {
        if(url.toLowerCase().startsWith("https")) {
            return getHttpsUrlContent(url);
        }

        URL website = new URL(url);
        URLConnection connection = website.openConnection();

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    private static String getHttpsUrlContent(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        HttpsURLConnection httpsUrlConnection = (HttpsURLConnection) connection;

        SSLSocketFactory sslSocketFactory = createTrustAllSslSocketFactory();
        httpsUrlConnection.setSSLSocketFactory(sslSocketFactory);

        BufferedReader in = new BufferedReader(new InputStreamReader(httpsUrlConnection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }


    //Method for trusting all certificates
    //Taken from http://saltnlight5.blogspot.nl/2014/10/how-to-setup-custom-sslsocketfactorys.html
    //Let op: Gevoelig voor man in the middle attacks
    private static SSLSocketFactory createTrustAllSslSocketFactory() throws Exception {
        TrustManager[] byPassTrustManagers = new TrustManager[] { new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        } };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, byPassTrustManagers, new SecureRandom());
        return sslContext.getSocketFactory();
    }
}
