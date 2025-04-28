class Campari extends DrinkStruct {
    public Campari() {
        super("Campari Family");
        DrinkStruct sweeter = new DrinkStruct("Sweeter and Lighter");
        sweeter.addChild(new DrinkStruct("Campari"));
        sweeter.addChild(new DrinkStruct("Aperol"));

        DrinkStruct darker = new DrinkStruct("Darker and more syrupy");
        darker.addChild(new DrinkStruct("Cynar"));
        darker.addChild(new DrinkStruct("Averna"));

        this.addChild(sweeter);
        this.addChild(darker);
    }
}