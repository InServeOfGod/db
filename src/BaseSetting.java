import javax.swing.*;
import java.awt.*;

public abstract class BaseSetting {
    private String title;
    private Icon icon;
    private Image image;
    private Color fg;
    private Color bg;
    private int[] size;
    private Font font;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Color getFg() {
        return fg;
    }

    public void setFg(Color fg) {
        this.fg = fg;
    }

    public Color getBg() {
        return bg;
    }

    public void setBg(Color bg) {
        this.bg = bg;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int[] getSize() {
        return size;
    }

    public void setSize(int[] size) {
        this.size = size;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    BaseSetting(){
        int[] size = {500, 500};
        Font f = new Font(Font.MONOSPACED, Font.PLAIN, 19);

        setTitle("");
        setIcon(null);
        setImage(null);
        setFg(Color.black);
        setBg(Color.white);
        setSize(size);
        setFont(f);
    }
}
