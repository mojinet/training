--PARTIE 1
-- a : liste des clients dont le nom commence par "D"
SELECT * 
	FROM Clients
	WHERE nom LIKE 'D%'; -- '%D' = finis par D

-- b : Nom et prénom de tout les clients 
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
--d : Détail de la fiches N°1002
SELECT f.noFic AS [Fiche N°], 
	c.nom AS [Nom Client], c.prenom AS [Prenom Client],
	a.refart AS Référence, a.designation, 
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
--f : liste des articles qui ont été loués au moins 3 fois
SELECT a.refart AS Référence, a.designation,
	COUNT(lf.refart)
	FROM articles a
		INNER JOIN lignesFic lf ON lf.refart = a.refart
	--GROUP BY a.refart

--g : recupere le resultat de la d dans une table local puis
-- faire un SUM() du montant total

--PARTIE 2
--1 : Liste des clients (nom, prénom, adresse, code postal, ville) ayant au moins une fiche de location en cours.
SELECT nom, prenom, adresse,cpo, ville
	FROM Clients c
		INNER JOIN Fiches f ON f.noCli = c.noCli
	WHERE f.etat = 'EC'

--2 Détail de la fiche de Dupond jean de paris (deisgnation des articles, date de depart et de retour)
SELECT c.nom + ' ' + c.prenom as Client,
	a.designation,
	FORMAT(lf.depart,'dd/MM/yyy') as [Date départ], FORMAT(lf.retour,'dd/MM/yyy') as [Date retour]
	FROM clients c
		INNER JOIN Fiches f ON f.noCli = c.noCli
		INNER JOIN lignesFic lf ON lf.noFic = f.noFic
		INNER JOIN articles a ON a.refart = lf.refart
	WHERE c.nom = 'Dupond' AND c.prenom = 'JEAN'

--3 listes de TOUT les articles dont le libelle de la catégorie contient 'ski'
SELECT a.refart, a.designation, a.codeCate, a.codeCate,
	c.libelle AS [Contient le mot SKI :]
	FROM articles a
		INNER JOIN grilleTarifs gt ON gt.codeCate = a.codeCate AND gt.codeGam = a.codeGam
		INNER JOIN categories c ON c.codeCate = gt.codeCate
	WHERE libelle LIKE 'SKI%'

--4 calcul du montant de chaque fiche soldée et du montant total des fiches
SELECT f.noFic AS [Numéro de fiche],
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

--5 Calcul du nombre d’articles actuellement en cours de location

--6  Calcul du nombre d’articles loués, par client

--7 Liste des clients qui ont effectué (ou sont en train d’effectuer) plus de 200€ de location

--8 Liste de tous les articles (loués au moins une fois) et le nombre de fois où ils ont été loués, triés du plus loué au moins loué.
