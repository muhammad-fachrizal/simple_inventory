<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['id_barang']) && isset($_REQUEST['stok'])) {
	
		$id_barang		= $_REQUEST['id_barang'];
		$stok			= $_REQUEST['stok'];
		
		$query 	= mysqli_query($conn, "UPDATE tbl_barang SET stok = '$stok' WHERE id_barang='$id_barang'" );
	
		if($query) {
			echo json_encode(array( 'response'=>'update_stok success' ));
		} else {
			echo json_encode(array( 'response'=>'update_stok failed' ));
		}
	}
	
	else {
   echo json_encode(array( 'response'=>'Parameter ada yang kurang'));
	}
?>