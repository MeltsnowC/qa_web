package com.bingo.qa.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author wangfeng78/AbeanCC
 * @date 2021/12/21 15:39
 */
@Slf4j
public class HttpUtils {

    private MyTrustManager mMyTrustManager;

    public static String doPost(String url, String body, String cookie) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request postRequest =
                    new Request.Builder()
                            .url(url)
                            .method("POST", requestBody)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/json")
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doMyPost(String url,String body,String cookie){
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType,body);
            Request postRequst = new Request.Builder()
                    .url(url)
                    .method("POST",requestBody)
                    .addHeader("Cookie",cookie)
                    .addHeader("Content-Type","application/json")
                    .build();
            Response response = httpClient.newCall(postRequst).execute();
            if (response.isSuccessful()){
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}",e.toString());
        }
        return result;
    }

    public static String doFormPost(String url, String body, String cookie) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request postRequest =
                    new Request.Builder()
                            .url(url)
                            .method("POST", requestBody)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/x-www-form-urlencoded")
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doPost(String url, Object body, String cookie) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(body), mediaType);
            Request postRequest =
                    new Request.Builder()
                            .url(url)
                            .method("POST", requestBody)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/json")
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doPost(String url, Object body, String cookie, String xBusilineId) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(body), mediaType);
            Request postRequest =
                    new Request.Builder()
                            .url(url)
                            .method("POST", requestBody)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("X-Busilineid", xBusilineId)
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }


    public static String doPostNau(String url, Object body, String authorization) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(body), mediaType);
            Request postRequest =
                    new Request.Builder()
                            .url(url)
                            .method("POST", requestBody)
                            .addHeader("Authorization", authorization)
                            .addHeader("Content-Type", "application/json")
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doPut(String url, Object body, String cookie) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(body), mediaType);
            Request putRequest =
                    new Request.Builder()
                            .url(url)
                            .method("PUT", requestBody)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/json")
                            .build();
            Response response = httpClient.newCall(putRequest).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doPutNau(String url, Object body, String authorization) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(body), mediaType);
            Request putRequest =
                    new Request.Builder()
                            .url(url)
                            .method("PUT", requestBody)
                            .addHeader("Authorization", authorization)
                            .addHeader("Content-Type", "application/json")
                            .build();
            Response response = httpClient.newCall(putRequest).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doPut(String url, String body, String cookie) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(JSON.parseObject(body)), mediaType);
            Request putRequest =
                    new Request.Builder()
                            .url(url)
                            .method("PUT", requestBody)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/json")
                            .build();
            Response response = httpClient.newCall(putRequest).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doDelete(String url, Object body, String cookie) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(body), mediaType);
            Request deleteRequest = new Request.Builder()
                    .url(url)
                    .method("DELETE", requestBody)
                    .addHeader("Cookie", cookie)
                    .addHeader("Content-Type", "application/json")
                    .build();
            Response response = httpClient.newCall(deleteRequest).execute();
            if (response.isSuccessful()) {
                result = Objects.requireNonNull(response.body()).string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.getMessage());
        }
        return result;
    }

    public static String doPost(String url, String body, Headers headers) {

        String result = null;
        try {
//            OkHttpClient httpClient = new OkHttpClient.Builder()
//                    .addInterceptor(new OceanusInterceptor("com.sankuai.grocerybi.mustang.service"))
//                    .build();
            OkHttpClient httpClient = new HttpUtils().getTrustAllClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");

            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request postRequest =
                    new Request.Builder().url(url)
                            .headers(headers)
                            .post(requestBody).build();
            Response response = httpClient.newCall(postRequest).execute();

            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (Exception e) {
            log.error("调用接口异常", e);
        }
        return result;
    }

//    public static HttpClient createHttpClient() {
//        /**
//         * @Description OceanusHttpClient
//         * @MethodName createHttpClient
//         * @Author wangfeng78
//         * @Date 2022/4/26 21:10
//         * @return org.apache.commons.httpclient.HttpClient
//         * @Parm []
//         **/
//        OceanusHttpProcessor oceanusHttpProcessor = null;
//        try {
//            // Mustang Appkey
//            oceanusHttpProcessor = new OceanusHttpProcessor("com.sankuai.grocerybi.mustang.service");
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//
//        HttpClientBuilder httpClientBuilder = HttpClients.custom();
//        httpClientBuilder.addInterceptorFirst((HttpRequestInterceptor) oceanusHttpProcessor);
//        HttpClient httpClient = httpClientBuilder.build();
//        return httpClient;
//    }

    public static String doPost(String url, Object obj) {

        String result = null;
        try {
//            OkHttpClient httpClient = getOkHttpClient();
            OkHttpClient httpClient = new HttpUtils().getTrustAllClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(obj), mediaType);
            Request postRequest =
                    new Request.Builder().url(url)
                            .post(requestBody).build();
            log.info("查询参数为： {}", JSONObject.toJSONString(obj));
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
//                log.info("调用http接口返回值:{}", result);
            }
        } catch (Exception e) {
            log.error("调用接口异常", e);
        }
        return result;
    }
    public static String doPost(String url, String body,Headers headers,Object obj){

        String result = null;
        try {
            OkHttpClient httpClient = new OkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(JSONObject.toJSONBytes(obj),mediaType);
            Request posstRequst = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .headers(headers)
                    .build();
            Response response = httpClient.newCall(posstRequst).execute();
            if (response.isSuccessful()){
                result = response.body().string();
            }
        } catch (IOException e) {
            log.info("接口调用异常{}",e);
        }
        return result;

    }


//    public static String doGet(String url) {
//
//        String result = null;
//        try {
//            OkHttpClient httpClient = getOkHttpClient();
//
//            Request postRequest =
//                    new Request.Builder().url(url)
//                            .addHeader("Cookie", SsoUtils.getSsoId())
//                            .addHeader("Content-Type", "application/json")
//                            .get()
//                            .build();
//            Response response = httpClient.newCall(postRequest).execute();
//            if (response.isSuccessful()) {
//                result = response.body().string();
//            }
//        } catch (IOException e) {
//            log.error("调用接口异常{}", e.toString());
//        }
//        return result;
//    }

    public static String doGet(String url, String cookie) {

        String result = null;
        try {
//            OkHttpClient httpClient = getOkHttpClient();
            OkHttpClient httpClient = new HttpUtils().getTrustAllClient();
            Request postRequest =
                    new Request.Builder().url(url)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/json")
                            .get()
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doGet(String url) {
        String result = null;
        try {
            OkHttpClient httpClient = new HttpUtils().getTrustAllClient();
            Request postRequest =
                    new Request.Builder().url(url)
                            .addHeader("Content-Type", "application/json")
                            .get()
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doGetNau(String url, String authorization) {

        String result = null;
        try {
            OkHttpClient httpClient = new HttpUtils().getTrustAllClient();
            Request postRequest =
                    new Request.Builder().url(url)
                            .addHeader("Authorization", authorization)
                            .addHeader("Content-Type", "application/json")
                            .get()
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doGetOrigin(String url, String cookie, String xBusilineId) {

        String result = null;
        try {
//            OkHttpClient httpClient = getOkHttpClient();
            OkHttpClient httpClient = new HttpUtils().getTrustAllClient();
            Request postRequest =
                    new Request.Builder().url(url)
                            .addHeader("Cookie", cookie)
                            .addHeader("Content-Type", "application/json")
                            .addHeader("X-BusilineId", xBusilineId)
                            .get()
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常{}", e.toString());
        }
        return result;
    }

    public static String doGetNew(String url, Headers headers) {
        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            Request postRequest = new Request
                    .Builder()
                    .url(url)
                    .headers(headers)
                    .get().build();
            Response response = httpClient.newCall(postRequest).execute();
            String results = String.valueOf(response.body());
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("调用接口异常,{}", e.toString());
        }
        return result;
    }

    public static String doPost(String url, String body, String token, String useName) {

        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request postRequest =
                    new Request.Builder().url(url)
                            .addHeader("Authorization", token)
                            .addHeader("USERNAME", useName)
                            .addHeader("Content-Type", "application/json")
                            .post(requestBody).build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("访问API报错了,{}", e.toString());
        }
        return result;
    }

    public static String doGet(String url, String token, String userName) throws Exception {
        String result = null;
        try {
            log.info("=========开始执行调用get方法=========");
//            OkHttpClient httpClient = getOkHttpClient();
            OkHttpClient httpClient = new HttpUtils().getTrustAllClient();
            httpClient.readTimeoutMillis();
            Request postRequest =
                    new Request.Builder().url(url)
                            .addHeader("Authorization", token)
                            .addHeader("USERNAME", userName)
                            .addHeader("Content-Type", "application/json")
                            .get()
                            .build();
            Response response = httpClient.newCall(postRequest).execute();
            log.info("+++++++++++调用doGet查询接口返回参数为+++++++++++++, {}", response);
            if (response.isSuccessful()) {
                result = response.body().string();
                log.info("+++++++++++调用doGet toString后查询接口返回参数为+++++++++++++, {}", result);
            }
        } catch (IOException e) {
            log.error("访问api报错，url={}", url, e);
            throw new Exception("访问api报错了" + e.toString());
        }
        return result;
    }

    public static String doPut(String url, String body, String token, String userName) {

        String result = null;
        try {
            OkHttpClient httpClient = getOkHttpClient();
            MediaType mediaType = MediaType.parse("application/json;charset=utf-8");
            RequestBody requestBody = RequestBody.create(mediaType, body);
            Request postRequest =
                    new Request.Builder().url(url)
                            .addHeader("Authorization", token)
                            .addHeader("USERNAME", userName)
                            .addHeader("Content-Type", "application/json")
                            .put(requestBody).build();
            Response response = httpClient.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
            }
        } catch (IOException e) {
            log.error("访问API报错了, {}", e.toString());
        }
        return result;
    }

    public static String doPostByFormData(String url, Map<String, String> dataMap, String userName, String token) {
        String result = "";
        try {
            OkHttpClient client = new HttpUtils().getTrustAllClient();
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, String> entry : dataMap.entrySet()) {
                builder.add(entry.getKey(), entry.getValue());
            }
            Request postRequest = new Request.Builder().url(url)
                    .addHeader("Authorization", "Basic " + Base64.getUrlEncoder().encodeToString((userName + ":" + token).getBytes(StandardCharsets.UTF_8)))
                    .addHeader("Content-Type", "multipart/form-data")
                    .put(builder.build())
                    .build();
            Response response = client.newCall(postRequest).execute();
            if (response.isSuccessful()) {
                result = response.body().string();
                return result;
            } else {
                return "post访问api失败";
            }
        } catch (IOException e) {
            log.error("post访问api报错,{}", e.toString());
            return result;
        }
    }

    private static OkHttpClient getOkHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .callTimeout(120, TimeUnit.SECONDS)
                .pingInterval(5, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false).build();
    }


    private SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;
        try {
            mMyTrustManager = new MyTrustManager();
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{mMyTrustManager}, new SecureRandom());
            ssfFactory = sc.getSocketFactory();
        } catch (Exception ignored) {
            log.error("认知失败");
        }

        return ssfFactory;
    }

    //实现X509TrustManager接口
    public class MyTrustManager implements X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    //实现HostnameVerifier接口
    private class TrustAllHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    }

    public OkHttpClient getTrustAllClient() {
        OkHttpClient.Builder mBuilder = new OkHttpClient.Builder();
        mBuilder.sslSocketFactory(createSSLSocketFactory(), mMyTrustManager)
                .hostnameVerifier(new TrustAllHostnameVerifier())
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .callTimeout(120, TimeUnit.SECONDS)
                .pingInterval(5, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(false);
        return mBuilder.build();
    }

    /**
     * 对外提供的获取支持自签名的okhttp客户端
     *
     * @param certificate 自签名证书的输入流
     * @return 支持自签名的客户端
     */
    public OkHttpClient getTrusClient(InputStream certificate) {
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        try {
            trustManager = trustManagerForCertificates(certificate);
            SSLContext sslContext = SSLContext.getInstance("TLS");
            //使用构建出的trustManger初始化SSLContext对象
            sslContext.init(null, new TrustManager[]{trustManager}, null);
            //获得sslSocketFactory对象
            sslSocketFactory = sslContext.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new RuntimeException(e);
        }
        return new OkHttpClient.Builder()
                .sslSocketFactory(sslSocketFactory, trustManager)
                .build();
    }

    /**
     * 获去信任自签证书的trustManager
     *
     * @param in 自签证书输入流
     * @return 信任自签证书的trustManager
     * @throws GeneralSecurityException
     */
    private X509TrustManager trustManagerForCertificates(InputStream in)
            throws GeneralSecurityException {
        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        //通过证书工厂得到自签证书对象集合
        Collection<? extends Certificate> certificates = certificateFactory.generateCertificates(in);
        if (certificates.isEmpty()) {
            throw new IllegalArgumentException("expected non-empty set of trusted certificates");
        }
        //为证书设置一个keyStore
        // Any password will work.
        char[] pd = "password".toCharArray();
        KeyStore keyStore = newEmptyKeyStore(pd);
        int index = 0;
        //将证书放入keystore中
        for (Certificate certificate : certificates) {
            String certificateAlias = Integer.toString(index++);
            keyStore.setCertificateEntry(certificateAlias, certificate);
        }
        // Use it to build an X509 trust manager.
        //使用包含自签证书信息的keyStore去构建一个X509TrustManager
        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(
                KeyManagerFactory.getDefaultAlgorithm());
        keyManagerFactory.init(keyStore, pd);
        TrustManagerFactory trustManagerFactory = TrustManagerFactory.getInstance(
                TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        TrustManager[] trustManagers = trustManagerFactory.getTrustManagers();
        if (trustManagers.length != 1 || !(trustManagers[0] instanceof X509TrustManager)) {
            throw new IllegalStateException("Unexpected default trust managers:"
                    + Arrays.toString(trustManagers));
        }
        return (X509TrustManager) trustManagers[0];
    }

    private KeyStore newEmptyKeyStore(char[] password) throws GeneralSecurityException {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = null; // By convention, 'null' creates an empty key store.
            keyStore.load(null, password);
            return keyStore;
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

}