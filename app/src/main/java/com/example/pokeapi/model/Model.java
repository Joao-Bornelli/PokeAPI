package com.example.pokeapi.model;

import android.content.Context;
import android.media.ImageReader;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import com.example.pokeapi.MainActivity;
import com.example.pokeapi.model.db.DataBase;
import com.example.pokeapi.model.db.Pokemon;

import java.util.Locale;

public class Model implements Observer<String> {
    private static Model instance;
    private DataBase dataBase;
    private ModelListener listener;

    public static Model getInstance() {
        if(instance == null) instance = new Model();
        return instance;
    }
    private Model() {

    }
    public void init(Context context) {
        VolleyQueueManager.init(context);
        PokeApiReader.data.observeForever(this);
        dataBase = Room.databaseBuilder(context,
                DataBase.class, "pokemon-database").build();
    }

    public void searchPokemon(@NonNull String name) {
//        PokeApiReader.requestData(name);
        Runnable r = new Runnable() {
            @Override
            public void run() {
                doSearch(name.toLowerCase(Locale.ROOT));
            }
        };
    }

    private void doSearch(String name) {
        Pokemon pokemon = dataBase.getPokemonDao().get(name);
        if(pokemon == null)
            PokeApiReader.requestData(name);
        else
            broadcastData(pokemon);
    }

    @Override
    public void onChanged(String s) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Pokemon pokemon = PokemonFactory.create(s);
                dataBase.getPokemonDao().add(pokemon);
                Log.d("runnn", "run: funcionou");
            }
        };
        (new Thread(r)).start();

    }

    public void setListener(ModelListener listener){
        this.listener = listener;
    }


    private void broadcastData(Pokemon pokemon){
        PokemonPOJO pj = new PokemonPOJO();
        pj.name = pokemon.getName();
        pj.height = (int) pokemon.getHeight();
        pj.weight = (int) pokemon.getWeight();
//        pj.image = null;

        if(listener!=null)
            listener.onPokemonFound(pj);
    }


}
