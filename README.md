# Sistema Bancário - Java

Sistema bancário desenvolvido em Java puro para praticar os fundamentos da linguagem.

## Funcionalidades

- Cadastro, busca, atualização e remoção de usuários (CRUD)
- Depósito, saque e transferência entre usuários
- Listagem de transações por usuário
- Busca de transações acima de um valor
- Agrupamento de transações por tipo
- Total movimentado por usuário
- Validações com exceptions customizadas

## Tecnologias

- Java 21
- Orientação a Objetos (POO)
- Collections (List, Map)
- Stream API + Lambda
- Exceptions customizadas

## Estrutura

src/
├── Exceptions/
│   ├── SaldoInsuficienteException
│   ├── ValorInvalidoException
│   ├── UsuarioExistenteException
│   ├── UsuarioNaoEncontrado
│   └── ListaVaziaException
├── Usuario
├── Transacao
├── BancoService
├── UsuarioService
└── Main

## Autor

Lucas Fernandes — [LinkedIn](https://linkedin.com/in/lucas-fernandes-ads) | [GitHub](https://github.com/lfernandes-dev)