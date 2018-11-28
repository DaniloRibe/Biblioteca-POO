package controller;

import java.util.Collection;
import javax.swing.JOptionPane;
import model.Cliente;
import model.Gerente;
import model.Livro;
import model.Operador;
import model.Usuario;
import repositories.RepositorioLivro;
import repositories.RepositorioUsuario;

public class Controlador {

    RepositorioLivro repositorioLivro = new RepositorioLivro();
    RepositorioUsuario repositorioUsuario = new RepositorioUsuario();
    Cliente c = new Cliente();

    public void adicionarLivro(String titulo, String autor, String editora, String edicao) {
        Livro l = new Livro(titulo, autor, editora, edicao);
        repositorioLivro.getLivros().put(titulo, l);
        JOptionPane.showMessageDialog(null, "LIVRO ADICIONADO");        
    }

    public void adicionarCliente(String nome, String cpf, String email, String senha) {
        Cliente u = new Cliente(nome, cpf, email, senha);
        repositorioUsuario.getUsuarios().add(u);
        JOptionPane.showMessageDialog(null, "CLIENTE ADICIONADO");       
    }

    public void adcionarGerente(String nome, String cpf, String email, String senha) {
        Gerente u = new Gerente(nome, cpf, email, senha);
        repositorioUsuario.getUsuarios().add(u);
    }

    public void adicionarOperador(String nome, String cpf, String email, String senha) {
        Operador u = new Operador(nome, cpf, email, senha);
        repositorioUsuario.getUsuarios().add(u);
        JOptionPane.showMessageDialog(null, "OPERADOR ADICIONADO");       
    }

    public void mostrarLivros() {
        if(repositorioLivro.getLivros().isEmpty()){
            JOptionPane.showMessageDialog(null, "NÃO HÁ LIVROS CADASTRADOS");
        }
        Collection<Livro> li = repositorioLivro.getLivros().values();
        for (Livro l : li) {
            System.out.println(l);
        }
    }

    public void mostrarClientes() {
        int cont = 0;
        for (Usuario u : repositorioUsuario.getUsuarios()) {
            if (u instanceof Cliente) {
                System.out.println(u);
                cont++;
            }
        }
        if (cont==0){
            JOptionPane.showMessageDialog(null, "NÃO HÁ CLIENTES CADASTRADOS");
        }
        
    }

    public void mostrarOperadores() {
        int cont = 0;
        for (Usuario u : repositorioUsuario.getUsuarios()) {
            if (u instanceof Operador) {
                System.out.println(u);
                cont++;
            } 
        }
        if (cont==0){
            JOptionPane.showMessageDialog(null, "NÃO HÁ OPERADORES CADASTRADOS");
        }
    }

    public void removerLivro(String titulo) {
        Livro liv = repositorioLivro.getLivros().remove(titulo);
        if (liv == null) {
            JOptionPane.showMessageDialog(null, "LIVRO NÃO ENCONTRADO!");
        } else {
            JOptionPane.showMessageDialog(null, "LIVRO REMOVIDO");
        }
    }

    
    
    public void removerOperador(String cpf){
        Usuario usu = null;
        for(Usuario u: repositorioUsuario.getUsuarios()){
            if(u.getCpf().equals(cpf)){
                usu = u;
            }
        }
        if(usu==null){
            JOptionPane.showMessageDialog(null, "OPERADOR NÃO ENCONTRADO!");
        }else{
            repositorioUsuario.getUsuarios().remove(usu);
            JOptionPane.showMessageDialog(null, "OPERADOR REMOVIDO!");
        }
    }

    public void buscarLivro(String titulo) {
        if (repositorioLivro.getLivros().get(titulo) == null) {
            JOptionPane.showMessageDialog(null, "LIVRO NÃO ENCONTRADO!");
        } else {
            System.out.println(repositorioLivro.getLivros().get(titulo));
        }
    }
    
    public boolean naoExisteLivro(String titulo){
        boolean e = false;
        if(repositorioLivro.getLivros().get(titulo)==null){
            e=true;
        }
        return e;
    }

    public void buscarCliente(String nome) {
        int cont1 = 0;
        int cont2 = 0;
        for (Usuario u : repositorioUsuario.getUsuarios()) {
            if (u instanceof Cliente) {
                cont1++;
                if (u.getNome().equals(nome)) {
                    cont2++;
                    System.out.println(u);
                }
            }
        }
        if (cont1 == 0 && cont2 == 0) {
            JOptionPane.showMessageDialog(null, "NÃO EXISTEM CLIENTES CADASTRADOS!");
        }
        if (cont1 > 0 && cont2 == 0) {
            JOptionPane.showMessageDialog(null, "CLIENTE NÃO ENCONTRADO!");
        }
    }
    
    public void buscarOperador(String nome) {
        int cont1 = 0;
        int cont2 = 0;
        for (Usuario u : repositorioUsuario.getUsuarios()) {
            if (u instanceof Operador) {
                cont1++;
                if (u.getNome().equals(nome)) {
                    cont2++;
                    System.out.println(u);
                }
            } 
        }
        if (cont1==0&&cont2==0){
            JOptionPane.showMessageDialog(null, "NÃO EXISTEM OPERADORES CADASTRADOS!");
        }
        if(cont1>0&&cont2==0){
            JOptionPane.showMessageDialog(null, "OPERADOR NÃO ENCONTRADO!");
        }
    }
    
    public void pegarLivro(String email, String titulo){
        Livro l = repositorioLivro.getLivros().get(titulo);
        for(Usuario u: repositorioUsuario.getUsuarios()){
            if(u instanceof Cliente){
                if(u.getEmail().equals(email)){
                    if(((Cliente) u).quantidadeLivros<4){
                        ((Cliente) u).getLivros().add(l);
                        ((Cliente) u).quantidadeLivros++;
                        repositorioLivro.getLivros().get(titulo).setStatus("Indisponível");
                        JOptionPane.showMessageDialog(null, "EMPRÉSTIMO REALIZADO");
                    }else{
                        JOptionPane.showMessageDialog(null, "QUANTIDADE MÁXIMA DE LIVROS ATINGIDA!");
                    }
                }
            }
        }
    }
    
    public boolean estaDisponivel(String titulo){
        boolean e = false;
        Livro l = repositorioLivro.getLivros().get(titulo);
        if (l.getStatus().equals("Disponível")){
            e = true;
        }
        return e;
    }
    
    public void devolverLivro(String email, String titulo){
        Livro l = repositorioLivro.getLivros().get(titulo);
        for(Usuario u: repositorioUsuario.getUsuarios()){
            if(u instanceof Cliente){
                if(u.getEmail().equals(email)){
                    ((Cliente) u).getLivros().remove(l);
                    ((Cliente) u).quantidadeLivros--;
                    repositorioLivro.getLivros().get(titulo).setStatus("Disponível");
                    JOptionPane.showMessageDialog(null, "LIVRO DEVOLVIDO!");
                }
            }
        }     
    }
    
    public void mostrarMeusLivros(String email){
        for(Usuario u: repositorioUsuario.getUsuarios()){
            if (u instanceof Cliente){
                if (((Cliente) u).getLivros().isEmpty()){
                    JOptionPane.showMessageDialog(null, "VOCÊ NÃO TEM LIVROS!");
                }
                if(u.getEmail().equals(email)){
                    for(Livro l: ((Cliente) u).getLivros()){
                        System.out.println(l.getTitulo());
                    }
                }
            }
        }
    }
    
    public boolean existeEmail(String email){
        boolean e = false;
        for(Usuario u: repositorioUsuario.getUsuarios()){
            if(u.getEmail().equals(email)){
                e = true;
            }
        }
        return e;
    }
    
    public boolean existeCpf(String cpf){
        boolean e = false;
        for(Usuario u: repositorioUsuario.getUsuarios()){
            if(u.getCpf().equals(cpf)){
                e = true;
            }
        }
        return e;
    }
    
    public void verficarLogin(String email, String senha){
        int cont = 0;
        String nome = null;
        for (Usuario u: repositorioUsuario.getUsuarios()){
            if(u.getEmail().equals(email)&&u.getSenha().equals(senha)){
                cont++;
                nome = u.getNome();
            }
        }
        if (cont==0){
            JOptionPane.showMessageDialog(null, "E-MAIL OU SENHA INCORRETOS!");
        }else{
            System.out.println("\nBem-Vindo "+nome);
        }
    }
    
    public Usuario verificarTipo(String email, String senha){
        Usuario u = null;
        for(Usuario usu: repositorioUsuario.getUsuarios()){
            if(usu.getEmail().equals(email)&&usu.getSenha().equals(senha)){
                u = usu;
            }
        }
        return u;
    }
}