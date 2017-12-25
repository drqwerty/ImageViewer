package view;

import model.Image;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileImageLoader implements ImageLoader {

    private final static String[] imageExtensions = new String[]{"jpg", "png", "bmp"};
    private final File[] files;

    public FileImageLoader(String folder) {
        this.files = new File(folder).listFiles(withImageExtension());
    }

    @Override
    public Image load() {
        return imageAt(0);
    }

    private Image imageAt(final int index) {
        return new Image() {

            @Override
            public byte[] bitmap() {
                try {
                    return read(new FileInputStream(files[index]));
                } catch (IOException ex) {
                    return new byte[0];
                }
            }

            private byte[] read(FileInputStream is) throws IOException {
                byte[] buffer = new byte[1024];
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                while (true) {
                    int length = is.read(buffer);
                    if (length < 0) break;
                    os.write(buffer, 0, length);
                }
                return os.toByteArray();
            }

            @Override
            public Image next() {
                return (index < files.length - 1) ? imageAt(index + 1) : imageAt(0);
            }

            @Override
            public Image prev() {
                return (index > 0) ? imageAt(index - 1) : imageAt(files.length - 1);
            }
        };

    }

    private FilenameFilter withImageExtension() {
        return (File dir, String name) -> {
            for (String extension : imageExtensions) {
                if (name.toLowerCase().endsWith(extension)) return true;
            }
            return false;
        };
    }
}
