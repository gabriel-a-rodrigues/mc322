import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Condutor {
	private String cpf;
	private String nome;
    private String telefone;
    private String endereco;
    private String email;
	private LocalDate dataNascimento;
	private ArrayList<Sinistro> listaSinistros;
	
	public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNascimento, ArrayList<Sinistro> listaSinistros) {
		this.cpf = cpf.replaceAll("[^0-9]", "");;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.listaSinistros = listaSinistros;
	}
	
	public Condutor(String cpf, String nome, String telefone, String endereco, String email, LocalDate dataNascimento) {
		this.cpf = cpf.replaceAll("[^0-9]", "");;
		this.nome = nome;
		this.telefone = telefone;
		this.endereco = endereco;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.listaSinistros = new ArrayList<Sinistro>();
	}
	
	public Condutor() {
		this.cpf = this.nome = this.telefone = this.endereco = this.email = "";
		this.dataNascimento = LocalDate.parse("01/01/0001", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		this.listaSinistros = new ArrayList<Sinistro>();
	}
	
	public String getCPF() {
		return cpf;
	}
	
	public void setCPF(String cpf) {
		this.cpf = cpf;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getTelefone() {
		return telefone;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ArrayList<Sinistro> getListaSinistros() {
		return listaSinistros;
	}

	public void setListaSinistros(ArrayList<Sinistro> listaSinistros) {
		this.listaSinistros = listaSinistros;
	}
	
	public boolean adicionarSinistro(Sinistro sinistro) {
		return getListaSinistros().add(sinistro);
	}
	
	public boolean adicionarSinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro) {
		Sinistro sinistro = new Sinistro(data, endereco, condutor, seguro);
		return getListaSinistros().add(sinistro);
	}
	
	public String listarSinistros() {
		String listaSinistrosString = "";
		
		for (Sinistro sinistro : getListaSinistros())
			listaSinistrosString += sinistro + " - ";
		
		listaSinistrosString = listaSinistrosString.substring(0, listaSinistrosString.length() - 3); // Remover o último " - "
		return listaSinistrosString;
	}

	public String toString() {
		return (getNome() + " - CPF " + getCPF() + " - " + getTelefone() + " - " + getEndereco() + " - " + getEmail() + " - Nascimento " + getDataNascimento());
	}
}
