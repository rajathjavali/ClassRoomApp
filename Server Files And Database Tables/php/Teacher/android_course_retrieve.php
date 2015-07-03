<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	$data="";
	/*$id='123';
	$acy='2014';
	$sem='6';
	$sec='A';*/
	$id=$_POST['id'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$sec=$_POST['sec'];
	$query = "select course.name from course,handles where handles.lid='".$id."' and handles.acy = '".$acy."' 
	and handles.sem = '".$sem."' and handles.section = '".$sec."' and course.ccode = handles.ccode";
	$result = mysql_query($query) or die(mysql_error());
	if($result){
		$res1=mysql_num_rows($result);
		$data=$res1;
		while($res=mysql_fetch_assoc($result)){
			$data.='-'.$res['name'];
		}
		echo $data;
	}

}

?>