CREATE TABLE `papeterie`.`dbo.articles` ( 
`idArticle` INT NOT NULL AUTO_INCREMENT , 
`reference` CHAR(10) NOT NULL , 
`marque` VARCHAR(200) NOT NULL , 
`designation` VARCHAR(250) NOT NULL , 
`prixUnitaire` FLOAT NOT NULL , 
`qteStock` INT NOT NULL , 
`grammage` INT NULL , 
`couleur` VARCHAR(50) NULL , 
`type` CHAR(10) NOT NULL , 
PRIMARY KEY (`idArticle`)) 
ENGINE = MyISAM; 