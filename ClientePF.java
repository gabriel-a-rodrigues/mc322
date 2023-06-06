import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class ClientePF extends Cliente{
	private String cpf;
	private String genero;
//	private LocalDate dataLicenca;
	private String educacao;
	private LocalDate dataNascimento;
	private ArrayList<Veiculo> listaVeiculos;
//	private String classeEconomica;
	
	public ClientePF(String nome, String telefone, String endereco, String email, String cpf, 
			String genero, String educacao, LocalDate dataNascimento, ArrayList<Veiculo> listaVeiculos){
		super(nome, telefone, endereco, email);
		this.cpf = cpf;
		this.genero = genero;
//		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.listaVeiculos = listaVeiculos;
//		this.classeEconomica = classeEconomica;
	}
	
	public ClientePF() {
		super("", "", "", "");
		this.cpf = "";
		this.genero = "";
		this.educacao = "";
		this.dataNascimento = LocalDate.parse("01/01/0001", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	// Getters e setters
    public String getCPF(){
        return cpf;
    }
    
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
    
//    public LocalDate getDataLicenca(){
//        return dataLicenca;
//    }
//    
//    public void setDataLicenca(LocalDate dataLicenca){
//        this.dataLicenca = dataLicenca;
//    }
    
    public String getEducacao(){
        return educacao;
    }
    
    public void setEducacao(String educacao){
        this.educacao = educacao;
    }
    
    public LocalDate getDataNascimento(){
        return dataNascimento;
    }
    
    public void setDataNascimento(LocalDate dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
//    public String getClasseEconomica(){
//        return classeEconomica;
//    }
//    
//    public void setClasseEconomica(String classeEconomica){
//        this.classeEconomica = classeEconomica;
//    }
    
    public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public boolean cadastrarVeiculo(Veiculo veiculo) {
		// Adiciona veiculo a listaVeiculos. Retorna true se veiculo já estiver na lista.		
		if (veiculoNaLista(veiculo.getPlaca()) != -1)
			return true;
		// Adicionar à lista:
		return listaVeiculos.add(veiculo);		
	}
	
	public boolean cadastrarVeiculo(String placa, String marca, String modelo, int anoFabricacao) {
		// Cria um objeto Veiculo e o adiciona a listaVeiculos. Retorna true se o veículo já estiver na lista.		
		if (veiculoNaLista(placa) != -1)
			return true;
		// Criar objeto e adicionar à lista:
		Veiculo veiculo = new Veiculo(placa, marca, modelo, anoFabricacao);
		return listaVeiculos.add(veiculo);
	}
	
	public boolean removerVeiculo(Veiculo veiculo) {
		// Remove o veículo de listaVeiculos. Retorna false se o veículo não estiver na lista.
		int index = veiculoNaLista(veiculo.getPlaca());
		
		if (index == -1) // Veículo não está na lista.
			return false;
		
		if (listaVeiculos.remove(index).getPlaca().equals(veiculo.getPlaca()))
			return true;
		return false;
	}
	
	public boolean removerVeiculo(String placa) {
		// Remove o veículo de listaVeiculos. Retorna false se o veículo não estiver na lista.
		int index = veiculoNaLista(placa);
		
		if (index == -1) // Veículo não está na lista.
			return false;
		
		if (listaVeiculos.remove(index).getPlaca().equals(placa))
			return true;
		return false;
	}
	
	private int veiculoNaLista(String placa) {
		// Retorna o índice do veículo com a placa informada em listaVeiculos. Retorna -1 se o veículo não estiver na lista.		
		for (int i = 0; i < getListaVeiculos().size(); i++)
			if (getListaVeiculos().get(i).getPlaca().equals(placa)) // Se as placas são iguais
				return i;
		return -1;
	}
	
	public String listarVeiculos(){
    	// Retorna uma String com as informações de todos os veículos do cliente. Se o cliente não tiver veículo, devolve uma String vazia.
    	if (getListaVeiculos().size() == 0)
    		return "";
    	
    	String listaVeiculosString = "";
    	
    	for (Veiculo veiculo : getListaVeiculos())
    		listaVeiculosString += veiculo;
    	
    	listaVeiculosString = listaVeiculosString.substring(0, listaVeiculosString.length() - 3); // Remover o último " - "
    	
    	return listaVeiculosString;
    }

//	public double calcularScore() {
//    	int idade = idade();
//    	int quantidadeCarros = getListaVeiculos().toArray().length;
//    	
//    	if (idade < 30)
//    		return CalcSeguro.VALOR_BASE.valor() * CalcSeguro.FATOR_18_30.valor() * quantidadeCarros;
//    	else if (idade < 60)
//    		return CalcSeguro.VALOR_BASE.valor() * CalcSeguro.FATOR_30_60.valor() * quantidadeCarros;
//    	return CalcSeguro.VALOR_BASE.valor() * CalcSeguro.FATOR_60_.valor() * quantidadeCarros;    		
//    }
    
    public int idade() {
    	// Retorna a idade do cliente.
    	LocalDate currentDate = LocalDate.now();
    	Period period = Period.between(getDataNascimento(), currentDate);
    	return period.getYears();
    }
 
	@Override
	public String toString(){
		return (getNome() + " - " + getDataNascimento() + " - Gênero " + getGenero() + " - CPF " + getCPF()+ " - " + getEndereco() + 
				" - " + getTelefone() + " - " + getEmail() + " - Educação " + getEducacao());
	}
}