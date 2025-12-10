# 📚 Sistema ENEM - Aplicação de Questões e Estatísticas

Sistema desktop desenvolvido em JavaFX para prática de questões do ENEM, com acompanhamento de estatísticas de desempenho por disciplina e visualização de progresso diário.

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Apresentação Pitch PDF](#apresentação-em-formato-de-pitch-pdf-)
- [Vídeo Com Pricipais Funcionalidades](#vídeo-de-apresentação-das-pricipais-funcionalidades-%EF%B8%8F)
- [Tecnologias](#%EF%B8%8F-tecnologias)
- [Estrutura do Projeto](#-estrutura-do-projeto)
- [Como Executar](#-como-executar)
- [Diagramas UML](#-diagramas-uml)
- [Dicionário de Dados](#%EF%B8%8F-dicionário-de-dados)
---

## 🎯 Sobre o Projeto

Sistema educacional desenvolvido para auxiliar estudantes na preparação para o ENEM. O sistema permite:

- **Prática de Questões:** Resolver questões do ENEM organizadas por disciplina
- **Acompanhamento de Desempenho:** Estatísticas detalhadas de acertos e erros por área de conhecimento
- **Histórico Diário:** Visualização de progresso ao longo dos dias
- **Acesso a Provas:** Links diretos para provas oficiais do INEP
- **Gestão de Usuários:** Sistema de login e registro

### Disciplinas Suportadas

- **Ciências Humanas** (Questões 1-45)
- **Ciências da Natureza** (Questões 46-90)
- **Linguagens e Códigos** (Questões 91-135)
- **Matemática** (Questões 136-180)

### Apresentação em formato de pitch PDF 📃

- **Pitch em formato PDF reunindo as informações essenciais do projeto** [Veja aqui!](https://github.com/gugucazhiz/ProjetoFinal/blob/main/teste_tela_enem/docs/ArquivosApresentacao/apresentacao-pitch.pdf)

### Vídeo de apresentação das pricipais funcionalidades 📽️

- **Visão rápida das principais funcionalidades e como elas funcionam na prática** [Assista aqui!](https://www.youtube.com/watch?v=MqjrqXF6gDE)

---

## 🛠️ Tecnologias

### Backend
- **Java 23** - Linguagem de programação
- **JavaFX 25** - Framework para interface gráfica
- **PostgreSQL** - Banco de dados relacional
- **Maven** - Gerenciamento de dependências

### Bibliotecas
- **Jackson Databind 2.17.0** - Processamento de arquivos JSON
- **PostgreSQL JDBC Driver 42.7.7** - Driver de conexão com banco de dados

### Arquitetura
- **Padrão MVC** (Model-View-Controller)
- **DAO Pattern** (Data Access Object)
- **Service Layer** para lógica de negócio

---

## 📁 Estrutura do Projeto

```
teste_tela_enem/
├── src/
│   └── main/
│       ├── java/
│       │   └── br/ufrn/tads/
│       │       ├── App.java                    # Classe principal
│       │       ├── controllers/                # Controladores das telas
│       │       ├── model/                      # Entidades do domínio
│       │       ├── repository/                 # Camada de acesso a dados
│       │       └── servicy/                    # Camada de serviços
│       └── resources/
│           └── br/ufrn/tads/
│               ├── *.fxml                     # Telas JavaFX
│               └── *.css                      # Estilos
├── docs/
│   ├── uml/                                   # Diagramas PlantUML
│   └── *.md                                   # Documentação detalhada
└── pom.xml                                    # Configuração Maven
```

### Camadas da Aplicação

1. **Controllers** - Gerenciam a interação com a interface gráfica
2. **Services** - Implementam a lógica de negócio
3. **Repository/DAO** - Acessam o banco de dados
4. **Model** - Representam as entidades do domínio

---

## 🚀 Como Executar

### Pré-requisitos

- Java 23 ou superior
- Maven 3.6+
- PostgreSQL instalado e rodando
- Banco de dados `Enem_crud` criado

### Configuração

1. **Configure o banco de dados:**
   - Crie o banco de dados: `CREATE DATABASE Enem_crud;`
   - Configure as credenciais em `varAmbiente/config.properties`:
   ```properties
   db.user=seu_usuario
   db.password=sua_senha
   ```

2. **Importe os dados (opcional):**
   - Execute a classe `Importer` para importar questões do arquivo JSON

### Executando a Aplicação

**Opção 1: Via Maven**
```bash
mvn clean javafx:run
```

**Opção 2: Via IDE**
- Execute a classe `br.ufrn.tads.App`

**Opção 3: Gerar JAR executável**
```bash
mvn clean package
java -jar target/teste_tela_enem-1.0-SNAPSHOT.jar
```

---

## 📊 Diagramas UML

### 1. Diagrama de Classes

O diagrama de classes apresenta a estrutura completa do sistema, mostrando todas as classes, seus atributos, métodos e relacionamentos.

<img width="1971" height="3023" alt="Image" src="https://github.com/user-attachments/assets/15b2f486-0c90-4e8a-af2e-02939baac342" />

**Principais componentes:**
- **Model:** User, Question, Alternative, Root, UserDailyStats
- **Controllers:** 8 controladores para diferentes telas
- **Services:** Login, QuestoesServicy
- **Repository:** UserDao, QuestionsDao, DbConnection, Importer

---

### 2. Diagrama de Componentes

O diagrama de componentes mostra a arquitetura do sistema em alto nível, destacando as dependências entre os módulos.

<img width="2024" height="1233" alt="Image" src="https://github.com/user-attachments/assets/5f72cf17-7ff3-401b-83d0-b3ead8645191" />

**Camadas identificadas:**
- **Frontend:** Interface JavaFX (FXML)
- **Controllers:** Camada de controle
- **Services:** Camada de serviços
- **Repository:** Camada de acesso a dados
- **Model:** Entidades do domínio
- **Database:** PostgreSQL


---

### 3. Diagrama de Entidade e Relacionamento (DER)

O diagrama DER representa a estrutura do banco de dados, mostrando as tabelas, seus campos e relacionamentos.

<img width="1379" height="753" alt="Image" src="https://github.com/user-attachments/assets/4041494c-3caf-4529-9446-186e98eb92a9" />

**Entidades principais:**
- `Usuario` - Dados dos usuários e estatísticas gerais
- `questions` - Questões do ENEM
- `alternatives` - Alternativas de cada questão
- `topics` - Tópicos associados às questões
- `user_stats_daily` - Estatísticas diárias dos usuários


---

## 🗄️ Dicionário de Dados

### Tabela: Usuario

Armazena informações dos usuários e suas estatísticas de desempenho.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id_user` | BIGINT (PK) | Identificador único do usuário |
| `nome` | VARCHAR (UNIQUE) | Nome de usuário (único) |
| `senha` | VARCHAR | Senha de acesso |
| `email` | VARCHAR | E-mail do usuário |
| `quest_feitas` | INTEGER | Total de questões respondidas |
| `quest_certas` | INTEGER | Total de questões corretas |
| `quest_erradas` | INTEGER | Total de questões erradas |
| `acertos_humanas` | INTEGER | Acertos em Ciências Humanas |
| `erros_humanas` | INTEGER | Erros em Ciências Humanas |
| `acertos_linguagem` | INTEGER | Acertos em Linguagens |
| `erros_linguagem` | INTEGER | Erros em Linguagens |
| `acertos_mat` | INTEGER | Acertos em Matemática |
| `erros_mat` | INTEGER | Erros em Matemática |
| `acertos_nat` | INTEGER | Acertos em Ciências da Natureza |
| `erros_nat` | INTEGER | Erros em Ciências da Natureza |

### Tabela: questions

Armazena as questões das provas do ENEM.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | BIGINT (PK) | Identificador único da questão |
| `index_number` | INTEGER | Número da questão na prova (1-180) |
| `title` | TEXT | Enunciado da questão |
| `discipline` | VARCHAR | Disciplina (ciencias-humanas, ciencias-natureza, linguagens, matematica) |
| `language` | VARCHAR | Idioma da questão |
| `year` | INTEGER | Ano da prova |
| `context` | TEXT | Contexto da questão |
| `correct_alternative` | VARCHAR | Letra da alternativa correta (A-E) |
| `files` | TEXT[] | Array de arquivos relacionados |

### Tabela: alternatives

Armazena as alternativas de resposta para cada questão.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | BIGINT (PK) | Identificador único da alternativa |
| `question_id` | BIGINT (FK) | Referência à questão |
| `letter` | VARCHAR | Letra da alternativa (A, B, C, D, E) |
| `text` | TEXT | Texto da alternativa |
| `is_correct` | BOOLEAN | Indica se é a alternativa correta |

### Tabela: topics

Armazena os tópicos/temas associados a cada questão.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | BIGINT (PK) | Identificador único do tópico |
| `question_id` | BIGINT (FK) | Referência à questão |
| `topic_text` | VARCHAR | Texto descritivo do tópico |

### Tabela: user_stats_daily

Armazena estatísticas diárias de desempenho dos usuários.

| Campo | Tipo | Descrição |
|-------|------|-----------|
| `id` | BIGINT (PK) | Identificador único do registro |
| `id_user` | BIGINT (FK) | Referência ao usuário |
| `data` | DATE | Data das estatísticas |
| `quest_feitas` | INTEGER | Questões feitas no dia |
| `quest_certas` | INTEGER | Questões corretas no dia |
| `quest_erradas` | INTEGER | Questões erradas no dia |

**Constraint:** `UNIQUE(id_user, data)` - Um registro por usuário por dia

### Relacionamentos

- **Usuario (1) ──► (N) user_stats_daily** - Um usuário tem múltiplas estatísticas diárias
- **questions (1) ──► (N) alternatives** - Uma questão possui 5 alternativas
- **questions (1) ──► (N) topics** - Uma questão pode ter múltiplos tópicos

> 📝 **Documentação completa:** Consulte [DICIONARIO_DE_DADOS.md](https://github.com/gugucazhiz/ProjetoFinal/blob/main/teste_tela_enem/docs/DICIONARIO_DE_DADOS.md) para informações detalhadas.

---

### Arquivos PlantUML

Os arquivos fonte dos diagramas estão em `docs/uml/`:

- `classes.puml` - Diagrama de Classes
- `component.puml` - Diagrama de Componentes
- `der.puml` - Diagrama de Entidade e Relacionamento

Para gerar os diagramas, acesse [PlantUML Online](http://www.plantuml.com/plantuml/uml/) e cole o conteúdo dos arquivos.

---

## 👥 Desenvolvimento

**Projeto:** Sistema ENEM - Aplicação de Questões e Estatísticas  
**Versão:** 1.0-SNAPSHOT  
**Linguagem:** Java 23  
**Framework:** JavaFX 25


**Desenvolvedores:**
**Davi Santos e**
**Gustavo Martins**

Somos do TADS/EAJ UFRN

---

**Última atualização:** 09/12/2025

