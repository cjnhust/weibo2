package com.bathust.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.bathust.R;
import com.bathust.adapter.WeiBoAdapter;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends ActionBarActivity  {

    private ListView showWeibo;
    private WeiBoAdapter weiBoAdapter;
    private ProgressDialog dialog;
    private List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
    private URL url;
     private String data;
    private SwipeRefreshLayout swipeRefreshLayout;
    private SimpleAdapter simpleAdapter;
    private ConnectivityManager networkConnectManager;
    private View footerView;
    private int MaxItemNum;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
          //  simpleAdapter = new SimpleAdapter(HomeActivity.this,list,R.layout.item,
            //        new String[]{"name","text"},new int[]{R.id.user_name,R.id.user_weibo});
            //simpleAdapter.notifyDataSetChanged();
            //showWeibo.setAdapter(simpleAdapter);


            weiBoAdapter.notifyDataSetChanged();
            showWeibo.setAdapter(weiBoAdapter);
            dialog.dismiss();
            swipeRefreshLayout.setRefreshing(false);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        showWeibo = (ListView)findViewById(R.id.show_weibo);
        weiBoAdapter=new WeiBoAdapter(HomeActivity.this,list);
        footerView= getLayoutInflater().inflate(R.layout.foot_view,null);
        showWeibo.addFooterView(footerView);
        dialog = new ProgressDialog(this);
        dialog.setTitle("正在加载");
        dialog.setMessage("请等待");
        dialog.setCancelable(false);
        dialog.show();
        sendRequestWithHttpURLConnection();



        swipeRefreshLayout=(SwipeRefreshLayout)findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                sendRequestWithHttpURLConnection();


            }
        });
        findViewById(R.id.more_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();
                sendRequestWithHttpURLConnection();

           }
        });




    }


    private String sendRequestWithHttpURLConnection(){


        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;


                    //HttpClient httpClient=new DefaultHttpClient();
                   // HttpGet httpGet = new HttpGet("https://www.baidu.com");
                   // httpClient.execute(httpGet);
                    //if(httpClient)



                try {
                    Intent intent=getIntent();
                    String token=intent.getStringExtra("access_token");
                   // Log.e("HomeActivity",token);
                    url=new URL("https://api.weibo.com/2/statuses/friends_timeline.json?" +
                            "access_token="+token+"&count=100");
                    connection=(HttpURLConnection)url.openConnection();
                   connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                   connection.setReadTimeout(8000);

                    InputStream in =connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                   StringBuilder response = new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null) {
                        response.append(line);

                    }


                    JSONObject jsonObject =new JSONObject(String.valueOf(response));
                    JSONArray statusesArray = jsonObject.getJSONArray("statuses");
                    for (int i = 0; i < statusesArray.length(); i++) {
                        JSONObject statusesObj = statusesArray.getJSONObject(i);

                        String text = statusesObj.getString("text");
                        String user = statusesObj.getString("user");
                        JSONObject userObj=new JSONObject(user);
                        String name = userObj.getString("name");
                       // String profile_image_url=userObj.getString("profile_image_url");

                      //  URI profile_image_uri=new URI(profile_image_url);
                        HashMap<String,Object> map=new HashMap<String, Object>();
                        map.put("name",name);
                        map.put("text",text);
<<<<<<< HEAD
                        //map.put("profile_image_j url",profile_image_uri);
=======
                        //map.put("profile_image_url",profile_image_uri);
>>>>>>> 8cebb8cfd8e0a3cb1955e9ab67245e56835092d6
                        list.add(map);
                        handler.sendEmptyMessage(0);

                    }


                }catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if(connection!=null) {
                        connection.disconnect();
                    }
                }

            }
        }).start();

        return data;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    }

