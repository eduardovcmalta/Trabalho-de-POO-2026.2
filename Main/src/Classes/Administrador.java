package Classes;

import Interfaces.GerenciaFilmes;
import java.io.*;

public class Administrador extends Base implements GerenciaFilmes {
    private double salario;
    private int id; 
    
    // Nomes dos arquivos de banco de dados em texto
    private final String ARQUIVO_USUARIOS = "usuarios.txt";
    private final String ARQUIVO_FILMES = "filmes.txt";

    public Administrador(String nome, int idade, String email, double salario, int id) {
        super(nome, idade, email);
        this.salario = salario;
        this.id = id;
    }

    public double getSalario() { return salario; }
    public void setSalario(double salario) { this.salario = salario; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    
    @Override
    public void adicionarUsuario(Usuario usuario) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_USUARIOS, true))) {
            // Salva os dados separados por ponto e vírgula (formato CSV)
            String linha = usuario.getUser() + ";" + usuario.getCpf() + ";" + usuario.getSenha() + ";" + 
                           usuario.getIdade() + ";" + usuario.getSexo() + ";" + usuario.getEmail() + ";" + 
                           usuario.getNomeCartao() + ";" + usuario.getNumeroCartao() + ";" + usuario.getCodigoVeriCartao();
            bw.write(linha);
            bw.newLine();
            System.out.println("Admin " + getNome() + " persistiu o usuário " + usuario.getUser() + " com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuário: " + e.getMessage());
        }
    }

    public void excluirUsuario(Usuario usuario) {
        File arquivoOriginal = new File(ARQUIVO_USUARIOS);
        File arquivoTemp = new File("usuarios_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemp))) {
             
            String linha;
            boolean encontrou = false;
            
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                // Se o CPF bater, ignora a linha (não escreve no arquivo novo)
                if (dados[1].equals(usuario.getCpf())) {
                    encontrou = true;
                    continue; 
                }
                bw.write(linha);
                bw.newLine();
            }
            
            if (encontrou) {
                System.out.println("Usuário " + usuario.getUser() + " excluído do banco de dados.");
            } else {
                System.out.println("Usuário não encontrado para exclusão.");
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao excluir usuário: " + e.getMessage());
        }

        // Substitui o arquivo antigo pelo novo
        arquivoOriginal.delete();
        arquivoTemp.renameTo(arquivoOriginal);
    }

    @Override
    public void alterarUsuario(Usuario usuario) {
        // A forma mais fácil de alterar em arquivos .txt é excluir o antigo e adicionar o novo atualizado
        System.out.println("Atualizando dados do usuário " + usuario.getUser() + "...");
        excluirUsuario(usuario);
        adicionarUsuario(usuario);
    }


    @Override
    public void incluirFilme(Filme filme) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_FILMES, true))) {
            String linha = filme.getNome() + ";" + filme.getDuracao() + ";" + filme.getSinopse() + ";" + filme.getValor();
            bw.write(linha);
            bw.newLine();
            System.out.println("Admin " + getNome() + " persistiu o filme " + filme.getNome() + " com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar filme: " + e.getMessage());
        }
    }

    @Override
    public void excluirFilme(Filme filme) {
        File arquivoOriginal = new File(ARQUIVO_FILMES);
        File arquivoTemp = new File("filmes_temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(arquivoOriginal));
             BufferedWriter bw = new BufferedWriter(new FileWriter(arquivoTemp))) {
             
            String linha;
            boolean encontrou = false;
            
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                // Se o nome do filme bater, ignora a linha
                if (dados[0].equals(filme.getNome())) {
                    encontrou = true;
                    continue;
                }
                bw.write(linha);
                bw.newLine();
            }
            
            if (encontrou) {
                System.out.println("Filme " + filme.getNome() + " excluído do banco de dados.");
            } else {
                System.out.println("Filme não encontrado para exclusão.");
            }
            
        } catch (IOException e) {
            System.out.println("Erro ao excluir filme: " + e.getMessage());
        }

        arquivoOriginal.delete();
        arquivoTemp.renameTo(arquivoOriginal);
    }

    @Override
    public void alterarFilme(Filme filme) {
        System.out.println("Atualizando dados do filme " + filme.getNome() + "...");
        excluirFilme(filme);
        incluirFilme(filme);
    }
}