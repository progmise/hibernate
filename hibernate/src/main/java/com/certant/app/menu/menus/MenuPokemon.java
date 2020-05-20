package com.certant.app.menu.menus;

import com.certant.app.menu.Menu;
import com.certant.app.menu.model.OptionKey;

import java.util.ArrayList;
import java.util.List;

public class MenuPokemon extends Menu {
    @Override
    protected List<OptionKey> getItemsList() {
        return new ArrayList<OptionKey>() {{
            add(new OptionKey(1, "Crear pokemon"));
            add(new OptionKey(2, "Editar pokemon"));
            add(new OptionKey(3, "Buscar pokemon"));
            add(new OptionKey(4, "Mostrar pokemons"));
            add(new OptionKey(5, "Volver"));
        }};
    }

    @Override
    protected String getTitle() {
        return "\nMenu Pokemons";
    }

    @Override
    protected boolean onItemSelected(Integer i) {
        switch (i) {
            case 1:
                Menu.notificator.notify("crearPokemon");
                return false;
            case 2:
                new MenuPokemonsEdition().showMenu();
                return false;
            case 3:
                new MenuPokemonsSearch().showMenu();
                return false;
            case 4: 
                Menu.notificator.notify("mostrarPokemons");
                return false;
            case 5:
                return false;
            default:
                return true;
        }
    }
}