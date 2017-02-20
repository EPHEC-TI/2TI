<?php
// Dans le cas d'une adress on peux utiliser la fonction basename qui retourne uniquement le nom du fichier.
$autreMoyenDeFaireLaMêmeChose = Array("A.1 Nom du script : " => basename($_SERVER['SCRIPT_NAME']),"A.2 DNS : " => $_SERVER['SERVER_NAME'],"A.3 Chemin à partir de la racine : " => $_SERVER["SCRIPT_FILENAME"],"A.4 Protocol : " => $_SERVER["SERVER_PROTOCOL"].' SSL : '.($_SERVER["SERVER_PORT_SECURE"] ? 'OUI' : 'NON'));

echo '<pre>';print_r($autreMoyenDeFaireLaMêmeChose);echo '<pre>';
