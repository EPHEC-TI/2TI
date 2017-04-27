### Troubleshooting EIGRP for IPv4

* R1
  * Pas de voisins dans la table des voisins ...
  * La table de topologie n'est pas complète
  * la table de routage non plus (manque 209.165.201.0, route statique censée
  redistribuée par R2).
  * on voit que les bons réseaux ont été annoncé dans le processus eigrp 
  * subtile erreur, R1 a annoncé le mauvais numéro d'AS (11 alors que les autres sont à 1)
```
R1#sh ip eigrp neighbors 
IP-EIGRP neighbors for process 11

R1#sh ip eigrp topology 
IP-EIGRP Topology Table for AS 11/ID(172.31.40.233)

Codes: P - Passive, A - Active, U - Update, Q - Query, R - Reply,
       r - Reply status

P 172.31.10.0/24, 1 successors, FD is 2816
         via Connected, GigabitEthernet0/0
P 172.31.40.224/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/0
P 172.31.40.232/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/1

R1#sh run 
...				 
router eigrp 11
 passive-interface GigabitEthernet0/0
 network 172.31.10.0 0.0.0.255
 network 172.31.40.224 0.0.0.3
 network 172.31.40.232 0.0.0.3
 
R1#sh ip route
...
Gateway of last resort is not set

     172.31.0.0/16 is variably subnetted, 6 subnets, 3 masks
C       172.31.10.0/24 is directly connected, GigabitEthernet0/0
L       172.31.10.1/32 is directly connected, GigabitEthernet0/0
C       172.31.40.224/30 is directly connected, Serial0/0/0
L       172.31.40.225/32 is directly connected, Serial0/0/0
C       172.31.40.232/30 is directly connected, Serial0/0/1
L       172.31.40.233/32 is directly connected, Serial0/0/1
```

* R2 
  * pas de voisins eigrp 
  * table de topologie étrange/incomplète
  * table de routage incomplète (172.31.30.0 manquant)
  * ici on voit bien que R2 n'a pas annoncé tous les réseaux qu'il connaissait ...
```
R2>sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1

R2>sh ip eigrp topology 
IP-EIGRP Topology Table for AS 1/ID(209.165.201.1)

Codes: P - Passive, A - Active, U - Update, Q - Query, R - Reply,
       r - Reply status

P 0.0.0.0/0, 1 successors, FD is 6777856
         via Rstatic (6777856/0)
P 172.31.20.0/24, 1 successors, FD is 2816
         via Connected, GigabitEthernet0/0
P 172.31.40.224/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/0

R2#sh run 
...
router eigrp 1
 redistribute static 
 passive-interface GigabitEthernet0/0
 network 172.31.40.224 0.0.0.3
 network 172.31.20.0 0.0.0.255
!
ip route 0.0.0.0 0.0.0.0 209.165.201.2 
 
R2#sh ip route
Codes: L - local, C - connected, S - static, R - RIP, M - mobile, B - BGP
       D - EIGRP, EX - EIGRP external, O - OSPF, IA - OSPF inter area
       N1 - OSPF NSSA external type 1, N2 - OSPF NSSA external type 2
       E1 - OSPF external type 1, E2 - OSPF external type 2, E - EGP
       i - IS-IS, L1 - IS-IS level-1, L2 - IS-IS level-2, ia - IS-IS inter area
       * - candidate default, U - per-user static route, o - ODR
       P - periodic downloaded static route

Gateway of last resort is 209.165.201.2 to network 0.0.0.0

     172.31.0.0/16 is variably subnetted, 6 subnets, 3 masks
C       172.31.20.0/24 is directly connected, GigabitEthernet0/0
L       172.31.20.1/32 is directly connected, GigabitEthernet0/0
C       172.31.40.224/30 is directly connected, Serial0/0/0
L       172.31.40.226/32 is directly connected, Serial0/0/0
C       172.31.40.228/30 is directly connected, Serial0/0/1
L       172.31.40.229/32 is directly connected, Serial0/0/1
     209.165.201.0/24 is variably subnetted, 2 subnets, 2 masks
C       209.165.201.0/27 is directly connected, Serial0/1/0
L       209.165.201.1/32 is directly connected, Serial0/1/0
S*   0.0.0.0/0 [1/0] via 209.165.201.2
```

* R3
  * R3 déclare auto-summary alors qu'on travaille avec des subnets mal organisés (cfr labo 6 eigrp),
	mais déclare bien tous les réseaux connus.
  * Toujours rien dans la table de topologie 
	
```
R3#sh run 
...
router eigrp 1
 passive-interface GigabitEthernet0/0
 network 172.31.40.228 0.0.0.3
 network 172.31.40.232 0.0.0.3
 network 172.31.30.0 0.0.0.255
 auto-summary
 
R3#sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1

R3#sh ip eigrp top
IP-EIGRP Topology Table for AS 1/ID(172.31.40.234)

Codes: P - Passive, A - Active, U - Update, Q - Query, R - Reply,
       r - Reply status

P 172.31.30.0/24, 1 successors, FD is 2816
         via Connected, GigabitEthernet0/0
P 172.31.40.228/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/1
P 172.31.40.232/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/0

R3# sh ip route
...

Gateway of last resort is not set

     172.31.0.0/16 is variably subnetted, 6 subnets, 3 masks
C       172.31.30.0/24 is directly connected, GigabitEthernet0/0
L       172.31.30.1/32 is directly connected, GigabitEthernet0/0
C       172.31.40.228/30 is directly connected, Serial0/0/1
L       172.31.40.230/32 is directly connected, Serial0/0/1
C       172.31.40.232/30 is directly connected, Serial0/0/0
L       172.31.40.234/32 is directly connected, Serial0/0/0
```
#### Solution

##### R1 
* enlever la configuration de routage pour l'AS 11 pour pouvoir la remettre dans 
l'AS 1.

```
no router eigrp 11
router eigrp 1
 passive-interface GigabitEthernet0/0
 network 172.31.10.0 0.0.0.255
 network 172.31.40.224 0.0.0.3
 network 172.31.40.232 0.0.0.3
```

##### R2

* Annoncé le réseaux manquant
```
router eigrp 1 
	network 172.31.40.228 0.0.0.3
```

##### R3

* Désactiver l'auto-summary

```
router eigrp 1
	no auto-summary
```
