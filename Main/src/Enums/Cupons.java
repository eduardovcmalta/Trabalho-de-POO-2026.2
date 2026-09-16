package Enums;

public enum Cupons {
    BEMVINDO(10.0),
    CLIENTEVIP(20.0),
    BLACKFRIDAY(35.0);

    private double desconto;

    Cupons(double desconto) {
        this.desconto = desconto;
    }


}