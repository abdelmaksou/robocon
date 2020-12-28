package com.abdelmaksoud.robocon;

import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.util.UUID;

//import static com.abdelmaksoud.robocon.Control.myUUID;

public class Control_g extends AppCompatActivity {
    ImageView forward_g, backward_g, right_g, left_g, flash_on_g, flash_off_g, on_g, off_g, through_g, pass_g, loob_g, shoot_g;
    private boolean f_p = false, r_p = false, l_p = false, b_p = false, s_p = false, p_p = false, t_p = false, lo_p = false, flash_p = false;
    String address = null;
    String write = "S" ;
    public String lock = "unlocked";
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private Handler mHandler = new Handler();
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control_g);
        forward_g = (ImageView) findViewById(R.id.frwrd_g);
        backward_g = (ImageView) findViewById(R.id.bckwrd_g);
        right_g = (ImageView) findViewById(R.id.right_g);
        left_g = (ImageView) findViewById(R.id.left_g);
        flash_on_g = (ImageView) findViewById(R.id.flash_on_g);
        flash_off_g = (ImageView) findViewById(R.id.flash_off_g);
        on_g = (ImageView) findViewById(R.id.on_g);
        off_g = (ImageView) findViewById(R.id.off_g);
        shoot_g = (ImageView) findViewById(R.id.shoot_g);
        pass_g = (ImageView) findViewById(R.id.pass_g);
        loob_g = (ImageView) findViewById(R.id.loob_g);
        through_g = (ImageView) findViewById(R.id.through_g);
        Intent newint = getIntent();
        address = newint.getStringExtra(Main.EXTRA_ADDRESS);
        new ConnectBT().execute(); //Call the class to connect
        forward_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        f_p = true;
                        forward_g.setImageResource(R.drawable.forward_arrow_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        f_p = false;
                        forward_g.setImageResource(R.drawable.forward_arrow);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });

        backward_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        b_p = true;
                        backward_g.setImageResource(R.drawable.backward_arrow_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        b_p = false;
                        backward_g.setImageResource(R.drawable.backward_arrow);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });

        left_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        l_p = true;
                        left_g.setImageResource(R.drawable.left_arrow_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        l_p = false;
                        left_g.setImageResource(R.drawable.left_arrow);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });

        right_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        r_p = true;
                        right_g.setImageResource(R.drawable.right_arrow_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        r_p = false;
                        right_g.setImageResource(R.drawable.right_arrow);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });
        shoot_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        s_p = true;
                        shoot_g.setImageResource(R.drawable.shoot_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        s_p = false;
                        shoot_g.setImageResource(R.drawable.shoot);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });
        pass_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        p_p = true;
                        pass_g.setImageResource(R.drawable.pass_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        p_p = false;
                        pass_g.setImageResource(R.drawable.pass);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });
        through_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        t_p = true;
                        through_g.setImageResource(R.drawable.through_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        t_p = false;
                        through_g.setImageResource(R.drawable.through);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return true;
            }
        });
        loob_g.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    if (lock == "unlocked") {
                        lo_p = true;
                        loob_g.setImageResource(R.drawable.loob_p);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    if (lock == "unlocked") {
                        lo_p = false;
                        loob_g.setImageResource(R.drawable.loob);
                        press();
                        return true;
                    } else {
                        Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                    }
                }
                return false;
            }
        });
        flash_off_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lock == "unlocked") {
                    flash_on_g.setVisibility(View.VISIBLE);
                    flash_off_g.setVisibility(View.INVISIBLE);
                    flash_p =true;
                    write("U");
                    press();
                } else {
                    Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                }
            }
        });
        flash_on_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lock == "unlocked") {
                    flash_on_g.setVisibility(View.INVISIBLE);
                    flash_off_g.setVisibility(View.VISIBLE);
                    flash_p = false;
                    write("W");
                    press();
                } else {
                    Toast.makeText(Control_g.this, "Turn the controler on", Toast.LENGTH_SHORT);
                }
            }
        });
        off_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                off_g.setVisibility(View.INVISIBLE);
                on_g.setVisibility(View.VISIBLE);
                write("3");
                lock = "locked";
            }
        });
        on_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                off_g.setVisibility(View.VISIBLE);
                on_g.setVisibility(View.INVISIBLE);
                lock = "unlocked";
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Disconnect();
    }

    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute() {
            progress = ProgressDialog.show(Control_g.this, "Connecting...", "To princess car");//show a progress dialog

        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try {
                if (btSocket == null || !isBtConnected) {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            } catch (IOException e) {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess) {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            } else {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
            press();
        }
    }
    public void write(String data) {
        try {

            btSocket.getOutputStream().write(data.toString().getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void Disconnect() {
        if (btSocket != null) //If the btSocket is busy
        {
            try {
                 //close connection
                write="D";
                btSocket.close();
            } catch (IOException e) {
                msg("Error");
            }
        }
        finish(); //return to the first layout
    }

    private void msg(String s) {
        Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
    }

    private void press() {
        if (f_p == true && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("F");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("S");
        } else if (f_p == false && b_p == true && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("B");
        } else if (f_p == false && b_p == false && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("R");
        } else if (f_p == false && b_p == false && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("L");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == true && lo_p == false && p_p == false && t_p == false ) {
            write("O");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == true && p_p == false && t_p == false ) {
            write("Z");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == true && t_p == false ) {
            write("P");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == true ) {
            write("T");
        } else if (f_p == true && b_p == false && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false) {
            write("G");
        } else if (f_p == false && b_p == true && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("H");
        } else if (f_p == false && b_p == true && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("J");
        } else if (f_p == true && b_p == false && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write ("I");
        } else if (f_p == true && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("F");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
           /* if (flash_p == true) {
                write = "s";
                start();
            }else {*/
            write ("S");
            //}
        } else if (f_p == false && b_p == true && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("B");
        } else if (f_p == false && b_p == false && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("R");
        } else if (f_p == false && b_p == false && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("L");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == true && lo_p == false && p_p == false && t_p == false ) {
            write("O");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == true && p_p == false && t_p == false ) {
            write("Z");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == true && t_p == false ) {
            write("P");
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == true ) {
            write("T");
        } else if (f_p == true && b_p == false && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("G");
        } else if (f_p == false && b_p == true && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("H");
        } else if (f_p == false && b_p == true && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write("J");
        } else if (f_p == true && b_p == false && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false ) {
            write ("I");
        } /*else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            if (flash_p == true) {
                write = "s";
                start();
            }else {
                write = "S";
                start();
            }
        } else if (f_p == false && b_p == true && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write="b";
            start();
        } else if (f_p == false && b_p == false && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write="r";
            start();
        } else if (f_p == false && b_p == false && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write="l";
            start();
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == true && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write="o";
            start();
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == true && p_p == false && t_p == false && flash_p == true) {
            write="z";
            start();
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == true && t_p == false && flash_p == true) {
            write="p";
            start();
        } else if (f_p == false && b_p == false && r_p == false && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == true && flash_p == true) {
            write="t";
            start();
        } else if (f_p == true && b_p == false && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write="g";
            start();
        } else if (f_p == false && b_p == true && r_p == false && l_p == true && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write="h";
            start();
        } else if (f_p == false && b_p == true && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write="j";
            start();
        } else if (f_p == true && b_p == false && r_p == true && l_p == false && s_p == false && lo_p == false && p_p == false && t_p == false && flash_p == true) {
            write = "i";
            start();
        } else {
            if (flash_p == true) {
                write = "s";
                start();
            }*/else {
            write ("S");
        }
    }
}