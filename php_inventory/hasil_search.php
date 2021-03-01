<?php 
	require_once('connection.php');
	
	$nama_barang = $_REQUEST['nama_barang'];

	$query = mysqli_query($conn, "SELECT * FROM tbl_barang WHERE nama_barang LIKE '%".$nama_barang."%' ORDER BY nama_barang ASC");
	
	$result = array();
	
	while($row = mysqli_fetch_assoc($query)){
		array_push($result,array(
			'id_barang'	=> $row['id_barang'],
			'nama_barang'		=> $row['nama_barang'],
			'stok'			=> $row['stok'],
			'tahun_didapatkan'			=> $row['tahun']
		));
		
	}
		
	echo json_encode(array(
		'result'	=> $result
	), JSON_NUMERIC_CHECK);
	
 ?>