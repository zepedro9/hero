import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Arena {
    private int width;
    private int height;

    private Hero hero;

    public Arena(int w, int h, int hx, int hy) {
        width = w;
        height = h;
        hero = new Hero(hx, hy);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#222222"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width+1, height+1), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#333333"));
        graphics.fillRectangle(new TerminalPosition(1, 1), new TerminalSize(width-1, height-1), ' ');
        hero.draw(graphics);
    }

    public Hero getHero() {
        return hero;
    }

    public boolean canHeroMove(Position position) {
        if (position.getX() > 0 && position.getX() < width && position.getY() > 0 && position.getY() < height) return true;
        else return false;
    }
}
