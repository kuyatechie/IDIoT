package com.kuyatechie.android.idiot;

/**
 * Created by arvin on 8/2/16.
 */

//import for the beacon app
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;

import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//import for the GET app

public class MainActivity extends AppCompatActivity {

    Button mSearch;
    EditText mHTTP;
    TextView mDisplay;

    String address;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSearch = (Button) findViewById(R.id.button);
        mHTTP = (EditText) findViewById(R.id.editText);
        mDisplay = (TextView) findViewById(R.id.textView3);

        final RequestQueue queue = Volley.newRequestQueue(this);

        mSearch.setOnClickListener(
                new View.OnClickListener() {
                    public void onClick(View view) {
                        address = MainActivity.this.mHTTP.getText().toString();
                        Log.v("MyActivity", address);

                        // Request a string response from the provided URL.
                        StringRequest stringRequest = new StringRequest(Request.Method.GET, address, new Response.Listener<String>() {

                            @Override
                            public void onResponse(String response) {
                                        // Display the first 500 characters of the response string.
                                        Log.v("MyActivity", response);
                                        mDisplay.setMovementMethod(new ScrollingMovementMethod());
                                        mDisplay.setText("Response is: "+ response);
                                    }
                                }, new Response.ErrorListener() {

                            @Override
                            public void onErrorResponse(VolleyError error) {
                                mDisplay.setText("That didn't work!");
                            }
                        });
                        // Add the request to the RequestQueue.
                        queue.add(stringRequest);
                        //mDisplay.setText(address);
                    }
                });
    }
//    private static final Map<String, List<String>> PLACES_BY_BEACONS;
//
//    // TODO: replace "<major>:<minor>" strings to match your own beacons.
//    static {
//        Map<String, List<String>> placesByBeacons = new HashMap<>();
//        placesByBeacons.put("22504:48827", new ArrayList<String>() {{
//            add("Heavenly Sandwiches");
//            // read as: "Heavenly Sandwiches" is closest
//            // to the beacon with major 22504 and minor 48827
//            add("Green & Green Salads");
//            // "Green & Green Salads" is the next closest
//            add("Mini Panini");
//            // "Mini Panini" is the furthest away
//        }});
//        placesByBeacons.put("648:12", new ArrayList<String>() {{
//            add("Mini Panini");
//            add("Green & Green Salads");
//            add("Heavenly Sandwiches");
//        }});
//        PLACES_BY_BEACONS = Collections.unmodifiableMap(placesByBeacons);
//    }
//
//    private List<String> placesNearBeacon(Beacon beacon) {
//        String beaconKey = String.format("%d:%d", beacon.getMajor(), beacon.getMinor());
//        if (PLACES_BY_BEACONS.containsKey(beaconKey)) {
//            return PLACES_BY_BEACONS.get(beaconKey);
//        }
//        return Collections.emptyList();
//    }
//
//    private BeaconManager beaconManager;
//    private Region region;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        beaconManager = new BeaconManager(this);
//        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
//            @Override
//            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
//                if (!list.isEmpty()) {
//                    Beacon nearestBeacon = list.get(0);
//                    List<String> places = placesNearBeacon(nearestBeacon);
//                    // TODO: update the UI here
//                    Log.d("Airport", "Nearest places: " + places);
//                }
//            }
//        });
//        region = new Region("ranged region", UUID.fromString("B9407F30-F5F8-466E-AFF9-25556B57FE6D"), null, null);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        SystemRequirementsChecker.checkWithDefaultDialogs(this);
//
//        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
//            @Override
//            public void onServiceReady() {
//                beaconManager.startRanging(region);
//            }
//        });
//    }
//
//    @Override
//    protected void onPause() {
//        beaconManager.stopRanging(region);
//
//        super.onPause();
//    }
//
////    @Override
////    public boolean onCreateOptionsMenu(Menu menu) {
////        // Inflate the menu; this adds items to the action bar if it is present.
////        getMenuInflater().inflate(R.menu.menu_main, menu);
////        return true;
////    }
////
////    @Override
////    public boolean onOptionsItemSelected(MenuItem item) {
////        // Handle action bar item clicks here. The action bar will
////        // automatically handle clicks on the Home/Up button, so long
////        // as you specify a parent activity in AndroidManifest.xml.
////        int id = item.getItemId();
////
////        //noinspection SimplifiableIfStatement
////        if (id == R.id.action_settings) {
////            return true;
////        }
////
////        return super.onOptionsItemSelected(item);
////    }
}