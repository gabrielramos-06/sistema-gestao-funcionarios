# Sistema de Gestão de Funcionários em Java

Projeto de Aplicação para a disciplina de **Programação Orientada a Objetos (POO)**.

## Alunos
- Jonas Guilhermino Nascimento
- Gabriel Oliveira Ramos

## Pacotes
- `br.com.empresa.model`
- `br.com.empresa.dao`
- `br.com.empresa`

## Conceitos e tecnologias implementadas
- `Superclasse Abstrata (Funcionario) - centralização de atributos comuns (id, nome e salário base);`
- `Herança (Gerente e Caixa) - subclasses com regras e atributos específicos (departamento e turno);`
- `Polimorfismo - cálculo dinâmico de bonificação salarial (20% para Gerente e 10% para Caixa);`
- `Persistência com JDBC - integração nativa com banco de dados relacional (SQLite);`
- `Padrão DAO (Data Access Object) - isolamento das operações de banco de dados (INSERT, SELECT e DELETE).`

## Funcionalidades implementadas
- Cadastro de Gerentes (nome, salário base e departamento);
- Cadastro de Caixas (nome, salário base e turno de trabalho);
- Listagem geral de funcionários cadastrados no banco de dados;
- Cálculo automático polimórfico de bonificação e salário final por cargo;
- Cálculo do total da folha de pagamento da empresa;
- Exclusão de funcionários do banco de dados por ID;
- Criação automática da tabela `funcionarios` no banco de dados local (`empresa.db`).

## Como compilar
```bash
mvn clean compile

Como executar:
mvn exec:java -Dexec.mainClass="br.com.empresa.Main"
