ALTER TABLE video
ADD id_categoria BIGINT NOT NULL;

ALTER TABLE video
ADD FOREIGN KEY (id_categoria) REFERENCES categoria(id);