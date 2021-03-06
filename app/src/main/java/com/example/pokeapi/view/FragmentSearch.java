package com.example.pokeapi.view;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.pokeapi.R;
import com.example.pokeapi.model.Model;


public class FragmentSearch extends Fragment {
    public FragmentSearch() {
        super(R.layout.fragment_search);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btSearch = getActivity().findViewById(R.id.btSearch);
        EditText pokemonName = getActivity().findViewById(R.id.edtPokemonName);
        btSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Model.getInstance().searchPokemon(pokemonName.getText().toString());
            }
        });
    }
}
