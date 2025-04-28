import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Queue;

public class KrungKringCafeGUI {
    private JFrame frame;
    private Font promptFont;
    private JPanel menuPanel;
    private JScrollPane scrollPane;


    public KrungKringCafeGUI() {
        loadCustomFont(); // Load the Prompt font first!

        frame = new JFrame("KrungKring Cafe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(new BorderLayout());

        // Set cute font and pastel colors
        Color softBrown = new Color(234,228,224);

        // Title label
        JLabel titleLabel = new JLabel("KrungKring Kin Lao Gun", JLabel.CENTER);
        titleLabel.setFont(promptFont.deriveFont(Font.BOLD, 28f)); // use custom font
        titleLabel.setForeground(new Color(83, 61, 44));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Menu Panel
        menuPanel = new JPanel();
        menuPanel.setLayout(new GridLayout(0, 3, 10, 10));
        menuPanel.setBackground(softBrown);
        scrollPane = new JScrollPane(menuPanel);
        frame.add(scrollPane, BorderLayout.CENTER);

        for (Menu menu : MenuList.menuList) {
            // Create a panel for each menu item
            JPanel itemPanel = new JPanel();
            itemPanel.setLayout(new BorderLayout());
            itemPanel.setBackground(Color.WHITE);
            itemPanel.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 1)); // optional light border

            // Load and resize image
            ImageIcon icon = new ImageIcon(menu.getImagePath());
            Image img = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(img));
            imageLabel.setHorizontalAlignment(JLabel.CENTER);

            // Menu name label
            JLabel nameLabel = new JLabel(menu.getName(), JLabel.CENTER);
            nameLabel.setFont(promptFont.deriveFont(Font.PLAIN, 18f));
            nameLabel.setForeground(new Color(83, 61, 44));
            nameLabel.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0));
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            // Description label
            JLabel descLabel = new JLabel(menu.getDescription(), JLabel.CENTER);
            descLabel.setFont(promptFont.deriveFont(Font.PLAIN, 14f));
            descLabel.setForeground(new Color(152, 141, 130));
            descLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
            descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JPanel textPanel = new JPanel();
            textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
            textPanel.setBackground(Color.WHITE);
            textPanel.add(nameLabel);
            textPanel.add(descLabel);
        
            // Add to itemPanel
            itemPanel.add(imageLabel, BorderLayout.CENTER);
            itemPanel.add(textPanel, BorderLayout.SOUTH);

            // Add click event
            itemPanel.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    showMenuDetails(menu);
                }
            });

            menuPanel.add(itemPanel);
        }

        JScrollPane scrollPane = new JScrollPane(menuPanel);
        // scrollPane.setBorder(BorderFactory.createTitledBorder("Menu"));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Exit Button
        JButton exitButton = new JButton("Exit");
        exitButton.setFont(promptFont.deriveFont(Font.PLAIN, 20f));
        exitButton.setBackground(new Color(145,135,127));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(e -> System.exit(0));
        frame.add(exitButton, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    private void loadCustomFont() {
        try {
            promptFont = Font.createFont(Font.TRUETYPE_FONT, new File("fonts/Prompt/Prompt-Regular.ttf"));
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(promptFont);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
            // Fallback font if loading fails
            promptFont = new Font("SansSerif", Font.PLAIN, 18);
        }
    }

    private void showMenuDetails(Menu menu) {
        JFrame detailFrame = new JFrame(menu.getName());
        detailFrame.setSize(400, 500);
        detailFrame.setLayout(new BorderLayout());
    
        // Load big image
        ImageIcon icon = new ImageIcon(menu.getImagePath());
        Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(img));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
    
        // Sutra Text
        JTextArea sutraArea = new JTextArea(menu.getSutraAsString());
        sutraArea.setWrapStyleWord(true);
        sutraArea.setLineWrap(true);
        sutraArea.setEditable(false);
        sutraArea.setFont(new Font("Prompt", Font.PLAIN, 16));
        sutraArea.setBackground(new Color(234,228,224));
        JScrollPane scrollPane = new JScrollPane(sutraArea);
    
        detailFrame.add(imageLabel, BorderLayout.NORTH);
        detailFrame.add(scrollPane, BorderLayout.CENTER);
    
        JButton startButton = new JButton("Start Picking Ingredients");
        startButton.setFont(promptFont.deriveFont(Font.BOLD, 20f));
        startButton.setBackground(new Color(145,135,127));
        startButton.setForeground(Color.WHITE);
        startButton.addActionListener(e -> {
            Queue<DrinkStruct> picked = IngredientPicker.pickIngredients(menu.getSutraQueue());
            showFinalSelection(picked);
        });
    
        detailFrame.add(startButton, BorderLayout.SOUTH);
    
        detailFrame.setVisible(true);
    }
    
    private void showFinalSelection(Queue<DrinkStruct> picked) {
        JFrame resultFrame = new JFrame("Your Drink is Ready!");
        resultFrame.setSize(400, 400);
        resultFrame.setLayout(new BorderLayout());
    
        JTextArea resultArea = new JTextArea();
        resultArea.setWrapStyleWord(true);
        resultArea.setLineWrap(true);
        resultArea.setEditable(false);
        resultArea.setFont(promptFont.deriveFont(Font.PLAIN, 16));
        resultArea.setBackground(new Color(234,228,224));
    
        StringBuilder sb = new StringBuilder();
        sb.append("You've chosen:\n\n");
        for (DrinkStruct ingredient : picked) {
            sb.append("- ").append(ingredient.getName()).append("\n");
        }
        resultArea.setText(sb.toString());
    
        JScrollPane scrollPane = new JScrollPane(resultArea);
        resultFrame.add(scrollPane, BorderLayout.CENTER);
    
        JButton okButton = new JButton("OK");
        okButton.setFont(promptFont.deriveFont(Font.BOLD, 18f));
        okButton.setBackground(new Color(145,135,127));
        okButton.setForeground(Color.WHITE);
        okButton.addActionListener(e -> resultFrame.dispose());
    
        resultFrame.add(okButton, BorderLayout.SOUTH);
    
        resultFrame.setVisible(true);
    }    

    public static void main(String[] args) {
        new KrungKringCafeGUI();
    }
}
