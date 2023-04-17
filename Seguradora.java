import java.util.List;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private List<Sinistro> listaSinistros;
    private List<Cliente> listaClientes;
    
    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco, List<Sinistro> listaSinistros, List<Cliente> listaClientes){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = listaSinistros;
        this.listaClientes = listaClientes;
    }
    
    // Getters e setters
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
    
    public List<Sinistro> getListaSinistros(){
    	return listaSinistros;
    }
    
    public void setListaSinistros(List<Sinistro> listaSinistros){
    	this.listaSinistros = listaSinistros;
    }
    
    public List<Cliente> getListaClientes(){
    	return listaClientes;
    }
    
    public void setListaClientes(List<Cliente> listaClientes){
    	this.listaClientes = listaClientes;
    }
    
    // Clientes:
    public boolean cadastrarCliente(Cliente cliente){
    	// Insere o cliente em listaClientes.    	
    	return listaClientes.add(cliente);
    }
    
    public boolean removerCliente(String cliente){
    	// Remove de listaClientes todas as ocorrências do cliente com o nome especificado. Retorna false se não for encontrado.
    	for (int i = 0; i < listaClientes.size(); i++)
    		if (listaClientes.get(i).getNome() == cliente)
    			return listaClientes.remove(listaClientes.get(i));
    	return false;
    }
    
    public String listarClientes(String tipoCliente){
    	if (listaClientes.size() == 0)
    		return "Não há clientes na lista.";
    	
    	String lista = "";
    	
    	for (int i = 0; i < listaClientes.size(); i++)
    		if (listaClientes.get(i).getClass() == ClientePF.class && tipoCliente == "PF")
    			lista += listaClientes.get(i).getNome() + " - ";
    		else if (listaClientes.get(i).getClass() == ClientePJ.class && tipoCliente == "PJ")
    			lista += listaClientes.get(i).getNome() + " - ";
    	
    	if (lista == "")
    		return "Não há clientes do tipo " + tipoCliente;
    	
    	return lista;
    }
    
    public Cliente getCliente(String nome){
    	if (getListaClientes().size() == 0)
    		return null;
    	
    	List<Cliente> listaClientes = getListaClientes();
    	
    	for (int i = 0; i < listaClientes.size(); i++){
    		if (listaClientes.get(i).getNome() == nome)
    			return listaClientes.get(i);
    	}
    	
    	return null;
    }
    
    // Sinistros:
    public boolean gerarSinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
    	// Cria um sinistro e adiciona em listaSinistros.
    	Sinistro sinistro = new Sinistro(data, endereco, seguradora, veiculo, cliente);
    	return listaSinistros.add(sinistro);
    }
    
    public boolean visualizarSinistro(String cliente){
    	// Imprime todos os sinistros com o nome especificado do cliente. Retorna false se não houver nenhum sinistro.
    	boolean clienteEncontrado = false;
    	
    	for (int i = 0; i < listaSinistros.size(); i++)
    		if (listaSinistros.get(i).getCliente().getNome() == cliente){
    			listaSinistros.get(i).toString();
    			clienteEncontrado = true;
    		}
    	
    	return clienteEncontrado;		    				
    }

    public String listarSinistros(){
    	if (listaSinistros.size() == 0)
    		return null;
    	
    	String listaString = "";
    	
    	for (int i = 0; i < listaSinistros.size(); i++)
    		listaString += listaSinistros.get(i).toString() + "\n";
    	
    	return listaString;
    }
    
    public String toString(){
        return ("Seguradora: Nome: " + getNome() + " | Telefone: " + getTelefone() + " | Email: " + getEmail() + " | Endereço: " + getEndereco());
    }
}