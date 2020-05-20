package com.certant.app.gui;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;
import com.certant.app.service.ServicePokemon;
import com.certant.app.service.ServiceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.collections4.ListUtils;
import org.apache.commons.lang3.StringUtils;

public class Detector {
    public Long detectPokemonId() throws PokedexException {
        Long id;
        ServicePokemon sp = new ServicePokemon();
        Scanner sc = new Scanner(System.in);
        
        /*
        Se recuperan los Pokemons, ordenados por Id, para que de este modo
        coincidan con el ingreso-1 para el indice correspondiente
        */
        List<Pokemon> pokemons = new ArrayList<>(sp.getPokemons());
        Collections.sort(pokemons);
        
        System.out.println("\nSeleccione el Pokemon que desea editar: \n");
        
        //Se listan los pokemons de la base de datos
        for (int i = 0; i < pokemons.size(); i++) {
            System.out.println(StringUtils.join(i+1, " - ", pokemons.get(i).getName()));
        }
        id = pokemons.get(sc.nextInt()-1).getId();
        
        System.out.println("\n----------------------------------------");
    
        return id;
    }

    public Long detectTypeId(Long pokemonId, String command) throws PokedexException {
        Long id = null;
        ServiceType st = new ServiceType();
        ServicePokemon sp = new ServicePokemon();
        Scanner sc = new Scanner(System.in);

        //Se recuperan los tipos y los tipos del Pokemon, ordenados por Id      
        List<Type> types = new ArrayList<>(st.getTypes());
        List<Type> pkTps = new ArrayList<>(sp.getPokemon(pokemonId).getTypes());
        Collections.sort(types);
        Collections.sort(pkTps);
        
        //Se declara esta lista para guardar el filtrado de tipos
        List<Type> ftrTps;
        
        //Se customiza el mensaje de acuerdo al comando ingresado
        if (command.equals("agregarTipo")) {
            /*
            Se asigna a la coleccion de tipos, removiendo los 
            tipos que ya existen en el Pokemon
            */
            ftrTps = ListUtils.removeAll(types, pkTps);
            
            System.out.println("\nSeleccione el tipo que desea agregar: \n");
            
            for (int i = 0; i < ftrTps.size(); i++) {
                System.out.println(StringUtils.join(i+1, " - ", ftrTps.get(i).getName()));
            }
            id = ftrTps.get(sc.nextInt()-1).getId();         
        } 
        else {
            //Se asignan los tipos que existen en ambas colecciones
            ftrTps = ListUtils.intersection(types, pkTps);
            
            System.out.println("\nSeleccione el tipo que desea quitar: \n");
            
            for (int i = 0; i < ftrTps.size(); i++) {
                System.out.println(StringUtils.join(i+1, " - ", ftrTps.get(i).getName()));      
            }       
            id = ftrTps.get(sc.nextInt()-1).getId();
        }
        
        System.out.println("\n----------------------------------------");

        return id;
    }    
}