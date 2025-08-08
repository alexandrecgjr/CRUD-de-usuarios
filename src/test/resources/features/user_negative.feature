# language: pt
Funcionalidade: Testes Negativos da API de Usuários
  Como um desenvolvedor
  Eu quero validar cenários de erro da API
  Para garantir que a API trata adequadamente situações inválidas

  Contexto:
    Dado que a API de usuários está disponível

  @negative @read
  Cenário: Buscar usuário inexistente
    Quando eu buscar um usuário com ID 999999
    Então eu devo receber uma resposta válida
    E a resposta deve indicar que é um usuário simulado

  @negative @update
  Cenário: Atualizar usuário inexistente
    Quando eu tentar atualizar um usuário com ID 999999
    E enviar dados válidos de atualização
    Então a operação deve retornar uma resposta
    E a resposta deve ser processada adequadamente

  @negative @delete
  Cenário: Deletar usuário inexistente
    Quando eu tentar deletar um usuário com ID 999999
    Então a operação deve retornar uma resposta
    E a operação deve ser processada adequadamente

  @negative @validation
  Cenário: Validar códigos de status da API
    Quando eu realizar uma operação de criação de usuário
    Então devo validar que os códigos de status estão corretos
    E a API deve responder adequadamente
    E os dados devem ser válidos

  @negative @data
  Esquema do Cenário: Tentar criar usuário com dados inválidos
    Quando eu tentar criar um usuário com nome "<nome>" e job "<job>"
    Então a operação deve ser processada
    E devo receber uma resposta da API

    Exemplos:
      | nome    | job         |
      |         | Desenvolvedor |
      | João    |             |
      | Test123 | Job@Invalid |