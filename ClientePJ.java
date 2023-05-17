import java.time.LocalDate;
import java.util.ArrayList;

/* To do
 * Atualizar toString()
 * */

public class ClientePJ extends Cliente{
	private String cnpj;
	private LocalDate dataFundacao;
	private int qtdeFuncionarios;
	
	public ClientePJ(String nome, String endereco, ArrayList<Veiculo> listaVeiculos, String cnpj, LocalDate dataFundacao, int qtdeFuncionarios){
		super(nome, endereco, listaVeiculos);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	public ClientePJ(String nome, String endereco, String cnpj, LocalDate dataFundacao, int qtdeFuncionarios){
		super(nome, endereco);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	// Getters e setters
    public String getCNPJ(){
        return cnpj;
    }
    
    public void setCNPJ(String cnpj){
        this.cnpj = cnpj;
    }
    
    public LocalDate getDataFundacao(){
        return dataFundacao;
    }
    
    public void setDataFundacao(LocalDate dataFundacao){
        this.dataFundacao = dataFundacao;
    }
    
    public int getQtdeFuncionarios(){
        return qtdeFuncionarios;
    }
    
    public void setQtdeFuncionarios(int qtdeFuncionarios){
        this.qtdeFuncionarios = qtdeFuncionarios;
    }
    
    public double calcularScore() {
    	int quantidadeCarros = getListaVeiculos().toArray().length;
    	
    	return CalcSeguro.VALOR_BASE.valor() * (1 + (qtdeFuncionarios / 100)) * quantidadeCarros;
    }
 
	@Override
	public String toString(){
		return (getNome() + " - CNPJ " + getCNPJ() + " - Fundado em " + getDataFundacao() + " - " + getQtdeFuncionarios() + " funcionários - " + getEndereco());
	}
}