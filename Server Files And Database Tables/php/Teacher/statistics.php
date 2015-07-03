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
	$cdate='Apr-2015';
	//$cdate=$_POST['cdate'];
	//$cname='Database Management Systems';
	//$cname='Software Engineering';
	$cname='Computer Networks and Security';
	//$cname='Emerging technologies';*/
	
	$data="";
	
	$query= " SELECT attends.susn,attends.cdte,attends.status from attends left join course on course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy WHERE course.name='".$cname."' and course.acy='".$acy."' 
	and course.sem = '".$sem."'";// and attends.cdte='".$cdate."'";
	$q2 = "SELECT distinct attends.susn from attends left join course on course.ccode=attends.ccode and course.sem=attends.sem and course.acy=attends.acy WHERE course.name='".$cname."' and course.acy='".$acy."' 
	and course.sem = '".$sem."'";
	$result1 = mysql_query($q2);
	if($result1){
		
		$no=mysql_num_rows($result1);
		//$res=mysql_num_rows($result);
		$data=$no;
		//echo $data;
		$flag = 0; 
		
		if($no){
			while($res2 = mysql_fetch_assoc($result1))
			{
				$i=0;
				$j=0;
				$result=mysql_query($query);
				while ($res1 = mysql_fetch_assoc($result)) 
				{
					if(stristr($res1['cdte'],$cdate))
					{
						$flag = 1 ; 
						if($res2['susn']==$res1['susn'])
						{
							//echo $res1['susn']."<br>";
							if($res1['status']=='P')
							{
								$i=$i+1;

							}
							if($res1['status']=='A')
							$j=$j+1;
						}
					}
				}
				//echo $res2['susn']." ".$i." ".$j."<br>";
				$per = 0;
				$k = $j+$i;
				if($k!=0)
					$per = ($i/$k)*100;
				else
					$per = 0; 
				
				$data.='$'.$res2['susn']."			".number_format((float)$per, 2, '.', '');
					

			}
		}
		if($flag == 1)
			echo $data;
		else
			echo "0";
	}

}

?>