<?php require_once ('mesFonctions.inc.php'); ?>
<pre>
<?php
    echo "Param : nbPassage<br>";
        echo (scriptInfos("nbPassage")).'<br><br>';
    echo "Param : Aucun </br>";
        print_r(scriptInfos());
    echo "Param : scriptName </br>";
        echo scriptInfos("scriptName").'<br><br>';
    echo "Param : nbPassage<br>";
        echo (scriptInfos("nbPassage")).'<br><br>';
    echo "Param : scriptDns </br>";
        echo scriptInfos("scriptDns").'<br><br>';
    echo "Param : scriptPath </br>";
        echo scriptInfos("scriptPath").'<br><br>';
    echo "Param : scriptProtocol </br>";
        echo scriptInfos("scriptProtocol").'<br><br>';
    echo "Param : scriptShortName </br>";
        echo scriptInfos("scriptShortName").'<br><br>';
    echo "Param : scriptExtension </br>";
        echo scriptInfos("scriptExtension").'<br><br>';
    echo "Param : scriptDirs </br>";
        print_r(scriptInfos("scriptDirs").'<br><br>');
    echo "<br>Param : scriptLongPath </br>";
        echo scriptInfos("scriptLongPath").'<br><br>';
    echo "Param : ImToOldForThisCrap<br>";
        echo scriptInfos("ImToOldForThisCrap").'<br><br>';
    echo "Param : nbPassage<br>";
        echo scriptInfos("nbPassage").'<br><br>';
?>
</pre>
<?php

$tableau = ['bo124' =>['auteur'=> 'B.Y.',  'titre'=> 'Connectique',              'prix'=> 5.20],
    'bo254' =>['auteur'=> 'L.Ch.', 'titre'=> 'Programmation C',          'prix'=> 4.75],
    'bo334' =>['auteur'=> 'L.Ch.', 'titre'=> 'JavaScript',               'prix'=> 6.40],
    'bo250' =>['auteur'=> 'D.A.',  'titre'=> 'Mathématiques',            'prix'=> 6.10],
    'bo604' =>['auteur'=> 'V.V.',  'titre'=> 'Objets',                   'prix'=> 4.95],
    'bo025' =>['auteur'=> 'D.M.',  'titre'=> 'Electricité',              'prix'=> 7.15],
    'bo099' =>['auteur'=> 'D.M.',  'titre'=> 'Phénomènes Périodiques',   'prix'=> 6.95],
    'bo023' =>['auteur'=> 'V.MN.', 'titre'=> 'Programmation Java',       'prix'=> 5.75],
    'bo100' =>['auteur'=> 'D.Y.',  'titre'=> 'Bases de Données',         'prix'=> 6.35],
    'bo147' =>['auteur'=> 'V.V.',  'titre'=> 'Traitement de Signal',     'prix'=> 4.85],
    'bo004' =>['auteur'=> 'B.W.',  'titre'=> 'Sécurité',                 'prix'=> 5.55],
    'bo074' =>['auteur'=> 'B.Y.',  'titre'=> 'Electronique digitale',    'prix'=> 4.35],
    'bo257' =>['auteur'=> 'D.Y.',  'titre'=> 'Programmation Multimedia', 'prix'=> 6.00]];



    echo '<br> Test de creeTableau avec un tableau de Livres et l\'affichage des index<br><br>';
    echo creeTableau($tableau, 'Liste de livres', true);

echo '<br> Test de creeTableau avec un tableau de Livres et sans l\'affichage des index<br><br>';
echo creeTableau($tableau, 'Liste de livres', false);

echo '<br> Test de monPrint_r avec un tableau de livres : <br>';
echo monPrint_r($tableau);
