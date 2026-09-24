package Classes;

public class Critica {
    private String nome;
    private String origem;
    private String mensagem;

    public Critica(String nome, String origem, String mensagem) {
        this.nome = nome;
        this.origem = origem;
        this.mensagem = mensagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public void atribuirNota(Filme filme, float nota){

        if (nota < 0  || nota > 10){
            System.out.println("Nota inválida, fora do intervalo entre 0 e 10");
            return;
        }

        filme.setQuantidade_criticos(filme.getQuantidade_criticos() + 1);

        if (filme.getQuantidade_criticos() < 2){
            filme.setNota(nota);
            return;
        }

        filme.setNota(  ( ( filme.getNota() * (filme.getQuantidade_criticos() - 1) ) + nota  ) / filme.getQuantidade_criticos()  );
    }

    public void atribuirCritica(Filme filme, String critica) {

        for (int i = 0; i < filme.getCritica().length; i++) {

            if (filme.getCritica()[i] == null) {
                Critica novaCritica = new Critica(this.nome, this.origem, critica);

                filme.getCritica()[i] = novaCritica;
                break;
            }
        }
    }
}