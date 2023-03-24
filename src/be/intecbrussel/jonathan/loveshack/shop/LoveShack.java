package be.intecbrussel.jonathan.loveshack.shop;

import be.intecbrussel.jonathan.loveshack.mixables.Ingredient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

public class LoveShack {
    //properties
    private ArrayList<SmoothieRecipe> orders;
    private Scanner scanner;
    private Ingredient[] allIngredients;
    private SmoothieRecipe[] allSmoothieOptions;


    //constructors
    public LoveShack() {
        this.orders = new ArrayList<>();
        this.allIngredients = Ingredient.values();
        this.allSmoothieOptions = SmoothieRecipe.values();
        this.scanner = new Scanner(System.in);
    }


    //custom methods
    public void order() {
        ArrayList<SmoothieRecipe> orders = new ArrayList<>();
        while (orders.size() < 4) {
            printSmoothieMenu();
            int userInput = scanner.nextInt();

            //checking if userInput has unacceptable value
            if (userInput == 9) {
                break;
            } else if (userInput < 0 || userInput >= SmoothieRecipe.values().length) {
                continue;
            }

            //branching on userInput
            //checking if user has already successfully ordered a custom smoothie
            if (userInput == SmoothieRecipe.CUSTOM_SMOOTHIE.ordinal()
                    && SmoothieRecipe.CUSTOM_SMOOTHIE.getRecipe().length <= 1) {
                orders.add(orderCustomSmoothie());
                orders.removeAll(Collections.singleton(null)); //removing possibly failed smoothie
            } else {
                orders.add(allSmoothieOptions[userInput]);
            }
        }
        printOrderTotal(orders);
    }

    private SmoothieRecipe orderCustomSmoothie() {
        ArrayList<Ingredient> customRecipe = createCustomRecipe();

        //checking if user created custom recipe correctly
        if (customRecipe.size() <= 1) {
            return null;
        }

        //saving custom recipe in CUSTOM_SMOOTHIE enum object
        SmoothieRecipe.CUSTOM_SMOOTHIE.setRecipe(customRecipe.toArray(new Ingredient[0]));
        return SmoothieRecipe.CUSTOM_SMOOTHIE;
    }

    private ArrayList<Ingredient> createCustomRecipe() {
        ArrayList<Ingredient> customRecipe = new ArrayList<>();
        int userInput;

        while (customRecipe.size() <= 4) {
            printIngredientsMenu();
            if ((userInput = scanner.nextInt()) == 9) {
                break;
            }
            customRecipe.add(allIngredients[userInput]);
        }

        return customRecipe;
    }

    private void printSmoothieMenu() {
        Stream.of(SmoothieRecipe.values())
                .forEach(item -> System.out.print(item.ordinal() + ". " + item.name().toLowerCase() + " "));
        System.out.print("9. SAVE\n");
    }

    private void printIngredientsMenu() {
        for (int i = 0; i < allIngredients.length; i++) {
            System.out.print(i + ". " + allIngredients[i].name().toLowerCase() + " ");
        }
        System.out.print("9. SAVE\n");
    }

    private void printOrderTotal(ArrayList<SmoothieRecipe> orders) {
        orders.toArray(new SmoothieRecipe[0]);

        //printing items in orders
        System.out.print("You ordered: ");
        Stream.of(orders).forEach(System.out::println);
        System.out.println("orderTotalPrice " + orders.stream().map(SmoothieRecipe::getTotalPrice).reduce(0.0, Double::sum));
    }
}
