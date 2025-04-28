class Gin extends DrinkStruct {
    public Gin() {
        super("Gin");
        DrinkStruct londonDry = new DrinkStruct("London Dry Style");
        DrinkStruct bigBrand  = new DrinkStruct("Big brand");
        bigBrand.addChild(new DrinkStruct("Tanqueray"));
        bigBrand.addChild(new DrinkStruct("Bombay Sapphire"));
        DrinkStruct cheapEnd  = new DrinkStruct("Cheapest end");
        cheapEnd.addChild(new DrinkStruct("Beefeater"));
        cheapEnd.addChild(new DrinkStruct("Gordon"));

        londonDry.addChild(bigBrand);
        londonDry.addChild(cheapEnd);
        this.addChild(londonDry);
    }
}
