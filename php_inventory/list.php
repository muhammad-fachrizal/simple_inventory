<?php 
	require_once('connection.php');
	
	if (isset($_REQUEST['id_barang'])) {
		
		$id_barang = $_REQUEST['id_barang'];
		
		$query = mysqli_query($conn, "SELECT * FROM tbl_barang WHERE id_barang = '$id_barang' ORDER BY id_barang ASC");
	
		$result = array();
	
	    if($query == FALSE) {
	        die(mysqli_error());
	    }
	        
	        
		while($row = mysqli_fetch_assoc($query)){
			array_push($result,array(
				'id_barang'			=> $row['id_barang'],
				'nama_barang'		=> $row['nama_barang'],
				'stok'				=> $row['stok'],
				'tahun_didapatkan'	=> $row['tahun']
				));
			}
			
	    
	   
	
		echo json_encode(array(
			'result'	=> $result
		), JSON_NUMERIC_CHECK);
	}
	
	else {
		
		$query = mysqli_query($conn, "SELECT * FROM tbl_barang ORDER BY id_barang ASC") or die (mysqli_error($conn));
	
		$result = array();
	
	     if($query == FALSE) {
	        die(mysqli_error());
	    }
		while($row = mysqli_fetch_assoc($query)){
			array_push($result,array(
				'id_barang'			=> $row['id_barang'],
				'nama_barang'		=> $row['nama_barang'],
				'stok'				=> $row['stok'],
				'tahun_didapatkan'	=> $row['tahun']
			));
		
		}
	
		echo json_encode(array(
			'result'	=> $result
		), JSON_NUMERIC_CHECK);
	
	
	}

 ?>