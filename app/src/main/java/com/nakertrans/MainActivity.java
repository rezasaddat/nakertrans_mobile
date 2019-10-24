package com.nakertrans;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.nakertrans.extras.FabOptions;
import com.nakertrans.master_data.BeritaMain;
import com.nakertrans.master_data.ProgramMain;
import com.nakertrans.master_data.SumberDataMain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements
            BottomNavigationView.OnNavigationItemSelectedListener,
            FloatingActionButton.OnClickListener{

    private BottomNavigationView navigationView;
    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.nav_view);
        floatingActionButton = findViewById(R.id.fabButton);
        // give action or listener
        navigationView.setOnNavigationItemSelectedListener(this);
        floatingActionButton.setOnClickListener(this);
        loadFragment(new SumberDataMain());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()) {
            case R.id.navigation_sumberdata:
                fragment = new SumberDataMain();
                break;
            case R.id.navigation_program:
                fragment = new ProgramMain();
                break;
            case R.id.navigation_berita:
                fragment = new BeritaMain();
                break;
        }
        return loadFragment(fragment);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tool_bar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item){
        ActivityOptions options = ActivityOptions.makeCustomAnimation(this,
                android.R.anim.slide_in_left, android.R.anim.fade_out);
        Intent intent;

        switch (item.getItemId()){
            case R.id.toolbar_settings :
                Toast.makeText(this, "Setting button", Toast.LENGTH_SHORT).show();
                intent = new Intent(this, SettingActivity.class);
                startActivity(intent, options.toBundle());
                return true;
        }

        return false;
    }

    public void onClick(@NonNull View view){
        //floating button
        Log.d("D", "onClick: Fab button primary");
        new FabOptions().show(getSupportFragmentManager(), "FabOptions");
    }

    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left,
                            android.R.anim.fade_out)
                    .addToBackStack(null)
                    .replace(R.id.frame_main, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed(){
        finish();
    }
}
