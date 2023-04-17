import java.util.Date;
import java.util.List;

/* To do
 * Atualizar toString()
 * */

public class ClientePJ extends Cliente{
	private String cnpj;
	private Date dataFundacao;
	
	public ClientePJ(String nome, String endereco, List<Veiculo> listaVeiculos, String cnpj, Date dataFundacao){
		super(nome, endereco, listaVeiculos);
		this.cnpj = cnpj;
		this.dataFundacao = dataFundacao;
	}
	
	// Getters e setters
    public String getCNPJ(){
        return cnpj;
    }
    
    public void setCNPJ(String cnpj){
        this.cnpj = cnpj;
    }
    
    public Date getDataFundacao(){
        return dataFundacao;
    }
    
    public void setDataFundacao(Date dataFundacao){
        this.dataFundacao = dataFundacao;
    }
 
	@Override
	public String toString(){
		return ("ClientePJ: Nome: " + getNome() + "| Endereço: " + getEndereco() + "| CNPJ: " + getCNPJ() + "| Data de fundação: " + getDataFundacao());
	}
	
	// CNPJ
    public boolean validarCNPJ(String cnpj){    	
        // Remover caracteres não numéricos:
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verificar se o CNPJ possui 12 dígitos:
        if (cnpj.length()!= 12)
            return false;

        long cnpjLong = Long.valueOf(cnpj).longValue();

        // Verificar se todos os 12 digítos são iguais:
        if (cnpjLong % 111111111111L == 0)
            return false;
        
        // Dígitos verificadores:
        int digitosVerificadores = digitosVerificadoresCNPJ(cnpjLong);
        
        if ((int)(cnpjLong % 100L) != digitosVerificadores)
            return false;

        return true;
    }

    private int digitosVerificadoresCNPJ(long cnpj){
        int j = 0; // Primeiro dígito
        int k = 0; // Segundo dígito
        
        cnpj -= cnpj % 10;
        cnpj -= cnpj % 100;       

        for (long l = 100000000000L, mult1 = 6L, mult2 = 5L; l >= 100L; l /= 10L, mult1 += 1L, mult2 += 1L){
            long i = (cnpj - (cnpj % l)) / l;
            j += (int)(mult1 * i);
            k += (int)(mult2 * i);
            cnpj -= l * i;
            
            if (mult1 == 9L)
            	mult1 = 1L;
            
            if (mult2 == 9L)
            	mult2 = 1L;
        }
        
        j %= 11;
        k = (k +(9 * j)) / 11;
        
        return j * 10 + k;
    }
}