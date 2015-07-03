<?php
require_once __DIR__ . '/db_connect.php';

/*$acy='2014';
$sem='6';
$sec='A';
$no='1';
$cname='Management and Organizational Behavior';
$ctme="19:42";
$cdte='16-Apr-2015';
$usn[0]='1rv12is041';
//$usn[1]='1rv12is009';
$status[0]='P';
//$status[1]='1';
$i=0;*/


$acy=$sem=$sec=$no=$cname=$ctme=$cdte="";

/*if(isset($_POST['acy'])&&isset($_POST['sem'])&&isset($_POST['sec'])&&isset($_POST['no'])&&isset($_POST['cname'])
	&&isset($_POST['time'])&&isset($_POST['date'])){*/
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	//$sec=$_POST['sec'];
	$no=$_POST['no'];
	$cname=$_POST['cname'];
	$ctme=$_POST['time'];
	$cdte=$_POST['date'];
	$ccode="";
	$usn=array();
	$status=array();
	$i=0;
	while($i<$no){
		$usn[$i]=$_POST['usn'.$i];
		$status[$i]=$_POST['status'.$i];
		$i=$i+1;
	}




	$db = new DB_CONNECT();
	if($db)
	{
		$count=0;
		$query="select ccode from course where sem = '".$sem."' and acy = '".$acy."' and name = '".$cname."'";
		$result = mysql_query($query);
		if($result){
			$res=mysql_fetch_array($result);
			$ccode = $res['ccode'];
			$i=0;
			while($i<$no){

				$query = "INSERT INTO attends (susn,cdte, ctme, ccode, acy, sem, status) VALUES ('".$usn[$i]."','".$cdte."','".$ctme."','".$ccode."','".$acy."','".$sem."','".$status[$i]."')";
				$i=$i+1;
				$count = $count+1;
				$res = mysql_query($query);
				
			}
			if($count>0)
			{
				echo "Successful";
			}
			else{
				echo "Error";
			}
			
			
		}
	}
//}

?>