import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class IngredientPicker {
    public static Queue<DrinkStruct> pickIngredients(Queue<String> components) {
        Queue<DrinkStruct> pickedIngredients = new LinkedList<>();

        for (String componentName : components) {
            DrinkStruct root = findRootStruct(componentName);
            if (root != null) {
                DrinkStruct chosen = pickIngredient(root);
                pickedIngredients.add(chosen);
            } else {
                // No tree structure for this component (like lemon juice, golden syrup)
                pickedIngredients.add(new DrinkStruct(componentName));
            }
        }

        return pickedIngredients;
    }

    private static DrinkStruct pickIngredient(DrinkStruct root) {
        List<DrinkStruct> children = root.getChildren();

        if (children.size() > 0) {
            String[] options = new String[children.size()];
            for (int i = 0; i < children.size(); i++) {
                options[i] = children.get(i).getName();
            }

            String selected = (String) JOptionPane.showInputDialog(
                    null,
                    "Pick for " + root.getName(),
                    "Choosing Ingredient",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    options[0]
            );

            if (selected != null) {
                for (DrinkStruct child : children) {
                    if (child.getName().equals(selected)) {
                        return pickIngredient(child);
                    }
                }
            }
        }
        // If no children, it's the final ingredient
        return root;
    }

    private static DrinkStruct findRootStruct(String name) {
        switch (name.toLowerCase()) {
            case "gin":
                return new Gin();
            case "bourbon":
                return new Bourbon();
            case "italian vermouth":
                return new ItalianVermouth();
            case "french vermouth":
                return new FrenchVermouth();
            case "campari":
                return new Campari();
            default:
                return null;
        }
    }
}
