package com.bathust.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bathust.R;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class SendActivity extends ActionBarActivity {
    private EditText editMessage;
    private Button sendButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        sendButton = (Button) findViewById(R.id.send);
        editMessage = (EditText) findViewById(R.id.edit_message);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editMessage.equals("")) {
                    Toast.makeText(SendActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }else {

                    String weiBo = editMessage.getText().toString();
                    try {
                        weiBo= URLEncoder.encode(weiBo,"UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }


                }
            }
        });

    }

    private void sendWeiBO(final URLEncoder urlEncoder){
        new Thread(new Runnable() {
            HttpURLConnection connection = null;
            @Override
            public void run() {


                try {
                    URL url = new URL("https://api.weibo.com/2/statuses/update.json?access_token=" +
                            "2.00WgyJAGDWVhUE68870b6f72hkqAIE"+"status="+urlEncoder);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setDoOutput(true);
                    connection.setDoInput(true);


                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send, menu);
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
