package dafla.dafla;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import static android.R.attr.id;

public class MainMenuActivity extends AppCompatActivity {

    private GoogleMap mMap;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.right_in, R.anim.left_out);
        setContentView(R.layout.activity_main_menu);

        if (savedInstanceState == null) {
            activity_map_navi f1= new activity_map_navi();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.content, f1);
            fragmentTransaction.commit();
        }

        BottomNavigationViewEx navigation = (BottomNavigationViewEx) findViewById(R.id.navigation);
        navigation.setTextVisibility(false);
        navigation.setIconSize(32,32);
        navigation.setIconSizeAt(1,100,40);
        navigation.setIconMarginTop(0,15);
        navigation.setIconMarginTop(1,7);
        navigation.setIconMarginTop(2,15);
        Menu menu = navigation.getMenu();
        MenuItem menuItem = menu.getItem(1);
        menuItem.setChecked(true);

        navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.content);
                switch (item.getItemId()) {
                    case R.id.navigation_camera:
                        Intent intent0 = new Intent(MainMenuActivity.this, CameraActivity.class);
                        startActivity(intent0);
                        finish();
                        return true;
                    case R.id.navigation_map:
                        activity_map_navi map = new activity_map_navi();
                        if(!currentFragment.getClass().getName().equalsIgnoreCase(map.getClass().getName())) {
                            FragmentManager manager = getSupportFragmentManager();
                            manager.beginTransaction().replace(R.id.content,
                                    map,
                                    map.getTag())
                                    .addToBackStack(null)
                                    .commit();
                            return true;
                            } else {
                                //currentFragment es igual a newFragment
                            return true;
                            }
                    case R.id.navigation_profile:
                        activity_profile_navi profile = new activity_profile_navi();
                        if(!currentFragment.getClass().getName().equalsIgnoreCase(profile.getClass().getName())) {
                            FragmentManager manager1 = getSupportFragmentManager();
                            manager1.beginTransaction().replace(R.id.content,
                                    profile,
                                    profile.getTag())
                                    .addToBackStack(null)
                                    .commit();
                            return true;
                        } else{
                                //currentFragment es igual a newFragment
                            return true;
                        }
                }
                return true;
            }
        });

    }
    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        for (Fragment frag : fm.getFragments()) {
            if (frag == null) {
                super.onBackPressed();
                finish();
                return;
            }
            if (frag.isVisible()) {
                FragmentManager childFm = frag.getChildFragmentManager();
                if (childFm.getFragments() == null) {
                    super.onBackPressed();
                    finish();
                    return;
                }
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                } else {

                    fm.popBackStack();
                    if (fm.getFragments().size() <= 1) {
                        finish();
                    }
                    return;
                }

            }
        }
    }

}

