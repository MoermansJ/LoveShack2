package be.intecbrussel.jonathan.loveshack.mixables;

import be.intecbrussel.jonathan.loveshack.shop.LoveShack;
import be.intecbrussel.jonathan.loveshack.shop.SmoothieRecipe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MainApp {
    public static void main(String[] args) {
        LoveShack loveShack = new LoveShack();
        loveShack.order();

        //uitleg ivm mutable collections
//        Set<Integer> demo = new HashSet<>();
//        demo.add(3);
//        demo.add(4);
//        demo.add(5);
//
//        System.out.println(demo);
//
//        demo.add(6);
//
//        System.out.println(demo);
//
//        demo = new HashSet<>(Collections.singleton(1));
//
//        System.out.println(demo.getClass());
//
//        demo.add(87);
//
//        System.out.println(demo);

    }
}
