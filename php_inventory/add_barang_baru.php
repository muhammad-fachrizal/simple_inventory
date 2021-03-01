<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['nama_barang']) && isset($_REQUEST['stok']) && isset($_REQUEST['tahun_didapatkan'])) {
			
	$nama_barang	= $_REQUEST['nama_barang'];
	$stok		= $_REQUEST['stok'];
	$tahun		= $_REQUEST['tahun_didapatkan'];
	
	$query = mysqli_query($conn, "INSERT INTO tbl_barang (nama_barang, stok, tahun) VALUES 
								('$nama_barang', '$stok', '$tahun') ");

	if($query) {
	    echo json_encode(array( 'response'=>'add_barang_baru success' ));
	} else {
	    echo json_encode(array( 'response'=>'add_barang_baru failed' ));
	}
		}
		else {
    echo json_encode(array( 'response'=>'Parameter ada yang kurang'));
}
?>





