package com.certant.app.gui;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Ability;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;
import com.certant.app.service.ServicePokemon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public class Printer {
    public void listPokemons() throws PokedexException {
        ServicePokemon sp = new ServicePokemon();
        
        //Se recuperan los Pokemons, ordenados por Id
        List<Pokemon> pokemons = new ArrayList<>(sp.getPokemons());
        Collections.sort(pokemons);
        
        for (int i = 0; i < pokemons.size(); i++) {
            List<Type> types = new ArrayList<>(pokemons.get(i).getTypes());
            
            String msg = StringUtils.join(
                    "Name: ", pokemons.get(i).getName(), ".\n",
                    "Type/s: ", StringUtils.join(types, ", ", 0, types.size()), ".\n",
                    "Level: ", pokemons.get(i).getLevelIsFound(), ".\n"
                    );
                    
            System.out.println(msg); 
        }
        
        System.out.println("\n----------------------------------------");
    }
    
    public void showNameTypeLevel(String name) throws PokedexException {
        ServicePokemon sp = new ServicePokemon();
        Pokemon pk = sp.getPokemon(name);
        List<Type> types = new ArrayList<>(pk.getTypes());
        
        //Se recuperan los Tipos, ordenados por Id
        Collections.sort(types);
        
        String msg = StringUtils.join(
                "Name: ", pk.getName(), ".\n",
                "Type/s: ", StringUtils.join(types, ", ", 0, types.size()), ".\n",
                "Level: ", pk.getLevelIsFound(), ".\n"
                );
        
        System.out.println(msg);
        
        System.out.println("\n----------------------------------------");
    }
    
    public void showAbilitiesEvolutions(String name) throws PokedexException {
        ServicePokemon sp = new ServicePokemon();
        Pokemon pokemon = sp.getPokemon(name);
        List<Ability> abilities = new ArrayList<>(pokemon.getAbilities());
        List<Evolution> evolutions = new ArrayList<>(pokemon.getEvolutions());
        
        //Se recuperan las Habilidad y Evoluciones, ordenados por Id
        Collections.sort(abilities);
        Collections.sort(evolutions);
        
        String msg = StringUtils.join("Name: ", pokemon.getName(), ".\n",
                "Abilities: ", StringUtils.join(abilities, ", ", 0, abilities.size()), ".\n",
                "Evolutions: ", StringUtils.join(evolutions, ", ", 0, evolutions.size()), ".\n"
                );
        
        System.out.println(msg);
        
        System.out.println("\n----------------------------------------");
    }
    
    public void showEvolutionsInformation(String name) throws PokedexException {
        ServicePokemon sp = new ServicePokemon();
        Pokemon pokemon = sp.getPokemon(name);
        List<Evolution> evolutions = new ArrayList<>(pokemon.getEvolutions());
        
        //Se recuperan las Evoluciones, ordenados por Id
        Collections.sort(evolutions);
        
        String msg = "Evolutions: \n";
        for (int i = 0; i < evolutions.size(); i++) {
            List<Type> tmpTp = new ArrayList<>(evolutions.get(i).getTypes());
            
            msg += StringUtils.join(i+1, ". ", 
                    "\t", "Name: ", evolutions.get(i).getName(), ".\n",
                    "\t", "Type/s: ", StringUtils.join(tmpTp, ", ", 0, tmpTp.size()), ".\n",
                    "\t", "Level: ", evolutions.get(i).getLevelNeeded(), ".\n\n"
                    );
        }
        
        System.out.println(msg);
        
        System.out.println("\n----------------------------------------");
    }
}