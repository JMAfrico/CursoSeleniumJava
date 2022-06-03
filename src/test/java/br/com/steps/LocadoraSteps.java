package br.com.steps;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Assert;

import br.com.entidades.Filme;
import br.com.entidades.TipoAluguel;
import br.com.services.AluguelService;
import br.com.services.NotaAluguel;
import br.com.utils.DateUtils;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Quando;

public class LocadoraSteps {

	private Filme filme;
	private AluguelService aluguel = new AluguelService();
	private NotaAluguel nota;
	private String erro;
	private TipoAluguel tipoAluguel;
	
	@Dado("um filme")
	public void umFilme(DataTable dataTable) {
		Map<String,String> map = dataTable.asMap(String.class, String.class);
		filme = new Filme();
		filme.setEstoque(Integer.parseInt(map.get("estoque")));
		filme.setAluguel(Integer.parseInt(map.get("preco")));
		String tipo = map.get("tipo");
		tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL:
			  tipo.equals("extendido")? TipoAluguel.EXTENDIDO:
		      TipoAluguel.COMUM;
	}
	
	@Dado("um filme com estoque de {int} unidades")
	public void umFilmeComEstoqueDeUnidades(Integer int1) {
		filme = new Filme();
		filme.setEstoque(int1);
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
		tipoAluguel = tipo.equals("semanal")? TipoAluguel.SEMANAL:
					  tipo.equals("extendido")? TipoAluguel.EXTENDIDO:
				      TipoAluguel.COMUM;
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
