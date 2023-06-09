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
    
    // Construtores
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
    
    public boolean removerCliente(Cliente cliente){
    	// Remove o cliente de listaClientes. Retorna true se o cliente não estiver na lista.
    	if (cliente instanceof ClientePF)
    		return removerCliente(((ClientePF)cliente).getCPF());
    	else if (cliente instanceof ClientePJ)
    		return removerCliente(((ClientePJ)cliente).getCNPJ());
    	return false;
    }
    
    public String listarClientes(){
    	String lista = "";
    	
    	for (Cliente cliente : getListaClientes())
    		lista += cliente + "\n";    	
    	
    	if (lista.equals(""))
    		return "A seguradora ainda não tem clientes cadastrados.";
    	
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
    // Gerar seguro PF sem sinistros:
    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, ArrayList<Condutor> listaCondutores, 
    		Veiculo veiculo, ClientePF cliente) {
    	// Cria um SeguroPF e adiciona à lista.
    	ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    	Seguro seguro = new SeguroPF(dataInicio, dataFim, this, listaSinistros, listaCondutores, veiculo, cliente);
    	return getListaSeguros().add(seguro);
    }
    
    // Gerar seguro PF com sinistros:
    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, ArrayList<Sinistro> listaSinistros, 
			ArrayList<Condutor> listaCondutores, Veiculo veiculo, ClientePF cliente) {
    	// Cria um SeguroPF e adiciona à lista.
    	Seguro seguro = new SeguroPF(dataInicio, dataFim, this, listaSinistros, listaCondutores, veiculo, cliente);
    	return getListaSeguros().add(seguro);
    }
    
    // Gerar seguro PJ sem sinistros:
    public boolean gerarSeguro(LocalDate dataInicio, LocalDate dataFim, ArrayList<Condutor> listaCondutores, 
    		Frota frota, ClientePJ cliente) {
    	// Cria um SeguroPJ e adiciona à lista.
    	ArrayList<Sinistro> listaSinistros = new ArrayList<Sinistro>();
    	Seguro seguro = new SeguroPJ(dataInicio, dataFim, this, listaSinistros, listaCondutores, frota, cliente);
    	return getListaSeguros().add(seguro);
    }
    
    // Gerar seguro PJ com sinistros:
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
    
    public boolean removerSinistro(Sinistro sinistro) {
    	// Remove o sinistro do seguro em que está cadastrado. Retorna false se não encontrar o sinistro em nenhum seguro.
    	for (Seguro seguro : getListaSeguros())
    		for (Sinistro sinistro1 : seguro.getListaSinistros())
    			if (sinistro1.getID() == sinistro.getID())
    				return seguro.getListaSinistros().remove(sinistro);
    	return false;
    }

    public double calcularReceita() {
    	int receita = 0;
    	String valores = "";
    	
    	for (Seguro seguro : getListaSeguros()) {
    		valores += (seguro.getValorMensal() + " + ");
    		receita += seguro.getValorMensal();
    	}
    	
    	if (valores.length() >= 3) {
    		valores = valores.substring(0, valores.length() - 3); // Remover o último " + "
        	valores += " = ";
    	}

    	return receita;
    }
    
    public ArrayList<Seguro> getSegurosPorCliente(Cliente cliente){
    	// Retorna uma lista de seguros associados ao cliente especificado. Retorna uma lista vazia se o CPF ou CNPJ for inválido.
    	ArrayList<Seguro> segurosCliente = new ArrayList<Seguro>();

    	for (Seguro seguro : getListaSeguros()) {
    		if (seguro instanceof SeguroPF) {
    			SeguroPF seguroPF = (SeguroPF)seguro;
    			
    			if (seguroPF.getCliente().equals(cliente))
    				segurosCliente.add(seguroPF);    				
    		} else if (seguro instanceof SeguroPJ) {
    			SeguroPJ seguroPJ = (SeguroPJ)seguro;
    			
    			if (seguroPJ.getCliente().equals(cliente))
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
        return (getNome() + " - " + getTelefone() + " - " + getEmail() + " - " + getEndereco() + " - Receita " + calcularReceita());
    }
}