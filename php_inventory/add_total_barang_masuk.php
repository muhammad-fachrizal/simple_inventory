<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['total_jumlah_masuk'])  && isset($_REQUEST['id_masuk']) && isset($_REQUEST['id_admin']) 
		&& isset($_REQUEST['tanggal_masuk']) && isset($_REQUEST['nama_pengembali'])) {
			
	$id_masuk				= $_REQUEST['id_masuk'];
	$nama_pengembali		= $_REQUEST['nama_pengembali'];
	$total_jumlah_masuk		= $_REQUEST['total_jumlah_masuk'];
	$id_admin				= $_REQUEST['id_admin'];
	$tanggal_masuk			= $_REQUEST['tanggal_masuk'];
	
	$query = mysqli_query($conn, "INSERT INTO tbl_total_barang_masuk (id_masuk, nama_pengembali, total_jumlah_masuk, id_admin, tanggal_masuk) VALUES 
								('$id_masuk', '$nama_pengembali', '$total_jumlah_masuk', '$id_admin', '$tanggal_masuk') ");

    if($query == FALSE) {
	        die(mysqli_error());
	        echo json_encode(array( 'response'=> die(mysqli_error())));
	    }
	else{
	    echo json_encode(array( 'response'=>'add_total_barang_masuk success' ));
	} 
		}
		else {
    echo json_encode(array( 'response'=>'Parameter ada yang kurang'));
}
?>






