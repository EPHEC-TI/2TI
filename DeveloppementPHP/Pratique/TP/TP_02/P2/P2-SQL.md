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
    1. Dessinez les tables :
    > J'ai foi en vous, vous pouvez le faire vous même <3

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
    1. Dessinez les tables :
    > J'ai foi en vous, vous pouvez le faire vous même <3

## Phase 3: Requêtes dans des DB multi-tables
1. Dans `World`
  1. Afficher la plus grande superficie pour un pays :
  ```sql
  SELECT MAX(SurfaceArea) AS laPlusGrande FROM country;
  ```
  1. Afficher le pays qui as la plus petite superficie
  ```sql
  SELECT Name, SurfaceArea AS 'laPlusPetiteSuperf.(km²)' FROM country WHERE SurfaceArea = (SELECT MIN(SurfaceArea) FROM country);
  ```
