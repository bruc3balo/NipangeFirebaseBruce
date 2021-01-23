package com.example.nipange.landlord;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nipange.R;
import com.example.nipange.models.PlaceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
//import com.google.android.gms.location.places.AutocompletePrediction;
//import com.google.android.gms.location.places.Place;
//import com.google.android.gms.location.places.PlaceBuffer;
//import com.google.android.gms.location.places.Places;
//import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


//imports in red below

import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;


//OTHERS BELOW:
//import com.google.android.libraries.places.api.Places;
//import com.google.android.libraries.places.api.model.Place;
//import com.google.android.libraries.places.api.net.PlacesClient;
//import com.google.android.libraries.places.widget.AutocompleteSupportFragment;
//import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;

import java.util.Arrays;
import java.util.List;

public class new_property_launch extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener {
    private static final int AUTOCOMPLETE_REQUEST_CODE = 5;

    //GoogleMap.OnMyLocationButtonClickListener,
    //        GoogleMap.OnMyLocationClickListener,
    //        OnMapReadyCallback {
    //
    //    @Override
    //            public void

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d(TAG, "onMapReady: map is ready");
        Toast.makeText(this, "Map is Ready", Toast.LENGTH_SHORT).show();
        mMap = googleMap;

        if (mLocationPermissionGranted) {
            getDeviceLocation();
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(true);

            init();


        }
    }


    //    GoogleMap map;
//    SupportMapFragment mapFragment;
//    SearchView searchView;
    TextView save;
//    PlaceAutocompleteFragment place_autocomplete_fragment;
////    private LatLng nairobi = new LatLng(-1.286389, 36.817223);
////    MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.google_map);
////    mapFragment.getMapAsync(this);
//
//
//PlacesClient placesClient;


    private static final String TAG = "MapActivity";
    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds
            (new LatLng(-40, -168), new LatLng(71, 136));


//try {
//if(mLocationPermissionGranted){
//    Task location = mFusedLocationProviderClient.getLastLocation();
//    location.addOnCompleteListener(new OnCompleteListener() {
//        @Override
//        public void onComplete(@NonNull Task task) {
//            if (task.isSuccessful()){
//                Log.d(TAG,"onComplete: found location!");
//
//            }
//            else{
//                Log.d(TAG,"onComplete: current location is null");
//
//
//            }
//        }
//    });
//}
//    }
//catch(SecurityException e){
//    Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
//    }


    //Widgets
    private AutoCompleteTextView mSearchText;
    //Variables

    private Boolean mLocationPermissionGranted = false;
    private GoogleMap mMap;
    //    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
//    private GoogleApiClient mGoogleApiClient;
//    private PlacesClient placesClient;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    //    private FusedLocationProviderClient mFusedLocationProviderClient;
//    private Place mPlace;
    private PlaceInfo mPlace;













    //NEW 2

    private void initMap(){

        Log.d(TAG,"initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(new_property_launch.this);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==AUTOCOMPLETE_REQUEST_CODE) {
            if(resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                Log.e("result", place.getName() + " Place found: ");
            } else if(resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.e("result", "Status " + status.getStatusMessage());
            }
        }
    }

    //ANOTHER 1
    private void init(){
        Log.d(TAG, "init: initializing");
//        mGoogleApiClient = new GoogleApiClient
//                .Builder(this)
//
////                .addApi(Places.GEO_DATA_API)
////                .addApi(Places.PLACE_DETECTION_API)
//
//                .enableAutoManage(this, this)
//
//                .build();
//        mSearchText.setOnItemClickListener(mAutocompleteClickListener);
//        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
//                LAT_LNG_BOUNDS,null );
//
//        mSearchText.setAdapter(mPlaceAutocompleteAdapter);


//        Places.initialize(getApplicationContext(), CLIENT_ID);
//        PlacesClient placesClient = Places.createClient(this);


        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId== EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_DONE
                        || keyEvent.getAction()== KeyEvent.ACTION_DOWN
                        || keyEvent.getAction()==KeyEvent.KEYCODE_ENTER){
                    geoLocate();
                }
                return false;
            }
        });
        hideSoftKeyboard();
    }



    //NEW MOST

    private void geoLocate(){
        Log.d(TAG, "geoLocate: geoLocating");
        String searchString = mSearchText.getText().toString();
        List <Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);

        Intent intent = new Autocomplete.IntentBuilder(
                AutocompleteActivityMode.FULLSCREEN,
                fields
        ).setCountry("KE")
                .build(this);

        startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
//        Geocoder geocoder = new Geocoder(new_property_launch.this);
//        List<Address> list = new ArrayList<>();
//        try{
//            list = geocoder.getFromLocationName (searchString, 1);
//        }
//        catch (IOException e){
//            Log.e(TAG, "geoLocate: IOException: " + e.getMessage());
//        }
//        if( list.size()>0){
//            Address address = list.get(0);
//            Log.d(TAG, "geoLocate: found a location: " + address.toString());
//
//            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
//                    address.getAddressLine(0));
//        }
    }










    //NEW
    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location ");

        FusedLocationProviderClient mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        try {
            if(mLocationPermissionGranted){
                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()){
                            Log.d(TAG,"onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();


                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "My Location");
                        }
                        else{
                            Log.d(TAG,"onComplete: current location is null");
                            Toast.makeText(new_property_launch.this , "unable to get current location", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }
        catch(SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage());
        }


    }
    private void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG,"moveCamera: moving camera to: lat: " + latLng.latitude + ", lng " + latLng.longitude);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));


        if(!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }
        hideSoftKeyboard();
    }


//NEW 0

    private void getLocationPermission() {
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                mLocationPermissionGranted = true;
                initMap();
            } else {
                ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);

            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, LOCATION_PERMISSION_REQUEST_CODE);

        }
    }








    //NEW 1
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        Log.d(TAG,"onRequestPermissionResult: called.");
        mLocationPermissionGranted =false;

        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if(grantResults.length>0){

                    for(int i = 0; i<grantResults.length;i++) {
                        if (grantResults [i] !=PackageManager.PERMISSION_GRANTED ){
                            mLocationPermissionGranted=false;
                            Log.d(TAG,"onRequestPermissionResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG,"onRequestPermissionResult: permission granted");
                    mLocationPermissionGranted=true;

                    //initialize our map
                    initMap();
                }
            }

        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }





//1ST

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_property_launch);

        mSearchText = (AutoCompleteTextView)findViewById(R.id.input_search);
        getLocationPermission();

        Log.e("starting", "Activity starts here");;



//NEW 3

        if(!Places.isInitialized()) {
            Places.initialize(getApplicationContext(), getString(R.string.map_key));
        }



        //NEW 0
//        private void getLocationPermission(){
//String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
//Manifest.permission.ACCESS_COARSE_LOCATION};
//
//if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
//      FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
//
//    if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
//            COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED) {
//        mLocationPermissionGranted = true;
//    }
//    else{
//        ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE);
//
//    }
//       }
//
//        }







//MAPS NEW 2

//        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
//                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//            @Override
//            public void onPlaceSelected(Place place) {
//
//                       final LatLng latLng = place.getLatLng();
//
//                                                               Toast.makeText(new_property_launch.this, ""+latLng.latitude, Toast.LENGTH_SHORT).show();
//
//                                                           }
//            @Override
//            public void onError(Status status){
//                                                               Toast.makeText(new_property_launch.this, ""+status.getStatusMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//



//MAPS TRY NEW EXTRA










////MAPS NEW
//
//        String apikey = "AIzaSyBops0Mr4ZGo7dtfk-WFRi1Xp8GiIrmmcw";
//        if (!Places.isInitialized()){
//            Places.initialize(getApplicationContext(),apikey);
//        }
//
//        PlacesClient placesClient = Places.createClient(this);
//
//        final AutocompleteSupportFragment autocompleteSupportFragment =
//                (AutocompleteSupportFragment)getSupportFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);
//
//        autocompleteSupportFragment.setPlaceFields(Arrays.asList(Place.Field.ID, Place.Field.LAT_LNG,Place.Field.NAME));
//
//autocompleteSupportFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
//
//                                                            @Override
//                                                           public void onPlaceSelected(@NonNull Place place) {
//                                                                final LatLng latLng = place.getLatLng();
//
//                                                               Toast.makeText(new_property_launch.this, ""+latLng.latitude, Toast.LENGTH_SHORT).show();
////                                                                Log.i(TAG, "Place: " + place.getName() + ", " + place.getId());
//                                                           }
//
//                                                           @Override
//                                                           public void onError(@NonNull Status status) {
//                                                               Toast.makeText(new_property_launch.this, ""+status.getStatusMessage(), Toast.LENGTH_SHORT).show();
//                                                           }
//                                                       });
//


////MAPS NEW



        save = (TextView) findViewById(R.id.textView10);

        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), details_about_property.class);
                startActivity(intent);

            }
        });



//       searchView = findViewById(R.id.place_autocomplete_fragment);



//        mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.google_map);



//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                String location = searchView.getQuery().toString();
//                List<Address> addressList = null;
//
//
//
//                if(location != null || !location.equals("true")){
//
//
//                    Geocoder geocoder = new Geocoder (new_property_launch.this);
//                    try {
//                        addressList = geocoder.getFromLocationName(location, 1);
//                    } catch(IOException e){
//                        e.printStackTrace();
//
//                    }
//
//                    Address address = addressList.get(0);
//                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
//                    map.addMarker(new MarkerOptions().position(latLng).title(location));
//                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10));
//
//                }
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                return false;
//            }
//        });

//        mapFragment.getMapAsync(this);


    }



//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//
//        map=googleMap;
//        updateLocationUI();
//        getDeviceLocation();
//        map.getUiSettings().setZoomControlsEnabled(true);
//        map.getUiSettings().setMyLocationButtonEnabled(true);
////        map.moveCamera(CameraUpdateFactory.newLatLngZoom(nairobi, 8.5f));
////        map.addMarker(new MarkerOptions()
////                .position(nairobi)
////                .title("General Location")
////                .snippet("Nairobi")
////                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
//        map.getUiSettings().setMyLocationButtonEnabled(true);
//        //map.setOnMyLocationButtonClickListener(this);
//
//
//        //map.setOnMyLocationClickListener(this);
////        map.setOnMyLocationButtonClickListener(this);
////        map.setOnMyLocationClickListener(this);
//
//
//
//    }
//
//    private void getDeviceLocation() {
//    }
//
//    private void updateLocationUI() {
//    }
//
//    @Override
//    public boolean onMyLocationButtonClick() {
//        return false;
//    }
//
//    @Override
//    public void onMyLocationClick(@NonNull Location location) {
//
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }

//
//    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            hideSoftKeyboard();
//
//
//            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(i);
//            final String placeId = item.getPlaceId();
//
//            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
//                    .getPlaceById(mGoogleApiClient, placeId);
//            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
//        }
//    };
//    private ResultCallback<PlaceBuffer>mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
//        @Override
//        public void onResult(@NonNull PlaceBuffer places) {
//            if (!places.getStatus().isSuccess()){
//                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
//            places.release();
//            return;
//            }
//            final Place place = (Place) places.get(0);
//            try{
//                mPlace = new PlaceInfo();
//                mPlace.setName(place.getName().toString());
//                Log.d(TAG, "onResult: name: " + place.getName());
//                mPlace.setAddress(place.getAddress().toString());
//                Log.d(TAG, "onResult: address: " + place.getAddress());
////                mPlace.setAttributions(place.getAttributions().toString());
////                Log.d(TAG, "onResult: attributions: " + place.getAttributions());
//                mPlace.setId(place.getId());
//                Log.d(TAG, "onResult: id:" + place.getId());
//                mPlace.setLatlng(place.getLatLng());
//                Log.d(TAG, "onResult: latlng: " + place.getLatLng());
//                mPlace.setRating((double) place.getRating());
//                Log.d(TAG, "onResult: rating: " + place.getRating());
//                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
//                Log.d(TAG, "onResult: phone number: " + place.getPhoneNumber());
//                mPlace.setWebsiteUri(place.getWebsiteUri());
//                Log.d(TAG, "onResult: website uri: " + place.getWebsiteUri());
//
//                Log.d(TAG, "onResult: place: " + mPlace.toString());
//            }catch (NullPointerException e){
//                Log.e(TAG, "onResult: NullPointerException: " + e.getMessage() );
//            }
//
//            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
//                    place.getViewport().getCenter().longitude), DEFAULT_ZOOM, mPlace.getName());
//
//            places.release();
//        }
//    };
//}




//    private void getLocationPermission(){
//        if (ContextCompat.checkSelfPermission(this.getApplicationContext(),
//                android.Manifest.permission.ACCESS_FINE_LOCATION)
//                == PackageManager.PERMISSION_GRANTED) {
//            mLocationPermissionGranted = true;
//        } else {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
//                    PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
//        }
//    }
}


