package com.abdelmaksoud.robocon;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;


public class Main_g extends AppCompatActivity {
    Button btnPaired_g, disbuttton_g;
    ListView devicelist_g;
    ImageView imgblt_g;
    private BluetoothAdapter myBluetooth = null;
    private Set<BluetoothDevice> pairedDevices;
    public static String EXTRA_ADDRESS = "device_address";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_g);
        myBluetooth = BluetoothAdapter.getDefaultAdapter();
        if(myBluetooth == null)
        {
            //Show a mensag. that thedevice has no bluetooth adapter
            Toast.makeText(getApplicationContext(), "Bluetooth Device Not Available", Toast.LENGTH_LONG).show();
            //finish apk
            finish();
        }
        else
        {
            if (myBluetooth.isEnabled())
            { }
            else
            {
                //Ask to the user turn the bluetooth on
                Intent turnBTon = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(turnBTon,1);
            }
        }
        disbuttton_g = (Button) findViewById(R.id.disbutton_g);
        btnPaired_g = (Button) findViewById(R.id.button_g);
        devicelist_g = (ListView) findViewById(R.id.listview_g);
        imgblt_g = (ImageView) findViewById(R.id.imgblt_g);
        btnPaired_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPaired_g.setVisibility(View.INVISIBLE);
                disbuttton_g.setVisibility(View.VISIBLE);
                imgblt_g.setVisibility(View.INVISIBLE);
                devicelist_g.setVisibility(View.VISIBLE);
                pairedDevicesList();
            }
        });
        disbuttton_g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnPaired_g.setVisibility(View.VISIBLE);
                disbuttton_g.setVisibility(View.INVISIBLE);
                imgblt_g.setVisibility(View.VISIBLE);
                devicelist_g.setVisibility(View.INVISIBLE);
            }
        });}
    private void pairedDevicesList()
    {
        pairedDevices = myBluetooth.getBondedDevices();
        ArrayList list = new ArrayList();

        if (pairedDevices.size()>0)
        {
            for(BluetoothDevice bt : pairedDevices)
            {
                list.add(bt.getName() + "\n" + bt.getAddress()); //Get the device's name and the address
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "No Paired Bluetooth Devices Found.", Toast.LENGTH_LONG).show();
        }

        final ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
        devicelist_g.setAdapter(adapter);
        devicelist_g.setOnItemClickListener(myListClickListener); //Method called when the device from the list is clicked

    }
    private AdapterView.OnItemClickListener myListClickListener = new AdapterView.OnItemClickListener()
    {
        public void onItemClick (AdapterView<?> av, View v, int arg2, long arg3)
        {
            // Get the device MAC address, the last 17 chars in the View
            String info = ((TextView) v).getText().toString();
            String address = info.substring(info.length() - 17);

            // Make an intent to start next activity.
            Intent i = new Intent(Main_g.this, Control_g.class);

            //Change the activity.
            i.putExtra(EXTRA_ADDRESS, address); //this will be received at ledControl (class) Activity
            startActivity(i);
        }
    };
    /*@Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_device_list, menu);
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
            return true ;
        }

        return super.onOptionsItemSelected(item);
    }*/

}


