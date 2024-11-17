CREATE TABLE events (
                        id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                        title VARCHAR(255) NOT NULL,
                        description VARCHAR(500),
                        start_time TIMESTAMP WITH TIME ZONE NOT NULL,
                        end_time TIMESTAMP WITH TIME ZONE NOT NULL,
                        location VARCHAR(255),
                        event_type VARCHAR(50) NOT NULL,
                        priority VARCHAR(50) NOT NULL,
                        all_day BOOLEAN NOT NULL DEFAULT FALSE,
                        recurrence_rule TEXT,
                        notes VARCHAR(500),
                        is_completed BOOLEAN NOT NULL DEFAULT FALSE,
                        created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
                        updated_at TIMESTAMP WITH TIME ZONE,
                        deleted_at TIMESTAMP WITH TIME ZONE
);

CREATE TABLE event_tags (
                            event_id UUID NOT NULL REFERENCES events(id) ON DELETE CASCADE,
                            tag VARCHAR(255) NOT NULL,
                            PRIMARY KEY (event_id, tag)
);