import java.time.LocalDate;
import java.util.ArrayList;
import java.time.Period;

/* To do
 * Atualizar toString()
 * */

public class ClientePJ extends Cliente{
	private String cnpj;
	private LocalDate dataFundacao;
	private ArrayList<Frota> listaFrotas;
	private int qtdeFuncionarios;
	
	public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, LocalDate dataFundacao, ArrayList<Frota> listaFrotas, int qtdeFuncionarios){
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj.replaceAll("[^0-9]", "");;
		this.dataFundacao = dataFundacao;
		this.listaFrotas = listaFrotas;
		this.qtdeFuncionarios = qtdeFuncionarios;
	}
	
	public ClientePJ(String nome, String telefone, String endereco, String email, String cnpj, LocalDate dataFundacao, int qtdeFuncionarios){
		super(nome, telefone, endereco, email);
		this.cnpj = cnpj.replaceAll("[^0-9]", "");;
		this.dataFundacao = dataFundacao;
		this.listaFrotas = new ArrayList<Frota>();
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
    
    public ArrayList<Frota> getListaFrotas() {
		return listaFrotas;
	}

	public void setListaFrotas(ArrayList<Frota> listaFrotas) {
		this.listaFrotas = listaFrotas;
	}
	
	public int getQtdeFuncionarios(){
        return qtdeFuncionarios;
    }
    
    public void setQtdeFuncionarios(int qtdeFuncionarios){
        this.qtdeFuncionarios = qtdeFuncionarios;
    }
	
	public boolean cadastrarFrota(Frota frota) {
		// Adiciona frota à lista de frotas. Retorna false se a frota já estiver cadastrada.
		
		for (Frota frota1 : getListaFrotas())
			if (frota1.getCode().equals(frota.getCode())) // Frota já está cadastrada.
				return false;
		
		return getListaFrotas().add(frota);
	}
	
	public boolean atualizarFrota(Veiculo veiculo, String code) {
		// Se veiculo não estiver na frota, inclui. Se estiver, remove. Retorna false se não houver frota com o code fornecido.
		Frota frota = new Frota();
		
		for (Frota f : getListaFrotas())
			if (f.getCode().equals(code))
				frota = f;
		
		if (frota.getCode().equals("")) // Não há frota com o code fornecido.
			return false;
		
		// Verificar se veiculo está na frota e remover se estiver:
		for (Veiculo v : frota.getListaVeiculos())
			if (v.getPlaca().equals(veiculo.getPlaca()))
					return frota.getListaVeiculos().remove(veiculo);
		// Se não encontrou, adicionar à lista:
		return frota.getListaVeiculos().add(veiculo);
	}
	
	public boolean atualizarFrota(String placa) {
		// Remove o veículo da frota onde ele está cadastrado. Retorna false se o veículo não foi encontrado na frota.
		for (Frota frota : getListaFrotas())
			for (Veiculo veiculo : frota.getListaVeiculos())
				if (veiculo.getPlaca().equals(placa))
					return frota.removerVeiculo(veiculo);
		return false;
	}
	
	public boolean atualizarFrota(Frota frota) {
		// Remove a frota de listaFrotas. Retorna false se listaFrotas já estiver vazia.		
		if (getListaFrotas().size() == 0)
			return false;		
		return(getListaFrotas().remove(frota));
	}
	
	public int idade() {
    	LocalDate currentDate = LocalDate.now();
    	Period period = Period.between(getDataFundacao(), currentDate);
    	return period.getYears();
    }
	
	public boolean getVeiculosPorFrota(Frota frota) { // ?????
		return true;
	}
    
//    public double calcularScore() {
//    	int quantidadeCarros = getListaVeiculos().toArray().length;
//    	
//    	return CalcSeguro.VALOR_BASE.valor() * (1 + (qtdeFuncionarios / 100)) * quantidadeCarros;
//    }
 
	@Override
	public String toString(){
		return (getNome() + " - " + getTelefone() + " - " + getEndereco() + " - " + getEmail() + " - CNPJ " + getCNPJ() + " - Fundado em " + getDataFundacao());
	}
}