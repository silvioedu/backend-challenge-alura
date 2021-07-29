set foreign_key_checks = 0;

delete from video;
delete from categoria;

set foreign_key_checks = 1;

alter table video auto_increment = 1;
alter table categoria auto_increment = 1;

insert into categoria values
(1, 'LIVRE', '#0A3D94'),
(2, 'Categoria 002', '#E0533D'),
(3, 'Categoria 003', '#266AE0'),
(4, 'Categoria 004', '#6F9412'),
(5, 'Categoria 005', '#6F9412');

insert into video values
(1, 'Video 001', 'Descrição do vídeo 001', 'http://video001.com', 1),
(2, 'Video 002', 'Descrição do vídeo 002', 'http://video002.com', 2),
(3, 'Video 003', 'Descrição do vídeo 003', 'http://video003.com', 3),
(4, 'Video 004', 'Descrição do vídeo 004', 'http://video004.com', 4),
(5, 'Video 005', 'Descrição do vídeo 005', 'http://video005.com', 1);