
import controller.Command;
import view.ImageDisplay;
import view.SwingImageDisplay;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

    private final Map<String, Command> commands = new HashMap<>();
    private ImageDisplay imageDisplay;

    public MainFrame() {
        setTitle("Image Viewer");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(image());
        add(toolbar(), BorderLayout.SOUTH);
        setVisible(true);
    }

    public void add(Command command) {
        commands.put(command.getName(), command);
    }

    public ImageDisplay getImageDisplay() {
        return imageDisplay;
    }

    private Component image() {
        SwingImageDisplay imageDisplay = new SwingImageDisplay();
        this.imageDisplay = imageDisplay;
        return imageDisplay;
    }

    private Component toolbar() {
        JPanel panel = new JPanel();
        panel.add(button("←"));
        panel.add(button("→"));
        return panel;
    }

    private Component button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(execute());
        return button;
    }

    private ActionListener execute() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(((JButton) e.getSource()).getText()).execute();
            }
        };
    }
}
