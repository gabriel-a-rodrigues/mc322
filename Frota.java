import java.util.ArrayList;

public class Frota {
	private String code;
	private ArrayList<Veiculo> listaVeiculos;
	
	public Frota(String code, ArrayList<Veiculo> listaVeiculos) {
		this.code = code;
		this.listaVeiculos = listaVeiculos;
	}
	
	public Frota(String code) {
		this.code = code;
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	public Frota() {
		this.code = "";
		this.listaVeiculos = new ArrayList<Veiculo>();
	}
	
	public String getCode() {
		return code;
	}
	 
	public void setCode(String code) {
		this.code = code;
	}

	public ArrayList<Veiculo> getListaVeiculos() {
		return listaVeiculos;
	}

	public void setListaVeiculos(ArrayList<Veiculo> listaVeiculos) {
		this.listaVeiculos = listaVeiculos;
	}
	
	public boolean addVeiculo(Veiculo veiculo) {
		// Adiciona veiculo à lista de veículos. Se já estiver na lista, retorna true.
		
		// Verificar se já está na lista:
		for (Veiculo v : getListaVeiculos())
			if (v.getPlaca().equals(veiculo.getPlaca()))
				return true;
		return getListaVeiculos().add(veiculo);
	}
	
	public boolean removerVeiculo(Veiculo veiculo) {
		// Remove veiculo da lista. Retorna true se não estiver na lista.
		
		for (Veiculo v : getListaVeiculos())
			if (v.getPlaca().equals(veiculo.getPlaca()))
				return getListaVeiculos().remove(v);
		return true;
	}
	
	public boolean removerVeiculo(String placa) {
		// Remove veiculo da lista. Retorna true se não estiver na lista.
		
		for (Veiculo v : getListaVeiculos())
			if (v.getPlaca().equals(placa))
				return getListaVeiculos().remove(v);
		return true;
	}
	
	private String listaVeiculosString() {
		// Formata listaVeiculos numa string.
		String lista = "";
		
		for (Veiculo v : getListaVeiculos())
			lista += v + " - ";
		
		// Tirar o último " - "
		lista = lista.substring(0, lista.length() - 3);
		
		return lista;
	}
	
	public String toString() {
		return (getCode() + " - " + listaVeiculosString());
	}
}
