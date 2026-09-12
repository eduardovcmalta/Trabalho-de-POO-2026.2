package Classes;

public class Sala {
    private Sessao[] sessao; 

    public Sala(int quantidadeHorarios) {
        this.sessao = new Sessao[quantidadeHorarios];
    }

    public Sessao getSessao(int indice) {
        return sessao[indice];
    }

    public void setSessao(int indice, Sessao sessao) {
        this.sessao[indice] = sessao;
    }
} 
    

