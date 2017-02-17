<?php

function scriptInfos($param = 'infos'){

    // On crée un variable statique, qui garde son dernier état à chaque appel de la fonction. Si l'on exécute deux fois la fonction d'affilé, la premiere aura son $premierPassage true, la seconde fois il vaudra false.
    static $premierPassage = true;


    //On simplifie et mettons en minuscule le paramètre afin de pouvoir permettre les paramètres "scriptFullPath" ou encore "fullpath", "FullPath" d'êtres interprété de la même manière par exemple
    $shortParam = str_replace('script', '', strtolower($param));

    /*
    * Selon le site officiel de PHP
    * mieux vaut être prudent lorsque l'on appele une constante _NOM_DE_LA_CONSTANTE_ (avec les 2 underscores)
    * car php pourrait un jour les utiliser comme constante magique (http://php.net/manual/fr/language.constants.php)
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

    $premierPassage = false;
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
        default:
            return '<span style="color:red;">Erreur dans <strong>'.__METHOD__.'()</strong> : paramètre inconnu ('.$param.')</span>';
            break;
    }



}