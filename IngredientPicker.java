import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.*;

public class IngredientPicker {
    public static Queue<DrinkStruct> pickIngredients(Queue<String> components) {
        Queue<DrinkStruct> pickedIngredients = new LinkedList<>();

        // Pick ingredient for each component
        while (!components.isEmpty()) {
            // Get component in the queue and get user's option
            String componentName = components.remove();
            DrinkStruct root = findRootStruct(componentName);
            if (root != null) {
                DrinkStruct chosen = IngredientDialog.pickIngredient(root);
                if (chosen != null) pickedIngredients.add(chosen);
            } else {
                // Component Tree doesn't exist. (Lemon juice, etc.)
                pickedIngredients.add(new DrinkStruct(componentName));
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
        // Store path history
        private final Stack<DrinkStruct> path = new Stack<>();
        private DrinkStruct result = null;

        public static DrinkStruct pickIngredient(DrinkStruct root) {
            IngredientDialog dialog = new IngredientDialog(root);
            dialog.setVisible(true); // Blocks until dialog is disposed
            return dialog.result;
        }

        // Picking form
        private IngredientDialog(DrinkStruct root) {
            path.push(root);
            setTitle("Choose Ingredient");
            setModal(true);
            setSize(400, 300);
            setLocationRelativeTo(null);
            setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());

            // Add path text at head
            pathLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
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

            if (current.getChildren().isEmpty()) {
                result = current;
                dispose();
                return;
            }

            if (path.size() > 1) {
                JButton backButton = new JButton("⬅ Back");
                backButton.setAlignmentX(Component.LEFT_ALIGNMENT);
                backButton.addActionListener(e -> {
                    path.pop();
                    showOptions(path.peek());
                });
                contentPanel.add(backButton);
                contentPanel.add(Box.createVerticalStrut(10));
            }

            for (DrinkStruct child : current.getChildren()) {
                JButton button = new JButton(child.getName());
                button.setAlignmentX(Component.LEFT_ALIGNMENT);
                button.addActionListener((ActionEvent e) -> {
                    path.push(child);
                    showOptions(child);
                });
                contentPanel.add(button);
                contentPanel.add(Box.createVerticalStrut(5));
            }

            contentPanel.revalidate();
            contentPanel.repaint();
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
