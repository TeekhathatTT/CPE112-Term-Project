class ItalianVermouth extends DrinkStruct {
    public ItalianVermouth() {
        super("Italian vermouth (sweet)");
        DrinkStruct martiniRosso = new DrinkStruct("Martini Rosso");
        DrinkStruct carpano       = new DrinkStruct("Carpano's");
        carpano.addChild(new DrinkStruct("Punt e Mes"));
        carpano.addChild(new DrinkStruct("Antica Formula"));

        martiniRosso.addChild(carpano);
        this.addChild(martiniRosso);
    }
}