class FrenchVermouth extends DrinkStruct {
    public FrenchVermouth() {
        super("French vermouth (dry)");
        this.addChild(new DrinkStruct("Noilly Prat (Aromatic of Marseille style)"));
        this.addChild(new DrinkStruct("Dolin Dry (Bone-dry Chambery style)"));
        this.addChild(new DrinkStruct("Martini Extra Dry"));
    }
}