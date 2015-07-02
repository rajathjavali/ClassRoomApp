<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	$usn=$_POST['usn'];
	$query_search = "select * from student where USN = '".$usn."'";
	$result=mysql_query($query_search);
	if($result){
		$sql=mysql_fetch_array($result);
		if($sql['FName']!=""){
			//echo "Not Registered";		
			$fname=$sql['FName'];
			$mname=$sql['Mname'];
			$lname=$sql['Lname'];
			$phno=$sql['Phno'];
			$email=$sql['emailid'];
			$data =$fname."-".$mname."-".$lname."-".$phno."-".$email;
			echo $data;
		}
	}


}

?>