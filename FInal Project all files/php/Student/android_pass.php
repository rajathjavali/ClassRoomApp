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
	$usn=$_POST['usn'];
	$oldpass=$_POST['oldpass'];
	$newpass=$_POST['newpass'];
	
	$query_search = "select * from student where USN = '".$usn."' AND pass = '".$oldpass. "'";
	$query_exec = mysql_query($query_search) or die(mysql_error());
	$rows = mysql_num_rows($query_exec);
	if($rows) { 
	
		$query2= "update student set pass ='".$newpass."' where USN ='".$usn."'";
		$result = mysql_query($query2);
 
	}


	$query="select pass from student where USN = '".$usn."'";
	$result = mysql_query($query);
	$res = mysql_fetch_array($result);
	echo $res['pass'];
	
}

?>