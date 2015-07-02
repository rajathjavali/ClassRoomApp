<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	/*$usn='1rv12is053';
	$acy='2014';
	$sem='6';
	$cname='Management and Organizational Behavior';*/
	$usn=$_POST['usn'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$cname=$_POST['cname'];
	$data="";
	$query="select distinct staff.Shortname,staff.FName,staff.Phno from staff,course,attends,handles where 
	staff.ID=handles.lid and course.ccode = handles.ccode and course.acy=handles.acy and course.sem = handles.sem and course.name='".$cname."' 
	and course.ccode = attends.ccode and course.acy=attends.acy and 
	course.sem = attends.sem and attends.susn='".$usn."' and attends.acy = '".$acy."' and attends.sem = '".$sem."'";

	/*$query="select staff.Shortname,staff.FName,staff.Phno from staff,course,attends,handles where 
	attends.susn='".$usn."' and attends.acy = '".$acy."' and attends.sem = '".$sem."' and 
	course.name = '".$cname."' and course.ccode = attends.ccode and course.ccode = handles.ccode and 
	course.acy=handles.acy and course.sem = handles.sem and staff.ID=handles.lid";
	$data="";*/
	$result=mysql_query($query) or die (mysql_error());
	if($result){
		$res1=mysql_num_rows($result)*2;
		$data.=$res1;
		while($res=mysql_fetch_assoc($result)){
			$data.='-'.$res['Shortname'].'('.$res['FName'].')-'.$res['Phno'];
		}
		echo $data;
		
	}

}

?>