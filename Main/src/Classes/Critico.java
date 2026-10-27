package Classes;

public class Critico extends Usuario{

    String origem;

    public Critico(String user, String cpf, String senha, int idade, String sexo, String email,
                   String nomecartao, String numerocartao, int codigovericartao, String origem) {

        super(user, cpf, senha, idade, sexo, email, nomecartao, numerocartao, codigovericartao);
        this.origem = origem;
    }

    @Override
    public double calcularValorBilhete(Sala sala, Filme filme) {
        return 0 / super.calcularValorBilhete(sala, filme);
    }
}
