<?php

/*$query="select course.name,handles.lid,staff.Shortname,studcourse.USN,course.ccode from studcourse left join handles on studcourse.ccode=handles.ccode and studcourse.acy=handles.acy 
and studcourse.sem = handles.sem and studcourse.section=handles.section left join course on handles.ccode = course.ccode and handles.sem = course.sem and handles.acy = course.acy 
left join staff on handles.lid = staff.ID";*/
require_once __DIR__ . '/db_connect.php';

/*$id='001';
$acy = '2014';
$sem='6';
$sec='A';
$cname = 'Management and Organizational Behavior';
$stime='09:00';
$apptime='10:15';
$sdate='06-Apr-2015';
$appdate='06-Apr-2015';*/

$id=$_POST['id'];
$acy=$_POST['acy'];
$sem=$_POST['sem'];
$sec=$_POST['sec'];
$stime=$_POST['stime'];
$apptime=$_POST['apptime'];
$appdate=$_POST['appdate'];
echo $id." ".$acy." ".$sem." ".$stime." ".$apptime." ".$appdate;
$data = '';
$db = new DB_CONNECT();
if($db)
{

	if((double)str_replace(':','.',$apptime)-(double)str_replace(',','.',$stime)<1.3 )
	{
	$query1="select class.ccode from class where class.tme='".$stime."' and class.dte = '".$sdate."'";
	$result1=mysql_query($query1);
	if($result1){
		$sql1=mysql_fetch_array($result1);

			//echo "Not Registered";		
		$ccode=$sql1['ccode'];

		$query="select studcourse.USN,student.FName,student.Lname from studcourse left join handles on studcourse.ccode=handles.ccode and studcourse.acy=handles.acy and 
		studcourse.sem = handles.sem and studcourse.section=handles.section left join student on studcourse.USN = student.USN where handles.lid = '".$id."' 
		and handles.acy = '".$acy."' and handles.sem = '".$sem."' and handles.section = '".$sec."' and handles.ccode = '".$ccode."'";
		$result=mysql_query($query) or die(mysql_error());
		if($result){
			$res = mysql_num_rows($result);
			if($res){
			
				$data=$res.'-'.$ccode;

				while($res1=mysql_fetch_assoc($result)) {
			
					$data.='-'.strtolower($res1['USN'])." ".$res1['FName']." ".$res1['Lname'];
				}
			
			echo $data;
			}
			else {
				echo "No Students";
			}

		}
	}
	else
		echo "No class scheduled currently";
}
}
	else
	{
		echo "No class scheduled currently";
	}

?>