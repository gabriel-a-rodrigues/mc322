import java.util.ArrayList;
import java.util.ArrayList;
import java.time.LocalDate;

public class Seguradora {
    private String nome;
    private String telefone;
    private String email;
    private String endereco;
    private ArrayList<Sinistro> listaSinistros;
    private ArrayList<Cliente> listaClientes;
    
    // Construtor
    public Seguradora(String nome, String telefone, String email, String endereco, ArrayList<Sinistro> listaSinistros, ArrayList<Cliente> listaClientes){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = listaSinistros;
        this.listaClientes = listaClientes;
    }
    
    public Seguradora(String nome, String telefone, String email, String endereco){
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.endereco = endereco;
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaClientes = new ArrayList<Cliente>();
    }
    
    public Seguradora() {
    	this.nome = "";
        this.telefone = "";
        this.email = "";
        this.endereco = "";
        this.listaSinistros = new ArrayList<Sinistro>();
        this.listaClientes = new ArrayList<Cliente>();
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
    
    public ArrayList<Sinistro> getListaSinistros(){
    	return listaSinistros;
    }
    
    public void setListaSinistros(ArrayList<Sinistro> listaSinistros){
    	this.listaSinistros = listaSinistros;
    }
    
    public ArrayList<Cliente> getListaClientes(){
    	return listaClientes;
    }
    
    public void setListaClientes(ArrayList<Cliente> listaClientes){
    	this.listaClientes = listaClientes;
    }
    
    // Clientes:
    public boolean cadastrarCliente(Cliente cliente){
    	// Insere o cliente em listaClientes.    	
    	return listaClientes.add(cliente);
    }
    
    public boolean removerCliente(String cliente){
    	// Remove de listaClientes todas as ocorrências do cliente com o nome especificado. Retorna false se não for encontrado.
    	boolean removido = false;
    	
    	for (int i = 0; i < listaClientes.size(); i++)
    		if (listaClientes.get(i).getNome().equals(cliente))
    			if (listaClientes.remove(listaClientes.get(i)))
    				removido = true;
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
    
    // Sinistros:
    public boolean gerarSinistro(LocalDate data, String endereco, Veiculo veiculo, Cliente cliente){
    	// Cria um sinistro e adiciona em listaSinistros.
    	Sinistro sinistro = new Sinistro(data, endereco, this, veiculo, cliente);
    	return listaSinistros.add(sinistro);
    }
    
    public boolean gerarSinistro(LocalDate data, String endereco){
    	// Cria um sinistro e adiciona em listaSinistros.
    	Sinistro sinistro = new Sinistro(data, endereco);
    	return listaSinistros.add(sinistro);
    }
    
    public boolean visualizarSinistro(String cliente){
    	// Imprime todos os sinistros com o nome especificado do cliente. Retorna false se não houver nenhum sinistro.
    	boolean clienteEncontrado = false;
    	
    	for (int i = 0; i < listaSinistros.size(); i++)
    		if (listaSinistros.get(i).getCliente().getNome().equals(cliente)){
    			System.out.println(listaSinistros.get(i));
    			clienteEncontrado = true;
    		}
    	
    	if (!clienteEncontrado)
    		System.out.println("Cliente " + cliente + " não tem nenhum sinistro registrado.");
    	
    	return clienteEncontrado;		    				
    }

    public String listarSinistros(){
    	if (listaSinistros.size() == 0)
    		return null;
    	
    	String listaString = "";
    	
    	for (int i = 0; i < listaSinistros.size(); i++)
    		listaString += listaSinistros.get(i) + "\n";
    	
    	return listaString;
    }
    
    public boolean removerSinistro(int id) {
    	for (Sinistro s : listaSinistros)
    		if (s.getId() == id)
    			return listaSinistros.remove(s);
    	return false;
    }
    
    public double calcularPrecoSeguroCliente(Cliente cliente){    	
    	return cliente.calcularScore() * (1 + contarSinistros(cliente, listaSinistros));
    }
    
    private static int contarSinistros(Cliente cliente, ArrayList<Sinistro> listaSinistros) {
    	int count = 0;
    	
    	for (Sinistro sinistro : listaSinistros)
    		if (sinistro.getCliente() == cliente)
    			count++;
    	
    	return count;
    }

    public double calcularReceita() {
    	double receita = 0.0;
    	String valores = "";
    	
    	for (Cliente c : listaClientes) {
    		c.setValorSeguro(calcularPrecoSeguroCliente(c));
    		valores += (c.getValorSeguro() + " + ");
    		receita += c.getValorSeguro();
    	}
    	
    	valores = valores.substring(0, valores.length() - 3);
    	valores += " = ";
    	System.out.println(valores);
    	return receita;
    }
    
    public String toString(){
        return (getNome() + " - " + getTelefone() + " - " + getEmail() + " - " + getEndereco());
    }
}