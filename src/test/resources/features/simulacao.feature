@regressao
Feature: CRUD de Simulação

@simulacao_positivo
Scenario Outline: Cadastro de simulação
	Given que eu possua as informações <nome> <cpf> <email> <valor> <parcelas> <seguro> para simulação
	When executo um "POST" para cadastrar uma simulação
	Then sistema retorna <code>
	Examples:
		|nome|cpf|email|valor|parcelas|seguro|code|
		|"Fulano 1"|""|"fulano1@teste.com.br"|1000|2|"true"|201|
		|"Fulano 2"|""|"fulano2@teste.com.br"|1001|3|"true"|201|
		|"Fulano 3"|""|"fulano3@teste.com.br"|39999|47|"true"|201|
		|"Fulano 4"|""|"fulano4@teste.com.br"|40000|48|"true"|201|
		|"Fulano 5"|""|"fulano5@teste.com.br"|1000|2|"false"|201|
		|"Fulano 6"|""|"fulano6@teste.com.br"|1001|3|"false"|201|
		|"Fulano 7"|""|"fulano7@teste.com.br"|39999|47|"false"|201|
		|"Fulano 8"|""|"fulano8@teste.com.br"|40000|48|"false"|201|

@simulacao_positivo
Scenario Outline: Consulta Simulação por CPF
	Given que eu possua um cpf
	When executo um "POST" para cadastrar uma <simulacao>
	And executo um "GET" para consultar essa <simulacao>
	Then sistema retorna <code> com a <mensagem>
	Examples:
		|code|mensagem|simulacao|
		|200 |	""	  |  "" 	|

@simulacao_positivo
Scenario Outline: Consultar todas as Simulações
	Given executo um "GETALL" para consultar as <simulacao> existentes
	Then sistema retorna <code> e lista de simulações
	Examples:
		|code|simulacao|
		|200|     ""   |

@simulacao_positivo
Scenario Outline: Deletar Simulação existente
	Given que eu possua um cpf
	When executo um "POST" para cadastrar uma <simulacao>
	And executo um "DELETE" para deletar essa <simulacao>
	And executo um "GET" para consultar essa <simulacao>
	Then sistema retorna <code> com a <mensagem>
	Examples:
		|code|mensagem|simulacao|
		|404 |	"CPF {cpf} não encontrado"	|  "" 	|

@simulacao_negativo
Scenario Outline: Consulta Simulação por CPF
	Given que eu possua um cpf
	And executo um "GET" para consultar essa <simulacao>
	Then sistema retorna <code> com a <mensagem>
	Examples:
		|code|mensagem|simulacao|
		|404 |	"CPF {cpf} não encontrado"	  |  "" 	|

@simulacao_negativo
Scenario Outline: Cadastro de simulação com regra inválida
	Given que eu possua as informações <nome> <cpf> <email> <valor> <parcelas> <seguro> para simulação
	When executo um "POST" para cadastrar uma simulação
	Then sistema retorna <code> com a <mensagem>
	Examples:
		|nome|cpf|email|valor|parcelas|seguro|code|mensagem|
		|"Fulano 1"|""|"fulano1@teste.com.br"|1000|1|"true"|400|"Parcelas deve ser igual ou maior que 2"|
		|"Fulano 4"|""|"fulano4@teste.com.br"|40001|2|"true"|400|"Valor deve ser menor ou igual a R$ 40.000"|
		|"Fulano 5"|""|"fulano5"|1000|2|"false"|400|"E-mail deve ser um e-mail válido"|
		|"Fulano 6"|""|"email"|1000|2|"false"|400|"não é um endereço de e-mail"|
		|"vazio"|""|"fulano7@teste.com.br"|1000|2|"false"|400|"Nome não pode ser vazio"|
		|"Fulano 8"|"vazio"|"fulano8@teste.com.br"|1000|2|"false"|400|"CPF não pode ser vazio"|
		|"Fulano 9"|""|"vazio"|1000|2|"false"|400|"E-mail não deve ser vazio"|
		|"Fulano 10"|""|"fulano10@teste.com.br"|0|2|"false"|400|"Valor não pode ser vazio"|
		|"Fulano 11"|""|"fulano13@teste.com.br"|1000|0|"false"|400|"Parcelas não pode ser vazio"|
		|"Fulano 12"|""|"fulano12@teste.com.br"|1000|2|"vazio"|201|""|
