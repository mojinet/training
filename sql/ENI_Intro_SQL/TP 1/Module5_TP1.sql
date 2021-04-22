--Définie sur quel BDD on travail
USE Locations;
GO

--Creation des tables
CREATE TABLE Clients(
	--Définitions des Colonnes
	NoCli	INT			NOT NULL,
	Nom		VARCHAR(30)	NOT NULL,
	Prenom	VARCHAR(30)	NULL,
	Adresse VARCHAR(120)NULL,
	CPo		CHAR(5) 	NOT NULL CONSTRAINT CK_CPo CHECK (CPo BETWEEN 01000 AND 95999),
	Ville	VARCHAR(80) NOT NULL CONSTRAINT DF_Clients_Ville DEFAULT 'Nantes'

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_Clients PRIMARY KEY(NoCli)
);

CREATE TABLE Fiches(
	-- Définitions des Colonnes
	NoFic	 INT		NOT NULL,
	noCli	 INT		NOT NULL,
	DateCrea DATE		NOT NULL CONSTRAINT DF_Fiches_DateCrea DEFAULT getdate(),
	DatePaye DATE		NULL	 CONSTRAINT CK_DatePaye_CompareDate CHECK (DatePaye > DateCrea),
	Etat	 CHAR(2)	NOT NULL 
		CONSTRAINT DF_Fiches_Etat DEFAULT 'EC'
		CONSTRAINT CK_Etat CHECK (Etat = 'EC' OR Etat = 'RE' OR Etat ='SO')

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_Fiches PRIMARY KEY(NoFic)
		--Clé etrangere (FOREIGN KEY)
	CONSTRAINT FK_Fiches_Clients FOREIGN KEY (NoCli) REFERENCES Clients(NoCli) ON DELETE CASCADE
);
CREATE TABLE Gammes(
	--Définitions des Colonnes
	CodeGam INT			 NOT NULL,
	Libelle VARCHAR(30)  NOT NULL

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_Gammes PRIMARY KEY(CodeGam),
		--Clé UNIQUE
	CONSTRAINT UN_Gammes_Libelle UNIQUE(Libelle)
);

CREATE TABLE Categories(
	-- Définitions des Colonnes
	CodeCate CHAR(5)	  NOT NULL,
	Libelle  VARCHAR(30)  NOT NULL

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_Categories PRIMARY KEY(CodeCate),
		--Clé UNIQUE
	CONSTRAINT UN_Categories_Libelle UNIQUE(Libelle)
);

CREATE TABLE Tarifs(
	-- Définitions des Colonnes
	CodeTarif CHAR(5) 	   NOT NULL,
	Libelle	  VARCHAR(30)  NOT NULL,
	PrixJour  DECIMAL(5,2) NOT NULL CONSTRAINT CK_PrixJour CHECK (PrixJour > 0)

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_Tarifs PRIMARY KEY(CodeTarif),
		--Clé UNIQUE
	CONSTRAINT UN_Tarifs_Libelle UNIQUE(Libelle)
);

CREATE TABLE GrilleTarifs(
	-- Définitions des Colonnes
	CodeGam   INT	  NOT NULL,
	CodeCate  CHAR(5) NOT NULL,
	CodeTarif CHAR(5) NOT NULL

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_Gammes PRIMARY KEY(CodeGam, CodeCate)
		--Clé etrangere (FOREIGN KEY)
	CONSTRAINT FK_GrilleTarifs_Gammes     FOREIGN KEY (CodeGam)   REFERENCES Gammes(CodeGam),
	CONSTRAINT FK_GrilleTarifs_Categories FOREIGN KEY (CodeCate)  REFERENCES Categories(CodeCate),
	CONSTRAINT FK_GrilleTarifs_Tarifs     FOREIGN KEY (CodeTarif) REFERENCES Tarifs(CodeTarif)
);

CREATE TABLE Articles(
	--Définitions des Colonnes
	RefArt		CHAR(8)	    NOT NULL,
	Designation VARCHAR(80) NOT NULL,
	CodeGam		INT			NOT NULL,
	CodeCate	INT			NOT NULL

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_Articles PRIMARY KEY(RefArt)
		--Clé etrangere (FOREIGN KEY)
	CONSTRAINT FK_Articles_GrilleTarifs FOREIGN KEY (CodeGam,CodeCate) REFERENCES GrilleTarifs(CodeGam,CodeCate)
);

CREATE TABLE LigneFic(
	--Définitions des Colonnes
	NoFic  INT		   NOT NULL,
	NoLig  INT		   NOT NULL,
	RefArt CHAR(8)     NOT NULL,
	Depart DATE		   NOT NULL CONSTRAINT DF_LigneFic_Depart DEFAULT getdate(),
	Retour DATE		   NOT NULL CONSTRAINT CK_Retour CHECK (Retour > Depart),

	--Définitions des contraintes de table
		--Clé primaire (PRIMARY KEY)
	CONSTRAINT PK_LigneFic PRIMARY KEY(NoFic,NoLig),
		--Clé etrangere (FOREIGN KEY)
	CONSTRAINT FK_LigneFic_Articles FOREIGN KEY (RefArt) REFERENCES Articles(RefArt)
);