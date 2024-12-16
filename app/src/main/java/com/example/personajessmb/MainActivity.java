package com.example.personajessmb;

import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.personajessmb.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding = null;
    private DrawerLayout drawerLayout = null;
    private ActionBarDrawerToggle drawerToggle = null;
    private NavigationView navView = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);

        loadLanguage();

        // snack bar con la bienvenida al usuario
        Snackbar sb = Snackbar.make(findViewById(R.id.main), R.string.sWelcome, Snackbar.LENGTH_SHORT);
        sb.show();

        drawerLayout = findViewById(R.id.main);
        navView = findViewById(R.id.navigation_view);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.sMenuOpen, R.string.sMenuClose);
        binding.main.addDrawerListener(drawerToggle);
        drawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // gestiona los items seleccionados del navigation drawer y navega a los mismos
        navView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home){
                Navigation.findNavController(MainActivity.this, R.id.fragmentContainerView)
                        .navigate(R.id.listaPersonajesFragment);
            }
            if (id == R.id.nav_settings){
                Navigation.findNavController(MainActivity.this, R.id.fragmentContainerView)
                        .navigate(R.id.ajustesFragment);
            }
            drawerLayout.closeDrawers();
            return true;
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.fragmentContainerView);
        return navController.navigateUp() || super.onSupportNavigateUp();
    }

    // metodo para inflar el menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // metodo para detectar que item de los menus se han seleccionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (item.getItemId() == R.id.mAcerca) {
            mostrarDialogo();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // metodo para mostrar el alerDialog "Acerca de..."
    public void mostrarDialogo(){
        new AlertDialog.Builder(this)
                .setTitle(R.string.sAbout)
                .setMessage(getString(R.string.sAboutText) + "\n" + getString(R.string.Autor) + ".\n" + getString(R.string.sVersion))
                .setPositiveButton(R.string.sAccept,(dialog, which) -> dialog.dismiss())
                .show();
    }

    // metodo para obtener el idioma seleccionado por el usuario
    private void loadLanguage() {
        SharedPreferences prefs = getSharedPreferences("Settings", MODE_PRIVATE);
        String languageCode = prefs.getString("Language", "es");
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        config.setLocale(locale);
        resources.updateConfiguration(config, displayMetrics);
    }
}