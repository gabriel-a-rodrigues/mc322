import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
	public static ArrayList<Seguradora> listaSeguradoras = new ArrayList<Seguradora>();
	
    public static void main(String[] args){
    	// Seguradora A
    	Seguradora seguradoraA = new Seguradora("13.243.546/5768-40", "Seguradora A", "(11) 1234-5678", "a@email.com", "Avenida Paulista 999");
    	listaSeguradoras.add(seguradoraA);
    	
    	// Clientes da Seguradora A    	    	
    	// Alberto cliente
    	LocalDate dataNascimentoAlberto = LocalDate.of(1999, 9, 1);
    	ClientePF albertoCliente = new ClientePF("Alberto da Silva", "(19) 99876-5432", "Rua das Rosas 24", "alberto@gmail.com", 
    			"123.456.789-09", "masculino", "ensino médio completo", dataNascimentoAlberto);
        // Golzin do Alberto
    	Veiculo golzin = new Veiculo("GOL-5678", "Volkswagen", "Gol", 2015);
    	albertoCliente.cadastrarVeiculo(golzin);
    	// Alberto condutor
    	Condutor albertoCondutor = new Condutor(albertoCliente.getCPF(), albertoCliente.getNome(), albertoCliente.getTelefone(), 
    			albertoCliente.getEndereco(), albertoCliente.getEmail(), albertoCliente.getDataNascimento());
    	// Bianca condutora (mesmo seguro que Alberto)
    	LocalDate dataNascimentoBianca = LocalDate.of(2000, 11, 23);
    	Condutor biancaCondutora = new Condutor("359.984.657-09", "Bianca da Silva", "(19) 99876-5433", "Rua das Rosas 24", 
    			"bianca@gmail.com", dataNascimentoBianca);
    	// Criar lista com os dois condutores:
    	ArrayList<Condutor> listaCondutoresAlberto = new ArrayList<Condutor>();
    	listaCondutoresAlberto.add(albertoCondutor);
    	listaCondutoresAlberto.add(biancaCondutora);
    	// Cadastrar Alberto em Seguradora A e gerar seguro:
    	seguradoraA.cadastrarCliente(albertoCliente);
		LocalDate dataInicioSeguroAlberto = LocalDate.of(2023, 2, 21);
    	LocalDate dataFimSeguroAlberto = LocalDate.of(2024, 2, 21);
    	seguradoraA.gerarSeguro(dataInicioSeguroAlberto, dataFimSeguroAlberto, listaCondutoresAlberto, golzin, albertoCliente);
    	
    	// Bolos S.A.
    	LocalDate dataFundacaoBolosSA = LocalDate.of(2016, 5, 3);
    	ClientePJ bolosSA = new ClientePJ("Bolos S.A.", "(19) 2234-9675", "Rua A número 73", "bolos@gmail.com", "12.345.678/9098-00", 
    			dataFundacaoBolosSA, 100);
    	// Veículos de entrega:
    	Veiculo saveiro1 = new Veiculo("SAV-7654", "Volkswagen", "Saveiro", 2012);
    	Veiculo saveiro2 = new Veiculo("SAV-9034", "Volkswagen", "Saveiro", 2016);
    	// Criar frota de entrega e adicionar:
    	Frota frotaEntrega = new Frota("entrega");
    	frotaEntrega.addVeiculo(saveiro1);
    	frotaEntrega.addVeiculo(saveiro2);
    	// Veículos corporativos:
    	Veiculo corolla = new Veiculo("COR-4534", "Toyota", "Corolla", 2021);
    	// Frota corporativa:
    	Frota frotaCorporativa = new Frota("corporativa");
    	frotaCorporativa.addVeiculo(corolla);
    	// Entregadores:
    	LocalDate dataNascimentoMarcos = LocalDate.of(1977, 4, 5);
    	Condutor marcos = new Condutor("568.743.345-73", "Marcos Pereira", "(13) 99823-2337", "Rua das Margaridas 67", 
    			"marcos@gmail.com", dataNascimentoMarcos);
    	LocalDate dataNascimentoElaine = LocalDate.of(1989, 3, 13);
    	Condutor elaine = new Condutor("095.784.938-94", "Elaine Rodrigues", "(11) 99755-5274", "Rua das Hortênsias 92", 
    			"elaine@gmail.com", dataNascimentoElaine);
    	// Criar lista com os dois entregadores:
    	ArrayList<Condutor> listaEntregadoresBolosSA = new ArrayList<Condutor>();
    	listaEntregadoresBolosSA.add(marcos);
    	listaEntregadoresBolosSA.add(elaine);
    	// Motorista corporativo:
    	LocalDate dataNascimentoLuiz = LocalDate.of(1966, 2, 7);
    	Condutor luiz = new Condutor("367.829.809-59", "Luiz Sousa", "(19) 99758-2264", "Rua das Tulipas 211", 
    			"luiz@gmail.com", dataNascimentoLuiz);
    	// Criar lista com o Luiz:
    	ArrayList<Condutor> listaMotoristasBolosSA = new ArrayList<Condutor>();
    	listaMotoristasBolosSA.add(luiz);
    	// Cadastrar Bolos S.A. em Seguradora A e gerar seguros:
    	seguradoraA.cadastrarCliente(bolosSA);
    	// Frota de entrega
    	LocalDate dataInicioSeguroBolosSAFrotaEntrega = LocalDate.of(2022, 3, 4);
    	LocalDate dataFimSeguroBolosSAFrotaEntrega = LocalDate.of(2024, 12, 31);
    	seguradoraA.gerarSeguro(dataInicioSeguroBolosSAFrotaEntrega, dataFimSeguroBolosSAFrotaEntrega, listaEntregadoresBolosSA, 
    			frotaEntrega, bolosSA);
    	// Frota corporativa
    	LocalDate dataInicioSeguroBolosSAFrotaCorporativa = LocalDate.of(2023, 2, 12);
    	LocalDate dataFimSeguroBolosSAFrotaCorporativa = LocalDate.of(2025, 6, 30);
    	seguradoraA.gerarSeguro(dataInicioSeguroBolosSAFrotaCorporativa, dataFimSeguroBolosSAFrotaCorporativa, listaMotoristasBolosSA, 
    			frotaCorporativa, bolosSA);
    	// Cadastrar sinistro do Alberto:
    	LocalDate dataSinistroAlberto = LocalDate.of(2023, 6, 9);
    	String enderecoSinistroAlberto = "Rua das Pedras 324";
    	
    	for (Seguro seguro : seguradoraA.getListaSeguros())
    		// Procurar seguro do Alberto e gerar o sinistro:
    		if (seguro instanceof SeguroPF && ((SeguroPF)seguro).getCliente().getCPF().equals(albertoCliente.getCPF()))
    			seguro.gerarSinistro(dataSinistroAlberto, enderecoSinistroAlberto, albertoCondutor);
    	
    	// Seguradora B
    	Seguradora seguradoraB = new Seguradora("03.712.999/4728-73", "Seguradora B", "(11) 2237-1828", "b@email.com", "Avenida Paulista 1331");
    	listaSeguradoras.add(seguradoraB);
    	
    	// Clientes da Seguradora B    	
    	// Livraria da Maria
    	LocalDate dataFundacao = LocalDate.of(2011, 11, 11);
    	ClientePJ livraria = new ClientePJ("Livraria da Maria", "(11) 2222-0919", "Rua B número 89", "livros@gmail.com", "56.749.301/9283-31", 
    			dataFundacao, 3);
    	// Kwid da livraria:
    	Veiculo kwid = new Veiculo("KWI-3412", "Renault", "Kwid", 2022);
    	// Criar frota de entrega e adicionar:
    	Frota frotaLivraria = new Frota("sóUmKwid");
    	frotaLivraria.addVeiculo(kwid);
    	// Condutores:
    	LocalDate dataNascimentoMaria = LocalDate.of(1981, 3, 2);
    	Condutor maria = new Condutor("153.762.809-77", "Maria da Penha", "(11) 99223-1474", "Rua das Orquídeas 43", 
    			"maria@gmail.com", dataNascimentoMaria);
    	LocalDate dataNascimentoRodrigo = LocalDate.of(1984, 5, 27);
    	Condutor rodrigo = new Condutor("672.334.591-69", "Rodrigo da Penha", "(11) 99081-1334", "Rua das Orquídeas 43", 
    			"rodrigo@gmail.com", dataNascimentoRodrigo);
    	// Criar lista com os dois condutores:
    	ArrayList<Condutor> listaCondutoresLivraria = new ArrayList<Condutor>();
    	listaCondutoresLivraria.add(maria);
    	listaCondutoresLivraria.add(rodrigo);
    	// Cadastrar Livraria da Maria em Seguradora B e gerar seguros:
    	seguradoraB.cadastrarCliente(livraria);
    	// Frota da livraria
    	LocalDate dataInicioSeguroLivraria = LocalDate.of(2021, 04, 22);
    	LocalDate dataFimSeguroLivraria = LocalDate.of(2023, 2, 28);
    	seguradoraB.gerarSeguro(dataInicioSeguroLivraria, dataFimSeguroLivraria, listaCondutoresLivraria, 
    			frotaLivraria, livraria);
    	// Cadastrar sinistro do Rodrigo:
    	LocalDate dataSinistroRodrigo = LocalDate.of(2022, 8, 2);
    	String enderecoSinistroRodrigo = "Rua do Rio 729";
    	seguradoraB.getListaSeguros().get(0).gerarSinistro(dataSinistroRodrigo, enderecoSinistroRodrigo, rodrigo);
    	
    	// toString() das classes:
    	// Seguradora
    	System.out.println(seguradoraB);
    	// ClientePF
    	System.out.println(albertoCliente);
    	// ClientePJ
    	System.out.println(bolosSA);
    	// Frota
    	System.out.println(frotaEntrega);
    	// Sinistro
    	seguradoraB.getSegurosPorCliente(livraria).get(0).visualizarSinistros(rodrigo);
    	// Seguro
    	System.out.println(seguradoraA.getSegurosPorCliente(bolosSA).get(0));
    	// Veículo
    	System.out.println(corolla);
    	// Condutor
    	System.out.println(elaine);
    	
    	// Métodos da classe Seguradora:
    	System.out.println("Receita da seguradora B: " + seguradoraB.calcularReceita());
    	System.out.println("Cliente da seguradora A: " + seguradoraA.listarClientes());
    	// Pegar o cliente pelo nome:
    	System.out.println(seguradoraB.getCliente("Livraria da Maria"));
    	// Cancelar seguro:
    	if (seguradoraA.cancelarSeguro(seguradoraA.getSegurosPorCliente(albertoCliente).get(0).getID()))
    		System.out.println("Seguro do Alberto cancelado!");
    	// Remover um sinistro:
    	if (seguradoraB.removerSinistro(seguradoraB.getListaSeguros().get(0).getListaSinistros().get(0)))
    		System.out.println("Sinistro do Rodrigo removido!");
    	
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
        
        public static void cadastrarClientePF(Scanner scanner) {
        	// Seguradora
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Nome
        	System.out.println("Nome do cliente: ");
        	String nome = scanner.nextLine();
        	
        	// Endereço
        	System.out.println("Endereço: ");
        	String endereco = scanner.nextLine();
        	
        	// Telefone
        	System.out.println("Telefone: ");
        	String telefone = scanner.nextLine();
        	
        	// Email
        	System.out.println("Email: ");
        	String email = scanner.nextLine();
        	
        	// CPF
        	System.out.println("CPF: ");
        	String cpf = getCPF(scanner);
        	
        	// Gênero
        	System.out.println("Gênero: ");
        	String genero = scanner.nextLine();
        	
        	// Educação
        	System.out.println("Educação: ");
        	String educacao = scanner.nextLine();
        	
        	// Data de nascimento
        	System.out.println("Data de nascimento: DD/MM/AAAA");
        	LocalDate dataNascimento = getData(scanner);

        	// Criar o cliente
        	ArrayList<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
        	ClientePF cliente = new ClientePF(nome, telefone, endereco, email, cpf, genero, educacao, dataNascimento, listaVeiculos);
        	
        	adicionarCliente(seguradora, cliente);
        }
        
        public static void cadastrarClientePJ(Scanner scanner) {
        	// Seguradora
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Nome
        	System.out.println("Nome do cliente: ");
        	String nome = scanner.nextLine();
        	
        	// Telefone
        	System.out.println("Telefone: ");
        	String telefone = scanner.nextLine();
        	
        	// Endereço
        	System.out.println("Endereço: ");
        	String endereco = scanner.nextLine();
        	
        	// Email
        	System.out.println("Email: ");
        	String email = scanner.nextLine();
        	
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
        	System.out.println("Data de fundação: DD/MM/AAAA");
        	LocalDate dataFundacao = getData(scanner);
        	
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
        	ArrayList<Frota> listaFrotas = new ArrayList<Frota>();
        	ClientePJ cliente = new ClientePJ(nome, telefone, endereco, email, cnpj, dataFundacao, listaFrotas, qtdeFuncionarios);    	
        	
        	adicionarCliente(seguradora, cliente);
        }
    
        public static void cadastrarVeiculo(Scanner scanner) {
        	// Seguradora
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Cliente        	
        	Cliente cliente = getCliente(scanner, seguradora); // Pega do terminal um CPF ou CNPJ e devolve o cliente.
        	
        	if (cliente == null) // O cliente não foi encontrado.
        		return;

        	// Criar o veículo e cadastrá-lo:
        	Veiculo veiculo = getVeiculo(scanner);
        	
        	if (cliente instanceof ClientePF) 
        		if (((ClientePF)cliente).cadastrarVeiculo(veiculo))
        			System.out.println("Veículo cadastrado com sucesso!");
        		else
        			System.out.println("O veículo não pôde ser cadastrado.");
        	else if (cliente instanceof ClientePJ) {
        		System.out.println("A qual frota o veículo deve ser adicionado? Code:");
        		String code = scanner.nextLine();
        		
        		if (((ClientePJ)cliente).atualizarFrota(veiculo, code))
        			System.out.println("Veículo cadastrado com sucesso!");
        		else
        			System.out.println("O veículo não pôde ser cadastrado.");
        	} 	
        	
        }
        
        public static void gerarSinistro(Scanner scanner) {
        	// Cria um Sinistro e adiciona à lista de sinistros da seguradora.
        	
        	// Seguradora
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	if (seguradora.getListaSeguros().size() == 0) {
        		System.out.println("A seguradora não tem seguros cadastrados. Por favor cadastre um seguro antes de gerar um sinistro.");
        		return;
        	}
        	
        	// Seguro
        	Seguro seguro = getSeguro(scanner, seguradora);
        	
        	if (seguro == null) // ID fornecida não corresponde a nenhum seguro da seguradora.
        		return;
        	        	
        	// Condutor
        	Condutor condutor = getCondutor(scanner);
        	
        	// Data
        	System.out.println("Data do sinistro: DD/MM/AAAA");
        	LocalDate data = getData(scanner);
        	
        	// Endereço
        	System.out.println("Endereço do sinistro: ");
        	String endereco = scanner.nextLine();        	
        	Sinistro sinistro = seguro.gerarSinistro(data, endereco, condutor);
        	
        	if (sinistro != null && condutor.adicionarSinistro(sinistro))
        			System.out.println("Sinistro gerado com sucesso!");
        	else
        		System.out.println("Não foi possível gerar o sinistro.");
        }        
        
        public static void cadastrarSeguradora(Scanner scanner) {
        	// CNPJ
        	System.out.println("CNPJ da seguradora: ");
        	String cnpj = getCNPJ(scanner);
        	
        	// Nome
        	System.out.println("Nome: ");
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
        	Seguradora seguradora = new Seguradora(cnpj, nome, telefone, email, endereco);
        	
        	if (listaSeguradoras.add(seguradora))
        		System.out.println("Seguradora cadastrada com sucesso!");
        	else
        		System.out.println("Não foi possível cadastrar a seguradora.");
        }
        
        public static void cadastrarSeguro(Scanner scanner) {
        	// Seguradora
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Cliente
        	Cliente cliente = getCliente(scanner, seguradora);
        	
        	ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
        	ArrayList<Condutor> listaCondutores = new ArrayList<Condutor>();
        	
        	if (cliente == null)
        		return;
        	
        	// Data de início
        	System.out.println("Data de início: DD/MM/AAAA");
        	LocalDate dataInicio = getData(scanner);
        	
        	// Data de fim
        	System.out.println("Data de fim: DD/MM/AAAA");
        	LocalDate dataFim = getData(scanner);
        	
        	if (cliente instanceof ClientePF) {
        		// Veículo
        		Veiculo veiculo = getVeiculo(scanner);
        		
        		// Gerar o seguro na seguradora
        		if (seguradora.gerarSeguro(dataInicio, dataFim, listaSinistros, listaCondutores, veiculo, (ClientePF)cliente))
        			System.out.println("Seguro cadastrado com sucesso!");
        		else
        			System.out.println("Não foi possível cadastrar o seguro.");
        	} else if (cliente instanceof ClientePJ) {
        		// Frota
        		System.out.println("Code da frota: ");
        		String code = scanner.nextLine();
        		
        		Frota frota = new Frota(code);
        		
        		// Gerar o seguro na seguradora
        		if (seguradora.gerarSeguro(dataInicio, dataFim, listaSinistros, listaCondutores, frota, (ClientePJ)cliente))
        			System.out.println("Seguro cadastrado com sucesso!");
        		else
        			System.out.println("Não foi possível cadastrar o seguro.");        	
        	}       	
        }
                
        // LISTAR
        public static void listarClientes(Scanner scanner) {
        	// Pega uma seguradora do terminal e lista todos os clientes.
        	
        	// Pegar seguradora do terminal:
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null) // Seguradora não foi encontrada.
        		return;
        	
        	System.out.println(seguradora.listarClientes());
        }
        
        public static void listarSinistrosSeguradora(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null) // Seguradora não foi encontrada.
        		return;
        	
        	int countSinistros = 0;
        	
        	for (Seguro seguro : seguradora.getListaSeguros())
        		for (Sinistro sinistro : seguro.getListaSinistros()) {
        			System.out.println(sinistro);
        			countSinistros++;
        		}
        	
        	if (countSinistros == 0)
        		System.out.println("Seguradora " + seguradora.getNome() + " não tem sinistros cadastrados.");
        }
        
        public static void listarSinistrosCliente(Scanner scanner) {
        	// Imprime os sinistros do cliente um a um.
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null) // Seguradora não foi encontrada.
        		return;
        	
        	Cliente cliente = getCliente(scanner, seguradora);
        	
        	if (cliente == null)
        		return;
        	
        	ArrayList<Sinistro> sinistrosCliente = seguradora.getSinistrosPorCliente(cliente);
        	
        	for (Sinistro sinistro : sinistrosCliente)
        		System.out.println(sinistro);
        }
        
        public static void listarVeiculosCliente(Scanner scanner) {
        	// Imprime os veículos do cliente um a um.
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null) // Seguradora não foi encontrada.
        		return;
        	
        	Cliente cliente = getCliente(scanner, seguradora);
        	
        	if (cliente == null)
        		return;
        	
        	if (cliente instanceof ClientePF)
        		for (Veiculo veiculo : ((ClientePF)cliente).getListaVeiculos())
        			System.out.println(veiculo);
        	else if (cliente instanceof ClientePJ)
        		for (Frota frota : ((ClientePJ)cliente).getListaFrotas())
        			for (Veiculo veiculo : frota.getListaVeiculos())
        				System.out.println(veiculo);
        }
        
        public static void listarVeiculosSeguradora(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null) // Seguradora não foi encontrada.
        		return;
        	
        	for (Cliente cliente : seguradora.getListaClientes())
        		if (cliente instanceof ClientePF)
            		for (Veiculo veiculo : ((ClientePF)cliente).getListaVeiculos())
            			System.out.println(veiculo);
            	else if (cliente instanceof ClientePJ)
            		for (Frota frota : ((ClientePJ)cliente).getListaFrotas())
            			for (Veiculo veiculo : frota.getListaVeiculos())
            				System.out.println(veiculo);
        }
        
        //EXCLUIR
        public static void excluirCliente(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
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
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null) // Seguradora não foi encontrada.
        		return;
        	
        	// Placa do veículo
        	System.out.println("Placa do veículo: ");
        	String placa = scanner.nextLine();
        	
        	for (Cliente cliente : seguradora.getListaClientes())
        		if (cliente instanceof ClientePF && ((ClientePF)cliente).removerVeiculo(placa)) {
        			System.out.println("Veículo removido com sucesso!");
        			return;
        		} else if (cliente instanceof ClientePJ && ((ClientePJ)cliente).atualizarFrota(placa)) {
        			System.out.println("Veículo removido com sucesso!");
        			return;
        		}
        	
        	System.out.println("Não foi possível remover o veículo especificado.");
        }
        
        public static void excluirSinistro(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Sinistro
        	Sinistro sinistro = getSinistro(scanner, seguradora);
        	
        	if (sinistro == null) {
        		System.out.println("Não há sinistro com a ID informada.");
        		return;
        	}
        	
        	if (seguradora.removerSinistro(sinistro))
        		System.out.println("Sinistro removido com sucesso!");
        	else
        		System.out.println("Não foi possível remover o sinistro.");
        }
        
        // OUTROS
        public static void transferirSeguro(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	// Pegar os clientes do terminal:
        	System.out.println("Cliente doador:");
        	Cliente doador = getCliente(scanner, seguradora);
        	
        	if (doador == null) // O cliente não foi encontrado.
        		return;
        	
        	System.out.println("Cliente receptor: ");
        	Cliente receptor = getCliente(scanner, seguradora);
        	
        	if (receptor == null) // O cliente não foi encontrado.
        		return;
        	
        	if ((doador instanceof ClientePF && receptor instanceof ClientePJ) ||
        			(doador instanceof ClientePJ && receptor instanceof ClientePF)) {
        		System.out.println("Ambos os clientes devem ser do mesmo tipo (PF ou PJ).");
        		return;
        	}        		
        	
        	Seguro seguro = getSeguro(scanner, seguradora);
        	
        	if (seguro instanceof SeguroPF) {
        		SeguroPF seguroPF = (SeguroPF)seguro;
        		ClientePF receptorPF = (ClientePF)receptor;
        		ClientePF doadorPF = (ClientePF)doador;
        		
        		seguroPF.setCliente(receptorPF); // Mudar o cliente atrelado ao seguro.
        		
        		if (receptorPF.cadastrarVeiculo(seguroPF.getVeiculo())) // Cadastrar o veículo do doador no receptor.
        			if (doadorPF.removerVeiculo(seguroPF.getVeiculo())) // Remover o veículo do doador.
        				seguroPF.setValorMensal(seguroPF.calcularValor(receptorPF)); // Atualizar o preço do seguro.
        			else {
        				receptorPF.removerVeiculo(seguroPF.getVeiculo()); // Devolver o veículo ao receptor.
        				System.out.println("Não foi possível transferir o veículo. Operação cancelada.");
        				return;
        			}
        		else {
        			System.out.println("Não foi possível transferir o veículo. Operação cancelada.");
    				return;
        		}
        	} else if (seguro instanceof SeguroPJ) {
        		SeguroPJ seguroPJ = (SeguroPJ)seguro;
        		ClientePJ receptorPJ = (ClientePJ)receptor;
        		ClientePJ doadorPJ = (ClientePJ)doador;
        		
        		seguroPJ.setCliente(receptorPJ); // Mudar o cliente atrelado ao seguro.
        		
        		if (receptorPJ.cadastrarFrota(seguroPJ.getFrota())) // Cadastrar a frota no receptor.
        			if (doadorPJ.atualizarFrota(seguroPJ.getFrota())) // Remover a frota do doador.
        				seguroPJ.setValorMensal(seguroPJ.calcularValor(receptorPJ));
        			else {
        				receptorPJ.atualizarFrota(seguroPJ.getFrota()); // Devolver a frota ao receptor.
        				System.out.println("Não foi possível transferir a frota. Operação cancelada.");
        				return;
        			}
        		else {
        			System.out.println("Não foi possível transferir a frota. Operação cancelada.");
        			return;
        		}
        	}
        	
        	System.out.println("Seguro transferido com sucesso!");
        }
        
        public static void calcularReceitaSeguradora(Scanner scanner) {
        	if (!existeSeguradora())
        		return;
        	
        	Seguradora seguradora = getSeguradora(scanner);
        	
        	if (seguradora == null)
        		return;
        	
        	System.out.println("Receita da seguradora " + seguradora.getNome() + ": " + seguradora.calcularReceita());       		
        }
        
        // AUXILIARES
        private static boolean existeSeguradora() {
        	if (listaSeguradoras.isEmpty()) {
        		System.out.println("Para realizar a operação, primeiro cadastre uma seguradora.");
        		return false;
        	}
        	return true;
        }
        
        private static Seguradora getSeguradora(Scanner scanner) {
        	// Pega o CNPJ da seguradora do terminal e devolve o objeto seguradora.        	
        	// CNPJ
        	System.out.println("CNPJ da seguradora: ");
        	String cnpj = getCNPJ(scanner);
        	
        	Seguradora seguradora = new Seguradora();
        	
        	// Verificar se a seguradora tem clientes cadastrados:
        	for (Seguradora seg : listaSeguradoras)
        		if (seg.getCNPJ().equals(cnpj))
        			seguradora = seg;
        	
        	if (seguradora.getNome().equals("")) { // Não existe seguradora com o nome fornecido.
        		System.out.println("Nenhuma seguradora tem o CNPJ fornecido.");
        		return null;
        	}
        	
        	return seguradora;
        }
    
        private static Sinistro getSinistro(Scanner scanner, Seguradora seguradora) {
        	System.out.println("ID do sinistro: ");
        	int idSinistro = 0;
        	
        	do {
        		String idString = scanner.nextLine();
            	idString = idString.replaceAll("[^0-9]", "");
            	
            	if (idString.isEmpty())
                	System.out.println("Entrada inválida. Por favor informe um número.");
                else
                	idSinistro = Integer.valueOf(idString);
        	} while (idSinistro == 0);
        	
        	for (Seguro seguro : seguradora.getListaSeguros())
        		for (Sinistro sinistro1 : seguro.getListaSinistros())
        			if (sinistro1.getID() == idSinistro)
        				return sinistro1;
        	return null;
        }
        
        private static Seguro getSeguro(Scanner scanner, Seguradora seguradora) {
        	int idSeguro = 0;
        	
        	System.out.println("ID do seguro: ");
        	
        	do {
        		String idSeguroString = scanner.nextLine();
            	idSeguroString = idSeguroString.replaceAll("[^0-9]", "");
        		
        		if (idSeguroString.isEmpty())
                	System.out.println("Entrada inválida. Por favor informe um número.");
                else
                	idSeguro = Integer.valueOf(idSeguroString);
        	} while (idSeguro == 0);
        	
        	// Verificar se o seguro está cadastrado:
        	Seguro seguro = seguradora.getSeguro(idSeguro);
        	
        	if (seguro == null) {
        		System.out.println("Não há seguro com a ID fornecida.");
        		return null;
        	}
        	
        	return seguro;
        }
        
        private static Condutor getCondutor(Scanner scanner) {
        	// CPF
        	System.out.println("CPF do condutor: ");
        	String cpf = getCPF(scanner);
        	
        	// Nome
        	System.out.println("Nome: ");
        	String nome = scanner.nextLine();
        	
        	// Telefone
        	System.out.println("Telefone: ");
        	String telefone = scanner.nextLine();
        	
        	// Endereço
        	System.out.println("Endereço: ");
        	String endereco = scanner.nextLine();
        	
        	// Email
        	System.out.println("Email: ");
        	String email = scanner.nextLine();
        	
        	// Data de nascimento
        	System.out.println("Data de nascimento: DD/MM/AAAA");
        	LocalDate dataNascimento = getData(scanner);
        	
        	// Criar condutor e devolver:
        	Condutor condutor = new Condutor(cpf, nome, telefone, endereco, email, dataNascimento);
        	return condutor;
        }
        
        private static String getCPF(Scanner scanner) {
        	String cpf = "";
        	boolean cpfValido = false;
        	
        	do {
        		cpf = scanner.nextLine();
        		
        		if (Validacao.validarCPF(cpf))
        			cpfValido = true;
        		else
        			System.out.println("CPF inválido. Por favor informe um CPF válido.");
        	} while (!cpfValido);
        	
        	cpf = cpf.replaceAll("[^0-9]", "");
        	return cpf;
        }
        
        private static String getCNPJ(Scanner scanner) {
        	String cnpj = "";
        	boolean cnpjValido = false;
        	
        	do {
        		cnpj = scanner.nextLine();
        		
        		if (Validacao.validarCNPJ(cnpj))
        			cnpjValido = true;
        		else
        			System.out.println("CNPJ inválido. Por favor informe um CNPJ válido.");
        	} while (!cnpjValido);
        	
        	cnpj = cnpj.replaceAll("[^0-9]", "");
        	return cnpj;
        }
    
        private static Cliente getCliente(Scanner scanner, Seguradora seguradora) {
        	// Pega do terminal um CPF ou CNPJ e encontra o cliente. Retorna null se o cliente não existir.
        	
        	// CPF ou CNPJ do cliente:      	
        	String cpfOuCNPJ = "";
        	boolean cpfValido = false;
        	boolean cnpjValido = false;
        	System.out.println("CPF ou CNPJ do cliente: ");
        	
        	// Pedir um CPF ou CNPJ válido:
        	do {
        		cpfOuCNPJ = scanner.nextLine();
        		
        		if (Validacao.validarCPF(cpfOuCNPJ))
        			cpfValido = true;
        		else if (Validacao.validarCNPJ(cpfOuCNPJ))
        			cnpjValido = true;
        		else
        			System.out.println("CPF ou CNPJ inválido. Por favor informe um valor válido.");
        	} while (!cpfValido && !cnpjValido);        	
        	
        	// Encontrar cliente:
        	for (Cliente cliente : seguradora.getListaClientes())
        		if (cpfValido && cliente instanceof ClientePF) {
        			ClientePF clientePF = (ClientePF)cliente;
        			
        			if (clientePF.getCPF().equals(cpfOuCNPJ))
        				return clientePF;
        		} else if (cnpjValido && cliente instanceof ClientePJ) {
        			ClientePJ clientePJ = (ClientePJ)cliente;
        			
        			if (clientePJ.getCNPJ().equals(cpfOuCNPJ))
        				return clientePJ;
        		}

        	System.out.println("Cliente não encontrado na seguradora.");        	
        	return null;
        }
        
        private static LocalDate getData(Scanner scanner) {
        	// Pega uma data do terminal, mostrando a mensagem fornecida.
        	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        	LocalDate dataFinal = LocalDate.parse("01/01/0001", formatter);
        	
        	do {
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
    
        private static Veiculo getVeiculo(Scanner scanner) {
        	// Pega as informações do veículo do terminal e devolve um objeto Veículo. Retorna null se o veículo não puder ser criado.
        	
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
        	String anoFabricacaoString = "";
        	int anoFabricacao = 0;
        	
        	do {
        		anoFabricacaoString = scanner.nextLine();
            	anoFabricacaoString = anoFabricacaoString.replaceAll("[^0-9]", "");
        		
        		if (anoFabricacaoString.isEmpty())
                	System.out.println("Entrada inválida. Favor informar um valor numérico.");
                else
                	anoFabricacao = Integer.valueOf(anoFabricacaoString);
        	} while (anoFabricacao == 0);
        	
        	// Criar o veículo
        	Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
        	return veiculo;
        }
        
        private static void adicionarCliente(Seguradora seguradora, Cliente cliente) {
        	if (seguradora.cadastrarCliente(cliente))
				System.out.println("Cliente cadastrado com sucesso!");
			else
				System.out.println("Não foi possível cadastrar o cliente.");
        }
    }
}