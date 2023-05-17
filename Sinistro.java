import java.util.Random;
import java.time.LocalDate;

public class Sinistro {
    private int id;
    private LocalDate data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    
    // Construtor
    public Sinistro(LocalDate data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    public Sinistro(LocalDate data, String endereco){
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
    }
    
    // Getters e setters
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
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
    
    public Seguradora getSeguradora(){
        return seguradora;
    }
    
    public void setSeguradora(Seguradora seguradora){
        this.seguradora = seguradora;
    }
    
    public Veiculo getVeiculo(){
        return veiculo;
    }
    
    public void setVeiculo(Veiculo veiculo){
        this.veiculo = veiculo;
    }
    
    public Cliente getCliente(){
        return cliente;
    }
    
    public void setCliente(Cliente cliente){
        this.cliente = cliente;
    }

    public int gerarId(){
        // Gera uma id pseudoaleatória.
    	Random random = new Random();
        return Math.abs(random.nextInt());
    }
    
    @Override
    public String toString(){
        return ("ID " + getId() + " - " + getData() + " - " + getEndereco() + " - " + getVeiculo() + " - " + getCliente());
    }
}