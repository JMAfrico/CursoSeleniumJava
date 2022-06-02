package br.com.steps;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;


public class AprenderCucumberStep {

	@Dado("que criei o arquivo corretamente")
	public void queCrieiOArquivoCorretamente() {
		System.out.println("passo 1");
	}
	@Quando("executa-lo")
	public void executaLo() {
		System.out.println("passo 3");
	}
	@Entao("a especificacao deve finalizar com sucesso")
	public void aEspecificacaoDeveFinalizarComSucesso() {
		System.out.println("passo 3");
	}
	
	private int contador = 0;
	@Dado("que o valor do contador e {int}")
	public void queOValorDoContador(Integer cont) {
		contador = cont;
	}
	@Quando("eu incrementar em {int}")
	public void euIncrementarEm(Integer cont) {
		contador = contador + cont;
	}
	@Entao("o valor do contador sera {int}")
	public void oValorDoContadorSera(Integer arg1) {
		System.out.println("Contador deve ser: "+arg1);
		System.out.println("Contador foi:"+contador);
		Assert.assertTrue(arg1 == this.contador);
	}
	
	
	Date entrega = new Date();
		
	@Dado("^que a entrega e dia (\\d{2})\\/(\\d{2})\\/(\\d{4})$")
	public void queAEntregaEDia(Integer dia, Integer mes, Integer ano) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, dia);
		calendar.set(Calendar.MONTH, mes-1);
		calendar.set(Calendar.YEAR, ano);
		entrega = calendar.getTime();
		
	}
	@Quando("^a entrega atrasar em (\\d+) (dia|dias|mes|meses)$")
	public void aEntregaAtrasarEmDias(Integer arg1, String tempo) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(entrega);
		if(tempo.equals("dias")) {
			calendar.add(Calendar.DAY_OF_MONTH,arg1);
		}
		if(tempo.equals("meses")) {
			calendar.add(Calendar.MONTH,arg1);
		}
		
		entrega = calendar.getTime();
	}
	@Entao("^a entrega sera efetuada em (\\d{2}\\/\\d{2}\\/\\d{4})$")
	public void aEntregaSeraEfetuadaEm(String data) {
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		String dataFormatada = format.format(entrega);
		Assert.assertEquals(data, dataFormatada);
	}

	Integer ticked;
	@Dado("^que o ticket( especial)? e (A.\\d{3})$")
	public void queOTicketE(String tipo, Integer tik) {
		ticked = tik;
	}

	Double valor;
	@Dado("que o valor da passagem e R$ {double}")
	public void que_o_valor_da_passagem_e_r$(Double double1) {
		valor = double1;
	}

	String nome;
	@Dado("^que o nome do passageiro e \"(.{5,20})\"$")
	public void que_o_nome_do_passageiro_e(String passageiro) {
		nome = passageiro;
	}

	String fone;
	@Dado("^que o telefone do passageiro e (9\\d{3}-\\d{4})$")
	public void que_o_telefone_do_passageiro_e(String cel) {
		fone = cel;
	}

	@Quando("criar os steps")
	public void criar_os_steps() {

	}

	@Entao("o teste vai funcionar")
	public void o_teste_vai_funcionar() {

	}
}
