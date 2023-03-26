public class Cliente{
    private String nome;
    private String cpf;
    private String dataNascimento;
    private int idade;
    private String endereco;

    // Construtor
    public Cliente(String nome, String cpf, String dataNascimento, int idade, String endereco){
        this.nome = nome;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.idade = idade;
        this.endereco = endereco;
    }
    
    // Getters e setters
    public String getNome(){
        return nome;
    }
    
    public void setNome(String nome){
        this.nome = nome;
    }
    
    public String getCPF(){
        return cpf;
    }
    
    public void setCPF(String cpf){
        this.cpf = cpf;
    }
    
    public String getDataNascimento(){
        return dataNascimento;
    }
    
    public void setDataNascimento(String dataNascimento){
        this.dataNascimento = dataNascimento;
    }
    
    public int getIdade(){
        return idade;
    }
    
    public void setIdade(int idade){
        this.idade = idade;
    }

    public String getEndereco(){
        return endereco;
    }
    
    public void setEndereco(String endereco){
        this.endereco = endereco;
    }

    // CPF
    public boolean validarCPF(){
        // Remover caracteres não numéricos:
    	String cpf = getCPF();
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

    public String toString(){
    	if (validarCPF())
    		return ("Cliente: Nome: " + getNome() + "; CPF: " + getCPF() + " (válido); Nascimento: " + getDataNascimento() + "; Idade: " + getIdade());
    	return ("Cliente: Nome: " + getNome() + "; CPF: " + getCPF() + " (atenção: inválido); Nascimento: " + getDataNascimento() + "; Idade: " + getIdade());
    }
}