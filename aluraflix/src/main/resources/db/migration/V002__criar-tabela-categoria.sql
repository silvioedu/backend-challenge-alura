CREATE TABLE categoria (
    id          BIGINT NOT NULL AUTO_INCREMENT,
    titulo      VARCHAR(100) NOT NULL,
    cor         VARCHAR(50) NOT NULL,

    PRIMARY KEY(id)
) engine=InnoDB default charset=utf8;