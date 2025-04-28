import java.util.ArrayList;
import java.util.List;

public class DrinkStruct {
    private String name;
    private List<DrinkStruct> children;

    public DrinkStruct(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(DrinkStruct child) {
        children.add(child);
    }

    public String getName() {
        return name;
    }

    public List<DrinkStruct> getChildren() {
        return children;
    }

    public void printTree(String indent) {
        System.out.println(indent + "- " + name);
        for (DrinkStruct child : children) {
            child.printTree(indent + "   ");
        }
    }

    public static void main(String[] args) {
        DrinkStruct gin = new Gin();
        DrinkStruct bourbon = new Bourbon();
        DrinkStruct itVermouth = new ItalianVermouth();
        DrinkStruct frVermouth = new FrenchVermouth();
        DrinkStruct campari = new Campari();

        System.out.println("=== Gin ===");
        gin.printTree("");
        System.out.println("\n=== Bourbon ===");
        bourbon.printTree("");
        System.out.println("\n=== Italian Vermouth ===");
        itVermouth.printTree("");
        System.out.println("\n=== French Vermouth ===");
        frVermouth.printTree("");
        System.out.println("\n=== Campari ===");
        campari.printTree("");
    }
}