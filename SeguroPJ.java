import java.time.LocalDate;
import java.util.ArrayList;

public class SeguroPJ extends Seguro {
	private Frota frota;
	private ClientePJ cliente;
	
	public SeguroPJ(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros, 
			ArrayList<Condutor> listaCondutores, Frota frota, ClientePJ cliente) {
		super(dataInicio, dataFim, seguradora, listaSinistros, listaCondutores);
		this.frota = frota;
		this.cliente = cliente;
		this.setValorMensal(calcularValor(cliente));
	}
	
	public Frota getFrota() {
		return frota;
	}
	
	public void setFrota(Frota frota) {
		this.frota = frota;
	}

	public ClientePJ getCliente() {
		return cliente;
	}

	public void setCliente(ClientePJ cliente) {
		this.cliente = cliente;
	}
	
	public int calcularValor(ClientePJ cliente) {
		int qtdeFuncionarios = cliente.getQtdeFuncionarios();
		int anosPosFundacao = cliente.idade();
		int quantidadeVeiculos = 0;
		int quantidadeSinistrosCliente = 0;
		int quantidadeSinistrosCondutor = 0;
		
		// Definir quantidadeVeiculos:
		for (Frota f : cliente.getListaFrotas())
			quantidadeVeiculos += f.getListaVeiculos().size();

		// Definir quantidadeSinistrosCliente:
		for (Seguro seguro : getSeguradora().getListaSeguros())
			if (seguro instanceof SeguroPJ) {
				SeguroPJ seguroPJ = (SeguroPJ)seguro;
				
				if (seguroPJ.getCliente().getCNPJ().equals(getCliente().getCNPJ()))
					quantidadeSinistrosCliente += seguroPJ.getListaSinistros().size();
			}
		
		// Definir quantidadeSinistrosCondutor:
		for (Condutor condutor : getListaCondutores())
			quantidadeSinistrosCondutor += condutor.getListaSinistros().size();
		
		return (int)(CalcSeguro.VALOR_BASE.valor() * (10 + (double)qtdeFuncionarios / 10) * 
				(1 + 1 / (double)(quantidadeVeiculos + 2)) * (1 + 1 / (double)(anosPosFundacao + 2)) * 
				(2 + (double)quantidadeSinistrosCliente / 10) * (5 + (double)quantidadeSinistrosCondutor / 10));
	}
	
	public String toString() {
		return (super.toString() + " - Frota " + frota + " - Cliente " + cliente);
	}
}
