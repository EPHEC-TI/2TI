function verif () {
    var fileName = document.getElementById("datafile").value;
    var fileExtention = fileName.split('.').pop();
    fileExtention = fileExtention.toLowerCase();
    if (fileExtention != 'jpg' && fileExtention != 'jpeg' && fileExtention != 'png' && fileExtention != 'gif') {
        document.getElementById("form").innerHTML += "Seul les fichiers jpg, jpeg, png et gif sont acceptés"
        document.getElementById("button").onclick = onclickButtonFalse;
    }
    else {
        try{
            var fileSize = document.getElementById("datafile").files[0].size;
            document.getElementById("tailleFichier").innerHTML = "La taille du fichier est de " + fileSize + " bytes";
        } catch(e){
            var objFSO = new ActiveXObject("Scripting.FileSystemObject");
            var e = objFSO.getFile( document.getElementById("datafile").value);
            var fileSize = e.size;
            document.getElementById("tailleFichier").innerHTML = "La taille du fichier est de " + fileSize + " bytes";
        }
        document.getElementById("button").onclick = onclickButtonTrue;
    }
}

function onclickButtonFalse () {
    return false;
}
function onclickButtonTrue () {
    return true;
}