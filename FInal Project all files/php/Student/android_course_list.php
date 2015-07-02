<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{
	/*$data="";
	$usn = '1rv12is053';
	$acy='2014';
	$sem='6';*/
	$usn=$_POST['usn'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];

	$query_search = "select distinct course.name from studcourse left join course on studcourse.ccode=course.ccode and studcourse.acy = course.acy and studcourse.sem = course.sem 
	where studcourse.USN = '".$usn."' and studcourse.acy='".$acy."' and studcourse.sem='".$sem."'";
	$result=mysql_query($query_search) or die(mysql_error());
	$rows = mysql_num_rows($result);
	$data=$rows;
	if($result){
		
		while($res=mysql_fetch_array($result))
		{
			$res2 = $res['name'];
		    $data .="-".$res2; 
		}
		
		echo $data;


	}
	else{
		echo "no details";
	}

}

?>
