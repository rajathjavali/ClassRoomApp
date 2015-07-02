<?php
$usn=$_POST['usn'];
//$usn = '1rv12is053';
$sno = array();
$no = $_POST['number'];
$i=0;
while($i<$no){
	$sno[$i] = $_POST[$i.""];
	$i=$i+1;
}
/*$no = 4;
$sno[0] = '1';
$sno[1] = '2';
$sno[2] = '3';
$sno[3] = '5';*/


require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db && isset($usn))
{
	$i=0;
	$count = 0;
	while($i<$no){
		$query = "insert into checks values('".$usn."','".$sno[$i]."')";
		$result = mysql_query($query);
		$i = $i+1;
		$count = $count + 1;
	}
	if($count == $no){
		echo "done";
	}
	else 
		echo "failed";
}
?>