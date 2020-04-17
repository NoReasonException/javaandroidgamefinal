package main.connectivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/***
 * a bunch of utils to use in our ScoreDriver , this uses the AsyncHttpClient
 */
public class HttpUtils {
    private static final String BASE_URL = "http://192.168.1.9:8000/";

    private static AsyncHttpClient client = new AsyncHttpClient();

    /***
     * creates a GET request
     * @param url the target url (without the BASE_URL)
     * @param params various url params
     * @param responseHandler the callback
     */
    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    /**
     * concats the BASE_URL with the relative target
     * @param relativeUrl the relative target
     * @return the absolute url
     */
    private static String getAbsoluteUrl(String relativeUrl) {
        return BASE_URL + relativeUrl;
    }
}