package com.izzist.game.map;

import com.izzist.game.entity.Item.Item;
import com.izzist.game.entity.Item.ItemBomb;
import com.izzist.game.entity.Item.ItemFlame;
import com.izzist.game.entity.Item.ItemSpeed;
import com.izzist.game.entity.Player;
import com.izzist.game.managers.ItemManager;
import com.izzist.game.managers.TileManager;
import com.izzist.game.map.tiles.Tile;
import com.izzist.game.map.tiles.TileBrick;
import com.izzist.game.map.tiles.TileGrass;
import com.izzist.game.map.tiles.TileWall;
import com.izzist.game.states.PlayState;
import com.izzist.game.ultility.Vector2D;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.StringTokenizer;

public class MapLoader {
    private int level;
    private int width;
    private int height;
    private String[] lineTiles;
    private final int TILE_SIZE = 32;


    public MapLoader(String path) {
        readMap(path);
    }

    public void readMap(String source) {

        try {
            File path = new File(source);
            Scanner myReader = new Scanner(path);
            String data = myReader.nextLine();
            StringTokenizer tokens = new StringTokenizer(data);

            level = Integer.parseInt(tokens.nextToken());
            height = Integer.parseInt(tokens.nextToken());
            width = Integer.parseInt(tokens.nextToken());
            lineTiles = new String[height];
            TileManager.tileManager = new Tile[width][height];
            for (int i = 0; i < height; ++i) {
                lineTiles[i] = myReader.nextLine().substring(0, width);

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                switch (lineTiles[y].charAt(x)) {
                    case '#': {
                        TileWall tileWall = new TileWall(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileManager[x][y] = tileWall;
                        break;
                    }
                    case '*': {
                        TileBrick tileBrick = new TileBrick(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileBrickManager.add(tileBrick);
                        TileGrass tileGrass = new TileGrass(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileManager[x][y] = tileGrass;

                        break;
                    }
                    case 'p': {
                        PlayState.player = new Player(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileGrass tileGrass = new TileGrass(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileManager[x][y] = tileGrass;
                        break;
                    }
                    case 'b': {
                        TileBrick tileBrick = new TileBrick(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileBrickManager.add(tileBrick);
                        ItemBomb itemBomb = new ItemBomb(new Vector2D(x * TILE_SIZE + 4, y * TILE_SIZE + 4));
                        ItemManager.items.add(itemBomb);
                        TileGrass tileGrass = new TileGrass(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileManager[x][y] = tileGrass;
                        break;
                    }
                    case 'f': {
                        TileBrick tileBrick = new TileBrick(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileBrickManager.add(tileBrick);
                        ItemFlame itemFlame = new ItemFlame(new Vector2D(x * TILE_SIZE + 4, y * TILE_SIZE + 4));
                        ItemManager.items.add(itemFlame);
                        TileGrass tileGrass = new TileGrass(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileManager[x][y] = tileGrass;
                        break;
                    }
                    case 's': {
                        TileBrick tileBrick = new TileBrick(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileBrickManager.add(tileBrick);
                        ItemSpeed itemSpeed = new ItemSpeed(new Vector2D(x * TILE_SIZE + 4, y * TILE_SIZE + 4));
                        ItemManager.items.add(itemSpeed);
                        TileGrass tileGrass = new TileGrass(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileManager[x][y] = tileGrass;
                        break;
                    }

                    default: {
                        TileGrass tileGrass = new TileGrass(new Vector2D(x * TILE_SIZE, y * TILE_SIZE), 32);
                        TileManager.tileManager[x][y] = tileGrass;
                        break;
                    }
                }
            }
        }
    }


    public void render(Graphics2D g2D) {

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (TileManager.tileManager[x][y] != null)
                    TileManager.tileManager[x][y].render(g2D);
            }
        }

        if (ItemManager.items != null) {
            for (Item item : ItemManager.items) {
                item.render(g2D);
            }
        }

        if (TileManager.tileBrickManager != null) {
            for (TileBrick brick : TileManager.tileBrickManager) {
                brick.render(g2D);
            }
        }

    }

    public void update() {
        if (TileManager.tileBrickManager != null) {
            for (TileBrick brick : TileManager.tileBrickManager) {
                brick.update();
            }
        }

        if (ItemManager.items != null) {
            for (Item item : ItemManager.items) {
                item.update();
            }
        }

        for (int i = TileManager.tileBrickManager.size() - 1; i >= 0; i--) {
            if (TileManager.tileBrickManager.get(i).isBreak()
                    && TileManager.tileBrickManager.get(i).getAnimation1().playOnce()) {
                TileManager.tileBrickManager.remove(i);
            }
        }
    }

}
