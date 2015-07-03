<?php

require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	/*echo "hello";*/
	$usn=$_POST['usn'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$cname=$_POST['cname'];
	/*$usn ='1rv12is009';
	$acy='2014';
	$sem='6';
	//$cname='Database Management Systems';
	//$cname='Software Engineering';
	//$cname='Computer Networks and Security';
	$cname='Emerging technologies';*/
	
	$data="";
	
	$query= " SELECT attends.cdte,attends.ctme,attends.status FROM attends,course WHERE attends.susn = '".$usn."' and course.name='".$cname."' and course.acy='".$acy."' 
	and course.sem = '".$sem."' and course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy and course.sem = attends.sem";
	$result = mysql_query($query);
	if($result){
		$res=mysql_num_rows($result);
		$data=$res;
		if($res){
			//$i=0;
			while ($res1 = mysql_fetch_assoc($result)) {
				$data.='$'.$res1['cdte']."   ".$res1['ctme']."           ".$res1['status'];
				# code...
			}
		}
		echo $data;
	}

}

?>