import java.util.Random;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Sinistro {
    private final int id;
    private LocalDate data;
    private String endereco;
    private Condutor condutor;
    private Seguro seguro;
//    private Seguradora seguradora;
//    private Veiculo veiculo;
//    private Cliente cliente;
    
    // Construtor
    public Sinistro(LocalDate data, String endereco, Condutor condutor, Seguro seguro){
        this.id = gerarID();
        this.data = data;
        this.endereco = endereco;
        this.condutor = condutor;
        this.seguro = seguro;
//        this.seguradora = seguradora;
//        this.veiculo = veiculo;
//        this.cliente = cliente;
    }
    
    public Sinistro(){
        this.id = -1;
        this.data = LocalDate.parse("01/01/0001", DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.endereco = "";
        this.condutor = new Condutor();
        this.seguro = new SeguroPF();
    }
    
//    public Sinistro(LocalDate data, String endereco){
//        this.id = gerarId();
//        this.data = data;
//        this.endereco = endereco;
//        this.condutor = new Condutor();
//        this.seguro = new Seguro();
//    }
    
    // Getters e setters
    public int getID(){
        return id;
    }
    
    public LocalDate getData(){
        return data;
    }
    
    public void setData(LocalDate data){
        this.data = data;
    }
    
    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }
    
//    public Seguradora getSeguradora(){
//        return seguradora;
//    }
//    
//    public void setSeguradora(Seguradora seguradora){
//        this.seguradora = seguradora;
//    }
//    
//    public Veiculo getVeiculo(){
//        return veiculo;
//    }
//    
//    public void setVeiculo(Veiculo veiculo){
//        this.veiculo = veiculo;
//    }
//    
//    public Cliente getCliente(){
//        return cliente;
//    }
//    
//    public void setCliente(Cliente cliente){
//        this.cliente = cliente;
//    }

    public Condutor getCondutor() {
		return condutor;
	}

	public void setCondutor(Condutor condutor) {
		this.condutor = condutor;
	}

	public Seguro getSeguro() {
		return seguro;
	}

	public void setSeguro(Seguro seguro) {
		this.seguro = seguro;
	}

	private int gerarID(){
        // Gera uma id pseudoaleatória.
    	Random random = new Random();
        return Math.abs(random.nextInt());
    }
    
    @Override
    public String toString(){
        return ("ID " + getID() + " - " + getData() + " - " + getEndereco() + " - Condutor " + getCondutor());
    }
}