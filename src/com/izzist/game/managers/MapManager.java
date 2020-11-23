package com.izzist.game.managers;

import com.izzist.game.map.MapLoader;

import java.util.ArrayList;

public class MapManager {
    public static ArrayList<MapLoader> mapLoaders;

    public MapManager() {
        mapLoaders = new ArrayList<>();
        mapLoaders.add(new MapLoader("data/map/Level1/Level1.txt"));
    }

    public MapLoader getMap(int level) {
        return mapLoaders.get(level);
    }
}
