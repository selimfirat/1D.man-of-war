package com.manofwar.logic;

import com.manofwar.logic.character.Character;
import com.manofwar.logic.block.Block;
import com.manofwar.logic.door.Door;
import com.manofwar.logic.item.Item;
import com.manofwar.logic.item.ItemType;
import com.manofwar.logic.entities.Inventory;
import com.manofwar.logic.entities.Velocity;
import com.manofwar.logic.mob.Mob;
import com.manofwar.logic.mob.MobType;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LevelFactory {

    private List<String> getLines(String path) {

        BufferedReader txtReader = null;
        try {
            txtReader = new BufferedReader(new FileReader(getClass().getResource(path).getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ArrayList<String> list = new ArrayList<String>();
        try {
            String str;
            while((str = txtReader.readLine()) != null){
                list.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Mob[] createMobs(int levelNum) {

        List<String> blueList = getLines("../../../level_" + levelNum + "/blue_mobs.txt");
        List<String> redList = getLines("../../../level_" + levelNum + "/red_mobs.txt");


        Mob[] mobsArray = new Mob[redList.size() + blueList.size()];
        for(int i = 0; i<blueList.size(); i++) {
            String[] splited = blueList.get(i).split("\\s+");
            int a = Integer.parseInt(splited[0]);
            int b = Integer.parseInt(splited[1]);
            mobsArray[i] = new Mob(new Rectangle(a, b, Config.TILE_WIDTH, Config.TILE_HEIGHT), MobType.BLUE,10, 0, 100, 100, new Inventory(), new Velocity());
        }


        for(int i = 0; i<redList.size(); i++) {
            String[] splited = redList.get(i).split("\\s+");
            int a = Integer.parseInt(splited[0]);
            int b = Integer.parseInt(splited[1]);
            mobsArray[blueList.size() + i] = new Mob(new Rectangle(a, b, Config.TILE_WIDTH, Config.TILE_HEIGHT), MobType.RED,0, 10, 100, 100, new Inventory(), new Velocity());
        }
        return mobsArray;
    }

    public Block[] createBlocks(int levelNum) {

        List<String> list = getLines("../../../level_" + levelNum + "/blocks.txt");

        Block[] blocksArray = new Block[list.size()];
        for(int i = 0; i<list.size(); i++) {
            String[] splited = list.get(i).split("\\s+");
            int a = Integer.parseInt(splited[0]);
            int b = Integer.parseInt(splited[1]);
            blocksArray[i] = new Block(new Rectangle(a, b, Config.TILE_WIDTH, Config.TILE_HEIGHT));
        }

        return blocksArray;
    }

    public Item[] createNonTakenItems(int levelNum) {

        List<String> blueList = getLines("../../../level_" + levelNum + "/blue_potions.txt");
        List<String> redList = getLines("../../../level_" + levelNum + "/red_potions.txt");

        Item[] itemsArray = new Item[blueList.size() + redList.size()];

        for(int i = 0; i< blueList.size(); i++) {
            String[] splited = blueList.get(i).split("\\s+");
            int a = Integer.parseInt(splited[0]);
            int b = Integer.parseInt(splited[1]);
            itemsArray[i] = new Item(new Rectangle(a, b, 50, 50), true, ItemType.BLUE_POTION, 10, 10, 0);
        }

        for(int i = 0; i < redList.size(); i++) {
            String[] splited = redList.get(i).split("\\s+");
            int a = Integer.parseInt(splited[0]);
            int b = Integer.parseInt(splited[1]);
            itemsArray[blueList.size() + i] = new Item(new Rectangle(a, b, 50, 50), true, ItemType.RED_POTION, 10, 0, 10);
        }

        return itemsArray;
    }

    public Door[] createDoors(int levelNum) {

        List<String> list = getLines("../../../level_" + levelNum + "/doors.txt");

        Door[] doorsArray = new Door[list.size()];
        for(int i = 0; i<list.size(); i++) {
            String[] splited = list.get(i).split("\\s+");
            int a = Integer.parseInt(splited[0]);
            int b = Integer.parseInt(splited[1]);
            int c = Integer.parseInt(splited[2]);
            doorsArray[i] = new Door(new Rectangle(a, b, Config.TILE_WIDTH, Config.TILE_HEIGHT), c);
        }

        return doorsArray;
    }

    public Character createCharacter(int levelNum) {
        List<String> list = getLines("../../../level_" + levelNum + "/spawn_point.txt");

        String[] splited = list.get(0).split("\\s+");
        int a = Integer.parseInt(splited[0]);
        int b = Integer.parseInt(splited[1]);

        return new Character(new Rectangle(a, b, 40, 40),10, 10, 100, 100, new Inventory(), new Velocity(0, 0));
    }
}