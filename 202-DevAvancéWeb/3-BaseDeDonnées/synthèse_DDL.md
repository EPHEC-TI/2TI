*Synthèse écrite par M. Petit sur base du cours donné par S. Faulkner s'appuyant sur un support de cours réalisé par J-L Hainaut.
Des phrases sont reprises en partie ou tels quelles de ce document.*

## BDD

### DDL

Le sous-langage DDL de SQL permet de créer des structures de données et de les modifier. 

##### create

* `create SCHEMA`, chaque DB sur un serveur est identifiée par un schéma ... 

* **tables** et ses colonnes. Le not null permet de spécifier des colonnes obligatoires, default spécifie la valeur par défaut en cas d'insertion de la ligne sans valeur pour cette colonne. Primary key définit la clé primaire, unique la/les clé(s) candidate(s) = UNICITE. 


```
create table CLIENT ( NCLI     char(10) not null,
                      NOM      char(32) not null,
                      ADRESSE  char(60) not null,
                      LOCALITE char(30) not null default 'Paris',
                      CAT      char(2) default 'B1',
                      COMPTE   decimal(9,2) not null,
                      primary key (NCLI),
                      unique (NOM) );
                      
...
/* exemple de création d'une structure cyclique directe */
create table PERSONNE ( NPERS       char (4) not null,
                        NOM         char(25) not null,
                        RESPONSABLE char (4),
                        primary key (NPERS),
                        foreign key (RESPONSABLE)
                                references PERSONNE);
...
/* exemple de création d'une structure cyclique directe */
create table PRODUIT ( NPRO     char(4) not null primary key,
                       LIBELLE  char(25) not null,
                       PRIX_U   char(4),
                       POIDS_U  char(4));
create table COMPOSITION ( COMPOSE   char(4) not null references PRODUIT,
                           COMPOSANT char(4) not null references PRODUIT,
                           QTE   decimal(4) not null,
                           primary key (COMPOSE,COMPOSANT));
```
  * clé étrangère
  ```
  foreign key (MaColonne) references CLIENT 
  
  /*MaColonne référence la clé primaire de la table CLIENT. Au cas ou la colonne référencée n'est pas la clé primaire, utiliser la forme suivante */
  
  foreign key (MaColonne) references CLIENT(LaColonne)
  
  /* ici MaColonne référence LaColonne de CLIENT
  
  ```
  * contraintes nommée, facilite la vie en cas de suppression ou modification de la contrainte. 
  ```
  ...
  constraint NOMCONTRAINTE primary key (NCOM),
  ...
  
  ```

* les **domaines** snot utile spour l'intégrité des données et l'évolutivité (+facile de changer un domaine que mille colonnes).

```
create domain MONTANT decimal(9,2);


/* utilisation du domaine créé */
create table CLIENT (   ...
                        COMPTE MONTANT);


```

* **structures physiques**
  * `create index +NomIndex on +TABLE +(COLONNE)` avec le mot unique permet d'interdire les valeurs dupliquées, par défaut créé sur la clé primaire de la table si aucune colonne n'est précisée.  
  
  ```
  create unique index XCLI_NCLI on CLIENT; 
  create index XCLI_LOC on CLIENT (LOCALITE);
  create index XCLI_LOC on CLIENT (LOCALITE asc);  
  ```
  
  * `create dbspace +nomDbSpace` à créer avant les tables qui se retrouveront dans cet espace de stockage
  
  ```
  create dbspace CLI_PRO_DAT;
  ...
  create table CLIENT ( . . . ) in CLI_PRO_DAT;
  ```
  
  
##### alter

* **Ajout, retrait et modification d'une colonne*

```
alter table PRODUIT add column POIDS smallint;
alter table PRODUIT drop column PRIX;
alter table CLIENT modify column CAT set '00';
alter table CLIENT modify column CAT drop default;
```
* **Ajout et retrait d'une contrainte
```
alter table NOMCOLONNE {add | modify | drop} + contrainte (cfr + haut)

alter table PROSPECT add primary key (NCLI);
alter table CLIENT   add unique (NOM,ADRESSE,LOCALITE);
alter table CLIENT   modify CAT not null;
alter table CLIENT   modify ADRESSE null;
alter table CLIENT   add foreign key (CAT) references CATEGORIE

alter table PROSPECT drop constraint PROPK;

```

##### Suppression

* `drop`

```
drop table COMMANDE;
drop index XCLI_LOC;
drop dbspace CLI_PRO_DAT;
alter table PRODUIT drop column PRIX;
alter table PROSPECT drop constraint PROPK;
```
