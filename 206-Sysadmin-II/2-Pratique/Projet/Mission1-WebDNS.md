# Dockify
EPHEC - 2016/17 - T2062 Administration système et réseaux II

## Première étape
Avant de commencer, il est important de mettre en place les outils utile à la réalisation du projet.
Il faut (entre autre) :
- [ ] Créer un repo Github (héberger les fichier de configuration)
- [ ] Créer un Wiki et penser à sa structure
- [ ] Convenir du moyen de communication du groupe
- [ ] Etablir une "**charte de collaboration**" : Implication, présence aux rendez-vous, respect des échéances, efficacité,..)
- [ ] Décider du support de rédaction du rapport (LatEx, Office, Google Docs,..)
- [ ] Répartir les responsabilité
  - [ ] Désigner un responsable par délivrables, par service, ... (Le responsable ne doit pas produire seul, mais c'est lui qui doit s'assurer que chacun réalise ce à quoi il s'est engagé et que les consignes sont respectée et que les délais sont respectés)

## Délivrables attendus
### A la fin de cette mission de **deux semaines**, il faut :
1. Faire une courte démo au professeur pour montrer ce qui est implémentée.
2. Remmetre un petit rapport au format PDF à destination du client, reformulant ses besoins et en justifiant la solution proposée (choix logiciels et des options de configuration)
3. Avoirs mis sur GitHub les fichiers de configurations (commenté !) nécéssaire pour déployer facilement le réseau sur un ou plusieurs VPS supportant Docker (Dockerfile et fichier de config spécifique aux services)
4. (Facultatif) Avoir mis sur DockerHub les images spécifiques que vous auriez éventuellement développés)
5. Avoir documenté la procédure d'installation (et toute informations intéréssante) sur le Wiki du repo
6. Fournir un schéma physique (répartition des containers sur les VPS) et un schéma logique (liens entre les services, addressage IP) de votre réseau

#### A la **fin du projet**
1. Défendre l'ensemble de votre infrastructure lors de l'examen
2. Remettre un rapport synthétisant les rapports intermédiaires remis au cours du semestre.
3. Avoir l'ensemble de la configuration disponible sur le repo GitHub/DockerHub
4. Avoir finalisé la documentation sur le Wiki du repo GitHub
5. Avoir complété les schémas réseau avec l'ensemble des services déployés

### Description du rapport attendus
Dans le petit rapport (2-3 pages max) devront figurer :
- Cahier des charges : Reformulation de la demande du client
- Traduction des besoins du client en langage informatique
- Proposition de solution techniques et comparaison éventuelle des alternatives possibles
- Choix et justification de la solution
- Rapport sur ce qui à été déployer (solution à moitié/totalement déployée, problèmes,...)

Le rapport est à destination du client, donc ne pas abusé des termes techniques

### Critères d'évaluation pour la démo
#### Critères minimaux
- [ ] Le site [www.wtX.ephec-ti.be](http://www.wtX.ephec-ti.be) est joignable depuis internet
- [ ] Le site [b2b.wtX.ephec-ti.be](http://b2B.wtX.ephec-ti.be) est joignable depuis internet et différent du site précédent.
- [ ]  Le serveur responsable du site B2B sait contcter le service de base de onnée nécéssaire à son fonctionnement
- [ ] Le site [intranet.wtX.ephec-ti.be](http://intranet.wtX.ephec-ti.be) est joignable depuis le poste d'un employé, mais pas depuis internet
- [ ] Les postes employés ont accès ) l'intranet et à Internet.

#### Critères avancés
- [ ] L'accès internet des employés est protégé par un Proxy
- [ ] Le proxy filtre les requêtes Web des employés
- [ ] Le proxy nécéssite que les employes s'authentifient avant d'aller sur internet
- [ ] Les site web sont protégés par un Reverse Proxy qui filtre les requêtes
- [ ] Les services réseaux directement joignable depuis internet sont isolés dans une DMZ
- [ ] Une solution de Firewall à été mise en place
- [ ] Une solution de Firewall à été mise en place et à été validée
- [ ] Les étudiants ont proposé et déployé d'autres éléments techniques conformes à la demande du client et justifiés
- [ ] ...
