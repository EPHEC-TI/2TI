# Solution P2-SQL "Agrégats & JOIN"

## Phase 1: MYSQL Workbench

1. Afficher les bases de données : `SHOW databases`  
1. Afficher la liste des tables disponibles : `SHOW tables`  
1. Lister les champs de la table `etudiants` : `DESCRIBE etudiants`

## Phase 2: Reverse Engineering
  1. World
    1. Afficher la liste des tables disponibles : `SHOW tables`
    1. Afficher la structure des tables :
    ```
    DESCRIBE city;
    DESCRIBE country;
    DESCRIBE countrylanguage;
    ```
    1. Dessinez le schéma des tables :
    > J'ai foi en vous, vous pouvez le faire vous même <3 #Flemme

  1. Minicampus
    1. Afficher la liste des tables disponibles : `SHOW tables`
    1. Afficher la structure des tables :
    ```
    DESCRIBE class;
    DESCRIBE class_user;
    DESCRIBE cours;
    DESCRIBE course_class;
    DESCRIBE cours_user;
    DESCRIBE faculte;
    DESCRIBE user;
    ```
    1. Dessinez le schéma relationnel des tables :
    > J'ai foi en vous, vous pouvez le faire vous même <3 #Flemme

## Phase 3: Requêtes dans des DB multi-tables
1. Travail "mono-table" -> Dans `World`
  1. Afficher la plus grande superficie pour un pays :
  ```sql
  SELECT MAX(country.SurfaceArea) AS 'laPlusGrande' FROM world.country;
  ```
  1. Afficher le pays qui as la plus petite superficie
  ```sql
  SELECT country.Name, country.SurfaceArea AS 'laPlusPetiteSuperf.(km²)' FROM world.country WHERE country.SurfaceArea = (SELECT MIN(country.SurfaceArea) FROM world.country);
  ```
  1. Afficher la superficie totale du "BENELUX"
  ```sql
  SELECT 'benelux', SUM(country.SurfaceArea) as 'surface' FROM world.country WHERE country.Code2 = 'BE' OR country.Code2 = 'NL' OR country.Code2 = 'LU';
  ```
  1. Afficher la superficie totale des pays suivants :
    ```sql
    #France
      SELECT country.Name, country.SurfaceArea FROM world.country WHERE country.Code2 = 'FR';

    #Allemagne
      SELECT country.Name, country.SurfaceArea FROM world.country WHERE country.Code2 = 'DE';

    #Espagne
      SELECT country.Name, country.SurfaceArea FROM world.country WHERE country.Code2 = 'ES';

    #Italie
      SELECT country.Name, country.SurfaceArea FROM world.country WHERE country.Code2 = 'IT';

    #Grèce
      SELECT country.Name, country.SurfaceArea FROM world.country WHERE country.Code2 = 'GR';
    ```

1. Travail "multi-tables" -> Dans `World`
  1. Pour les pays du "BENELUX"
    1. Donnez le nom du pays, la population du pays et la population totale des villes du pays reprise dans la table `city`

    ```sql
      SELECT country.Name AS 'pays', country.Population AS 'popuPays', SUM(city.Population) AS 'popuVilles' FROM world.country
      INNER JOIN world.city
      ON country.Code=city.CountryCode
      WHERE country.Code='BEL' OR country.Code='NLD' OR country.Code='LUX'
      GROUP BY country.Code;
    ```

    1. Donnez en ordre décroissant sur les pourcentages, les noms et les pourcentages des langues parlées

    ```sql
    SELECT ctLang.Language AS 'language', SUM(ctLang.Percentage/100*ct.population)/(SELECT SUM(ct.population) FROM world.country AS ct WHERE ct.Code='BEL' OR ct.Code='NLD' OR ct.Code='LUX' )*100 AS 'pct'
    FROM world.countrylanguage AS ctLang
    INNER JOIN world.country AS ct
    ON ct.code = ctLang.CountryCode
    WHERE ctLang.CountryCode='BEL' OR ctLang.CountryCode='NLD' OR ctLang.CountryCode='LUX'
    GROUP BY  language
    ORDER BY pct DESC;
    ```

    1. Donnez le pourcentage de personnes qui parlent une langue officielle (dans le monde)

    ```sql
    SELECT SUM((ctLang.Percentage/100)*ct.population)/(SELECT SUM(ct.population) FROM world.country AS ct )*100 AS '%_langueOfficielle'
    FROM world.countryLanguage AS ctLang
    INNER JOIN world.country AS ct
    ON ct.code = ctLang.CountryCode
    WHERE ctLang.isOfficial = 'T';
    ```

    1. Donnez le pourcentage de personnes qui parlent une langue non-officielle (dans le monde)

    ```sql
    SELECT SUM((ctLang.Percentage/100)*ct.population)/(SELECT SUM(ct.population) FROM world.country AS ct )*100 AS '%_langueNonOfficielle'
    FROM world.countryLanguage AS ctLang
    INNER JOIN world.country AS ct
    ON ct.code = ctLang.CountryCode
    WHERE ctLang.isOfficial = 'F';
    ```

## A faire chez soi

1. Dans `world` : mono-table
  1. Afficher la superficie de chacune des régions d'Europe

    ```sql
    SELECT ct.Region, SUM(ct.SurfaceArea) FROM world.country AS ct
    WHERE ct.Continent = 'Europe' AND ct.Region LIKE '%Europe'
    GROUP BY ct.Region;
    ```

  1. Pour chacun des continents, afficher le(s) pays qui a (ont) eu leur indépendance le plus récemment

    ```sql
    SELECT ct1.Name, ct1.Continent, ct1.IndepYear
    FROM world.country AS ct1
    JOIN (SELECT ct.Continent, MAX(ct.IndepYear) AS 'maxIndepYear' FROM world.country AS ct GROUP BY Continent) ct2
    ON ct1.Continent = ct2.Continent AND ct1.IndepYear = ct2.maxIndepYear
    ORDER BY ct1.Continent, ct1.Name;
    ```
1. Dans `minicampus` : mono-table
  1. Afficher la liste des facultés "de base" (Celles qui n'ont pas de parent)
    ```sql
    SELECT * FROM minicampus.faculte WHERE faculte.codeParent IS NULL;
    ```

  1. Afficher les "filles" d'une faculté donnée (p.e. TI)
    ```sql
    SELECT * FROM minicampus.faculte WHERE faculte.codeParent = "TI";
    ```

  1. Afficher la liste des classes "de base" (celles qui n'ont pas de parent)
    ```sql
    SELECT * FROM minicampus.class WHERE class.parent_id IS NULL;
    ```

  1. Afficher les "filles" d'une "section" donnée (p.e TI)
    ```sql
    SELECT class.nom AS 'nomFilles(TI)' FROM minicampus.class WHERE parent_id = 1;
    ```

  1. Afficher les "filles des filles" d'une classe donnée (p.e TI)
    ```sql
    SELECT class.nom AS 'nomPetitesFilles(TI)' FROM minicampus.class WHERE class.parent_id BETWEEN 2 AND 4;
    ```

1. Dans `minicampus` : multi-tables
  1. Pour tous les étudiants, afficher les informations suivantes :  
    Groupe, Matricule, Nom, Prénom, Email (construit : matricule@students...)

    ```sql
    SELECT class.nom, UCASE(user.username) AS 'matricule', user.nom, user.prenom, CONCAT(LCASE(user.username), '@students.ephec.be')
    FROM minicampus.user
    INNER JOIN minicampus.class_user
    ON user.id = class_user.user_id
    INNER JOIN minicampus.class
    ON class_user.class_id = class.id
    WHERE user.username LIKE 'HE%';
    ```

  1. Afficher la liste des cours (code, faculté et libellé) pour une classe donnée (p.e. 1TL2)
    ```sql
    SELECT cours.code, cours.intitule
    FROM minicampus.cours
    INNER JOIN minicampus.course_class
    ON cours.code = course_class.cours_id
    INNER JOIN minicampus.class
    ON class.id = course_class.class_id
    WHERE class.nom='1TL2';
    ```

  1. Pour chacune des "facultés", afficher ces informations précédées du nom de son "parent"

    ```sql
    SELECT f2.nom, f1.id, f1.nom, f1.code, f1.codeParent, f1.position, f1.nbEnfants
    FROM minicampus.faculte f1
    INNER JOIN minicampus.faculte f2
    ON f1.codeParent = f2.code
    WHERE f1.codeParent IS NOT NULL;
    ```
