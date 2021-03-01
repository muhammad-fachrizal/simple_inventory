<?php

  define('HOST','localhost');
  define('USER','id16218600_fachrizal');
  define('PASS','@4F=Pf8hGJev');
  define('DB','id16218600_inventory');
  $conn = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect');

  date_default_timezone_set("Asia/Jakarta");
?>