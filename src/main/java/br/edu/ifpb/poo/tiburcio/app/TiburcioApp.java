package br.edu.ifpb.poo.tiburcio.app;

import br.edu.ifpb.poo.tiburcio.controlador.TiburcioController;
import br.edu.ifpb.poo.tiburcio.ui.TiburcioUI;

public class TiburcioApp {
    public static void main(String[] args) {
        TiburcioUI ui = new TiburcioUI();
        TiburcioController controller = new TiburcioController(ui);
        controller.execute();
    }
}
