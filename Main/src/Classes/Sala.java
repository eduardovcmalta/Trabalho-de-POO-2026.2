package Classes;
import Enums.TipoSala;

public class Sala {

    private Sessao sessoes[];
    private int numero;
    private TipoSala tipo;

    // Construtor
    public Sala(int numero, TipoSala tipo) {
        this.numero = numero;
        this.tipo = tipo;
        this.sessoes = new Sessao[7];   // 08-10, 10-12, ..., 20-22
    }

    // Get e set
    public TipoSala getTipo() {
        return tipo;
    }

    public void setTipo(TipoSala tipo) {
        this.tipo = tipo;
    }

    public Sessao[] getSessoes() {
        return sessoes;
    }

    public void setSessoes(Sessao sessoes[]) {
        this.sessoes = sessoes;
    }
    public int getNumero(){
        return numero;
    }
    public void setNumero(int numero){
        this.numero = numero;
    }
    // Funções
    public boolean agendarSessao(int horario, Sessao sessao) {
        if (horario >= 0 && horario < sessoes.length) {
            this.sessoes[horario] = sessao;
            return true;
        }
        return false;
    }

    public String obterHorario(int indice) {
        int inicio = 8 + (indice * 2);
        int fim = inicio + 2;
        return String.format("%02d:00 - %02d:00", inicio, fim);
    }

    public String getHorarioSessao(Sessao sessao) {
        for (int i = 0; i < sessoes.length; i++) {
            if (sessoes[i] == sessao) {
                return obterHorario(i);
            }
        }
        return "Horário não definido";
    }

    public void exibirSessoesDisponiveis() {
        System.out.println("=== Sessões na Sala " + numero + " (" + tipo.getTipo() + ") ===");
        for (int i = 0; i < sessoes.length; i++) {
            if (sessoes[i] != null) {
                System.out.println(obterHorario(i) + " -> " + sessoes[i].getFilme().getNome());            
            }
        }
    }
}