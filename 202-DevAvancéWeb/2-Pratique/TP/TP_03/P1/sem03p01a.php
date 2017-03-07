<?php
require_once('dbConnect.inc.php');
require_once('mesFonctions.inc.php');

$dbName = 'minicampus';
$query ='SELECT cours.code, cours.faculte , cours.intitule
        FROM minicampus.cours
        INNER JOIN minicampus.course_class
        ON cours.code = course_class.cours_id
        INNER JOIN minicampus.class
        ON class.id = course_class.class_id
        WHERE class.nom=\'1TL2\';
        ';
try
{
    $bdd = new PDO('mysql:host='.getServer().';dbname='.$dbName.';charset=utf8', $__INFOS__['user'], $__INFOS__['pswd']);

    $reponse = $bdd->query($query);

    foreach ($reponse->fetchAll(PDO::FETCH_ASSOC) as $row){

        echo monPrint_r($row);
    }

    $reponse->closeCursor();
}
catch (Exception $e)
{
    die('Erreur : ' . $e->getMessage());
}



