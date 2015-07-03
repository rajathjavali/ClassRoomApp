<?php
$usn=$_POST['usn'];
//$usn = '1rv12is053';

require_once __DIR__ . '/db_connect.php';
	 
$db = new DB_CONNECT();
if($db && isset($usn))
{
	$data="0";
	$query = "select nsno from checks where USN = '".$usn."' order by nsno desc";
	$result = mysql_query($query);
	if($result)
	{
		$no = 0;
		while($res = mysql_fetch_assoc($result))
		{
			$no=$res['nsno'];
			//echo($no)."<br><br>";
			break;
		}
		$query1 = "select notices.serialno,notices.info,notices.dte,notices.tme,staff.Shortname from notices left join staff on notices.id = staff.ID where serialno > '".$no."'";
		$result1 = mysql_query($query1);
		if($result1){
			$num = mysql_num_rows($result1);
			//echo($num);
			$data = $num;
			while ($res = mysql_fetch_assoc($result1)) {
				$data .= "$".$res['serialno']."#".$res['info']."#".$res['dte']."#".$res['tme']."#".$res['Shortname'];
				//$query2 = "insert into checks values('".$usn."' , '".$res['serialno']."')";
				//$result2 = mysql_query($query2);
				
			}
		}
	}
	echo $data;
	
}
?>