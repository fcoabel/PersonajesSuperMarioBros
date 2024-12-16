package com.example.personajessmb;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personajessmb.databinding.FragmentAjustesBinding;

import java.util.Locale;

public class AjustesFragment extends Fragment {
    private FragmentAjustesBinding binding = null;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAjustesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String currentLanguage = getActivity().getSharedPreferences("Settings", Context.MODE_PRIVATE).getString("Language", "es");
        binding.switchLanguage.setChecked(currentLanguage.equals("en"));

        // Configurar el listener para cambiar el idioma de la aplicación
        binding.switchLanguage.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                changeLanguage("en");
            } else {
                changeLanguage("es");
            }
        });
    }
    // metodo para cambiar el lenguaje en las sharedPreferences pasandole el string por parametro
    private void changeLanguage(String languageCode) {
        // Configura nuevo idioma
        Locale locale = new Locale(languageCode);
        Locale.setDefault(locale);

        Resources resources = getResources();
        Configuration config = resources.getConfiguration();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();

        config.setLocale(locale);
        resources.updateConfiguration(config, displayMetrics);

        // Guarda el idioma en SharedPreferences
        saveLanguage(languageCode);

        // Reinicia la actividad para aplicar cambios
        if (getActivity() != null) {
            getActivity().recreate();
        }
    }

    // Metodo para guardar el idioma seleccionado
    private void saveLanguage(String languageCode) {
        SharedPreferences prefs = getActivity().getSharedPreferences("Settings", getContext().MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Language", languageCode);
        editor.apply();
    }
}