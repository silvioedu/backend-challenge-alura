set foreign_key_checks = 0;

delete from video;

set foreign_key_checks = 1;

alter table video auto_increment = 1;

insert into video values
(1, 'Video 001', 'Descrição do vídeo 001', 'http://video001.com'),
(2, 'Video 002', 'Descrição do vídeo 002', 'http://video002.com'),
(3, 'Video 003', 'Descrição do vídeo 003', 'http://video003.com'),
(4, 'Video 004', 'Descrição do vídeo 004', 'http://video004.com'),
(5, 'Video 005', 'Descrição do vídeo 005', 'http://video005.com');