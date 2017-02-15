package com.scythex14.nfcreader_writer.Activities;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;

import com.scythex14.nfcreader_writer.Adapters.HomePagerAdapter;
import com.scythex14.nfcreader_writer.Adapters.ViewPager;
import com.scythex14.nfcreader_writer.Fragments.Emulator;
import com.scythex14.nfcreader_writer.Fragments.Home;
import com.scythex14.nfcreader_writer.Fragments.Reader;
import com.scythex14.nfcreader_writer.Fragments.Settings;
import com.scythex14.nfcreader_writer.Fragments.Writer;

import Activities.R;


//Secure all tags via hashing with password
// UID setter ?
// bruteforce UID randomized everytime

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private HomePagerAdapter adapter;
    private TabLayout tabLayout;
    private Toolbar toolbar;
    /*
    final private int[] tabIcons = {
    };
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        //setupTabIcons();

        // change Hide/Show Status bar according to a NFC reception
        // Hide Status Bar
        if (Build.VERSION.SDK_INT < 16)
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);
        // Show it
        if (Build.VERSION.SDK_INT < 16)
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        else
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

    }
/*    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
    }*/

    private void setupViewPager(ViewPager viewPager) {
        adapter = new HomePagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new Emulator(), "EMUL");
        adapter.addFragment(new Reader(), "READ");
        adapter.addFragment(new Home(), "HOME");
        adapter.addFragment(new Writer(), "WRITE");
        adapter.addFragment(new Settings(), "SETTINGS");

        viewPager.setAdapter(adapter);
    }


}