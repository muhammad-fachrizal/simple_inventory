<?php 
	require_once('connection.php');
	
	$query = mysqli_query($conn, "SELECT b.nama_barang, dk.id_keluar, 
											dk.jumlah_keluar, tk.status, tk.tanggal_keluar, tk.nama_peminjam
								FROM tbl_barang b
								JOIN tbl_detail_barang_keluar dk
									ON b.id_barang = dk.id_barang
								JOIN tbl_total_barang_keluar tk
									ON dk.id_keluar = tk.id_keluar
									
								ORDER BY id_keluar DESC "
	);
	
	$result = array();
	
	while($row = mysqli_fetch_assoc($query)){
		array_push($result,array(
			'id_keluar'			=> $row['id_keluar'],
			'nama_peminjam'		=> $row['nama_peminjam'],
			'nama_barang'		=> $row['nama_barang'],
			'jumlah_keluar'		=> $row['jumlah_keluar'],
			'status'			=> $row['status'],
			'tanggal_keluar'	=> $row['tanggal_keluar']
			
		));
		
	}
	
	echo json_encode(array(
		'result'	=> $result
	), JSON_NUMERIC_CHECK);
	
 ?>