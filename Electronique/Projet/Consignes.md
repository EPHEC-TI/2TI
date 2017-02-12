# Consignes du projet
EPHEC - 2016/17 - T207 Électronique digitale, traitement de signal

## Finalité
* Elaborer une méthodologie de travail et planifier les activités en réalisant un projet permettant l’**interfaçage du système informatique avec un environnement extérieur**
* Analyser une situation donnée sous ses aspects techniques et **proposer des solutions qui tiennent compte des contraintes**
* **Mettre en œuvre des connaissances théoriques** acquises dans les cours d’électronique de première année et de deuxième année.
* Toucher aux aspects de conception et de **fabrication d’un produit électronique**
* Réaliser le travail demandé sur base du cahier de charges

## Consignes & Objectifs

* **Etablir un planning** décomposant le projet en étapes principales, et s'il y a lieu en sous-étapes, avec pour chacune d'elles une estimation des ressources nécessaires (matérielles et humaines), du temps de réalisation et de la date de réalisation
* **Présenter l'état d'avancement du projet** début mars par un rapport écrit (de 2 à 3 pages). Le dépôt du rapport doit être effectif au plus tard le 6 mars 2017. Les étudiants doivent aussi y suggérer les pistes envisagées pour la suite du développement du projet.
* **Présenter et défendre ce projet oralement** devant Mr Bouterfa et Mr Dewulf la dernière semaine de cours du quadrimestre (apprentissage par rapport au TFE). La date précise ainsi que l’horaire de passage seront précisés ultérieurement

## Cahier des Charges
### Produit demandé
Réaliser une carte avec un PIC récupérant une entrée analogique et définissant l’état de plusieurs sorties digitales, mais qui comporte aussi d’une part la sonde et d’autre part un affichage de la température aux côtés d’une information d’alarme.

### Spécifications Matérieles
* PIC : **18F458**
  * Programmable via l'interface RS232
* Sonde de température : **LM35**
  * La sonde doit fonctionner dans la gamme de température allant de 0° à 100°.
  * L’utilisation de la sonde pour les températures négatives peut être implémentée mais n’est pas obligatoire.
* Affichage de la température : il doit être fait sur deux afficheurs 7 segments à cathodes communes. Ces afficheurs se trouvent sur la carte aux côtés de la sonde.
* Alerte : elle est signalée par une LED rouge qui clignote.
* Lorsqu’aucune alerte n’est en cours, une LED verte doit rester en permanence allumée

### Spécifications Logiciels
* **Interface JAVA** : 
  * Doit afficher si une alerte est en cours ou pas. 
  * Doit permettre d’envoyer au PIC la valeur de la température au-delà de laquelle l’alerte est déclenchée.
  * Elle communique avec le PIC via le port RS232 du PC.
    * L’API « [RxTx](http://users.frii.com/jarvi/rxtx/) » sera utilisée pour piloter le port RS232 en java ([WIKI](http://rxtx.qbang.org/wiki/index.php/Main_Page))

### Listes non-hexaustive des composants
* Module « **Alimentation en 5V** »
* Module « **Sonde de température** »
* Module « **Affichage de la température** »
* Module « **PIC** » avec tous les composants annexes nécessaires à son bon fonctionnement
* Des **borniers ou pin headers** pour le transfert des signaux vers d’éventuelles autres cartes
* Module « **Alerte** »
* Module « **Interface Java** »
* ...

### Délivrables
* **Rapport intermédiaire** ( au plus tard le *6 mars 2017* )
  * 2 à 3 pages
  * **Objectifs** du projet
  * **Répartition** du travail au sein du groupe
  * Schéma électronique **finalisé** et **définitif** de la carte
  * Etat d’avancement général
  * Etat d’avancement de la simulation
  * Etat d’avancement de la programmation
* Le fichier **EAGLE** ( au plus tard le 6 mars 2017 )
  * Afin de permettre le tirages des PCB

* **Rapport final**
  * Coté Techniques
    * Le descriptif de la carte ainsi que son mode d'emploi
    * Les schémas électroniques
    * Les codes C (PIC) et JAVA
    * Les tests effectués et leurs résultats
    * Conformité (ou non) par rapport au cahier des charges
    * Caractéristiques techniques des éléments :
      * Sonde
      * PIC
      * Modules
  * Coté Projet
    * Le planning du projet
    * Les principaux problèmes rencontrés, ainsi que les solutions apportées
    * Les limites du système, les améliorations à y apporter
    * …
  * Le rapport doit être rendu en même temps que la présentation orale.

* **Présentation orale**
  * Présentation devant un jury de professeurs (dernière semaine de cours au mois de mai 2017) :
  * Démonstration du projet
  * Présentation sans transparents expliquant
    * Le travail réalisé, la conformité par rapport au cahier des charges
    * Les caractéristiques du projet
    * Les problèmes rencontrés et leurs solutions

    
* **Softwares et librairies**
  * **Simulateur électronique** : Proteus ISIS
  * **Dessin électronique** : Cadsoft Eagle
  * **Compilateur C pour PIC** : CCS PCWH Compiler ou autre (MPLAB® X IDE)
  * **Java** : Eclipse ou autre…
  * **Simulation de la liaison série** : Virtual Serial Ports Emulator
  * **Librairie** : java RxTx pour gérer en java la communication sur le port COM



