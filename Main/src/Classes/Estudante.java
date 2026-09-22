package Classes;

public class Estudante extends Usuario{

    public Estudante(String user, String cpf, String senha, int idade, String sexo, String email,
                   String nomecartao, int numerocartao, int codigovericartao, Compra compra){

        super(user, cpf, senha, idade, sexo, email, nomecartao, numerocartao, codigovericartao, compra);
    }

    @Override
    public double calcularValorBilhete(Sala sala, Filme filme) {
        double valorCheio = super.calcularValorBilhete(sala, filme);
        return valorCheio / 2;
    }
}
