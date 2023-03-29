package be.intecbrussel.jonathan.loveshack.shop;

import be.intecbrussel.jonathan.loveshack.mixables.Ingredient;

import java.util.stream.DoubleStream;
import java.util.stream.Stream;

public enum SmoothieRecipe {
    //instances
    CITRUS(new Ingredient[]{Ingredient.ORANGE, Ingredient.ORANGE, Ingredient.LEMON}),
    STRAWBERRYDREAM(new Ingredient[]{Ingredient.STRAWBERRY, Ingredient.ORANGE, Ingredient.ORANGE, Ingredient.BANANA}),
    BANANASLIDE(new Ingredient[]{Ingredient.BANANA, Ingredient.BANANA, Ingredient.ORANGE}),
    VEGGIESLURRY(new Ingredient[]{Ingredient.BANANA, Ingredient.CELERY, Ingredient.SPINACH, Ingredient.APPLE}),
    CUSTOM_SMOOTHIE(new Ingredient[]{});


    //properties
    private Ingredient[] recipe;


    //constructors
    SmoothieRecipe(Ingredient[] recipe) {
        this.recipe = recipe;
    }


    //getters & setters
    public Ingredient[] getRecipe() {
        return recipe;
    }

    public void setRecipe(Ingredient[] recipe) {
        if (this.equals(CUSTOM_SMOOTHIE)) {
            this.recipe = recipe;
        }
    }


    //custom methods
    public double getTotalPrice() {
//        return Stream.of(recipe).map(item -> item.getPricePerPiece()).reduce(0.0, Double::sum);
        return Stream.of(recipe).mapToDouble(item -> item.getPricePerPiece()).sum();
    }


}
