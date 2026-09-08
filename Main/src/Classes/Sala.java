package Classes;

public class Sala {

    private Sessao sessoes[];
    private int numero;

    //Construtor

    public Sala(int numero) {
        this.numero = numero;
        this.sessoes = new Sessao[5];
    }

    //Get e set

    public Sessao[] getSessoes() {
        return sessoes;
    }

    public void setSessoes(Sessao sessoes[]) {
        this.sessoes = sessoes;
    }

    //Funções

    public boolean agendarSessao(int horario, Sessao sessao) {
        if (horario >= 0 && horario < sessoes.length) {
            this.sessoes[horario] = sessao;
            return true;
        }
        return false;
    }

    public void exibirSessoesDisponiveis() {
        System.out.println("=== Sessões na Sala " + numero + " ===");
        for (int i = 0; i < sessoes.length; i++) {
            if (sessoes[i] != null) {
                System.out.println("Horário [" + (i + 1) + "] -> " + sessoes[i].getFilme());
            }
        }
    }
}
