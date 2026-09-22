package Enums;

public enum Cupons {

        MEIAENTRADA(50.0),
        QUARTAPROMOCIONAL(30.0),
        PRIORITARIO(20.0);

        private final double desconto;

        Cupons(double desconto) {
            this.desconto = desconto;
        }

        public double getDesconto() {
            return desconto;
        }

        public double aplicarDesconto(double valorOriginal) {
            return valorOriginal - (valorOriginal * (desconto / 100));
        }


}
