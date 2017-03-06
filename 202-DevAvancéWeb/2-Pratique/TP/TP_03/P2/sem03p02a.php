<?php
require_once('dbConnect.inc.php');
require_once('mesFonctions.inc.php');
$dbName = 'minicampus';
$classe = '1TL2';
if(isset($_GET['groupe'])){
    $classe = $_GET['groupe'];

    echo '<span style="color:red;">Cette manière comporte une faille de sécurité, elle ne sera donc pas éxécuté<br>
          Elle est fonctionnel mais VOUS NE POUVEZ PAS l\'UTILISER EN PRODUCTION.<br>
          Paramètre groupe reçus : '.$classe.'</span>';
}
else {
$query ='SELECT cours.code, cours.faculte , cours.intitule
        FROM minicampus.cours
        INNER JOIN minicampus.course_class
        ON cours.code = course_class.cours_id
        INNER JOIN minicampus.class
        ON class.id = course_class.class_id
        WHERE class.nom LIKE \''.$classe.'\';';

try
{
    $bdd = new PDO('mysql:host='.getServer().';dbname='.$dbName.';charset=utf8', $__INFOS__['user'], $__INFOS__['pswd']);

    $reponse = $bdd->query($query);

    $data = $reponse->fetchAll(PDO::FETCH_ASSOC);

    echo creeTableau($data, "Avec index", true);
    echo creeTableau($data, "Sans index", false);

}
catch (Exception $e)
{
    die('Erreur : ' . $e->getMessage());
}
}



