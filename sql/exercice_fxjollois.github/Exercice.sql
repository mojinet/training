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
--a : Lister les clients franÃ§ais installÃ©s Ã  Paris
SELECT * 
FROM client
WHERE Pays LIKE "France" AND ville LIKE "Paris"

-- b : Lister les clients suisses, allemands et belges
SELECT * 
FROM client
WHERE Pays IN ('Suisse', 'Allemagne', 'Belgique')

-- c : Lister les clients dont le numÃ©ro de fax n'est pas renseignÃ©
SELECT * 
FROM client
WHERE fax IS NULL

-- d : Lister les clients dont le nom contient "restaurant" (nom prÃ©sent dans la colonne Societe)
SELECT * 
FROM client
WHERE Societe LIKE '%restaurant%'

--Partie 4
-- a : Lister uniquement la description des catÃ©gories de produits (table Categorie)
SELECT description 
FROM categorie

-- b : Lister les diffÃ©rents pays des clients
SELECT DISTINCT pays
FROM client

-- c : Idem en ajoutant les villes, le tout triÃ© par ordre alphabÃ©tique du pays et de la ville
SELECT DISTINCT pays, ville
FROM client
ORDER BY pays, ville

--Partie 5
-- a : Lister tous les produits vendus en bouteilles ou en canettes
SELECT *
FROM produit
WHERE qteparunit LIKE '%bouteille%' OR qteparunit LIKE '%canette%'

-- b : Lister les fournisseurs franÃ§ais, en affichant uniquement le nom, le contact et la ville, triÃ©s par ville
SELECT societe, contact, ville
FROM fournisseur
WHERE pays = 'France'
ORDER BY ville

-- c :  Lister les produits (nom en majuscule et rÃ©fÃ©rence) du fournisseur nÂ° 8 dont le prix unitaire est entre 10 et 100 euros, en renommant les attributs pour que ce soit explicite
SELECT UPPER(nomprod) AS 'Nom produit', refprod AS Reference
FROM produit
WHERE nofour = 8 AND prixUnit BETWEEN 10 AND 100

-- d : Lister les numÃ©ros d'employÃ©s ayant rÃ©alisÃ© une commande (cf table Commande) Ã  livrer en France, Ã  Lille, Lyon ou Nantes
SELECT noEmp AS 'numero d''employe'
FROM commande
WHERE paysliv = 'France' AND villeliv IN ('Lille', 'Lyon', 'Nantes')

-- e : Lister les produits dont le nom contient le terme "tofu" ou le terme "choco", dont le prix est infÃ©rieur Ã  100 euros (attention Ã  la condition Ã  Ã©crire)
SELECT *
FROM produit
WHERE nomprod LIKE '%tofu%' OR nomprod LIKE '%choco%'

---------------------------------------------------------
-- 2 - "Calculs et fonctions"
---------------------------------------------------------
--Partie 1
SELECT 
    refprod AS RÃ©ference, 
    PrixUnit AS "Prix unitaire" , 
    qte AS QuantitÃ©, 
    ROUND((qte*prixunit)*remise,2) AS Remise,
    qte * prixunit - remise AS "Montant Ã  payer"
FROM DetailCommande
WHERE NoCom = 10251

--Partie 2
SELECT 
    (adresse ||' '|| ville || ' ' || codepostal || ' ' || pays) AS Adresse,
    SUBSTR(codecli, LENGTH(codeCli) - 2 , 2) AS "Short Code Client",
    UPPER(societe) AS SociÃ©tÃ©,
    REPLACE(Fonction, "marketing","mercatique") AS "Fonction des contacts",
    REPLACE(INSTR(Fonction, "Chef"),1,"Chef") AS Chef
FROM Client

--Partie 3 : date
-- a : Calculer pour chaque commande le jour de la semaine, le numÃ©ro de semaine dans l'annÃ©e et le mois
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

-- d : On souhaite aussi contacter les clients 1 an, 1 mois et 1 semaine aprÃ¨s leur commande. Calculer les dates correspondantes pour chaque commande
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
    END AS DisponibilitÃ©
FROM produit

-- b :Dans la table DetailCommande, indiquer les infos suivantes en fonction de la remise
	-- si elle vaut 0 : "aucune remise"
	-- si elle vaut entre 1 et 5% (inclus) : "petite remise"
	-- si elle vaut entre 6 et 15% (inclus) : "remise modÃ©rÃ©e"
	-- sinon :"remise importante"
SELECT *,
    CASE 
        WHEN remise = 0 THEN "Pas de remise"
        WHEN remise BETWEEN 0.01 AND 0.05 THEN "Petite remise"
        WHEN remise BETWEEN 0.06 AND 0.15 THEN "Remise modere"
        ELSE "Remise importante"
    END AS remise
FROM detailcommande

-- c : Indiquer pour les commandes envoyÃ©es si elles ont Ã©tÃ© envoyÃ©es en retard (date d'envoi DateEnv supÃ©rieure (ou Ã©gale) Ã  la date butoir ALivAvant) ou Ã  temps
SELECT noCom AS 'Commande NÂ°',
    CASE
        WHEN dateEnv >= aLivAvant THEN "Retard"
        ELSE "Pas de retard"
    END AS "Retard"
FROM commande
ORDER BY retard DESC

--Partie 5 : TODO
-- a :RÃ©cupÃ©rer l'annÃ©e de naissance et l'annÃ©e d'embauche des employÃ©s

-- b :Calculer Ã  l'aide de la requÃªte prÃ©cÃ©dente l'Ã¢ge d'embauche et le nombre d'annÃ©es dans l'entreprise

-- c : Afficher le prix unitaire original, la remise en pourcentage, le montant de la remise et le prix unitaire avec remise (tous deux arrondis aux centimes), pour les lignes de commande dont la remise est strictement supÃ©rieure Ã  10%

-- d :Calculer le dÃ©lai d'envoi (en jours) pour les commandes dont l'envoi est aprÃ¨s la date butoir, ainsi que le nombre de jours de retard

-- e :Rechercher les sociÃ©tÃ©s clientes, dont le nom de la sociÃ©tÃ© contient le nom du contact de celle-ci

---------------------------------------------------------
-- 3 - AgrÃ©gat
---------------------------------------------------------
--Partie 1
-- a : Calculer le nombre d'employÃ©s qui sont "ReprÃ©sentant(e)"
SELECT Count(*) AS Total
FROM employe
WHERE fonction LIKE '%reprÃ©sentant%'

-- b : Calculer le nombre de produits de moins de 50 euros
SELECT Count(*) AS Total
FROM produit
WHERE prixunit > 50

-- c :  Calculer le nombre de produits de catÃ©gorie 2 et avec plus de 10 unitÃ©s en stocks
SELECT Count(*) AS Total
FROM produit
WHERE codecateg = 2 AND unitesStock > 10

-- d : Calculer le nombre de produits de catÃ©gorie 1, des fournisseurs 1 et 18
SELECT Count(*) AS Total
FROM produit
WHERE codecateg = 1 AND noFour IN (1, 18)

-- e : Calculer le nombre de pays diffÃ©rents de livraison
SELECT Count(DISTINCT paysLiv) AS Total
FROM commande

-- f : Calculer le nombre de commandes rÃ©alisÃ©es le 28/03/2016.
SELECT 
    COUNT(*) AS total, 
    datecom AS date
FROM commande
WHERE datecom = '2016-03-28'

--Partie 2
-- a : Calculer le coÃ»t moyen du port pour les commandes du client dont le code est "QUICK" (attribut CodeCli)
SELECT ROUND(AVG(Port), 2) AS "Cout moyen du port"
FROM commande
WHERE codeCLi = "QUICK"

-- b : Calculer le coÃ»t du port minimum et maximum des commandes
SELECT
    MIN(Port) AS Minimum,
    MAX(Port) AS Maximum
FROM commande

-- c : Pour chaque messager (par leur numÃ©ro : 1, 2 et 3), donner le montant total des frais de port leur correspondant
	-- il faut faire 3 requÃªtes diffÃ©rentes donc
SELECT
    SUM(port)
FROM commande
WHERE nomess = 3 -- idem avec 1 & 2

--Partie 3
-- a : Donner le nombre d'employÃ©s par fonction
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

-- c : Donner le nombre de catÃ©gories de produits fournis par chaque fournisseur
SELECT
    NoFour AS Fournisseur,
    COUNT(DISTINCT codeCateg) AS "Nombre CatÃ©gorie"
FROM produit
GROUP BY noFour

-- d : Donner le prix moyen des produits pour chaque fournisseur et chaque catÃ©gorie de produits fournis par celui-ci
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

-- b : Lister les catÃ©gories dont les prix sont en moyenne supÃ©rieurs strictement Ã  150 euros
SELECT
    codecateg AS Categorie,
    ROUND(AVG(prixunit),2) AS moyenne
FROM produit
GROUP BY codecateg
HAVING moyenne > 150

-- c :  Lister les fournisseurs ne fournissant qu'une seule catÃ©gorie de produits
SELECT
    noFour AS Fournisseur,
    COUNT(DISTINCT codeCateg) AS TOTAL
FROM produit
GROUP BY noFour
HAVING total = 1

-- d : Lister les fonctions pour lesquelles la moyenne d'Ã¢ge des employÃ©s dÃ©passe les 45 ans

       
--Partie 5 : TODO
-- a :Donner la quantitÃ© totale commandÃ©e par les clients, pour chaque produit

-- b :Donner les cinq clients avec le plus de commandes, triÃ©s par ordre dÃ©croissant

-- c : Calculer le montant total des lignes d'achats de chaque commande, sans et avec remise sur les produits

-- d :Pour chaque catÃ©gorie avec au moins 10 produits, calculer le montant moyen des prix

-- e :Donner le numÃ©ro de l'employÃ© ayant fait le moins de commandes

---------------------------------------------------------
-- 4 - 
---------------------------------------------------------
--Partie 1
-- a : RÃ©cupÃ©rer les informations des fournisseurs pour chaque produit
SELECT 
    p.nomProd AS Produit,
    f.societe AS Fournisseur
FROM produit p
    NATURAL JOIN fournisseur f
    
-- b : Afficher les informations des commandes du client "Lazy K Kountry Store"
SELECT 
    cl.societe AS Client,
    co.nocom AS Commande,
    nomprod AS Article
FROM commande co
    NATURAL JOIN client cl
    NATURAL JOIN detailCommande dc
    NATURAL JOIN produit
    
-- c : Afficher le nombre de commande pour chaque messager (en indiquant son nom)
SELECT 
    nomMess AS Messager,
    COUNT(*) AS total
FROM commande c
    NATURAL JOIN messager m
GROUP BY messager

--Partie 2
-- a : RÃ©cupÃ©rer les informations des fournisseurs pour chaque produit, avec une jointure interne
SELECT 
    f.societe AS Fournisseur,
    p.nomProd AS Produit
FROM produit p
    INNER JOIN fournisseur f USING(noFour)
       
-- b : Afficher les informations des commandes du client "Lazy K Kountry Store", avec une jointure interne
SELECT 
    co.noCom As Commande,
    co.datecom AS Date
FROM commande co
    INNER JOIN client cl USING(codeCli)
WHERE cl.societe = "Lazy K Kountry Store"
       
-- c : Afficher le nombre de commande pour chaque messager (en indiquant son nom), avec une jointure interne
SELECT 
    COUNT(*) AS Total,
    m.nomMess
FROM commande c
    INNER JOIN messager m USING (noMess)
GROUP BY nomMess
       
-- d : Afficher pour chaque employÃ© le nom et le prÃ©nom de son responsable
SELECT 
    (e.nom || ' ' || e.prenom) AS Patron,
    (e2.nom || ' ' || e2.prenom) AS Employe
FROM employe e
    INNER JOIN employe e2 ON e.noEmp = e2.rendCompteA 
       
--Partie 3
-- a : Compter pour chaque produit, le nombre de commandes oÃ¹ il apparaÃ®t, mÃªme pour ceux dans aucune commande
SELECT 
    refprod AS reference,
    COUNT (dc.refprod) AS total
FROM produit p
    LEFT OUTER JOIN detailCommande dc USING (refProd)
GROUP BY p.refProd
       
-- b : Lister les produits n'apparaissant dans aucune commande
SELECT 
    refprod AS reference,
    COUNT (dc.refprod) AS total
FROM produit p
    LEFT OUTER JOIN detailCommande dc USING (refProd)
GROUP BY p.refProd
HAVING total = 0
       
-- c : Existe-t'il un employÃ© n'ayant enregistrÃ© aucune commande ?
SELECT 
    nom || ' ' ||prenom AS Employe,
    count(noCom) AS total
FROM employe
    LEFT OUTER JOIN commande USING(noEmp)
GROUP BY employe
HAVING total = 0
       
--Partie 4
-- a : RÃ©cupÃ©rer les informations des fournisseurs pour chaque produit, avec jointure Ã  la main
SELECT 
    f.societe,
    p.nomProd
FROM fournisseur f, produit p
WHERE f.noFour = p.noFour
       
-- b : Afficher les informations des commandes du client "Lazy K Kountry Store", avec jointure Ã  la main
SELECT
    *
FROM client c, commande co
WHERE c.codeCli = co.codeCli 
    AND c.societe = "Lazy K Kountry Store"
       
-- c : Afficher le nombre de commande pour chaque messager (en indiquant son nom), avec jointure Ã  la main
SELECT
    m.nomMess,
    COUNT(c.noCom) as total
FROM messager m, commande c
GROUP BY nomMess
       
--Partie 5
-- a : Compter le nombre de produits par fournisseur
       
-- b : Compter le nombre de produits par pays d'origine des fournisseurs
       
-- c : Compter pour chaque employÃ© le nombre de commandes gÃ©rÃ©es, mÃªme pour ceux n'en ayant fait aucune
       
-- d : Afficher le nombre de pays diffÃ©rents des clients pour chaque employe (en indiquant son nom et son prÃ©nom)
       
-- e : Compter le nombre de produits commandÃ©s pour chaque client pour chaque catÃ©gorie
     
---------------------------------------------------------
-- 5 - Sous requetes
---------------------------------------------------------
--Partie 1
-- a : Lister les employés n'ayant jamais effectué une commande, via une sous-requête
SELECT 
    e.nom || ' ' || e.prenom AS Employe
FROM employe e
WHERE noEmp NOT IN (
    SELECT noEmp
    FROM commande
)

-- b : Nombre de produits proposés par la société fournisseur "Mayumis", via une sous-requête
SELECT *
FROM produit
WHERE noFour = (
    SELECT noFour
    FROM fournisseur
    WHERE societe = 'Mayumis'
)

-- c : Nombre de commandes passées par des employés sous la responsabilité de "Patrick Emery"
SELECT
    noEmp AS Employé,
    COUNT(*) AS Total 
FROM commande
WHERE noEmp IN (
    SELECT noEmp
    FROM employe
    WHERE rendCompteA = (
        SELECT noEmp
        FROM Employe
        WHERE nom = "Emery"
    )
)
GROUP BY noEmp

--Partie 2
-- a : Lister les produits n'ayant jamais été commandés, à l'aide de l'opérateur EXISTS
SELECT *
FROM produit p
WHERE NOT EXISTS (
    SELECT *
    FROM detailCommande
    WHERE refProd = p.refProd
)

-- b : Lister les fournisseurs dont au moins un produit a été livré en France

-- c : Liste des fournisseurs qui ne proposent que des boissons

--Partie 3
-- a : Lister les clients qui ont commandé du "Camembert Pierrot" (sans aucune jointure)

-- b : Lister les fournisseurs dont aucun produit n'a été commandé par un client français

-- c : Lister les clients qui ont command ́e tous les produits du fournisseur "Exotic liquids"

-- d : Quel est le nombre de fournisseurs n’ayant pas de commandes livrées au Canada ?

-- d :Lister les employés ayant une clientèle sur tous les pays

---------------------------------------------------------
-- 6 - 
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
-- Recapitulatif 1 
---------------------------------------------------------
--Partie 2
-- a : Lister le contenu de la table Seances
SELECT * FROM seances
-- b : Lister les sportifs par ordre croissant d'âge
SELECT * FROM sportifs ORDER BY age ASC
-- c : Lister les 5 gymnases les plus grands
-- d :
-- e :
-- f :
--Partie 2
--Partie 3
--Partie 4
--Partie 5

---------------------------------------------------------
-- Recapitulatif 2
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
