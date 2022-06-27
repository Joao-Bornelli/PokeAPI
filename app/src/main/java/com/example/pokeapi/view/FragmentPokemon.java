package com.example.pokeapi.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.pokeapi.R;
import com.example.pokeapi.model.PokemonPOJO;

public class FragmentPokemon extends Fragment {
    PokemonPOJO pokemonPOJO;
    public FragmentPokemon(PokemonPOJO p){
        super(R.layout.fragment_pokemon);
        this.pokemonPOJO = p;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView pokemonName = getActivity().findViewById(R.id.showPokemonName);
        pokemonName.setText(pokemonPOJO.toString());
    }
}
