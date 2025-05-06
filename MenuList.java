import java.util.LinkedList;
import java.nio.*;

public class MenuList {
    public static LinkedList<Menu> menuList = new LinkedList<>();

    static {
        Menu Martini = new Menu("Martini", "./images/Martini.png", "The romantic drink");
        Martini.addStep("Gin");
        Martini.addStep("French vermouth");
    
        Menu Manhattan = new Menu("Manhattan", "./images/Manhattan.png", "The serious drink");
        Manhattan.addStep("bourbon");
        Manhattan.addStep("Italian vermouth");

        Menu Sour = new Menu("Sour", "./images/Sour.png", "The morning drink");
        Sour.addStep("bourbon");
        Sour.addStep("lemon juice");
        Sour.addStep("golden sugar syrup");
    
        Menu OldFashioned = new Menu("Old Fasion", "./images/Old_fashioned.png", "The old drink");
        OldFashioned.addStep("bourbon");
        OldFashioned.addStep("golden syrup");
    
        Menu Negroni = new Menu("Negroni", "./images/Negroni.png", "The work and play drink");
        Negroni.addStep("Gin");
        Negroni.addStep("Italian vermouth");
        Negroni.addStep("Campari");
    
        menuList.add(Martini);
        menuList.add(Manhattan);
        menuList.add(Sour);
        menuList.add(OldFashioned);
        menuList.add(Negroni);
    }
    
}
