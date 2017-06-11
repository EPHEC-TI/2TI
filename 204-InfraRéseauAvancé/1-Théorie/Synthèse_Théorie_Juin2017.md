# CISCO
## Matière 
-[x] Spanning Tree	
-[x] DHCP en IPv6
- Sollicited Node
-[x] Access List
  - Particularités ACL en IPv6 
-[x] NAT
  - NAT en IPv6
-[x] Protocole OSPF multi-area et simple
- Protocole EIGRP
-[x] Cryptographique 
- IPSEC (VPN)
-[x] BPDU Guard


# Spanning Tree
Le STP est un protocole de couche 2 (liaison de données). Il permet de créer un chemin sans boucle dans un environnement commuté et physiquement redondant. STP détecte et désactivec les boucles et fournit un mécanisme de liens de sauvegarde. 

Ce protocole répond à la problématique de trames dupliquées. Il fonctionne de cette manière : 
- Sélection d'un switch root (principal)
- Calculs des chemins les plus courts vers ce switch
- Les ports des switches passent par plusieurs états dont : 
  - **Blocking** qui ne transfère pas de trames de données 
  - **Forwarding** qui transfère les trames de données
## Problématique 
Sans ce protocole, deux problèmes peuvent survenir : 
1. **Tempêtes de diffusion :** Lorsque des trames de diffusion sont envoyées, les switches envoient ces trames par tous les ports. Ces trames circulent en boucles et sont multipliées. Les trames n'ayant aucune durée de vie (TTL) peuvent tourner indéfiniment. 
  ![Figure2](http://cisco.goffinet.org/s3/stp1)
2. **Instabilité des tables MAC :** Quand une trame parvient aux switches connectés en redondance, le port d'origine risque d'être erroné,  un risque de boucle est donc possible. 
  ![Figure3](http://cisco.goffinet.org/s3/stp2)
> Dans cet exemple, le PC1 envoie une trame au PC2. Les deux commutateurs reçoivent la trame sur leur port 0/2 et associent ce port à l'adresse MAC de PC1. Si l'adresse de PC2 est inconnue, les deux commutateurs transfèrent la trame à travers leur port 0/1. Les commutateurs reçoivent respectivement ces trames inversement et associent l'adresse MAC de PC1 au port 0/1. Ce processus peut se répéter indéfiniment.

## Fonctionnement du STP
Pour éviter les boucles dans un réseau redondante, STP crée au sein de ce réseau un chemin sans boucle basé sur un chemin le plus court. Ce chemin est établi en fonction de la somme des coûts de liens entre les switches. Le coût est une valeur inverse à la vitesse d'un port car un lien rapide aura un coût moins élevé qu'un lien lent. Un chemin sans boucles implique que certaines ports soit bloqués. 
STP échange régulièrement des informations (**BPDU - Bridge Protocol Data Unit**) afin qu'une éventuelle modification du réseau puisse être adaptée sans boucle. 

### Sélection d'un switch Root 
Le switch root (principal) est le point central de l'arbre STP. Il a donc une importance particulière. 
Par défaut, le switch ayant l'ID le plus faible sera le Root. L'id est composé en 2 parties : 
- **Priorité** (2 octets)
- **Adresse MAC** (6 octets)

Sur le switch root, tous les ports sont des ports **Designated**, ils sont donc en état "fowarding", le switch envoie et reçoit le trafic.

### Sélection d'un port pour les switches non-routés
Les autres switches vont sélectionner un seul port Root qui aura le chemin le plus court vers le switch Root. 

### Sélection d'un port désigné pour chaque segment 
Pour chaque segment physique, domaine de collision ou lien, il y a un port **Designated**, il s'agit du chemin le plus rapide vers le switch Root. En état *"forwarding"*.
Les autres ports sont des **Non-Designated** en état "blocking". 

### En bref 
> - Un switch root par réseau dont tous les ports sont Designated
> - Un port Root par switch Non-Root
> - Un port Designated par domaine de collision (liaison)
> - Tous les autres ports sont bloqués.

## Différents états STP 
![Figure5](http://cisco.goffinet.org/s3/stp4)
1. **Etat "Blocking"**
  - Rejette toutes les trames de données venant du segment attaché
  - Rejette toutes les trames de données venant d'un autre port de transfert 
  - N'intègre aucun emplacement de station dans sa MAC table
  - Reçoit les BPDUs et les transmet à son système 
  - Répond à SNMP 
2. **Etat "Listening"**
  - Même états que **Blocking** sauf :
  - Envoie les BPDUs reçus de son système 
3. **Etat "Learning"**
  - Même états que **Learning** sauf :
  - Intègre les emplacements de station dans sa MAC table (apprentissage)
4. **Etat "Forwarding"**
  - Même états que **Forwarding** sauf :
  - Commute toutes les trames de données venant du segment attaché
  - Commute toutes les trames de données venant d'un autre port de transfert 
5. **Etat "Disabled"**
  - Similaire à "Blocking" sauf qu'il est éteint (physiquement non opérationnel)

## Bridge Protocol Data Units (BPDU)
Les switches s'échangent des BPDU de 2 types : 
- **Configuration :** utilisés lors des éléections, pour maintenir la connectivité entre les switches
- **Topology Change Notification (TCN) :** envoyés auprès d'un switch Root pour signaler des ruptures de liens. Quand un switch reçoit un TCN, un ACK est envoyé. 

# Access List
> CCNA2 - Chapitre 9 : ACL

Une liste de contôle d'accès (ACL) est une série de commandes qui déterminent si un roteur achemine ou abandonne les paquets en fonction des informations contenues dans l'en-tête di paquet. 
- Limite le trafic réseau pour accroître les performances réseau
- Contrôle le flux de trafic pour préserver la bande passante 
- Filtre le trafic en fonction de son type 
- Filtre les hôtes pour autoriser/refuser l'accès aux services sur le réseau
- Permet de classer le trafic par ordre de priorité

## Filtrage des paquets 
Le filtrage des paquets intervient sur les couches 3 et 4 *(Network et Transport)*. Il permet le contrôle de l'accès un à réseau en analysant les paquets entrants et sortants et en les transmettant ou en les refusant selon des critères tels que l'adresse IP source, IPs destinations, le protocole, etc. 
![aclFigure](https://puu.sh/wfFZW/7f2fb57bc7.png)

## Fonctionnement
Les ACL sont configurées pour s'appliquer au trafic entrant ou sortant. 
### ACL entrantes
Les paquets sont traités avant d'être routés vers l'interface de sortie. Elle est efficace car elle réduit la charge de recherches de routage en cas d'abandon du paquet. 
### ACL sortantes 
Les paquets sont d'abord acheminés vers l'interface de sortie et sont ensuite traités. Elles sont efficace lorsqu'un même filtre est appliqué aux paquets provenant de plusieurs  interfaces d'entrées.

## Types ACL 
Il existe 2 types d'ACL : 
1. **ACL standard** : qui refuse uniquement le trafic sur les IP sources 
  - Numérotée entre **1 et 99** et **1300 à 1999**
2. **ACL étendue** : qui permet un filtrage plus précis avec des règles
  - Numérotée entre **100 et 199** et **2000 à 2699** 
  - Elles peuvent être nommées 

## Création d'une ACL
Quelques instructions pour utiliser une ACL : 
- Utiliser sur les routeurs firewall entre réseau interne et externe, internet 
- Utiliser situé entre 2 section du réseau pour contrôler le trafic entrant et sortant 
- Configurer sur des routeurs périphériques permettant une protection contre le réseau externe 

### Règle des 3P 
- **ACL par protocole :** contrôle le flux du trafic d'une interface (IPv4 et IPv6) 
- **ACL par direction :** contrôle le trafic dans une seule direction. Une ACL pour le trafic entrant et une autre pour le trafic sortant.
- **ACL par interface :** contrôle le trafic d'une seule interface


| directive                                | Avantage                                 |
| ---------------------------------------- | :--------------------------------------- |
| Créer une ACL en rapport à la stratégie de sécurité de l'entreprise | Implémenter les instructions relatives à la sécurité organisationnelle |
| Description des tâches que devront effectuer les ACL | Eviter des créer des problèmes d'accès   |
| Utiliser éditeur de texte pour créer ses ACL | ACL réutilisables et faciles d'accès     |
| Tester avant d'implémenter               | Eviter de commettre des erreurs coûteuses |

## Positionnement ACL 
Le positionnement approprié d'une ACL peut optimiser l'efficacité du réseau. Elle peut être placée pour réduire le trafic superflu. 
- **ACL étendues :** Placé le plus près possible de la source de trafic à filtrer. 
- **ACL stantard :** Placé le plus près de la destination

Le positionnement peut aussi dépendre de : 
- **Contrôle de l'admin réseau** 
- **Bande passante des réseaux concernés** 
- **Facilité de configuration** 

## Logique d'utilisation ACL standard
![ACL](https://puu.sh/wfIs1/855aebef78.png)
```
access-list 2 deny 192.168.10.10
access-list 2 permit 192.168.10.0 0.0.0.255
access-list 2 deny 192.168.0.0 0.0.255.255
access-list 2 permit 192.0.0.0 0.255.255.255
```
## ACL étendues 
Les ACL étendues peuvent filtrer sur : 
- Adresse Source
- Adresse Destination
- Protocole
- Numéros de port 

Elles sont plus répandues que les ACL standards car elles permettent un meilleur contrôle. La possibilité de filtrer en fonction des protocoles et ports permet de créer des ACL précises. Une application peut être spécifiée via un port ou un nom. 
<u>Exemple :</u>Application FTP = port 21 ou nom ftp.

## ACL en IPv6 
Les ACL pour IPv6 sont très semblables aux ACL d'IPv4. Mais cependant, il n'existe qu'une seule type d'ACL : les **étendues** nommées. 
Une ACL IPv6 ne peut avoir le même nom qu'une IPv4.
### Comparaison des ACL IPv4 et IPv6 
Il existe 3 grandes différentes différences entre ces ACL : 
- **Application d'ACL IPv6 :** La commande *"ip access-group"* en IPv4 et la commande *"ipv6 traffic-filter"* 
- **Aucun masque générique :** IPv6 n'utilise pas de masque générique, au lieu de cela, la longueur de préfixe est utilisée pour indiquer dans quelle mesure l'adresse IPv6 source ou de destination doit correspondre. 
- **Instructions supplémentaires par défaut :**  utilisation de l'équivalent du protocole ARP de l'IPv4.
```
permit icmp any any nd-na
permit icmp aany any nd-ns
```

# DHCPv6 (Dynamic Host Config Protocol)
Comme en IPv4, les adresses de monodiffusion globale peuvent être configurées de manière **manuelle** ou **dynamique**, cependant il existe 2 méthodes d'attribution dynamique : 
- Configuration automatique des adresses sans état *(SLAAC)*
- Protocole DHCPv6 *(avec état)*

## SLAAC
Méthode qui permet à un périphérique d'obtenir une adresse IPv6 sans les services d'un réseau DHCP. SLAAC utilise des messages d'annonce et de sollicitation de routeur ICMPv6 pour fournir les informations d'adressage et d'autres informations de config. *(Normalement fournie par un DHCP)*
- **Message sollicitation routeur (RS) :** Envoie d'un message RS à tous les  routeurs pour l'obtention d'une adresse IPv6. 
- **Message annonce routeur (RA) :** Messages envoyés par les routeurs pour fournir des informations d'adressages aux clients. Le message RA inclut le préfixe et la longueur du préfixe du segment local. Les clients utilisent ces informations pour créer une adresse IPv6. Les routeurs envoient un RA à intervalles réguliers ou en réponse à un RS.

### Fonctionnement SLAAC 
![slaac](https://puu.sh/wg9I3/ca11377c5a.png)

## DHCPv6 sans état 
L'option DHCPv6 sans état enjoint le client à utiliser les informations dans le message RA pour l'adressage, mais les paramètres de config supplémentaires sont fournis par un serveur DHCP. 
Le client génère son IPv6 via les informations du RA ensuite il communique avec le DHCPv6 pour avoir des informations supplémentaires non fournies. On appele ce service DHCPv6 sans état car il ne converse aucune information sur l'état des clients (IPv6 disponible ou attribuée). Le DHCPv6 fournit uniquement des paramètres de configuration pour le client et non des adresses IPv6. 

## DHCPv6 avec état 
Proche dans le fonctionne du DHCPv4. Le message RA n'est pas utilisé pour la génération d'une IPv6 mais le client obtient son adresse IP auprès d'un DHCPv6 et celui-ci maintient les informations d'attributions. (comme en DHCPv4).

### Fonctionnement DHCPv6
Un message RA est envoyé pour savoir quel type de serveur DHCP doit être utilisé. Une fois les informations acquises, une requête est envoyé au serveur DHCPv6 selon les informations tirées du RA. le client utilise donc un DHCPv6 avec ou sans état de lien selon les informations qu'il possède

# NAT
Network Address Translation
## Fonction NAT 
La fonction du NAT principale consiste à limiter la consommation des adresses IPv4 publiques. Elle perme donc l'utilisation d'adresse IP privée en interne et une traduction de cette adresse en une adresse publique uniquement quand c'est nécessaire. Le NAT permet également d'ajouter de la confidentialité et de la sécurité à réseau. 

Un routeur NAT fonctionne généralement à la périphérie d'un réseau tronqué. Il s'agit d'un réseau ayant une seule connexion à son réseau voisin, avec un seul chemin pour émettre et recevoir. 
![nat](https://puu.sh/wgrG1/5f4dac6747.png)
La fonction NAT comprend 4 types d'adresses : 
- **Adresse locale interne** 
- **Adresse globale interne** 
- **Adresse locale externe** 
- **Adresse globale externe**

- **Adresse interne :** adresse du périphérique traduite via la NAT
- **Adresse externe :** adresse du périphérique de destination 
- **Adresse locale :** fait référence à toute adresse qui apparaît sur la partie interne du réseau
- **Adresse globale :** fait référence à toute adresse qui appaît sur la partie externe du réseau

## Types de NAT 
### NAT statique 
La NAT statique utilise un mappage de type un à un des adresses locales et globales. 
Elle est utile pour les serveurs Web ou les périphériques qui doivent posséder une adresse permantente accessible depuis internet. 
La NAT statique nécessite qu'il existe suffisamment d'adresses publiques disponibles pour satisfaire le nombre total de sessions utilisateur simultanées. 

### NAT dynamique 
La NAT dynamique utilise un pool d'adresses publiques et utilise FIFO. Lorsqu'une adresse privée doit être traduite, la NAT dynamique attribue une adresse IP publique disponible dans la pool. 

### PAT (Port Address Translation)
Egalement appelée **surchage NAT**, cette méthode mappe plusieurs adresses privée à une une adresse publique unique. (Utilisé chez les particuliers). 
Chaque adresse privée est associée à un numéro de port. Ce qui permet d'identifier le client qui essaye d'accéder au réseau externe. 
Le PAT garantit que chaque périphérique utile un numéro de port TCP différent pour chaque session ce qui facilite le renvoie d'une requête vers le bon client, par exemple. 

#### Port disponible 
Le PAT tente de conserver le port source d'origine. Cependant, si le port source est déjà utilisé, le PAT attribue le premier numéro de port disponible. 
Lorsqu'il n'y a plus de port disponible, et qu'il y a plusieurs adresses externes dans le pool, le PAT passe à l'adresse suivante. 

## Avantages de la NAT
- Conserve la schéma d'adressage officielement inscrit 
- Augmente la souplesse des connexions au réseau public 
- Assure la cohérence des schémas d'adressage du réseau interne 
- Garantit la sécurité du réseau 

## Inconvénients de la fonction NAT 
- Dégradation des performances 
- Dégradation de la fonctionnalité de bout en bout 
- Perte de la traçabilité IP de bout à bout 
- Complexification de la transmission tunnel 
- Perturbations éventuelles de l'établissement des connexions TCP

## NAT pour IPv6 
La NAT en IPv6 sert à fournir de façon transparente un accès entre un réseau IPv6-only et un réseau IPv4-only. Elle ne sert pas dans un cadre de traduction. 
Dans l'idéal, IPv6 doit être executé de manière native dans la mesure du possible, càd que les périphériques IPv6 communiquent entre eux sur des réseaux IPv6. Pour faciliter le passage d'IPv4 à IPv4, il existe plusieurs techniques de transition adaptées à différents scénarios de type IPv4 vers IPv6, y compris la double pile, la transmission tunnel et la traduction. 
- **Double pile :** périphériques exécutent des protocoles associés à l'IPv4 et l'IPv6 
- **Transmission tunnel :** processus d'encapsulation d'un paquet IPv6 à l'intérieur d'un paquet IPv4 pour transmettre un paquet IPv6 sur un réseau IPv4-only.

La NAT IPv6 est utilisé uniquement comme mécanisme de transition entre l'IPv4 et l'IPv6

# Protocole OSPF
Le procole OSPF (Open Shortest Path First) a été développé pour améliorer le routage. 
## OSPF à zone unique
### Caractéristiques 
- **Sans classe**
- **Efficace :** SPF utilisé, changement de routage > màj
- **Convergeance rapide :** diffuse rapidement les modifications
- **Evolutif**
- **Sécurisé**
### Structure de donnée 
- **Base de données de contiguîté** - Crée la table de voisinage 
- **Base de données d'états de liens** - Crée la table topologie
- **Base de données de réacheminement** - Crée la table de routage 
### Messages protocole de routage 
- Paquet **Hello** 
- Paquet **DBD (Database Description)** - Description base de données
- Paquet **LSR (Link-State Request)** - Demande état de liens 
- Paquet **LSU (Link-State Update** - Mise à jour état de liens
- Paquet **LSAck** - Accusé de réception d'état de liens 
### Etats OSPF 
![etats](https://puu.sh/wgvX4/3959198001.png)
- **État Down** - Envoie de paquets Hello, transition vers état Init
- **État Init** - Hello reçus, ID routeur, transition vers état Two-Way
- **État Two-Way** - Liaisons Ethernet, DR et BDR, transition état ExStart
- **État ExStart** - Négociation relation master/slave et numéro paquet DBD
- **État Exchange** - Routeurs échangent des paquets DBD, transition vers état full si info complète, état Loading si d'autres informations sont nécessaires
- **État Loading** - Paquets LSR/LSU donnent des infos supplémentaires, routes traitées (SPF)
- **État Full** - Routeurs convergés

### Problème OSPF à zone unique 
- Taille excessive de la table de routage 
- Taille excessive base de donnée états de liens 
- Fréquence élevée des calculs de l'algo SPF

## OSPF à zones multiples
Lorsqu'une zone OSPF de grande taille est divisée en zones plus petites, on parle de procole OSPF à zones multiples. Ce protocole permet de réduire la charge de traitement et de stockage. Cela permet donc de régler les prblèmes de OSPF unique. 

Un réseau OSPF à zones multiples nécessite une conception de réseau hiéarchique. La zone principale est appelée **zone fédératrice (zone 0)** et toutes les autres zones doivent être reliées. Cela permet une gestion de routage dans chaque zone et une mise à jour "simplifiée" sur les routeurs de la zone fédératrice. 
- Une zone ne doit pas contenir plus de 50 routeurs 
- Un routeur ne doit pas être inclus dans plus de 3 zones 
- Un routeur ne doit pas avoir plus de 60 voisins.

### Types de routeurs 
- **Routeur interne :** toutes les interfaces se situent dans la même zone
- **Routeur fédérateur :** situé dans la zone fédératrice
- **Routeur ABR (Area Border Router) :** routeurs ayant des interfaces dans plusieurs zones
- **Routeur ASBR (Autonomous System Boundary Routeur) :** routeur possèdant une interface associée à un interréseau externe. (utilisant un autre protocole)

### Propagation des messages LSA (types) 
#### Type 1 
![lsatype1](https://puu.sh/wgzOl/624182c707.png)
- Contiennent une liste des types de lien et des préfixes de réseau connectés directement
- Tous les routeurs émettent des LSA de type 1 
- Diffusée au sein de la zone et ne propagent pas au-delà du routeur ABR 
- ID états de lien LSA type 1 = ID routeur origine 
#### Type 2 
![lsatype2](https://puu.sh/wgzPx/1e4a61c65c.png)
- "Annonces de réseau"
- Existe seulement dans réseaux accès multiple 
- Seul un DR émet des LSA de type 2 
- Identifient les routeurs et adresses réseau des liens à accès multiple
- Diffusée au sein de la zone et ne propagent pas au-delà du routeur ABR 
- ID états de lien LSA type 2 = ID Routeur désigné 
#### Type 3
![lst3](https://puu.sh/wgzQv/687047a868.png)
- Décrit l'adresse réseau apprise par LSA type 1
- Requise pour chaque sous réseau
- Routeurs ABR diffusent les LSA dans d'autres zones ou sont générée par un ABR
- Identifié par l'adresse réseau
- Les routes ne sont pas récapitulées 
#### Type 4
![t4](https://puu.sh/wgzRz/963c8a8503.png)
- Annonce un routeur ASBR à d'autres zones et fournir une route vers le routeur ASBR
- ABR émettent ce type de LSA
- Genérée par le routeur ABR d'origine et à nouveau par d'autres routeurs ABR
#### Type 5 
![t5](https://puu.sh/wgzSw/a10da2a0f4.png)
- Annonce les adresses réseaux externes (non OSPF)
- ASBR émettent ce type de LSA
- Diffusées dans toute la zone et générées par d'autres routeurs ABR

## OSPFv3 (IPv6)
- **Anonces** - Route pour IPv6
- **Adresse source** - Messages OSPF fournis avec l'adresse link-local de l'int de sortie 
- **Adresse multidiffusion OSPF** - FF02::5
- **Adresse multidiffusion DR/BDR** - FF02::6 
- **Authentification** - Authentification IPv6
- **Routage monodiffusion** - Doit être activé

# Protocole EIGRP 


# Cryptographique 
## Principes de base
- Authentification des données
- Authentification de la source
- Intégrité
- Confidentialité 

## Clé symétrique
- Petite clé pour chiffrer l'information 
- Sécurisation rapide 
- 1000x plus rapide qu'un chiffrement asymétrique 

## Clé asymétrique 
- Permet de signer un document, chiffrement avec clé privé. 
- Permet d'échanger un clé symétrique à l'intérieur 
- Envoie un message seulement à la personne qui a cette clé privée 
- Haché le message pour savoir que le message envoyé par clé publique n'a pas été altéré

# IPSec (VPN)
> CCNA slides 402 à 418
> IPsec est un ensemble de règles utilisant d'autres protocles pour **sécuriser les communications** de données à travers un réseau **non sécurité** :
- **Authentification des données** - chiffrement symétrique
- **Authentification de la source** - chiffre asymétrique
  -** Intégrité** - signature
- **Confidentialité** - mot de passe, certificat, signature

## Mode 
### Transport 
L'en-tête de paquet IP est préservée pour permettre au routage de fonctionner de façon transparente. 
- Idéal pour le **site to site**
- Chiffrement de l'en-tête IP et données 
- Problème avec NAT (Couche 4 cryptée => pas de n° de port)
- Supporte IP, IPX, AppleTalk
- Autorise les multicasts dans le tunnel 
- Sans état (pas de contrôle de flux)
- Pas de sécurité
- Authentification en texte clair
### Tunnel 
La totatilité du paquet IP (en-tête + charge utile) est encapsulée et un nouvel en-tête de paquet IP est créé. 
- Idéal pour le **host to host**
- Chiffrement des données uniquement
- Souvent utilisé en GRE

### En-tête IPsec 
- **AH (Authentification Header)** 
  - Protocole IP 51
  - Authentification, intégrité, anti-rejeu
  - Pas de confidentialité (pas de chiffrement)
- **ESP (Encapsulation Security Payload)**
  - Protocole IP 50 
  - Confidentialité
  - Authentification, intégrité, anti-rejeu

## Fonctionnement 
### Phase 1 : établissement d'une association de sécurité (IKE)
IKE permet à deux extrémités en communication d'établir une association de sécurité. 
1. Négocie l'association de sécurité (SA) au travers d'un canal non sécurisé.  
  - **Aggressive Mode :** 1 échange = 3 paquets
    - rapide mais ID initiateur et répondeur transmis en clair.
      ![ipsec2](https://puu.sh/wgAXa/2ed6b961b9.png)
### Phase 1.5 : Extended Authentification (XAUTH)
Etape facultative permettant de fournir des éléments de configuration supplémentaires à un client VPN : adresses IPv4 ou IPv6, masque réseau, serveur DNS, etc.
### Phase 2 : IKE (IPsec-SA)
Se déroule sous la protection du tunnel ISAKMP établi en phase 1.
L'objectif est d'établir deux associations unidirectionnes entre les mêmes extrémités. Une fois ces 2 SA mis en place, le tunnel IPsec est actif. Les paramètres négociés sont quasiment les mêmes que à la phase 1.
- Négocie une clé au travers d'un canal sécurisé (phase 1)
- Négociation unidirectionnelle des assoc SA (AH/ESP)
- Etablissement des SA IPSec. 
- Renégociation périodique des SA IPSec. (délai ou quantité de donnée)
  ![ipsec2](https://puu.sh/wgAUZ/77973e352d.png)
### Phase 3 : Résiliation 
- Fin de vie des SAs
- Dépassement du compteur de paquet 
