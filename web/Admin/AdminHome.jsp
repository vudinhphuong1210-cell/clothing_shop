<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Home</title>

    <style>
        body {
            margin: 0;
            font-family: Arial, sans-serif;
        }

        .sidebar {
            width: 220px;
            height: 100vh;
            position: fixed;
            top: 0;
            left: 0;
            background-color: #1e293b;
            color: white;
            padding: 20px;
            box-sizing: border-box;
        }

        .sidebar h3 {
            text-align: center;
            margin-bottom: 30px;
            letter-spacing: 1px;
        }

        .sidebar ul {
            list-style: none;
            padding: 0;
        }

        .sidebar li {
            margin-bottom: 15px;
        }

        .sidebar a {
            display: block;
            padding: 10px 12px;
            color: white;
            text-decoration: none;
            border-radius: 6px;
            transition: background 0.2s;
        }

        .sidebar a:hover {
            background-color: #334155;
        }

        /* Nội dung chính */
        .main-content {
            margin-left: 220px;
            padding: 20px;
        }
    </style>
</head>

<body>

<div class="sidebar">
    <h3>ADMIN</h3>
    <ul>
        <li><a href="AdminDashboard">📊 Dashboard</a></li>
        <li><a href="AdminOrder">🧾 Orders</a></li>
        <li><a href="AdminProduct">👕 Products</a></li>
        <li><a href="AdminCustomerControl">👤 Customers</a></li>
        <li><a href="AdminEmployeeControl">👨‍ Employees</a></li>
        <li><a href="AdminLogout">🚪 Logout</a></li>
    </ul>
</div>

<div class="main-content">
    <h2>Welcome Admin</h2>
   
</div>

</body>
</html>