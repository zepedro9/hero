import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int width;
    private int height;

    private Hero hero;
    private List<Wall> walls;

    public Arena(int w, int h, int hx, int hy) {
        width = w;
        height = h;
        hero = new Hero(hx, hy);
        this.walls = createWalls();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#222222"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width+1, height+1), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#333333"));
        graphics.fillRectangle(new TerminalPosition(1, 1), new TerminalSize(width-1, height-1), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
    }

    public Hero getHero() {
        return hero;
    }

    public boolean canHeroMove(Position position) {
        if (position.getX() > 0 && position.getX() < width && position.getY() > 0 && position.getY() < height && isNotWall(position)) return true;
        else return false;
    }

    private boolean isNotWall(Position position) {
        boolean isNotWall = true;
        for (Wall wall : walls) {
            isNotWall = !position.equals(wall.getPosition());
            if(!isNotWall) break;
        }
        return isNotWall;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }
}
