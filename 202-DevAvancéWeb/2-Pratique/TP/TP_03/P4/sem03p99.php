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
        $checkQuery = 'CALL 1617he201352.mc_parentGroup(:groupe)';

        $checkClasse = $bdd->prepare($checkQuery);
        $checkClasse->bindValue(':groupe', $classe, PDO::PARAM_STR);

        $checkClasse->execute();

        $classeChecked = $checkClasse->fetch(PDO::FETCH_NUM);

        $checkClasse->closeCursor();

        if($classeChecked == NULL OR !isset($classeChecked)){
            echo 'Ce groupe n\'existe pas';
        }
        else {



            echo 'Groupe : ' . $classeChecked[0] . '<br>';
            echo 'Nom du parent : ' . $classeChecked[1] . '<br>';

            $query = 'CALL 1617he201352.mc_coursesGroup(:groupeChecked)';

            $string = "2TL2";

            $reponse = $bdd->prepare($query);
            $reponse->bindValue(':groupeChecked', $string, PDO::PARAM_STR);
            $reponse->execute();


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



