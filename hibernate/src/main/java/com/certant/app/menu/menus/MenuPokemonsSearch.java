package com.certant.app.menu.menus;

import com.certant.app.menu.Menu;
import com.certant.app.menu.model.OptionKey;

import java.util.ArrayList;
import java.util.List;

public class MenuPokemonsSearch extends Menu {
    @Override
    protected List<OptionKey> getItemsList() {
        return new ArrayList<OptionKey>() {{
            add(new OptionKey(1, "Recuperar nombre, tipo/s y nivel"));
            add(new OptionKey(2, "Recuperar habilidades y evoluciones"));
            add(new OptionKey(3, "Recuperar evoluciones y su informacion"));
            add(new OptionKey(4, "Volver"));
        }};
    }

    @Override
    protected String getTitle() {
        return "\nMenu busqueda de Pokemons";
    }

    @Override
    protected boolean onItemSelected(Integer i) {
        switch (i) {
            case 1:
                Menu.notificator.notify("recuperarNombreTiposNivel");
                return false;
            case 2:
                Menu.notificator.notify("recuperarHabilidadesEvoluciones");
                return false;
            case 3:
                Menu.notificator.notify("recuperarEvolucionesInformacion");
                return false;
            case 4:
                return false;
            default:
                return true;
        }
    }
}