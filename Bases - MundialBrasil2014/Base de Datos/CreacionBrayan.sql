/*

Creación de la base de Datos
create database Copamundialfifa2014;

Creación de tablas
*/
create table Grupo (
idGrupo text not null,
equipo1 text not null,
equipo2 text not null,
equipo3 text not null,
equipo4 text not null,
primary key (idGrupo)
);

create table Equipos (
nombre text not null,
posicion numeric(1,0) not null,
partidosJugados numeric(1,0) not null,
victorias numeric(1,0) not null,
empates numeric(1,0) not null,
derrotas numeric(1,0) not null,
golesAFavor numeric(2,0) not null,
golesEnContra numeric(2,0) not null,
diferenciaGoles numeric(2,0) not null,
puntos numeric (1,0) not null,
grupo text not null,
primary key(nombre)   
);

create table CalendarioPartidos (
id numeric (2,0) not null,
orden numeric (1,0) not null,
equipo1 text not null,
equipo2 text not null,
grupo text not null,
golesEquipo1 numeric(1,0) not null,
golesEquipo2 numeric(1,0) not null,
primary key(id)
);

create table Bitácora (
id numeric (3,0) not null,
tipoEvento text not null,
fechaHora timestamp not null,
resultado text,
primary key(id)
);

/*
Creación de la vista
*/
create view TablaGeneral as
select Equipos.posicion as Posicion, Equipos.nombre as Equipo, Equipos.partidosJugados as PJ, Equipos.victorias as PG, 
Equipos.empates as PE, Equipos.derrotas as PP, Equipos.golesAFavor as GF, Equipos.golesEnContra as GC, Equipos.puntos as Pts
from Equipos;

/*
Creación de secuencia
*/
create sequence secBitácora
  increment 1
  minvalue 000
  maxvalue 999
  start 1
  cache 1;
/*
Creación de la funcion que utiliza el trigger
*/
CREATE FUNCTION bitácora() RETURNS TRIGGER AS $bitacora_tabla$
	DECLARE
	BEGIN
		NEW.id := secbitácora;
		NEW.fechahora := current_timestamp;

		RETURN NEW;
	END;
$bitacora_tabla$  LANGUAGE plpgsql;

/*

Creación del trigger que usa la tabla bitácora
*/
CREATE TRIGGER bitacora BEFORE INSERT ON bitácora
FOR EACH ROW EXECUTE PROCEDURE bitácora();
  
/*
Llaves foráneas.
alter table Grupo add foreign key (equipo1) references Equipos, add foreign key (equipo2) references Equipos, add foreign key (equipo3) references Equipos, 
add foreign key (equipo4) references Equipos;
alter table CalendarioPartidos add foreign key (equipo1) references Equipos, add foreign key (equipo2) references Equipos;
alter table CalendarioPartidos add foreign key (grupo) references Grupo;
alter table Equipos add foreign key (grupo) references Grupo;
*/

