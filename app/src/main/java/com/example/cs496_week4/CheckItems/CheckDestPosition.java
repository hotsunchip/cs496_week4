package com.example.cs496_week4.CheckItems;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.cs496_week4.Main.MainActivity;
import com.example.cs496_week4.R;
import com.example.cs496_week4.Retrofit.CallRetrofit;
import com.example.cs496_week4.Retrofit.Data.appt.Model__apptInfo;
import com.example.cs496_week4.Retrofit.Data.user.GET__userDeparture;
import com.example.cs496_week4.Retrofit.Data.user.Output__Coordinate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class CheckDestPosition extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int apptId;
    private Model__apptInfo apptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkdestposition);

        Intent intent = getIntent();
        apptId = intent.getIntExtra("apptId", -1);
        apptInfo = new CallRetrofit().apptInfo(MainActivity.userToken, apptId);

        // set toolbar
        TextView title = findViewById(R.id.cdp_toolbar_title);
        title.setText(apptInfo.getDestination());
        Toolbar toolBar = findViewById(R.id.cdp_toolbar);
        setSupportActionBar(toolBar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setDisplayShowTitleEnabled(false);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.ic_close_24);
        }

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;

        Output__Coordinate coordinate = new CallRetrofit().convertAddressToCoordinate(MainActivity.userToken, apptInfo.getDestination());
        LatLng destCoor = new LatLng(coordinate.getLatitude(), coordinate.getLongitutde());

        MarkerOptions markerOptions = new MarkerOptions();         // 마커 생성
        markerOptions.position(destCoor);
        markerOptions.title("서울");                         // 마커 제목
        markerOptions.snippet("한국의 수도");         // 마커 설명
        mMap.addMarker(markerOptions);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(destCoor));                 // 초기 위치
        mMap.animateCamera(CameraUpdateFactory.zoomTo(15));                         // 줌의 정도
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);                           // 지도 유형 설정

    }

}