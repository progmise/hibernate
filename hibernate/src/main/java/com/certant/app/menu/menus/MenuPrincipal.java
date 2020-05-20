package com.certant.app.menu.menus;

import com.certant.app.menu.Menu;
import com.certant.app.menu.model.OptionKey;

import java.util.ArrayList;
import java.util.List;

public class MenuPrincipal extends Menu {
    @Override
    protected List<OptionKey> getItemsList() {
        return new ArrayList<OptionKey>() {{
            add(new OptionKey(1, "Pokemons"));
            add(new OptionKey(2, "Prueba02"));
            add(new OptionKey(3, "Prueba03"));
            add(new OptionKey(4, "Salir"));
        }};
    }

    @Override
    protected String getTitle() {
        return "\nMenu principal";
    }

    @Override
    protected boolean onItemSelected(Integer i) {
        switch (i) {
            case 1:
                new MenuPokemon().showMenu();
                return true;
            case 2:
                return false;
            case 3:
                return false;
            case 4:
                return false;
            default:
                return true;
        }
    }    
}