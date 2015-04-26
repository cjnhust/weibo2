package com.bathust.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bathust.R;
import com.bathust.util.AccessTokenKeeper;
import com.bathust.util.Constants;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WeiboAuthListener;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;

public class AuthActivity extends ActionBarActivity {


    private static final String TAG = "weibo";
    private AuthInfo authInfo;
    private Oauth2AccessToken accessToken;

    private SsoHandler ssoHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorize);
        authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        ssoHandler = new SsoHandler(AuthActivity.this, authInfo);
        findViewById(R.id.authorize_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ssoHandler.authorize(new AuthListener());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_auth, menu);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }


    class AuthListener implements WeiboAuthListener {

        @Override
        public void onComplete(Bundle values) {
            accessToken = Oauth2AccessToken.parseAccessToken(values);
           if (accessToken.isSessionValid()) {
                AccessTokenKeeper.writeAcessToken(AuthActivity.this, accessToken);
                Toast.makeText(AuthActivity.this,
                        "成功授权", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AuthActivity.this,HomeActivity.class);
               intent.putExtra("access_token",accessToken.getToken());
               Log.e("AuthActivity",accessToken.getToken());
                startActivity(intent);
                finish();
            } else {

                String code = values.getString("code");
                String message = getString(R.string.auth_failed);
                if (!TextUtils.isEmpty(code)) {
                    message = message + "\nObtained the code: " + code;
                }
                Toast.makeText(AuthActivity.this, message, Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public void onCancel() {
            Toast.makeText(AuthActivity.this,
                    "授权取消", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onWeiboException(WeiboException e) {
            e.printStackTrace();
        }
    }



}
