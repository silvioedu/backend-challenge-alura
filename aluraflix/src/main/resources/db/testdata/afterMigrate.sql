set foreign_key_checks = 0;

delete from video;
delete from categoria;

set foreign_key_checks = 1;

alter table video auto_increment = 1;
alter table categoria auto_increment = 1;

insert into categoria values
(1, 'LIVRE', '#0A3D94'),
(2, 'Drama', '#E0533D'),
(3, 'Ação', '#266AE0'),
(4, 'Baseados na vida', '#6F9412'),
(5, 'Clássicos', '#6F9412'),
(6, 'Faroeste', '#6F9413'),
(7, 'Comédias românticas', '#6F9414'),
(8, 'Ficção científica', '#6F9415'),
(9, 'Romance', '#6F9416')
;

insert into video values
( 1, 'Um sonho de liberdade', 'Condenado por assassinato, o banqueiro Andy Dufresne vai para a prisão, desenvolve uma forte amizade com um preso mais velho e aprende a navegar o duro clima da prisão.', 'https://www.netflix.com/title/70005379', 2),
( 2, 'O poderoso chefão', 'Uma família mafiosa luta para estabelecer sua supremacia nos Estados Unidos depois da Segunda Guerra Mundial. Uma tentativa de assassinato deixa o chefão Vito Corleone incapacitado e força os filhos Michael e Sonny a assumir os negócios.', 'https://www.netflix.com/title/60011152', 2),
( 3, 'O poderoso chefão II', 'Após a máfia matar sua família, o jovem Vito foge da sua cidade na Sicília e vai para a América.', 'https://www.netflix.com/title/60011663', 2),
( 4, 'Batman - O cavaleiro das trevas', 'Ea pariatur anim sunt id cupidatat pariatur aliqua id proident pariatur cupidatat.', 'https://www.netflix.com/title/70079583', 3),
( 5, '12 Homens e uma Sentença', 'Do adipisicing est sit Lorem culpa ipsum sit voluptate ullamco id occaecat enim.', 'https://www.netflix.com/title/60004251', 3),
( 6, 'A lista de Schindler', 'Ipsum duis veniam quis labore.', 'http://www.netflix.com/title/60036359', 4),
( 7, 'Pulp Fiction - Tempo de violência', 'Adipisicing occaecat sit occaecat pariatur enim quis amet occaecat nisi nulla sit duis.', 'https://www.netflix.com/title/880640', 5),
( 8, 'O senhor dos anéis - O retorno do rei', 'Adipisicing elit excepteur et proident do est duis.', 'https://www.netflix.com/title/60004484', 3),
( 9, 'Três homens em conflito', 'Nulla eiusmod sit velit irure velit enim et eiusmod.', 'http://www.netflix.com/title/553500', 6),
(10, 'Clube da luta', 'Anim tempor incididunt esse cupidatat irure exercitation minim sunt minim ex dolore culpa nisi id.', 'https://www.netflix.com/title/26004747', 3),
(11, 'O senhor dos anéis - A sociedade do anel', 'Sunt adipisicing dolor aliquip ipsum elit irure commodo amet magna.', 'https://www.netflix.com/title/60004480', 8),
(12, 'Forrest Gump - O Contador de histórias', 'Enim mollit eu aliquip esse ea esse deserunt tempor est.', 'http://www.netflix.com/title/60000724', 7),
(13, 'Star Wars - Episódio V', 'Laborum laboris esse exercitation ad veniam.', 'https://www.netflix.com/title/60011114', 8),
(14, 'Inception', 'Tempor adipisicing occaecat amet nostrud aliquip nisi eiusmod fugiat magna ex enim nostrud duis incididunt.', 'http://www.netflix.com/title/70131314', 8),
(15, 'O senhor dos anéis - As duas torres', 'Sit nostrud cupidatat enim nulla magna duis et.', 'http://www.netflix.com/title/60004483', 8),
(16, 'Um estranho no ninho', 'Nulla in deserunt veniam fugiat aute labore laborum dolor do.', 'http://www.netflix.com/title/825812', 5),
(17, 'Os bons companheiros', 'Dolor non incididunt tempor velit sint voluptate magna.', 'https://www.netflix.com/title/70002022', 5),
(18, 'Matrix', 'Ea officia qui in et sint laborum eu mollit veniam aliquip in elit amet.', 'https://www.netflix.com/title/20557937', 8),
(19, 'Os sete samurais', 'Sit culpa labore deserunt occaecat elit et eu nulla eu cupidatat culpa et excepteur cupidatat.', 'http://www.netflix.com/title/950727', 8),
(20, 'Guerra nas estrelas - Uma nova esperança', 'Amet non ex esse minim laborum incididunt.', 'https://www.netflix.com/title/60010932', 8);
