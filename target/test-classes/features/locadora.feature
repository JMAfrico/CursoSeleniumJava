# language:pt

@tag
Funcionalidade: Locadora
Como um usuário
Eu quero cadastrar alugueis de filmes 
Para controlar precos e datas de entrega

  @alugar_filme
  Cenario: Deve alugar um filme com sucesso
	  Dado um filme com estoque de 2 unidades
	  E que o preco de aluguel seja R$ 3
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
	  
	@aluguel_filme_categoria_comum
	Cenario: Deve alugar para categoria comum
		Dado um filme com estoque de 2 unidades 
		E que o preco de aluguel seja R$ 4
		E o tipo do aluguel seja comum
		Quando alugar
		Entao o preco do aluguel sera R$ 4
		E o estoque do filme sera 1 unidades
		E a data de entrega sera em 1 dias 
		E a pontuacao recebida sera de 1 ponto
	
	@aluguel_filme_categoria_extendida
	Cenario: Deve dar condicoes especiais para categoria extendida
		Dado um filme com estoque de 2 unidades 
		E que o preco de aluguel seja R$ 4
		E o tipo do aluguel seja extendido
		Quando alugar
		Entao o preco do aluguel sera R$ 8
		E o estoque do filme sera 1 unidades
		E a data de entrega sera em 3 dias 
		E a pontuacao recebida sera de 2 pontos
