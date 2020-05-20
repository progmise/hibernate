package com.certant.app.factories;

import com.certant.app.utils.MediadorApp;
import com.certant.app.menu.Menu;
import com.certant.app.menu.menus.MenuPrincipal;
import com.certant.app.service.ServiceEvolution;
import com.certant.app.service.ServicePokemon;
import com.certant.app.gui.Detector;
import com.certant.app.gui.Reader;
import com.certant.app.gui.Printer;

public class Factory {
    public static Menu createMenu() {
        //Factory de clases necesarias para la GUI
        MediadorApp mediadorApp = new MediadorApp(
                new Reader(),
                new Detector(),
                new Printer(),
                new ServicePokemon(),
                new ServiceEvolution()
        );
        
        Menu menu = new MenuPrincipal();
        Menu.setObserver(mediadorApp);
        
        return menu;
    }
}