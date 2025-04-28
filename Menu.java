import java.util.LinkedList;
import java.util.Queue;

public class Menu {
    private String name;
    private String imagePath; // ADD this
    private String description;
    private Queue<String> sutraQueue;

    public Menu(String name, String imagePath, String description) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
        this.sutraQueue = new LinkedList<>();
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getDescription() {
        return description;
    }
    
    public Queue<String> getSutraQueue() {
        return sutraQueue;
    }

    public void addStep(String step) {
        sutraQueue.add(step);
    }

    public String getSutraAsString() {
        StringBuilder sb = new StringBuilder();
        int stepNum = 1;
        for (String step : sutraQueue) {
            sb.append(stepNum).append(". ").append(step).append("\n");
            stepNum++;
        }
        return sb.toString();
    }
}
