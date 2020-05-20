package com.certant.app.utils;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.factories.Factory;
import com.certant.app.manager.SessionManager;
import com.certant.app.model.Ability;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;
import com.certant.app.service.ServiceAbility;
import com.certant.app.service.ServiceEvolution;
import com.certant.app.service.ServicePokemon;
import com.certant.app.service.ServiceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.lang3.StringUtils;

public class App {
    public static void main(String[] args) {
        Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
        
        ServiceEvolution se = new ServiceEvolution();
        ServiceAbility sa = new ServiceAbility();
        ServicePokemon sp = new ServicePokemon();
        ServiceType st = new ServiceType();
        
        /*
        //List all pokemons
        Set<Pokemon> pokemons1 = sp.getPokemons();

        for (Pokemon tmpPokemon : pokemons1) {
        System.out.println(tmpPokemon.toString());
        }


        //Add a new pokemon
        Ability ay1 = sa.getAbility(2L);
        Type tp1 = st.getType(5L);

        Pokemon pk1 = new Pokemon();
        pk1.setName("Bellsprout");
        pk1.setLevelIsFound(10);
        pk1.getTypes().add(tp1);
        pk1.getAbilities().add(ay1);

        sp.addPokemon(pk1);


        //Modify pokemon's name
        sp.modifyPokemonName(6L, "Oddish");


        //Add pokemon's type
        sp.addPokemonType(2L, 1L);


        //Remove pokemon's type
        sp.removePokemonType(5L, 12L);


        //Add pokemon's evolution
        Evolution ev = new Evolution();
        Pokemon pk2 = sp.getPokemon(7L);

        ev.setName("Persian");
        ev.setPokemon(pk2);
        ev.setLevelNeeded(30);
        ev.getTypes().add(st.getType(1L));

        se.addEvolution(ev);
        sp.addPokemonEvolution(pk2.getId(), ev);
        */

        /*------------- NO VA -------------
        //Filter pokemons by name, types and level
        Set<Type> types = new HashSet<>();
        types.add(st.getType(5L));
        types.add(st.getType(8L));

        Set<Pokemon> pokemons1 = sp.filterPokemonsByNameTypesLevel("Bulbasaur", types, 4);

        for (Pokemon tmpPk : pokemons1) {
        System.out.println(tmpPk.toString());
        }


        //Filter pokemons by name, abilities and evolutions
        Set<Ability> abilities = new HashSet<>();
        abilities.add(sa.getAbility(5L));
        abilities.add(sa.getAbility(6L));

        Set<Evolution> evolutions = new HashSet<>();
        evolutions.add(se.getEvolution(5L));
        evolutions.add(se.getEvolution(6L));

        Set<Pokemon> pokemons2 = sp.filterPokemonsByNameAbilitiesEvolutions("Squirtle", abilities, evolutions);

        for (Pokemon tmpPk : pokemons2) {
        System.out.println(tmpPk.toString());
        }
        ----------------------------------
        */

        /*
        //Given a specific Pokemon’s name, its Name, Type/s and Level are retrieved from the Database
        Pokemon pk4 = sp.getPokemon("Charmander");
        List<Type> types = new ArrayList<>(pk4.getTypes());

        String msg1 = StringUtils.join(
        "Name: ", pk4.getName(), ".\n",
        "Type/s: ", StringUtils.join(types, ", ", 0, types.size()), ".\n",
        "Level: ", pk4.getLevelIsFound(), ".\n"
        );

        System.out.println(msg1);


        //Given a specific Pokemon’s name, its Abilities and Evolutions are retrieved from the Database
        Pokemon pk5 = sp.getPokemon("Squirtle");
        List<Ability> abilities = new ArrayList<>(pk5.getAbilities());
        List<Evolution> evolutions1 = new ArrayList<>(pk5.getEvolutions());

        String msg2 = StringUtils.join(
        "Name: ", pk5.getName(), ".\n",
        "Abilities: ", StringUtils.join(abilities, ", ", 0, abilities.size()), ".\n",
        "Evolutions: ", StringUtils.join(evolutions1, ", ", 0, evolutions1.size()), ".\n"
        );

        System.out.println(msg2);


        //Given a specific Pokemon’s name, all its Evolutions and their information (Name, Type/s and Level) are retrieved from the Database
        Pokemon pk6 = sp.getPokemon("Bulbasaur");
        List<Evolution> evolutions2 = new ArrayList<>(pk6.getEvolutions());

        String msg3 = "Evolutions: \n";
        for (int i = 0; i < evolutions2.size(); i++) {
        List<Type> tmpTp = new ArrayList<>(evolutions2.get(i).getTypes());

        msg3 += StringUtils.join(i+1, ". ",
        "\t", "Name: ", evolutions2.get(i).getName(), ".\n",
        "\t", "Type/s: ", StringUtils.join(tmpTp, ", ", 0, tmpTp.size()), ".\n",
        "\t", "Level: ", evolutions2.get(i).getLevelNeeded(), ".\n\n"
        );
        }

        System.out.println(msg3);

        System.out.println("\n");
        System.out.println("******************************************");
        System.out.println("Welcome to Pokedex-Lite");
        System.out.println("******************************************");
        System.out.println("\n");

        */
        Factory.createMenu().showMenu();

        SessionManager.getSessionFactory().close();        
    }
}