package com.domain.kevin.myreportrash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.domain.kevin.myreportrash.ReporTrash_clases.Usuario;
import com.domain.kevin.myreportrash.ReporTrash_db.DBHandler;


public class Profile extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Usuario usuario;

    private Toolbar toolbar;
    private DrawerLayout drawer;
    private NavigationView navigationView;


    private View hView;
    private FragmentTransaction fragmentTransaction;
    private Menu_Fragment mf;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        final DBHandler db = new DBHandler(this);
        String username = getIntent().getStringExtra("username");

        //Bundle b = getIntent().getExtras();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        navigationView = (NavigationView) findViewById(R.id.nav_view);

        hView = navigationView.inflateHeaderView(R.layout.nav_header_profile);
        navigationView.setNavigationItemSelectedListener(this);

        //navigationView.setNavigationItemSelectedListener(this);

        usuario = db.getUsuario(username);
        loadProfile(usuario);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        loadMenu(usuario);
    }

    @Override
    public void onBackPressed() {
        if(getFragmentManager().getBackStackEntryCount()>0)
        {
            getFragmentManager().popBackStack();
        }else
            super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_reportar) {
            Reportar_Fragment reportar_fragment = new Reportar_Fragment();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,reportar_fragment)
                    .addToBackStack("home").commit();

        } else if (id == R.id.nav_myreports) {

        } else if (id == R.id.nav_cerrarSesion) {
            intent = new Intent(Profile.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void loadProfile(Usuario usuario){
        String nombre;
        String apellido;
        try{
            //View hView =  navigationView.inflateHeaderView(R.layout.nav_header_profile);
            nombre = usuario.getNombre();
            apellido = usuario.getApellido();

            TextView navNombre = (TextView)hView.findViewById(R.id.nombrenav);
            TextView navApellido = (TextView)hView.findViewById(R.id.apellidonav);

            navNombre.setText(nombre);
            navApellido.setText(apellido);

            navigationView.addHeaderView(hView);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void loadMenu(Usuario usuario){
            Bundle bundle = new Bundle();
            bundle.putString("username",usuario.getUsername());

            mf = new Menu_Fragment();
            mf.setArguments(bundle);
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.content_frame,mf);
            fragmentTransaction.commit();
    }
}
