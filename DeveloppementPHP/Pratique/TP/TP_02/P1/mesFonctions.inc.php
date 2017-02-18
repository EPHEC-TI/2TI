<?php
/**
 * scriptInfos donne des informations sur le script qui l'appelle et le serveur utilisé
 * @param string $param Si vide, renvoie un tableau avec toutes les informations. Sinon la function vas interpréter le paramètre pour renvoyé la valeur associé
 * @return array|int|mixed|string
 */
function scriptInfos($param = 'infos')
{
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
    if (!defined('_SCRIPT_NAME_')) define('_SCRIPT_NAME_', basename($_SERVER['SCRIPT_NAME']));
    if (!defined('_SCRIPT_DNS_')) define('_SCRIPT_DNS_', $_SERVER['SERVER_NAME']);
    if (!defined('_SCRIPT_PATH_')) define('_SCRIPT_PATH_', $_SERVER['PATH_TRANSLATED']);
    if (!defined('_SCRIPT_PROTOCOL_')) define('_SCRIPT_PROTOCOL_', $_SERVER['SERVER_PROTOCOL']);


    $scriptInfo = Array(
        'scriptName' => _SCRIPT_NAME_,
        'scriptDns' => _SCRIPT_DNS_,
        'scriptPath' => _SCRIPT_PATH_,
        'scriptProtocol' => _SCRIPT_PROTOCOL_,
        'scriptExtension' => '.' . pathinfo(_SCRIPT_NAME_, PATHINFO_EXTENSION),
        'scriptShortName' => pathinfo(_SCRIPT_NAME_, PATHINFO_FILENAME),
        'scriptDirs' => pathinfo($_SERVER['SCRIPT_NAME'], PATHINFO_DIRNAME),
        'scriptLongPath' => $_SERVER['SCRIPT_NAME'],
        'scriptFullPath' => $_SERVER["HTTP_REFERER"] . _SCRIPT_NAME_
    );

    if ($shortParam != "nbpassage") {
        $nbPassage++;
    }
    switch ($shortParam) {

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
            return '<span style="color:red;">Erreur dans <strong>' . __METHOD__ . '()</strong> : paramètre inconnu (' . $param . ')</span>';
            break;
    }
}

//Prépa pour le TP suivant
function creeTableau(array $liste, $titre = NULL){
    //On récupère le "clef" du tableau ("Marque" => "VW", "Puissance" => "3KW" vas donner un tableau avec ([0] => "Marque", [1] => "Puissance)
    $clef = array_keys($liste);

    $table = '<table>';
    if(isset($titre)){
        $table .= '<caption>'.$titre.'</caption>';
    }
        $table .='<thead>';
            $table .='<tr>';
                //Possibilité 1 :
                $table .='<th>Clef :</th>';
                $table .='<th>Valeur :</th>';
                //Possibilité 2 (Les clef de $liste sont les têtes de colonnes) :
                /*for($i=0; $i < count($clef); $i++){
                    $table .='<th>'.$clef[$i].'</th>';
                }*/
            $table .='</tr>';
        $table .='</thead>';
        $table .='<tbody>';
        for($i=0; $i < count($liste); $i++){
            $table .='<tr>';
                //Possibilité 1 :
                $table .='<td>'.$clef[$i].'</td>';
                $table .='<td>'.$liste[$clef[$i]].'</td>';
                //Possibilité 2 (Les clef de $liste sont les têtes de colonnes) :
                /*for($i=0; $i < count($clef); $i++){
                    $table .='<th>'.$liste[$clef[$i]].'</th>';
                }*/
            $table .='</tr>';
        }
        $table .='</tbody>';
    $table .='</table>';
    return $table;
}

