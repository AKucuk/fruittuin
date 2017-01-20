package com.example.abdullahkucuk.fruittuin.Activities;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.abdullahkucuk.fruittuin.Enumerations.FragmentEnum;
import com.example.abdullahkucuk.fruittuin.Factory.FragmentManager;
import com.example.abdullahkucuk.fruittuin.Fragments.ContactFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.FruittuinVanWestFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.OpeningstijdenFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.PlattegrondFragment;
import com.example.abdullahkucuk.fruittuin.Fragments.SpeurtochtFragments.StartFragment;
import com.example.abdullahkucuk.fruittuin.Global.Memory;
import com.example.abdullahkucuk.fruittuin.Global.Session;
import com.example.abdullahkucuk.fruittuin.Helpers.FragmentHelper;
import com.example.abdullahkucuk.fruittuin.R;
import com.firebase.client.Firebase;

import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager = new FragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);

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

        FragmentHelper.mFragment = fragmentManager.getFragment(FragmentEnum.START);
        ft.beginTransaction().replace(R.id.fragment_frame, FragmentHelper.mFragment).commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(getSupportFragmentManager().getBackStackEntryCount() > 0)
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
        if (id == R.id.action_exit) {
            //return true;

            new AlertDialog.Builder(this, R.style.MyDialogTheme)
                    //.setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Afsluiten")
                    .setMessage("Wilt u de app echt afsluiten?")
                    .setPositiveButton("Ja", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }

                    })
                    .setNegativeButton("Nee", null)
                    .show();

        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        android.support.v4.app.FragmentManager ft = getSupportFragmentManager();

        int id = item.getItemId();

        if (id == R.id.nav_speurtocht) {
            ft.beginTransaction().replace(R.id.fragment_frame, FragmentHelper.mFragment).addToBackStack("SPEURTOCHT").commit();
        } else if (id == R.id.nav_fruittuin) {
            ft.beginTransaction().replace(R.id.fragment_frame, fragmentManager.getFragment(FragmentEnum.FRUITTUIN_VAN_WEST)).addToBackStack("FRUITTUIN_VAN_WEST").commit();

        } else if (id == R.id.nav_plattegrond) {
            ft.beginTransaction().replace(R.id.fragment_frame, fragmentManager.getFragment(FragmentEnum.PLATTEGROND)).addToBackStack("PLATTEGROND").commit();

        } else if (id == R.id.nav_contact) {
            ft.beginTransaction().replace(R.id.fragment_frame, fragmentManager.getFragment(FragmentEnum.CONTACT)).addToBackStack("CONTACT").commit();

        } else if (id == R.id.nav_openingstijden) {
            ft.beginTransaction().replace(R.id.fragment_frame, fragmentManager.getFragment(FragmentEnum.OPENINGSTIJDEN)).addToBackStack("OPENINGSTIJDEN").commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
