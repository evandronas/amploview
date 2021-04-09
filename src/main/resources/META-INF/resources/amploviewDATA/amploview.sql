drop table Sites;
drop table Grupos;
drop table Elementos;
drop table Atributos;
drop table Valores;
drop table Colecoes;
drop table AssociacoesColecaoGrupo;
drop table AssociacoesColecaoColecao;
create table Sites (Id INTEGER PRIMARY KEY AUTOINCREMENT, Descricao TEXT NOT NULL);
create table Grupos (Id INTEGER PRIMARY KEY AUTOINCREMENT, Descricao TEXT NOT NULL, Id_Site INTEGER NOT NULL, FOREIGN KEY(Id_Site) REFERENCES Sites(Id));
create table Elementos (Id INTEGER PRIMARY KEY AUTOINCREMENT, Descricao TEXT NOT NULL, Id_Grupo INTEGER NOT NULL, Id_Site INTEGER NOT NULL, FOREIGN KEY(Id_Grupo) REFERENCES Grupos(Id), FOREIGN KEY(Id_Site) REFERENCES Sites(Id));
create table Atributos (Id INTEGER PRIMARY KEY AUTOINCREMENT, Nome TEXT NOT NULL, Id_Elemento INTEGER NOT NULL, FOREIGN KEY(Id_Elemento) REFERENCES Elementos(Id));
create table Valores (Id INTEGER PRIMARY KEY AUTOINCREMENT, Valor TEXT NOT NULL, Id_Atributo INTEGER NOT NULL, FOREIGN KEY(Id_Atributo) REFERENCES Atributos(Id));
create table Colecoes (Id INTEGER PRIMARY KEY AUTOINCREMENT, Descricao TEXT NOT NULL, Id_Site INTEGER NOT NULL, FOREIGN KEY(Id_Site) REFERENCES Sites(Id));
create table AssociacoesColecaoGrupo (Id INTEGER PRIMARY KEY AUTOINCREMENT, Id_Colecao INTEGER NOT NULL, Id_Grupo INTEGER NOT NULL, FOREIGN KEY(Id_Colecao) REFERENCES Colecoes(Id), FOREIGN KEY(Id_Grupo) REFERENCES Grupos(Id));
create table AssociacoesColecaoColecao (Id INTEGER PRIMARY KEY AUTOINCREMENT, Id_ColecaoPai INTEGER NOT NULL, Id_ColecaoFilho INTEGER NOT NULL, FOREIGN KEY(Id_ColecaoPai) REFERENCES Colecoes(Id), FOREIGN KEY(Id_ColecaoFilho) REFERENCES Colecoes(Id));
DROP INDEX idx_Sites;
DROP INDEX idx_Grupos;
DROP INDEX idx_Elementos;
DROP INDEX idx_Atributos;
DROP INDEX idx_Valores;
DROP INDEX idx_Sites_ids;
DROP INDEX idx_Grupos_ids;
DROP INDEX idx_Elementos_ids;
DROP INDEX idx_Atributos_ids;
DROP INDEX idx_Valores_ids;
DROP INDEX idx_Colecoes;
DROP INDEX idx_Colecoes_ids;
DROP INDEX idx_AssociacoesColecaoGrupo1;
DROP INDEX idx_AssociacoesColecaoGrupo2;
DROP INDEX idx_AssociacoesColecaoColecao1;
DROP INDEX idx_AssociacoesColecaoColecao2;
CREATE UNIQUE INDEX idx_Sites ON Sites (Descricao);
CREATE UNIQUE INDEX idx_Grupos ON Grupos (Descricao, Id_Site);
CREATE UNIQUE INDEX idx_Elementos ON Elementos (Descricao, Id_Site, Id_Grupo);
CREATE UNIQUE INDEX idx_Atributos ON Atributos (Nome, Id_Elemento);
CREATE UNIQUE INDEX idx_Valores ON Valores (Valor, Id_Atributo);
CREATE UNIQUE INDEX idx_Colecoes ON Colecoes (Descricao);
CREATE INDEX idx_Sites_ids ON Sites (Id);
CREATE INDEX idx_Grupos_ids ON Grupos (Id_Site, Id);
CREATE INDEX idx_Elementos_ids ON Elementos (Id_Site, Id_Grupo, Id);
CREATE INDEX idx_Atributos_ids ON Atributos (Id_Elemento, Id);
CREATE INDEX idx_Valores_ids ON Valores (Id_Atributo, Id);
CREATE INDEX idx_Colecoes_ids ON Colecoes (Id_Site, Id);
CREATE UNIQUE INDEX idx_AssociacoesColecaoGrupo1 ON AssociacoesColecaoGrupo (Id_Colecao, Id_Grupo);
CREATE UNIQUE INDEX idx_AssociacoesColecaoGrupo2 ON AssociacoesColecaoGrupo (Id_Grupo, Id_Colecao);
CREATE UNIQUE INDEX idx_AssociacoesColecaoColecao1 ON AssociacoesColecaoColecao (Id_ColecaoPai, Id_ColecaoFilho);
CREATE UNIQUE INDEX idx_AssociacoesColecaoColecao2 ON AssociacoesColecaoColecao (Id_ColecaoFilho, Id_ColecaoPai);
DELETE FROM sqlite_sequence WHERE name = 'Sites';
DELETE FROM sqlite_sequence WHERE name = 'Grupos';
DELETE FROM sqlite_sequence WHERE name = 'Elementos';
DELETE FROM sqlite_sequence WHERE name = 'Atributos';
DELETE FROM sqlite_sequence WHERE name = 'Valores';
DELETE FROM sqlite_sequence WHERE name = 'Colecoes';
DELETE FROM sqlite_sequence WHERE name = 'AssociacaoColecaoGrupo';
DELETE FROM sqlite_sequence WHERE name = 'AssociacaoColecaoColecao';