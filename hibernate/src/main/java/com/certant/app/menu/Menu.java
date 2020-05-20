package com.certant.app.menu;

import com.certant.app.menu.model.OptionKey;
import com.certant.app.interfaces.INotificable;

import java.util.List;
import java.util.Scanner;

public abstract class Menu {
    protected static INotificable<String> notificator;

    public static void setObserver(INotificable<String> iNotificator) {
        notificator = iNotificator;
    }
    
    //Vista del menu
    private boolean innerShowMenu() {
        Scanner scn = new Scanner(System.in);
        List<OptionKey> listaDeTeclasOpciones = getItemsList();
        
        System.out.println(getTitle());
        
        for (OptionKey to : listaDeTeclasOpciones) {
            System.out.print(to.key);
            System.out.print(".- ");
            System.out.println(to.optionText);
        }
        
        System.out.println("");
        int option = scn.nextInt();
        
        //Validar las opciones ingresadas
        Integer found = -1;
        
        for (OptionKey to : listaDeTeclasOpciones) {
            if (to.key.equals(option)) {
                found = option;
            }
        }
        
        if (found >= 0) {
            return onItemSelected(found);
        } 
        else {
            throw new RuntimeException();
        }
    }
    
    //Este metodo ejecutara la vista del Menu
    public void showMenu() {
        for (boolean continuar = true; continuar;) {
            try {
                continuar = innerShowMenu();
            } 
            catch (Exception ex) {
                System.out.println("La opcion ingresada NO es válida");
                System.out.println("");
            }
        }
    }
    
    //Se definiran las opciones a mostrar del Menu
    protected abstract List<OptionKey> getItemsList();
    
    //Se definira el titulo que tendra el Menu
    protected abstract String getTitle();
    
    //De acuerdo a la opcion elegida, se definira el comportamiento
    protected abstract boolean onItemSelected(Integer i);
}