package main.game.core.map;

import java.util.Objects;

/**
 * represents a position  ,absolute or relative , WATCH BECAUSE THE DISTINCTION IS VERY IMPORTANT
 */
public class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Position setX(int x) {
        return new Position(x,this.y);
    }

    public Position setY(int y) {
        return new Position(this.x,y);
    }
    public Position addX(int offset) {
        return new Position(x+offset,this.y);
    }

    public Position addY(int offset) {
        return new Position(this.x+offset,y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
