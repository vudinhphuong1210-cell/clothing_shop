<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${product.productName} - Clothing Shop</title>
        <style>
            .container { max-width: 1200px; margin: 0 auto; padding: 20px; }
            .product-layout { display: flex; gap: 40px; margin-top: 20px; }
            .product-image { flex: 1; text-align: center; }
            .product-image img { max-width: 100%; border-radius: 8px; }
            .product-info { flex: 1; }
            .product-title { font-size: 2em; margin-bottom: 15px; }
            .product-price { font-size: 1.5em; color: #e44d26; font-weight: bold; margin-bottom: 20px; }
            .product-desc { margin-bottom: 20px; line-height: 1.6; color: #555; }
            .variant-section { margin-bottom: 20px; }
            .variant-label { font-weight: bold; display: block; margin-bottom: 10px; }
            .actions { margin-top: 30px; display: flex; gap: 15px; }
            .btn {
                padding: 12px 25px;
                border: none;
                border-radius: 4px;
                font-size: 16px;
                cursor: pointer;
                font-weight: bold;
                text-decoration: none;
                text-align: center;
            }
            .btn-cart { background-color: #f8b400; color: #000; }
            .btn-buy { background-color: #e44d26; color: #fff; }
            .btn:hover { opacity: 0.9; }
            select, input[type="number"] { padding: 8px; border: 1px solid #ccc; border-radius: 4px; }
        </style>
    </head>
    <body>
        <jsp:include page="/view/home/componentHome/header.jsp"/>
        
        <div class="container">
            <c:if test="${not empty product}">
                <div class="product-layout">
                    <div class="product-image">
                        <img src="${pageContext.request.contextPath}/${product.image}" alt="${product.productName}">
                    </div>
                    <div class="product-info">
                        <h1 class="product-title">${product.productName}</h1>
                        <div class="product-price">
                            <fmt:formatNumber value="${product.price}" type="number" maxFractionDigits="0"/> VND
                        </div>
                        <div class="product-desc">
                            ${product.description}
                        </div>

                        <form action="${pageContext.request.contextPath}/cart" method="GET">
                            <input type="hidden" name="id" value="${product.productId}">

                            <div class="variant-section">
                                <span class="variant-label">Màu sắc:</span>
                                <select name="color" required>
                                    <option value="">Chọn màu</option>
                                    <c:forEach items="${product.variants}" var="v">
                                        <option value="${v.color}">${v.color}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="variant-section">
                                <span class="variant-label">Kích cỡ:</span>
                                <select name="size" required>
                                    <option value="">Chọn size</option>
                                    <c:forEach items="${product.variants}" var="v">
                                        <option value="${v.size}">${v.size}</option>
                                    </c:forEach>
                                </select>
                            </div>

                            <div class="variant-section">
                                <span class="variant-label">Số lượng:</span>
                                <input type="number" name="quantity" value="1" min="1" max="100">
                            </div>

                            <div class="actions">
                                <button type="submit" name="action" value="add" class="btn btn-cart">Thêm vào giỏ hàng</button>
                                <button type="submit" name="action" value="buyNow" class="btn btn-buy">Mua ngay</button>
                            </div>
                        </form>
                    </div>
                </div>
            </c:if>
            <c:if test="${empty product}">
                <h2>Sản phẩm không tồn tại</h2>
            </c:if>
        </div>
        
        <jsp:include page="/view/home/componentHome/footer.jsp"/>
    </body>
</html>
