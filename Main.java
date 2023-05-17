import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
	public static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	
    public static void main(String[] args){
    	// Seguradora
    	Seguradora seguradora = new Seguradora("Seguradora A", "(11)1234-5678", "a@email.com", "Avenida Paulista 999");
    	
    	// Alberto
    	LocalDate dataLicencaAlberto = LocalDate.of(2022, 4, 12);
    	LocalDate dataNascimentoAlberto = LocalDate.of(1999, 9, 1);
    	ClientePF alberto = new ClientePF("Alberto da Silva", "Rua das Rosas 24", "123.456.789-09", "masculino", dataLicencaAlberto, "médio", dataNascimentoAlberto, "B");
    	
    	// Bolos S.A.
    	LocalDate dataFundacao = LocalDate.of(2016, 5, 3);
    	ClientePJ bolosSA = new ClientePJ("Bolos S.A.", "Rua A número 73", "123456789098-00", dataFundacao, 10);
    	
    	// Ford Ka
    	Veiculo ka = new Veiculo("KAA1234", "Ford", "Ka", 2020);
    	
    	// Volkswagen Gol
    	Veiculo golzin = new Veiculo("GOL5678", "Volkswagen", "Gol", 2015);
    	
    	// Adicionar veículos nos clientes
    	alberto.adicionarVeiculo(golzin);
    	bolosSA.adicionarVeiculo(ka);
    	
    	// Cadastrar clientes na seguradora
    	seguradora.cadastrarCliente(alberto);
    	seguradora.cadastrarCliente(bolosSA);
    	
    	// Sinistros
    	LocalDate anoNovo = LocalDate.of(2022, 1, 1);
    	seguradora.gerarSinistro(anoNovo, "Rodovia dos Bandeirantes 8743", golzin, alberto);
    	
    	LocalDate aniversarioAlberto = LocalDate.of(2022, 1, 9);
    	seguradora.gerarSinistro(aniversarioAlberto, "Avenida das Pontes 900", golzin, alberto);
    	
    	// Métodos da classe Seguradora
    	System.out.println("Clientes pessoa física: " + seguradora.listarClientes("f"));
    	System.out.println("Clientes pessoa jurídica: " + seguradora.listarClientes("j"));
    	System.out.println("Sinistros Alberto: ");
    	seguradora.visualizarSinistro("Alberto da Silva");
    	System.out.println("Sinistros Bolos S.A.: ");
    	seguradora.visualizarSinistro("Bolos S.A.");
    	System.out.println("Receita da seguradora: ");
    	System.out.println(seguradora.calcularReceita());
    	
    	// Menu
    	Menu.main(args);
    }

    public static class Menu {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            MenuOperacoes menu = MenuOperacoes.HOME;

            while (menu != MenuOperacoes.SAIR) {
            	// Mostrar o menu
                System.out.println(menu.texto());

                // Pegar o input do usuário
                String input = scanner.nextLine();
                // Tirar todos os caracteres não numéricos
                input = input.replaceAll("[^0-9]", "");
                
                if (input.isEmpty())
                	System.out.println("Entrada inválida. Por favor selecione uma das opções listadas.");
                else {
                	int inputInt = Integer.valueOf(input);
                	// Processar o input
                	menu = menu.processarInput(inputInt, scanner);
                }
            }

            scanner.close();
        }
    
        // CADASTRAR
        public static void cadastrarSeguradora(Scanner scanner) {
        	// Nome
        	System.out.println("Nome da seguradora: ");
        	String nome = scanner.nextLine();
        	
        	// Telefone
        	System.out.println("Telefone: ");
        	String telefone = scanner.nextLine();
        	
        	// Email
        	System.out.println("Email: ");
        	String email = scanner.nextLine();
        	
        	// Endereço
        	System.out.println("Endereço: ");
        	String endereco = scanner.nextLine();
        	
        	// Criar seguradora
        	Seguradora seguradora = new Seguradora(nome, telefone, email, endereco);
        	
        	if (listaSeguradoras.add(seguradora))
        		System.out.println("Seguradora cadastrada com sucesso!");
        	else
        		System.out.println("Não foi possível cadastrar a seguradora.");
        }
        
        public static void cadastrarClientePF(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	// Seguradora
            System.out.println("Seguradora: ");
            String nomeSeguradora = scanner.nextLine();
            Seguradora seguradora = new Seguradora();
            	
            // Encontrar a seguradora em listaSeguradoras:
            for (Seguradora s : listaSeguradoras)
            	if (s.getNome().equals(nomeSeguradora))
            		seguradora = s;
            	
            if (seguradora.getNome().equals("")) { // Não existe seguradora com o nome fornecido.
            	System.out.println("Nenhuma seguradora tem o nome fornecido.");
            	return;
            }
        	
        	// Nome
        	System.out.println("Nome do cliente: ");
        	String nome = scanner.nextLine();
        	
        	// Endereço
        	System.out.println("Endereço: ");
        	String endereco = scanner.nextLine();
        	
        	// CPF
        	String cpf = "";
        	boolean cpfValido = false;
        	
        	do {
        		System.out.println("CPF: ");
        		cpf = scanner.nextLine();
        		
        		if (Validacao.validarCPF(cpf))
        			cpfValido = true;
        		else
        			System.out.println("CPF inválido. Por favor informe um CPF válido.");
        	} while (!cpfValido);
        	
        	// Gênero
        	System.out.println("Gênero: ");
        	String genero = scanner.nextLine();
        	
        	// Data da licença
        	LocalDate dataLicenca = getData(scanner, "Data da licença: DD/MM/AAAA");
        	
        	// Educação
        	System.out.println("Educação: ");
        	String educacao = scanner.nextLine();
        	
        	// Data de nascimento
        	LocalDate dataNascimento = getData(scanner, "Data de nascimento: DD/MM/AAAA");
        	
        	// Classe econômica
        	System.out.println("Classe econômica: ");
        	String classeEconomica = scanner.nextLine();
        	
        	// Criar o cliente
        	ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        	ClientePF cliente = new ClientePF(nome, endereco, listaVeiculos, cpf, genero, dataLicenca, educacao, dataNascimento, classeEconomica);
        	cliente.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cliente));
        	
        	adicionarCliente(seguradora, cliente);
        }
        
        public static void cadastrarClientePJ(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	// Seguradora
            System.out.println("Seguradora: ");
            String nomeSeguradora = scanner.nextLine();
            Seguradora seguradora = new Seguradora();
            	
            // Encontrar a seguradora em listaSeguradoras:
            for (Seguradora s : listaSeguradoras)
            	if (s.getNome().equals(nomeSeguradora))
            		seguradora = s;
            	
            if (seguradora.getNome().equals("")){ // Não existe seguradora com o nome fornecido.
            	System.out.println("Nenhuma seguradora tem o nome fornecido.");
            	return;
            }
        	
        	// Nome
        	System.out.println("Nome do cliente: ");
        	String nome = scanner.nextLine();
        	
        	// Endereço
        	System.out.println("Endereço: ");
        	String endereco = scanner.nextLine();
        	
        	// CNPJ
        	String cnpj = "";
        	boolean cnpjValido = false;
        	
        	do {
        		System.out.println("CNPJ: ");
        		cnpj = scanner.nextLine();
        		
        		if (Validacao.validarCNPJ(cnpj))
        			cnpjValido = true;
        		else
        			System.out.println("CNPJ inválido. Por favor informe um CNPJ válido.");
        	} while (!cnpjValido);
        	
        	
        	// Data de fundação
        	LocalDate dataFundacao = getData(scanner, "Data de fundação: DD/MM/AAAA");
        	
        	// Número de funcionários
        	System.out.println("Número de funcionários: ");
        	String qtdeFuncionariosString = scanner.nextLine();
        	qtdeFuncionariosString = qtdeFuncionariosString.replaceAll("[^0-9]", "");
        	int qtdeFuncionarios = 0;
        	
        	if (qtdeFuncionariosString.isEmpty()) {
            	System.out.println("Entrada inválida");
            	return;
        	}
            else
            	qtdeFuncionarios = Integer.valueOf(qtdeFuncionariosString);
        	
        	// Criar o cliente
        	ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        	ClientePJ cliente = new ClientePJ(nome, endereco, listaVeiculos, cnpj, dataFundacao, qtdeFuncionarios);
        	cliente.setValorSeguro(seguradora.calcularPrecoSeguroCliente(cliente));        	
        	
        	adicionarCliente(seguradora, cliente);
        }
        
        private static void adicionarCliente(Seguradora seguradora, Cliente cliente) {
        	// Procurar seguradora e adicionar o cliente:
        	boolean seguradoraEncontrada = false;
        	
        	for (Seguradora s : listaSeguradoras) {
        		if (s == seguradora) {
        			seguradoraEncontrada = true;
        			
        			if (s.cadastrarCliente(cliente))
        				System.out.println("Cliente cadastrado com sucesso!");
        			else
        				System.out.println("Não foi possível cadastrar o cliente.");
        		}
        	}
        	
        	if (!seguradoraEncontrada)
        		System.out.println("Nenhuma seguradora tem o nome fornecido.");
        }
    
        public static void cadastrarVeiculo(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = encontrarSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	Cliente cliente = getCliente(scanner, seguradora);
        	
        	if (cliente.getNome().equals("")) // O cliente não foi encontrado.
        		return;
        	        	
        	// Placa
        	System.out.println("Placa do veículo: ");
        	String placa = scanner.nextLine();
        	
        	// Marca
        	System.out.println("Marca: ");
        	String marca = scanner.nextLine();
        	
        	// Modelo
        	System.out.println("Modelo: ");
        	String modelo = scanner.nextLine();
        	
        	// Ano de fabricação
        	System.out.println("Ano de fabricação: ");
        	String anoFabricacaoString = scanner.nextLine();
        	anoFabricacaoString = anoFabricacaoString.replaceAll("[^0-9]", "");
        	int anoFabricacao = 0;
        	
        	if (anoFabricacaoString.isEmpty()) {
            	System.out.println("Entrada inválida");
            	return;
        	}
            else
            	anoFabricacao = Integer.valueOf(anoFabricacaoString);
        	
        	// Criar o veículo e adicionar à lista do cliente:
        	Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
        	
        	if(cliente.adicionarVeiculo(veiculo))
        		System.out.println("Veículo cadastrado com sucesso!");
        	else
        		System.out.println("O veículo não pôde ser cadastrado");      	
        	
        }
        
        private static Cliente getCliente(Scanner scanner, Seguradora seguradora) {
        	// Pega do terminal um CPF ou CNPJ e encontra o cliente. Retorna um Cliente com parâmetros
        	// default se o CPF/CNPJ for inválido ou se o cliente não existir.
        	
        	// CPF ou CNPJ do cliente:      	
        	String cpfCNPJ = "";
        	boolean cpfValido = false;
        	boolean cnpjValido = false;
        	
        	do {
        		System.out.println("CPF ou CNPJ: ");
        		cpfCNPJ = scanner.nextLine();
        		
        		if (Validacao.validarCPF(cpfCNPJ))
        			cpfValido = true;
        		else if (Validacao.validarCNPJ(cpfCNPJ))
        			cnpjValido = true;
        		else
        			System.out.println("CPF ou CNPJ inválido. Por favor informe um valor válido.");
        	} while (!cpfValido && !cnpjValido);        	
        	
        	Cliente cliente = new Cliente();
        	boolean clienteEncontrado = false;
        	
        	for (Cliente c : seguradora.getListaClientes())
        		if (cpfValido && c instanceof ClientePF) {
        			ClientePF pF = (ClientePF) c;
        			
        			if (pF.getCPF().equals(cpfCNPJ)) {
        				clienteEncontrado = true;
        				cliente = pF;
        			}
        		} else if (cnpjValido && c instanceof ClientePJ) {
        			ClientePJ pJ = (ClientePJ) c;
        			
        			if (pJ.getCNPJ().equals(cpfCNPJ)) {
        				clienteEncontrado = true;
        				cliente = pJ;
        			}
        		}
        	
        	if (!clienteEncontrado)
        		System.out.println("Cliente não encontrado na seguradora.");
        	
        	return cliente;
        }
        
        private static LocalDate getData(Scanner scanner, String mensagem) {
        	// Pega uma data do terminal, mostrando a mensagem fornecida.
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	LocalDate dataFinal = LocalDate.parse("01/01/0001", formatter);
        	
        	do {
        		System.out.println(mensagem);
            	String dataString = scanner.nextLine();
            	
            	try {
            		LocalDate data = LocalDate.parse(dataString, formatter);
            		dataFinal = data;
            	} catch (Exception DateTimeParseException) {
            		System.out.println("Formato de data inválido. Por favor use o formato DD/MM/AAAA");
            	}            	
        	} while (dataFinal.getYear() == 1);
        	
        	return dataFinal;
        }
        
        // LISTAR
        public static void listarClientes(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	// PF ou PJ
        	System.out.println("Pessoa física (f) ou pessoa jurídica (j)? ");
        	String pfOuPJ = scanner.nextLine();
        	
        	// Seguradora:
        	System.out.println("Seguradora: ");
        	String nomeSeguradora = scanner.nextLine();
        	Seguradora seguradora = new Seguradora();
        	
        	// Verificar se a seguradora tem clientes cadastrados:
        	for (Seguradora s : listaSeguradoras)
        		if (s.getNome().equals(nomeSeguradora))
        			if (s.getListaClientes().isEmpty()){
        				System.out.println("Não há clientes cadastrados nessa seguradora.");
        				return;
        			} else
        				seguradora = s;
        	
        	if (seguradora.getNome().equals("")) { // Não existe seguradora com o nome fornecido.
        		System.out.println("Nenhuma seguradora tem o nome fornecido.");
        		return;
        	}
        	
        	System.out.println(seguradora.listarClientes(pfOuPJ));
        }
        
        public static void listarSinistrosSeguradora() {
        	if (!existeSeguradora())
        		return;        	
        	
        	for (Seguradora seg : listaSeguradoras) {
        		if (seg.getListaSinistros().size() == 0){
        			System.out.println("Seguradora " + seg.getNome() + " não tem sinistros cadastrados.");
        		}
        		
        		System.out.println("Seguradora " + seg.getNome() + ":");
        		
        		for (Sinistro sin : seg.getListaSinistros())
        			System.out.println(sin);
        		
        		System.out.println("");
        	}
        }
        
        public static void listarSinistrosCliente() {
        	if (!existeSeguradora())
        		return;
        	
        	ArrayList<Cliente> clientesListados = new ArrayList<Cliente>();
        	
        	for (Seguradora seg : listaSeguradoras) {        		
        		for (Cliente cli : seg.getListaClientes()) {
        			// Verifique se o cliente já foi listado
        			if (clientesListados.contains(cli))
        				break;
        			// Se não, imprima o cliente
        			System.out.println("Cliente: " + cli.getNome());
        			
        			for (Seguradora seg1 : listaSeguradoras)
        				for (Sinistro sin : seg1.getListaSinistros())
        					if (sin.getCliente().getNome().equals(cli.getNome())) // Imprima os sinistros do cliente nesta seguradora
        						System.out.print(sin);
        			clientesListados.add(cli);
        		}
        		
        		System.out.println("");
        	}
        }
        
        public static void listarVeiculosCliente() {
        	if (!existeSeguradora())
        		return;
        	
        	for (Seguradora s : listaSeguradoras) {        		
        		for (Cliente c : s.getListaClientes()) {
        			// Imprimir o cliente
        			System.out.println(c);
        			
        			// Imprimir os veículos do cliente
        			for (Veiculo v : c.getListaVeiculos())
        				System.out.println(v);
        		}
        		
        		System.out.println("");
        	}
        }
        
        public static void listarVeiculosSeguradora() {
        	if (!existeSeguradora())
        		return;
        	
        	for (Seguradora s : listaSeguradoras) {
        		// Imprimir a seguradora
        		System.out.println(s);
        		
        		// Imprimir os veículos cobertos pela seguradora
        		for (Cliente c : s.getListaClientes())
        			for (Veiculo v : c.getListaVeiculos())
        				System.out.println(v);
        		
        		System.out.println("");
        	}
        }
        
        //EXCLUIR
        public static void excluirCliente(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = encontrarSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Nome do cliente
        	System.out.println("Nome do cliente: ");
        	String nomeCliente = scanner.nextLine();

        	if (seguradora.removerCliente(nomeCliente))
        		System.out.println("Cliente removido com sucesso!");
        	else
        		System.out.println("O nome informado não corresponde a nenhum cliente da seguradora.");
        }
        
        public static void excluirVeiculo(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = encontrarSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Placa do veículo
        	System.out.println("Nome do cliente: ");
        	String placa = scanner.nextLine();
        	
        	for (Cliente c : seguradora.getListaClientes())
        		if (c.removerVeiculo(placa)) {
        			System.out.println("Veículo removido com sucesso!");
        			return;
        		}
        	
        	System.out.println("Não foi possível remover o veículo especificado.");
        }
        
        public static void excluirSinistro(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = encontrarSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// ID
        	System.out.println("ID do sinistro: ");
        	String idString = scanner.nextLine();
        	idString = idString.replaceAll("[^0-9]", "");
        	int id = 0;
        	
        	if (idString.isEmpty()) {
            	System.out.println("Entrada inválida");
            	return;
        	}
            else
            	id = Integer.valueOf(idString);
        	
        	if (seguradora.removerSinistro(id))
        		System.out.println("Sinistro removido com sucesso!");
        	else
        		System.out.println("Não foi possível remover o sinistro.");
        }
        
        public static void gerarSinistro(Scanner scanner) {
        	// Pegar seguradora do terminal e verificar se tem clientes:
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = encontrarSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	if (seguradora.getListaClientes().size() == 0) {
        		System.out.println("A seguradora não tem clientes cadastrados. Por favor cadastre um cliente antes de gerar um sinistro.");
        		return;
        	}
        	
        	// Pegar cliente do terminal e verificar se tem veículos:
        	Cliente cliente = getCliente(scanner, seguradora);
        	
        	if (cliente.getNome().equals("")) // O cliente não foi encontrado.
        		return;
        	
        	if (cliente.getListaVeiculos().size() == 0) {
        		System.out.println("O cliente não tem veículos cadastrados. Por favor cadastre um cliente antes de gerar um sinistro.");
        	}
        	
        	// Pegar placa do veículo e verificar se já foi cadastrado:
        	System.out.println("Placa do veículo: ");
        	String placa = scanner.nextLine();
        	Veiculo veiculo = new Veiculo();
        	
        	for (Veiculo v : cliente.getListaVeiculos())
        		if (v.getPlaca().equals(placa)) {
        			veiculo = v;
        			break;
        		}
        			
        	
        	if (veiculo.getPlaca().equals("")) { // O cliente não tem veículo com a placa fornecida
        		System.out.println("O cliente não tem nenhum veículo com a placa fornecida. Por favor cadastre o veículo.");
        		return;
        	}
        	
        	// Data
        	LocalDate data = getData(scanner, "Data do sinistro: DD/MM/AAAA");
        	
        	// Endereço
        	System.out.println("Endereço do sinistro: ");
        	String endereco = scanner.nextLine();
        	
        	if (seguradora.gerarSinistro(data, endereco, veiculo, cliente))
        		System.out.println("Sinistro gerado com sucesso!");
        	else
        		System.out.println("Não foi possível gerar o sinistro.");
        }
        
        public static void transferirSeguro(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = encontrarSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Pegar os clientes do terminal:
        	System.out.println("Cliente doador:");
        	Cliente doador = getCliente(scanner, seguradora);
        	
        	if (doador.getNome().equals("")) // O cliente não foi encontrado.
        		return;
        	
        	System.out.println("Cliente receptor: ");
        	Cliente receptor = getCliente(scanner, seguradora);
        	
        	if (receptor.getNome().equals("")) // O cliente não foi encontrado.
        		return;
        	
        	// Transferir veículos de doador a receptor:
        	int numVeiculosDoador = doador.getListaVeiculos().size();
        	
        	for (int i = 0; i < numVeiculosDoador; i++) {
        		receptor.adicionarVeiculo(doador.getListaVeiculos().get(0));
        		doador.removerVeiculo(doador.getListaVeiculos().get(0).getPlaca());
        	}
        	
        	// Atualizar o valor do seguro dos clientes:
        	doador.setValorSeguro(seguradora.calcularPrecoSeguroCliente(doador));
        	receptor.setValorSeguro(seguradora.calcularPrecoSeguroCliente(receptor));
        	
        	System.out.println("Seguro transferido com sucesso!");
        }
        
        public static void calcularReceitaSeguradora(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = encontrarSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	System.out.println("Receita da seguradora " + seguradora.getNome() + ": " + seguradora.calcularReceita());       		
        }
        
        private static boolean existeSeguradora() {
        	if (listaSeguradoras.isEmpty()) {
        		System.out.println("Para realizar a operação, primeiro cadastre uma seguradora.");
        		return false;
        	}
        	return true;
        }
        
        private static Seguradora encontrarSeguradora(Scanner scanner) {
        	// Seguradora:
        	System.out.println("Seguradora: ");
        	String nomeSeguradora = scanner.nextLine();
        	Seguradora seguradora = new Seguradora();
        	
        	// Verificar se a seguradora tem clientes cadastrados:
        	for (Seguradora s : listaSeguradoras)
        		if (s.getNome().equals(nomeSeguradora))
        			if (s.getListaClientes().isEmpty()){
        				System.out.println("Não há clientes cadastrados nessa seguradora.");
        				return null;
        			} else
        				seguradora = s;
        	
        	if (seguradora.getNome().equals("")) { // Não existe seguradora com o nome fornecido.
        		System.out.println("Nenhuma seguradora tem o nome fornecido.");
        		return null;
        	}
        	
        	return seguradora;
        }
    }
}