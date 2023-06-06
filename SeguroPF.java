import java.time.LocalDate;
import java.util.ArrayList;

public class SeguroPF extends Seguro {
	Veiculo veiculo;
	ClientePF cliente;
	
	public SeguroPF(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros, 
			ArrayList<Condutor> listaCondutores, Veiculo veiculo, ClientePF cliente) {
		super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
		this.veiculo = veiculo;
		this.cliente = cliente;
		this.setValorMensal(calcularValor());
	}
	
	public SeguroPF() {
		super();
		this.veiculo = new Veiculo();
		this.cliente = new ClientePF();
		this.setValorMensal(-1);
	}
	
	public Veiculo getVeiculo() {
		return veiculo;
	}
	
	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}
	
	public ClientePF getCliente() {
		return cliente;
	}
	
	public void setCliente(ClientePF cliente) {
		this.cliente = cliente;
	}
	
//	public boolean gerarSinistro()
	
	public int calcularValor() {
		double fatorIdade = 0;
		int idade = cliente.idade();
		int quantidadeVeiculos = cliente.getListaVeiculos().size();	
		int quantidadeSinistrosCliente = 0;
		int quantidadeSinistrosCondutor = 0;
		
		// Definir fatorIdade:
		if (idade < 30)
			fatorIdade = CalcSeguro.FATOR_18_30.valor();
		else if (idade < 60)
			fatorIdade = CalcSeguro.FATOR_30_60.valor();
		else
			fatorIdade = CalcSeguro.FATOR_60_.valor();
		
		// Definir quantidadeSinistrosCliente:
		for (Condutor c : getListaCondutores())
			if (c.getCPF().equals(cliente.getCPF()))
				quantidadeSinistrosCliente = c.getListaSinistros().size();
		
		// Definir quantidadeSinistrosCondutor
		for (Condutor c : getListaCondutores())
			quantidadeSinistrosCondutor += c.getListaSinistros().size();		
		
		return (int)(CalcSeguro.VALOR_BASE.valor() * fatorIdade * (1 + (1 / (double)(quantidadeVeiculos + 2)) * 
				(2 + (double)quantidadeSinistrosCliente / 10) * (5 + (double)quantidadeSinistrosCondutor / 10)));
	}
	
	public String toString() {
		return (super.toString() + " - " + getCliente());
	}
}
