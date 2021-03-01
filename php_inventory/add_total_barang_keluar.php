<?php
	require_once('connection.php');
	
	if (isset($_REQUEST['total_jumlah_keluar']) && isset($_REQUEST['nama_peminjam']) && isset($_REQUEST['id_admin'])
		&& isset($_REQUEST['id_keluar'])  && isset($_REQUEST['status']) && isset($_REQUEST['tanggal_keluar'])) {
			
	$id_keluar					= $_REQUEST['id_keluar'];
	$nama_peminjam 				= $_REQUEST['nama_peminjam'];
	$id_admin 					= $_REQUEST['id_admin'];
	$total_jumlah_keluar		= $_REQUEST['total_jumlah_keluar'];
	$status 					= $_REQUEST['status'];
	$tanggal_keluar				= $_REQUEST['tanggal_keluar'];
	
	$query = mysqli_query($conn, "INSERT INTO tbl_total_barang_keluar (id_keluar, nama_peminjam, id_admin, 
									total_jumlah_keluar, status, tanggal_keluar) 
									VALUES 
									('$id_keluar', '$nama_peminjam', '$id_admin', '$total_jumlah_keluar', 
									 '$status', '$tanggal_keluar') ");


	if($query) {
	    echo json_encode(array( 'response'=>'add_total_barang_keluar success' ));
	} else {
	    echo json_encode(array( 'response'=>'add_total_barang_keluar failed' ));
	}
		}
		else {
    echo json_encode(array( 'response'=>'Parameter ada yang kurang' ));
}
?>



