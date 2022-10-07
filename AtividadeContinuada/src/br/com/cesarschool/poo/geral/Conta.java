package br.com.cesarschool.poo.geral;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Conta {
	private long codigo;   // Indentificar a conta
	private int status; // 1- Ativa, 2- Encerrada 3- Bloqueada
	private double saldo = 0; 
	private LocalDate dataAbertura;
	private LocalDate dataAtual = LocalDate.now();
	
	public Conta(long codigo, int status, double saldo, LocalDate dataAbertura) {
		this.codigo = codigo;
		this.status = status;
		this.saldo = saldo;
		this.dataAbertura = dataAbertura;
	}

	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public double getSaldo() {
		return saldo;
	}
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}
	public LocalDate getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	/**
	 * O conceito de c�digo v�lido � codigo > 0
	 * 
	 * @return true se o c�digo for v�lido e false caso contr�rio.
	 */
	boolean codigoValido() {
		if (this.codigo <= 0) {
			return false;
		}
		return true;
	}
	
	
	public void Creditar(double credito) {
		
		if(status != 2) {
			if(credito >= 0) {
				saldo += credito;
				System.out.printf("Novo saldo: %d\n", saldo);
				
			}
			else {
				System.out.println("Valor inválido!");
			}
		}
		else {
			System.out.println("Não é possível realizar a operação.\n Motivo: Conta encerrada!");
		}
	}
	
	public void Debitar(double debito) {
		
		if(status != 3) {      //perguntar ao professor se conta encerrada também
			if(debito >= 0) {
				saldo -= debito;
				System.out.printf("Novo saldo: %d\n", saldo);
				
			}
			else {
				System.out.println("Valor inválido!");
			}
		}
		else {
			System.out.println("Não é possível realizar a operação.\n Motivo: Conta bloqueada!\n");
		}
	}
	
	public int CalcularEscoreDaConta() {
		// Escore : 1- Bronze, 2-Prata, 3-Ouro, 4-Diamante 0-Inativo
		if(status == 1) {
			double F;
			long difData;
			difData = dataAbertura.until(dataAtual, ChronoUnit.DAYS); // calcular o tempo de aberturada da conta
			F = (saldo*3) + (difData*2);
			
			if(F <= 5800) {
				return 1;
			}
			else if(F >= 5801 && F <= 13000) {
				return 2;
			}
			else if(F >= 13001 && F <= 39000) {
				return 3;
			}
			else{
				return 4;
			}
		} 
		else if(status == 2){
			return 0;
		}
		else{
			System.out.println("O escore não pode ser calculado!\n Motivo: Conta Bloqueada!\n");
			return -1;
		}
	}
}


/**
 * M�todo respons�vel por valida o atributo nome
 * 
 * O nome � obrigat�rio. 
 * 
 * @return Caso o nome n�o esteja preenchido, retorna NOME_NAO_INFORMADO.
 *         Caso o nome tenha menos do que TAMANHO_MINIMO_NOME caracteres,
 *         retorna NOME_MUITO_CURTO. Caso contr�rio, retorna SUCESSO. 
 
int validarNome() {
	if (nome == null || nome.trim().equals("")) {
		return NOME_NAO_INFORMADO;
	} else if (nome.trim().length() < TAMANHO_MINIMO_NOME) {
		return NOME_MUITO_CURTO;
	}
	return SUCESSO;
}
boolean precoValido() {
	return this.saldo > 0;
}
boolean tipoPreechido() {
	return dataAbertura != null;
}
*/
