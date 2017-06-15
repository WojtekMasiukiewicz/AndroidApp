package com.club.clubapp;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ShareActionProvider;

import com.google.android.gms.maps.GoogleMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


public class MapsActivity extends FragmentActivity implements LoadAllClubsInterface, ClubAdapter.OnClubClickListener {

    private GoogleMap mMap;
    private String[] titles;
    private DrawerLayout drawerLayout;
    private ShareActionProvider shareActionProvider;
    private ActionBarDrawerToggle drawerToggle;

    private RecyclerView clubRecycler;
    private RecyclerView.LayoutManager clubLayoutManager;
    private ArrayList<ClubBean> clubList = new ArrayList<ClubBean>();

    private RecyclerView.Adapter clubAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MyMapFragment()).commit();
        
        new LoadAllClubs(this).execute(); //load list in background from database

        titles = getResources().getStringArray(R.array.club_array);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        clubRecycler = (RecyclerView) findViewById(R.id.recycler_view);
        clubRecycler.setHasFixedSize(true);
        clubLayoutManager = new LinearLayoutManager(this);


        clubRecycler.setLayoutManager(clubLayoutManager);
        //clubRecycler.setAdapter(clubAdapter);
        //clubAdapter.notifyDataSetChanged();

//        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open_drawer, R.string.close_drawer) {
//
//            @Override
//            public void onDrawerClosed(View view) {
//                super.onDrawerClosed(view);
//                invalidateOptionsMenu();
//            }
//
//            @Override
//            public void onDrawerOpened(View drawerView) {
//                super.onDrawerOpened(drawerView);
//                invalidateOptionsMenu();
//            }
//        };
//
//        drawerLayout.addDrawerListener(drawerToggle);
//        getActionBar().setDisplayHomeAsUpEnabled(true);
//        getActionBar().setHomeButtonEnabled(true);
//
//        getFragmentManager().addOnBackStackChangedListener(
//            new FragmentManager.OnBackStackChangedListener(){
//                @Override
//                public void onBackStackChanged() {
//                    FragmentManager fragMan = getFragmentManager();
////                    Fragment fragment = fragMan.findFragmentByTag("visible_fragment");
////                    if(fragment instanceof ClubFragment){
////                        currentPosition = 0;
////                    }
////                    setActionBarTitle(currentPosition);
//                    //clubRecycler.setItemChecked(currentPosition, true);
//                }
//            }
//        );

    }



//    @Override
//    public void onSaveInstanceState(Bundle outState){
//        super.onSaveInstanceState(outState);
//        outState.putInt("position", currentPosition);
//    }



    @Override
    public void finishDataLoad(ArrayList<HashMap<String, String>> clubs) {
        Iterator<HashMap<String, String>> iterator = clubs.iterator();
        Map<String, String> map = new HashMap<String, String>();
        while (iterator.hasNext()){
            map = iterator.next();
            clubList.add(new ClubBean(map.get("name"),map.get("localization"), map.get("score")));
        }
        clubAdapter = new ClubAdapter(clubList, this, this);
        clubRecycler.setAdapter(clubAdapter);
    }
    
    @Override
    public void onClicked (ClubBean club) {
        ClubBean bean = club;
        Log.d("Name: ", bean.getClubName());
    }

//    private class DrawerItemClickListener implements ListView.OnItemClickListener {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//            //selectItem(position);
//        }
//    }


}
