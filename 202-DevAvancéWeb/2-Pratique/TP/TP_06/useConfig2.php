<?php
$text = '';
if ($_POST != null) {
    $text .= "[SITE]";
    $text .= "\n";
    foreach ($_POST['SITE'] as $pKey => $pData) {
        $text .= $pKey;
        $text .= " = ";
        $text .= $pData;
        $text .= "\n";
    }
    $text .= "[LOGO]";
    $text .= "\n";
    if ($_POST['AVATAR']) {
        foreach ($_POST['LOGO'] as $pKey => $pData) {
            $text .= $pKey;
            $text .= " = ";
            $text .= $pData;
            $text .= "\n";
        }
        $text .= "min = 150\n";
        $text .= "max = 250\n";
        $text .= "pas = 10\n";
    }
    $text .= "[DB]";
    $text .= "\n";
    foreach ($_POST['DB'] as $pKey => $pData) {
        $text .= $pKey;
        $text .= " = ";
        $text .= $pData;
        $text .= "\n";
    }
    $text .= "[AVATAR]";
    $text .= "\n";
    if ($_POST['AVATAR']) {
        foreach ($_POST['AVATAR'] as $pKey => $pData) {
            if ($pKey != "jpg" && $pKey != "png" && $pKey != "gif") {
                $text .= $pKey;
                $text .= " = ";
                $text .= $pData;
                $text .= "\n";
            } else {
                if ($pKey == "jpg") {
                    $text .= "choix[]";
                    $text .= " = ";
                    $text .= "jpg";
                    $text .= "\n";
                }
                if ($pKey == "gif") {
                    $text .= "choix[]";
                    $text .= " = ";
                    $text .= "gif";
                    $text .= "\n";
                }
                if ($pKey == "png") {
                    $text .= "choix[]";
                    $text .= " = ";
                    $text .= "png";
                    $text .= "\n";
                }
            }
        }
        $text .= "min = 100\n";
        $text .= "max = 200\n";
        $text .= "type[]";
        $text .= " = ";
        $text .= "jpg";
        $text .= "\n";
        $text .= "type[]";
        $text .= " = ";
        $text .= "gif";
        $text .= "\n";
        $text .= "type[]";
        $text .= " = ";
        $text .= "png";
        $text .= "\n";
    }
    $maConfig = fopen("./config.ini.php", "w");
    fwrite($maConfig, $text);
    fclose($maConfig);
}

function chargeConfig($configName) {
    return parse_ini_file($configName, true);
}

function afficheConfig($config) {
    $out = [];
    //$out [] += '<pre>' . print_r($config) . '</pre>';
    $out [] = "<form id='modifConfig' name='modifConfig' method='POST' action='' onsubmit='this'>";
    $choix = [];
    foreach($config as $key => $value) {
        //echo($key);
        if ($key != "ERREUR") {
            $out [] .= "<fieldset>";
            $out [] .= "<legend>$key</legend>";
            $min = isset($value['min']) ? $value['min'] : null;
            $max = isset($value['max']) ? $value['max'] : null;
            $pas = isset($value['pas']) ? $value['pas'] : null;
            $choix [] = isset($value['choix']) ? $value['choix'] : "null";
            //print_r($value);
            //print_r($choix);
            foreach ($value as $key2 => $value2) {
                switch ($key2) {
                    case 'taille':
                        $out [] .= "<label for=\"SITE_$key2\">$key2</label>";
                        $out [] .= "<input type='number' id=\"$key" . "[$key2" . "]\" name=\"$key" . "[$key2" . "]\" value='" . $config[$key][$key2] . "' min=$min max=$max step='$pas' title=\"min=$min max=$max step='$pas'\" required></input>";
                        $out [] .= "<br>";
                        break;
                    case 'choix':
                        break;
                    case 'type':
                        foreach ($value2 as $key3 => $value3) {
                            $out [] .= "<label for=\"SITE_$key2\">$value3</label>";
                            //$choix[] = isset($value4['choix[]']) ? $value4['choix[]'] : "null";
                            //print_r($value3);
                            $id = $value3;
                            //print_r ($id);
                            $out [] .= "<input type='checkbox' id=\"$key" . "[$value3" . "]\" name=\"$key" . "[$value3" . "]\"";
                            //print_r($choix);
                            foreach ($choix as $key4 => $value4) {
                                //print_r ($choix);
                                if ($value4 == 'null') {
                                } else {
                                    //print_r($value4);
                                    foreach ($value4 as $value5) {
                                        if ($value5 == $id) {
                                            $out [] .= " checked";
                                        }
                                    }
                                }
                                //print_r($value4);
                                //print_r($choix);
                                //echo $id;
                            }
                            $out [] .= "></input>";
                            $out [] .= "<br>";
                        }
                        break;
                    case 'min':
                        break;
                    case 'max':
                        break;
                    case 'pas':
                        break;
                    default:
                        $out [] .= "<label for=\"SITE_$key2\">$key2</label>";
                        $out [] .= "<input type='text' id=\"$key" . "[$key2" . "]\" name=\"$key" . "[$key2" . "]\" value='" . $config[$key][$key2] . "' required></input>";
                        $out [] .= "<br>";
                        break;
                }
            }
            $out [] .= "</fieldset>";
        }
    }
    $out [] .= "<input type='submit' value='envoyer'>";
    $out [] .= "</form>";
    return implode("\n", $out);
}

function getValuesOnly($var) {
    return ($var != null);
}

$config = chargeConfig('./config.ini.php');
print_r(afficheConfig($config));