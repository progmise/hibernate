package com.certant.app.gui;

import com.certant.app.exceptions.PokedexException;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.model.Type;
import com.certant.app.service.ServiceType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class Reader {    
    public Pokemon readPokemon() throws PokedexException {
        Pokemon pk = new Pokemon();
        ServiceType st = new ServiceType();
        Scanner sc = new Scanner(System.in);
        
        //Se recuperan los Tipos, ordenados por Id
        List<Type> types = new ArrayList<>(st.getTypes());
        List<Type> newTps = new ArrayList<>();
        int index;
        
        Collections.sort(types);
        
        /*
        Se quitan los espacios, luego se pasa a minusculas
        y se capitaliza el String ingresado
        */
        System.out.println("\nIngrese el nombre del Pokemon: \n");
        pk.setName(StringUtils.capitalize(
                        StringUtils.lowerCase(
                            StringUtils.trim(
                                sc.next())))
        );
        
        /*
        Los Tipos al estar ordenados, y visualmente mostrados a partir del 1,
        para recuperar dicho Tipo, va a ser necesario restar -1 al index
        */
        System.out.println("\nSeleccione el tipo del Pokemon: \n");
        
        for (int i = 0; i < types.size(); i++) {
            System.out.println(StringUtils.join(i+1, " - ", types.get(i).getName()));
        }
        index = sc.nextInt();
        newTps.add(types.get(index-1));
        types.remove(index-1);
        
        while (index != 0) {
            System.out.println("\nSeleccione otro tipo para el Pokemon o ingrese 0 para finalizar: \n");
            for (int i = 0; i < types.size(); i++) {
                System.out.println(StringUtils.join(i+1, " - ", types.get(i).getName()));
            }
            index = sc.nextInt();
            
            if (index-1 != -1) {
                newTps.add(types.get(index-1));
                types.remove(index-1);
            }
        }
        pk.setTypes(new HashSet<>(newTps));
        
        System.out.println("\nIngrese el nivel del Pokemon: \n");
        pk.setLevelIsFound(sc.nextInt());
        
        System.out.println("\n----------------------------------------");
        
        return pk;
    }
    
    public String readPokemonsName() {
        String name;
        Scanner sc = new Scanner(System.in);
        
        /*
        Se quitan los espacios, luego se pasa a minusculas
        y se capitaliza el String ingresado
        */        
        System.out.println("\nIngrese el nombre del Pokemon: \n");
        name = StringUtils.capitalize(
                        StringUtils.lowerCase(
                            StringUtils.trim(
                                sc.next())))
        ;
        
        System.out.println("\n----------------------------------------");
        
        return name;
    }
    
    public Integer readPokemonsLevel() {
        Integer level;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("\nIngrese el nivel del Pokemon: \n");
        level = sc.nextInt();

        System.out.println("\n----------------------------------------");
        
        return level;        
    }
    
    public Evolution readEvolution() throws PokedexException {
        Evolution evolution = new Evolution();
        ServiceType st = new ServiceType();
        Scanner sc = new Scanner(System.in);
        
        //Se recuperan los Tipos, ordenados por Id
        List<Type> types = new ArrayList<>(st.getTypes());
        List<Type> newTps = new ArrayList<>();
        int index;
        
        Collections.sort(types);

        /*
        Se quitan los espacios, luego se pasa a minusculas
        y se capitaliza el String ingresado
        */        
        System.out.println("\nIngrese el nombre de la Evolucion: \n");
        evolution.setName(StringUtils.capitalize(
                        StringUtils.lowerCase(
                            StringUtils.trim(
                                sc.next())))
        );
        
        /*
        Los Tipos al estar ordenados, y visualmente mostrados a partir del 1,
        para recuperar dicho Tipo, va a ser necesario restar -1 al index
        */        
        System.out.println("\nSeleccione el tipo de la Evolucion: \n");
        
        for (int i = 0; i < types.size(); i++) {
            System.out.println(StringUtils.join(i+1, " - ", types.get(i).getName()));
        }
        index = sc.nextInt();
        newTps.add(types.get(index-1));
        types.remove(index-1);
        
        while (index != 0) {
            System.out.println("\nSeleccione otro tipo para la Evolucion o ingrese 0 para finalizar: \n");
            for (int i = 0; i < types.size(); i++) {
                System.out.println(StringUtils.join(i+1, " - ", types.get(i).getName()));
            }
            index = sc.nextInt();
            
            if (index-1 != -1) {
                newTps.add(types.get(index-1));
                types.remove(index-1);
            }
        }
        evolution.setTypes(new HashSet<>(newTps));
        
        System.out.println("\nIngrese el nivel requerido para la Evolucion: \n");
        evolution.setLevelNeeded(sc.nextInt());
        
        System.out.println("\n----------------------------------------");
        
        return evolution;
    }    
}