package com.example.abdullahkucuk.fruittuin.Activities;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abdullahkucuk.fruittuin.Fragments.ContactFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.FruittuinVanWestFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.OpeningstijdenFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.PlattegrondFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.StartFragment;
import com.example.abdullahkucuk.fruittuin.Global.Memory;
import com.example.abdullahkucuk.fruittuin.Global.Session;
import com.example.abdullahkucuk.fruittuin.R;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        Session session = Memory.getInstance();
        session.setDateStart(new Date());

        android.support.v4.app.FragmentManager ft = getSupportFragmentManager();
        ft.beginTransaction().replace(R.id.fragment_frame, new StartFragment()).commit();

//        //successorChickenFragment:
//        BetweenAlternativeFragment successorChickenFragment = new BetweenAlternativeFragment();
//        successorChickenFragment.setTextPartOne(getResources().getString(R.string.picture_fragment_text_chicken_explanation));
//        //successorChickenFragment.setFragment(chickenFragment);
//
//        //chickenFragment
//        PictureFragment chickenFragment = new PictureFragment();
//        chickenFragment.setFragment(successorChickenFragment);
//        chickenFragment.setMessage(getResources().getString(R.string.picture_fragment_text_chicken));
//        chickenFragment.setToFindDutch(Arrays.asList("kip", "vogel"));
//        chickenFragment.setToFindEnglish(Arrays.asList("chicken", "bird"));
//        chickenFragment.setNumberOfTries(4);
//
//        //successorBlackBerryFragment:
//        BetweenAlternativeFragment successorBlackBerryFragment = new BetweenAlternativeFragment();
//        successorBlackBerryFragment.setTextPartOne(getResources().getString(R.string.picture_fragment_text_blackberry_explanation));
//        successorBlackBerryFragment.setFragment(chickenFragment);
//
//        //blackBerryFragment
//        PictureFragment blackBerryFragment = new PictureFragment();
//        blackBerryFragment.setFragment(successorBlackBerryFragment);
//        blackBerryFragment.setMessage(getResources().getString(R.string.picture_fragment_text_blackberry));
//        blackBerryFragment.setToFindDutch(Arrays.asList("braam", "fruit"));
//        blackBerryFragment.setToFindEnglish(Arrays.asList("blackberry", "fruit"));
//        blackBerryFragment.setNumberOfTries(4);
//
//        //successorCherryFragment:
//        BetweenAlternativeFragment successorCherryFragment = new BetweenAlternativeFragment();
//        successorCherryFragment.setTextPartOne(getResources().getString(R.string.picture_fragment_text_raspberry_explanation));
//        successorCherryFragment.setFragment(blackBerryFragment);
//
//        //raspBerryFragment
//        PictureFragment cherryPictureFragment = new PictureFragment();
//        cherryPictureFragment.setFragment(successorCherryFragment);
//        cherryPictureFragment.setMessage(getResources().getString(R.string.picture_fragment_text_cherry));
//        cherryPictureFragment.setToFindDutch(Arrays.asList("kers", "fruit"));
//        cherryPictureFragment.setToFindEnglish(Arrays.asList("cherry", "fruit"));
//        cherryPictureFragment.setNumberOfTries(4);
//
//        //successorRaspBerryFragment:
//        BetweenAlternativeFragment successorRaspBerryFragment = new BetweenAlternativeFragment();
//        successorRaspBerryFragment.setTextPartOne(getResources().getString(R.string.picture_fragment_text_raspberry_explanation));
//        successorRaspBerryFragment.setFragment(cherryPictureFragment);
//
//        //raspBerryFragment
//        PictureFragment raspberryPictureFragment = new PictureFragment();
//        raspberryPictureFragment.setFragment(successorRaspBerryFragment);
//        raspberryPictureFragment.setMessage(getResources().getString(R.string.picture_fragment_text_raspberry));
//        raspberryPictureFragment.setToFindDutch(Arrays.asList("framboos", "fruit"));
//        raspberryPictureFragment.setToFindEnglish(Arrays.asList("raspberry", "fruit"));
//        raspberryPictureFragment.setNumberOfTries(4);
//
//        //clayFragment
//        ClayFragment clayFragment = new ClayFragment();
//        clayFragment.setFragment(raspberryPictureFragment);
//
//        //groundtypeFragment
//        GroundtypeFragment groundtypeFragment = new GroundtypeFragment();
//        groundtypeFragment.setFragment(clayFragment);
//
//        //proefPaardenbloemFragment
//        ProefPaardenbloemFragment tasteDandelionFragment = new ProefPaardenbloemFragment();
//        tasteDandelionFragment.setFragment(groundtypeFragment);
//
//        //successorDandelion:
//        BetweenAlternativeFragment successorDandelionFragment = new BetweenAlternativeFragment();
//        successorDandelionFragment.setTextPartOne(getResources().getString(R.string.picture_fragment_text_paardenbloem_eetbaar));
//        successorDandelionFragment.setFragment(tasteDandelionFragment);
//
//        //dandelionPictureFragment
//        PictureFragment pictureFragment = new PictureFragment();
//        pictureFragment.setFragment(successorDandelionFragment);
//        pictureFragment.setMessage(getResources().getString(R.string.picture_fragment_text_paardenbloem));
//        pictureFragment.setToFindDutch(Arrays.asList("paardenbloem", "bloem"));
//        pictureFragment.setToFindEnglish(Arrays.asList("dandelion", "flower"));
//        pictureFragment.setNumberOfTries(4);
//        ft.beginTransaction().replace(R.id.fragment_frame, pictureFragment).commit();
//
//        //apple
////        PictureFragment pictureFragment = new PictureFragment();
////        pictureFragment.setFragment(fragment);
////        pictureFragment.setMessage(getResources().getString(R.string.picture_fragment_text_apple));
////        pictureFragment.setToFindDutch(Arrays.asList("appel", "fruit"));
////        pictureFragment.setToFindEnglish(Arrays.asList("apple", "fruit"));
////        pictureFragment.setNumberOfTries(4);
////        ft.beginTransaction().replace(R.id.fragment_frame, pictureFragment).commit();

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
        getMenuInflater().inflate(R.menu.main_drawer, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.support.v4.app.FragmentManager ft = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_speurtocht) {
            ft.beginTransaction().replace(R.id.fragment_frame, new StartFragment()).commit();

        } else if (id == R.id.nav_fruittuin) {
            ft.beginTransaction().replace(R.id.fragment_frame, new FruittuinVanWestFragment()).commit();

        } else if (id == R.id.nav_plattegrond) {
            ft.beginTransaction().replace(R.id.fragment_frame, new PlattegrondFragment()).commit();

        } else if (id == R.id.nav_contact) {
            ft.beginTransaction().replace(R.id.fragment_frame, new ContactFragment()).commit();

        } else if (id == R.id.nav_openingstijden) {
            ft.beginTransaction().replace(R.id.fragment_frame, new OpeningstijdenFragment()).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
