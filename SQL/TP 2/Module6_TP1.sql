USE Locations
GO

BEGIN TRAN TR_MAJ;

	INSERT INTO gammes VALUES
		('PR', 'Matériel Professionnel'),
		('HG', 'Haut de gamme'),
		('EG', 'Entree de gamme');

	INSERT INTO Categories VALUES
		('MONO', 'Monoski'),
		('SURF', 'Surf'),
		('PA', 'Patinette'),
		('SA', 'Ski alpin'),
		('FOA', 'Ski de fond alternatif'),
		('FOP', 'Ski de font patineur');

	INSERT INTO tarifs VALUES
		('T1', 'Base', 10),
		('T2', 'Chocolat', 15),
		('T3', 'Bronze', 20),
		('T4', 'Argent', 30),
		('T5', 'Or', 50),
		('T6', 'Platine', 90);

	INSERT INTO GrilleTarifs VALUES
		('EG', 'MONO', 'T1'),
		('EG', 'SURF', 'T1'),
		('HG', 'SURF', 'T3'),
		('PR', 'SURF', 'T5'),
		('EG', 'PA', 'T1'),
		('EG', 'FOA', 'T1'),
		('HG', 'FOA', 'T4'),
		('PR', 'FOA', 'T6'),
		('EG', 'FOP', 'T2'),
		('HG', 'FOP', 'T4'),
		('PR', 'FOP', 'T6'),
		('EG', 'SA', 'T1'),
		('HG', 'SA', 'T4'),
		('PR', 'SA', 'T6');

	INSERT INTO Articles VALUES 
		('F05', 'Fischer Cruiser', 'EG', 'FOA'),
		('F50', 'Fischer SOSSkating VASA', 'HG', 'FOP'),
		('F60', 'Fischer RCS CARBOLITE Skating', 'PR', 'FOP');

	INSERT INTO Clients VALUES 
		(14, 'Boutaud','Sabine', 'Rue des platanes', 75002, 'Paris');

	INSERT INTO Fiches VALUES 
		(1001, 14, GETDATE()-15, GETDATE()-13, 'SO');

	INSERT INTO LignesFic VALUES 
		(1001, 1, 'F05', GETDATE()-15, GETDATE()-13),
		(1001, 2, 'F50', GETDATE()-15, GETDATE()-14),
		(1001, 3, 'F60', GETDATE()-14, GETDATE()-13); -- TODO : 6h apres



COMMIT TRAN TR_MAJ;
--ROLLBACK TRAN TR_MAJ