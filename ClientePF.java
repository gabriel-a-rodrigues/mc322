import java.util.Date;
import java.util.List;

/* To do
 * Atualizar toString()
 * */

public class ClientePF extends Cliente{
	private String cpf;
	private String genero;
	private Date dataLicenca;
	private String educacao;
	private Date dataNascimento;
	private String classeEconomica;
	
public ClientePF(String nome, String endereco, List<Veiculo> listaVeiculos, String cpf, String genero, Date dataLicenca, String educacao, Date dataNascimento, String classeEconomica){
		super(nome, endereco, listaVeiculos);
		this.cpf = cpf;
		this.genero = genero;
		this.dataLicenca = dataLicenca;
		this.educacao = educacao;
		this.dataNascimento = dataNascimento;
		this.classeEconomica = classeEconomica;
	}
	
	// Getters e setters
    public String getCPF(){
        return cpf;
    }
    
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    
    public String getGenero(){
        return genero;
    }
    
    public void setGenero(String genero){
        this.genero = genero;
    }
    
    public Date getDataLicenca(){
        return dataLicenca;
    }
    
    public void setDataLicenca(Date dataLicenca){
        this.dataLicenca = dataLicenca;
    }
    
    public String getEducacao(){
        return educacao;
    }
    
    public void setEducacao(String educacao){
        this.educacao = educacao;
    }
    
    public Date getDataNascimento(){
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public String getClasseEconomica(){
        return classeEconomica;
    }
    
    public void setClasseEconomica(String classeEconomica){
        this.classeEconomica = classeEconomica;
    }
 
	@Override
	public String toString(){
		return ("ClientePF: Nome: " + getNome() + "| Endereço: " + getEndereco() + "| CPF: " + getCPF() + 
				"| Gênero: " + getGenero() + "| Data da licença: " + getDataLicenca() + "| Educação: " + getEducacao() + 
				"| Data de nascimento: " + getDataNascimento() + "| Classe econômica: " + getClasseEconomica());
	}
	
	// CPF
    public boolean validarCPF(String cpf){
        // Remover caracteres não numéricos:
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF possui 11 dígitos:
        if (cpf.length()!= 11)
            return false;

        long cpfLong = Long.valueOf(cpf).longValue();

        // Verificar se todos os 11 digítos são iguais:
        if (cpfLong % 11111111111L == 0)
            return false;
        
        // Dígitos verificadores:
        int digitosVerificadores = digitosVerificadoresCPF(cpfLong);
        
        if ((int)(cpfLong % 100L) != digitosVerificadores)
            return false;

        return true;
    }

    private int digitosVerificadoresCPF(long cpf){
        int j = 0; // Primeiro dígito
        int k = 0; // Segundo dígito
        
        cpf -= cpf % 10;
        cpf -= cpf % 100;       

        for (long l = 10000000000L, multiplicador = 11L; l >= 100L; l /= 10L, multiplicador -= 1L){
            long i = (cpf - (cpf % l)) / l;
            j += (int)((multiplicador - 1) * i);
            k += (int)(multiplicador * i);
            cpf -= l * i;
        }
        
        k += 2 * j;
        
        if (j % 11 < 2)
            j = 0;
        else
            j = 11 - j % 11;

        if (k % 11 < 2)
            k = 0;
        else
            k = 11 - k % 11;

        return j * 10 + k;
    }
}