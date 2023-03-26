import java.util.Random;

public class Sinistro {
    private int id;
    private String data;
    private String endereco;
    
    // Construtor
    public Sinistro(String data, String endereco){
        this.id = gerarId(data); // A data será usada como semente da função pseudoleatória.
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

    public int gerarId(String seedString){
        /* Gera uma id pseudoaleatória. Recebe uma string que será convertida para 
        long. */
    	seedString = seedString.replaceAll("[^0-9]", "");
        long seed = Long.valueOf(seedString).longValue();
        return getRandom(seed);
    }

    private int getRandom(long seed){
        Random random = new Random();
        random.setSeed(seed);
        return random.nextInt();
    }

    public String toString(){
        return ("Sinistro: ID: " + getId() + "; Data: " + getData() + "; Endereço: " + getEndereco());
    }
}