package com.bathust.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;

/**
 * Created by bathust on 15-4-14.
 */
public class AccessTokenKeeper {
    private static final String PREFERENCES_NAME = "com_weibo_sdk_android";
    private static final String KEY_UID = "uid";
    private static final String KEY_ACCESS_TOKEN  = "access_token";
    private static final String KEY_EXPIRES_IN    = "expires_in";
    public static void writeAcessToken(Context context, Oauth2AccessToken token) {
        if (context == null || token == null) {
            return;
        }
        SharedPreferences tokenSave = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = tokenSave.edit();
        editor.putString(KEY_UID, token.getUid());
        editor.putString(KEY_ACCESS_TOKEN, token.getToken());
        editor.putLong(KEY_EXPIRES_IN, token.getExpiresTime());
        editor.commit();
    }
    public static Oauth2AccessToken readAcessToken(Context context) {
        Oauth2AccessToken token = new Oauth2AccessToken();
        SharedPreferences tokenSave = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_APPEND);
        token.setUid(tokenSave.getString(KEY_UID, ""));
        token.setToken(tokenSave.getString(KEY_ACCESS_TOKEN,""));
        token.setExpiresTime(tokenSave.getLong(KEY_EXPIRES_IN,0L));
        return token;
    }
    public static void clear(Context context) {
        SharedPreferences tokenSave = context.getSharedPreferences(PREFERENCES_NAME,Context.MODE_APPEND);
        SharedPreferences.Editor editor = tokenSave.edit();
        editor.clear();
        editor.commit();
    }
}
