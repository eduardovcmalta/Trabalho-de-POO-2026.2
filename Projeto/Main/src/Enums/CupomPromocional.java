package Enums;

public enum CupomPromocional {

        BEMVINDO10(10.0),
        QUARTAPROMOCIONAL(30.0),
        PRIORITARIO(20.0);

        private final double desconto;

        CupomPromocional(double desconto) {
            this.desconto = desconto;
        }

        public double getDesconto() {
            return desconto;
        }

        public double aplicarDesconto(double valorOriginal) {
            return valorOriginal - (valorOriginal * (desconto / 100));
        }


}
