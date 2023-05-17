import java.util.List;
import java.util.ArrayList;

public class Cliente{
    private String nome;
    private String endereco;
    private ArrayList<Veiculo> listaVeiculos;
    private double valorSeguro;

    // Construtor
    public Cliente(String nome, String endereco, ArrayList<Veiculo> listaVeiculos){
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = listaVeiculos;
        this.valorSeguro = 0.0;
    }
    
    public Cliente(String nome, String endereco){
        this.nome = nome;
        this.endereco = endereco;
        this.listaVeiculos = new ArrayList<Veiculo>();
        this.valorSeguro = 0.0;
    }
    
    public Cliente() {
    	this.nome = "";
    	this.endereco = "";
    	this.listaVeiculos = new ArrayList<Veiculo>();
    	this.valorSeguro = 0.0;
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
    
    public ArrayList<Veiculo> getListaVeiculos(){
    	return listaVeiculos;
    }
    
    public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos){
    	this.listaVeiculos = listaVeiculos;
    }
    
    public double getValorSeguro(){
    	return valorSeguro;
    }
    
    public void setValorSeguro(double valorSeguro){
    	this.valorSeguro = valorSeguro;
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
    
    public boolean adicionarVeiculo(Veiculo veiculo) {
    	return listaVeiculos.add(veiculo);
    }
    
    public boolean removerVeiculo(String placa) {
    	for (Veiculo v : listaVeiculos)
    		if (v.getPlaca().equals(placa))
    			return listaVeiculos.remove(v);
    	return false;
    	
    }
    
    double calcularScore(){
    	return 1.0;
    }
    
    public String toString(){
    	return (getNome() + " - " + getEndereco() + " - " + listarVeiculos());
    }
}