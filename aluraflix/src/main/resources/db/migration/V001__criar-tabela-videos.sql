CREATE TABLE video (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    titulo      VARCHAR(100) NOT NULL,
    descricao   VARCHAR(100) NOT NULL,
    url         VARCHAR(100) NOT NULL,

    PRIMARY KEY(id)
) engine=InnoDB default charset=utf8;