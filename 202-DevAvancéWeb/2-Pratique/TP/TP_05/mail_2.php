<?php
$to = $_POST['email'];
$subject = $_POST['sujet'];
$message = $_POST['message'];
$headers = 'From: ' . $to . "\r\n" .
    'Reply-To: ' . $to;

echo (mail($to, $subject, $message, $headers)) ? 'Email envoye':'FAIL ENVOIE EMAIL';
?>