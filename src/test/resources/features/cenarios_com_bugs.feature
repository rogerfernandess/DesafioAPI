@regressao
Feature: Cenarios que possuem bug


@cenarios_com_bug
Scenario Outline: Cadastro de simulação CPF duplicado
	Given que eu possua as informações <nome> <cpf> <email> <valor> <parcela> <seguro> para simulação
	When executo um "POST" para cadastrar uma simulação de um cpf já cadastrado
	Then sistema retorna <code> com a <mensagem>
	Examples:
		|nome|cpf|email|valor|parcela|seguro|code|mensagem|
		|"Fulano 1"|"12345678910"|"fulano1@teste.com.br"|1000|2|"true"|409|"CPF duplicado"|

@cenarios_com_bug
Scenario Outline: Alteração de simulação
	Given que eu possua as informações <nome> <cpf> <email> <valor> <parcelas> <seguro> para simulação
	When executo um "POST" para cadastrar uma simulação
	And  executo um "PUT" para alterar uma simulação <nome2> <cpf2> <email2> <valor2> <parcelas2> <seguro2>
	Then sistema retorna <code>
	Examples:
		|nome|cpf|email|valor|parcelas|seguro|code|nome2|cpf2|email2|valor2|parcelas2|seguro2|
		|"Fulano 1"|""|"fulano1@teste.com.br"|1100|2|"true"|200|"Fulano 2"|""|"fulano2@teste.com.br"|1200|3|"true"|

@cenarios_com_bug
Scenario Outline: Cadastro de simulação com regra inválida
	Given que eu possua as informações <nome> <cpf> <email> <valor> <parcelas> <seguro> para simulação
	When executo um "POST" para cadastrar uma simulação
	Then sistema retorna <code> com a <mensagem>
	Examples:
		|nome|cpf|email|valor|parcelas|seguro|code|mensagem|
		|"Fulano 2"|""|"fulano2@teste.com.br"|1000|49|"false"|400|"Parcelas deve ser menor ou igual que 48"|
		|"Fulano 3"|""|"fulano3@teste.com.br"|999|2|"false"|400|"Valor deve ser maior ou igual a R$ 1.000"|





