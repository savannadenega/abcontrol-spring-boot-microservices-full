# Abcontrol | Spring Boot | Microservices

Desenvolvimento de uma aplicação com Microservices, com a utilização de Spring Boot, para a disciplina de Desenvolvimento de Software III Unisinos-2018.

_ABControl é um software que irá auxiliar no controle de tarefas, recursos humanos, prazos, materiais, orçamento, estoque e qualidade para uma empresa chamada Construtora ABC que é uma companhia que presta serviços no ramo da construção civil. O software visa melhorar a comunicação com clientes e fornecedores, e melhorar o acesso à informação dos projetos._

Grupo: Fabio Varisto, Franciele Fagundes e Savanna Denega.

### Recursos utilizados

- Microservices
- Spring Boot

- Mockito

------------------------------------------------------------------------------------------------------------------

### Como configurar o ambiente:

- Faça clone do projeto;
- Importe o projeto para sua IDE de preferência via pom (Maven).

### Realizando testes nos serviços

- Para realizar testes utilize o programa o Postman;
- No endereço `http://localhost:8080` execute as operações disponíveis.  

Exemplo de operação de GET: `http://localhost:8080/tarefas`  
_Esta operação retorna todas as tarefas disponíveis na base de dados._

Exemplo de operação de inserção, POST: `http://localhost:8080/tarefas`
_Esta operação adiciona uma tarefa na base de dados, utilizando o modelo JSON abaixo._
```
    {
        "id": 4,
        "nomeCompra": "Contratar empregados",
        "descricao": "Contratar empregados",
        "prioridade": "Alta",
        "status": "Em construção",
        "dataComecoRealizacao": "07/08/2017",
        "dataEstimadaEntrega": "08/12/2018",
        "usuario": "Savanna Denega",
        "materialEntity": "Material 1",
        "anexo": "Sem anexo"
    }
```

