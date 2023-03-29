package be.intecbrussel.jonathan.loveshack.shop;

import be.intecbrussel.jonathan.loveshack.mixables.Ingredient;

import java.util.*;
import java.util.stream.Collectors;
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
            System.out.println(printEnumValues(SmoothieRecipe.values()));
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

                Optional<SmoothieRecipe> customSmoothie = orderCustomSmoothie();

                if (customSmoothie.isEmpty()) {
                    System.out.println("Adding custom smoothie recipe failed! Try again?");
                    continue;
                }

                orders.add(customSmoothie.get());
            } else {
                orders.add(allSmoothieOptions[userInput]);
            }
        }
        System.out.println(printOrderTotal(orders));
    }

    private Optional<SmoothieRecipe> orderCustomSmoothie() {
        ArrayList<Ingredient> customRecipe = createCustomRecipe();

        //checking if user created custom recipe correctly
        if (customRecipe.size() <= 1) {
            return Optional.empty();
        }

        //saving custom recipe in CUSTOM_SMOOTHIE enum object
        SmoothieRecipe.CUSTOM_SMOOTHIE.setRecipe(customRecipe.toArray(Ingredient[]::new));
        return Optional.of(SmoothieRecipe.CUSTOM_SMOOTHIE);
    }

    private ArrayList<Ingredient> createCustomRecipe() {
        ArrayList<Ingredient> customRecipe = new ArrayList<>();
        int userInput;

        while (customRecipe.size() <= 4) {
            System.out.println(printEnumValues(Ingredient.values()));
            if ((userInput = scanner.nextInt()) == 9) {
                break;
            }
            customRecipe.add(allIngredients[userInput]);
        }

        return customRecipe;
    }

    private String printEnumValues(Object[] values) {
        String result = "";

        for (int i = 0; i < values.length; i++) {
            result += i + ". " + values[i].toString().toLowerCase() + " ";
        }

        return result + "9. SAVE & EXIT";
    }

    private String printOrderTotal(ArrayList<SmoothieRecipe> orders) {
        return "You ordered: "
                + orders.stream()
                .map(item -> item.name().toLowerCase())
                .collect(Collectors.joining(", "))
                + "\norderTotalPrice: "
                + orders.stream().mapToDouble(SmoothieRecipe::getTotalPrice).sum();
    }
}
