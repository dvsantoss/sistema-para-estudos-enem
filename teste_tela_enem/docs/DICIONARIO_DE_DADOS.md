# Dicionário de Dados - Projeto ENEM

**Sistema:** Aplicação ENEM - Sistema de Questões e Estatísticas  
**Banco de Dados:** PostgreSQL  
**Database:** Enem_crud  
**Versão:** 1.0  
**Data:** 2025

---

## 📋 Índice

1. [Tabela: Usuario](#1-tabela-usuario)
2. [Tabela: questions](#2-tabela-questions)
3. [Tabela: alternatives](#3-tabela-alternatives)
4. [Tabela: topics](#4-tabela-topics)
5. [Tabela: user_stats_daily](#5-tabela-user_stats_daily)
6. [Relacionamentos](#relacionamentos)
7. [Índices](#índices)
8. [Constraints](#constraints)

---

## 1. Tabela: Usuario

**Descrição:** Armazena informações dos usuários do sistema, incluindo credenciais de acesso e os registros de desempenho do usuário nas questões do ENEM.

**Nome da Tabela:** `Usuario`  
**Sinonímia:** `users`, `usuarios`

### Estrutura da Tabela

| # | Nome do Campo | Tipo de Dado | Tamanho | Null? | Chave | Default | Descrição |
|---|---------------|--------------|---------|-------|-------|---------|-----------|
| 1 | id_user | BIGINT | - | NÃO | PK | AUTO_INCREMENT | Identificador único do usuário (chave primária) |
| 2 | nome | VARCHAR | 255 | NÃO | UK | - | Nome de usuário (deve ser único no sistema) |
| 3 | senha | VARCHAR | 255 | NÃO | - | - | Senha de acesso do usuário (armazenada em texto) |
| 4 | email | VARCHAR | 255 | NÃO | - | - | Endereço de e-mail do usuário |
| 5 | quest_feitas | INTEGER | - | SIM | - | 0 | Total de questões respondidas pelo usuário |
| 6 | quest_certas | INTEGER | - | SIM | - | 0 | Total de questões respondidas corretamente |
| 7 | quest_erradas | INTEGER | - | SIM | - | 0 | Total de questões respondidas incorretamente |
| 8 | acertos_humanas | INTEGER | - | SIM | - | 0 | Total de acertos em Ciências Humanas |
| 9 | erros_humanas | INTEGER | - | SIM | - | 0 | Total de erros em Ciências Humanas |
| 10 | acertos_linguagem | INTEGER | - | SIM | - | 0 | Total de acertos em Linguagens e Códigos |
| 11 | erros_linguagem | INTEGER | - | SIM | - | 0 | Total de erros em Linguagens e Códigos |
| 12 | acertos_mat | INTEGER | - | SIM | - | 0 | Total de acertos em Matemática |
| 13 | erros_mat | INTEGER | - | SIM | - | 0 | Total de erros em Matemática |
| 14 | acertos_nat | INTEGER | - | SIM | - | 0 | Total de acertos em Ciências da Natureza |
| 15 | erros_nat | INTEGER | - | SIM | - | 0 | Total de erros em Ciências da Natureza |

### Constraints

- **PRIMARY KEY:** `id_user`
- **UNIQUE:** `nome`
- **NOT NULL:** `id_user`, `nome`, `senha`, `email`
- **DEFAULT:** Todos os campos de estatísticas têm valor padrão 0

### Regras de Negócio

1. O campo `nome` deve ser único no sistema (não pode haver dois usuários com o mesmo nome)
2. O campo `nome` e `senha` devem ter no mínimo 4 caracteres (validação no código)
3. Os campos de estatísticas são incrementados conforme o usuário responde questões
4. A soma de `quest_certas` + `quest_erradas` deve ser igual a `quest_feitas`

### Exemplo de Dados

```
id_user: 1
nome: "joao_silva"
senha: "senha123"
email: "joao@email.com"
quest_feitas: 45
quest_certas: 30
quest_erradas: 15
acertos_humanas: 8
erros_humanas: 2
acertos_linguagem: 7
erros_linguagem: 3
acertos_mat: 10
erros_mat: 5
acertos_nat: 5
erros_nat: 5
```

---

## 2. Tabela: questions

**Descrição:** Armazena as questões das provas do ENEM, incluindo informações sobre disciplina, ano, contexto e alternativa correta.

**Nome da Tabela:** `questions`  
**Sinonímia:** `questoes`, `questao`

### Estrutura da Tabela

| # | Nome do Campo | Tipo de Dado | Tamanho | Null? | Chave | Default | Descrição |
|---|---------------|--------------|---------|-------|-------|---------|-----------|
| 1 | id | BIGINT | - | NÃO | PK | AUTO_INCREMENT | Identificador único da questão (chave primária) |
| 2 | index_number | INTEGER | - | NÃO | - | - | Número da questão na prova (ex: 1, 2, 3... até 180) |
| 3 | title | TEXT | - | SIM | - | NULL | Enunciado/título da questão |
| 4 | discipline | VARCHAR | 255 | SIM | - | NULL | Disciplina da questão. Valores possíveis: "ciencias-humanas", "ciencias-natureza", "linguagens", "matematica" |
| 5 | language | VARCHAR | 50 | SIM | - | NULL | Idioma da questão (ex: "pt-BR", "en") |
| 6 | year | INTEGER | - | SIM | - | NULL | Ano da prova do ENEM (ex: 2022, 2023, 2024) |
| 7 | context | TEXT | - | SIM | - | NULL | Contexto ou texto base da questão |
| 8 | correct_alternative | VARCHAR | 1 | SIM | - | NULL | Letra da alternativa correta (A, B, C, D ou E) |
| 9 | files | TEXT[] | - | SIM | - | NULL | Array de strings contendo nomes/caminhos de arquivos relacionados à questão (imagens, gráficos, etc.) |

### Constraints

- **PRIMARY KEY:** `id`
- **NOT NULL:** `id`, `index_number`

### Regras de Negócio

1. O campo `index_number` representa a posição da questão na prova (1 a 180)
2. O campo `discipline` pode ter os seguintes valores:
   - `"ciencias-humanas"` - Questões 1 a 45
   - `"ciencias-natureza"` - Questões 46 a 90
   - `"linguagens"` - Questões 91 a 135
   - `"matematica"` - Questões 136 a 180
3. O campo `correct_alternative` deve conter apenas uma letra: A, B, C, D ou E
4. O campo `files` é um array PostgreSQL que pode conter múltiplos arquivos

### Exemplo de Dados

```
id: 1
index_number: 1
title: "Questão sobre História do Brasil"
discipline: "ciencias-humanas"
language: "pt-BR"
year: 2022
context: "No período colonial brasileiro..."
correct_alternative: "B"
files: ["imagem1.png", "grafico1.jpg"]
```

---

## 3. Tabela: alternatives

**Descrição:** Armazena as alternativas de resposta para cada questão. Cada questão possui 5 alternativas (A, B, C, D, E).

**Nome da Tabela:** `alternatives`  
**Sinonímia:** `alternativas`, `alternativa`

### Estrutura da Tabela

| # | Nome do Campo | Tipo de Dado | Tamanho | Null? | Chave | Default | Descrição |
|---|---------------|--------------|---------|-------|-------|---------|-----------|
| 1 | id | BIGINT | - | NÃO | PK | AUTO_INCREMENT | Identificador único da alternativa (chave primária) |
| 2 | question_id | BIGINT | - | NÃO | FK | - | Referência à questão (chave estrangeira para questions.id) |
| 3 | letter | VARCHAR | 1 | SIM | - | NULL | Letra da alternativa (A, B, C, D ou E) |
| 4 | text | TEXT | - | SIM | - | NULL | Texto completo da alternativa |
| 5 | is_correct | BOOLEAN | - | SIM | - | FALSE | Indica se esta é a alternativa correta (true) ou não (false) |

### Constraints

- **PRIMARY KEY:** `id`
- **FOREIGN KEY:** `question_id` → `questions.id` (ON DELETE CASCADE)
- **NOT NULL:** `id`, `question_id`

### Regras de Negócio

1. Cada questão deve ter exatamente 5 alternativas (A, B, C, D, E)
2. Apenas uma alternativa por questão deve ter `is_correct = true`
3. O campo `letter` deve conter apenas uma letra: A, B, C, D ou E
4. Quando uma questão é deletada, todas suas alternativas são deletadas automaticamente (CASCADE)
5. A combinação `question_id` + `letter` deve ser única (não pode haver duas alternativas "A" para a mesma questão)

### Exemplo de Dados

```
id: 1
question_id: 1
letter: "A"
text: "Primeira alternativa da questão"
is_correct: false

id: 2
question_id: 1
letter: "B"
text: "Segunda alternativa da questão (CORRETA)"
is_correct: true

id: 3
question_id: 1
letter: "C"
text: "Terceira alternativa da questão"
is_correct: false
```

---

## 4. Tabela: topics

**Descrição:** Armazena os tópicos/temas associados a cada questão. Uma questão pode ter múltiplos tópicos.

**Nome da Tabela:** `topics`  
**Sinonímia:** `topicos`, `topico`

### Estrutura da Tabela

| # | Nome do Campo | Tipo de Dado | Tamanho | Null? | Chave | Default | Descrição |
|---|---------------|--------------|---------|-------|-------|---------|-----------|
| 1 | id | BIGINT | - | NÃO | PK | AUTO_INCREMENT | Identificador único do tópico (chave primária) |
| 2 | question_id | BIGINT | - | NÃO | FK | - | Referência à questão (chave estrangeira para questions.id) |
| 3 | topic_text | VARCHAR | 255 | SIM | - | NULL | Texto descritivo do tópico/tema da questão |

### Constraints

- **PRIMARY KEY:** `id`
- **FOREIGN KEY:** `question_id` → `questions.id` (ON DELETE CASCADE)
- **NOT NULL:** `id`, `question_id`

### Regras de Negócio

1. Uma questão pode ter zero, um ou múltiplos tópicos associados
2. Tópicos são usados para categorizar e facilitar a busca de questões
3. Quando uma questão é deletada, todos seus tópicos são deletados automaticamente (CASCADE)
4. O mesmo tópico pode aparecer em múltiplas questões

### Exemplo de Dados

```
id: 1
question_id: 1
topic_text: "História do Brasil - Período Colonial"

id: 2
question_id: 1
topic_text: "Economia Açucareira"

id: 3
question_id: 5
topic_text: "História do Brasil - Período Colonial"
```

---

## 5. Tabela: user_stats_daily

**Descrição:** Armazena estatísticas diárias de desempenho dos usuários. Permite rastrear o progresso diário de cada usuário.

**Nome da Tabela:** `user_stats_daily`  
**Sinonímia:** `estatisticas_diarias`, `stats_diarias`

### Estrutura da Tabela

| # | Nome do Campo | Tipo de Dado | Tamanho | Null? | Chave | Default | Descrição |
|---|---------------|--------------|---------|-------|-------|---------|-----------|
| 1 | id | BIGINT | - | NÃO | PK | AUTO_INCREMENT | Identificador único do registro (chave primária) |
| 2 | id_user | BIGINT | - | NÃO | FK | - | Referência ao usuário (chave estrangeira para Usuario.id_user) |
| 3 | data | DATE | - | NÃO | UK | - | Data das estatísticas (formato: YYYY-MM-DD) |
| 4 | quest_feitas | INTEGER | - | SIM | - | 0 | Quantidade de questões feitas no dia |
| 5 | quest_certas | INTEGER | - | SIM | - | 0 | Quantidade de questões corretas no dia |
| 6 | quest_erradas | INTEGER | - | SIM | - | 0 | Quantidade de questões erradas no dia |

### Constraints

- **PRIMARY KEY:** `id`
- **FOREIGN KEY:** `id_user` → `Usuario.id_user` (ON DELETE CASCADE)
- **UNIQUE:** `(id_user, data)` - Um usuário pode ter apenas um registro por dia
- **NOT NULL:** `id`, `id_user`, `data`
- **DEFAULT:** `quest_feitas`, `quest_certas`, `quest_erradas` = 0

### Regras de Negócio

1. **Constraint UNIQUE:** Um usuário pode ter apenas um registro por dia. Se tentar inserir outro registro para o mesmo usuário e data, o sistema atualiza o registro existente (ON CONFLICT DO UPDATE)
2. A soma de `quest_certas` + `quest_erradas` deve ser igual a `quest_feitas`
3. Quando um usuário é deletado, todas suas estatísticas diárias são deletadas automaticamente (CASCADE)
4. O campo `data` armazena apenas a data (sem hora), usando o tipo DATE do PostgreSQL
5. O sistema usa `CURRENT_DATE` para inserir automaticamente a data atual quando não especificada

### Exemplo de Dados

```
id: 1
id_user: 1
data: 2024-01-15
quest_feitas: 20
quest_certas: 15
quest_erradas: 5

id: 2
id_user: 1
data: 2024-01-16
quest_feitas: 25
quest_certas: 18
quest_erradas: 7
```

---

## Relacionamentos

**A estrutura de relacionamentos abaixo apresenta de forma resumida como as tabelas se conectam entre si.**

### Diagrama de Relacionamentos

```
Usuario (1) ────────< (N) user_stats_daily
  │
  │ (1)
  │
  └─── id_user (FK)

questions (1) ────────< (N) alternatives
  │
  │ (1)
  │
  └─── id (FK: question_id)

questions (1) ────────< (N) topics
  │
  │ (1)
  │
  └─── id (FK: question_id)
```

### Detalhamento dos Relacionamentos

#### 1. Usuario ↔ user_stats_daily
- **Tipo:** Um para Muitos (1:N)
- **Cardinalidade:** Um usuário pode ter múltiplas estatísticas diárias
- **Chave Estrangeira:** `user_stats_daily.id_user` → `Usuario.id_user`
- **Comportamento:** CASCADE (quando usuário é deletado, estatísticas são deletadas)
- **Constraint:** UNIQUE(id_user, data) - um registro por usuário por dia

#### 2. questions ↔ alternatives
- **Tipo:** Um para Muitos (1:N)
- **Cardinalidade:** Uma questão possui múltiplas alternativas (5 alternativas: A, B, C, D, E)
- **Chave Estrangeira:** `alternatives.question_id` → `questions.id`
- **Comportamento:** CASCADE (quando questão é deletada, alternativas são deletadas)

#### 3. questions ↔ topics
- **Tipo:** Um para Muitos (1:N)
- **Cardinalidade:** Uma questão pode ter múltiplos tópicos associados
- **Chave Estrangeira:** `topics.question_id` → `questions.id`
- **Comportamento:** CASCADE (quando questão é deletada, tópicos são deletados)

---

## Índices

### Índices Primários (Criados Automaticamente)

| Tabela | Índice | Coluna(s) | Tipo |
|--------|--------|-----------|------|
| Usuario | PK_Usuario | id_user | PRIMARY KEY |
| questions | PK_questions | id | PRIMARY KEY |
| alternatives | PK_alternatives | id | PRIMARY KEY |
| topics | PK_topics | id | PRIMARY KEY |
| user_stats_daily | PK_user_stats_daily | id | PRIMARY KEY |

### Índices Únicos

| Tabela | Índice | Coluna(s) | Tipo |
|--------|--------|-----------|------|
| Usuario | UK_Usuario_nome | nome | UNIQUE |
| user_stats_daily | UK_user_stats_daily | (id_user, data) | UNIQUE |

### Índices Recomendados (Para Performance)

| Tabela | Coluna(s) | Motivo |
|--------|-----------|--------|
| alternatives | question_id | Busca frequente de alternativas por questão |
| topics | question_id | Busca frequente de tópicos por questão |
| user_stats_daily | id_user | Busca de estatísticas por usuário |
| user_stats_daily | data | Busca de estatísticas por data |
| questions | index_number | Busca de questões por número |
| questions | discipline | Busca de questões por disciplina |
| questions | year | Busca de questões por ano |

**Script SQL para criar índices recomendados:**

```sql
-- Índices para melhorar performance
CREATE INDEX idx_alternatives_question_id ON alternatives(question_id);
CREATE INDEX idx_topics_question_id ON topics(question_id);
CREATE INDEX idx_user_stats_daily_id_user ON user_stats_daily(id_user);
CREATE INDEX idx_user_stats_daily_data ON user_stats_daily(data);
CREATE INDEX idx_questions_index_number ON questions(index_number);
CREATE INDEX idx_questions_discipline ON questions(discipline);
CREATE INDEX idx_questions_year ON questions(year);
```

---

## Constraints

### Resumo de Constraints por Tabela

#### Tabela: Usuario
- **PRIMARY KEY:** `id_user`
- **UNIQUE:** `nome`
- **NOT NULL:** `id_user`, `nome`, `senha`, `email`
- **CHECK:** (implícito no código) `nome.length() > 4`, `senha.length() > 4`

#### Tabela: questions
- **PRIMARY KEY:** `id`
- **NOT NULL:** `id`, `index_number`
- **CHECK:** (recomendado) `index_number BETWEEN 1 AND 180`
- **CHECK:** (recomendado) `correct_alternative IN ('A', 'B', 'C', 'D', 'E')`
- **CHECK:** (recomendado) `discipline IN ('ciencias-humanas', 'ciencias-natureza', 'linguagens', 'matematica')`

#### Tabela: alternatives
- **PRIMARY KEY:** `id`
- **FOREIGN KEY:** `question_id` → `questions.id` (ON DELETE CASCADE)
- **NOT NULL:** `id`, `question_id`
- **CHECK:** (recomendado) `letter IN ('A', 'B', 'C', 'D', 'E')`
- **UNIQUE:** (recomendado) `(question_id, letter)` - uma letra por questão

#### Tabela: topics
- **PRIMARY KEY:** `id`
- **FOREIGN KEY:** `question_id` → `questions.id` (ON DELETE CASCADE)
- **NOT NULL:** `id`, `question_id`

#### Tabela: user_stats_daily
- **PRIMARY KEY:** `id`
- **FOREIGN KEY:** `id_user` → `Usuario.id_user` (ON DELETE CASCADE)
- **UNIQUE:** `(id_user, data)`
- **NOT NULL:** `id`, `id_user`, `data`
- **CHECK:** (recomendado) `quest_feitas = quest_certas + quest_erradas`

---

## Scripts SQL de Referência

### Criação das Tabelas

```sql
-- Tabela Usuario
CREATE TABLE Usuario (
    id_user BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    quest_feitas INTEGER DEFAULT 0,
    quest_certas INTEGER DEFAULT 0,
    quest_erradas INTEGER DEFAULT 0,
    acertos_humanas INTEGER DEFAULT 0,
    erros_humanas INTEGER DEFAULT 0,
    acertos_linguagem INTEGER DEFAULT 0,
    erros_linguagem INTEGER DEFAULT 0,
    acertos_mat INTEGER DEFAULT 0,
    erros_mat INTEGER DEFAULT 0,
    acertos_nat INTEGER DEFAULT 0,
    erros_nat INTEGER DEFAULT 0
);

-- Tabela questions
CREATE TABLE questions (
    id BIGSERIAL PRIMARY KEY,
    index_number INTEGER NOT NULL,
    title TEXT,
    discipline VARCHAR(255),
    language VARCHAR(50),
    year INTEGER,
    context TEXT,
    correct_alternative VARCHAR(1),
    files TEXT[]
);

-- Tabela alternatives
CREATE TABLE alternatives (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL,
    letter VARCHAR(1),
    text TEXT,
    is_correct BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- Tabela topics
CREATE TABLE topics (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL,
    topic_text VARCHAR(255),
    FOREIGN KEY (question_id) REFERENCES questions(id) ON DELETE CASCADE
);

-- Tabela user_stats_daily
CREATE TABLE user_stats_daily (
    id BIGSERIAL PRIMARY KEY,
    id_user BIGINT NOT NULL,
    data DATE NOT NULL,
    quest_feitas INTEGER DEFAULT 0,
    quest_certas INTEGER DEFAULT 0,
    quest_erradas INTEGER DEFAULT 0,
    FOREIGN KEY (id_user) REFERENCES Usuario(id_user) ON DELETE CASCADE,
    UNIQUE(id_user, data)
);
```

---

## Glossário de Termos

| Termo | Definição |
|-------|-----------|
| **BIGINT** | Tipo de dado numérico inteiro grande (64 bits) |
| **BIGSERIAL** | Tipo de dado auto-incrementável grande (equivalente a BIGINT com AUTO_INCREMENT) |
| **VARCHAR(n)** | Tipo de dado texto com tamanho variável até n caracteres |
| **TEXT** | Tipo de dado texto sem limite de tamanho |
| **INTEGER** | Tipo de dado numérico inteiro (32 bits) |
| **BOOLEAN** | Tipo de dado lógico (true/false) |
| **DATE** | Tipo de dado para armazenar datas (sem hora) |
| **TEXT[]** | Array de strings no PostgreSQL |
| **PRIMARY KEY (PK)** | Chave primária - identifica unicamente cada registro |
| **FOREIGN KEY (FK)** | Chave estrangeira - referencia uma chave primária de outra tabela |
| **UNIQUE** | Constraint que garante valores únicos na coluna ou conjunto de colunas |
| **NOT NULL** | Constraint que impede valores nulos |
| **CASCADE** | Comportamento de deleção em cascata - quando registro pai é deletado, registros filhos também são deletados |
| **AUTO_INCREMENT** | Valor gerado automaticamente pelo banco de dados |

---

## Histórico de Alterações

| Data | Versão | Alteração | Autor |
|------|--------|-----------|-------|
| 2025 | 1.0 | Criação inicial do dicionário de dados | Gustavo Martins |

---

**Fim do Dicionário de Dados**

