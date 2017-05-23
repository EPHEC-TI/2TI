<?php
$file = $_FILES['datafile']['name'];
$dossier ='./DOCUMENTS/';

if (move_uploaded_file($_FILES['datafile']['tmp_name'], "$dossier/$file")) {
    echo "Le fichier est valide, et a été téléchargé avec succès.\n";
    $link = "<a href='$dossier/$file'>$file</a><br />";
    echo $link;
} else {
    echo "Attaque potentielle par téléchargement de fichiers.";
}