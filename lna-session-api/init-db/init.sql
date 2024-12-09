-- Create the sessions table
CREATE TABLE sessions
(
    id           UUID PRIMARY KEY                  DEFAULT gen_random_uuid(),
    session_name VARCHAR(255),
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    closed_at    TIMESTAMP WITH TIME ZONE
);

-- Create the messages table
CREATE TABLE messages
(
    id         SERIAL PRIMARY KEY,
    content    TEXT                     NOT NULL,
    role       VARCHAR(10)              NOT NULL CHECK (role IN ('USER', 'SYSTEM')),
    sent_at    TIMESTAMP WITH TIME ZONE NOT NULL,
    agent      VARCHAR(255)             NOT NULL,
    session_id UUID                     NOT NULL,
    CONSTRAINT fk_session FOREIGN KEY (session_id) REFERENCES sessions (id) ON DELETE CASCADE
);

-- Create Sessions
INSERT INTO sessions (id, session_name, created_at, closed_at)
VALUES ('11111111-1111-1111-1111-111111111111', 'Session 1', NOW(), NULL),
       ('22222222-2222-2222-2222-222222222222', 'Session 2', NOW() + INTERVAL '1 day', NULL),
       ('33333333-3333-3333-3333-333333333333', 'Session 3', NOW() + INTERVAL '2 days', NULL),
       ('44444444-4444-4444-4444-444444444444', 'Session 4', NOW() + INTERVAL '3 days', NULL);

-- Add Messages for Each Session
INSERT INTO messages (content, role, sent_at, agent, session_id)
VALUES
    -- Messages for Session 1
    ('User message 1 for Session 1', 'USER', NOW(), 'Agent 1', '11111111-1111-1111-1111-111111111111'),
    ('System response 1 for Session 1', 'SYSTEM', NOW() + INTERVAL '10 minutes', 'Agent 1',
     '11111111-1111-1111-1111-111111111111'),
    ('User message 2 for Session 1', 'USER', NOW() + INTERVAL '20 minutes', 'Agent 1',
     '11111111-1111-1111-1111-111111111111'),
    ('System response 2 for Session 1', 'SYSTEM', NOW() + INTERVAL '30 minutes', 'Agent 1',
     '11111111-1111-1111-1111-111111111111'),
    ('User message 3 for Session 1', 'USER', NOW() + INTERVAL '40 minutes', 'Agent 1',
     '11111111-1111-1111-1111-111111111111'),
    ('System response 3 for Session 1', 'SYSTEM', NOW() + INTERVAL '50 minutes', 'Agent 1',
     '11111111-1111-1111-1111-111111111111'),

    -- Messages for Session 2
    ('User message 1 for Session 2', 'USER', NOW() + INTERVAL '1 day', 'Agent 2',
     '22222222-2222-2222-2222-222222222222'),
    ('System response 1 for Session 2', 'SYSTEM', NOW() + INTERVAL '1 day + 10 minutes', 'Agent 2',
     '22222222-2222-2222-2222-222222222222'),
    ('User message 2 for Session 2', 'USER', NOW() + INTERVAL '1 day + 20 minutes', 'Agent 2',
     '22222222-2222-2222-2222-222222222222'),
    ('System response 2 for Session 2', 'SYSTEM', NOW() + INTERVAL '1 day + 30 minutes', 'Agent 2',
     '22222222-2222-2222-2222-222222222222'),
    ('User message 3 for Session 2', 'USER', NOW() + INTERVAL '1 day + 40 minutes', 'Agent 2',
     '22222222-2222-2222-2222-222222222222'),
    ('System response 3 for Session 2', 'SYSTEM', NOW() + INTERVAL '1 day + 50 minutes', 'Agent 2',
     '22222222-2222-2222-2222-222222222222'),

    -- Messages for Session 3
    ('User message 1 for Session 3', 'USER', NOW() + INTERVAL '2 days', 'Agent 3',
     '33333333-3333-3333-3333-333333333333'),
    ('System response 1 for Session 3', 'SYSTEM', NOW() + INTERVAL '2 days + 10 minutes', 'Agent 3',
     '33333333-3333-3333-3333-333333333333'),
    ('User message 2 for Session 3', 'USER', NOW() + INTERVAL '2 days + 20 minutes', 'Agent 3',
     '33333333-3333-3333-3333-333333333333'),
    ('System response 2 for Session 3', 'SYSTEM', NOW() + INTERVAL '2 days + 30 minutes', 'Agent 3',
     '33333333-3333-3333-3333-333333333333'),
    ('User message 3 for Session 3', 'USER', NOW() + INTERVAL '2 days + 40 minutes', 'Agent 3',
     '33333333-3333-3333-3333-333333333333'),
    ('System response 3 for Session 3', 'SYSTEM', NOW() + INTERVAL '2 days + 50 minutes', 'Agent 3',
     '33333333-3333-3333-3333-333333333333'),

    -- Messages for Session 4
    ('User message 1 for Session 4', 'USER', NOW() + INTERVAL '3 days', 'Agent 4',
     '44444444-4444-4444-4444-444444444444'),
    ('System response 1 for Session 4', 'SYSTEM', NOW() + INTERVAL '3 days + 10 minutes', 'Agent 4',
     '44444444-4444-4444-4444-444444444444'),
    ('User message 2 for Session 4', 'USER', NOW() + INTERVAL '3 days + 20 minutes', 'Agent 4',
     '44444444-4444-4444-4444-444444444444'),
    ('System response 2 for Session 4', 'SYSTEM', NOW() + INTERVAL '3 days + 30 minutes', 'Agent 4',
     '44444444-4444-4444-4444-444444444444'),
    ('User message 3 for Session 4', 'USER', NOW() + INTERVAL '3 days + 40 minutes', 'Agent 4',
     '44444444-4444-4444-4444-444444444444'),
    ('System response 3 for Session 4', 'SYSTEM', NOW() + INTERVAL '3 days + 50 minutes', 'Agent 4',
     '44444444-4444-4444-4444-444444444444');
