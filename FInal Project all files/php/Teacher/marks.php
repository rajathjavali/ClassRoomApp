<?php
require_once __DIR__ . '/db_connect.php';

/*$acy='2014';
$sem='6';
$sec='A';
$no='2';
$cname='Computer Networks and Security';
//$ctme="21:11:46";
//$cdte='07-Apr-2015';
$usn[1]='1rv12is004';
$usn[2]='1rv12is009';
//$status[0]='1';
//$status[1]='1';
$tmarks[1]='20';
$qmarks[1]='10';
$tmarks[2]='na';
$qmarks[2]='na';
$i=0;
$type='1';*/


$acy=$sem=$sec=$no=$cname=$ctme=$cdte=$ccode="";

/*if(isset($_POST['acy'])&&isset($_POST['sem'])&&isset($_POST['sec'])&&isset($_POST['no'])&&isset($_POST['cname'])
	&&isset($_POST['time'])&&isset($_POST['date'])){*/
	$acy=$_POST['acy'];
	$sem=$_POST['sem'];
	$sec=$_POST['sec'];
	$no=$_POST['no'];
	$cname=$_POST['cname'];
	//$ctme=$_POST['time'];
	//$cdte=$_POST['date'];
	$usn=array();
	$tmarks=array();
	$qmarks=array();
	//$status=array();

	$type=$_POST['type'];

	

/*******************************************************************************************************************************************************************/


	if($type=='1'||$type=='2'||$type=='3')
	{
		
		$i=1;
		while($i<=$no){
			$usn[$i]=$_POST['usn'.$i];
			$tmarks[$i]=$_POST['test'.$i];
			//if(isset($_POST['quiz'.$i]))
			$qmarks[$i]=$_POST['quiz'.$i];
			$i=$i+1;
		}

		$test='t'.$type;
		$quiz='q'.$type;

		$db = new DB_CONNECT();
		if($db)
		{

			$count=0;
			$query="select ccode from course where sem = '".$sem."' and acy = '".$acy."' and name = '".$cname."'";
			$result = mysql_query($query);
			if($result){
				$res=mysql_fetch_array($result);
				
				$ccode = $res['ccode'];
				//echo $ccode."<br>";
				$i=1;
				if($ccode!=null)
				{
					while($i<=$no){
						
						$query = "select * from marks where susn = '".$usn[$i]."' and ccode = '".$ccode."'";
						
						$result=mysql_query($query);
						$num=mysql_num_rows($result);
						
						if($num==0){
							//if($tmarks[$i]!="/"&& $qmarks[$i]!="/"){
							/*	$tmarks[$i]=0;
							}
							if(){
								$qmarks[$i]=0;*/
							
							$query = "INSERT INTO marks (susn,".$test.",".$quiz.",ccode,acy, sem) VALUES ('".$usn[$i]."','"
							.$tmarks[$i]."','".$qmarks[$i]."','".$ccode."','".$acy."','".$sem."')";
							//echo $query."<br>";
							$i=$i+1;
							$count = $count+1;
							$res = mysql_query($query);
							//}
							
						}
						else{
							
							if($tmarks[$i]=="na" && $qmarks[$i]=="na"){$i=$i+1;}
								else{
								$query = "UPDATE marks set ".$test."='".$tmarks[$i]."',".$quiz."='".$qmarks[$i]."' WHERE susn='".$usn[$i]."' and ccode='".$ccode."' and acy='".$acy."' and  sem='".$sem."'" ;
								//echo $query;
								$i=$i+1;
								$count = $count+1;
								$res = mysql_query($query);
							}

						}
					}	
					
						
				}
				else{
					//echo "no course";
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

	}
	else
	{
		$i=1;
		while($i<=$no){
			$usn[$i]=$_POST['usn'.$i];
			$tmarks[$i]=$_POST['test'.$i];
			//if(isset($_POST['quiz'.$i]))
			$i=$i+1;
		}
		if($type=='4')
			$test = 'lab';
		else
			$test='assign';

		
		$db = new DB_CONNECT();
		if($db)
		{

			$count=0;
			$query="select ccode from course where sem = '".$sem."' and acy = '".$acy."' and name = '".$cname."'";
			$result = mysql_query($query);
			if($result){
				$res=mysql_fetch_array($result);
				
				$ccode = $res['ccode'];
				//echo $ccode;
				$i=1;
				
				while($i<=$no){
					$query = "select * from marks where susn = '".$usn[$i]."' and ccode = '".$ccode."'";
					
					$result=mysql_query($query);
					$num=mysql_num_rows($result);
					if($num==0){
						$query = "INSERT INTO marks (susn,".$test.",ccode,acy, sem) VALUES ('".$usn[$i]."','"
						.$tmarks[$i]."','".$ccode."','".$acy."','".$sem."')";
						//echo $query;
						$i=$i+1;
						$count = $count+1;
						$res = mysql_query($query);	
					}
					else{
						$query = "UPDATE marks set ".$test."='".$tmarks[$i]."' WHERE susn='".$usn[$i]."' and ccode='".$ccode."' and acy='".$acy."' and  sem='".$sem."'" ;
						//echo $query;
						$i=$i+1;
						$count = $count+1;
						$res = mysql_query($query);
					}
					
					
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
	}


?>