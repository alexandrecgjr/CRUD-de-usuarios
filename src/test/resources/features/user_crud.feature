# language: pt
Funcionalidade: Gerenciamento de Usuários via API
  Como um desenvolvedor
  Eu quero gerenciar usuários através da API
  Para que eu possa realizar operações CRUD completas

  Contexto:
    Dado que a API de usuários está disponível

  @smoke @crud
  Cenário: Ciclo completo CRUD de usuário
    Quando eu criar um usuário com nome "Ale Xandrinho" e job "testador"
    Então o usuário deve ser criado com sucesso
    E eu devo receber um ID válido para o usuário
    
    Quando eu buscar o usuário criado
    Então eu devo receber os dados do usuário
    E o nome deve ser "Ale Xandrinho"
    E o job deve ser "testador"
    
    Quando eu atualizar o job do usuário para "QA de automação"
    Então o usuário deve ser atualizado com sucesso
    E o job deve ser "QA de automação"
    E deve existir uma data de atualização
    
    Quando eu deletar o usuário
    Então o usuário deve ser deletado com sucesso
    E a operação deve retornar status 204

  @create @positive
  Cenário: Criar usuário com dados válidos
    Quando eu criar um usuário com nome "Maria Santos" e job "Analista de QA"
    Então o usuário deve ser criado com sucesso
    E o status code deve ser 201
    E a resposta deve conter os dados do usuário

  @update @positive
  Cenário: Atualizar usuário existente
    Dado que existe um usuário com ID 1
    Quando eu atualizar o usuário com nome "Pedro Costa" e job "Tech Lead"
    Então o usuário deve ser atualizado com sucesso
    E o status code deve ser 200
    E a resposta deve conter os dados atualizados

  @read @positive
  Cenário: Buscar usuário por ID
    Dado que existe um usuário com ID 1
    Quando eu buscar o usuário pelo ID
    Então eu devo receber os dados do usuário
    E o status code deve ser 200
    E a resposta deve conter um objeto de dados válido

  @delete @positive
  Cenário: Deletar usuário existente
    Dado que existe um usuário com ID 1
    Quando eu deletar o usuário
    Então o usuário deve ser deletado com sucesso
    E o status code deve ser 204

  @create @boundary
  Esquema do Cenário: Criar usuários com diferentes dados
    Quando eu criar um usuário com nome "<nome>" e job "<job>"
    Então o usuário deve ser criado com sucesso
    E o status code deve ser 201

    Exemplos:
      | nome              | job                    |
      | João Silva        | Desenvolvedor Frontend |
      | Ana Costa         | Product Manager        |
      | Carlos Oliveira   | DevOps Engineer        |
      | Mariana Santos    | UX Designer            |