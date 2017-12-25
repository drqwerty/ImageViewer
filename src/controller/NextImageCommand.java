package controller;

import view.ImageDisplay;

public class NextImageCommand implements Command {

    private final ImageDisplay imageDisplay;

    public NextImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public String getName() {
        return "→";
    }

    @Override
    public void execute() {
        imageDisplay.display(imageDisplay.currentImage().next());
    }
}
