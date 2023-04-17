import java.util.List;

public class Cliente{
    private String nome;
    private String endereco;
    private List<Veiculo> listaVeiculos;

    // Construtor
    public Cliente(String nome, String endereco, List<Veiculo> listaVeiculos){
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
    }
    
    // Getters e setters
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
    public List<Veiculo> getListaVeiculos(){
    	return listaVeiculos;
    }
    
    public void setListaVeiculos(List<Veiculo> listaVeiculos){
    	this.listaVeiculos = listaVeiculos;
    }
    
    // Veículos
    public String listarVeiculos(){
    	if (listaVeiculos.size() == 0)
    		return "";
    	
    	String listaVeiculosString = "";
    	List<Veiculo> listaVeiculos = getListaVeiculos();
    	
    	for (int i = 0; i < listaVeiculos.size() - 1; i++)
    		listaVeiculosString += listaVeiculos.get(i) + " - ";
    	
    	listaVeiculosString += listaVeiculos.get(listaVeiculos.size() - 1);
    	
    	return listaVeiculosString;
    }
    
    public String toString(){
    	return ("Cliente: Nome: " + getNome() + " | Endereço: " + getEndereco() + " | Veículos: \n" + listarVeiculos()); // Lista veículos
    }
}