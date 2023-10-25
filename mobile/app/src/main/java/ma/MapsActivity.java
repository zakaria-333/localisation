package ma;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;
import android.util.Log;


import com.android.volley.Request;
import com.android.volley.RequestQueue;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Map;

import ma.ensa.Position;
import ma.ensa.list.R;
import ma.ensa.list.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private String insertUrl = "http://192.168.1.102/localisation/loadPosition.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocations();
        LatLng sydney = new LatLng(33.247121672931385, -8.526599818411267);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in El Jadida"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

    }

    public void getLocations() {
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest request = new StringRequest(Request.Method.POST, insertUrl, response -> {
            try {
                JSONArray jsonArray = new JSONArray(response);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    double latitude = jsonObject.optDouble("latitude", 0.0);
                    double longitude = jsonObject.optDouble("longitude", 0.0);

                    if (latitude != 0.0 && longitude != 0.0) {
                        LatLng position = new LatLng(latitude, longitude);
                        mMap.addMarker(new MarkerOptions().position(position).title("Marker " + i));
                    }
                }
            } catch (JSONException e) {
                Log.e("Error", "Error parsing JSON: " + e.getMessage());
            }
        }, error -> {
            Log.e("Error", "Volley error: " + error.getMessage());
        });

        requestQueue.add(request);
    }


}
