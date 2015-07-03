<?php

/*$query="select course.name,handles.lid,staff.Shortname,studcourse.USN,course.ccode from studcourse left join handles on studcourse.ccode=handles.ccode and studcourse.acy=handles.acy 
and studcourse.sem = handles.sem and studcourse.section=handles.section left join course on handles.ccode = course.ccode and handles.sem = course.sem and handles.acy = course.acy 
left join staff on handles.lid = staff.ID";*/
require_once __DIR__ . '/db_connect.php';

/*$id='123';
$acy = '2014';
$sem='6';
$sec='A';
$cname = 'Management and Organizational Behavior';*/

$id=$_POST['id'];
$acy=$_POST['acy'];
$sem=$_POST['sem'];
$sec=$_POST['sec'];
$cname=$_POST['cname'];
	 
$data = '';
$db = new DB_CONNECT();
if($db)
{
	$query="select studcourse.USN,student.FName,student.Lname from studcourse left join handles on studcourse.ccode=handles.ccode and studcourse.acy=handles.acy and 
	studcourse.sem = handles.sem and studcourse.section=handles.section left join course on handles.ccode = course.ccode and handles.sem = course.sem and handles.acy = course.acy 
	left join student on studcourse.USN = student.USN where handles.lid = '".$id."' and handles.acy = '".$acy."' and handles.sem = '".$sem."' and handles.section = '".$sec."' and 
	course.name = '".$cname."'";
	$result=mysql_query($query) or die(mysql_error());
	if($result){
		$res = mysql_num_rows($result);
		if($res){
			
			$data=$res;

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
?>