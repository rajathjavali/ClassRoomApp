<?php 
require_once __DIR__ . '/db_connect.php';
$id=$_POST['id'];
$acy=$_POST['acy'];
$sem=$_POST['sem'];
$sec=$_POST['sec'];
$time=$_POST['starttime'];
$apptime=$_POST['apptime'];
$date=$_POST['date'];
$data = '';
$db = new DB_CONNECT();
if($db)
{
	$query="select ccode from class where ctme = '".$time."' and cdte = '".$date."' and sem = '".$sem."' and acy = '".$sec."'  ";
}


?>