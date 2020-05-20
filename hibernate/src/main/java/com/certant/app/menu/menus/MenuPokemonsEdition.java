package com.certant.app.menu.menus;

import com.certant.app.menu.Menu;
import com.certant.app.menu.model.OptionKey;

import java.util.ArrayList;
import java.util.List;

public class MenuPokemonsEdition extends Menu {
    @Override
    protected List<OptionKey> getItemsList() {
        return new ArrayList<OptionKey>() {{
            add(new OptionKey(1, "Editar nombre"));
            add(new OptionKey(2, "Agregar tipo"));
            add(new OptionKey(3, "Quitar tipo"));
            add(new OptionKey(4, "Editar nivel"));
            add(new OptionKey(5, "Agregar evolucion"));
            add(new OptionKey(6, "Volver"));
        }};
    }

    @Override
    protected String getTitle() {
        return "\nMenu edicion de Pokemons";
    }

    @Override
    protected boolean onItemSelected(Integer i) {
        switch (i) {
            case 1:
                Menu.notificator.notify("editarNombre");
                return false;
            case 2:
                Menu.notificator.notify("agregarTipo");
                return false;
            case 3:
                Menu.notificator.notify("quitarTipo");
                return false;
            case 4:
                Menu.notificator.notify("editarNivel");
                return false;
            case 5: 
                Menu.notificator.notify("agregarEvolucion");
                return false;
            case 6:
                return false;
            default:
                return true;
        }
    }
}