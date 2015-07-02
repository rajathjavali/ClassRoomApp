<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	$id=$_POST['id'];
	
	$query_search = "select * from staff where  ID= '".$id."'";
	$result=mysql_query($query_search)or die(mysql_error());
	if($result){
		$sql=mysql_fetch_array($result);
		if($sql['FName']!=""){
			//echo "Not Registered";		
			$fname=$sql['FName'];
			$mname=$sql['Mname'];
			$lname=$sql['Lname'];
			$sname=$sql['Shortname'];
			$phno=$sql['Phno'];
			$email=$sql['emailid'];
			$data =$fname."-".$mname."-".$lname."-".$phno."-".$email."-".$sname;
			echo $data;
		}
	}


}

?>