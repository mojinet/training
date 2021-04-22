---------------------------------------------------------
-- Mes solution : https://fxjollois.github.io/cours-sql/
---------------------------------------------------------
---------------------------------------------------------
-- 1 - "Requetage simple"
---------------------------------------------------------
--Partie 2 : Lister les trois produits les plus chers de la table Produit,
SELECT * 
FROM produit
ORDER BY prixunit DESC
LIMIT 3

--Partie 3
--a : Lister les clients fran�ais install�s � Paris
SELECT * 
FROM client
WHERE Pays LIKE "France" AND ville LIKE "Paris"

-- b : Lister les clients suisses, allemands et belges
SELECT * 
FROM client
WHERE Pays IN ('Suisse', 'Allemagne', 'Belgique')

-- c : Lister les clients dont le num�ro de fax n'est pas renseign�
SELECT * 
FROM client
WHERE fax IS NULL

-- d : Lister les clients dont le nom contient "restaurant" (nom pr�sent dans la colonne Societe)
SELECT * 
FROM client
WHERE Societe LIKE '%restaurant%'

--Partie 4
-- a : Lister uniquement la description des cat�gories de produits (table Categorie)
SELECT description 
FROM categorie

-- b : Lister les diff�rents pays des clients
SELECT DISTINCT pays
FROM client

-- c : Idem en ajoutant les villes, le tout tri� par ordre alphab�tique du pays et de la ville
SELECT DISTINCT pays, ville
FROM client
ORDER BY pays, ville

--Partie 5
-- a : Lister tous les produits vendus en bouteilles ou en canettes
SELECT *
FROM produit
WHERE qteparunit LIKE '%bouteille%' OR qteparunit LIKE '%canette%'

-- b : Lister les fournisseurs fran�ais, en affichant uniquement le nom, le contact et la ville, tri�s par ville
SELECT societe, contact, ville
FROM fournisseur
WHERE pays = 'France'
ORDER BY ville

-- c :  Lister les produits (nom en majuscule et r�f�rence) du fournisseur n� 8 dont le prix unitaire est entre 10 et 100 euros, en renommant les attributs pour que ce soit explicite
SELECT UPPER(nomprod) AS 'Nom produit', refprod AS Reference
FROM produit
WHERE nofour = 8 AND prixUnit BETWEEN 10 AND 100

-- d : Lister les num�ros d'employ�s ayant r�alis� une commande (cf table Commande) � livrer en France, � Lille, Lyon ou Nantes
SELECT noEmp AS 'numero d''employe'
FROM commande
WHERE paysliv = 'France' AND villeliv IN ('Lille', 'Lyon', 'Nantes')

-- e : Lister les produits dont le nom contient le terme "tofu" ou le terme "choco", dont le prix est inf�rieur � 100 euros (attention � la condition � �crire)
SELECT *
FROM produit
WHERE nomprod LIKE '%tofu%' OR nomprod LIKE '%choco%'

---------------------------------------------------------
-- 2 - "Calculs et fonctions"
---------------------------------------------------------
--Partie 1
SELECT 
    refprod AS R�ference, 
    PrixUnit AS "Prix unitaire" , 
    qte AS Quantit�, 
    ROUND((qte*prixunit)*remise,2) AS Remise,
    qte * prixunit - remise AS "Montant � payer"
FROM DetailCommande
WHERE NoCom = 10251

--Partie 2
SELECT 
    (adresse ||' '|| ville || ' ' || codepostal || ' ' || pays) AS Adresse,
    SUBSTR(codecli, LENGTH(codeCli) - 2 , 2) AS "Short Code Client",
    UPPER(societe) AS Soci�t�,
    REPLACE(Fonction, "marketing","mercatique") AS "Fonction des contacts",
    REPLACE(INSTR(Fonction, "Chef"),1,"Chef") AS Chef
FROM Client

--Partie 3 : date
-- a : Calculer pour chaque commande le jour de la semaine, le num�ro de semaine dans l'ann�e et le mois
SELECT 
    nocom AS numero,
    datecom AS 'date de commande',
    strftime('%d', datecom) AS jour,
    strftime('%W', datecom) AS semaine,
    strftime('%m', datecom) AS mois
FROM commande

-- b : Lister les commandes ayant eu lieu un dimanche
SELECT 
    nocom, 
    datecom,
    STRFTIME('%w', datecom) AS 'jour de la semaine'
FROM commande
WHERE STRFTIME('%w', datecom) = '6'

-- c : Calculer le nombre de jours entre la date de la commande (DateCom) et la date butoir de livraison (ALivAvant), pour chaque commande
SELECT 
    nocom as 'test 2', 
    strftime('%d/%m/%Y',datecom) AS 'date de commande',
    strftime('%d/%m/%Y',alivavant) AS 'date butoir',
    ( (strftime('%s', alivavant) - strftime('%s',datecom)) / (24*60*60) ) AS 'Nombre de jour avant date butoir'
FROM commande
ORDER BY "Nombre de jour avant date butoir"

-- d : On souhaite aussi contacter les clients 1 an, 1 mois et 1 semaine apr�s leur commande. Calculer les dates correspondantes pour chaque commande
SELECT 
    nocom,
    strftime('%d/%m/%Y', datecom) AS 'Date de commande',
    strftime('%d/%m/%Y', datecom, '+1 day', '+1 month', '+1 year') AS "a contacter le"
FROM commande

--Partie 4 
-- a :A partir de la table Produit, afficher "Produit non disponible" lorsque l'attribut Indisponible vaut 1, et "Produit disponible" sinon.
SELECT refprod,
    CASE indisponible
        WHEN 1 THEN "Produit non disponible"
        ELSE "Produit disponible"
    END AS Disponibilit�
FROM produit

-- b :Dans la table DetailCommande, indiquer les infos suivantes en fonction de la remise
	-- si elle vaut 0 : "aucune remise"
	-- si elle vaut entre 1 et 5% (inclus) : "petite remise"
	-- si elle vaut entre 6 et 15% (inclus) : "remise mod�r�e"
	-- sinon :"remise importante"
SELECT *,
    CASE 
        WHEN remise = 0 THEN "Pas de remise"
        WHEN remise BETWEEN 0.01 AND 0.05 THEN "Petite remise"
        WHEN remise BETWEEN 0.06 AND 0.15 THEN "Remise modere"
        ELSE "Remise importante"
    END AS remise
FROM detailcommande

-- c : Indiquer pour les commandes envoy�es si elles ont �t� envoy�es en retard (date d'envoi DateEnv sup�rieure (ou �gale) � la date butoir ALivAvant) ou � temps
SELECT noCom AS 'Commande N�',
    CASE
        WHEN dateEnv >= aLivAvant THEN "Retard"
        ELSE "Pas de retard"
    END AS "Retard"
FROM commande
ORDER BY retard DESC

--Partie 5 : TODO
-- a :R�cup�rer l'ann�e de naissance et l'ann�e d'embauche des employ�s

-- b :Calculer � l'aide de la requ�te pr�c�dente l'�ge d'embauche et le nombre d'ann�es dans l'entreprise

-- c : Afficher le prix unitaire original, la remise en pourcentage, le montant de la remise et le prix unitaire avec remise (tous deux arrondis aux centimes), pour les lignes de commande dont la remise est strictement sup�rieure � 10%

-- d :Calculer le d�lai d'envoi (en jours) pour les commandes dont l'envoi est apr�s la date butoir, ainsi que le nombre de jours de retard

-- e :Rechercher les soci�t�s clientes, dont le nom de la soci�t� contient le nom du contact de celle-ci

---------------------------------------------------------
-- 3 - Agr�gat
---------------------------------------------------------
--Partie 1
-- a : Calculer le nombre d'employ�s qui sont "Repr�sentant(e)"
SELECT Count(*) AS Total
FROM employe
WHERE fonction LIKE '%repr�sentant%'

-- b : Calculer le nombre de produits de moins de 50 euros
SELECT Count(*) AS Total
FROM produit
WHERE prixunit > 50

-- c :  Calculer le nombre de produits de cat�gorie 2 et avec plus de 10 unit�s en stocks
SELECT Count(*) AS Total
FROM produit
WHERE codecateg = 2 AND unitesStock > 10

-- d : Calculer le nombre de produits de cat�gorie 1, des fournisseurs 1 et 18
SELECT Count(*) AS Total
FROM produit
WHERE codecateg = 1 AND noFour IN (1, 18)

-- e : Calculer le nombre de pays diff�rents de livraison
SELECT Count(DISTINCT paysLiv) AS Total
FROM commande

-- f : Calculer le nombre de commandes r�alis�es le 28/03/2016.
SELECT 
    COUNT(*) AS total, 
    datecom AS date
FROM commande
WHERE datecom = '2016-03-28'

--Partie 2
-- a : Calculer le co�t moyen du port pour les commandes du client dont le code est "QUICK" (attribut CodeCli)
SELECT ROUND(AVG(Port), 2) AS "Cout moyen du port"
FROM commande
WHERE codeCLi = "QUICK"

-- b : Calculer le co�t du port minimum et maximum des commandes
SELECT
    MIN(Port) AS Minimum,
    MAX(Port) AS Maximum
FROM commande

-- c : Pour chaque messager (par leur num�ro : 1, 2 et 3), donner le montant total des frais de port leur correspondant
	-- il faut faire 3 requ�tes diff�rentes donc
SELECT
    SUM(port)
FROM commande
WHERE nomess = 3 -- idem avec 1 & 2

--Partie 3
-- a : Donner le nombre d'employ�s par fonction
SELECT
    fonction,
    COUNT(*) AS TOTAL
FROM employe
GROUP BY fonction

-- b : Donner le montant moyen du port par messager
SELECT 
    noMess AS Messager,
    ROUND(AVG(port),2) AS "cout moyen du port"
FROM commande
GROUP BY messager

-- c : Donner le nombre de cat�gories de produits fournis par chaque fournisseur
SELECT
    NoFour AS Fournisseur,
    COUNT(DISTINCT codeCateg) AS "Nombre Cat�gorie"
FROM produit
GROUP BY noFour

-- d : Donner le prix moyen des produits pour chaque fournisseur et chaque cat�gorie de produits fournis par celui-ci
SELECT
    NoFour AS Fournisseur,
    codeCateg AS Categorie,
    ROUND(AVG(prixUnit),2) AS "Prix Moyen"
FROM produit
GROUP BY noFour, codecateg

--Partie 4
-- a : Lister les fournisseurs ne fournissant qu'un seul produit
SELECT
    nofour AS Fournisseur,
    COUNT(*) AS TOTAL
FROM produit
GROUP BY noFour
HAVING total = 1

-- b : Lister les cat�gories dont les prix sont en moyenne sup�rieurs strictement � 150 euros
SELECT
    codecateg AS Categorie,
    ROUND(AVG(prixunit),2) AS moyenne
FROM produit
GROUP BY codecateg
HAVING moyenne > 150

-- c :  Lister les fournisseurs ne fournissant qu'une seule cat�gorie de produits
SELECT
    noFour AS Fournisseur,
    COUNT(DISTINCT codeCateg) AS TOTAL
FROM produit
GROUP BY noFour
HAVING total = 1

-- d : Lister les fonctions pour lesquelles la moyenne d'�ge des employ�s d�passe les 45 ans

--Partie 5

---------------------------------------------------------
-- 4 - 
---------------------------------------------------------
--Partie 1
-- a :
-- b :
-- c : 
-- d :
-- e :
-- f :
--Partie 2
--Partie 3
--Partie 4
--Partie 5

---------------------------------------------------------
-- 5 - 
---------------------------------------------------------
--Partie 1
--Partie 2
--Partie 3
--Partie 4
--Partie 5

---------------------------------------------------------
-- 6 - 
---------------------------------------------------------
--Partie 1
--Partie 2
--Partie 3
--Partie 4
--Partie 5

---------------------------------------------------------
-- Recapitulatif 1 
---------------------------------------------------------
--Partie 1
--Partie 2
--Partie 3
--Partie 4
--Partie 5

---------------------------------------------------------
-- Recapitulatif 2
---------------------------------------------------------
--Partie 1
--Partie 2
--Partie 3
--Partie 4
--Partie 5