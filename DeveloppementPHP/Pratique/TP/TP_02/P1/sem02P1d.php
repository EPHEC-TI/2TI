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
        print_r(scriptInfos("scriptInfos"));
    echo "<br>Param : scriptLongPath </br>";
        echo scriptInfos("scriptLongPath").'<br><br>';
    echo "Param : ImToOldForThisCrap<br>";
        echo scriptInfos("ImToOldForThisCrap").'<br><br>';
    echo "Param : nbPassage<br>";
        echo scriptInfos("nbPassage").'<br><br>';
?>
</pre>

<?php
//Prépa. pour le TP suivant
print_r(creeTableau(scriptInfos(), 'Script Info'));
