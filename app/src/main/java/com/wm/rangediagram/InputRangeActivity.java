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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by WM on 9/30/2015.
 */


public class InputRangeActivity extends AppCompatActivity {
    private static final String LOG_TAG = "LOG Cat";
    boolean juststarted;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);



        Bundle extras = getIntent().getExtras();

        juststarted=extras.getBoolean("juststarted");

        if (juststarted) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Instructions");
            builder.setMessage("1. Fill in the pit dimensions then press 'Create Pit'." + "\n" + "\n" + "2. After pressing 'Create Pit' example range diagram will be drawn, and the dragline and pit dimensions can be changed."
                    + "\n" + "\n" + "3. Pit volumes and rehandle will be calculated on the next screen.");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    dialog.cancel();
                }
            });
            AlertDialog alert1 = builder.create();
            alert1.show();
        } else if (!juststarted) {
            ActionBar br = getSupportActionBar();

            br.setDisplayHomeAsUpEnabled(true);
            Intent intent = getIntent();

            EditText SAReditview= (EditText) findViewById(R.id.SAReditView);
            SAReditview.setText(Integer.toString((intent.getIntExtra("SAR",0))));

            EditText HWeditview= (EditText) findViewById(R.id.HWeditView);
            HWeditview.setText(Integer.toString((intent.getIntExtra("HW",0))));

            EditText PWeditview= (EditText) findViewById(R.id.PWeditView);
            PWeditview.setText(Integer.toString((intent.getIntExtra("PW", 0))));

            EditText BWeditview= (EditText) findViewById(R.id.BenchwidthView);
            BWeditview.setText(Integer.toString((intent.getIntExtra("BW", 0))));

            EditText CHeditview = (EditText) findViewById(R.id.ChopheightView);
            CHeditview.setText(Integer.toString((intent.getIntExtra("CH", 0))));

            EditText BHeditview= (EditText) findViewById(R.id.BenchheightView);
            BHeditview.setText(Integer.toString((intent.getIntExtra("BH", 0))));

            EditText SFeditview= (EditText) findViewById(R.id.SFeditView);
            SFeditview.setText(Double.toString((intent.getDoubleExtra("SF", 0.0))));


        }



    }

    public void sendPitdim(View ButtonCreatePit) {
        //Check form for entries
        //Convert input to String

        final EditText editSAR = (EditText) findViewById(R.id.SAReditView);
        String SARval = editSAR.getText().toString();
        if (SARval.matches("")) {
            Toast.makeText(this, "Enter a SAR", Toast.LENGTH_SHORT).show();
            return;
        }
        int SARtestval = Integer.parseInt(SARval);
        if (SARtestval >= 90 || SARtestval <= 0) {
            Toast.makeText(this, "Enter a SAR between 90 and 0", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editHW = (EditText) findViewById(R.id.HWeditView);
        String HWval = editHW.getText().toString();
        if (HWval.matches("")) {
            Toast.makeText(this, "Enter a High Wall Angle", Toast.LENGTH_SHORT).show();
            return;
        }
        int HWtestval = Integer.parseInt(HWval);
        if (HWtestval >= 90 || HWtestval <= 0) {
            Toast.makeText(this, "Enter a HW between 90 and 0", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editPW = (EditText) findViewById(R.id.PWeditView);
        String PWval = editPW.getText().toString();
        if (PWval.matches("")) {
            Toast.makeText(this, "Enter a Pit Width", Toast.LENGTH_SHORT).show();
            return;
        }

        final EditText editBench = (EditText) findViewById(R.id.BenchwidthView);
        String Benchval = editBench.getText().toString();
        if (Benchval.matches("")) {
            Toast.makeText(this, "Enter a Chop Bench Width", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editBenchh = (EditText) findViewById(R.id.BenchheightView);
        String Benchhval = editBenchh.getText().toString();
        if (Benchhval.matches("")) {
            Toast.makeText(this, "Enter a Bench Height", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editChoph = (EditText) findViewById(R.id.ChopheightView);
        String Chophval = editChoph.getText().toString();
        if (Chophval.matches("")) {
            Toast.makeText(this, "Enter a Chop Thickness", Toast.LENGTH_SHORT).show();
            return;
        }
        final EditText editSF = (EditText) findViewById(R.id.SFeditView);
        String SFval = editSF.getText().toString();
        if (SFval.matches("")) {
            Toast.makeText(this, "Enter a Swell Factor", Toast.LENGTH_SHORT).show();
            return;
        }
        final CheckBox editRH = (CheckBox) findViewById(R.id.RHcheckBox);
        Boolean RHval = editRH.isChecked();


        if(juststarted) {
            juststarted=true;
        }

        Log.i(LOG_TAG, "Store Values");

        Intent draw = new Intent(InputRangeActivity.this, DrawRange.class);
        draw.putExtra("SAR", SARval);
        draw.putExtra("HW", HWval);
        draw.putExtra("PW", PWval);
        draw.putExtra("BW", Benchval);
        draw.putExtra("BH", Benchhval);
        draw.putExtra("CH", Chophval);
        draw.putExtra("FromRangeInput",true);
        draw.putExtra("juststarted", juststarted);
        draw.putExtra("SF", SFval);
        draw.putExtra("RH",RHval);

        if(!juststarted) {
                setResult(RESULT_OK, draw);
                finish();
            } else {

                startActivity(draw);
            finish();

        }
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
                builder.setMessage("1. Fill in the pit dimensions then press 'Create Pit'." + "\n" + "\n" + "2. After pressing 'Create Pit' example range diagram will be drawn, and the dragline and pit dimensions can be changed."
                        + "\n" + "\n" + "3. Pit volumes and rehandle will be calculated on the next screen.");
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



