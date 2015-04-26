package com.bathust.ui;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.bathust.R;
import com.bathust.activity.HomeActivity;

/**
 * Created by bathust on 15-4-24.
 */
public class TitleLayout extends LinearLayout implements View.OnClickListener{

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title,this);
        Button titleHome=(Button) findViewById(R.id.title_home);
        Button titleNews=(Button) findViewById(R.id.title_news);
        Button titleFind=(Button)findViewById(R.id.title_find);
        Button titleMe = (Button) findViewById(R.id.title_me);
        titleFind.setOnClickListener(this);
        titleHome.setOnClickListener(this);
        titleMe.setOnClickListener(this);
        titleNews.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.title_home:
                intent.setClass(getContext(), HomeActivity.class);


        }
    }
}
