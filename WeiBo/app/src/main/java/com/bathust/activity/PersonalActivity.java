package com.bathust.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.bathust.R;
import com.bathust.util.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PersonalActivity extends ActionBarActivity {

    private Oauth2AccessToken accessToken;
    private TextView personNameText;
    private TextView descriptionText;
    private Button weiboButton;
    private Button fansButton;
    private Button friendsButton;
    private ProgressDialog dialog;


    android.os.Handler handler = new android.os.Handler() {
        @Override
        public void handleMessage(Message msg) {
<<<<<<< HEAD
            if(msg.what==0) {
                Bundle bundle = msg.getData();
                String personalName = bundle.getString("name");
                String description = bundle.getString("description");
                int statuses_count = bundle.getInt("statuses_count");
                int friends_count = bundle.getInt("friends_count");
                int followers_count = bundle.getInt("followers_count");
                personNameText.setText(personalName);
                descriptionText.setText(description);
                friendsButton.setText("关注" + friends_count);
                fansButton.setText("粉丝" + followers_count);
                weiboButton.setText("微博" + statuses_count);
                dialog.dismiss();
            }
=======

            Bundle bundle=msg.getData();
            String personalName=bundle.getString("name");
            String description=bundle.getString("description");
            int statuses_count=bundle.getInt("statuses_count");
            int friends_count=bundle.getInt("friends_count");
            int followers_count=bundle.getInt("followers_count");
            personNameText.setText(personalName);
            descriptionText.setText(description);
            friendsButton.setText("关注"+friends_count);
            fansButton.setText("粉丝"+followers_count);
            weiboButton.setText("微博"+statuses_count);
            dialog.dismiss();
>>>>>>> 8cebb8cfd8e0a3cb1955e9ab67245e56835092d6
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);
        personNameText=(TextView)findViewById(R.id.personal_name);
        descriptionText=(TextView)findViewById(R.id.description);
        weiboButton=(Button)findViewById(R.id.weibo_button);
        fansButton=(Button)findViewById(R.id.fans_button);
        friendsButton=(Button)findViewById(R.id.friends_button);
        sendPersonRequest();

        dialog = new ProgressDialog(this);
        dialog.setTitle("正在加载");
        dialog.setMessage("请等待");
        dialog.setCancelable(false);
        dialog.show();

    }


    private void sendPersonRequest()  {

        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                accessToken= AccessTokenKeeper.readAcessToken(PersonalActivity.this);
                String token = accessToken.getToken();
                try {
<<<<<<< HEAD

                   URL url=new URL("https://api.weibo.com/2/users/show.json?access_token=2.00WgyJAGDWVhUE68870b6f72hkqAIE&uid=5499175220");
                    connection=(HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);

                    InputStream in =connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
=======
                   // URL uidURL = new URL("https://api.weibo.com/2/account/get_uid.json?access_token="+token);

                  //  connection =(HttpURLConnection)uidURL.openConnection();
                  ////  connection.setRequestMethod("GET");
                  //  connection.setConnectTimeout(8000);
                 //   connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    BufferedReader reader=new BufferedReader(new InputStreamReader(in));
                    //String uidJson = reader.readLine();
                   // Log.d("PersonalActivity",uidJson);

                   // JSONObject uidObject = new JSONObject(uidJson);
                  //  String uid = uidObject.getString("uid");
                    URL personURL = new URL("https://api.weibo.com/2/users/show.json?access_token=2.00WgyJAGDWVhUE68870b6f72hkqAIE&uid=5499175220");
                    connection =(HttpURLConnection)personURL.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
>>>>>>> 8cebb8cfd8e0a3cb1955e9ab67245e56835092d6
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line=reader.readLine())!=null) {
                        response.append(line);

                    }
                    Log.e("PersonalActivity", String.valueOf(response));
                    JSONObject personalObj = new JSONObject(String.valueOf(response));
                    String personalName=personalObj.getString("name");
                    String description=personalObj.getString("description");
                    int statuses_count=personalObj.getInt("statuses_count");
                    int friends_count=personalObj.getInt("friends_count");
                    int followers_count=personalObj.getInt("followers_count");
                    Message message = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putString("name",personalName);
                    bundle.putString("description",description);
                    bundle.putInt("statuses_count", statuses_count);
                    bundle.putInt("friends_count",friends_count);
                    bundle.putInt("followers_count",followers_count);
                    message.setData(bundle);
<<<<<<< HEAD
                    message.what=0;
=======
>>>>>>> 8cebb8cfd8e0a3cb1955e9ab67245e56835092d6
                    handler.sendMessage(message);



                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal, menu);
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
