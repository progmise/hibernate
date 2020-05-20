package com.certant.app.utils;

import com.certant.app.gui.Printer;
import com.certant.app.gui.Reader;
import com.certant.app.gui.Detector;
import com.certant.app.model.Evolution;
import com.certant.app.model.Pokemon;
import com.certant.app.service.ServiceEvolution;
import com.certant.app.service.ServicePokemon;
import com.certant.app.interfaces.INotificable;

public class MediadorApp implements INotificable<String> {    
    private final Reader reader;
    private final Detector detector;
    private final Printer printer;
    private final ServicePokemon servicePokemon;
    private final ServiceEvolution serviceEvolution;

    public MediadorApp( 
            Reader reader, Detector detector, Printer printer, 
            ServicePokemon servicePokemon, ServiceEvolution serviceEvolution) {
        this.reader = reader;
        this.detector = detector;
        this.printer = printer;
        this.servicePokemon = servicePokemon;
        this.serviceEvolution = serviceEvolution;
    }
    
    //Ejecuta las acciones, acorde a lo opcion elegida en alguno de los menus
    @Override
    public void notify(String comando) {
        switch (comando) {
            case "crearPokemon":
                try {
                    Pokemon pk = reader.readPokemon();
                    servicePokemon.addPokemon(pk);
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                } 
                finally {
                    System.out.println("");
                }
                break;
            case "editarNombre":
                try {
                    Long id = detector.detectPokemonId();
                    String nombre = reader.readPokemonsName();
                    servicePokemon.modifyPokemonName(id, nombre);
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;
            case "agregarTipo":
                try {
                    Long idPk = detector.detectPokemonId();
                    Long idTp = detector.detectTypeId(idPk, comando);
                    servicePokemon.addPokemonType(idPk, idTp);
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;
            case "quitarTipo":
                try {
                    Long idPk = detector.detectPokemonId();
                    Long idTp = detector.detectTypeId(idPk, comando);
                    servicePokemon.removePokemonType(idPk, idTp);
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;
            case "editarNivel":
                try {
                    Long idPk = detector.detectPokemonId();
                    Integer nivel = reader.readPokemonsLevel();
                    servicePokemon.modifyPokemonLevel(idPk, nivel);
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;                
            case "agregarEvolucion":
                try {
                    Long idPk = detector.detectPokemonId();
                    
                    Evolution ev = reader.readEvolution();
                    serviceEvolution.addEvolution(ev);
                    
                    servicePokemon.addPokemonEvolution(idPk, ev);
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;                
            case "recuperarNombreTiposNivel":
                try {
                    String nombre = reader.readPokemonsName();
                    printer.showNameTypeLevel(nombre);
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;
            case "recuperarHabilidadesEvoluciones":
                try {
                    String nombre = reader.readPokemonsName();
                    printer.showAbilitiesEvolutions(nombre);                    
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;
            case "recuperarEvolucionesInformacion":
                try {
                    String nombre = reader.readPokemonsName();
                    printer.showEvolutionsInformation(nombre);  
                } 
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;                
            case "mostrarPokemons":
                try {
                    printer.listPokemons();
                }
                catch (Exception ex) {
                    System.out.println("Error");
                    System.out.println(ex.getMessage());
                }
                break;
            default:
                System.out.print("Vino el comando: ");
                System.out.println(comando);
                break;
        }
    }
}