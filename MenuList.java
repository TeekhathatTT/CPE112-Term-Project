import java.util.LinkedList;

public class MenuList {
    public static LinkedList<Menu> menuList = new LinkedList<>();

    static {
        Menu Martini = new Menu("Martini", "images/cocoa.png", "The romantic drink");
        Martini.addStep("Gin");
        Martini.addStep("French vermouth");
    
        Menu Manhattan = new Menu("Manhattan", "images/matcha.png", "The serious drink");
        Manhattan.addStep("bourbon");
        Manhattan.addStep("Italian vermouth");

        Menu Sour = new Menu("Sour", "images/lemontea.png", "The morning drink");
        Sour.addStep("bourbon");
        Sour.addStep("lemon juice");
        Sour.addStep("golden sugar syrup");
    
        Menu OldFashioned = new Menu("Old Fasion", "images/lemontea.png", "The old drink");
        OldFashioned.addStep("bourbon");
        OldFashioned.addStep("golden syrup");
    
        Menu Negroni = new Menu("Negroni", "images/lemontea.png", "The work and play drink");
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
