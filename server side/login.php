<?php 

include_once('koneksi.php');

if (!empty($_POST['user_email']) && !empty($_POST['user_password'])) {

    $user_email = $_POST['user_email'];
    $user_password = md5($_POST['user_password']);

    $query = "SELECT * FROM user WHERE user_email = '$user_email' AND user_password = '$user_password'";

$get = mysqli_query($connect, $query);
$data = array();

if(mysqli_num_rows($get) > 0) {
    while ($row = mysqli_fetch_assoc($get)) {
        $data[] = $row;
    }
    set_response(true, "Login Berhasil", $data);
} else {
    set_response(false, "Login gagal", $data);
}
} else {
    set_response(false, "email, password tidak boleh kosong", $data);
}

function set_response($isSuccess, $message, $data) {

    $result = array(
        'isSuccess' => $isSuccess,
        'message' => $message,
        'data' => $data
    );
    echo json_encode($result);
}
