package br.com.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Assert;
import br.com.entidades.Filme;
import br.com.services.AluguelService;
import br.com.services.NotaAluguel;
import br.com.utils.DateUtils;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class LocadoraSteps {

	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private String tipoAluguel;
	
	@Dado("um filme com estoque de {int} unidades")
	public void umFilmeComEstoqueDeUnidades(Integer unidade) {
		filme = new Filme();
		filme.setEstoque(unidade);
	}
	
	@Dado("que o preco de aluguel seja R$ {int}")
	public void queOPrecoDeAluguelSejaR$(Integer preco) {
		filme.setAluguel(preco);
	}
	
	@Quando("alugar")
	public void alugar() {
		try {
			nota = aluguel.alugar(filme, tipoAluguel);
		}catch (RuntimeException e) {
			erro = e.getMessage();
		}
	}
	
	@Entao("o preco do aluguel sera R$ {int}")
	public void oPrecoDoAluguelSeraR$(Integer int1) {
		Assert.assertEquals(int1, (Integer)nota.getPreco());
	}

	@Entao("o estoque do filme sera {int} unidades")
	public void oEstoqueDoFilmeSeraUnidade(Integer int1) {
		Assert.assertEquals(int1, filme.getEstoque());
	}
	
	@Entao("nao sera possivel por falta de estoque")
	public void nao_sera_possivel_por_falta_de_estoque() {
		Assert.assertEquals("Filme sem estoque", erro);
	}
	
	@Dado("^o tipo do aluguel seja (.*)$")
	public void oTipoDoAluguelSejaExtendido(String tipo) {
		tipoAluguel = tipo;
	}
	
	@Entao("^a data de entrega sera em (\\d+) dias?$")
	public void aDataDeEntregaSeraEmDias(Integer dias) {
		Date dataEsperada = DateUtils.obterDataDiferencaDias(dias);
		Date dataReal = nota.getDataEntrega();	
		DateFormat format = new SimpleDateFormat("dd/MM/yyyy");	
		Assert.assertEquals(format.format(dataEsperada),format.format(dataReal));		
	}
	
	@Entao("^a pontuacao recebida sera de (\\d+) pontos?$")
	public void aPontuacaoRecebidaSeraDePontos(Integer pontuacao) {
		Assert.assertTrue(nota.getPontuacao() == pontuacao);
	}
}
