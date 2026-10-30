package Persistencia;

import Classes.Critica;
import Classes.Critico;
import Classes.Estudante;
import Classes.Filme;
import Classes.Usuario;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Guarda usuários e filmes em memória (listas dinâmicas) e sabe salvar/carregar
 * em arquivos de texto. Quem decide QUANDO persistir é o Administrador.
 *
 * Arquivos (dentro da pasta informada):
 *   usuarios.txt -> TIPO;user;cpf;senha;idade;sexo;email;nomeCartao;numCartao;cvv[;origem]
 *   filmes.txt   -> nome;duracao;sinopse;valor;emCartaz;nota;qtdCriticos
 *   criticas.txt -> nomeFilme;nomeCritico;origem;mensagem
 */
public class Repositorio {

    private final Path pasta;
    private final List<Usuario> usuarios = new ArrayList<>();
    private final List<Filme> filmes = new ArrayList<>();

    public Repositorio() {
        this("dados");
    }

    public Repositorio(String pasta) {
        this.pasta = Paths.get(pasta);
    }

    // ---------------- usuários (chave: CPF) ----------------

    public boolean adicionarUsuario(Usuario usuario) {
        if (usuario == null || buscarUsuario(usuario.getCpf()) != null) {
            return false;
        }
        usuarios.add(usuario);
        return true;
    }

    public boolean removerUsuario(String cpf) {
        Usuario u = buscarUsuario(cpf);
        return u != null && usuarios.remove(u);
    }

    public boolean alterarUsuario(Usuario novosDados) {
        for (int i = 0; i < usuarios.size(); i++) {
            if (usuarios.get(i).getCpf().equals(novosDados.getCpf())) {
                usuarios.set(i, novosDados);
                return true;
            }
        }
        return false;
    }

    public Usuario buscarUsuario(String cpf) {
        for (Usuario u : usuarios) {
            if (u.getCpf().equals(cpf)) return u;
        }
        return null;
    }

    public List<Usuario> getUsuarios() {
        return Collections.unmodifiableList(usuarios);
    }

    // ---------------- filmes (chave: nome) ----------------

    public boolean adicionarFilme(Filme filme) {
        if (filme == null || buscarFilme(filme.getNome()) != null) {
            return false;
        }
        filmes.add(filme);
        return true;
    }

    public boolean removerFilme(String nome) {
        Filme f = buscarFilme(nome);
        return f != null && filmes.remove(f);
    }

    public boolean alterarFilme(Filme novosDados) {
        for (int i = 0; i < filmes.size(); i++) {
            if (filmes.get(i).getNome().equalsIgnoreCase(novosDados.getNome())) {
                filmes.set(i, novosDados);
                return true;
            }
        }
        return false;
    }

    public Filme buscarFilme(String nome) {
        for (Filme f : filmes) {
            if (f.getNome().equalsIgnoreCase(nome)) return f;
        }
        return null;
    }

    public List<Filme> getFilmes() {
        return Collections.unmodifiableList(filmes);
    }

    // ---------------- arquivos de texto ----------------

    public boolean salvar() {
        try {
            Files.createDirectories(pasta);

            List<String> linhasUsuarios = new ArrayList<>();
            for (Usuario u : usuarios) {
                linhasUsuarios.add(serializar(u));
            }
            Files.write(pasta.resolve("usuarios.txt"), linhasUsuarios, StandardCharsets.UTF_8);

            List<String> linhasFilmes = new ArrayList<>();
            List<String> linhasCriticas = new ArrayList<>();
            for (Filme f : filmes) {
                linhasFilmes.add(String.join(";",
                        limpar(f.getNome()), String.valueOf(f.getDuracao()), limpar(f.getSinopse()),
                        String.valueOf(f.getValor()), String.valueOf(f.isEmCartaz()),
                        String.valueOf(f.getNota()), String.valueOf(f.getQuantidade_criticos())));
                for (Critica c : f.getCritica()) {
                    if (c != null) {
                        linhasCriticas.add(String.join(";", limpar(f.getNome()), limpar(c.getNome()),
                                limpar(c.getOrigem()), limpar(c.getMensagem())));
                    }
                }
            }
            Files.write(pasta.resolve("filmes.txt"), linhasFilmes, StandardCharsets.UTF_8);
            Files.write(pasta.resolve("criticas.txt"), linhasCriticas, StandardCharsets.UTF_8);
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
            return false;
        }
    }

    // Substitui o conteúdo da memória pelo que está nos arquivos
    public boolean carregar() {
        usuarios.clear();
        filmes.clear();
        try {
            Path pu = pasta.resolve("usuarios.txt");
            if (Files.exists(pu)) {
                for (String linha : Files.readAllLines(pu, StandardCharsets.UTF_8)) {
                    if (linha.isBlank()) continue;
                    Usuario u = lerUsuario(linha);
                    if (u != null) usuarios.add(u);
                    else System.out.println("Linha inválida ignorada em usuarios.txt: " + linha);
                }
            }

            Path pf = pasta.resolve("filmes.txt");
            if (Files.exists(pf)) {
                for (String linha : Files.readAllLines(pf, StandardCharsets.UTF_8)) {
                    if (linha.isBlank()) continue;
                    Filme f = lerFilme(linha);
                    if (f != null) filmes.add(f);
                    else System.out.println("Linha inválida ignorada em filmes.txt: " + linha);
                }
            }

            Path pc = pasta.resolve("criticas.txt");
            if (Files.exists(pc)) {
                for (String linha : Files.readAllLines(pc, StandardCharsets.UTF_8)) {
                    if (linha.isBlank()) continue;
                    String[] t = linha.split(";", -1);
                    Filme f = t.length >= 4 ? buscarFilme(t[0]) : null;
                    if (f == null) continue;
                    Critica[] lista = f.getCritica();
                    for (int i = 0; i < lista.length; i++) {
                        if (lista[i] == null) {
                            lista[i] = new Critica(t[1], t[2], t[3]);
                            break;
                        }
                    }
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return false;
        }
    }

    // ---------------- conversão linha <-> objeto ----------------

    // ';' e quebras de linha dentro de um texto quebrariam o formato do arquivo
    private String limpar(String texto) {
        if (texto == null) return "";
        return texto.replace(";", ",").replace("\r", " ").replace("\n", " ");
    }

    private String serializar(Usuario u) {
        String tipo = "USUARIO";
        String extra = "";
        if (u instanceof Critico) {
            tipo = "CRITICO";
            extra = ";" + limpar(((Critico) u).getOrigem());
        } else if (u instanceof Estudante) {
            tipo = "ESTUDANTE";
        }
        return tipo + ";" + limpar(u.getUser()) + ";" + limpar(u.getCpf()) + ";" + limpar(u.getSenha()) + ";"
                + u.getIdade() + ";" + limpar(u.getSexo()) + ";" + limpar(u.getEmail()) + ";"
                + limpar(u.getNomeCartao()) + ";" + limpar(u.getNumeroCartao()) + ";"
                + u.getCodigoVeriCartao() + extra;
    }

    private Usuario lerUsuario(String linha) {
        String[] t = linha.split(";", -1);
        if (t.length < 10) return null;
        try {
            int idade = Integer.parseInt(t[4]);
            int cvv = Integer.parseInt(t[9]);
            switch (t[0]) {
                case "ESTUDANTE":
                    return new Estudante(t[1], t[2], t[3], idade, t[5], t[6], t[7], t[8], cvv);
                case "CRITICO":
                    if (t.length < 11) return null;
                    return new Critico(t[1], t[2], t[3], idade, t[5], t[6], t[7], t[8], cvv, t[10]);
                default:
                    return new Usuario(t[1], t[2], t[3], idade, t[5], t[6], t[7], t[8], cvv);
            }
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Filme lerFilme(String linha) {
        String[] t = linha.split(";", -1);
        if (t.length < 7) return null;
        try {
            Filme f = new Filme(t[0], Integer.parseInt(t[1]), t[2], Float.parseFloat(t[3]));
            f.setEmCartaz(Boolean.parseBoolean(t[4]));
            f.setNota(Double.parseDouble(t[5]));
            f.setQuantidade_criticos(Integer.parseInt(t[6]));
            return f;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
