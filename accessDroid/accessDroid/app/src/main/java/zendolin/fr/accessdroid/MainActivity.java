package zendolin.fr.accessdroid;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        if(savedInstanceState == null){
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            MainFragment mainF = new MainFragment();
            ft.replace(R.id.fragment_container, mainF, "MainFragment");

            ft.commit();
        }

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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        switch (id){
            case R.id.nav_accueil:
                Log.d("nav_accueil", "Test");
                MainFragment mainF = new MainFragment();
                ft.replace(R.id.fragment_container, mainF, "MainFragment");
                break;
            case R.id.nav_images:
                Log.d("nav_images", "Test");
                ImageFragment imgF = new ImageFragment();
                ft.replace(R.id.fragment_container, imgF, "ImageFragment");
                break;
            case R.id.nav_formulaires :
                Log.d("nav_formulaires", "Test");
                FormFragment formF = new FormFragment();
                ft.replace(R.id.fragment_container, formF, "FormFragment");
                break;
            case R.id.nav_boutons :
                Log.d("nav_boutons", "Test");
                BoutonFragment btnF = new BoutonFragment();
                ft.replace(R.id.fragment_container, btnF, "ImageFragment");
                break;

            case R.id.nav_structure :
                Log.d("nav_structure", "Test");
                StructureFragment structF = new StructureFragment();
                ft.replace(R.id.fragment_container, structF, "StructureFragment");
                break;

            case R.id.nav_groupe :
                Log.d("nav_groupe", "Test");
                GroupFragment groupF = new GroupFragment();
                ft.replace(R.id.fragment_container, groupF, "GroupFragment");
                break;

            case R.id.nav_texte :
                Log.d("nav_texte", "Test");
                TextFragment textF = new TextFragment();
                ft.replace(R.id.fragment_container, textF, "TextFragment");
                break;

            case R.id.nav_outils :
                Log.d("nav_outils", "Test");
                ToolFragment toolF = new ToolFragment();
                ft.replace(R.id.fragment_container, toolF, "ToolFragment");
                break;

            default :
                break;
        }

        ft.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
