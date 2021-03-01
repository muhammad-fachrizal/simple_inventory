<?php 
	require_once('connection.php');

	$query = mysqli_query($conn, 
		"SELECT id_keluar FROM tbl_total_barang_keluar"
		);
		
		$result = array();
		$angkamax = array();
		$json = array();
		
		$i = 0;
		
		while($row = mysqli_fetch_assoc($query)){
		array_push($result,array(
			$row['id_keluar']
		));
		
	}
	
	
	if(empty($result))
	{
		echo json_encode(array('')); 
	}
	
	else
	{

		$hasil = MAX($result);
			
        $json =(object) $hasil;
		
		$angkamax = array ($json);
    
		echo json_encode($angkamax); 
		
	}
		
	
 ?>