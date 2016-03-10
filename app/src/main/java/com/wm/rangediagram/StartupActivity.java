package com.wm.rangediagram;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by WM on 9/30/2015.
 */
public class StartupActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startup_activity);

        boolean juststarted=true;
        Intent intent = new Intent(StartupActivity.this,InputRangeActivity.class);
        intent.putExtra("juststarted",juststarted);
        startActivity(intent);
        finish();
    }

}
