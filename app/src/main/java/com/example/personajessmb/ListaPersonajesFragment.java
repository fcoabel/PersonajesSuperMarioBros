package com.example.personajessmb;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.personajessmb.databinding.FragmentListaPersonajesBinding;

import java.util.ArrayList;

public class ListaPersonajesFragment extends Fragment {

    private FragmentListaPersonajesBinding binding = null;
    private NavController navController = null;
    private ArrayList<Personaje> personajes = null;
    private rvAdapter adapter = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListaPersonajesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        inflarLista();

        adapter = new rvAdapter(personajes, getActivity(), personaje -> navToDetalles(view, personaje));
        binding.rv.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.rv.setAdapter(adapter);
    }

    // metodo para navegar al fragment detalles
    public void navToDetalles(View v, Personaje p){

        Bundle bundle = new Bundle();
        bundle.putSerializable("personaje",p);

        navController = NavHostFragment.findNavController(this);
        navController.navigate(R.id.action_listaPersonajesFragment_to_detallesPersonajesFragment, bundle);

    }

    // metodo encargado de inicializar la lista con los personajes y cargarlos
    private void inflarLista(){
        personajes = new ArrayList<Personaje>();
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/a/af/MvDK_NS_Mario.png/revision/latest?cb=20241009015633&path-prefix=es",
                "Mario",
                "correr",
                "arreglar tuberias"));
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/4/43/Luigi_NSMBUD_Artwork.webp/revision/latest?cb=20230911112822&path-prefix=es",
                "Luigi",
                "correr",
                "arreglar tuberias"));
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/4/4d/Yoshi_-_Mario_Party_10.png/revision/latest/scale-to-width-down/1000?cb=20171012174642&path-prefix=es",
                "Yoshi",
                "correr",
                "arreglar tuberias"));
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/6/62/Princesa_Peach_Super_Mario_Bros._Wonder.webp/revision/latest?cb=20230901155158&path-prefix=es",
                "Peach", "correr", "arreglar tuberias"));
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/8/89/Bowser_Artwork.webp/revision/latest?cb=20230506150335&path-prefix=es",
                "Bowser",
                "correr",
                "arreglar tuberias"));
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/2/2c/Toad_super_mario.png/revision/latest/scale-to-width-down/1000?cb=20161129175624&path-prefix=es",
                "Toad",
                "correr",
                "arreglar tuberias"));
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/8/8e/Daisy_Super_Mario_Bros._Wonder.webp/revision/latest?cb=20230901160824&path-prefix=es",
                "Daisy",
                "asfd",
                "sdf"
        ));
        personajes.add(new Personaje(
                "https://static.wikia.nocookie.net/mario/images/3/3c/Wario_MP100.png/revision/latest/scale-to-width-down/1000?cb=20171120161202&path-prefix=es",
                "Wario",
                "asfd",
                "asdf"
        ));
    }
}