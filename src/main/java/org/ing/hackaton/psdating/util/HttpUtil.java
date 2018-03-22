package org.ing.hackaton.psdating.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.Map;

public final class HttpUtil {

    private HttpUtil() {}

    public static final Document executeRequestForDocument(final HttpMethod method, final String url,
            final Map<String, String> headers, final Map<String, String> queryParams) throws IOException {
        final byte[] responseBody = executeRequestForBytes(method, url, headers, queryParams);
        return Jsoup.parse(new String(responseBody));
    }

    private static byte[] executeRequestForBytes(final HttpMethod method, final String url,
            final Map<String, String> headers, final Map<String, String> queryParams) throws IOException {
        final HttpClient client = new HttpClient();

        final String escapedUrl = url.replaceAll(" ", "%20");
        final HttpMethodBase methodWithUrl;
        switch(method) {
        case GET:
            methodWithUrl = new GetMethod(escapedUrl);
            break;
        case POST:
            methodWithUrl = new PostMethod(escapedUrl);
            break;
        default:
            throw new IllegalArgumentException("Not yet supported: " + method);
        }
        methodWithUrl.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try {
            int statusCode = client.executeMethod(methodWithUrl);
            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + methodWithUrl.getStatusLine());
            }
            byte[] responseBody = methodWithUrl.getResponseBody();
            return responseBody;
        } finally {
            methodWithUrl.releaseConnection();
        }
    }

    public static JSONObject executeRequestForJsonObject(final HttpMethod method, final String url,
            final Map<String, String> headers, final Map<String, String> queryParams)
            throws IOException, ParseException {
        final byte[] responseBody = executeRequestForBytes(method, url, headers, queryParams);
        final JSONParser parser = new JSONParser();
        final Object resultObject = parser.parse(new String(responseBody));
        return (JSONObject) resultObject;
    }
}
