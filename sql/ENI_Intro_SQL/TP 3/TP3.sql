--PARTIE 1
-- a : liste des clients dont le nom commence par "D"
SELECT * 
	FROM Clients
	WHERE nom LIKE 'D%'; -- '%D' = finis par D

-- b : Nom et pr�nom de tout les clients 
SELECT nom, prenom
	FROM Clients;

-- c : Liste des fiches pour les clients qui habite en loire atlantique (44)
SELECT f.noFic, f.etat, c.nom AS [Nom Client], c.prenom AS [Prenom Client]
	FROM Fiches f
		INNER JOIN Clients c ON c.noCli = f.noCLi
	WHERE c.CPo LIKE '44%';

---------------------------------------------------------------
-------------------------TODO ERREUR---------------------------
---------------------------------------------------------------
--d : D�tail de la fiches N�1002
SELECT f.noFic AS [Fiche N�], 
	c.nom AS [Nom Client], c.prenom AS [Prenom Client],
	a.refart AS R�f�rence, a.designation, 
	lf.depart, lf.retour,
	t.prixJour AS [Prix par Jour],
	SUM(t.prixJour*( DATEDIFF(day,lf.depart,lf.retour) )) as Montant
	FROM Fiches f
		INNER JOIN Clients c		ON c.noCli = f.noCLi
		INNER JOIN LignesFic lf		ON f.noFic = lf.noFic
		INNER JOIN Articles a		ON a.refart= lf.refart
		INNER JOIN GrilleTarifs gt	ON gt.codeGam = a.codeGam AND gt.codeCate = a.codeCate
		INNER JOIN Tarifs t			ON t.codeTarif = gt.codeTarif
	WHERE f.noFic=1002
	--GROUP BY f.noFic

--e : prix journalier par gamme
SELECT g.libelle AS Gamme,
	ROUND(AVG(t.prixJour),2) AS [Tarif Moyen]
	FROM Gammes g
		INNER JOIN grilleTarifs gt ON gt.codeGam = g.codeGam
		INNER JOIN tarifs t ON t.codeTarif = gt.codeTarif
	GROUP BY g.libelle

---------------------------------------------------------------
-------------------------TODO ERREUR---------------------------
---------------------------------------------------------------
--f : liste des articles qui ont �t� lou�s au moins 3 fois
SELECT a.refart AS R�f�rence, a.designation,
	COUNT(lf.refart)
	FROM articles a
		INNER JOIN lignesFic lf ON lf.refart = a.refart
	--GROUP BY a.refart

--g : recupere le resultat de la d dans une table local puis
-- faire un SUM() du montant total

--PARTIE 2
--1 : Liste des clients (nom, pr�nom, adresse, code postal, ville) ayant au moins une fiche de location en cours.
SELECT nom, prenom, adresse,cpo, ville
	FROM Clients c
		INNER JOIN Fiches f ON f.noCli = c.noCli
	WHERE f.etat = 'EC'

--2 D�tail de la fiche de Dupond jean de paris (deisgnation des articles, date de depart et de retour)
SELECT c.nom + ' ' + c.prenom as Client,
	a.designation,
	FORMAT(lf.depart,'dd/MM/yyy') as [Date d�part], FORMAT(lf.retour,'dd/MM/yyy') as [Date retour]
	FROM clients c
		INNER JOIN Fiches f ON f.noCli = c.noCli
		INNER JOIN lignesFic lf ON lf.noFic = f.noFic
		INNER JOIN articles a ON a.refart = lf.refart
	WHERE c.nom = 'Dupond' AND c.prenom = 'JEAN'

--3 listes de TOUT les articles dont le libelle de la cat�gorie contient 'ski'
SELECT a.refart, a.designation, a.codeCate, a.codeCate,
	c.libelle AS [Contient le mot SKI :]
	FROM articles a
		INNER JOIN grilleTarifs gt ON gt.codeCate = a.codeCate AND gt.codeGam = a.codeGam
		INNER JOIN categories c ON c.codeCate = gt.codeCate
	WHERE libelle LIKE 'SKI%'

--4 calcul du montant de chaque fiche sold�e et du montant total des fiches
SELECT f.noFic AS [Num�ro de fiche],
	SUM(t.prixJour) AS [Somme total]
	INTO #SomFich
	FROM fiches f
		INNER JOIN lignesFic lf ON lf.noFic = f.noFic
		INNER JOIN articles a ON a.refart = lf.refart
		INNER JOIN grilleTarifs gt ON gt.codeCate = a.codeCate AND gt.codeGam = a.codeGam
		INNER JOIN tarifs t ON t.codeTarif = gt.codeTarif
	WHERE f.etat = 'SO'
	GROUP BY f.noFic

SELECT *, SUM([Somme total])
	FROM #SomFich

--5 Calcul du nombre d�articles actuellement en cours de location

--6  Calcul du nombre d�articles lou�s, par client

--7 Liste des clients qui ont effectu� (ou sont en train d�effectuer) plus de 200� de location

--8 Liste de tous les articles (lou�s au moins une fois) et le nombre de fois o� ils ont �t� lou�s, tri�s du plus lou� au moins lou�.
