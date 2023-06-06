public enum CalcSeguro {
	VALOR_BASE	(10.0),
	FATOR_18_30	(1.2),
	FATOR_30_60	(1.0),
	FATOR_60_	(1.5);
	
	private final double valor;
	
	CalcSeguro(double valor){
		this.valor = valor;
	}
	
	public double valor() {
		return valor;
	}
}