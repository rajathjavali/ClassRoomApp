<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{

	$usn=$_POST['usn'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$cname=$_POST['cname'];
	/*$usn="1rv12is001";
	$acy="2014";
	$sem="6";
	$cname="Software Engineering";
	$data="";*/
	
	$query=" SELECT staff.FName,staff.Shortname,staff.phno from attends,handles,staff,course WHERE susn ='" .$usn."'  and handles.sem='".$sem."' and handles.acy ='".$acy."' and course.name='".$cname."'" ;
	$result=mysql_query($query) or die(mysql_error());
	if($result)
	{
		$res1=mysql_num_rows($result)*2;
		$data=$res1;
		while($res=mysql_fetch_assoc($result)){
			$data.="-".$res["Shortname"]."(".$res["FName"].")-".$res["Phno"];
		}
		
	echo $data;
}
}