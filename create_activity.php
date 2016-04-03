<?php
    $con = mysqli_connect("mysql9.000webhost.com", "a7068193_user", "c0ding", "a7068193_mmlogin");
    
    // $name = $_POST["name"];
    // $age = $_POST["age"];
    // $username = $_POST["username"];
    // $password = $_POST["password"];

    $event_time = $_POST["time"];
    $event_place = $_POST["place"];

    $statement = mysqli_prepare($con, "INSERT INTO event (event_time, event_place) VALUES (?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sss", $event_time, $event_place);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>