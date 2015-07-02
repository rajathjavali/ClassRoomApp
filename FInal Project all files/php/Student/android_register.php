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
	$usn=$_POST['usn'];
	if(isset($fn)&&isset($em)){
		$sql = "update student set FName='".$fn."', Mname='".$mn."', Lname='".$ln."',Phno='".$ph."', emailid='".$em."' where USN='".$usn."'";
		$result=mysql_query($sql);
		if($result){
			echo "Successful";
		}
		else{
			echo "Unsuccessful";
		}
	}
}


?>