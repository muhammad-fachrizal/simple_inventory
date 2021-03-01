<?php
	require_once('connection.php');

	$id_barang	= $_REQUEST['id_barang'];
	
	$query = mysqli_query($conn, "DELETE FROM tbl_barang WHERE id_barang ='$id_barang' ");

	if($query) {
	    echo json_encode(array( 'response'=>'success' ));
	} else {
	    echo json_encode(array( 'response'=>'failed' ));
	}
?>