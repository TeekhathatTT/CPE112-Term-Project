import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class IngredientPicker {
    public static Queue<DrinkStruct> pickIngredients(Queue<String> components) {
        // cQ = components queue. This is a copy of original components
        // Queue is reference type, if I don't make copy, it will affect the original one
        Queue<String> cQ = new LinkedList<>(components);
        Queue<DrinkStruct> pickedIngredients = new LinkedList<>();

        // Pick ingredient for each component
        while (!cQ.isEmpty()) {
            // Get component in the queue and get user's option
            String current = cQ.remove();
            DrinkStruct root = findRootStruct(current);
            if (root != null) {
                DrinkStruct chosen = IngredientDialog.pickIngredient(root);
                if (chosen != null) pickedIngredients.add(chosen);
            } else {
                // Component Tree doesn't exist. (Lemon juice, etc.)
                pickedIngredients.add(new DrinkStruct(current));
            }
        }
        return pickedIngredients;
    }

    private static DrinkStruct findRootStruct(String name) {
        switch (name.toLowerCase()) {
            case "gin": return new Gin();
            case "bourbon": return new Bourbon();
            case "italian vermouth": return new ItalianVermouth();
            case "french vermouth": return new FrenchVermouth();
            case "campari": return new Campari();
            default: return null;
        }
    }

    static class IngredientDialog extends JDialog {
        private final JPanel contentPanel = new JPanel();
        private final JLabel pathLabel = new JLabel();
        private final Stack<DrinkStruct> path = new Stack<>(); // Store path history
        private DrinkStruct result = null;

        public static DrinkStruct pickIngredient(DrinkStruct root) {
            IngredientDialog dialog = new IngredientDialog(root);
            dialog.setVisible(true);
            return dialog.result;
        }

        // Picking form
        private IngredientDialog(DrinkStruct root) {
            path.push(root); // Update path
            setTitle("Choose Ingredient");
            setModal(true); // Block all other window until it's closed
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            // Add path at the head of the box
            pathLabel.setFont(new Font("Prompt", Font.PLAIN, 14));
            pathLabel.setForeground(AppColors.DARK_BROWN);
            pathLabel.setBackground(AppColors.LIGHT_BROWN);
            add(pathLabel, BorderLayout.NORTH);

            contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
            contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(contentPanel, BorderLayout.CENTER);

            // Show options in current Node
            showOptions(path.peek());
        }

        private void showOptions(DrinkStruct current) {
            // Claer panel
            contentPanel.removeAll();
            updatePathLabel();

            // Leaf node found, close the window
            if (current.getChildren().isEmpty()) {
                result = current;
                dispose();
                return;
            }

            // Show back button only when user moved from root node
            if (path.size() > 1) {
                JButton backButton = new JButton("⬅ Back");
                backButton.setBackground(AppColors.LIGHT_BROWN);
                backButton.setForeground(AppColors.DARK_BROWN);
                backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
                backButton.addActionListener(e -> {
                    path.pop(); // Backtrack
                    showOptions(path.peek());
                });
                contentPanel.add(backButton);
                contentPanel.add(Box.createVerticalStrut(10)); // Similar to CSS margin
            }

            // Show option buttons
            for (DrinkStruct child : current.getChildren()) {
                JButton button = new JButton(child.getName());
                button.setBackground(AppColors.MEDIUM_BROWN);
                button.setForeground(Color.WHITE);
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                // If option is clicked, add to path & go deeper into that option.
                button.addActionListener((ActionEvent e) -> {
                    path.push(child);
                    showOptions(child);
                });
                contentPanel.add(button);
                contentPanel.add(Box.createVerticalStrut(5)); // Margin
            }

            contentPanel.revalidate(); // Recalculate layout
            contentPanel.repaint(); // Redraw whole thing or it will overlap with old ones ;)
        }

        private void updatePathLabel() {
            java.util.List<String> names = new ArrayList<>();
            for (DrinkStruct node : path) {
                names.add(node.getName());
            }
            pathLabel.setText("Path: " + String.join(" > ", names));
        }
    }
}
