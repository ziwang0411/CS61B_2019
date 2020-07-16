package byow.lab12;

import org.junit.Test;

import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;
    private static final Random RANDOM = new Random();


    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {
        if (s<2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }
        for (int i = 0; i<s*2;i++) {
            int row = i+p.y;
            for (int j = 0; j < hexRowHeight(s,i);j++) {
                int col = p.x + hexRowOffset(s,i)+j;
                Position newP = new Position(col, row);
                printTile(world, newP, t);
            }
        }
    }

    public static void printTile(TETile[][] world, Position p,TETile t) {
        if (p.x<world.length && p.y <world[0].length)
        world[p.x][p.y] = t;

    }

    /**
     * @param size the size of hex
     * @param i    the ith row of hex
     * @return the width(how many tiles) of the tiles that will be filled on ith row
     */
    public static int hexRowHeight(int size, int i) {
        if (i < size) {
            return size + 2 * i;
        } else {
            return size + 2 * (size - 1) - 2 * (i - size);
        }
    }

    /**
     * @param size the size of hex
     * @param i    the ith row of hex
     * @return the offset
     */
    public static int hexRowOffset(int size, int i) {
        if (i < size) {
            return size - 1 - i;
        } else {
            return i - size;
        }
    }

    private static TETile randomTile() {
        int tileNum = RANDOM.nextInt(6);
        switch (tileNum) {
            case 0: return Tileset.WALL;
            case 1: return Tileset.FLOWER;
            case 2: return Tileset.GRASS;
            case 3: return Tileset.MOUNTAIN;
            case 4: return Tileset.SAND;
            case 5: return Tileset.TREE;
            default: return Tileset.WATER;
        }
    }
    public static void main(String[] args) {
//        int size = 5;
//        for (int i = 0; i < size * 2; i++) {
//            System.out.println(hexRowOffset(size, i));
//        }
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);


        TETile[][] Hexworld = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
            for (int y = 0; y < HEIGHT; y += 1) {
                Hexworld[x][y] = Tileset.NOTHING;
            }
        }
        int size = 4;
        int hexWidth = 2*size-1;
        int hexHeight = size;
        Position p1 = new Position(0,25);
        updateHex(Hexworld, p1, size);
        Position p2 = new Position(hexWidth,25+hexHeight);
        updateHex(Hexworld, p2, size);
        Position p3 = new Position(hexWidth,25-hexHeight);
        updateHex(Hexworld, p3, size);
        Position p4 = new Position(2*hexWidth,25);
        updateHex(Hexworld, p4, size);
        Position p5 = new Position(2*hexWidth,25+hexHeight*2);
        updateHex(Hexworld, p5, size);
        Position p6 = new Position(2*hexWidth,25-hexHeight*2);
        updateHex(Hexworld, p6, size);
        Position p7 = new Position(3*hexWidth,25+hexHeight);
        updateHex(Hexworld, p7, size);
        Position p8 = new Position(3*hexWidth,25-hexHeight);
        updateHex(Hexworld, p8, size);
        Position p9 = new Position(4*hexWidth,25);
        updateHex(Hexworld, p9, size);


        ter.renderFrame(Hexworld);
    }

    private static void updateHex(TETile[][] world,Position p, int size) {
        TETile t = randomTile();
        addHexagon(world,p,size,t);
    }
}
