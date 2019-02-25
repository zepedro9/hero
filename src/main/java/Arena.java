import com.googlecode.lanterna.screen.Screen;

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

    public void draw(Screen screen) {
        hero.draw(screen);
    }

    public Hero getHero() {
        return hero;
    }

    public boolean canHeroMove(Position position) {
        if (position.getX() >= 0 && position.getX() <= width && position.getY() >= 0 && position.getY() <= height) return true;
        else return false;
    }
}
