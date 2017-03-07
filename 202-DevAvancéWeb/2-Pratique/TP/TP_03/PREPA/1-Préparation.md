# Semaine 03 - Préparation

## 1.2 Première procédure `mc_allGroups`
```sql
CREATE PROCEDURE `mc_allGroups`()
BEGIN
SELECT * FROM minicampus.class;
END
```

## 1.3 Premier passage de paramètre `mc_group`
```sql
CREATE PROCEDURE `mc_group`(IN groupe VARCHAR(10))
BEGIN
SELECT * FROM minicampus.class WHERE class.nom = groupe;
END
```
## 2.1 Réalisation

 `mc_parentGroup()`
```sql
CREATE PROCEDURE `mc_coursesGroup`(IN groupe VARCHAR(10))
BEGIN
SELECT cours.code, cours.faculte , cours.intitule
FROM minicampus.cours
INNER JOIN minicampus.course_class
ON cours.code = course_class.cours_id
INNER JOIN minicampus.class
ON class.id = course_class.class_id
WHERE class.nom LIKE groupe;
END
```

`mc_coursesGroup()`
```sql
CREATE PROCEDURE `mc_parentGroup`(IN groupe VARCHAR(10))
BEGIN
SELECT c1.nom AS 'nomEnfant', c2.nom AS 'nomParent'
FROM minicampus.class c1
JOIN minicampus.class c2
ON c1.parent_id = c2.id
WHERE c1.nom LIKE groupe;
END
```
