<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['email']) && isset($_REQUEST['password']) && isset($_REQUEST['nama_admin'])) {					
					
				$email 		= $_REQUEST['email'];
				$nama_admin = $_REQUEST['nama_admin'];
				$password 	= $_REQUEST['password'];
								
				$query = mysqli_query($conn, "SELECT email FROM tbl_admin WHERE email = '$email'");
				
				$result = array();
	
				while($row = mysqli_fetch_assoc($query)){
					array_push($result,array(
						$row['email']
					));
				}
				
				 $json =(object) $result;
				 
				if(empty($result))
					{
						$query = mysqli_query($conn, "INSERT INTO tbl_admin (nama_admin, email, password) VALUES 
									('$nama_admin', '$email', '$password') ");
						
						if($query) {
									echo json_encode(array( 'response'=>'Registrasi Berhasil' ));
									} else {
									echo json_encode(array( 'response'=>'Gagal Menyimpan Akun' ));
									}
					}
				else
					{
						echo json_encode(array('response' => 'Email Sudah Digunakan'));
					}
					
	}
					
	
	else {
		echo json_encode(array('response' => 'Parameter registrasi ada yang kurang'));;
	}
?>