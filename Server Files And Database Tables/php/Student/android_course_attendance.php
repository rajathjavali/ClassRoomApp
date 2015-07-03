<?php

require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	/*$usn = '1rv12is053';
	$acy='2014';
	$sem='6';
	//$cname='Database Management Systems';
	//$cname='Software Engineering';
	//$cname='Computer Networks and Security';
	$cname='Management and Organizational Behavior';*/
	$usn=$_POST['usn'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$cname=$_POST['cname'];
	$data="";

	$query= " SELECT distinct attends.cdte,attends.ctme,attends.status FROM attends,course WHERE susn = '".$usn."' and course.name='".$cname."' and course.acy='".$acy."' 
	and course.sem = '".$sem."' and course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy and course.sem = attends.sem";
	$result = mysql_query($query) or die(mysql_error());
	if($result){
		$res=mysql_num_rows($result);
		$query= " SELECT distinct attends.cdte,attends.ctme,attends.status FROM attends,course WHERE susn = '".$usn."' and course.name='".$cname."' and course.acy='".$acy."' 
		and course.sem = '".$sem."' and attends.status = 'P' and course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy and course.sem = attends.sem";
		$result1=mysql_query($query) or die(mysql_error());
		$res1=mysql_num_rows($result1);
		$per=0;
		if($res!=0){
		$per = $res1/$res;
		$per=$per*100;
		
		}
		else{
			$per=0;
		}
		$data.="-".$res1."-".$res."-".number_format((float)$per, 2, '.', '');
		
	}

}

?>