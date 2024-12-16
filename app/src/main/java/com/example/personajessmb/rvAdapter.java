package com.example.personajessmb;

import android.app.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personajessmb.databinding.PersonajeCardviewBinding;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class rvAdapter extends RecyclerView.Adapter<rvHolder> {

    private final ArrayList<Personaje> personajes;
    private final Context context;
    private onItemClickListener listener;

    // interfaz para gestionar cuando se pulsa sobre un objeto del recycler view
    public interface onItemClickListener{
        void onItemClick(Personaje p);
    }

    public rvAdapter(ArrayList<Personaje> personajes, Context context, onItemClickListener listener) {
        this.personajes = personajes;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public rvHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PersonajeCardviewBinding binding = PersonajeCardviewBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new rvHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull rvHolder holder, int position) {
        Personaje p = this.personajes.get(position);
        holder.bind(p);
        holder.itemView.setOnClickListener(view -> listener.onItemClick(p));
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }
}

class rvHolder extends RecyclerView.ViewHolder{

    private final PersonajeCardviewBinding binding;

    public rvHolder( PersonajeCardviewBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Personaje p){
        binding.tvNombre.setText(p.getNombre());
        Picasso.get().load(p.getImg()).into(binding.ImgV);
    }
}