<?php

$id=$_POST['id'];
$info=$_POST['info'];
$date=$_POST['date'];
$time=$_POST['time'];

/*$id="123";
$info="extra class on 21st april 2015";
$date="19-Apr-2015";
$time="12:45";*/

require_once __DIR__ . '/db_connect.php';

	 
$db = new DB_CONNECT();
if($db)
{
	$query = "INSERT INTO notices(info,dte, tme,id) 
	VALUES ('".$info."','".$date."','".$time."','".$id."')";
	$result = mysql_query($query);
	if($result)
		echo "Successful";
	else
		echo "Error";
}
?>