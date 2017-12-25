
import controller.NextImageCommand;
import controller.PrevImageCommand;
import view.ImageLoader;
import view.FileImageLoader;

public class Main {

    public static void main(String[] args) {
        ImageLoader imageLoader = new FileImageLoader("Images");
        MainFrame mainFrame = new MainFrame();
        mainFrame.add(new NextImageCommand(mainFrame.getImageDisplay()));
        mainFrame.add(new PrevImageCommand(mainFrame.getImageDisplay()));
        mainFrame.getImageDisplay().display(imageLoader.load());
    }
}
