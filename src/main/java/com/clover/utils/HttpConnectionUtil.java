package com.clover.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Map;

/**
 * Created by lkl on 2017/3/21.
 */
public class HttpConnectionUtil {

    private static final Logger logger =
            LoggerFactory.getLogger(HttpConnectionUtil.class);

    //发送http post请求
    public static String post(
            Map<String, String> params, String address) {

        HttpURLConnection connection = null;
        return null;
    }

    //发送https post
    public static String postWithoutCertificate(
            Map<String, String> params, String address) {

        HttpURLConnection connection = null;

        //组装参数
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Map.Entry entry : params.entrySet()) {
                sb.append(entry.getKey() + "=" + entry.getValue() + "&");
            }
            sb.deleteCharAt(sb.length()-1);
        }

        try {
            connection = buidConnection(connection, address);

            if (params!=null) {
                connection.setDoOutput(true);
                DataOutputStream out =
                        new DataOutputStream(connection.getOutputStream());
                out.write(sb.toString().getBytes(Charset.forName("UTF-8")));
                out.flush();
                out.close();
            }

            connection.connect();
            //get result
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String result = parseResponse(connection.getInputStream());
                logger.info(result);
                return result;
            } else {
                logger.info(connection.getResponseCode()
                                + " "
                                + connection.getResponseMessage());
            }

        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
        } catch (KeyManagementException e) {
            logger.error(e.getMessage());
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if(connection != null) {
                connection.disconnect();
            }
        }

        return null;
    }

    //解析response
    private static String parseResponse(InputStream is) throws IOException {
        BufferedReader bufferedReader =
                new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = "";
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }

    //构建Https connection
    private static HttpURLConnection buidConnection(
            HttpURLConnection connection, String address)
            throws NoSuchAlgorithmException,
            KeyManagementException, IOException {

        //https
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {return null;}
            public void checkClientTrusted(X509Certificate[] certs, String authType) {}
            public void checkServerTrusted(X509Certificate[] certs, String authType) {}
        }};
        // Install the all-trusting trust manager
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null,trustAllCerts,new SecureRandom());
        //ip host verify
        HostnameVerifier hv = new HostnameVerifier() {
            public boolean verify(String urlHostName, SSLSession session) {
                return urlHostName.equals(session.getPeerHost());
            }
        };
        HttpsURLConnection.setDefaultHostnameVerifier(hv);
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        //设置请求参数
        URL url = new URL(address);
        connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");// POST
        connection.setConnectTimeout(3000);
        connection.setReadTimeout(3000);

        return connection;
    }
}
