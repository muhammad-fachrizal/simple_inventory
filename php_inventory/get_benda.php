<?php
require_once('connection.php');
 
    $query = mysqli_query($conn, "SELECT * FROM tbl_barang ORDER BY id_barang ASC");
     
	$json = array();
	 
    while($row = mysqli_fetch_assoc($query)){
        $json[] = $row;
    }
    
  
    echo json_encode($json);
	

?>