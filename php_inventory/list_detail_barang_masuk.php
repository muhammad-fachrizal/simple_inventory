<?php 
	require_once('connection.php');

	$query = mysqli_query($conn, "SELECT b.nama_barang, dm.id_masuk, 
											dm.jumlah_masuk, tm.nama_pengembali, tm.tanggal_masuk, ta.nama_admin
								FROM tbl_barang b
								JOIN tbl_detail_barang_masuk dm
									ON b.id_barang = dm.id_barang
								JOIN tbl_total_barang_masuk tm
									ON dm.id_masuk = tm.id_masuk
									
								JOIN tbl_admin ta
									ON tm.id_admin = ta.id_admin
									
								ORDER BY id_masuk DESC

	");
	
	$result = array();
	
	while($row = mysqli_fetch_assoc($query)){
		array_push($result,array(
			'id_masuk'				=> $row['id_masuk'],
			'nama_barang'			=> $row['nama_barang'],
			'nama_pengembali'		=> $row['nama_pengembali'],
			'nama_admin'			=> $row['nama_admin'],
			'jumlah_masuk'			=> $row['jumlah_masuk'],
			'tanggal_masuk'			=> $row['tanggal_masuk']
		));
	}
	
	echo json_encode(array(
		'result'	=> $result
	), JSON_NUMERIC_CHECK);
	
 ?>