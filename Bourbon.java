class Bourbon extends DrinkStruct {
    public Bourbon() {
        super("Bourbon");
        DrinkStruct entry = new DrinkStruct("Entry level");
        entry.addChild(new DrinkStruct("Jim Beam"));
        entry.addChild(new DrinkStruct("Maker's Mark"));

        DrinkStruct strongRye = new DrinkStruct("Strong rye");
        strongRye.addChild(new DrinkStruct("Wild Turkey 101"));
        strongRye.addChild(new DrinkStruct("Buffalo Trace"));
        strongRye.addChild(new DrinkStruct("George T. Stagg"));

        DrinkStruct pureRye = new DrinkStruct("Pure rye");
        pureRye.addChild(new DrinkStruct("Rittenhouse 100"));

        this.addChild(entry);
        this.addChild(strongRye);
        this.addChild(pureRye);
    }
}