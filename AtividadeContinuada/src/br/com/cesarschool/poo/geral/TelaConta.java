package br.com.cesarschool.poo.geral;

import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

/**
 * @author An�nimo
 *
 * Trata-se de uma implementa��o de tela prim�ria, para efeito did�tico e de demonstra��o simplificada
 * da arquitetura em camadas e do custo de implementa��o de uma interface visual, por mais simples que
 * seja!
 * 
 */

public class TelaConta {
	
	private static final int CODIGO_DESCONHECIDO = -1;
	private static final Scanner ENTRADA = new Scanner(System.in);
	private RepositorioConta repositorioConta = new RepositorioConta();
	
	public void executaTela() {
		while(true) {
			long codigo = CODIGO_DESCONHECIDO;
			imprimeMenuPrincipal();
			int opcao = ENTRADA.nextInt();
			if (opcao == 1) {
				processaInclusao();
			} else if (opcao == 2) {
				codigo = processaBusca();
				if (codigo != CODIGO_DESCONHECIDO) {
					processaAlteracao(codigo);
				} 
			} else if (opcao == 3) {
				codigo = processaBusca();
				if (codigo != CODIGO_DESCONHECIDO) {
					processaExclusao(codigo);
				}			
			} else if (opcao == 4) {
				processaBusca();
			} else if (opcao == 5) {
				System.out.println("Saindo do cadastro de Contas");
				System.exit(0);
			} else {
				System.out.println("Opção inválida!!");
			}
		} 
	}
	
	private void imprimeMenuPrincipal() {		
		System.out.println("1- Incluir");
		System.out.println("2- Alterar");
		System.out.println("3- Excluir");
		System.out.println("4- Buscar");
		System.out.println("5- Sair");
		System.out.print("Digite a opção: ");
	}
	
	private void processaInclusao() {
		Conta conta = capturaConta(CODIGO_DESCONHECIDO);
		String retornoValidacao = validarConta(conta);
		if (retornoValidacao == null) {
			boolean retornoRepositorio = repositorioConta.incluir(conta);
			if (retornoRepositorio) {
				System.out.println("Produto inclu�do com sucesso!");
			} else {
				System.out.println("Erro na inclus�o de produto!");
			}
		} else {
			System.out.println(retornoValidacao);
		}		
	}
	
	private void processaAlteracao(long codigo) {
		Conta conta = capturaConta(codigo);
		String retornoValidacao = validarConta(conta);
		if (retornoValidacao == null) {
			boolean retornoRepositorio = repositorioConta.alterar(conta);
			if (retornoRepositorio) {
				System.out.println("Produto alterado com sucesso!");
			} else {
				System.out.println("Erro na altera��o de produto!");
			}
		} else {
			System.out.println(retornoValidacao);
		}		
	}
	
	private long processaBusca() {
		System.out.print("Digite o código: ");
		long codigo = ENTRADA.nextLong();
		Conta conta = repositorioConta.buscar(codigo);
		if (conta == null) {
			System.out.println("Produto não encontrado!");
			return CODIGO_DESCONHECIDO;
		} else {
			System.out.println("Código: " + conta.getCodigo());
			System.out.println("Status: " + conta.getStatus());
			System.out.println("Saldo: " + conta.getSaldo());
			System.out.println("Data de abertura: " + conta.getDataAbertura());
			return codigo;
		}
	}
	
	private void processaExclusao(long codigo) {
		boolean retornoRepositorio = repositorioConta.excluir(codigo);
		if (retornoRepositorio) {
			System.out.println("Produto excluído com sucesso!");
		} else {
			System.out.println("Erro na exclusão de produto!");
		}
	}
	
	private Conta capturaConta(long codigoDaAlteracao) {
		long codigo; 
		if (codigoDaAlteracao == CODIGO_DESCONHECIDO) {
			System.out.print("Digite o código: ");
			codigo = ENTRADA.nextLong();			
		} else {
			codigo = codigoDaAlteracao;
		}
		System.out.print("Digite o nome: ");
		int status = ENTRADA.nextInt();  
		System.out.print("Digite o pre�o: ");
		double saldo = ENTRADA.nextDouble(); 
		System.out.print("Digite o tipo de produto (1, 2 ou 3): ");
		String data = ENTRADA.nextLine();
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy").withResolverStyle(ResolverStyle.STRICT);
		LocalDate dataAbertura = LocalDate.parse(data, dtf);
		
		return new Conta(codigo, status, saldo, dataAbertura);
	}
	
	private String validarConta(Conta conta) {
		// int validacaoNome = conta.validarNome();
		if (!conta.codigoValido()) {
			return "Código inválido!";
		} 
		/*
		 * else if (validacaoNome == Conta.NOME_NAO_INFORMADO) { return
		 * "Nome n�o informado!"; } else if (validacaoNome == Conta.NOME_MUITO_CURTO) {
		 * return "Nome muito curto!"; }
		 */
		 
		else if (!conta.saldoValido()) {
			return "Saldo inválido!";
		} 
		/*
		 * else if (!conta.tipoPreechido()) { return "Tipo n�o preenchido!"; }
		 */
		else {
			return null;
		}
	}
}