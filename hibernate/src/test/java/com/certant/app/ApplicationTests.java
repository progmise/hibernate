package com.certant.app;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.gui.Printer;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.service.ServiceAbility;
import com.certant.app.service.ServiceEvolution;
import com.certant.app.service.ServicePokemon;
import com.certant.app.service.ServiceType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import org.mockito.Mockito;

public class ApplicationTests {
    //@Test
    void listarPokemons() throws PokedexException {
        //Arrange
        ServicePokemon sp = new ServicePokemon();
        Set<Pokemon> pokemons;
        Long size;
        //Act
        pokemons = sp.getPokemons();
        size = Long.valueOf(pokemons.size());
        //Assert
        assertEquals(11L, size);
    }
       
    //@Test
    void mostrarNombreTiposNivel() throws PokedexException {
        //Arrange
        Printer im = Mockito.mock(Printer.class);
        //Act
        im.showNameTypeLevel("SQUIRTLE");
        //Assert
        Mockito.verify(im, Mockito.times(1)).showNameTypeLevel(Mockito.any());
    }
    
    //@Test
    void mostrarHabilidadesEvoluciones() throws PokedexException {
        //Arrange
        Printer im = Mockito.mock(Printer.class);
        //Act
        im.showAbilitiesEvolutions("cHARMander");
        //Assert
        Mockito.verify(im, Mockito.times(1)).showAbilitiesEvolutions(Mockito.any());
    }
    
    //@Test
    void mostrarEvolucionesInformacion() throws PokedexException {
        //Arrange
        Printer im = Mockito.mock(Printer.class);
        //Act
        im.showEvolutionsInformation("bulbasaur");
        //Arrange
        Mockito.verify(im, Mockito.times(1)).showEvolutionsInformation(Mockito.any());
    }
    
    //@Test
    void listarPokemonAgregado() throws PokedexException {
        //Arrange
        ServicePokemon sp = new ServicePokemon();
        ServiceAbility sa = new ServiceAbility();
        ServiceType st = new ServiceType();
        Pokemon pokemon = new Pokemon();
        
        pokemon.setName("Meowth");
        pokemon.setAbilities(
                new HashSet<>(
                        Arrays.asList(
                                sa.getAbility(16L))));
        pokemon.setTypes(
                new HashSet<>(
                        Arrays.asList(
                                st.getType(1L))));
        pokemon.setLevelIsFound(8);
        
        sp.addPokemon(pokemon);
        
        Set<Pokemon> pokemons;
        Long size;
        //Act
        pokemons = sp.getPokemons();
        size = Long.valueOf(pokemons.size());
        //Assert
        assertEquals(12L, size);
        
        
        //Act
        List<Pokemon> pks = new ArrayList<>(pokemons);
        
        Collections.sort(pks);
        //Assert
        Assertions.assertAll("pokemon",
            () -> assertEquals(pokemon, pks.get(pks.size()-1))
        );
    }
    
    //@Test
    void actualizarNombreTipoNivel() throws PokedexException {
        //Arrange
        ServicePokemon sp = new ServicePokemon();
        ServiceType st = new ServiceType();
        ServiceEvolution se = new ServiceEvolution();
        
        String pkName = "Raichu";
        Long pkId = 2L;
        Long tpId = 5L;
        Integer lvl = 11;
        Pokemon pokemon = sp.getPokemon(pkId);
        
        Evolution evolution = new Evolution();
        evolution.setName("Persian");
        evolution.setTypes(
                new HashSet<>(
                        Arrays.asList(
                                st.getType(8L),st.getType(9L))));
        evolution.setLevelNeeded(40);
        
        se.addEvolution(evolution);
        
        //Act
        sp.modifyPokemonName(pkId, pkName);
        sp.addPokemonType(pkId, tpId);
        sp.modifyPokemonLevel(pkId, lvl);
        sp.addPokemonEvolution(pkId, evolution);
        //Assert
        Assertions.assertAll("modificaciones",
            () -> assertEquals(pkName, sp.getPokemon(pkId).getName()),
            () -> assertEquals(true, sp.getPokemon(pkId).getTypes().contains(st.getType(tpId))),
            () -> assertEquals(lvl, sp.getPokemon(pkId).getLevelIsFound()),
            () -> assertEquals(true, sp.getPokemon(pkId).getEvolutions().contains(evolution))
        );        
    }
}
