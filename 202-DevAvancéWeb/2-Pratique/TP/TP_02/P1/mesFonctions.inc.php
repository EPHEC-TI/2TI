<?php
/**
 * scriptInfos donne des informations sur le script qui l'appelle et le serveur utilisé
 * @param string $param Si vide, renvoie un tableau avec toutes les informations. Sinon la function vas interpréter le paramètre pour renvoyé la valeur associé
 * @return array|int|mixed|string
 */
function scriptInfos($param = 'infos'){

    // Une variable statique garde son dernier état. Elle n'est pas réinitialisée à chaque appel de la fonction
    static $nbPassage = 0;


    //On simplifie et mettons en minuscule le paramètre afin de pouvoir permettre les paramètres "scriptFullPath" ou encore "fullpath", "FullPath" d'êtres interprété de la même manière par exemple
    $shortParam = str_replace('script', '', strtolower($param));

    /*
    * Selon la doc officiel de PHP
    * mieux vaut être prudent lorsque l'on appele une constante _NOM_DE_LA_CONSTANTE_ (avec les 2 underscores)
    * car php pourrait un jour les utiliser comme constante magique (http://php.net/manual/fr/language.constants.php)
    * Ici j'utilise seulement un seul underscore afin d'être sur que cela ne pose pas de problème dans le future.
    */
    // Dans le cas d'une adresse on peux utiliser la fonction basename qui retourne uniquement le nom du fichier.
    if (!defined('_SCRIPT_NAME_'))      define('_SCRIPT_NAME_', basename($_SERVER['SCRIPT_NAME']));
    if (!defined('_SCRIPT_DNS_'))       define('_SCRIPT_DNS_', $_SERVER['SERVER_NAME']);
    if (!defined('_SCRIPT_PATH_'))      define('_SCRIPT_PATH_', $_SERVER['PATH_TRANSLATED']);
    if (!defined('_SCRIPT_PROTOCOL_'))  define('_SCRIPT_PROTOCOL_', $_SERVER['SERVER_PROTOCOL']);


    $scriptInfo = Array (
        'scriptName' => _SCRIPT_NAME_,
        'scriptDns' => _SCRIPT_DNS_,
        'scriptPath' => _SCRIPT_PATH_,
        'scriptProtocol' => _SCRIPT_PROTOCOL_,
        'scriptExtension' =>'.'.pathinfo(_SCRIPT_NAME_, PATHINFO_EXTENSION),
        'scriptShortName' => pathinfo(_SCRIPT_NAME_, PATHINFO_FILENAME),
        'scriptDirs' => pathinfo($_SERVER['SCRIPT_NAME'], PATHINFO_DIRNAME),
        'scriptLongPath' => $_SERVER['SCRIPT_NAME'],
        'scriptFullPath' => $_SERVER["HTTP_REFERER"]._SCRIPT_NAME_
        );

    if($shortParam != "nbpassage"){
    $nbPassage++;
    }
    switch($shortParam){

        case 'name':
            return $scriptInfo['scriptName'];
            break;
        case 'dns':
            return $scriptInfo['scriptDns'];
            break;
        case 'path':
            return $scriptInfo['scriptPath'];
            break;
        case 'protocol':
            return $scriptInfo['scriptProtocol'];
            break;
        case 'extension':
            return $scriptInfo['scriptExtension'];
            break;
        case 'shortname':
            return $scriptInfo['scriptShortName'];
            break;
        case 'dirs':
            return $scriptInfo['scriptDirs'];
            break;
        case 'longpath':
            return $scriptInfo['scriptLongPath'];
            break;
        case 'fullpath':
            return $scriptInfo['scriptFullPath'];
            break;
        case 'infos':
            return $scriptInfo;
            break;
        case 'nbpassage':
            return $nbPassage;
            break;
        default:
            return '<span style="color:red;">Erreur dans <strong>'.__METHOD__.'()</strong> : paramètre inconnu ('.$param.')</span>';
            break;
    }
}

/**
 * CreeTableau retourne un tableau formatté en HTML5 pour une liste donnée
 * @param Array $liste  Le tableau de donnée à traiter. Ce tableau doit être composé de sous-array
 * @param string $titre Le titre du tableau
 * @param bool $index true affiche les indexes; false sinon
 * @return string un tableau formatté en HTML5 avec les données passée en paramètre
 */
function creeTableau($liste, $titre = 'Tableau', $index){

    //On obtiens un tableau avec les indexes de la liste (Ex : bo124, bo254,..)
    $refListe = array_keys($liste);


    if(is_array($liste[$refListe[0]])){
        $titreListe = array_keys($liste[$refListe[0]]);
    }



    //OPTIONEL : Provoque un retour de ligne (pour l'indentation du code source généré par php)
    $n = "\r\n";
    //OPTIONEL : Provoque une tabulation (pour l'indentation du source généré par php)
    $t = "\t";

    $table = $n.'<table>'."\r\n";
        $table .= $t.'<caption>'.$titre.'</caption>'.$n;
        $table .= $t.'<thead>'.$n;
            $table .= $t.$t.'<tr>'.$n;
                if($index){
                    $table .= $t.$t.$t.'<th>Index</th>'.$n;
                }
                for($i=0; $i < count($titreListe); $i++){
                    $table .= $t.$t.$t.'<th>'.ucwords($titreListe[$i]).'</th>'.$n;
                }
                $table .= $t.$t.$t.'<th></th>'.$n;
            $table .= $t.$t.'</tr>'.$n;
        $table .= $t.'</thead>'.$n;
        $table .= $t.'<tbody>'.$n;
                for($i = 0; $i < count($refListe); $i++) {
                    $table .= $t.$t.'<tr>'.$n;
                    if ($index) {
                        $table .= $t . $t . $t . '<td>' . $refListe[$i] . '</td>' . $n;
                    }
                    for ($j = 0; $j < count($titreListe); $j++){
                        $table .= $t . $t . $t . '<td>' . $liste[$refListe[$i]][$titreListe[$j]] . '</td>' . $n;
                    }
                    $table .=$t.$t.'</tr>'.$n;
                }

        $table .=$t.'</tbody>'.$n;
    $table .='</table>'.$n;
    return $table;
}

/**
 * Imprime de manière lisible un tableau passé en paramètre. Entoure celui-ci des balises <pre></pre>
 * @param $liste Un tableau
 * @return string retourne l'impression d'un Array formatté pour être lisible
 */
function monPrint_r($liste){

    //OPTIONEL : Provoque un retour de ligne (pour l'indentation du code source généré par php)
    $n = "\r\n";

    $out = $n.'<pre>'.$n;

    $out .= print_r($liste, true);

    $out .= '</pre>'.$n;

    return $out;
}