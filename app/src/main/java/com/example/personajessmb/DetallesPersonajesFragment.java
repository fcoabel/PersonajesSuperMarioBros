package com.example.personajessmb;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.fragment.app.Fragment;
import androidx.palette.graphics.Palette;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.personajessmb.databinding.FragmentDetallesPersonajesBinding;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class DetallesPersonajesFragment extends Fragment {
    private FragmentDetallesPersonajesBinding binding = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDetallesPersonajesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            // obtengo el objeto con el que inflo los detalles del bundle
            Personaje p = (Personaje) getArguments().getSerializable("personaje");
            if (p != null) {

                // Cargo la imagen del personaje
                Picasso.get().load(p.getImg()).into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                        binding.imgDetalles.setImageBitmap(bitmap);
                        // aqui obtengo el color dominante de la imagen para usarla de fondo en el fragment
                        Palette.from(bitmap).generate(palette ->
                                binding.getRoot().setBackgroundColor(ColorUtils.setAlphaComponent(
                                    palette.getDominantColor(
                                        ContextCompat.getColor(getContext(), R.color.colorLightFragment)
                                    ),
                                    50
                                )
                        ));
                    }

                    @Override
                    public void onBitmapFailed(Exception e, Drawable errorDrawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable placeHolderDrawable) {

                    }
                });

                binding.nombreDetalles.setText(p.getNombre());
                binding.desDetalles.setText(p.getDescripcion());
                binding.habDetalles.setText(p.getHabilidad());
            }
            // toast que indica el nombre del usuario seleccionado
            Toast.makeText(getContext(), getString(R.string.sSelectedCharacter) + " " + p.getNombre(), Toast.LENGTH_LONG).show();
        }
    }
}