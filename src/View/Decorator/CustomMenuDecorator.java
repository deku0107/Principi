package View.Decorator;

import javax.swing.*;
import java.util.List;

public abstract class CustomMenuDecorator extends MenuHome{

    protected MenuHome guestMenu;


    @Override
    public abstract List<JButton> getPulsanti();
}
