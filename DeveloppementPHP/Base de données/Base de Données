# Base De Donnée

## 1. Premier chapitre
START : 17/02/2017
### Introduction
L'enjeu pour les entreprise est de pouvoir stocker et traiter toutes sortes d'information (par exemple des commandes, clients, des posts d'un blog,..).

Dans l'exemple du bon de commande,
Il existe plusieurs possibilités :
- Scanner le bon de commande [1/10]
- Encoder le bon dans Word [2/10]
- Encoder le bon dans Excel [3/10]
- Encoder le bon dans une base de donnée [10/10]

Dans le cas de l'utilisation d'une BDD, il faut encore concevoir la façons dont les données vont être stocker. A des fins d'optimisation.
Par exemple si l'on peux calculé une informations à partir de celles déjà présente, rien ne sert de la stocker (Ex: le total d'une commande)

#### Utiliser une base de donnée
Afin d'interroger une base de donnée relationnel, le langage SQL est utilisé. Exemple :
```SQL
SELECT NCLI, NOM, ADRESSE
FROM CLIENT
WHERE LOCALITE = 'TOULOUSE'
```
Le SQL est un langage de très haut niveau, accessible aux personnes dont l'informatique n'est pas leur métier.

#### Que permet les bases de données
L'avantage principal des bases de données relationnels est qu'il est possible de faire "parler" les données.
Par exemple :
- Quel est le chiffre d'affaire dans la région de TOULOUSE
- Produire une facture
- Quel est le marché avec le moins de chiffre
- Quel est la localité avec le plus de clients
- ...

#### Première approche des données
(Dans le cadre du cours, on utilise les données d'un bon de commande en entreprise. Client, Produits, Commandes, Détails de commandes)

Données de la commande

| NCOM  | NCLI | DATECOM  |
|-------|------|----------|
| 30188 | 3512 | 2/1/2009 |

Données du client

| NCLI | NOM | ADRESSE         | LOCALITE |
|------|---------|-----------------|----------|
| 3512 | GILLET  | 14, R. de l'été | Toulouse |

Données des détails

| NCOM  | NPRO  | QCOM |
|-------|-------|------|
| 30188 | CS464 | 180  |
| 30188 | PA45  | 22   |
| 30188 | PA60  | 70   |
| 30188 | PH222 | 92   |

L'**identifiant unique** de la table détail sont les colonnes **NCOM & NPRO**
TODO : Ajouter point sur l'identifiant unique et expliquer quels sont les clef étrangères

Données des produits

| NPRO  | LIBELLE         | PRIX |
|-------|-----------------|------|
| CS464 | Produit 1       | 220  |
| PA45  | POINTE ACIER 45 | 105  |
| PA60  | POINTE ACIER 60 | 95   |
| PH222 | PL. HETRE       | 230  |




#### Première conclusion
- On évite de stocker des informations qu'il est possible de **calculer** à partir de données déjà enregistrée
- On ne conserve pas dans une même table des données de natures différentes
- Le langage SQL permet de rédiger de manière simple et concise des requêtes d'**extraction** et de **calcul** de donnée de complexité quelconque
- On ne produit pas une BDD pour un besoin immédiat, on produit une BDD pour représenter le plus **fidèlement possible** l'activé, indépendamment des données que l'on y entrera
- La construction d'une BDD nécessite une **analyse rigoureuse** et attentive. Bien qu'il est possible d'ajouter des fonctionnalité par la suite


#### 1.4 Système de gestion de bases de données (SGBD)
La gestion de bases de données pose des problèmes complexes
- Organisation des données
- Gestions des données
- Accès aux données
- Protection contre les accidents (Intégrité)
- Gestion des accès concurrents (Accès simultané aux données)
- Contrôle des accès

#### 1.5 Défis des BDD d'aujourd'hui
- Multiplicité des types de données.
Une base de données moderne peut contenir des données multimédia, textuelle, spaciale (GPS), historique, données demi-structurée,...
- L'information incomplète.
Une BDD doit admettre que l'on ne connaisse pas tout sur tout et que les connaissance peuvent être erronée. Comment assurer la cohérences des données ?
- Volumes et performance.
Une BDD peut contenir des dizaines de milliers de tables. Comment garantir un temps d'accès court, leur protection contre les accidents,...
- Accès par les non-informaticiens
- Maintenance et évolution
- Les données distribuées et nomades. Une base de données peut être répartie et/ou dupliquées sur plusieurs ordinateurs répartis géographiquement
- La Business Intelligence (BI).
Prendre des décision basé sur une computation des données de l'entreprise.

## Chapitre 2: Concepts des BDD
** A L'examen il faut être précis pour les définitions. Pas avec nos propres mots**

### 2.1 Tables, lignes et colonnes
Il y à donc des tables, des lignes (data), des colonnes facultative (par défaut) et obligatoire.

|       | Colonnes |
|-------|----------|
| Ligne |   .      |

## 2.2 Valeur Null
L'absence de valeur est indiquée par un marqueur spécial : NULL

## 2.3 Identifiants & Clef étrangères
- Identifiant : Groupe de colonnes d'une table tel qu'avec cet identifiants on retrouve une et UNE seul ligne de la table
  - Contrainte d'unicité
    - Un identifiant est minimal si chacune des colonnes est nécéssaire pour garantir l'unicité (Exemple : Une table de 6 colonnes, 3 sont l'identifiant. Si l'on peux supprimé l'une des 3 et que l'unicité est toujours garantie, l'identifiant est donc minimal)
  - Contrainte obligatoire
  - Il est possible de déclarer une table sans identifiant mais ce n'est pas recommandé
  - Une table peut posséder plusieurs identifiant, le plus important est primaire, les autres secondaires
- Clé étrangère : Un groupe de colonnes d'une table S qui tel qu'il existe, dans une table T, une ligne dont l'identifiant à pour valeur celle du groupe
  Exemple le numéro Client dans le bon de commande fait référence à un client unique dans la table CLIENT
  - Définis une contrainte référentielle.
  - Si une colonne d'une clef étrangère est facultative, les autres colonnes devraient l'être aussi
  - Une clé étrangère et l'identifiant qu'elle référence ont la même composition : même nombre de colonnes et colonnes de mêmes types prises deux à deux.
  -
  - Une clef étrangère référence en principe l'identifiant primaire de la table cible. (Elle peux référencer un identifiant secondaire)
  -

## 2.4 Schéma et contenu
- Le schéma d'une table définit sa structure. Notamment
  1. Le nom de la table
  2. Pour chaque colonne, nom, type, caractère obligatoire
  3. Identifiant primaire
  4 Identifiants secondaires éventuelle
  5. Clef étrangère éventuelle
- Le contenus d'une table est formé d'un ensemble de lignes conformes au schéma
- Le contenus d'une table change constamment, le schéma peut évoluer mais moins fréquemment

## 2.6 Modifications et contraintes d'intégrité
Si l'on désire supprimer un élément qui est une clef étrangère il y à 4 options :
1. mode No Action (Refuse la suppression)
2. mode Cascade (Ligne supprimée, mais aussi les lignes de COMMANDE dépendantes) : Très dangereux !
3. Mode set Null (si NCLI de COMMANDE facultative) la colonne NCLI des lignes dépendantes de COMMANDE est mise à Null) - Pas top
4. Mode set default : Applique la valeur par défaut (définis dans le schéma de la BDD)

END : 17/02/2017
