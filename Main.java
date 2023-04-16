import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

/* To do
 * Corrigir cadastrarClientes()
 * Adicionar menu para poder escolher o veículo do sinistro em gerarSinistro()
 * */

public class Main {	
    public static void main(String[] args){
    	Seguradora seguradora = new Seguradora("Seguros SA", "080010101010", "seguros@seguros.com", "Avenida 1, 345", 
    			Collections.<Sinistro>emptyList(), Collections.<Cliente>emptyList());
    	userInterface(seguradora);
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
//    	Veiculo veiculo = new Veiculo("AAA1A111", "FORD", "KA", 2013);
//    	List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
//    	listaVeiculos.add(veiculo);
//    	Cliente cliente = new Cliente("Maria", "Rua 2 numero 3", listaVeiculos);
//    	
//    	List<Sinistro> listaSinistros = new ArrayList<Sinistro>(); // Declaring these lists feels so wrong. Find a better way.
//    	List<Cliente> listaClientes = new ArrayList<Cliente>();
//    	Seguradora seguradora = new Seguradora("Porto", "1191919191", "porto@p.com.br", "Avenida 2 434", listaSinistros, listaClientes);
//    	
//    	
//    	Sinistro sinistro = new Sinistro("12/03/2023", "ABC 123", seguradora, veiculo, cliente);
//        
//    	System.out.println(cliente.toString());
//    	System.out.println(sinistro.toString());
//    	System.out.println(veiculo.toString());
//    	System.out.println(seguradora.toString());
    }
    
    public static void userInterface(Seguradora seguradora){
    	homeScreen(seguradora);
    }
    
    public static void homeScreen(Seguradora seguradora){
    	System.out.println("O que você gostaria de fazer?");
    	System.out.println("[1] Cadastrar cliente");
    	System.out.println("[2] Remover cliente");
    	System.out.println("[3] Listar clientes");
    	System.out.println("[4] Gerar sinistro");
    	System.out.println("[5] Visualizar sinistro");
    	System.out.println("[6] Listar sinistros");
    	System.out.println("[7] Sair");
    	
    	processarEntradaHomeScreen(seguradora);
    }
    
    public static void processarEntradaHomeScreen(Seguradora seguradora){
    	Scanner scanner = new Scanner(System.in);
    	String entrada = scanner.nextLine();
//    	scanner.close();
    	
    	switch (entrada){
    	case "1":
    		boolean sucesso = cadastrarCliente(seguradora); // Está dando erro.
    		mensagemCadastrarCliente(sucesso);
    		homeScreen(seguradora);
    		break;
    	case "2":
    		sucesso = removerCliente(seguradora);
    		mensagemRemoverCliente(sucesso);
    		homeScreen(seguradora);
    		break;
    	case "3":
    		listarClientes(seguradora);
    		homeScreen(seguradora);
    		break;
    	case "4":
    		sucesso = gerarSinistro(seguradora);
    		mensagemGerarSinistro(sucesso);
    		homeScreen(seguradora);
    		break;
    	case "5":
    		visualizarSinistro();
    		homeScreen(seguradora);
    		break;
    	case "6":
    		listarSinistros();
    		homeScreen(seguradora);
    		break;
    	case "7":
    		break;
    	default:
    		entradaHomeScreenInvalida(entrada, seguradora);
    	}
    }
    
    public static boolean cadastrarCliente(Seguradora seguradora){
    	Scanner scanner = new Scanner(System.in);
    	String nome, endereco;
    	List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
    	
    	// Nome do cliente:
    	System.out.print("Nome: ");
    	nome = scanner.nextLine();
    	
    	// Endereço:    
    	System.out.print("Endereço: ");
    	endereco = scanner.nextLine();
    	
    	// Veículos:
    	System.out.print("Quantos veículos? ");
    	int numVeiculos = scanner.nextInt();
    	
    	for (int i = 0; i < numVeiculos; i++){
    		String placa, marca, modelo;
    		int anoFabricacao;
    		
    		// Placa:
    		System.out.print("Placa: ");
        	placa = scanner.nextLine();
        	
        	// Marca:
    		System.out.print("Marca: ");
        	marca = scanner.nextLine();
        	
        	// Modelo:
    		System.out.print("Modelo: ");
        	modelo = scanner.nextLine();
    		
        	// Ano de fabricação:
    		System.out.print("Ano de fabricação: ");
        	anoFabricacao = scanner.nextInt();
        	
    		Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
    		listaVeiculos.add(veiculo);
    	}
    	
    	return seguradora.cadastrarCliente(nome, endereco, listaVeiculos);
    }
    
    
    public static void mensagemCadastrarCliente(boolean sucesso){
    	if (cadsucessoastro)
    		System.out.println("Cliente cadastrado com sucesso!");
    	else
    		System.out.println("Cliente não pôde ser cadastrado.");
    }
    
    
    public static boolean removerCliente(Seguradora seguradora){
    	if (seguradora.getListaClientes().size() == 0){
    		System.out.println("Nenhum cliente cadastrado.");
    		return;
    	}
    	
    	System.out.println("Nome do cliente: ");
    	Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        
        return seguradora.removerCliente(nome);
    }
    
    
    public static void mensagemRemoverCliente(boolean sucesso){
    	if (sucesso)
    		System.out.println("Cliente não foi encontrado ou não pôde ser removido.");
    	else
    		System.out.println("Cliente removido com sucesso!");
    }
    
    
    public static void listarClientes(Seguradora seguradora){
    	System.out.println(seguradora.listarClientes());
    }
    
    public static boolean gerarSinistro(Seguradora seguradora){
    	Scanner scanner = new Scanner(System.in);
    	String nome, data, endereco;
    	
    	// Data e endereço do sinistro:
    	System.out.println("Data do sinistro: ");
    	data = scanner.nextLine();
    	System.out.println("Endereço do sinistro: ");
    	endereco = scanner.nextLine();
    	
    	// Cliente:
    	System.out.println("Nome do cliente: ");
    	nome = scanner.nextLine();
    	Cliente cliente = seguradora.getCliente(nome);
    	
    	if (cliente == null){
    		boolean cadastro = cadastrarCliente(seguradora);
    		mensagemCadastrarCliente(cadastro);
    		
    		if (!cadastro)
    			return false;
    	}
    	
    	// Veículo:
    	Veiculo veiculo = cliente.getListaVeiculos().get(0); // Adicionar menu para poder escolher o veículo.
    	
    	return (seguradora.gerarSinistro(data, endereco, seguradora, veiculo, cliente));
    }

    public static void mensagemGerarSinistro(boolean sucesso){
    	if (sucesso)
    		System.out.println("Sinistro gerado com sucesso!");
    	else
    		System.out.println("Sinistro não pôde ser gerado.");    		
    }
    
    public static void visualizarSinistro(){
    	System.out.println("VISUALIZAR SINISTRO");
    }
    
    
    public static void listarSinistros(){
    	System.out.println("LISTAR SINISTROS");
    }
    
    public static void entradaHomeScreenInvalida(String entrada, Seguradora seguradora){
    	System.out.println(entrada + " não corresponde a nenhuma opção.");
    	homeScreen(seguradora);
    }
}