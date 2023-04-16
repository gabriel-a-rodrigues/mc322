import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;
    private Seguradora seguradora;
    private Veiculo veiculo;
    private Cliente cliente;
    
    // Construtor
    public Sinistro(String data, String endereco, Seguradora seguradora, Veiculo veiculo, Cliente cliente){
        this.id = gerarId();
        this.data = data;
        this.endereco = endereco;
        this.seguradora = seguradora;
        this.veiculo = veiculo;
        this.cliente = cliente;
    }
    
    // Getters e setters
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getData(){
        return data;
    }
    
    public void setData(String data){
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
    	long seed = System.currentTimeMillis();
        return getRandom(seed);
    }

    private int getRandom(long seed){
        Random random = new Random();
        random.setSeed(seed);
        return random.nextInt();
    }

    public String toString(){
        return ("Sinistro: ID: " + getId() + " | Data: " + getData() + " | Endereço: " + getEndereco() + " | Veículo: " + veiculo.toString() + " | Cliente: " + cliente.getNome());
    }
}