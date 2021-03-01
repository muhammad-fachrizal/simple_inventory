<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['email']) && isset($_REQUEST['password'])) {					
					
				$email 		= $_REQUEST['email'];
				$password 	= $_REQUEST['password'];
				
				$result = array();
				
				$query = mysqli_query($conn, "SELECT id_admin, nama_admin FROM tbl_admin WHERE email = '$email' and password = '$password'");
				
				while($row = mysqli_fetch_assoc($query)){
					array_push($result,array(
						$row['id_admin'],
						//$row['nama_admin']
					));
				}
				
				if(empty($result))
					{
						echo json_encode(array( 'response'=>'Email atau Password Salah' ));
					}
				else
					{
						echo json_encode(array('response' => $result));
					}
	}
					
	else {
    echo json_encode(array( 'response'=>'Parameter login ada yang kurang' ));
	}
?>