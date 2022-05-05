package ro.tuc.pt;

import ro.tuc.pt.Presentation.ClientsView;
import ro.tuc.pt.Presentation.Controller;
import ro.tuc.pt.Presentation.View;

import javax.swing.*;

public class App
{
    public static void main( String[] args )
    {

        View view = new View("Orders Management");
        Controller controller= new Controller(view);
    }
}
