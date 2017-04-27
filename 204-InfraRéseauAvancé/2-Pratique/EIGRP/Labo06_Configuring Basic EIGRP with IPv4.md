### Part 1: configure EIGRP

#### Step 1 :
`R1(config)# router eigrp 1` sur les trois routeurs 

**En EIGRP il est super important de respecter le numéro d'AS sur tous les routeurs de la même zone** 

*What is the range of numbers that can be used for AS numbers?*
+ 1 - 65535 ( use `router eigrp ?`)

#### Step 2 :
a. Subnet adresses ends with anything but /32

b. 


```
R1(config-router)# network 172.16.1.0 0.0.0.255
R1(config-router)# network 172.16.3.0 0.0.0.3
R1(config-router)# network 192.168.10.4 0.0.0.3
...
R2(config-router)#network 172.16.2.0 0.0.0.255
R2(config-router)#net
R2(config-router)#network 192.168.10.8 0.0.0.3
R2(config-router)#network 172.16.3.0 0.0.0.3
R2(config-router)#
%DUAL-5-NBRCHANGE: IP-EIGRP 1: Neighbor 172.16.3.1 (Serial0/0/0) is up: new adjacency
...
R3(config-router)#network 192.168.1.0 0.0.0.255
R3(config-router)#network 192.168.10.8 0.0.0.3
R3(config-router)#
%DUAL-5-NBRCHANGE: IP-EIGRP 1: Neighbor 192.168.10.9 (Serial0/0/1) is up: new adjacency
R3(config-router)#network 192.168.10.4 0.0.0.3
R3(config-router)#
%DUAL-5-NBRCHANGE: IP-EIGRP 1: Neighbor 192.168.10.5 (Serial0/0/0) is up: new adjacency

```

#### Step 3 :

```
R1(config-router)#passive-interface g0/0
...
R2(config-router)#passive-interface g0/0
...
R3(config-router)#passive-interface g0/0
```

#### Step 4 :

*Le problème est que des sous-réseaux sont utilisés. On a du 172.16.x.x, qui* 
*sont placés derrière plusieurs routeur. Donc eigrp va par exemple, résumer que 172.16.0.0/16*
*se trouve derrière R1, mais du coup il ne verra pas du tout les autres 172.16.x.x*
*et il enverra tout vers R1, dans le pire des cas il enverra une fois sur deux vers un* 
*des 172.16.x.x...*
```
R1(config-router)# no auto-summary
...
R2(config-router)# no auto-summary
...
R3(config-router)# no auto-summary
```

#### Step 4 :

```
R1#copy running-config startup-config 
...
R2#copy running-config startup-config 
Destination filename [startup-config]? startup-config
Building configuration...
[OK]
...
R3#copy running-config startup-config 
Destination filename [startup-config]? startup-config
Building configuration...
[OK]
```
### Part 2: Verify EIGRP Routing

#### Step 1 :

a. `R1#show ip eigrp neighbors `

b.
```
IP-EIGRP neighbors for process 1
H   Address         Interface      Hold Uptime    SRTT   RTO   Q   Seq
                                   (sec)          (ms)        Cnt  Num
0   172.16.3.2      Se0/0/0        13   00:22:12  40     1000  0   7
1   192.168.10.6    Se0/0/1        14   00:20:12  40     1000  0   14
```

#### Step 2 :

a-b.
```
R2#sh ip protocols 

Routing Protocol is "eigrp  1 " 
  Outgoing update filter list for all interfaces is not set 
  Incoming update filter list for all interfaces is not set 
  Default networks flagged in outgoing updates  
  Default networks accepted from incoming updates 
  Redistributing: eigrp 1
  EIGRP-IPv4 Protocol for AS(1)
    Metric weight K1=1, K2=0, K3=1, K4=0, K5=0
    NSF-aware route hold timer is 240
    Router-ID: 
    Topology : 0 (base)
      Active Timer: 3 min
      Distance: internal 90 external 170
      Maximum path: 4
      Maximum hopcount 100
      Maximum metric variance 1

  Automatic Summarization: disabled
  Automatic address summarization: 
  Maximum path: 4
  Routing for Networks:  
     172.16.2.0/24
     192.168.10.8/30
     172.16.3.0/30
  Passive Interface(s): 
    GigabitEthernet0/0
  Routing Information Sources:  
    Gateway         Distance      Last Update 
    172.16.3.1      90            1339261    
    192.168.10.10   90            1437179    
  Distance: internal 90 external 170
```
* Deux routeurs partagent des informations avec R2 
* `Routing Information Sources`
* `Maximum hopcount 100` Nombre de routeur maximum qu'on peut traveser avant de considérer la cible comme unreachable.

#### Step 3 :

Vous êtes assez grand pour faire des pings et vérifier votre connectivité de bout en bout <3 

### Part 4: Remarques (ajout personel)

* Selon cisco le labo est fini, seulement ils oublient d'attirer l'attention sur 
les routes résumées, comme on a annulé l'auto-summarisation, il faudrait le faire nous 
même. Alors dans ce petit réseau, c'est pas possible en fait car le réseau est petit et mal fait ...
mais dans un plus grand réseau ce serait obligatoire car c'est le seul avantage
d'EIGRP (mais un énorme avantage bien configuré).

* Ne pas oublier que CDP est activé de base sur les machines CISCO, et ainsi
même sans eigrp, avec `show cdp neighbors detail` on peut avoir plein d'info (trop?) sur les voisins.
```
R1#sh cdp neighbors detail 

Device ID: R1 LAN
Entry address(es): 
Platform: cisco 2960, Capabilities: Switch
Interface: GigabitEthernet0/0, Port ID (outgoing port): GigabitEthernet0/1
Holdtime: 179

Version :
Cisco IOS Software, C2960 Software (C2960-LANBASE-M), Version 12.2(25)FX, RELEASE SOFTWARE (fc1)
Copyright (c) 1986-2005 by Cisco Systems, Inc.
Compiled Wed 12-Oct-05 22:05 by pt_team

advertisement version: 2
Duplex: full
---------------------------

Device ID: R2
Entry address(es): 
  IP address : 172.16.3.2
Platform: cisco C1900, Capabilities: Router
Interface: Serial0/0/0, Port ID (outgoing port): Serial0/0/0
Holdtime: 127

Version :
Cisco IOS Software, C1900 Software (C1900-UNIVERSALK9-M), Version 15.1(4)M4, RELEASE SOFTWARE (fc2)
Technical Support: http://www.cisco.com/techsupport
Copyright (c) 1986-2012 by Cisco Systems, Inc.
Compiled Thurs 5-Jan-12 15:41 by pt_team

advertisement version: 2
Duplex: full
---------------------------

Device ID: R3
Entry address(es): 
  IP address : 192.168.10.6
Platform: cisco C1900, Capabilities: Router
Interface: Serial0/0/1, Port ID (outgoing port): Serial0/0/0
Holdtime: 130

Version :
Cisco IOS Software, C1900 Software (C1900-UNIVERSALK9-M), Version 15.1(4)M4, RELEASE SOFTWARE (fc2)
Technical Support: http://www.cisco.com/techsupport
Copyright (c) 1986-2012 by Cisco Systems, Inc.
Compiled Thurs 5-Jan-12 15:41 by pt_team

advertisement version: 2
Duplex: full
```

* Une commande pratique aussi est `show protocols`, qui permet d'avoir un résultat similaire
à `show ip int br` mais avec les informations de masques en plus ! 

```
R1#sh protocols 
Global values:
  Internet Protocol routing is enabled
GigabitEthernet0/0 is up, line protocol is up
  Internet address is 172.16.1.1/24
GigabitEthernet0/1 is administratively down, line protocol is down
Serial0/0/0 is up, line protocol is up
  Internet address is 172.16.3.1/30
Serial0/0/1 is up, line protocol is up
  Internet address is 192.168.10.5/30
Vlan1 is administratively down, line protocol is down
```