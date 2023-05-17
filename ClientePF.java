import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Period;

/* To do
 * Atualizar toString()
 * */

public class ClientePF extends Cliente{
	private String cpf;
	private String genero;
	private LocalDate dataLicenca;
	private String educacao;
	private LocalDate dataNascimento;
	private String classeEconomica;
	
	public ClientePF(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cpf, String genero, LocalDate dataLicenca, String educacao, LocalDate dataNascimento, String classeEconomica){
		super(nome, endereco, listaVeiculos);
		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
	}
	
	public ClientePF(String nome, String endereco, String cpf, String genero, LocalDate dataLicenca, String educacao, LocalDate dataNascimento, String classeEconomica){
		super(nome, endereco);
		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
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
    
    public LocalDate getDataLicenca(){
        return dataLicenca;
    }
    
    public void setDataLicenca(LocalDate dataLicenca){
        this.dataLicenca = dataLicenca;
    }
    
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
    
    public String getClasseEconomica(){
        return classeEconomica;
    }
    
    public void setClasseEconomica(String classeEconomica){
        this.classeEconomica = classeEconomica;
    }
    
    public double calcularScore() {
    	int idade = idade();
    	int quantidadeCarros = getListaVeiculos().toArray().length;
    	
    	if (idade < 30)
    		return CalcSeguro.VALOR_BASE.valor() * CalcSeguro.FATOR_18_30.valor() * quantidadeCarros;
    	else if (idade < 60)
    		return CalcSeguro.VALOR_BASE.valor() * CalcSeguro.FATOR_30_60.valor() * quantidadeCarros;
    	return CalcSeguro.VALOR_BASE.valor() * CalcSeguro.FATOR_60_90.valor() * quantidadeCarros;    		
    }
    
    private int idade() {
    	LocalDate currentDate = LocalDate.now();
    	Period period = Period.between(dataNascimento, currentDate);
    	return period.getYears();
    }
 
	@Override
	public String toString(){
		return (getNome() + " - " + getDataNascimento() + " - Gênero " + getGenero() + " - CPF " + getCPF()+ " - " + getEndereco() + 
				" - Liçenca " + getDataLicenca() + " - Educação " + getEducacao() + " - Classe " + getClasseEconomica());
	}
}