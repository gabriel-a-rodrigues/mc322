import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
    	Cliente cliente = new Cliente("Maria", "123.456.789-07", "11/01/1991", 32, "Rua 2 numero 3");
    	
    	List<Sinistro> listaSinistros = new ArrayList<Sinistro>(); // Declaring these lists feels so wrong. Find a better way.
    	List<Cliente> listaClientes = new ArrayList<Cliente>();
    	Seguradora seguradora = new Seguradora("Porto", "1191919191", "porto@p.com.br", "Avenida 2 434", listaSinistros, listaClientes);
    	
    	Veiculo veiculo = new Veiculo("AAA1A111", "FORD", "KA", 2013);
    	Sinistro sinistro = new Sinistro("12/03/2023", "ABC 123", seguradora, veiculo, cliente);
        
    	System.out.println(cliente.toString());
    	System.out.println(sinistro.toString());
    	System.out.println(veiculo.toString());
    	System.out.println(seguradora.toString());
    }
}
