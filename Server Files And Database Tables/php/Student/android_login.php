<?php
// $hostname_localhost ="localhost";
// $database_localhost ="project";
// $username_localhost ="root";
// $password_localhost ="";
// $localhost = mysql_connect($hostname_localhost,$username_localhost,$password_localhost) or trigger_error(mysql_error(),E_USER_ERROR);
 
// mysql_select_db($database_localhost, $localhost);
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
 
	$username = $_POST['username'];
	$password = $_POST['password'];
	$query_search = "select * from student where USN = '".$username."' AND pass = '".$password. "'";
	$query_exec = mysql_query($query_search) or die(mysql_error());
	$rows = mysql_num_rows($query_exec);
	
	if($rows == 0) { 
		echo "User Not Found"; 
	}
	else  {
	    echo "User Found"; 
	}
}
?>