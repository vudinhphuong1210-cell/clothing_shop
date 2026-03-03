<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kết quả tìm kiếm - ${keyword}</title>
        <style>
            .product-list {
                display: flex;
                flex-wrap: wrap;
                gap: 20px;
                padding: 20px;
            }
            .product-item {
                border: 1px solid #ccc;
                padding: 10px;
                width: 200px;
                text-align: center;
            }
            .product-item img {
                max-width: 100%;
                height: auto;
            }
        </style>
    </head>
    <body>
        <jsp:include page="../../home/componentHome/header.jsp"/>
        
        <main style="padding: 20px;">
            <h1>Kết quả tìm kiếm cho: "${keyword}"</h1>
            
            <c:if test="${not empty products}">
                <div class="product-list">
                    <c:forEach items="${products}" var="p">
                        <div class="product-item">
                            <img src="${pageContext.request.contextPath}/${p.image}" alt="${p.productName}">
                            <h3>${p.productName}</h3>
                            <p><strong>${p.price} VND</strong></p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>
            
            <c:if test="${empty products}">
                <div class="no-results">
                    Không tìm thấy sản phẩm nào phù hợp với từ khóa "${keyword}".
                </div>
            </c:if>
        </main>

        <jsp:include page="../../home/componentHome/footer.jsp"/>
    </body>
</html>
