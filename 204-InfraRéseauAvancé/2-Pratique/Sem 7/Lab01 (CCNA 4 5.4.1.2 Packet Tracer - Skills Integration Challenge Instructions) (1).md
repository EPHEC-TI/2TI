## Skills integration Challenge 

> J'arrive pas a plus que 66/70, à cause des merdiers de package a installé pour les commandes ssh, en gros la commande `license boot ...` expliquée par mr schalkwijk en fonctionne que sur les routeurs et on nous demande une configuration avancée ssh sur un switch, sombre histoire ....

![table d'adressage](table_adressage.PNG)  

![réseau](reseau.PNG)

```
!------------------------------------------------------------------------
Admin-Sw
!------------------------------------------------------------------------
!
ip domain-name cisco.com
!
username Admin password letmein
service password-encryption
!
crypto key generate rsa
1024
!
ip ssh version 2
! les 2 commandes suivante n'est disponible dans packet tracer que après activation de 
! licence et permettent respectivement de limiter a 2 tentatives d'authentification ssh
! et de terminer la possibillité d'authentification après 60 secondes
! ssh login-attempts 2
! ssh login-gracetime 60
!
ip default-gateway 10.10.10.145
!
line vty 0 15
    password letmein
    login 
    transport input ssh
    exit
! VLAN
! 
vlan 15
    name Servers
vlan 30
    name PCs
vlan 45
    name Native
vlan 60
    name Management
    exit
!
interface range f0/11-20
    switchport mode access
    switchport access vlan 15
    no shut
    exit
!
interface range f0/1-10
    switchport mode access
    switchport access vlan 30
    no shut
    exit
!   
interface G0/1
    switchport mode trunk
    switchport trunk native vlan 45
    no shut
    exit
!
interface G0/2
    shutdown
    exit
!
interface vlan 60
    ip address 10.10.10.146 255.255.255.240
    no shut
    exit
!
interface F0/1
    switchport port-security
    switchport port-security maximum 2
    switchport port-security mac-address sticky
    switchport port-security violation restrict
    exit
!
interface F0/21
    shutdown
!------------------------------------------------------------------------
Admin
!------------------------------------------------------------------------
! routage inter-vlan
!
interface G0/0
    no ip address
    no shut
interface G0/0.15
    description "Vlan serveurs"
    encapsulation dot1q 15
    ip address 10.10.10.161 255.255.255.224
    ip nat inside
    no shut
!
interface G0/0.30
    description "vlan PC"
    encapsulation dot1q 30
    ip address 10.10.10.193 255.255.255.192
    ip nat inside
    no shut
!
interface G0/0.45
    description "vlan natif"
    encapsulation dot1q 45 native
    ip address 10.10.10.129 255.255.255.240
    no shut
!
interface G0/0.60
    description "vlan de gestion"
    encapsulation dot1q 60 
    ip address 10.10.10.145 255.255.255.240
    no shut
    exit
!
interface S0/0/0
    ip nat inside
    exit
!
interface S0/0/1
    ip nat inside
    exit
!
interface S0/1/0
    ip nat outside
    exit

! DHCP 
! 
! pour vérifier si cette partie fonctionne, aller sur le pc ITSupport et configurer son 
! adresse ip en DHCP, normalement il devrait recevoir une adresse automatiquement
ip dhcp pool LAN
    network 10.10.10.192 255.255.255.192
    default-router 10.10.10.193
    exit
!
ip dhcp excluded-address 10.10.10.193
!
! ROUTAGE 
!
ip route 0.0.0.0 0.0.0.0 S0/1/0
router ospf 1
    ! deconseille de faire comme ça pour l'id ( il vaut mieux mettre une loobpack)
    router-id 1.1.1.1
    network 10.10.10.0 255.255.255.0 area 0
    passive-interface G0/0
    exit
! 
! NAT
!statique pour le file server
ip nat inside source static 10.10.10.162 198.133.219.130
! dynamique pour le reste
access-list 1 permit 10.10.10.0 0.0.0.255
ip nat pool POOL 198.133.219.128 198.133.219.129 netmask 255.255.255.0
ip nat inside source list 1 pool TOTO overload









```