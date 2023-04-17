import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collections;

/* To do
 * Por que lista.add() não está funcionando?
 * Especificar o tipo de cliente em cadastrarCliente() e listarClientes()
 * Adicionar menu para poder escolher o veículo do sinistro em gerarSinistro()
 * Consertar a interface toda: problemas com System.in
 * */

public class Main {	
    public static void main(String[] args){
    	Seguradora seguradora = new Seguradora("Seguros SA", "080010101010", "seguros@seguros.com", "Avenida 1, 345", 
    			Collections.<Sinistro>emptyList(), Collections.<Cliente>emptyList());
    	
    	// Cliente Maria:
    	List<Veiculo> veiculosMaria = new ArrayList<Veiculo>();
    	Veiculo kaMaria = new Veiculo("AAA1A111", "FORD", "KA", 2016);
    	veiculosMaria.add(kaMaria);
    	
    	SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
    	String dataLicencaString = "12/11/2015";
    	String dataNascimentoString = "04/03/1994";    	
    	Date dataLicenca = new Date();
    	Date dataNascimento = new Date();
    	
    	try {
    		dataLicenca = format.parse(dataLicencaString);
        	dataNascimento = format.parse(dataNascimentoString);
    	} catch (ParseException e){
    		e.printStackTrace();
    	}
    	ClientePF maria = new ClientePF("Maria", "Rua A, 90", veiculosMaria, "111.111.111-09", "feminino", dataLicenca, 
    			"superior", dataNascimento, "B");
    	seguradora.cadastrarCliente(maria);
    	maria.validarCPF(maria.getCPF());
    	
    	// Cliente JBS:
    	List<Veiculo> veiculosJBS = new ArrayList<Veiculo>();
    	Veiculo jettaJBS = new Veiculo("AAA0A111", "VOLKSWAGEN", "JETTA", 2020);
    	veiculosJBS.add(jettaJBS);
    	
    	String dataFundacaoString = "05-05-1953";
    	Date dataFundacao = new Date();
    	
    	try {
    		dataFundacao = format.parse(dataFundacaoString);
    	} catch (ParseException e){
    		e.printStackTrace();
    	}
    	
    	ClientePJ jbs = new ClientePJ("JBS", "Avenida Paulista 357", veiculosJBS, "12.234.456/678-09", dataFundacao);
    	seguradora.cadastrarCliente(jbs);
    	jbs.validarCNPJ(jbs.getCNPJ());
    	
    	seguradora.gerarSinistro("17/04/2023", "Rua B 34", seguradora, jettaJBS, jbs);
    	seguradora.toString();
    	jbs.toString();
    	maria.toString();
    	seguradora.getListaSinistros().get(0).toString();
    	kaMaria.toString();
    	seguradora.listarClientes("PF");
    	seguradora.visualizarSinistro("jbs");
    	seguradora.listarSinistros();
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
    		sucesso = visualizarSinistro(seguradora);
    		mensagemVisualizarSinistro(sucesso);
    		homeScreen(seguradora);
    		break;
    	case "6":
    		sucesso = listarSinistros(seguradora);
    		mensagemListarSinistros(sucesso);
    		homeScreen(seguradora);
    		break;
    	case "7":
    		break;
    	default:
    		entradaHomeScreenInvalida(entrada, seguradora);
    	}
    	
    	scanner.close();
    }
    
    public static void entradaHomeScreenInvalida(String entrada, Seguradora seguradora){
    	System.out.println(entrada + " não corresponde a nenhuma opção.");
    	homeScreen(seguradora);
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
    	
    	scanner.close();
    	Cliente cliente = new Cliente(nome, endereco, listaVeiculos);
    	return seguradora.cadastrarCliente(cliente);
    }
    
    public static void mensagemCadastrarCliente(boolean sucesso){
    	if (sucesso)
    		System.out.println("Cliente cadastrado com sucesso!");
    	else
    		System.out.println("Cliente não pôde ser cadastrado.");
    }
    
    public static boolean removerCliente(Seguradora seguradora){
    	if (seguradora.getListaClientes().size() == 0){
    		System.out.println("Nenhum cliente cadastrado.");
    		return false;
    	}
    	
    	System.out.println("Nome do cliente: ");
    	Scanner scanner = new Scanner(System.in);
        String nome = scanner.nextLine();
        scanner.close();        
        
        return seguradora.removerCliente(nome);
    }
    
    public static void mensagemRemoverCliente(boolean sucesso){
    	if (sucesso)
    		System.out.println("Cliente não foi encontrado ou não pôde ser removido.");
    	else
    		System.out.println("Cliente removido com sucesso!");
    }
    
    public static void listarClientes(Seguradora seguradora){
    	System.out.println(seguradora.listarClientes("PF"));
    	System.out.println(seguradora.listarClientes("PJ"));
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
    	scanner.close();
    	
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
    
    public static boolean visualizarSinistro(Seguradora seguradora){
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Nome do cliente:");
    	String nome = scanner.nextLine();
    	scanner.close();
    	return seguradora.visualizarSinistro(nome);
    }
    
    public static void mensagemVisualizarSinistro(boolean sucesso){
    	if (!sucesso)
    		System.out.println("O cliente não existe ou não tem sinistros.");
    }
    
    public static boolean listarSinistros(Seguradora seguradora){
    	String listaString = seguradora.listarSinistros();
    	
    	if (listaString == null)
    		return false;
    	else
    		System.out.println(listaString);
    	return true;
    }
    
    public static void mensagemListarSinistros(boolean sucesso){
    	if (!sucesso)
    		System.out.println("Nenhum sinistro cadastrado.");
    }
}