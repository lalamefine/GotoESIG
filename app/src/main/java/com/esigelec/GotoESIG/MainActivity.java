package com.esigelec.GotoESIG;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.esigelec.GotoESIG.Views.AjouterTrajetFragment;
import com.esigelec.GotoESIG.Views.ChercherTrajetFragment;
import com.esigelec.GotoESIG.Views.ConnexionInscriptionFragment;
import com.esigelec.GotoESIG.Views.EvaluerTrajetFragment;
import com.esigelec.GotoESIG.Views.MesTrajetFragment;
import com.esigelec.GotoESIG.Views.ProfilFragment;
import com.esigelec.GotoESIG.Views.StatistiquesFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
        , AjouterTrajetFragment.OnFragmentInteractionListener, ChercherTrajetFragment.OnFragmentInteractionListener
        , ConnexionInscriptionFragment.OnFragmentInteractionListener, EvaluerTrajetFragment.OnFragmentInteractionListener
        , MesTrajetFragment.OnFragmentInteractionListener, ProfilFragment.OnFragmentInteractionListener
        , StatistiquesFragment.OnFragmentInteractionListener{
    DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        // Ouverture de la vue de connexion
        this.startTransactionFragment(ConnexionInscriptionFragment.newInstance());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
// -------- Right menu -------
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


    //FOR FRAGMENTS
    // 1 - Declare fragment handled by Navigation Drawer
    private Fragment ajouterTrajetFragment;
    private Fragment chercherTrajetFragment;
    private Fragment evaluerTrajetFragment;
    private Fragment mesTrajetFragment;
    private Fragment profilFragment;
    private Fragment statistiquesFragment;

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();

        // 6 - Show fragment after user clicked on a menu item
        switch (id){
            case R.id.nav_ajoutertrajet :
                if (ajouterTrajetFragment == null) ajouterTrajetFragment = AjouterTrajetFragment.newInstance();
                this.startTransactionFragment(ajouterTrajetFragment);
                break;
            case R.id.nav_cherchertrajet:
                if (chercherTrajetFragment == null) chercherTrajetFragment = ChercherTrajetFragment.newInstance();
                this.startTransactionFragment(chercherTrajetFragment);
                break;
            case R.id.nav_evaluertrajet:
                if (evaluerTrajetFragment == null) evaluerTrajetFragment = EvaluerTrajetFragment.newInstance();
                this.startTransactionFragment(evaluerTrajetFragment);
                break;
            case R.id.nav_mestrajets:
                if (mesTrajetFragment == null) mesTrajetFragment = MesTrajetFragment.newInstance();
                this.startTransactionFragment(mesTrajetFragment);
                break;
            case R.id.nav_profil:
                if (profilFragment == null) profilFragment = ProfilFragment.newInstance();
                this.startTransactionFragment(profilFragment);
                break;
            case R.id.nav_quitter:
                //TODO: Quitter l'appli
                break;
            case R.id.nav_statistiques:
                if (statistiquesFragment == null) statistiquesFragment = StatistiquesFragment.newInstance();
                this.startTransactionFragment(statistiquesFragment);
                break;
            default:
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    // 3 - Generic method that will replace and show a fragment inside the MainActivity Frame Layout
    private void startTransactionFragment(Fragment fragment){
        if (!fragment.isVisible()){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_holder, fragment).commit();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
