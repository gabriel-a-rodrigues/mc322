import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public enum MenuOperacoes {
    HOME("\n1 - Cadastros\n2 - Listar\n3 - Excluir\n4 - Gerar sinistro\n5 - Transferir seguro\n6 - Calcular receita da seguradora\n0 - Sair\n"),

    CADASTRAR("\n1 - Cadastrar cliente PF\n2 - Cadastrar cliente PJ\n3 - Cadastrar veículo\n4 - Cadastrar seguradora\n5 - Cadastrar seguro\n6 - Voltar"),

    LISTAR("\n1 - Listar clientes\n2 - Listar sinistros por seguradora\n3 - Listar sinistros por cliente\n4 - Listar veículos por cliente\n5 - Listar veículos por seguradora\n6 - Voltar\n"),

    EXCLUIR("\n1 - Excluir cliente\n2 - Excluir veículo\n3 - Excluir sinistro\n4 - Voltar\n"),

    SAIR("");

    private final String texto;

    MenuOperacoes(String texto) {
        this.texto = texto;
    }

    public String texto() {
        return texto;
    }

    public MenuOperacoes processarInput(int input, Scanner scanner) {
        switch (this) {
            case HOME:
                switch (input) {
                    case 1: // Cadastros
                        return CADASTRAR;
                    case 2: // Listar
                        return LISTAR;
                    case 3: // Excluir
                        return EXCLUIR;
                    case 4: // Gerar sinistro
                    	Main.Menu.gerarSinistro(scanner);
                        return HOME;
                    case 5: // Transferir seguro
                    	Main.Menu.transferirSeguro(scanner);
                        return HOME;
                    case 6: // Calcular receita da seguradora
                    	Main.Menu.calcularReceitaSeguradora(scanner);
                        return HOME;
                    case 0: // Sair
                        return SAIR;
                    default:
                        System.out.println("Opção " + input + " inválida.");
                        return HOME;
                }
            case CADASTRAR:
                switch (input) {
                    case 1: // Cliente PF
                    	Main.Menu.cadastrarClientePF(scanner);
                        return HOME;
                    case 2: // Cliente PJ
                    	Main.Menu.cadastrarClientePJ(scanner);
                    	return HOME;
                    case 3: // Veículo
                        Main.Menu.cadastrarVeiculo(scanner);
                        return HOME;
                    case 4: // Seguradora
                        Main.Menu.cadastrarSeguradora(scanner);
                        return HOME;
                    case 5: // Seguro
                    	Main.Menu.cadastrarSeguro(scanner);
                    case 6: // Voltar
                        return HOME;
                    default:
                    	System.out.println("Opção " + input + " inválida.");
                        return CADASTRAR;
                }
            case LISTAR:
                switch (input) {
                    case 1:
                        Main.Menu.listarClientes(scanner);
                        return HOME;
                    case 2:
                        Main.Menu.listarSinistrosSeguradora(scanner);
                        return HOME;
                    case 3:
                        Main.Menu.listarSinistrosCliente(scanner);
                        return HOME;
                    case 4:
                        Main.Menu.listarVeiculosCliente(scanner);
                        return HOME;
                    case 5:
                        Main.Menu.listarVeiculosSeguradora(scanner);
                        return HOME;
                    case 6:
                        return HOME;
                    default:
                    	System.out.println("Opção " + input + " inválida.");
                        return LISTAR;
                }
            case EXCLUIR:
                switch (input) {
                    case 1: // Cliente
                        Main.Menu.excluirCliente(scanner);
                        return HOME;
                    case 2: // Veículo
                        Main.Menu.excluirVeiculo(scanner);
                        return HOME;
                    case 3: // Sinistro
                        Main.Menu.excluirSinistro(scanner);
                        return HOME;
                    case 4:
                        return HOME;
                    default:
                    	System.out.println("Opção " + input + " inválida.");
                        return EXCLUIR;
                }
            case SAIR:
                return SAIR;
            default:
                throw new AssertionError("Menu de tipo desconhecido: " + this);
        }
    }    
}