package com.campulse.campulse;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.campulse.campulse.api.CampulseApi;
import com.campulse.campulse.eventList.EventListFragment;
import com.campulse.campulse.model.Event;
import com.campulse.campulse.model.EventResponse;
import com.facebook.AccessToken;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private CampulseApi mCampulseApi;
    private EventListFragment mEventListFragment;
    private FacebookApplication mFacebookApplication;
    private final String TAG = "Main Activity";
    private Fragment fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCampulseApi =  CampulseApi.retrofit.create(CampulseApi.class);
        mEventListFragment = new EventListFragment();
        mFacebookApplication = new FacebookApplication();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // TODO set default fragment as saved list

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        ImageView profileImage = (ImageView) headerView.findViewById(R.id.nav_header_main_imageView);
        TextView name = (TextView) headerView.findViewById(R.id.nav_header_main_name);
        name.setText("Eduardo Coronado"); // TODO : Yah ok
        TextView major = (TextView) headerView.findViewById(R.id.nav_header_main_studying);
        major.setText("Software Engineering");
        navigationView.setNavigationItemSelectedListener(this);

        Picasso.Builder builder = new Picasso.Builder(this);
        builder.listener(new Picasso.Listener() {
            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                exception.printStackTrace();
            }
        });
        builder.build().load("https://scontent.xx.fbcdn.net/v/t1.0-1/p86x86/13532940_10153548858582187_8447062067806751471_n.jpg?oh=9cef8712f16ca264bedbfb10c5861b3c&oe=5864DB15")
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(profileImage);


        fragment = mEventListFragment;
        changeFragment(fragment);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart(){
        super.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        AccessToken token = AccessToken.getCurrentAccessToken();
        if(token != null){
            Call<EventResponse> eventsAddedToDB = mCampulseApi.postFacebookEvents(token.toString());
            eventsAddedToDB.enqueue(new Callback<EventResponse>() {
                @Override
                public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                    if(response != null){
                        EventResponse mEventResponse = response.body();
                        if(mEventResponse != null) {
                            ArrayList<Event> newEvents = mEventResponse.getData();
                            for (Event event : newEvents) {
                                Log.d(TAG, "Event added to DB: " + event.getName());
                            }
                        }
                    }
                }

                @Override
                public void onFailure(Call<EventResponse> call, Throwable t) {

                }
            });
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        // TODO switch to case
        if (id == R.id.nav_events) {
            // TODO close drawer if true
            if(this.fragment.equals(mEventListFragment)) return true;
            this.fragment = mEventListFragment;
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        changeFragment(this.fragment);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment navigationFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment != null) {
            fragmentTransaction.add(R.id.content_frame, fragment);
            fragmentTransaction.commit();
        }
    }

    public CampulseApi getCampulseApi(){ return mCampulseApi; }

}
