<?php

require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	/*echo "hello";*/
	//$usn=$_POST['usn'];
	$cdate=$_POST['cdate'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$cname=$_POST['cname'];
	/*$usn ='1rv12is009';
	$acy='2014';
	$sem='6';
	$cdate='10-Apr-2015';
	//$cdate=$_POST['cdate'];
	//$cname='Database Management Systems';
	//$cname='Software Engineering';
	$cname='Computer Networks and Security';
	//$cname='Emerging technologies';*/
	
	$data="";
	
	$query= " SELECT attends.susn,attends.cdte,attends.ctme,attends.status from attends left join course on course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy WHERE course.name='".$cname."' and course.acy='".$acy."' 
	and course.sem = '".$sem."' and attends.cdte='".$cdate."'";
	$result = mysql_query($query);
	if($result){
		$res=mysql_num_rows($result);
		$data=$res;
		//echo $data;
		if($res){
			$i=0;
			while ($res1 = mysql_fetch_assoc($result)) {
				$data.='$'.$res1['susn']."			".$res1['cdte']."  			 ".$res1['ctme']."           ".$res1['status'];
				

				# code...
			}
			echo $data;
		}
	}

}

?>