# TaskCo

TaskCo é uma aplicação de gerenciamento de tarefas desenvolvida em **Java** utilizando o framework **Spring Boot**. O objetivo do projeto é facilitar a organização de tarefas, equipes e categorias, permitindo o acompanhamento de prazos e status.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**
- **Jakarta Persistence API (JPA)**
- **Lombok**

## Estrutura do Projeto

O projeto está organizado em entidades que representam os principais componentes do sistema:

- **UserTeam**: Relaciona usuários a equipes.
- **Categories**: Representa categorias de tarefas.
- **Tasks**: Gerencia as tarefas, incluindo descrição, prioridade, prazos e status.
- **Comments**: Permite adicionar comentários às tarefas.

## Configuração do Ambiente

1. Certifique-se de ter o **Java 17** e o **Maven** instalados.
2. Clone este repositório:
   ```bash
   git clone https://github.com/cad1n/taskco.git