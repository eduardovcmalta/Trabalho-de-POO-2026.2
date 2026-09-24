package Classes;

public class Estudante extends Usuario{

    public Estudante(String user, String cpf, String senha, int idade, String sexo, String email,
                   String nomecartao, int numerocartao, int codigovericartao){

        super(user, cpf, senha, idade, sexo, email, nomecartao, numerocartao, codigovericartao);
    }

    @Override
    public double calcularValorBilhete(Sala sala, Filme filme) {
        return super.calcularValorBilhete(sala, filme) / 2;
    }
}
