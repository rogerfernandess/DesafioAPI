@regressao
Feature: Consulta da situação de CPF

@restricoes
Scenario Outline: CPF com restrição
	Given que eu possua um <cpf> com restrição
	When executo um "GET" para consultar <restricao> no CPF
	Then sistema retorna <code> com a <mensagem>
	Examples:
	|cpf|code|mensagem|restricao|
	|"97093236014"|200|"O CPF 97093236014 tem problema"|  "restricao" |

@restricoes
Scenario Outline: CPF sem restrição
	Given que eu possua um cpf sem restrição
	When executo um "GET" para consultar <restricao> no CPF
	Then sistema retorna <code> com a <mensagem>
	Examples:
		|code|mensagem|restricao|
		|204|""|  "restricao"   |


