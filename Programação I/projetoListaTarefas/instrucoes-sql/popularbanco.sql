TRUNCATE TABLE prioridade, responsavel, listatarefas RESTART IDENTITY CASCADE;

INSERT INTO prioridade (descricao) VALUES
('Crítica'),
('Alta'),
('Média'),
('Baixa'),
('Planejada');

INSERT INTO responsavel (nome) VALUES
('Ana Clara Borges'),
('Bruno Cabral'),
('Carla Dias'),
('Daniel Evangelista'),
('Elisa Fernandes'),
('Fábio Guedes'),
('Gabriela Herrera'),
('Hugo Ibarra'),
('Íris Justo'),
('Júlio Kerman'),
('Larissa Matos'),
('Marcos Neves'),
('Olívia Pinheiro'),
('Paulo Queiroz'),
('Raquel Silveira');

INSERT INTO listatarefas (
    data_tarefa, 
    descricao_tarefa, 
    observacao, 
    prioridade_id, 
    responsavel_id
)
SELECT
    CURRENT_DATE - (floor(random() * 180) * interval '1 day'),
    
    (ARRAY['Revisar', 'Implementar', 'Atualizar', 'Corrigir', 'Testar', 'Documentar', 'Otimizar', 'Remover'])[floor(random() * 8 + 1)] || ' ' || 
    (ARRAY['módulo de pagamento', 'API de usuários', 'cache do sistema', 'deploy', 'relatório financeiro', 'fluxo de login', 'script de build', 'bug #'])[floor(random() * 8 + 1)] || 
    CAST(floor(random() * 100 + 1) AS TEXT) AS descricao_tarefa,
    
    (ARRAY['Pendente de aprovação', 'Concluído', 'Em andamento', 'Bloqueado', 'Requer mais testes', ''])[floor(random() * 6 + 1)] AS observacao,
    
    floor(random() * 5 + 1) AS prioridade_id,
    
    floor(random() * 15 + 1) AS responsavel_id
    
FROM generate_series(1, 350);

SELECT 'Banco de dados populado com 370 registros!' AS status;