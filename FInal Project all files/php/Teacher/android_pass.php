<?php
require_once __DIR__ . '/db_connect.php';
	
$d = 0; 
$db = new DB_CONNECT();
if($db)
{	
	/*$usn = '1rv12is041';
	$oldpass='qwerty';
	$oldpass='poke';
	$newpass='qwerty';
	$newpass='poke';*/
	$id=$_POST['id'];
	$oldpass=$_POST['oldpass'];
	$newpass=$_POST['newpass'];
	
	$query_search = "select * from staff where ID = '".$id."' AND pass = '".$oldpass. "'";
	$query_exec = mysql_query($query_search) or die(mysql_error());
	$rows = mysql_num_rows($query_exec);
	if($rows) { 
	
		$query2= "update staff set pass ='".$newpass."' where ID ='".$id."'";
		$result = mysql_query($query2);
 
	}


	$query="select pass from staff where ID = '".$id."'";
	$result = mysql_query($query);
	$res = mysql_fetch_array($result);
	echo $res['pass'];
	
}

?>