package com.wm.rangediagram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by WM on 11/9/2015.
 */
public class InputDLSizeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LOG Cat";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dlsize_activity);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        ActionBar br = getSupportActionBar();

        br.setDisplayHomeAsUpEnabled(true);
        Bundle extras = getIntent().getExtras();

        if (extras == null) {
            Intent dldrawsettings = new Intent(this, DrawRange.class);

            dldrawsettings.putExtra("FromRangeInput", false);
            dldrawsettings.putExtra("juststarted", true);
            setResult(RESULT_CANCELED, dldrawsettings);
            finish();
        } else {

            Intent intent = getIntent();

            EditText SAeditview = (EditText) findViewById(R.id.Swingangleview);
            SAeditview.setText(Integer.toString((intent.getIntExtra("SA", 0))));

            EditText DLReditview = (EditText) findViewById(R.id.DLreachview);
            DLReditview.setText(Integer.toString((intent.getIntExtra("DLR", 0))));


            EditText TWeditview = (EditText) findViewById(R.id.TubWview);
            TWeditview.setText(Integer.toString((intent.getIntExtra("TW", 0))));


            EditText DHeditview = (EditText) findViewById(R.id.DumpHView);
            DHeditview.setText(Integer.toString((intent.getIntExtra("DH", 0))));


            // EditText DDeditview = (EditText) findViewById(R.id.DigDView);
            // DDeditview.setText(Integer.toString((intent.getIntExtra("DD", 0))));

            EditText TOeditview = (EditText) findViewById(R.id.TubOffview);
            TOeditview.setText(Integer.toString((intent.getIntExtra("TO", 0))));
        }

    }


    public void DLDim_onClick(View dlsize_button) {

        final EditText editSA = (EditText) findViewById(R.id.Swingangleview);
        String SAval = editSA.getText().toString();
        if (SAval.matches("")) {
            Toast.makeText(this, "Enter a Swing Angle", Toast.LENGTH_SHORT).show();
            return;
        }
        int SAtestval = Integer.parseInt(SAval);
        if (SAtestval >= 180 || SAtestval <= 0) {
            Toast.makeText(this, "Enter a SA between 180 and 0", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editDLreach = (EditText) findViewById(R.id.DLreachview);
        String DLRval = editDLreach.getText().toString();
        if (DLRval.matches("")) {
            Toast.makeText(this, "Enter a DL Reach", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editTubW = (EditText) findViewById(R.id.TubWview);
        String TWval = editTubW.getText().toString();
        if (TWval.matches("")) {
            Toast.makeText(this, "Enter a Tub Width", Toast.LENGTH_SHORT).show();
            return;
        }
//            final EditText editDD = (EditText) findViewById(R.id.DigDView);
//            String DDval = editDD.getText().toString();
//                if(DDval.matches("")){Toast.makeText(this,"Enter a Dig Depth",Toast.LENGTH_SHORT).show();return;}
        final EditText editDH = (EditText) findViewById(R.id.DumpHView);
        String DHval = editDH.getText().toString();
        if (DHval.matches("")) {
            Toast.makeText(this, "Enter a Dump Height", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editTO = (EditText) findViewById(R.id.TubOffview);
        String TOval = editTO.getText().toString();
        if (TOval.matches("")) {
            Toast.makeText(this, "Enter a Tub offset", Toast.LENGTH_SHORT).show();
            return;
        }

        Log.i(LOG_TAG, "Store Values dl settings");


        Intent dldrawsettings = new Intent(this, DrawRange.class);

        dldrawsettings.putExtra("SA", SAval);
        dldrawsettings.putExtra("DLR", DLRval);
        dldrawsettings.putExtra("TW", TWval);
        dldrawsettings.putExtra("DH", DHval);
//            dldrawsettings.putExtra("DD", DDval);
        dldrawsettings.putExtra("TO", TOval);
        dldrawsettings.putExtra("Source", false);
        dldrawsettings.putExtra("juststarted", false);
        dldrawsettings.putExtra("FromRangeInput", false);
        setResult(RESULT_OK, dldrawsettings);
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.help_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Process clicks on Options Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.helpmenu:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Instructions");
                builder.setMessage("1. Fill in the dragline dimensions then press 'Done'." + "\n" + "\n" + "2. Next an example range diagram will be drawn, the dragline and pit dimensions can be changed."
                        + "\n" + "\n" + "3. Pit volumes and rehandle will be calculated on the next screen and can be read by selection from the menu or by pressing the red parameter bar at the bottom of the screen.");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //if user pressed "yes", then he is allowed to exit from application
                        dialog.cancel();
                    }
                });
                AlertDialog alert1 = builder.create();
                alert1.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
