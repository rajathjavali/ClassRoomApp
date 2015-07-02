<?php
require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db)
{

	/*$usn = '1rv12is053';
	$acy='2014';
	$sem='6';
	$cname='Database Management Systems';
	$cname='Software Engineering';
	$cname='Computer Networks and Security';
	$cname='Management and Organizational Behavior';*/
	$usn=$_POST['usn'];
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$cname=$_POST['cname'];
	$data="";
	$query="select * from marks left join course on marks.ccode = course.ccode and marks.acy=course.acy and marks.sem=course.sem where 
	susn = '".$usn."' and course.name = '".$cname."' and course.sem = '".$sem."' and course.acy = '".$acy."'";
	//$query="select lab,t1,t2,t3,assign from marks where susn = '".$usn."' and ccode,sem,acy in ";
	/*$query = "SELECT distinct lab,t1,t2,t3,assign FROM marks,course WHERE susn = '".$usn."'and course.ccode = marks.ccode  and course.acy = marks.acy and 
	course.sem = marks.sem and course.name = '".$cname."' and course.acy = '".$acy."' and course.sem='".$sem."'";*/
	$result=mysql_query($query) or die(mysql_error());
	if($result)
	{
		$code=mysql_fetch_array($result);
		$no = mysql_num_rows($result);
		if($no)
		{
			$data=$code['lab']."-".$code['t1']."-".$code['q1']."-".$code['t2']."-".$code['q2']."-".$code['t3']."-".$code['q3']."-".$code['assign'];

		}
		else
		{
			$data = '0-0-0-0-0-0-0-0';
		}
		
		
		$query= " SELECT distinct attends.cdte,attends.ctme,attends.status FROM attends,course WHERE susn = '".$usn."' and course.name='".$cname."' and course.acy='".$acy."' 
		and course.sem = '".$sem."' and course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy and course.sem = attends.sem";
		$result = mysql_query($query) or die(mysql_error());
		if($result){
			$res=mysql_num_rows($result);
			if($res){
				$query= " SELECT distinct attends.cdte,attends.ctme,attends.status FROM attends,course WHERE susn = '".$usn."' and course.name='".$cname."' and 
				course.acy='".$acy."' and course.sem = '".$sem."' and attends.status = 'P' and course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy 
				and course.sem = attends.sem";
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
			else{
				$data.="-0-0-0";
			}
		}
		echo $data;
		
	}
	
}

?>