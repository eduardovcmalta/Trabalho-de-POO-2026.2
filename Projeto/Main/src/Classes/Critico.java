package Classes;

public class Critico extends Usuario {

    private String origem;

    public Critico(String user, String cpf, String senha, int idade, String sexo, String email,
                   String nomecartao, String numerocartao, int codigovericartao, String origem) {
        super(user, cpf, senha, idade, sexo, email, nomecartao, numerocartao, codigovericartao);
        this.origem = origem;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    @Override
    public double calcularValorBilhete(Sala sala, Filme filme) {
        return 0;
    }

    public void atribuirNota(double nota, Filme filme) {
        if (nota < 0 || nota > 10) {
            System.out.println("Nota inválida: deve estar entre 0 e 10");
            return;
        }
        int qtd = filme.getQuantidade_criticos();
        filme.setNota((filme.getNota() * qtd + nota) / (qtd + 1));
        filme.setQuantidade_criticos(qtd + 1);
    }

    public void atribuirCritica(String critica, Filme filme) {
        Critica[] lista = filme.getCritica();
        for (int i = 0; i < lista.length; i++) {
            if (lista[i] == null) {
                lista[i] = new Critica(getUser(), origem, critica);
                return;
            }
        }
        System.out.println("Limite de críticas do filme atingido");
    }
}