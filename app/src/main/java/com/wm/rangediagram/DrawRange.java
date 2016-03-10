package com.wm.rangediagram;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * Created by WM on 9/30/2015.
 */
public class DrawRange extends AppCompatActivity {
    private static final String LOG_TAG = "LOG Cat";
    Drawrangeview rangeview;
    boolean juststarted;
    private double HWx, HWy, SARx, SARy, PWx, PWy, Sx, Sy, HWxo, HWyo, PWxo, PWyo, SARxo, SARyo, Sxo, Syo, TOx, TOxo, SAReach, RHx, RHy, CHx, CHy, CHxoffset, CHxoffseto;
    private double Syf, Sxf, Syfo, Sxfo, Sxftest, SFnum, SFtran, RHxo, RHyo, RHPeakx, CHxo, CHyo;
    private double Pitarea, Spoilarea, bankspoilarea, RHarea, CombSpoilarea, CHarea;
    private int TWtran, DLRtran, DHtran, DDtran, TOtran, SAtran, SARtran, HWtran, PWtran, BWtran, BHtran, DLRnum, TWnum, DHnum, DDnum, SARnum, HWnum, BWnum, BHnum, PWnum, TOnum, SAnum, OGSARnum, CHnum, CHtran;
    private boolean SARchange, yes, RHnum, RHtran, juststartedhelp;
    private boolean FromRangeInput;
    private TableLayout parameterListView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Log.i(LOG_TAG, "Retrieve Values");

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        parameterListView = (TableLayout) this.findViewById(R.id.TableLayoutResults);

        registerForContextMenu(parameterListView);

        /* Get values from Intent */
        Bundle intent = getIntent().getExtras();
        juststarted = intent.getBoolean("juststarted", true);
        FromRangeInput = intent.getBoolean("FromRangeInput", true);

        grabdata();
    }

    public void grabdata() {
        //Convert input to String
        if (juststarted) {
            SARchange = false;

            String SAstring = "90";
            SAnum = Integer.parseInt(SAstring);
            String DLRstring = "300";
            DLRnum = Integer.parseInt(DLRstring);
            String TWstring = "80";
            TWnum = Integer.parseInt(TWstring);
            String DHstring = "115";
            DHnum = Integer.parseInt(DHstring);
            String DDstring = "150";
            DDnum = Integer.parseInt(DDstring);
            String TOstring = "25";
            TOnum = Integer.parseInt(TOstring);

            Bundle extra = getIntent().getExtras();

            TextView SARtext = (TextView) findViewById(R.id.Sardrawview);
            SARtext.setText(getIntent().getStringExtra("SAR"));
            String SARstring = SARtext.getText().toString();
            SARnum = Integer.parseInt(SARstring);

            OGSARnum = SARnum;

            TextView HWtext = (TextView) findViewById(R.id.HWdrawview);
            HWtext.setText(getIntent().getStringExtra("HW"));
            String HWstring = HWtext.getText().toString();
            HWnum = Integer.parseInt(HWstring);

            TextView PWtext = (TextView) findViewById(R.id.PWdrawview);
            PWtext.setText(getIntent().getStringExtra("PW"));
            String PWstring = PWtext.getText().toString();
            PWnum = Integer.parseInt(PWstring);

            TextView BWtext = (TextView) findViewById(R.id.BWdrawview);
            BWtext.setText(getIntent().getStringExtra("BW"));
            String BWstring = BWtext.getText().toString();
            BWnum = Integer.parseInt(BWstring);

            TextView BHtext = (TextView) findViewById(R.id.BHdrawview);
            BHtext.setText(getIntent().getStringExtra("BH"));
            String BHstring = BHtext.getText().toString();
            BHnum = Integer.parseInt(BHstring);

            String CHstring = extra.getString("CH");
            CHnum = Integer.parseInt(CHstring);


            String SFstring = extra.getString("SF");
            SFnum = Double.parseDouble(SFstring);

            Boolean RHbool = extra.getBoolean("RH");
            RHnum = RHbool;

            juststartedhelp = true;

        } else {

            if (FromRangeInput) {
                OGSARnum = SARnum;
                SARnum = SARtran;
                HWnum = HWtran;
                PWnum = PWtran;
                BWnum = BWtran;
                BHnum = BHtran;
                SFnum = SFtran;
                RHnum = RHtran;
                CHnum = CHtran;
                Intent extras = getIntent();
                extras.putExtra("SAR", SARnum);
                extras.putExtra("HW", HWnum);
                extras.putExtra("PW", PWnum);
                extras.putExtra("BW", BWnum);
                extras.putExtra("BH", BHnum);
                extras.putExtra("SF", SFnum);
                extras.putExtra("RH", RHnum);
                extras.putExtra("CH", CHnum);
            } else {
                DLRnum = DLRtran;
                TWnum = TWtran;
                DHnum = DHtran;
                DDnum = DDtran;
                TOnum = TOtran;
                SAnum = SAtran;
                Intent extras = getIntent();
                extras.putExtra("DLR", DLRnum);
                extras.putExtra("SA", SAnum);
                extras.putExtra("TW", TWnum);
                extras.putExtra("DH", DHnum);
                extras.putExtra("DD", DDnum);
                extras.putExtra("TO", TOnum);
            }
                Bundle extra = getIntent().getExtras();

                if (extra == null) {
                    finish();
                } else {
                    Intent extras = getIntent();
                    if (SARchange) {
                        extras.putExtra("SAR", OGSARnum);
                        SARnum = OGSARnum;
                        SARchange = false;
                    }

                    if (FromRangeInput) {


                        TextView SARtext = (TextView) findViewById(R.id.Sardrawview);
                        SARtext.setText(Integer.toString(extras.getIntExtra("SAR", 0)));
                        String SARstring = SARtext.getText().toString();
                        SARnum = Integer.parseInt(SARstring);


                        TextView HWtext = (TextView) findViewById(R.id.HWdrawview);
                        HWtext.setText(Integer.toString(extras.getIntExtra("HW", 0)));
                        String HWstring = HWtext.getText().toString();
                        HWnum = Integer.parseInt(HWstring);

                        TextView PWtext = (TextView) findViewById(R.id.PWdrawview);
                        PWtext.setText(Integer.toString(extras.getIntExtra("PW", 0)));
                        String PWstring = PWtext.getText().toString();
                        PWnum = Integer.parseInt(PWstring);

                        TextView BWtext = (TextView) findViewById(R.id.BWdrawview);
                        BWtext.setText(Integer.toString(extras.getIntExtra("BW", 0)));
                        String BWstring = BWtext.getText().toString();
                        BWnum = Integer.parseInt(BWstring);

                        TextView BHtext = (TextView) findViewById(R.id.BHdrawview);
                        BHtext.setText(Integer.toString(extras.getIntExtra("BH", 0)));
                        String BHstring = BHtext.getText().toString();
                        BHnum = Integer.parseInt(BHstring);

                        String SFstring = Double.toString(extras.getDoubleExtra("SF", 0));
                        SFnum = Double.parseDouble(SFstring);

                        String CHstring = Integer.toString(extras.getIntExtra("CH", 0));
                        CHnum = Integer.parseInt(CHstring);

                        Boolean RHbool = (extras.getBooleanExtra("RH", false));
                        RHnum = RHbool;
                    }
                }

        }

        Log.i(LOG_TAG, "Retrieved");


        if (RHnum) {
            norehandlecalcs(SARnum, HWnum, BWnum, PWnum, BHnum, DLRnum, TWnum, DHnum, DDnum, SFnum, TOnum, SAnum, CHnum);
            bestfitwrehandle(SARnum, HWnum, BWnum, PWnum, BHnum, DLRnum, TWnum, DHnum, DDnum, SFnum, TOnum, SAnum, CHnum);
        } else {
            norehandlecalcs(SARnum, HWnum, BWnum, PWnum, BHnum, DLRnum, TWnum, DHnum, DDnum, SFnum, TOnum, SAnum, CHnum);
        }
        Intent sartest = getIntent();

        if (SARchange) {
            sartest.putExtra("SAR", SARnum);
            SARchange = true;
        }
        if (!juststarted) {
            TextView SARtext = (TextView) findViewById(R.id.Sardrawview);
            SARtext.setText(Integer.toString(sartest.getIntExtra("SAR", 0)));
            String SARstring = SARtext.getText().toString();
            SARnum = Integer.parseInt(SARstring);

        }
        juststarted = false;
        Log.i(LOG_TAG, "Calculated");
        Log.d(LOG_TAG, "HWx: " + Double.toString(HWx));
        Log.d(LOG_TAG, "HWy: " + Double.toString(HWy));
        Log.d(LOG_TAG, "PWx; " + Double.toString(PWx));
        Log.d(LOG_TAG, "PWy; " + Double.toString(PWy));
        Log.d(LOG_TAG, "SARx: " + Double.toString(SARx));
        Log.d(LOG_TAG, "SARy: " + Double.toString(SARy));
        Log.d(LOG_TAG, "Sx; " + Double.toString(Sx));
        Log.d(LOG_TAG, "Sy; " + Double.toString(Sy));


        int HWxi = (int) HWx;
        int HWyi = (int) HWy;
        int PWxi = (int) PWx;
        int PWyi = (int) PWy;
        int SARxi = (int) SARx;
        int SARyi = (int) SARy;
        int Sxi = (int) Sx;
        int Syi = (int) Sy;
        int HWxio = (int) HWxo;
        int HWyio = (int) HWyo;
        int PWxio = (int) PWxo;
        int PWyio = (int) PWyo;
        int SARxio = (int) SARxo;
        int SARyio = (int) SARyo;
        int Sxio = (int) Sxo;
        int Syio = (int) Syo;
        int Sxfio = (int) Sxfo;
        int Syfio = (int) Syfo;
        int Sxfi = (int) Sxf;
        int Syfi = (int) Syf;
        int TOfi = (int) TOx;
        int RHxi = (int) RHx;
        int RHyi = (int) RHy;
        int RHxio = (int) RHxo;
        int RHyio = (int) RHyo;
        int RHPeakxi = (int) RHPeakx;
        int CHxi = (int) CHx;
        int CHyi = (int) CHy;
        int CHxio = (int) CHxo;
        int CHyio = (int) CHyo;
        int CHxoffseti = (int) CHxoffset;
        int CHxoffsetio = (int) CHxoffseto;
        double SFi = SFnum;
        int SAi = SAnum;
        yes = true;


        rangeview = (Drawrangeview) findViewById(R.id.draw_canvas_main_activity);

        rangeview.setSides(HWxi, HWyi, PWxi, PWyi, SARxi, SARyi, Sxi, Syi, Sxfi, Syfi,
                yes, DLRnum, TWnum, HWxio, HWyio, PWxio, PWyio, SARxio, SARyio, Sxio,
                Syio, Sxfio, Syfio, Pitarea, Spoilarea, SFi, bankspoilarea, TOfi, SAi, SARchange,
                DHnum, RHxi, RHyi, RHxio, RHyio, RHPeakxi, RHarea, CHxi, CHyi, CHxio,
                CHyio, CHarea, CHxoffseti, CHxoffsetio);
        //  ^^10 variables per line ^^

        if (juststartedhelp) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Instructions");
            builder.setMessage("Click 'List Pit Volumes' to display the calculated volumes and the used pit dimensions." + "\n" + "\n" + "Click 'Adjust DL Dimensions' to change the dragline specifications."
                    + "\n" + "\n" + "Click 'Adjust Range Settings' to change the range diagram settings." + "\n" + "\n" + "Click the red box at the bottom of the screen to display volumetrics and calculated quantities");
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application
                    dialog.cancel();
                }
            });
            juststartedhelp = false;
            AlertDialog alert1 = builder.create();
            alert1.show();
        }
        if (SARchange) {
            Toast.makeText(this, "Entered SAR places spoil above DH, SAR changed to: " + SARnum + "°", Toast.LENGTH_SHORT).show();
        }
    }

    public void bestfitwrehandle(double Sar, double HW, double BW, double PW, double BH, double Reach, double Tub, double DH, double DD, double SF, double TO, double SA, double CH) {
        double HWangle = Math.toRadians(HW);
        double SARangle = Math.toRadians(Sar);
        SAReach = Reach * (Math.sin(Math.toRadians(SA)));
        double Newreach = SAReach - (Tub / 2) - TO - (BH / (Math.tan(HWangle)));

        if (bankspoilarea*SFnum < (Pitarea + CHarea)*SFnum) {
            RHarea = 0;
            CombSpoilarea = (bankspoilarea*SFnum + RHarea);
            for (RHx = SARx; CombSpoilarea <= (Pitarea + CHarea)*SFnum && RHx >= PWx; RHx=) {

                RHy = Sy + ((SARx - RHx) / 2) * (Math.tan(SARangle));
                RHarea = Sy * (-RHx + SARx) + (RHy - Sy) * ((RHy - Sy) / Math.tan(SARangle));
                CombSpoilarea = (bankspoilarea*SFnum + RHarea);
                RHPeakx = Sx - ((RHy - Sy) / ((Sy - SARy) / (Sx - SARx)));
            }


        } else if (bankspoilarea*SFnum >= (Pitarea + CHarea)*SFnum) {
            RHy = SARy;
            int count = 1;
            RHyo = SARyo;
            for (RHx = 0; bankspoilarea *SFnum>= (Pitarea + CHarea)*SFnum && (SARx >= PWxo); RHx++) {
                double rehandlediff = (RHx);

                SARx++;
                Sy = Sy - (count * (Math.tan(SARangle)));
                //Calculate Intercept for Spoil Toe between old and new spoil
                SARxo++;
                Syo = Syo - (count * (Math.tan(SARangle)));
                //Calculate Intercept for Spoil Toe between old and new spoil
                Sxftest = (((Sx + (Newreach - RHx)) * Math.tan(SARangle) - (-Math.tan(SARangle) * SARxo)) / (2 * (Math.tan(SARangle))));
                if (Sxftest > SARxo) {
                    Sxf = Sxftest;
                    Syf = Math.abs(-Math.tan(SARangle) * (Sxf) + ((Sx + (Newreach - RHx)) * Math.tan(SARangle)));
                } else {
                    Sxf = 2 * Sx - SARx;
                    Syf = SARy;
                }


                Sxfo = SARxo + Sxf - SARx;
                Syfo = Syf;

                if (Sxf > SARxo) {
                    bankspoilarea = ((.5 * (Sx - SARx) * Sy) + ((.5 * (Sx - SARx) * Sy) - (Syf / Math.tan(SARangle) * Syf)) + (CH * BW)) / SF;
                } else {
                    bankspoilarea = ((.5 * (Sx - SARx) * Sy) * 2 + (CH * BW)) / SF;
                }
            }
            RHxo = SARxo + (Sx - RHx);

            if (Sxf > SARxo) {
                Spoilarea = ((.5 * (Sx - SARx) * Sy) + ((.5 * (Sx - SARx) * Sy) - (Syf / Math.tan(SARangle) * Syf)) + (CH * BW));
            } else {
                Spoilarea = ((.5 * (Sx - SARx) * Sy) * 2 + (CH * BW));
            }

            RHx = 0;
            RHarea = 0;

        }

    }

    public void norehandlecalcs(double Sar, double HW, double BW, double PW, double BH, double Reach, double Tub, double DH, double DD, double SF, double TO, double SA, double CH) {
        double HWangle = Math.toRadians(HW);
        double Tubloc = BW + PW - TO - Tub / 2;
        SAReach = Reach * (Math.sin(Math.toRadians(SA)));
        CHy = CH + BH;
        CHx = BW;
        CHxoffset = CHx + CH / (Math.tan(HWangle));
        HWx = CHxoffset + PW;
        HWy = BH;

        double SARangle = Math.toRadians(Sar);
        PWx = HWx + BH / (Math.tan(HWangle));
        PWy = HWy - BH;
        SARy = PWy;
        SARx = PWx + PW;
        TOx = TO;


        double Newreach = SAReach - (Tub / 2) - TO - (BH / (Math.tan(HWangle)));

        Sx = SARx + (Newreach);
        if ((SARy + (Newreach * (Math.tan(SARangle)))) < (HWy + DH)) {
            Sy = Math.abs(SARy + (Newreach * (Math.tan(SARangle))));
        } else {
            Sy = Math.abs(HWy + DH);
            SARangle = Math.atan(Sy / Newreach);
            SARnum = (int) Math.round(Math.toDegrees(SARangle));
            SARchange = true;
        }

        //for old pit
        CHxo = CHx + BW;
        CHyo = CHy;
        CHxoffseto = CHxo + CH / (Math.tan(HWangle));
        HWxo = HWx + PW;
        HWyo = BH;
        PWxo = HWxo + BH / (Math.tan(HWangle));
        PWyo = HWyo - BH;
        SARyo = PWyo;
        SARxo = PWxo + PW;
        Sxo = SARxo + (Newreach);
        if (!SARchange) {
            Syo = Math.abs(SARyo + (Newreach * (Math.tan(SARangle))));
        } else {
            Syo = Math.abs(HWyo + DH);
        }
        //Calculate Intercept for Spoil Toe between old and new spoil
        Sxftest = (((Sx + Newreach) * Math.tan(SARangle) - (-Math.tan(SARangle) * SARxo)) / (2 * (Math.tan(SARangle))));

        if (Sxftest > SARxo) {
            Sxf = Sxftest;
            Syf = Math.abs(-Math.tan(SARangle) * (Sxf) + ((Sx + Newreach) * Math.tan(SARangle)));
        } else {
            Sxf = Sx + ((Newreach));
            Syf = Math.abs(SARy);

        }

        Sxfo = SARxo + Sxf - SARx;
        Syfo = Syf;

        RHx = SARx;
        RHy = SARy;

        RHxo = SARxo;
        RHyo = SARyo;
        //Areas
        Pitarea = (PW * BH);

        if (Sxf > SARxo) {
            Spoilarea = (((.5 * (Sx - SARx) * Sy) - .5 * (RHy / Math.tan(HWangle) * RHy) - .5 * (RHy / Math.tan(SARangle) * RHy)) + ((.5 * (Sx - SARx) * Sy) - (Syf / Math.tan(SARangle) * Syf)) + (CH * BW));
        } else {
            Spoilarea = ((.5 * (Sx - SARx) * Sy) * 2 - .5 * (RHy / Math.tan(HWangle) * RHy) - .5 * (RHy / Math.tan(SARangle) * RHy) + (CH * BW));
        }
        RHx = 0;
        bankspoilarea = Spoilarea / SF;
        CHarea = CHx * CHy;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // Process clicks on Options Menu items
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.draglinedims:
                Intent dlsize = new Intent(this, InputDLSizeActivity.class);
                dlsize.putExtra("SA", SAnum);
                dlsize.putExtra("DLR", DLRnum);
                dlsize.putExtra("TW", TWnum);
                dlsize.putExtra("DH", DHnum);
                dlsize.putExtra("DD", DDnum);
                dlsize.putExtra("TO", TOnum);
                startActivityForResult(dlsize, 1);
                return true;
            case R.id.listdimensions:
                openContextMenu(parameterListView);
                return true;
            case R.id.adjustrangesettings:
                Intent rangesize = new Intent(this, InputRangeActivity.class);
                rangesize.putExtra("SAR", SARnum);
                rangesize.putExtra("HW", HWnum);
                rangesize.putExtra("PW", PWnum);
                rangesize.putExtra("BW", BWnum);
                rangesize.putExtra("BH", BHnum);
                rangesize.putExtra("CH", CHnum);
                rangesize.putExtra("FromRangeInput", false);
                rangesize.putExtra("SF", SFnum);
                startActivityForResult(rangesize, 1);
                return true;
            case R.id.help:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("- Click 'List Pit Volumes' to display the calculated volumes and the used pit dimensions." + "\n" + "\n" + "- Click 'Adjust DL Dimensions' to change the dragline specifications."
                        + "\n" + "\n" + "- Click 'Adjust Range Settings' to change the range diagram settings." + "\n" + "\n" + "- Click the red box at the bottom of the screen to display volumetrics.");
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String SAstring, DLRstring, TWstring, DHstring, DDstring, TOstring, SARstring, SFstring, HWstring, BHstring, BWstring, PWstring, CHstring;
        int SAnumretrieve, DLRnumretrieve, TWnumretrieve, DHnumretrieve, DDnumretrieve, TOnumretrieve, SARnumretrieve, HWnumretrieve, PWnumretrieve, BWnumretrieve, BHnumretrieve, CHnumretrieve;
        double SFnumretrieve;
        if (resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();

            FromRangeInput = extras.getBoolean("FromRangeInput");
            if (FromRangeInput) {
                SARstring = extras.getString("SAR");
                SARnumretrieve = Integer.parseInt(SARstring);

                HWstring = extras.getString("HW");
                HWnumretrieve = Integer.parseInt(HWstring);

                PWstring = extras.getString("PW");
                PWnumretrieve = Integer.parseInt(PWstring);

                BWstring = extras.getString("BW");
                BWnumretrieve = Integer.parseInt(BWstring);

                BHstring = extras.getString("BH");
                BHnumretrieve = Integer.parseInt(BHstring);

                CHstring = extras.getString("CH");
                CHnumretrieve = Integer.parseInt(CHstring);

                SFstring = extras.getString("SF");
                SFnumretrieve = Double.parseDouble(SFstring);

                Boolean RHboolretrieve = extras.getBoolean("RH");
                RHnum = RHboolretrieve;

                SARtran = SARnumretrieve;
                HWtran = HWnumretrieve;
                PWtran = PWnumretrieve;
                BWtran = BWnumretrieve;
                BHtran = BHnumretrieve;
                SFtran = SFnumretrieve;
                CHtran = CHnumretrieve;
                RHtran = RHboolretrieve;
                juststarted = false;


            } else {
                SAstring = extras.getString("SA");
                SAnumretrieve = Integer.parseInt(SAstring);

                DLRstring = extras.getString("DLR");
                DLRnumretrieve = Integer.parseInt(DLRstring);

                TWstring = extras.getString("TW");
                TWnumretrieve = Integer.parseInt(TWstring);

                DHstring = extras.getString("DH");
                DHnumretrieve = Integer.parseInt(DHstring);

                //               DDstring = extras.getString("DD");
                //               DDnumretrieve = Integer.parseInt(DDstring);

                TOstring = extras.getString("TO");
                TOnumretrieve = Integer.parseInt(TOstring);

                DLRtran = DLRnumretrieve;
                TWtran = TWnumretrieve;
                DHtran = DHnumretrieve;
                //               DDtran = DDnumretrieve;
                TOtran = TOnumretrieve;
                SAtran = SAnumretrieve;

                juststarted = false;
            }


            grabdata();
        }


    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Parameters and Volumes");

        menu.add(0, v.getId(), 0, "Bank Pit Area " + NumberFormat.getIntegerInstance().format(Math.round(Pitarea)) + " ft^2");
        menu.add(0, v.getId(), 0, "Bank Chop Area " + NumberFormat.getIntegerInstance().format(Math.round(CHarea)) + " ft^2");
        menu.add(0, v.getId(), 0, "Swelled Pit & Chop Area " + NumberFormat.getIntegerInstance().format((Pitarea + CHarea) * SFnum) + " ft^2");
        menu.add(0, v.getId(), 0, "Loose Spoil Area " + NumberFormat.getIntegerInstance().format(Math.round(Spoilarea)) + " ft^2");
        menu.add(0, v.getId(), 0, "Rehandle Area " + NumberFormat.getIntegerInstance().format(Math.round(RHarea)) + " ft^2");
        menu.add(0, v.getId(), 0, "Total Spoil Area " + NumberFormat.getIntegerInstance().format(Math.round(Spoilarea + RHarea)) + " ft^2");
        menu.add(0, v.getId(), 0, "RH " + NumberFormat.getIntegerInstance().format(Math.round((RHarea / (Pitarea + CHarea) * SFnum) * 100)) + " %");
        menu.add(0, v.getId(), 0, "HW Offset " + NumberFormat.getIntegerInstance().format(Math.round(PWx - HWx)) + " ft");
        menu.add(0, v.getId(), 0, "SAR @ " + SARnum + " deg");
        menu.add(0, v.getId(), 0, "HW @ " + HWnum + " deg");
        menu.add(0, v.getId(), 0, "PW " + PWnum + " ft");
        menu.add(0, v.getId(), 0, "BH " + BHnum + " ft");
        menu.add(0, v.getId(), 0, "BW " + BWnum + " ft");
        menu.add(0, v.getId(), 0, "CH " + BHnum + " ft");
        menu.add(0, v.getId(), 0, "SF " + SFnum);

    }

    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        builder.setMessage("Are you sure you want to Exit?               (Use option menu to change settings");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application

                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });

        AlertDialog alert1 = builder.create();
        alert1.show();

    }

}

