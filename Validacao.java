public class Validacao {
	// CPF
    public static boolean validarCPF(String cpf){
    	// Retorna true se o CPF for válido e false caso contrário.
        // Remover caracteres não numéricos:
        cpf = cpf.replaceAll("[^0-9]", "");

        // Verificar se o CPF possui 11 dígitos:
        if (cpf.length()!= 11)
            return false;
        
        // Verificar se todos os dígitos são iguais:
        boolean diferente = false;
        
        for (int i = 0; i < 10; i++)
        	if (cpf.charAt(i) != cpf.charAt(i) + 1) {
        		diferente = true;
        		break;
        	}
        
        if (!diferente)
        	return false;
        
        // Dígitos verificadores:
        int digitosVerificadores = digitosVerificadoresCPF(cpf);
        int digitosCPF = 10 * (cpf.charAt(9) - '0') + (cpf.charAt(10) - '0');
        
        if  (digitosCPF != digitosVerificadores)
            return false;

        return true;
    }

    private static int digitosVerificadoresCPF(String cpf){
    	// Retorna os dígitos verificadores do CPF na forma XY (isto é, um número < 100).
        int j = 0; // Primeiro dígito
        int k = 0; // Segundo dígito         
        
        for (int i = 0; i < 9; i++) {
        	int algarismo = cpf.charAt(i) - '0';
        	
        	j += (i + 1) * algarismo;
        	k += i * algarismo;
        }
        
        if (j % 11 == 10)
        	j = 0;
        else 
        	j %= 11;
        
        k += 9 * j;
        
        if (k % 11 == 10)
        	k = 0;
        else
        	k %= 11;
        
        return j * 10 + k;
    }
    
    // CNPJ
    public static boolean validarCNPJ(String cnpj){    
    	// Retorna true se o CNPJ for válido e false caso contrário.
        // Remover caracteres não numéricos:
        cnpj = cnpj.replaceAll("[^0-9]", "");

        // Verificar se o CNPJ possui 14 dígitos:
        if (cnpj.length()!= 14)
            return false;

        // Verificar se todos os dígitos são iguais:
        boolean diferente = false;
        
        for (int i = 0; i < 10; i++)
        	if (cnpj.charAt(i) != cnpj.charAt(i) + 1) {
        		diferente = true;
        		break;
        	}
        
        if (!diferente)
        	return false;
        
        // Dígitos verificadores:
        int digitosVerificadores = digitosVerificadoresCNPJ(cnpj);
        int digitosCNPJ = 10 * (cnpj.charAt(12) - '0') + (cnpj.charAt(13) - '0');
        
        if (digitosCNPJ != digitosVerificadores)
            return false;

        return true;
    }

    private static int digitosVerificadoresCNPJ(String cnpj){
    	// Retorna os dígitos verificadores do CNPJ na forma XY (isto é, um número < 100).
        int j = 0; // Primeiro dígito
        int k = 0; // Segundo dígito
        
        for (int i = 0; i < 4; i++) {
        	int algarismo = cnpj.charAt(i) - '0';       	
        	
        	j += (i + 6) * algarismo;
        	k += (i + 5) * algarismo;
        }   	
    	
    	j += 2 * (cnpj.charAt(4) - '0');
    	k += 9 * (cnpj.charAt(4) - '0');
    	
    	for (int i = 5; i < 12; i++) {
        	int algarismo = cnpj.charAt(i) - '0';       	
        	
        	j += (i - 2) * algarismo;
        	k += (i - 3) * algarismo;
        }   	
        
        if (j % 11 == 10)
        	j = 0;
        else 
        	j %= 11;
        
        k += 9 * j;
        
        if (k % 11 == 10)
        	k = 0;
        else
        	k %= 11;
        
        return j * 10 + k;
    }

    // Nome
    public static boolean validarNome(String nome) {
    	// Verifica se o nome contém somente letras e espaços em branco.
    	return nome.replaceAll("[^a-zA-Z\\s]", "").isEmpty();
    }
}