# DesafioAPI

O projeto foi criado no Intellij.

Pré-rquisitos:
Instalar os plugins do cucumber e Gherkin
Com a IDE aberta
- ctrl+alt+s clique no meu Plugins
- buscar por cucumber for Java e instalar
- buscar por Gherkin e instalar

Para executar todos os cenários através do maven:
- Abrir o Terminal
- Navegar até a raiz do projeto
- Digitar "mvn clean test allure:report"
- Após os testes finalizarem, digite "mvn allure:serve" para abrir o report no Browser.

A execução está rodando os 32 cenários. De forma proposital deixei os cenários que dão erro na execução para ficar evidenciado que não estão se comportando como o esperado nos exemplos usados nos cenários.

Dentro do projeto criei runners específicos para Tags especificas, caso queira executar um grupo de testes específicos.

Dos 32 cenários, 4 desses estão com bug e estão destacados na lista abaixo

Regras em desacordo com o comportamento:
- (bug) No post é possível cadastrar passando numero de parcelas maior que 48
- (bug) No Post é possível cadastrar passando valor menor que 1000 (cenário com falha)
- (bug) Cadastro de CPF já existente, o código de erro é 400, porém na documentação diz que deveria vir 409
- No delete, nao tem um erro 404 para IDs que não existem.

Mensagens diferentes do esperado:
- CPF com restrição Swagger exemplo:"O CPF 999999999 possui restrição" a resposta na API "O CPF 99999999999 tem problema"
- Email inválido possui 2 mensagens que se alternam com a execução:"não é um endereço de e-mail" e "E-mail deve ser um e-mail válido"
- Cadastro de CPF já existente, no exemplo do Swagger e nas regras a msg é "CPF já existente", porém na execução é "CPF duplicado"
- Consulta simulação pelo CPF, no Swagger mostra como exemplo a mensagem "O CPF 999999999 possui restrição" o que não faz sentido com a consulta.

Chumbado no código:
- (bug) No put o campo valor não pode ser alterado, pois está chumbado no código para ficar sempre com o valor que foi cadastrado primeiro.
- No post o campo seguro quando passado null sempre é gravado como false. Apesar de no código de vocês ter uma mensagem programada para valor nulo nesse campo 

Melhorias:
- No get de restrição, deveria ter uma validação de mínimo de caracteres e apenas aceitar números. Qualquer valor que coloco alfanumerico sempre retorna 204.
- No post, deveria ter uma validação de mínimo de caracteres, no caso 11 quando se trata de CPF.
- No put, poderia ter uma tratativa quando tentamos modificar o ID, uma mensagem e um erro 400 seria melhor que o 500 atual
