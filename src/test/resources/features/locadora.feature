# language:pt

@tag
Funcionalidade: Locadora
Como um usuário
Eu quero cadastrar alugueis de filmes 
Para controlar precos e datas de entrega
	  
	@alugar_filme
  Cenario: Deve alugar um filme com sucesso
	  Dado um filme 
	  	|estoque| 2    |
	  	|preco  | 3    |
	  	|tipo	  | comum|
	  Quando alugar 
	  Entao o preco do aluguel sera R$ 3
	  E a data de entrega sera em 1 dia
	  E o estoque do filme sera 1 unidades
	  
	@alugar_filme_sem_estoque
	Cenario: Nao deve alugar filme sem estoque
	  Dado um filme com estoque de 0 unidades
	  Quando alugar 
	  Entao nao sera possivel por falta de estoque
	  E o estoque do filme sera 0 unidades

 	@aluguel_filme_conforme_condicoes
	Esquema do Cenario: Deve alugar um filme conforme condicoes
		Dado um filme com estoque de <estoque> unidades 
		E que o preco de aluguel seja R$ <preco>
		E o tipo do aluguel seja <tipo>
		Quando alugar
		Entao o preco do aluguel sera R$ <valor>
		E o estoque do filme sera 1 unidades
		E a data de entrega sera em <qtdDias> dias 
		E a pontuacao recebida sera de <pontuacao> pontos
		
	  Exemplos:	
		|estoque |preco |tipo			 |valor|qtdDias|pontuacao|
		|2			 |4		  |extendido |8		 |3			 |2				 |	
		|2			 |4		  |comum     |4		 |1			 |1				 |
		|2			 |5	    |semanal   |15   |7			 |3				 |	
