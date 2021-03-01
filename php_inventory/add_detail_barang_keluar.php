<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['id_barang']) && isset($_REQUEST['jumlah_keluar']) && isset($_REQUEST['id_keluar'])) {
			
	$id_barang			= $_REQUEST['id_barang'];
	$jumlah_keluar		= $_REQUEST['jumlah_keluar'];
	$id_keluar			= $_REQUEST['id_keluar'];
	
	$query = mysqli_query($conn, "INSERT INTO tbl_detail_barang_keluar (id_keluar, id_barang, jumlah_keluar) 
									VALUES 
									('$id_keluar', '$id_barang', '$jumlah_keluar') ");

	if($query) {
	    echo json_encode(array( 'response'=>'add_detail_barang_keluar success' ));
	} else {
	    echo json_encode(array( 'response'=>'add_detail_barang_keluar failed' ));
	}
	
		}
		
		else {
     echo json_encode(array( 'response'=>'Parameter ada yang kurang' ));
}
?>