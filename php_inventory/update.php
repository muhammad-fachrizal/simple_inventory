<?php
	require_once('connection.php');

	$id_barang		= $_REQUEST['id_barang'];
	$nama_barang	= $_REQUEST['nama_barang'];
	$stok			= $_REQUEST['stok'];
	$tahun			= $_REQUEST['tahun_didapatkan'];
	
	$sql	= "UPDATE tbl_barang SET id_barang = '$id_barang', nama_barang='$nama_barang', stok='$stok', tahun='$tahun' WHERE id_barang='$id_barang'";
	$query 	= mysqli_query($conn, $sql );
	
	if($query) {
	    echo json_encode(array( 'response'=>'success' ));
	} else {
	    echo json_encode(array( 'response'=>'failed' ));
	}
?>