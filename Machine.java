import java.util.List;
import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

class Machine {
    // ? Methods
    public DrinkStruct pickIngredient(DrinkStruct root) {
        List<DrinkStruct> children = root.getChildren();

        // If options exist
        if (children.size() > 0) {
            Scanner sc = new Scanner(System.in);

            // Show options
            int optionNum;
            while (true) {
                System.out.println("== Options for " + root.getName());
                for (int i=0; i<children.size(); i++) {
                    System.out.printf("%d.) %s\n", i+1, children.get(i).getName());
                }
                // Get user option
                System.out.print("Pick: ");
                optionNum = sc.nextInt();

                // Input validation
                if (optionNum<=children.size() && optionNum>0) break;
                System.out.println("\n== Please input the specified options!\n");
            }
            return pickIngredient(children.get(optionNum-1));
        }
        // No options found. (Real Ingredient found)
        return root;
    }

    // cQ = Component Queue (Basically order for ingredients)
    public void process(Queue<DrinkStruct> cQ) {
        Queue<DrinkStruct> output = new LinkedList<>();
        
        while (!cQ.isEmpty()) {
            // Enqueue and proceed to pick ingredient
            DrinkStruct component = cQ.remove();
            DrinkStruct ingredient = pickIngredient(component);
            output.add(ingredient);
            System.out.printf("\n==You've picked %s for %s!\n\n", ingredient.getName(), component.getName());
        }

        System.out.println("Enjoy your drink!");
        while (!output.isEmpty()) {
            DrinkStruct ingredient = output.remove();
            System.out.println(ingredient.getName());
        }
    }

    public static void main(String[] args) {
        DrinkStruct gin = new Gin();
        DrinkStruct bourbon = new Bourbon();
        DrinkStruct itVermouth = new ItalianVermouth();
        DrinkStruct frVermouth = new FrenchVermouth();
        DrinkStruct campari = new Campari();


        // Add process (Queue for demo)
        Queue<DrinkStruct> components = new LinkedList<>();
        components.add(gin);
        components.add(bourbon);
        components.add(itVermouth);
        components.add(frVermouth);
        components.add(campari);

        Machine machine = new Machine();

        machine.process(components);
    }
}