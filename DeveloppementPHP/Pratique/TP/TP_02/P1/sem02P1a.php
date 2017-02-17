<?php
echo "A. Affichage des paramètres de PHP";
phpinfo(INFO_VARIABLES);

// Dans le cas d'une adresse on peux utiliser la fonction basename qui retourne uniquement le nom du fichier.
echo "A.1 Le nom du script : ".basename($_SERVER['SCRIPT_NAME']).'<br>';
echo "A.2 DNS du serveur : ".$_SERVER['SERVER_NAME'].'<br>';
echo "A.3 Chemin permettant d'aller de la racine du serveur au script : ".$_SERVER["SCRIPT_FILENAME"].'<br>';
echo "A.4 Type de protocol utilisé :  ".$_SERVER["SERVER_PROTOCOL"];
