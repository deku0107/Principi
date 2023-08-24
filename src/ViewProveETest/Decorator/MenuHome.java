package ViewProveETest.Decorator;

import ViewProveETest.Home;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public abstract class MenuHome {

    protected List<JButton> pulsanti = new ArrayList<>();
    protected Home finestra;

    public List<JButton> getPulsanti() {
        return pulsanti;
    }

    public Home getFinestra() {return finestra;}
}
