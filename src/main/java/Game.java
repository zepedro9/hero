import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    Screen screen;
    Arena arena;

    private int x = 10;
    private int y = 10;

    public Game() {
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary

        } catch (IOException e) {
            e.printStackTrace();
        }

        arena = new Arena(20, 20, 10, 10);
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        while(true) {
            draw();
            KeyStroke key = screen.readInput();
            if (key.getKeyType().equals(KeyType.EOF)) break;
            processKey(key);
        }
    }

    private void moveHero(Position position) {
        if (arena.canHeroMove(position)) {
            arena.getHero().setPosition(position);
        }
    }

    private void processKey(KeyStroke key) throws IOException {
        switch(key.getKeyType()) {
            case ArrowUp:
                moveHero(arena.getHero().moveUp());
                break;
            case ArrowDown:
                moveHero(arena.getHero().moveDown());
                break;
            case ArrowLeft:
                moveHero(arena.getHero().moveLeft());
                break;
            case ArrowRight:
                moveHero(arena.getHero().moveRight());
                break;
            case Character:
                switch(key.getCharacter()) {
                    case 'w':
                        moveHero(arena.getHero().moveUp());
                        break;
                    case 's':
                        moveHero(arena.getHero().moveDown());
                        break;
                    case 'a':
                        moveHero(arena.getHero().moveLeft());
                        break;
                    case 'd':
                        moveHero(arena.getHero().moveRight());
                        break;
                    case 'q':
                        screen.close();
                        break;
                }
                break;
        }
    }
}