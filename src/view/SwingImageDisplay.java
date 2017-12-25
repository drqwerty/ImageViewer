package view;

import model.Image;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Graphics;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class SwingImageDisplay extends JPanel implements ImageDisplay {

    private Image image;

    public SwingImageDisplay() {
        super(new BorderLayout());
    }

    @Override
    public void display(Image image) {
        this.image = image;
        this.removeAll();
        this.add(imagePanel());
        this.updateUI();
    }

    private Component imagePanel() {
        return new JPanel() {

            @Override
            public void paint(Graphics g) {
                int imgWidth = bitmap().getWidth(null);
                int imgHeight = bitmap().getHeight(null);

                double imgAspect = (double) imgHeight / imgWidth;

                int canvasWidth = this.getWidth();
                int canvasHeight = this.getHeight();

                double canvasAspect = (double) canvasHeight / canvasWidth;

                int x1 = 0; // top left X position
                int y1 = 0; // top left Y position
                int x2 = 0; // bottom right X position
                int y2 = 0; // bottom right Y position

                if (imgWidth < canvasWidth && imgHeight < canvasHeight) {
                    // the image is smaller than the canvas
                    x1 = (canvasWidth - imgWidth) / 2;
                    y1 = (canvasHeight - imgHeight) / 2;
                    x2 = imgWidth + x1;
                    y2 = imgHeight + y1;

                } else {
                    if (canvasAspect > imgAspect) {
                        y1 = canvasHeight;
                        // keep image aspect ratio
                        canvasHeight = (int) (canvasWidth * imgAspect);
                        y1 = (y1 - canvasHeight) / 2;
                    } else {
                        x1 = canvasWidth;
                        // keep image aspect ratio
                        canvasWidth = (int) (canvasHeight / imgAspect);
                        x1 = (x1 - canvasWidth) / 2;
                    }
                    x2 = canvasWidth + x1;
                    y2 = canvasHeight + y1;
                }
                g.clearRect(0, 0, this.getWidth(), this.getHeight());
                g.drawImage(bitmap(), x1, y1, x2, y2, 0, 0, imgWidth, imgHeight, null);
            }

            private java.awt.Image bitmap() {
                try {
                    return ImageIO.read(new ByteArrayInputStream(image.bitmap()));
                } catch (IOException ex) {
                    return null;
                }
            }
        };
    }

    @Override
    public Image currentImage() {
        return image;
    }
}
