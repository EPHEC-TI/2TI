# Cahier des charges (Client)
WoodyToys souhaite proposer une boîte mail à l'ensemble de ses employés. Chaque employé doit donc disposer d'une adresse mail type [nom.prenom@wtX.ephec-ti.be](mailto:nom.prenom@wtX.ephec-ti.be). En plus de cela, l'entreprise souhaite également créer des adresses mail de service génériques ([contact@wtX.ephec-ti.be](mailto:contact@wtX.ephec-ti.be), [b2b@wtX.ephec-ti.be](mailto:b2b@wtX.ephec-ti.be) par exemple), qui seront redirigée au besoin vers le ou les employés responsables du service en question.

Les employés doivent pouvoir consulter leur courrier électronique et en envoyer à l'aide d'un client mail classique (Client Lourd) depuis l'entreprise. Mais il doivent également être en mesure d'utiliser leur mail en déplacement ou à domicile.

# Cahier des charges (Technique)
- Besoin client :
  - Créer un serveur mail pour le domaine `wtX.ephec-ti.be`
    - Un serveur d'envoi `SMTP`
    - Un serveur de réception (`IMAP`/`POP`)
  - Chaque employé doit disposer d'un compte propre `nom.prenom@wtX.ephec-ti.be`
  - Pour les différents services (b2b, contact, ...) un adresse email doit être disponible et redirigé vers le responsable du service.
  - Les adresse doivent être accessible en **IMAP** et en POP
  - La mise en place d'un webmail.
  - Le serveur doit être accessible depuis internet
 - Sécurité :
    - Le serveur SMTP ne relaye que les mails de l'organisation (authentification)
    - Mettre en place un filtre anti-spam
    - Vérifier et valider la structure de l'ensemble de l'installation grâce à [**DockerBench**](https://github.com/docker/docker-bench-security)

# Prototype à produire

Il nous est demandé de proposer une architecture permettant la mise en place du service mail décrit dans le cahier des charges. Le prototype de démonstration sera installé dans notre réseau de VPS et utilisera le nom de domaine `wt16.ephec-ti.be`


# Délivrables attendus
## A la fin de cette mission de **deux semaines**, il faut :
1. Faire une courte démo au professeur pour montrer ce qui est implémentés
1. Remettre un petit rapport au format PDF à destination du client, reformulant ses besoins et en justifiant la solution proposée (choix logiciels et des options de configuration)
1. Avoirs mis sur GitHub les fichiers de configurations (commenté !) nécéssaire pour déployer facilement le réseau sur un ou plusieurs VPS supportant Docker (Dockerfile et fichier de config spécifique aux services)
1. (Facultatif) Avoir mis sur DockerHub les images spécifiques que vous auriez éventuellement développés)
1. Avoir documenté la procédure d'installation (et toute informations intéréssante) sur le Wiki du repo
1. Fournir une mise à jour du schéma physique (répartition des containers sur les VPS) et un schéma logique (liens entre les services, addressage IP) de notre réseau

## Critères d'évaluation pour la démo
### Critères minimaux
- [ ] Les employés disposent d'une adresse professionnelle
- [ ] Un employé peut envoyer un mail depuis un client mail à l'intérieur de l'entreprise en utilisant son adresse professionnelle
- [ ] Un employé peut consulter les courriers reçus sur son adresse professionnelle depuis l'entreprise
- [ ] Un employé peut **envoyer** un mail depuis son domicile en utilisant son adresse professionnelle
- [ ] Un employé peut **consulter** ses mails depuis son domicile en utilisant son adresse professionnelle
- [ ] Il existe au moins une adresse générique (`contact@wtX.ephec-ti.be`) qui redirige le courrier reçus vers une adresse d'un employé

### Critères avancés
- [ ] Les emplacements des serveurs mails sont choisis en tenant compte de la sécurité (DMZ ou autre)
- [ ] Des mécanismes de proxy ont été utilisés
- [ ] Le relai SMTP ne relaye que les mails des employés
- [ ] Un portail Webmail a été mis en place
- [ ] Des filtres anti-spam ont été déployés
- [ ] ...
