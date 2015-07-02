<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	$fn=$_POST['fname'];
	$mn=$_POST['mname'];
	$ln=$_POST['lname'];
	$ph=$_POST['ph'];
	$em=$_POST['email'];
	$id=$_POST['id'];
	$sn=$_POST['sname'];
	if(isset($fn)&&isset($em)){
		$sql = "update staff set FName='".$fn."', Mname='".$mn."', Lname='".$ln."', Shortname='".$sn."',Phno='".$ph."', emailid='".$em."' where ID='".$id."'";
		$result=mysql_query($sql)or die(mysql_error());
		if($result){
			echo "Successful";
		}
		else{
			echo "Unsuccessful";
		}
	}
}


?>