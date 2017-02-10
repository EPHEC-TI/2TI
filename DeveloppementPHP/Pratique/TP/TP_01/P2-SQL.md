# Solution P2-SQL-Select "Base"

## 2. Afficher la liste des bases de donnée disponible
Requete :
`SHOW databases`
## 3. Macro-commande
Réaliser une seule commande qui vas répondre à toutes les questions :
Dans la base de donnée : `Test`
* Combien d'étudiants ?
* Combien d'étudiants en 1TL1 ?
* Combien d'étudiants dans tout les "groupes" (sous-classes) ?
* Combien d'étudiants par années/sections ?
* Combien d'étudiants par sections ?
* Combien de fois revienne chaque prénom ?
* Quel est le plus grand nombre de fois que revient un prénom ?
* Quel(s) sont le(s) prénom(s) qui revien(nen)t le plus souvent ?
* Il y à t'il un/des cas d'homonymie* dans `étudiants` et dans `étudiants_2`
* Quels sont les étudiants concerné par une homonymie*

 >[Homonymie](https://www.google.be/url?sa=t&rct=j&q=&esrc=s&source=web&cd=2&cad=rja&uact=8&ved=0ahUKEwiDhI6p0YXSAhUBBBoKHdqmAdkQFgggMAE&url=https%3A%2F%2Ffr.wikipedia.org%2Fwiki%2FHomonymie&usg=AFQjCNG_QtPUKNtOBpUZv6KMK6HoS2aJFA&sig2=GW3jHncJT2_Fv4lXdUCNyg) : Càd. les personnes qui ont le même nom et le même prénom.
 

```sql
# Combien détudiants ?
SELECT count(*) AS 'nbEtudiant'  
FROM test.etudiants;

# Combien détudiants en 1TL1 ?
SELECT count(*) AS 'etudiant1TL1'  
FROM test.etudiants  
WHERE Classe='1TL1';

# Combien détudiants dans tout les "groupes" (sous-classes) ?
SELECT Classe, count(*) AS 'nbEtudiant'  
FROM test.etudiants  
GROUP BY Classe;

# Combien détudiants par années/sections ?
SELECT substr(Classe, 1, 2) AS 'Année/Section', count(*) AS 'nbEtudiant'  
FROM test.etudiants  
GROUP BY substr(Classe, 1, 2);

# Combien détudiants par sections ?
SELECT substr(Classe, 2, 1) AS 'Année/Section', count(*) AS 'nbEtudiant'  
FROM test.etudiants  
GROUP BY substr(Classe, 2, 1);

# Combien de fois revienne chaque prénom ?
SELECT prenom, count(*) AS 'nbEtudiant'  
FROM test.etudiants  
GROUP BY prenom;

# Quel est le plus grand nombre de fois que revient un prénom ?
SELECT MAX(nbOccurencePrenom) AS 'prenomPlusPopulaire'  
FROM (
	SELECT count(*) AS 'nbOccurencePrenom'  
    FROM test.etudiants
    GROUP BY prenom
) AS nbOccurencePrenom;

# Quel(s) sont le(s) prénom(s) qui revien(nen)t le plus souvent ?
SELECT prenom
FROM test.etudiants
GROUP BY prenom
ORDER BY count(prenom) DESC;

# Il y à til un/des cas dhomonymie* dans étudiants et dans étudiants_2
SELECT nom, prenom, nbOccurences
FROM (
	SELECT nom, prenom, count(*) AS 'nbOccurences'
    FROM test.etudiants  
    GROUP BY nom, prenom
) AS tb
HAVING nbOccurences>1;

# Quels sont les étudiants concerné par une homonymie*
SELECT etuParent.*
FROM test.etudiants_2 AS etuParent
JOIN (
	SELECT nom, prenom, count(*)
    FROM test.etudiants_2 AS etuFils
    GROUP BY nom, prenom
    HAVING count(*) >= 2
) AS etuFils
ON etuParent.nom = etuFils.nom
AND etuParent.prenom = etuFils.prenom
ORDER BY nom,prenom,matricule;
```
