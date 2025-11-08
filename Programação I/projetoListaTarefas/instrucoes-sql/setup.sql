-- ===================================================================
-- SCRIPT DE CRIAÇÃO DO BANCO DE DADOS "gerenciador_tarefas"
-- Este script:
-- 1. Exclui as tabelas existentes (para uma recriação limpa).
-- 2. Cria as tabelas 'prioridade', 'responsavel' e 'listatarefas'.
-- 3. Define as chaves primárias e estrangeiras.
-- 4. Insere alguns dados de exemplo.
-- ===================================================================

-- ETAPA 1: EXCLUIR TABELAS EXISTENTES (em ordem inversa de dependência)
-- Excluímos 'listatarefas' primeiro, pois ela depende das outras.
DROP TABLE IF EXISTS listatarefas;
DROP TABLE IF EXISTS prioridade;
DROP TABLE IF EXISTS responsavel;


-- ETAPA 2: CRIAR TABELAS PRINCIPAIS (as que não dependem de ninguém)

CREATE TABLE prioridade (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(100) NOT NULL
);

CREATE TABLE responsavel (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(255) NOT NULL
);


-- ETAPA 3: CRIAR TABELA DEPENDENTE ('listatarefas')

CREATE TABLE listatarefas (
    id SERIAL PRIMARY KEY,
    data_tarefa DATE,
    descricao_tarefa TEXT,
    observacao TEXT,
    
    -- Chave Estrangeira para a tabela PRIORIDADE
    prioridade_id INTEGER,
    CONSTRAINT fk_prioridade
        FOREIGN KEY(prioridade_id) 
        REFERENCES prioridade(id),

    -- Chave Estrangeira para a tabela RESPONSAVEL
    responsavel_id INTEGER,
    CONSTRAINT fk_responsavel
        FOREIGN KEY(responsavel_id) 
        REFERENCES responsavel(id)
);


-- ETAPA 4: INSERIR DADOS DE EXEMPLO (OPCIONAL, mas recomendado)

-- Inserindo prioridades
-- O 'SERIAL' cuidará dos IDs (1, 2, 3...)
INSERT INTO prioridade (descricao) VALUES
('Alta'),
('Normal'),
('Baixa');

-- Inserindo responsáveis
INSERT INTO responsavel (nome) VALUES
('Ana Souza'),
('Bruno Mendes'),
('Equipe Infra');

-- Inserindo uma tarefa de exemplo
-- Vamos supor que 'Alta' é ID 1 e 'Ana Souza' é ID 1
INSERT INTO listatarefas (data_tarefa, descricao_tarefa, observacao, prioridade_id, responsavel_id) VALUES
('2025-10-28', 'Configurar firewall principal', 'Verificar regras da VLAN 10', 1, 1),
('2025-10-29', 'Atualizar servidor web', 'Aplicar patches de segurança', 2, 2);


-- Mensagem de conclusão
SELECT 'Script executado com sucesso!' AS status;