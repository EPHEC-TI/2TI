# Système
## Changer le mot de passe du compte root
Pré-requis : 
- Etre connecté en root sur le serveur
- Lancer la commande `passwd`
```bash
passwd
```
- Entrez le nouveau mot de passe souhaité
```bash
Enter new UNIX password: 
Retype new UNIX password: 
```
- Une confirmation vas ensuite apparaitre
```bash
passwd: password updated successfully
```
## Créer un nouvel utilisateur avec les droits administrateur
- Etre connecté en root ou avec un compte possédant déjà les droits administrateur
- Utiliser la commande `adduser`
```bash
adduser username
```
- Entrez et valider le mot de passe
```bash
Enter new UNIX password:
Retype new UNIX password:
passwd: password updated successfully
```
- Remplissez les informations de l'utilisateur ou laissez les blanches
```bash
Changing the user information for username
Enter the new value, or press ENTER for the default
    Full Name []:
    Room Number []:
    Work Phone []:
    Home Phone []:
    Other []:
Is the information correct? [Y/n]
```
- Ensuite utilisez la commande `usermod`pour donner les droits administrateur à l'utilisateur fraichement créé
```bash
usermod -aG sudo username
```
## Sécurisation
### Ajouter une clef publique (ROOT)
**Il est recommandé de laisser un terminal ouvert avec connexion `root` lorsque vous sécurisez votre serveur. Afin de ne pas ce retrouver bloqué en dehors de celui-ci**

En tant que "propriétaire" de votre serveur, il est recommander de n'ajouter que votre clef publique au compte `root` même si par après nous allons désactiver l'accès à celui-ci

- Etant connecté en root, naviguer vers : 
```bash
cd ~/.ssh
```
- Avec l'éditeur de votre choix, éditez le fichier `authorized_keys` et ajoutez-y votre clef publique, sur une ligne.
```bash
nano authorized_keys
```
- Ouvrez un autre terminal et connecter-vous en root à votre serveur. Si il ne vous demande pas de mot de passe, l'authentification par clef est activée et correctement configurée

### Ajouter une clef publique (Utilisateur)
- Naviguer dans le dossier utilisateur 
```bash
cd /home/username/
```
- Afficher le contenus complet de son dossier
```bash
ls -a
```
- Si le dossier `.ssh` n'existe pas, créez-le avec la commande `mkdir`
- Avec l'éditeur de votre choix, éditez ou créer le fichier `authorized_keys` et ajoutez-y votre clef publique, sur une ligne.
```bash
nano authorized_keys
```
- Il est important que les droits du dossier `.ssh` et du fichier `authorized_keys`
 - Dossier `.ssh` (Le propriétaire à tout les droits, les autres n'ont aucun accès)
 ```bash
chmod 700 /home/username/.ssh
 ```
 - Le fichier `authorized_keys` (Le propriétaire peut lire/ecrire dans le fichier mais ne peut pas l'executer)
 ```bash
chmod 600 /home/username/.ssh/authorized_keys
 ```
- Si vous avez effectué les commandes de création de dossier/fichier en `root` il est indispensable de nomme `username` propriétaire des dossiers/fichiers créés avec la commande `chown`
 ```bash
  chown -R username:username .ssh/
 ```
- Ouvrez un autre terminal et connecter-vous avec l'utilisateur à votre serveur. Si il ne vous demande pas de mot de passe, l'authentification par clef est activée et correctement configurée

- A partir de maintenant, ne vous connectez plus avec votre compte `root`, utilisez votre compte personnelle et la commande `sudo` lorsque vous avez temporairement besoins des droits administrateurs

## Désactiver la connection par mot de passe
**Il est recommandé de laisser un terminal ouvert avec connexion `root` lorsque vous sécurisez votre serveur. Afin de ne pas ce retrouver bloqué en dehors de celui-ci**

Afin d'éviter un accès non désiré à votre serveur, il est important de désactiver la connection par mot de passe.

- Naviguer dans le dossier : 
```bash
cd /etc/ssh/
```
- Editer la configuration du service SSH
```bash
sudo nano sshd_config
``` 
- Localisez la ligne contenant :
```bash
# Change to no to disable tunnelled clear text passwords
PasswordAuthentication yes
```
- Remplacez `yes` par `no`
- Ensuite relancer le service SSH
```bash
sudo service ssh restart
```
- Si vous essayez de vous connecter au serveur avec un ordinateur qui ne possède pas votre clef privée, vous obtiendrez ce message : 
```bash
Permission denied (publickey).
```
## Désactiver la connection au compte root
**Il est recommandé de laisser un terminal ouvert avec connexion `root` lorsque vous sécurisez votre serveur. Afin de ne pas ce retrouver bloqué en dehors de celui-ci**

Afin d'éviter un accès non désiré à votre serveur, il est important de désactiver la connection par mot de passe.

- Naviguer dans le dossier : 
```bash
cd /etc/ssh/
```
- Editer la configuration du service SSH
```bash
sudo nano sshd_config
``` 
- Localisez la ligne contenant :
```bash
# Authentication:
LoginGraceTime 120
PermitRootLogin yes
````
- Editer la ligne `PermitRootLogin` en remplaçant le `yes` par `no``
- Ensuite relancer le service SSH
```bash
sudo service ssh restart
```
- Si vous essayer de vous connecter en `root` vous obtiendrez ce message : 
```bash
ssh root@serveur
Permission denied (publickey).
```
## Changer le nom d'hôte (hotname)
- Etant connecté avec un utilisateur ayant les droits administrateur
- Utiliser la commande `hostname` 
 ```bash
 sudo hostname nomDeDomaine
 ```
- Relancer le serveur
 ```bash
 sudo reboot
 ```
