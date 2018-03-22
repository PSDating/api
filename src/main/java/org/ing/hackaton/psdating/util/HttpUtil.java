package org.ing.hackaton.psdating.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethodBase;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.http.HttpMethod;

import java.io.IOException;
import java.util.Map;

public final class HttpUtil {

    private HttpUtil() {}

    public static final Document executeRequest(final HttpMethod method, final String url,
            final Map<String, String> headers, final Map<String, String> queryParams) throws IOException {
        final HttpClient client = new HttpClient();

        final HttpMethodBase methodWithUrl;
        switch(method) {
        case GET:
            methodWithUrl = new GetMethod(url);
            break;
        case POST:
            methodWithUrl = new PostMethod(url);
            break;
        default:
            throw new IllegalArgumentException("Not yet supported: " + method);
        }

        // Provide custom retry handler is necessary
        methodWithUrl.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
                new DefaultHttpMethodRetryHandler(3, false));

        try {
            // Execute the method.
            int statusCode = client.executeMethod(methodWithUrl);

            if (statusCode != HttpStatus.SC_OK) {
                System.err.println("Method failed: " + methodWithUrl.getStatusLine());
            }

            // Read the response body.
            byte[] responseBody = methodWithUrl.getResponseBody();

            // Deal with the response.
            // Use caution: ensure correct character encoding and is not binary data
            return Jsoup.parse(new String(responseBody));
        } finally {
            // Release the connection.
            methodWithUrl.releaseConnection();
        }
    }

}
