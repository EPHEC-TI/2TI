<form method="get" action="">
    <input type="text" name="groupe" placeholder="groupe recherché" value="<?php if(isset($_GET['groupe']) and $_GET['groupe'] != NULL) echo $_GET['groupe']; ?>"/>
    <input type="submit" value="Envoi" />
</form>
<?php
require_once('dbConnect.inc.php');
require_once('mesFonctions.inc.php');
$dbName = 'minicampus';

if(isset($_GET['groupe'])) {
    $classe = $_GET['groupe'];
}

if(isset($_GET['groupe'])) {
    try {
        $bdd = new PDO('mysql:host=' . getServer() . ';dbname=' . $dbName . ';charset=utf8', $__INFOS__['user'], $__INFOS__['pswd']);

        $checkQuery = 'SELECT c1.nom AS \'Nom enfant\', c2.nom AS \'Nom parent\' FROM class c1 JOIN class c2 ON c1.parent_id = c2.id WHERE c1.nom LIKE ?';

        $checkClasse = $bdd->prepare($checkQuery);
        $checkClasse->execute(array($classe));

        $classeChecked = $checkClasse->fetch(PDO::FETCH_NUM);

        $checkClasse->closeCursor();

        if($classeChecked == NULL OR !isset($classeChecked)){
            echo 'Ce groupe n\'existe pas';
        }
        else {

            echo 'Groupe : ' . $classeChecked[0] . '<br>';
            echo 'Nom du parent : ' . $classeChecked[1] . '<br>';

            $query = 'SELECT cours.code, cours.faculte , cours.intitule
        FROM minicampus.cours
        INNER JOIN minicampus.course_class
        ON cours.code = course_class.cours_id
        INNER JOIN minicampus.class
        ON class.id = course_class.class_id
        WHERE class.nom LIKE ?';


            $reponse = $bdd->prepare($query);
            $reponse->execute(array($classeChecked[0]));


            $data = $reponse->fetchAll(PDO::FETCH_ASSOC);

            $reponse->closeCursor();


            if(!empty($data)){
                echo creeTableau($data, "Avec index", true);
                echo creeTableau($data, "Sans index", false);
            }
            else{
                echo "Aucun cours n'est rattaché à ce groupe !";
            }
        }

    } catch (Exception $e) {
        die('Erreur : ' . $e->getMessage());
    }
}



