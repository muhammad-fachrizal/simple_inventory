<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['id_barang']) && isset($_REQUEST['jumlah_masuk']) 
		&& isset($_REQUEST['id_masuk']) ) {
			

	$id_barang			= $_REQUEST['id_barang'];
	$jumlah_masuk		= $_REQUEST['jumlah_masuk'];
	$id_masuk			= $_REQUEST['id_masuk'];
	
	
	$query = mysqli_query($conn, "INSERT INTO tbl_detail_barang_masuk (id_masuk, id_barang, jumlah_masuk) VALUES 
								('$id_masuk', '$id_barang', '$jumlah_masuk') ");

	if($query) {
	    echo json_encode(array( 'response'=>'add_detail_barang_masuk success' ));
	} else {
	    echo json_encode(array( 'response'=>'add_detail_barang_masuk failed' ));
	}
	
		}
		
		else {
     echo json_encode(array( 'response'=>'Parameter ada yang kurang' ));
}
?>