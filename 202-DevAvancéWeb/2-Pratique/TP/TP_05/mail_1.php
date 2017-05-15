<?php
$to      = 'HE201408@students.ephec.be';
$subject = 'le sujet';
$message = '<html><body>';
$message .= '<a href="http://193.190.65.94/HE201408/2_PROJET/phase_00c">lien</a>';
$message .= '</body></html>';
/*$headers = 'From: HE201408@students.ephec.be' . "\r\n" .
    'Reply-To: HE201408@students.ephec.be' . "\r\n" .
    'X-Mailer: HTML/' . phpversion();*/
$headers = "From: " . "HE201408@students.ephec.be" . "\r\n";
$headers .= "Reply-To: ". "HE201408@students.ephec.be" . "\r\n";
$headers .= "CC: susan@example.com\r\n";
$headers .= "MIME-Version: 1.0\r\n";
$headers .= "Content-Type: text/html; charset=ISO-8859-1\r\n";
mail($to, $subject, $message, $headers);
?>