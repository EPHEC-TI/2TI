<?php
ini_set('post_max_size', '1025M');
ini_set('upload_max_filesize', '1024M');
$file = $_FILES['datafile']['name'];
$dossier ='./MES_IMAGES/';
$uploadIsOk = true;
$typeImage = pathinfo($dossier.$file,PATHINFO_EXTENSION);

if($typeImage != "jpg" && $typeImage != "png" && $typeImage != "jpeg" && $typeImage != "gif" && $typeImage != "JPG" && $typeImage != "PNG" && $typeImage != "JPEG" && $typeImage != "GIF") {
    echo "Seul les formats JPG, JPEG, PNG et GIF sont supportés";
    $uploadIsOk = false;
}

if ($uploadIsOk) {
    if (move_uploaded_file($_FILES['datafile']['tmp_name'], "$dossier/$file")) {
        echo "Le fichier est valide, et a été téléchargé avec succès.\n";
        $link = "<a href='$dossier/$file'>$file</a><br />";
        echo $link;
    } else {
        echo "Attaque potentielle par téléchargement de fichiers.";
    }
}