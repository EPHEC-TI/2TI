<?php

function scriptInfos($info = "INFOS") {
    // ! Mauvaise pratique __NAME__
    if (!defined('__SCRIPT_NAME__')) define('__SCRIPT_NAME__', $_SERVER['PHP_SELF']);
    if (!defined('__SCRIPT_DNS__')) define('__SCRIPT_DNS__', $_SERVER['SERVER_NAME']);
    if (!defined('__SCRIPT_PATH__')) define('__SCRIPT_PATH__', $_SERVER['PATH_TRANSLATED']);
    if (!defined('__SCRIPT_PROTOCOL__')) define('__SCRIPT_PROTOCOL__', $_SERVER['SERVER_PROTOCOL']);

    $scriptNameToArray =  explode(".", __SCRIPT_NAME__); // [foo, bar]
    $allConstants = get_defined_constants(); // key, value => array of constants
    $scriptInfo = "__SCRIPT_" . strtoupper($info) . "__"; // name => __SCRIPT_NAME__

    if (array_key_exists($scriptInfo, $allConstants)) {
        return $allConstants[$scriptInfo];
    }

    switch(strtoupper($info)) {
        case 'EXTENSION': return $scriptNameToArray[count($scriptNameToArray) - 1];
            break;
        case 'SHORTNAME': return $scriptNameToArray[0];
            break;
        case 'DIRS': return explode("/", __SCRIPT_PATH__);
            break;
        case 'LONGPATH': return __SCRIPT_PATH__;
            break;
        case 'FULLPATH':
            return __SCRIPT_PROTOCOL__ . ': (' . __SCRIPT_DNS__ . ') ' . __SCRIPT_PATH__ . ' [' . __SCRIPT_NAME__ . ']';
            break;
        case 'INFOS':
            return [
                'scriptName' => __SCRIPT_NAME__,
                'scriptDns' => __SCRIPT_DNS__,
                'scriptPath' => __SCRIPT_PATH__,
                'scriptProtocol' => __SCRIPT_PROTOCOL__
            ];
            break;
        default:
            $constants = ['__SCRIPT_NAME__', '__SCRIPT_DNS__', '__SCRIPT_PATH__', '__SCRIPT_PROTOCOL__']; // for the sake of simplicity
            foreach($constants as $el) {
                if (preg_match_all('/' . $info . '/i',  $el)) {
                    return 'Err in ' . $el . ' : paramètre inconnu (' . $info . ')';
                };
            }
    }
}

function creeTableau($table, $titre = "", $index = false) {
    $tableHTML = "<table>";
    $tableHTML .= "<caption>" . $titre . "</caption>";
    $tableHTML .= "<thead><tr>";

    if ($index) {
        $tags = array_merge(['index'], array_keys($table[array_keys($table)[0]]));
    } else {
        $tags = array_keys($table[array_keys($table)[0]]);
    }

    foreach ($tags as $tag) {
        $tableHTML .= "<td>" . $tag . "</td>";
    }
    $tableHTML .= "</tr></thead><tbody>";

    foreach ($table as $key => $value) {
        $tableHTML .= "<tr>";
        if ($index) $tableHTML .= "<td>" . $key . "</td>";
        foreach ($value as $el) {
            $tableHTML .= "<td>" . $el . "</td>";
        }
        $tableHTML .= "</tr>";
    }

    $tableHTML .= "</tbody></table>";
    return $tableHTML;
}

function myPrintR($table) {
    $customPre = "<pre>";
    $customPre .= print_r($table, true); // true, retourne le résultat de print_r
    $customPre .= "</pre>";
    return $customPre;
}


