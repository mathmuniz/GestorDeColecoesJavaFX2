# GestorDeColecoesJavaFX2

para executar instalar o SceneBuilder, netbeans, connector mysql


### Criação das tabelas:
create table skin
(
    id int(6) not null primary key auto_increment,
    nome varchar(50),
    colecao varchar(50),
    preco varchar(30),
    status varchar(30),
    foto varchar(50)
)
