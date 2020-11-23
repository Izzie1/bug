package com.izzist.game.managers;

import com.izzist.game.entity.Bomb.Bomb;
import com.izzist.game.map.tiles.Tile;
import com.izzist.game.map.tiles.TileBrick;

import java.util.ArrayList;

public class TileManager {
    public static Tile[][] tileManager;
    public static ArrayList<TileBrick> tileBrickManager = new ArrayList<>();

    public static Tile getTile(int x, int y) {
        if (tileManager[x][y] != null)
            return tileManager[x][y];
        else return null;
    }

    public static TileBrick getBrick(int x, int y) {
        TileBrick temp = null;
        for (TileBrick b : tileBrickManager) {
            if ((int)b.getPosition().x/32 == x && (int)b.getPosition().y/32 == y) {
                temp = b;
            }
        }
        return temp;
    }
}
