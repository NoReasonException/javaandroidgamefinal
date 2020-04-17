package main.connectivity;

import android.arch.core.util.Function;
import android.os.Handler;
import android.util.Pair;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import cz.msebera.android.httpclient.Header;


/**
 * the ScoreServer Driver , a light wrapper over AsyncHttpClient for communicating with our server
 */
public class ScoreServerDriver extends HttpUtils {


    /**
     * calls the .getScores() controller , on success calls the success callback , otherwise it calls the failure callback
     * @param handler the main handler to post jobs
     * @param success the success callback
     * @param failure the failure callback
     */
    public static void getScores(Handler handler, Function<ArrayList<Pair<String,String>>,Void> success, Function<String,Void> failure){
        get("getScores/", new RequestParams(), new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {

                try {
                    JSONObject testV=new JSONObject(new String(responseBody));
                    ArrayList<Pair<String,String>> scores=new ArrayList<>();
                    JSONArray results=testV.getJSONArray("results");
                    if(results.length()>0){
                        scores.add(new Pair<String,String> (
                                testV.getJSONArray("results").getJSONObject(0).getString("name"),
                                testV.getJSONArray("results").getJSONObject(0).getString("time")));
                    }
                    if(results.length()>1){
                        scores.add(new Pair<String,String> (
                                testV.getJSONArray("results").getJSONObject(1).getString("name"),
                                testV.getJSONArray("results").getJSONObject(1).getString("time")));
                    }
                    if(results.length()>2){
                        scores.add(new Pair<String,String> (
                                testV.getJSONArray("results").getJSONObject(2).getString("name"),
                                testV.getJSONArray("results").getJSONObject(2).getString("time")));
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            success.apply(scores);
                        }
                    });


                } catch (JSONException e) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            failure.apply("Fail to parse json maybe?" );//TODO Maybe fix sometime?
                        }
                    });
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        if(error!=null){

                            failure.apply(new String(error.getMessage()));
                        }
                        else {
                            failure.apply("responce was "+statusCode+"with empty error");
                        }
                    }
                });
            }
        });
    }

    /**
     * sets a given key,value pair as score
     * @param name the name of the player
     * @param time the time of the player in number format (02:33 -> 0233 as string)
     */
    public static void setScore(String name,String time){//is given as MM:SS
        time=time.replace(":","");
        get("postScore/?name="+name+"&time="+time, new RequestParams(), new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                //DO NOTHING
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                //DO NOTHING
            }
        });
    }

}
