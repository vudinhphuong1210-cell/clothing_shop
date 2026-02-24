<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: 'Segoe UI', sans-serif; background: #f4f6f9; color: #333; }
        .container { padding: 24px; }

        /* ── Header ── */
        .page-header { margin-bottom: 24px; }
        .page-header h2 { font-size: 24px; font-weight: 700; color: #1a1a2e; }
        .page-header p  { color: #888; font-size: 14px; margin-top: 4px; }

        /* ── Summary cards ── */
        .summary-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
            gap: 16px;
            margin-bottom: 28px;
        }
        .sum-card {
            background: #fff;
            border-radius: 12px;
            padding: 20px 22px;
            box-shadow: 0 1px 4px rgba(0,0,0,.08);
            border-top: 4px solid transparent;
            transition: transform .15s;
        }
        .sum-card:hover { transform: translateY(-2px); }
        .sum-card.blue   { border-color: #4f46e5; }
        .sum-card.green  { border-color: #10b981; }
        .sum-card.orange { border-color: #f59e0b; }
        .sum-card.red    { border-color: #ef4444; }
        .sum-card.purple { border-color: #8b5cf6; }
        .sum-card.teal   { border-color: #06b6d4; }

        .sum-card .icon { font-size: 28px; margin-bottom: 10px; }
        .sum-card .num  { font-size: 30px; font-weight: 700; color: #1a1a2e; }
        .sum-card .lbl  { font-size: 13px; color: #888; margin-top: 4px; }

        .section-title {
            font-size: 17px; font-weight: 700; margin-bottom: 16px;
            padding-left: 14px;
            border-left: 5px solid #4f46e5;
            color: #1a1a2e;
        }
        .section-sub { font-size: 13px; color: #888; font-weight: 400; margin-left: 8px; }

        .table-card {
            background: #fff;
            border-radius: 12px;
            box-shadow: 0 1px 4px rgba(0,0,0,.08);
            overflow: hidden;
        }
        table { width: 100%; border-collapse: collapse; }
        thead { background: #1a1a2e; color: #fff; }
        thead th { padding: 13px 16px; text-align: left; font-size: 13px; font-weight: 600; }
        tbody tr { border-bottom: 1px solid #f0f0f0; transition: background .1s; }
        tbody tr:last-child { border-bottom: none; }
        tbody tr:hover { background: #fafafa; }
        tbody td { padding: 13px 16px; font-size: 14px; vertical-align: middle; }

        /* Rank badge */
        .rank {
            width: 30px; height: 30px; border-radius: 50%;
            display: flex; align-items: center; justify-content: center;
            font-weight: 700; font-size: 14px;
        }
        .rank-1 { background: #fef3c7; color: #92400e; font-size: 18px; }
        .rank-2 { background: #f3f4f6; color: #374151; font-size: 16px; }
        .rank-3 { background: #fde8d8; color: #9a3412; font-size: 16px; }
        .rank-n { background: #ede9fe; color: #5b21b6; }

        /* Product info */
        .prod-info { display: flex; align-items: center; gap: 12px; }
        .prod-img  {
            width: 44px; height: 44px; border-radius: 8px;
            object-fit: cover; border: 1px solid #eee;
        }
        .no-img {
            width: 44px; height: 44px; border-radius: 8px;
            background: #f3f4f6; display: flex; align-items: center;
            justify-content: center; font-size: 20px; flex-shrink: 0;
        }
        .prod-name { font-weight: 600; color: #1a1a2e; }
        .prod-cat  { font-size: 12px; color: #888; margin-top: 2px; }

        /* Progress bar */
        .bar-wrap { display: flex; align-items: center; gap: 10px; }
        .bar-bg {
            flex: 1; height: 8px; background: #f3f4f6;
            border-radius: 4px; overflow: hidden;
        }
        .bar-fill {
            height: 100%; border-radius: 4px;
            background: linear-gradient(90deg, #4f46e5, #818cf8);
        }
        .bar-num { font-weight: 700; color: #4f46e5; min-width: 32px;
                   text-align: right; font-size: 14px; }

        /* Revenue */
        .revenue { color: #059669; font-weight: 600; }

        /* Empty state */
        .empty {
            text-align: center; padding: 48px; color: #aaa; font-size: 15px;
        }

        /* Warning badge */
        .badge-warn {
            background: #fef3c7; color: #92400e;
            padding: 3px 8px; border-radius: 12px;
            font-size: 12px; font-weight: 600;
        }

    </style>
</head>

<body class="container-fluid p-4">
<jsp:include page="/Admin/AdminHome.jsp"/>
<div class="main-content">
<h2 class="mb-4">📊 Admin Dashboard</h2>

<!-- ENTRY -->
<form method="get" action="${pageContext.request.contextPath}/AdminDashboard">
    <button class="btn btn-primary mb-4">🔄 Load Dashboard Data</button>
</form>

<!-- KPI -->
<div class="row mb-4">
    <div class="col-md-3">
        <div class="card text-bg-primary">
            <div class="card-body">
                <h5>Total Orders</h5>
                <h3>${totalOrders}</h3>
            </div>
        </div>
    </div>

    <div class="col-md-3">
        <div class="card text-bg-warning">
            <div class="card-body">
                <h5>Pending Orders</h5>
                <h3>${pendingOrders}</h3>
            </div>
        </div>
    </div>

    <div class="col-md-3">
        <div class="card text-bg-success">
            <div class="card-body">
                <h5>Revenue (This Month)</h5>
                <h3>${revenueThisMonth}</h3>
            </div>
        </div>
    </div>

    <div class="col-md-3">
        <div class="card text-bg-dark">
            <div class="card-body">
                <h5>Total Customers</h5>
                <h3>${totalCustomers}</h3>
            </div>
        </div>
    </div>
</div>

<!-- Charts -->
<div class="row mb-4">
    <div class="col-md-6">
        <canvas id="revenueChart"></canvas>
    </div>
    <div class="col-md-6">
        <canvas id="orderStatusChart"></canvas>
    </div>
</div>

<!-- Tables -->
<div class="row">
    <div class="col-md-7">
        <h5>🧾 Latest Orders</h5>
        <table class="table table-striped">
            <tr>
                <th>ID</th>
                <th>Customer</th>
                <th>Total</th>
                <th>Status</th>
                <th>Date</th>
            </tr>
            <c:forEach items="${latestOrders}" var="o">
                <tr>
                    <td>${o.id}</td>
                    <td>${o.customer}</td>
                    <td>${o.total}</td>
                    <td>${o.status}</td>
                    <td>${o.date}</td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <div class="col-md-5">
        <h5>📦 Low Stock Products</h5>
        <table class="table table-bordered">
            <tr>
                <th>Product</th>
                <th>Stock</th>
            </tr>
            <c:forEach items="${lowStockProducts}" var="p">
                <tr>
                    <td>${p.name}</td>
                    <td class="text-danger">${p.stock}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

<%-- Top 10 bán chạy --%>
    <div class="section-title">
    🏆 Top 10 sản phẩm bán chạy nhất
    <span class="section-sub">(tính theo lượt bán, không kể đơn Cancelled)</span>
</div>

<div class="table-card">
    <c:choose>
        <c:when test="${empty top10}">
            <div class="empty">
                Chưa có dữ liệu bán hàng
            </div>
        </c:when>

        <c:otherwise>
            <%-- Lấy maxSold của sản phẩm #1 để tính % thanh bar --%>
            <c:set var="maxSold" value="${top10[0].totalSold}"/>

            <table>
                <thead>
                    <tr>
                        <th style="width:60px">Hạng</th>
                        <th>Sản phẩm</th>
                        <th style="width:200px">Lượt bán</th>
                        <th>Giá</th>
                        <th>Doanh thu</th>
                    </tr>
                </thead>

                <tbody>
                    <c:forEach items="${top10}" var="item">
                        <tr>
                            <%-- Hạng --%>
                            <td>
                                <c:choose>
                                    <c:when test="${item.rank == 1}">
                                        <div class="rank rank-1">🥇</div>
                                    </c:when>
                                    <c:when test="${item.rank == 2}">
                                        <div class="rank rank-2">🥈</div>
                                    </c:when>
                                    <c:when test="${item.rank == 3}">
                                        <div class="rank rank-3">🥉</div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class="rank rank-n">${item.rank}</div>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <%-- Tên sản phẩm + ảnh --%>
                            <td>
                                <div class="prod-info">
                                    <c:choose>
                                        <c:when test="${not empty item.image}">
                                            <img src="${item.image}" class="prod-img"
                                                 alt="${item.productName}"/>
                                        </c:when>
                                        <c:otherwise>
                                            <div class="no-img">👕</div>
                                        </c:otherwise>
                                    </c:choose>
                                    <div>
                                        <div class="prod-name">${item.productName}</div>
                                        <div class="prod-cat">${item.categoryName}</div>
                                    </div>
                                </div>
                            </td>

                            <%-- Thanh progress bar --%>
                            <td>
                                <div class="bar-wrap">
                                    <div class="bar-bg">
                                        <div class="bar-fill"
                                             style="width:${maxSold > 0 ? (item.totalSold * 100 / maxSold) : 0}%">
                                        </div>
                                    </div>
                                    <span class="bar-num">${item.totalSold}</span>
                                </div>
                            </td>
                            <%-- Giá --%>
                                <td>
                                    <fmt:formatNumber value="${item.price}"
                                                      type="number" maxFractionDigits="0"/>đ
                                </td>

                                <%-- Doanh thu --%>
                                <td>
                                    <span class="revenue">
                                        <fmt:formatNumber value="${item.totalRevenue}"
                                                          type="number" maxFractionDigits="0"/>đ
                                    </span>
                                </td>

                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:otherwise>
    </c:choose>
</div>
                               
<!-- ================= JS ================= -->
<script>
    // ===== REVENUE =====
    const revenueLabels = [
        <c:forEach items="${revenueLast7Days}" var="e" varStatus="s">
            '${e.key}'<c:if test="${!s.last}">,</c:if>
        </c:forEach>
    ];

    const revenueValues = [
        <c:forEach items="${revenueLast7Days}" var="e" varStatus="s">
            ${e.value}<c:if test="${!s.last}">,</c:if>
        </c:forEach>
    ];

    const revenueRawMax = revenueValues.length ? Math.max(...revenueValues) : 0;

    // 🔥 ÉP SCALE – KHÔNG BAO GIỜ CÓ 0.1
    const revenueMax = Math.ceil(revenueRawMax / 100) * 100 || 100;
    const revenueStep = revenueMax / 5;

    new Chart(document.getElementById('revenueChart'), {
        type: 'line',
        data: {
            labels: revenueLabels,
            datasets: [{
                label: 'Revenue',
                data: revenueValues,
                borderWidth: 2,
                tension: 0.3
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    min: 0,
                    max: revenueMax,
                    ticks: {
                        stepSize: revenueStep,
                        callback: v => v.toLocaleString()
                    }
                }
            }
        }
    });

    // ===== ORDERS BY STATUS =====
    const statusLabels = [
        <c:forEach items="${ordersByStatus}" var="e" varStatus="s">
            '${e.key}'<c:if test="${!s.last}">,</c:if>
        </c:forEach>
    ];

    const statusValues = [
        <c:forEach items="${ordersByStatus}" var="e" varStatus="s">
            ${e.value}<c:if test="${!s.last}">,</c:if>
        </c:forEach>
    ];

    const orderMax = statusValues.length ? Math.max(...statusValues) : 0;

    new Chart(document.getElementById('orderStatusChart'), {
        type: 'bar',
        data: {
            labels: statusLabels,
            datasets: [{
                label: 'Orders',
                data: statusValues
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true,
                    min: 0,
                    max: orderMax + 1,
                    ticks: {
                        stepSize: 1,
                        precision: 0
                    }
                }
            }
        }
    });
</script>
</div>
</body>
</html>