package be.intecbrussel.jonathan.loveshack.mixables;

public enum Ingredient {
    //instances
    ORANGE(1, "pressed"),
    LEMON(0.75, "pressed"),
    BANANA(1.25, "mashed"),
    STRAWBERRY(2, "mashed"),
    APPLE(0.5, "mashed"),
    CELERY(1, "cut"),
    SPINACH(1.25, "mashed");


    //properties
    private double pricePerPiece;
    private String mixMessage;


    //constructors
    Ingredient(double pricePerPiece, String mixMessage){
        this.pricePerPiece = pricePerPiece;
        this.mixMessage = mixMessage;
    }


    //getters & setters
    public double getPricePerPiece() {
        return pricePerPiece;
    }

    public void setPricePerPrice(double pricePerPiece) {
        this.pricePerPiece = pricePerPiece;
    }

    public String getMixMessage() {
        return mixMessage;
    }

    public void setMixMessage(String mixMessage) {
        this.mixMessage = mixMessage;
    }


    //custom methods
    public String mix(){
        return this.name().toLowerCase() + " is " + getMixMessage() + " and added.";
    }

}
