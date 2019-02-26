import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;

    private Hero hero;
    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(int w, int h, int hx, int hy) {
        width = w;
        height = h;
        hero = new Hero(hx, hy);
        this.walls = createWalls();
        this.coins = createCoins();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#222222"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width+1, height+1), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#333333"));
        graphics.fillRectangle(new TerminalPosition(1, 1), new TerminalSize(width-1, height-1), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
    }

    public Hero getHero() {
        return hero;
    }

    public boolean canHeroMove(Position position) {
        if (position.getX() > 0 && position.getX() < width && position.getY() > 0 && position.getY() < height && isNotWall(position)) return true;
        else return false;
    }

    public boolean isNotWall(Position position) {
        boolean isNotWall = true;
        for (Wall wall : walls) {
            isNotWall = !position.equals(wall.getPosition());
            if(!isNotWall) break;
        }
        return isNotWall;
    }

    public boolean isHero(Position position) {
        return position.equals(hero.getPosition());
    }

    public boolean isCoin(Position position) {
        boolean isCoin = false;
        for (Coin coin : coins) {
            isCoin = position.equals(coin.getPosition());
            if(isCoin) break;
        }
        return isCoin;
    }

    public List<Coin> getCoins() {
        return coins;
    }

    public void setCoins(List<Coin> newCoins) {
        coins = newCoins;
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        int x, y;
        Position temp;
        for (int i = 0; i < 8; i++) {
            x = random.nextInt(width - 2) + 1;
            y = random.nextInt(height - 2) + 1;
            temp = new Position(x, y);
            if (!isHero(temp) && isNotWall(temp)) coins.add(new Coin(temp));
            else i--;
        }
        return coins;
    }
}
