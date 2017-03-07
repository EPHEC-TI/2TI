<form method="get" action="">
    <input type="text" name="groupe" placeholder="groupe recherché" value="<?php if(isset($_GET['groupe']) and $_GET['groupe'] != NULL) echo $_GET['groupe']; ?>"/>
    <input type="submit" value="Envoi" />
</form>
<?php
require_once('dbConnect.inc.php');
require_once('mesFonctions.inc.php');
$dbName = 'minicampus';
$classe = '1TL2';
if(isset($_GET['groupe'])) {
    $classe = $_GET['groupe'];
}

$query ='SELECT cours.code, cours.faculte , cours.intitule
        FROM minicampus.cours
        INNER JOIN minicampus.course_class
        ON cours.code = course_class.cours_id
        INNER JOIN minicampus.class
        ON class.id = course_class.class_id
        WHERE class.nom LIKE ?;';
if(isset($_GET['groupe'])) {
    try {
        $bdd = new PDO('mysql:host=' . getServer() . ';dbname=' . $dbName . ';charset=utf8', $__INFOS__['user'], $__INFOS__['pswd']);

        $reponse = $bdd->prepare($query);
        $reponse->execute(array($classe));


        $data = $reponse->fetchAll(PDO::FETCH_ASSOC);

        $reponse->closeCursor();

        echo creeTableau($data, "Avec index", true);
        echo creeTableau($data, "Sans index", false);

    } catch (Exception $e) {
        die('Erreur : ' . $e->getMessage());
    }
}



