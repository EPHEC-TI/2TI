**/!\ Problème d'adressage, il ne faut pas croire les annotations sur le schéma,
c'est du bullshit même leur tableau est pas bon, ne se référer qu'à ce
que vous voyez sur les machines et en ligne de commande <3 love cisco <3, 
si qqun est chaud refaire une table d'adressage qu'il se fasse plaiz moi j'ai la flemme !**

### Part 1: Verify EIGRP Configuration

#### Step 1 : Examine the routing tables of each router and verify that there is a path to every network in the topology.

* `R1#show ip route`

```
R1>sh ip route 

Gateway of last resort is not set

     172.16.0.0/16 is variably subnetted, 5 subnets, 3 masks
C       172.16.1.0/24 is directly connected, GigabitEthernet0/0
L       172.16.1.254/32 is directly connected, GigabitEthernet0/0
D       172.16.2.0/24 [90/2170112] via 172.16.3.2, 00:03:43, Serial0/0/0
C       172.16.3.0/30 is directly connected, Serial0/0/0
L       172.16.3.1/32 is directly connected, Serial0/0/0
D    192.168.1.0/24 [90/2170112] via 192.168.10.6, 00:03:40, Serial0/0/1
     192.168.10.0/24 is variably subnetted, 3 subnets, 2 masks
C       192.168.10.4/30 is directly connected, Serial0/0/1
L       192.168.10.5/32 is directly connected, Serial0/0/1
D       192.168.10.8/30 [90/2681856] via 172.16.3.2, 00:03:43, Serial0/0/0
                        [90/2681856] via 192.168.10.6, 00:03:40, Serial0/0/1
...
R2>sh ip route 

Gateway of last resort is not set

     172.16.0.0/16 is variably subnetted, 5 subnets, 3 masks
D       172.16.1.0/24 [90/2170112] via 172.16.3.1, 00:05:20, Serial0/0/0
C       172.16.2.0/24 is directly connected, GigabitEthernet0/0
L       172.16.2.254/32 is directly connected, GigabitEthernet0/0
C       172.16.3.0/30 is directly connected, Serial0/0/0
L       172.16.3.2/32 is directly connected, Serial0/0/0
D    192.168.1.0/24 [90/2170112] via 192.168.10.10, 00:05:20, Serial0/0/1
     192.168.10.0/24 is variably subnetted, 3 subnets, 2 masks
D       192.168.10.4/30 [90/2681856] via 192.168.10.10, 00:05:20, Serial0/0/1
                        [90/2681856] via 172.16.3.1, 00:05:17, Serial0/0/0
C       192.168.10.8/30 is directly connected, Serial0/0/1
L       192.168.10.9/32 is directly connected, Serial0/0/1
...
R3>sh ip route

Gateway of last resort is not set

     172.16.0.0/16 is variably subnetted, 3 subnets, 2 masks
D       172.16.1.0/24 [90/2170112] via 192.168.10.5, 00:05:47, Serial0/0/0
D       172.16.2.0/24 [90/2170112] via 192.168.10.9, 00:05:50, Serial0/0/1
D       172.16.3.0/30 [90/2681856] via 192.168.10.9, 00:05:50, Serial0/0/1
                      [90/2681856] via 192.168.10.5, 00:05:47, Serial0/0/0
     192.168.1.0/24 is variably subnetted, 2 subnets, 2 masks
C       192.168.1.0/24 is directly connected, GigabitEthernet0/0
L       192.168.1.254/32 is directly connected, GigabitEthernet0/0
     192.168.10.0/24 is variably subnetted, 4 subnets, 2 masks
C       192.168.10.4/30 is directly connected, Serial0/0/0
L       192.168.10.6/32 is directly connected, Serial0/0/0
C       192.168.10.8/30 is directly connected, Serial0/0/1
L       192.168.10.10/32 is directly connected, Serial0/0/1

```

* Oui on peut observer dans les tables de routages, des lignes ou deux chemins
de même poids sont précisés, donc on fera du load-balancing, un autre moyen de 
l'observer est d'utiliser la commande `traceroute`. On observe bien que pour la même
destination, on emprunte un chemin 1 fois sur 2.
```
R1>traceroute 192.168.10.10
Type escape sequence to abort.
Tracing the route to 192.168.10.10

  1   172.16.3.2      5 msec    3 msec    1 msec    
R1>traceroute 192.168.10.10
Type escape sequence to abort.
Tracing the route to 192.168.10.10

  1   192.168.10.6    1 msec    0 msec    1 msec    
R1>traceroute 192.168.10.10
Type escape sequence to abort.
Tracing the route to 192.168.10.10

  1   172.16.3.2      1 msec    0 msec    0 msec    
R1>traceroute 192.168.10.10
Type escape sequence to abort.
Tracing the route to 192.168.10.10

  1   192.168.10.6    6 msec    1 msec    4 msec  
```

#### Step 2: Verify that each router has entries in its neighbor table.

```
R1>sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1
H   Address         Interface      Hold Uptime    SRTT   RTO   Q   Seq
                                   (sec)          (ms)        Cnt  Num
0   172.16.3.2      Se0/0/0        11   00:00:44  40     1000  0   7
1   192.168.10.6    Se0/0/1        14   00:00:42  40     1000  0   10
...
R2#sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1
H   Address         Interface      Hold Uptime    SRTT   RTO   Q   Seq
                                   (sec)          (ms)        Cnt  Num
0   172.16.3.1      Se0/0/0        13   00:01:15  40     1000  0   13
1   192.168.10.10   Se0/0/1        13   00:01:14  40     1000  0   9
...
R3>sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1
H   Address         Interface      Hold Uptime    SRTT   RTO   Q   Seq
                                   (sec)          (ms)        Cnt  Num
0   192.168.10.9    Se0/0/1        14   00:01:47  40     1000  0   8
1   192.168.10.5    Se0/0/0        12   00:01:46  40     1000  0   14
```
#### Step 3: Analyze the topology table of each router.

* a. `show ip eigrp topology`
Je comprends pas bien la question de cisco mais on peut observer que chaque route 
vers un réseau possède un successor qui est l'équivalent du next-hop. Seule le
réseau 192.168.10.8 possède deux successors, seulement car dans la table de 
routage il y a deux routes vers ce réseau équivalente et qu'il fait du load-balancing.
On peut observer qu'il n'y a pas de faisible successor.

* b.
```
R1#sh ip eigrp topology 
IP-EIGRP Topology Table for AS 1/ID(192.168.10.5)

Codes: P - Passive, A - Active, U - Update, Q - Query, R - Reply,
       r - Reply status

P 172.16.1.0/24, 1 successors, FD is 2816
         via Connected, GigabitEthernet0/0
P 172.16.2.0/24, 1 successors, FD is 2170112
         via 172.16.3.2 (2170112/2816), Serial0/0/0
P 172.16.3.0/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/0
P 192.168.1.0/24, 1 successors, FD is 2170112
         via 192.168.10.6 (2170112/2816), Serial0/0/1
P 192.168.10.4/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/1
P 192.168.10.8/30, 2 successors, FD is 2681856
         via 172.16.3.2 (2681856/2169856), Serial0/0/0
         via 192.168.10.6 (2681856/2169856), Serial0/0/1
```

### Part 2: Observe the EIGRP DUAL FSM

#### Step 1: On R1, turn on the debugging feature that will display DUAL FSM notifications.

* `R1#debug eigrp fsm`

**Dans la pratique il vaut toujours mieux lancer la commande `undebug all`, avant
de lancer une commande debug. En effet, dans la réalité, il peut y avoir tellement
de message qui apparraissent en debug qu'il est quasi impossible de lancer la commande
d'arrêt par après.**

#### Step 2: Force a DUAL FSM update to generate debug output.

* on R3 
```
```

```
R3(config)# interface s0/0/0
R3(config-if)# shutdown
...
R1#
%LINK-5-CHANGED: Interface Serial0/0/1, changed state to down

%LINEPROTO-5-UPDOWN: Line protocol on Interface Serial0/0/1, changed state to down

%DUAL-5-NBRCHANGE: IP-EIGRP 1: Neighbor 192.168.10.6 (Serial0/0/1) is down: interface down

DUAL: linkdown: start - 192.168.10.6 via Serial0/0/1

DUAL: Destination 192.168.1.0/24

DUAL: rcvupdate: 192.168.1.0/24 via 192.168.10.6 metric 4294967295/4294967295

DUAL: Find FS for dest: 192.168.1.0/24. FD is 2170112, RD is 2816

DUAL:  	0.0.0.0 metric 4294967295/4294967295 not found Dmin is 4294967295

DUAL: Destination 192.168.10.8/30

DUAL: rcvupdate: 192.168.10.8/30 via 192.168.10.6 metric 4294967295/4294967295

DUAL: Find FS for dest: 192.168.10.8/30. FD is 2681856, RD is 2169856

DUAL: linkdown: finish

DUAL: rcvupdate: 192.168.10.4/30 via 0.0.0.0 metric 4294967295/4294967295

DUAL: Find FS for dest: 192.168.10.4/30. FD is 2169856, RD is 0

DUAL:  	0.0.0.0 metric 4294967295/4294967295 not found Dmin is 4294967295

DUAL: ifdelete: Serial0/0/1 is being deleted
DUAL: dual_ifdelete(): finish

DUAL: rcvupdate: 192.168.1.0/24 via 172.16.3.2 metric 2682112/2170112

DUAL: Find FS for dest: 192.168.1.0/24. FD is 4294967295, RD is 4294967295

DUAL: RT installed 192.168.1.0/24 via 172.16.3.2
DUAL: Send update about 192.168.1.0/24.  Reason: metric chg

DUAL: rcvupdate: 192.168.10.4/30 via 172.16.3.2 metric 3193856/2681856

DUAL: Find FS for dest: 192.168.10.4/30. FD is 4294967295, RD is 4294967295

DUAL: RT installed 192.168.10.4/30 via 172.16.3.2
DUAL: Send update about 192.168.10.4/30.  Reason: metric chg

DUAL: rcvupdate: 192.168.10.4/30 via 172.16.3.2 metric 4294967295/4294967295

DUAL: Find FS for dest: 192.168.10.4/30. FD is 3193856, RD is 2681856

DUAL:  	0.0.0.0 metric 4294967295/4294967295 not found Dmin is 4294967295

DUAL: Dest 192.168.10.4/30 (No peers) not entering active state.
DUAL: Removing dest 192.168.10.4/30, nexthop 0.0.0.0
DUAL: No routes.  Flushing dest 192.168.10.4/30

```

#### Step 3: Display the routing table of R1.
* `R1#show ip route`
```
R1#sh ip route
...
     172.16.0.0/16 is variably subnetted, 5 subnets, 3 masks
C       172.16.1.0/24 is directly connected, GigabitEthernet0/0
L       172.16.1.254/32 is directly connected, GigabitEthernet0/0
D       172.16.2.0/24 [90/2170112] via 172.16.3.2, 00:16:02, Serial0/0/0
C       172.16.3.0/30 is directly connected, Serial0/0/0
L       172.16.3.1/32 is directly connected, Serial0/0/0
D    192.168.1.0/24 [90/2682112] via 172.16.3.2, 00:16:02, Serial0/0/0
     192.168.10.0/30 is subnetted, 1 subnets
D       192.168.10.8/30 [90/2681856] via 172.16.3.2, 00:16:02, Serial0/0/0
```

* on remarque en effet qu'il n'y a plus de route vers 192.168.10.4, de plus, 
il n'y a plus qu'une seule route pour 192.168.10.8 (cfr plus haut).

#### Step 4: Determine the difference in the topology table.

```
R1#sh ip eigrp topology 
IP-EIGRP Topology Table for AS 1/ID(192.168.10.5)

Codes: P - Passive, A - Active, U - Update, Q - Query, R - Reply,
       r - Reply status

P 172.16.1.0/24, 1 successors, FD is 2816
         via Connected, GigabitEthernet0/0
P 172.16.2.0/24, 1 successors, FD is 2170112
         via 172.16.3.2 (2170112/2816), Serial0/0/0
P 172.16.3.0/30, 1 successors, FD is 2169856
         via Connected, Serial0/0/0
P 192.168.1.0/24, 1 successors, FD is 2682112
         via 172.16.3.2 (2682112/2170112), Serial0/0/0
P 192.168.10.8/30, 1 successors, FD is 2681856
         via 172.16.3.2 (2681856/2169856), Serial0/0/0
```
* Plus que 5 entrées dans la table de topomlogie, le réseau 192.168.10.4 n'y figure plus.
De plus, plus qu'un seul successeur pour le réseau 192.168.10.8.

#### Step 5: Document changes in each router’s neighbor table.

```
R1#sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1
H   Address         Interface      Hold Uptime    SRTT   RTO   Q   Seq
                                   (sec)          (ms)        Cnt  Num
0   172.16.3.2      Se0/0/0        14   00:27:19  40     1000  0   59
...
R2>sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1
H   Address         Interface      Hold Uptime    SRTT   RTO   Q   Seq
                                   (sec)          (ms)        Cnt  Num
0   192.168.10.10   Se0/0/1        14   00:27:46  40     1000  0   55
1   172.16.3.1      Se0/0/0        14   00:27:46  40     1000  0   56
...
R3>sh ip eigrp neighbors 
IP-EIGRP neighbors for process 1
H   Address         Interface      Hold Uptime    SRTT   RTO   Q   Seq
                                   (sec)          (ms)        Cnt  Num
0   192.168.10.9    Se0/0/1        12   00:28:04  40     1000  0   60
```
* Plus qu'une entrée dans la table des voisins pour R1 et R3, logique.

#### Step 6: Restore connectivity between R1 and R2

```
R3(config)#interface S0/0/0
R3(config-if)#no shut

R3(config-if)#
%LINK-5-CHANGED: Interface Serial0/0/0, changed state to up
...
%LINEPROTO-5-UPDOWN: Line protocol on Interface Serial0/0/0, changed state to up

%DUAL-5-NBRCHANGE: IP-EIGRP 1: Neighbor 192.168.10.5 (Serial0/0/0) is up: new adjacency
...
R1#
%LINK-5-CHANGED: Interface Serial0/0/1, changed state to up

%LINEPROTO-5-UPDOWN: Line protocol on Interface Serial0/0/1, changed state to up

DUAL: rcvupdate: 192.168.10.4/30 via Connected metric 2169856/0

DUAL: Find FS for dest: 192.168.10.4/30. FD is 4294967295, RD is 4294967295

DUAL: RT installed 192.168.10.4/30 via 0.0.0.0
DUAL: Send update about 192.168.10.4/30.  Reason: metric chg

DUAL: Send update about 192.168.10.4/30.  Reason: new if

DUAL: rcvupdate: 172.16.1.0/24 via Connected metric 2816/0

DUAL: Find FS for dest: 172.16.1.0/24. FD is 2816, RD is 0

DUAL: Send update about 172.16.1.0/24.  Reason: new if

DUAL: rcvupdate: 172.16.3.0/30 via Connected metric 2169856/0

DUAL: Find FS for dest: 172.16.3.0/30. FD is 2169856, RD is 0

DUAL: Send update about 172.16.3.0/30.  Reason: new if

DUAL: rcvupdate: 192.168.10.4/30 via 172.16.3.2 metric 4294967295/4294967295

DUAL: Find FS for dest: 192.168.10.4/30. FD is 2169856, RD is 0

%DUAL-5-NBRCHANGE: IP-EIGRP 1: Neighbor 192.168.10.6 (Serial0/0/1) is up: new adjacency

DUAL: rcvupdate: 192.168.10.4/30 via 192.168.10.6 metric 4294967295/4294967295

DUAL: Find FS for dest: 192.168.10.4/30. FD is 2169856, RD is 0

DUAL: rcvupdate: 172.16.1.0/24 via 192.168.10.6 metric 3194112/2682112

DUAL: Find FS for dest: 172.16.1.0/24. FD is 2816, RD is 0

DUAL: rcvupdate: 172.16.2.0/24 via 192.168.10.6 metric 2682112/2170112

DUAL: Find FS for dest: 172.16.2.0/24. FD is 2170112, RD is 2816

DUAL: rcvupdate: 172.16.3.0/30 via 192.168.10.6 metric 3193856/2681856

DUAL: Find FS for dest: 172.16.3.0/30. FD is 2169856, RD is 0

DUAL: rcvupdate: 192.168.1.0/24 via 192.168.10.6 metric 2170112/2816

DUAL: Find FS for dest: 192.168.1.0/24. FD is 2682112, RD is 2170112

DUAL: RT installed 192.168.1.0/24 via 192.168.10.6
DUAL: Send update about 192.168.1.0/24.  Reason: metric chg

DUAL: rcvupdate: 192.168.10.8/30 via 192.168.10.6 metric 2681856/2169856

DUAL: Find FS for dest: 192.168.10.8/30. FD is 2681856, RD is 2169856

DUAL: rcvupdate: 172.16.1.0/24 via 192.168.10.6 metric 4294967295/4294967295

DUAL: Find FS for dest: 172.16.1.0/24. FD is 2816, RD is 0
```

* Je pense que c'est cette ligne la qui indique un changement dans la table de routage: 
`DUAL: RT installed 192.168.1.0/24 via 192.168.10.6`. 
* Une fois la route vers R1 récupérée, DUAL FSM recalcule tous les Feasible successor
et choisit les meilleurs comme successor dans sa table de topologie.
D'ou l'importance d'un réseau bien configuré, en effet cela évitera d'avoir 10000
requête et réponse entre routeurs à chaque changement de topologie. 