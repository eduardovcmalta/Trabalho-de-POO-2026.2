package Classes;

public class Usuario{
    private String user;
    private String cpf;
    private String senha;
    private int idade;
    private String sexo;
    private String email;
    private String nomecartao;
    private String numerocartao;
    private int codigovericartao;
    private Compra compra;

    public String getUser(){
        return user;
    }
    public void setUser(String user){
        this.user = user;
    }

    public String getCpf(){
        return cpf;
    }
    public void setCpf(String cpf){
        this.cpf = cpf;
    }

    public String getSenha(){
        return senha;
    }
    public void setSenha(String senha){
        this.senha = senha;
    }

    public int getIdade(){
        return idade;
    }
    public void setIdade(int idade){
        this.idade = idade;
    }

    public String getSexo(){
        return sexo;
    }
    public void setSexo(String sexo){
        this.sexo = sexo;
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getNomeCartao(){
        return nomecartao;
    }
    public void setNomeCartao(String nomecartao){
        this.nomecartao = nomecartao;
    }

    public String getNumeroCartao(){
        return numerocartao;
    }
    public void setNumeroCartao(String numerocartao){
        this.numerocartao = numerocartao;
    }

    public int getCodigoVeriCartao(){
        return codigovericartao;
    }
    public void setCodigoVeriCartao(int codigovericartao){
        this.codigovericartao = codigovericartao;
    }

    public Compra getCompra(){
        return compra;
    }
    public void setCompra(Compra compra){
        this.compra = compra;
    }
    public Usuario(String user, String cpf, String senha, int idade, String sexo, String email, 
        String nomecartao, String numerocartao, int codigovericartao){
            this.user = user;
            this.cpf = cpf;
            this.senha = senha;
            this.idade = idade;
            this.sexo = sexo;
            this.email = email;
            this.nomecartao = nomecartao;
            this.numerocartao = numerocartao;
            this.codigovericartao = codigovericartao;
        }

    public double calcularValorBilhete(Sala sala, Filme filme) {
        double valorFinal = sala.getTipo().calcularValor(filme.getValor());

        return valorFinal;
    }
    
    public void realizarCompra(Compra novaCompra) {
        this.compra = novaCompra;
        System.out.println("Compra realizada com sucesso para o usuário: " + this.user);
    }

    public void cancelarCompra() {
        if (this.compra != null) {
            // Percorre os bilhetes da compra e libera as cadeiras na respectiva sessão
            for (Bilhete b : this.compra.getBilhetes()) {
                b.getSessao().cancelarReserva(b.getCadeira());
            }
            this.compra = null;
            System.out.println("Compra cancelada. As poltronas foram liberadas.");
        } else {
            System.out.println("Nenhuma compra ativa para cancelar.");
        }
    }

    public void alterarCompra(Compra compraAtualizada) {
        System.out.println("Alterando compra atual...");
        this.cancelarCompra(); // Cancela a anterior e libera os assentos
        this.realizarCompra(compraAtualizada); // Atribui a nova compra
    }
}