import java.util.ArrayList;
import java.util.ArrayList;
import java.time.LocalDate;

public class Seguradora {
	private final String cnpj;
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Cliente> listaClientes;
    private ArrayList<Seguro> listaSeguros;
    
    // Construtor
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco, ArrayList<Cliente> listaClientes, 
    		ArrayList<Seguro> listaSeguros){
        this.cnpj = cnpj;
    	this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = listaClientes;
        this.listaSeguros = listaSeguros;
    }
    
    public Seguradora(String cnpj, String nome, String telefone, String email, String endereco){
        this.cnpj = cnpj;
    	this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaClientes = new ArrayList<Cliente>();
        this.listaSeguros = new ArrayList<Seguro>();
    }
    
    public Seguradora() {
    	this.cnpj = this.nome = this.telefone = this.email = this.endereco = "";
        this.listaClientes = new ArrayList<Cliente>();
        this.listaSeguros = new ArrayList<Seguro>();
    }
    
    // Getters e setters
    public String getCNPJ() {
    	return cnpj;
    }
    
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getTelefone(){
        return telefone;
    }
    
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
    
    public String getEmail(){
        return email;
    }
    
    public void setEmail(String email){
        this.email = email;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
//    public ArrayList<Sinistro> getListaSinistros(){
//    	return listaSinistros;
//    }
//    
//    public void setListaSinistros(ArrayList<Sinistro> listaSinistros){
//    	this.listaSinistros = listaSinistros;
//    }
    
    public ArrayList<Cliente> getListaClientes(){
    	return listaClientes;
    }
    
    public void setListaClientes(ArrayList<Cliente> listaClientes){
    	this.listaClientes = listaClientes;
    }
    
    public ArrayList<Seguro> getListaSeguros() {
		return listaSeguros;
	}

	public void setListaSeguros(ArrayList<Seguro> listaSeguros) {
		this.listaSeguros = listaSeguros;
	}

	// Clientes:
    public boolean cadastrarCliente(Cliente cliente){
    	// Insere o cliente em listaClientes.    	
    	return listaClientes.add(cliente);
    }
    
    public boolean cadastrarCliente(ClientePF cliente){
    	// Insere o cliente em listaClientes.    	
    	return listaClientes.add(cliente);
    }
    
    public boolean cadastrarCliente(ClientePJ cliente){
    	// Insere o cliente em listaClientes.    	
    	return listaClientes.add(cliente);
    }
    
//    public boolean removerCliente(String nomeCliente){
//    	// Remove o cliente de listaClientes. Retorna true se o cliente não estiver na lista.
//    	boolean removido = true;
//    	
//    	for (Cliente cliente : getListaClientes())
//    		if (c.getNome().equals(nomeCliente))
//    			return listaClientes.remove(c);
//    	return removido;
//    }
    
    public boolean removerCliente(String cpfOuCNPJ){
    	// Remove o cliente de listaClientes. Retorna true se o cliente não estiver na lista.
    	if (!Validacao.validarCNPJ(cpfOuCNPJ) && Validacao.validarCPF(cpfOuCNPJ)) // CPF ou CNPJ inválido.
    		return false;
    	
    	boolean removido = true;
    	
    	for (Cliente cliente : getListaClientes())
    		if (cliente instanceof ClientePJ) {
    			ClientePJ clientePJ = (ClientePJ)cliente;
    			
    			if (clientePJ.getCNPJ().equals(cpfOuCNPJ))
    				return getListaClientes().remove(clientePJ);
    		} else if (cliente instanceof ClientePF) {
    			ClientePF clientePF = (ClientePF)cliente;
    			
    			if (clientePF.getCPF().equals(cpfOuCNPJ))
    				return getListaClientes().remove(clientePF);
    		}
    	return removido;
    }
    
    public String listarClientes(String tipoCliente){
    	if (listaClientes.size() == 0)
    		return "Não há clientes na lista.";
    	
    	String lista = "";
    	
    	for (int i = 0; i < listaClientes.size(); i++)
    		if (listaClientes.get(i) instanceof ClientePF && tipoCliente.equals("f"))
    			lista += listaClientes.get(i) + " - ";
    		else if (listaClientes.get(i) instanceof ClientePJ && tipoCliente.equals("j"))
    			lista += listaClientes.get(i) + " - ";
    	
    	if (lista.equals(""))
    		return "Não há clientes do tipo " + tipoCliente;
    	
    	return lista;
    }
    
    public Cliente getCliente(String nome){
    	if (getListaClientes().size() == 0)
    		return null;
    	
    	for (int i = 0; i < listaClientes.size(); i++)
    		if (listaClientes.get(i).getNome().equals(nome))
    			return listaClientes.get(i);
    	return null;
    }
    
    // Seguros:
    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, ArrayList<Sinistro> listaSinistros, 
			ArrayList<Condutor> listaCondutores, Veiculo veiculo, ClientePF cliente) {
    	// Cria um SeguroPF e adiciona à lista.
    	Seguro seguro = new SeguroPF(dataInicio, dataFim, this, listaSinistros, listaCondutores, veiculo, cliente);
    	return getListaSeguros().add(seguro);
    }
    
    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, ArrayList<Sinistro> listaSinistros, 
			ArrayList<Condutor> listaCondutores, Frota frota, ClientePJ cliente) {
    	// Cria um SeguroPJ e adiciona à lista.
    	Seguro seguro = new SeguroPJ(dataInicio, dataFim, this, listaSinistros, listaCondutores, frota, cliente);
    	return getListaSeguros().add(seguro);
    }
    
    public boolean cancelarSeguro(int id) {
    	// Remove o seguro especificado da lista de seguros. Retorna true se o seguro não estiver na lista.
    	for (Seguro seguro : getListaSeguros())
    		if (seguro.getID() == id)
    			return getListaSeguros().remove(seguro);
    	return true;
    }
    
    public Seguro getSeguro(int id) {
    	// Devolve o seguro com a id especificada. Se não houver seguro com a id, retorna null.
    	for (Seguro seguro : getListaSeguros())
    		if (seguro.getID() == id)
    			return seguro;
    	return null;
    }
    

//    
//    public boolean visualizarSinistro(String cliente){
//    	// Imprime todos os sinistros com o nome especificado do cliente. Retorna false se não houver nenhum sinistro.
//    	boolean clienteEncontrado = false;
//    	
//    	for (int i = 0; i < listaSinistros.size(); i++)
//    		if (listaSinistros.get(i).getCliente().getNome().equals(cliente)){
//    			System.out.println(listaSinistros.get(i));
//    			clienteEncontrado = true;
//    		}
//    	
//    	if (!clienteEncontrado)
//    		System.out.println("Cliente " + cliente + " não tem nenhum sinistro registrado.");
//    	
//    	return clienteEncontrado;		    				
//    }
//
//    public String listarSinistros(){
//    	if (listaSinistros.size() == 0)
//    		return null;
//    	
//    	String listaString = "";
//    	
//    	for (int i = 0; i < listaSinistros.size(); i++)
//    		listaString += listaSinistros.get(i) + "\n";
//    	
//    	return listaString;
//    }
//    
    public boolean removerSinistro(Sinistro sinistro) {
    	// Remove o sinistro do seguro em que está cadastrado. Retorna false se não encontrar o sinistro em nenhum seguro.
    	for (Seguro seguro : getListaSeguros())
    		for (Sinistro sinistro1 : seguro.getListaSinistros())
    			if (sinistro1.getID() == sinistro.getID())
    				return seguro.getListaSinistros().remove(sinistro);
    	return false;
    }
//    
//    public double calcularPrecoSeguroCliente(Cliente cliente){    	
//    	return cliente.calcularScore() * (1 + contarSinistros(cliente, listaSinistros));
//    }
//    
//    private static int contarSinistros(Cliente cliente, ArrayList<Sinistro> listaSinistros) {
//    	int count = 0;
//    	
//    	for (Sinistro sinistro : listaSinistros)
//    		if (sinistro.getCliente() == cliente)
//    			count++;
//    	
//    	return count;
//    }

    public double calcularReceita() {
    	int receita = 0;
    	String valores = "";
    	
    	for (Seguro s : getListaSeguros()) {
    		valores += (s.getValorMensal() + " + ");
    		receita += s.getValorMensal();
    	}
    	
    	valores = valores.substring(0, valores.length() - 3); // Remover o último " + "
    	valores += " = ";
    	System.out.println(valores);
    	return receita;
    }
    
    public ArrayList<Seguro> getSegurosPorCliente(String cpfOuCNPJ){
    	// Retorna uma lista de seguros associados ao cliente especificado. Retorna uma lista vazia se o CPF ou CNPJ for inválido.
    	ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();
    	
    	if (!Validacao.validarCPF(cpfOuCNPJ) && !Validacao.validarCNPJ(cpfOuCNPJ))
    		return segurosCliente;
    	
    	for (Seguro seguro : getListaSeguros()) {
    		if (seguro instanceof SeguroPF) {
    			SeguroPF seguroPF = (SeguroPF)seguro;
    			
    			if (seguroPF.getCliente().getCPF().equals(cpfOuCNPJ))
    				segurosCliente.add(seguroPF);    				
    		} else if (seguro instanceof SeguroPJ) {
    			SeguroPJ seguroPJ = (SeguroPJ)seguro;
    			
    			if (seguroPJ.getCliente().getCNPJ().equals(cpfOuCNPJ))
    				segurosCliente.add(seguroPJ);
    		}
    	}
    	
    	return segurosCliente;
    }
    
    public ArrayList<Sinistro> getSinistrosPorCliente(Cliente cliente){
    	// Retorna uma lista de com todos os sinistros associados aos seguros do cliente especificado.
    	ArrayList<Sinistro> sinistrosCliente = new ArrayList<Sinistro>();    	
    	
    	for (Seguro seguro : getListaSeguros())
    		if (seguro instanceof SeguroPF && ((SeguroPF) seguro).getCliente().equals(cliente))
    			for (Sinistro sinistro : seguro.getListaSinistros())
    				sinistrosCliente.add(sinistro);
    		else if (seguro instanceof SeguroPJ && ((SeguroPJ) seguro).getCliente().equals(cliente))
    			for (Sinistro sinistro : seguro.getListaSinistros())
    				sinistrosCliente.add(sinistro);
    	return sinistrosCliente;
    }
    
    public String toString(){
        return (getNome() + " - " + getTelefone() + " - " + getEmail() + " - " + getEndereco());
    }
}