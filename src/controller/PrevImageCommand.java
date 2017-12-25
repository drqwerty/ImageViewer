package controller;

import view.ImageDisplay;

public class PrevImageCommand implements Command {

    private final ImageDisplay imageDisplay;

    public PrevImageCommand(ImageDisplay imageDisplay) {
        this.imageDisplay = imageDisplay;
    }

    @Override
    public String getName() {
        return "←";
    }

    @Override
    public void execute() {
        imageDisplay.display(imageDisplay.currentImage().prev());
    }
}
