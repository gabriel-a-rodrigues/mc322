import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Random;
import java.time.format.DateTimeFormatter;

public abstract class Seguro {
	private final int id;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private Seguradora seguradora;
	private ArrayList<Sinistro> listaSinistros;
	private ArrayList<Condutor> listaCondutores;
	private int valorMensal;
	
	public Seguro(LocalDate dataInicio, LocalDate dataFim, Seguradora seguradora, ArrayList<Sinistro> listaSinistros, 
			ArrayList<Condutor> listaCondutores) {
		this.id = gerarID();
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.seguradora = seguradora;
		this.listaSinistros = listaSinistros;
		this.listaCondutores = listaCondutores;
		this.valorMensal = calcularValor();
	}
	
	public Seguro() {
		this.id = -1;
		this.dataInicio = LocalDate.parse("01/01/0001", DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
		this.dataFim = LocalDate.parse("01/01/0001", DateTimeFormatter.ofPattern("dd/MM/yyyy"));;
		this.seguradora = new Seguradora();
		this.listaSinistros = new ArrayList<Sinistro>();
		this.listaCondutores = new ArrayList<Condutor>();
		this.valorMensal = -1;
	}
	
//	public Seguro() { 
//		this.id = gerarID();
//	}
	
	public int getID() {
		return id;
	}

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Seguradora getSeguradora() {
		return seguradora;
	}

	public void setSeguradora(Seguradora seguradora) {
		this.seguradora = seguradora;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}

	public ArrayList<Condutor> getListaCondutores() {
		return listaCondutores;
	}

	public void setListaCondutores(ArrayList<Condutor> listaCondutores) {
		this.listaCondutores = listaCondutores;
	}

	public int getValorMensal() {
		return valorMensal;
	}

	public void setValorMensal(int valorMensal) {
		this.valorMensal = valorMensal;
	}
	
	private int gerarID(){
        // Gera uma id pseudoaleatória.
    	Random random = new Random();
        return Math.abs(random.nextInt());
    }
	
	public boolean desautorizarCondutor(Condutor condutor) {
		// Remove o condutor da lista de condutores. Retorna true se ele já não estiver na lista.
		for (Condutor c : getListaCondutores())
			if (c.getCPF().equals(condutor.getCPF()))
				return getListaCondutores().remove(c);
		return true;
	}
	
	public boolean desautorizarCondutor(String cpf) {
		// Remove o condutor da lista de condutores. Retorna true se ele já não estiver na lista.
		for (Condutor c : getListaCondutores())
			if (c.getCPF().equals(cpf))
				return getListaCondutores().remove(c);
		return true;
	}
	
	public boolean autorizarCondutor(Condutor condutor) {
		// Adiciona o condutor à lista. Retorna true se o condutor já estiver na lista.		
		// Verificar se o condutor já está autorizado:
		for (Condutor c : getListaCondutores())
			if (c.getCPF().equals(condutor.getCPF()))
				return true;
		return getListaCondutores().add(condutor);
	}
	
	public int calcularValor() {
		return (int)CalcSeguro.VALOR_BASE.valor();
	}
	
	public Sinistro gerarSinistro(LocalDate data, String endereco, Condutor condutor) {
		// Cria um objeto Sinistro e o adiciona a listaSinistros. Se não conseguir adicionar à lista, retorna null.
		Sinistro sinistro = new Sinistro(data, endereco, condutor, this);
		if(getListaSinistros().add(sinistro))
			return sinistro;
		return null;
	}
	
	public String listarSinistros() {
		String listaSinistrosString = "";
		
		for (Sinistro sinistro : getListaSinistros())
			listaSinistrosString += sinistro + " - ";
		
		listaSinistrosString = listaSinistrosString.substring(0, listaSinistrosString.length() - 3); // Remover o último " - "
		return listaSinistrosString;
	}
	
	public String toString() {
		return ("ID " + getID() + " " + getDataInicio() + " a " + getDataFim() + " - Seguradora " + getSeguradora().getNome() + " - Valor mensal " + getValorMensal());
	}
}
