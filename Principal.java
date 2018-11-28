package exec;

import controller.Controlador;
import controller.ParametroVazioException;

import model.Cliente;
import model.Operador;
import model.Usuario;
import java.util.Scanner;
import javax.swing.JOptionPane;
import model.Gerente;

public class Principal {

    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Controlador c = new Controlador();

        c.adcionarGerente("Danilo", "05728113303", "daniloribeiro@alu.ufc.br", "3247");

        String interfaceInicial = "\n*****Bem-vindo à Biblioteca***** \n"
                + "1 - Fazer login \n"
                + "2 - Cadastrar-se como cliente \n"
                + "3 - Sair";

        String interfaceGerente = "\n1 - Cadastrar Operador \n"
                + "2 - Remover Operador \n"
                + "3 - Listar Livros \n"
                + "4 - Listar Clientes \n"
                + "5 - Listar Operadores \n"
                + "6 - Procurar Livro \n"
                + "7 - Procurar Cliente \n"
                + "8 - Procurar Operador \n"
                + "9 - Sair";

        String interfaceOperador = "\n1 - Cadastrar Livro \n"
                + "2 - Remover Livro \n"
                + "3 - Listar Livros \n"
                + "4 - Listar Clientes \n"
                + "5 - Procurar Livro \n"
                + "6 - Procurar Cliente \n"
                + "7 - Sair";

        String interfaceCliente = "\n1 - Listar Livros \n"
                + "2 - Listar Meus Livros \n"
                + "3 - Pegar Livro \n"
                + "4 - Devolver Livro \n"
                + "5 - Sair";

        while (true) {
            System.out.println(interfaceInicial);
            System.out.print("Digite uma opção: ");
            String comando = in.nextLine();
            if (comando.equals("3")) {
                break;
            }
            switch (comando) {
                case "1":
                    System.out.print("Digite seu email: ");
                    String email = in.nextLine();
                    System.out.print("Digite sua senha: ");
                    String senha = in.nextLine();
                    c.verficarLogin(email, senha);
                    Usuario u = c.verificarTipo(email, senha);
                    if (u instanceof Gerente) {
                        System.out.println("Gerente");
                        while (true) {
                            System.out.println(interfaceGerente);
                            System.out.print("Digite uma opção: ");
                            String comandoGerente = in.nextLine();
                            if (comandoGerente.equals("9")) {
                                break;
                            }
                            switch (comandoGerente) {
                                case "1":
                                    System.out.print("Digite o nome: ");
                                    String nome = in.nextLine();
                                    System.out.print("Digite o CPF: ");
                                    String cpf = in.nextLine();
                                    if (c.existeCpf(cpf)) {
                                        JOptionPane.showMessageDialog(null, "Erro: CPF já cadastrado!");
                                        break;
                                    }
                                    System.out.print("Digite o e-mail: ");
                                    String newEmail = in.nextLine();
                                    if (c.existeEmail(newEmail)) {
                                        JOptionPane.showMessageDialog(null, "Erro: e-mail já cadastrado!");
                                        break;
                                    }
                                    System.out.print("Digite a senha: ");
                                    String newSenha = in.nextLine();
                                    
                                    c.adicionarOperador(nome, cpf, newEmail, newSenha);
                                   
                                    break;
                                case "2":
                                    String operador = JOptionPane.showInputDialog("Digite o CPF");
                                    c.removerOperador(operador);
                                    break;
                                case "3":
                                    c.mostrarLivros();
                                    break;
                                case "4":
                                    c.mostrarClientes();
                                    break;
                                case "5":
                                    c.mostrarOperadores();
                                    break;
                                case "6":
                                    String livro = JOptionPane.showInputDialog(null, "Nome do livro");
                                    c.buscarLivro(livro);
                                    break;
                                case "7":
                                    String cliente = JOptionPane.showInputDialog("Nome do cliente");
                                    c.buscarCliente(cliente);
                                    break;
                                case "8":
                                    String os = JOptionPane.showInputDialog("Nome do operador");
                                    c.buscarOperador(os);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "ENTRADA INVÁLIDA");
                                    break;
                            }
                        }
                    } else if (u instanceof Cliente) {
                        System.out.println("Cliente");
                        while (true) {
                            System.out.println(interfaceCliente);
                            System.out.print("Digite uma opção: ");
                            String comandoCliente = in.nextLine();
                            if (comandoCliente.equals("5")) {
                                break;
                            }
                            switch (comandoCliente) {
                                case "1":
                                    c.mostrarLivros();
                                    break;
                                case "2":
                                    c.mostrarMeusLivros(email);
                                    break;
                                case "3":
                                    String livro = JOptionPane.showInputDialog("Nome do livro");
                                    if (c.naoExisteLivro(livro)) {
                                        JOptionPane.showMessageDialog(null, "NÃO EXISTE ESTE LIVRO NA BIBLIOOTECA!");
                                        break;
                                    }
                                    if (!c.estaDisponivel(livro)) {
                                        JOptionPane.showMessageDialog(null, "ESTE LIVRO NÃO ESTÁ DISPONÍVEL!");
                                        break;
                                    } else {
                                        c.pegarLivro(email, livro);

                                    }
                                    break;
                                case "4":
                                    String livroD = JOptionPane.showInputDialog(null, "Nome do livro");
                                    c.devolverLivro(email, livroD);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "ENTRADA INVÁLIDA!");
                                    break;
                            }
                        }
                    } else if (u instanceof Operador) {
                        System.out.println("Operador do Sistema");
                        while (true) {
                            System.out.println(interfaceOperador);
                            System.out.print("Digite uma opção: ");
                            String comandoOperador = in.nextLine();
                            if (comandoOperador.equals("7")) {
                                break;
                            }
                            switch (comandoOperador) {
                                case "1":
                                    System.out.print("Nome do livro: ");
                                    String titulo = in.nextLine();
                                    System.out.print("Autor do livro: ");
                                    String autor = in.nextLine();
                                    System.out.print("Editora: ");
                                    String editora = in.nextLine();
                                    System.out.print("Edição: ");
                                    String edicao = in.nextLine();
                                    c.adicionarLivro(titulo, autor, editora, edicao);

                                    break;
                                case "2":
                                    String tituloR = JOptionPane.showInputDialog(null, "Nome do livro");
                                    c.removerLivro(tituloR);
                                    break;
                                case "3":
                                    c.mostrarLivros();
                                    break;
                                case "4":
                                    c.mostrarClientes();
                                    break;
                                case "5":
                                    String livroBuscar = JOptionPane.showInputDialog("Nome do livro");
                                    c.buscarLivro(livroBuscar);
                                    break;
                                case "6":
                                    String clienteBuscar = JOptionPane.showInputDialog("Nome do cliente");
                                    c.buscarCliente(clienteBuscar);
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "ENTRADA INVÁLIDA!");
                            }
                        }
                    }
                    break;
                case "2":
                    System.out.print("Digite seu nome: ");
                    String nome = in.nextLine();
                    System.out.print("Digite seu CPF: ");
                    String cpf = in.nextLine();
                    if (c.existeCpf(cpf)) {
                        JOptionPane.showMessageDialog(null, "Erro: CPF já cadastrado!");
                        break;
                    }
                    System.out.print("Digite seu email: ");
                    String newEmail = in.nextLine();
                    if (c.existeEmail(newEmail)) {
                        JOptionPane.showMessageDialog(null, "Erro: e-mail já cadastrado!");
                        break;
                    } else {
                        System.out.print("Digite sua senha: ");
                        String newSenha = in.nextLine();
                        
                        c.adicionarCliente(nome, cpf, newEmail, newSenha);
                       
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "ENTRADA INVÁLIDA");
            }
        }
    }
}