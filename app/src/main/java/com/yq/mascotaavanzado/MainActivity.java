package com.yq.mascotaavanzado;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.yq.mascotaavanzado.adapter.PageAdapter;
import com.yq.mascotaavanzado.database.BaseDatos;
import com.yq.mascotaavanzado.fragment.PerfilFragment;
import com.yq.mascotaavanzado.fragment.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    protected Toolbar toolbar;
    protected ImageView img3;
    protected TabLayout tabLayout;
    protected ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);

        toolbar = findViewById(R.id.toolbarcontacto);
        setSupportActionBar(toolbar);

        img3 = findViewById(R.id.icSecondary);


        if (toolbar != null){
            setSupportActionBar(toolbar);
        }
        setUpViewPager();

        FloatingActionButton floatingActionButton = findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(view -> Snackbar.make(view, "Se presionó el FAB", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        fragments.add(new RecyclerViewFragment());
        fragments.add(new PerfilFragment());
        return fragments;
    }

    private void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        BaseDatos database = new BaseDatos(getApplicationContext());
        Objects.requireNonNull(tabLayout.getTabAt(0)).setIcon(R.drawable.ic_house_name);
        Objects.requireNonNull(tabLayout.getTabAt(1)).setIcon(R.drawable.ic_heart_name);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()) {

            case R.id.mContacto:
                Intent intent = new Intent(this, ActivityContacto.class);
                startActivity(intent);
                break;

            case R.id.mAcerca:
                Intent j = new Intent(this, ActivityAcerca.class);
                startActivity(j);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
