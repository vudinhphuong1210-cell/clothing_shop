<%-- 
    Document   : men
    Created on : Feb 28, 2026, 8:57:38 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:include page="/view/home/componentHome/header.jsp"/>
        <section>

            <!-- Product 1 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/men/menimg/product1.png" alt="Áo Khoác Harrington" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-XL</span>
                </div>

                <h3>Áo Khoác Harrington</h3>

                <p><strong>1.275.000 VND</strong></p>

                <p> (đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="1">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="1">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 2 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/men/menimg/product2.png" alt="Áo Khoác Kiểu Sơ Mi Vải Denim" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-L</span>
                </div>

                <h3>Áo Khoác Kiểu Sơ Mi Vải Denim Dáng Relax</h3>

                <p><strong>980.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 3 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/men/menimg/product3.png" alt="Áo Khoác Kéo Khóa Dáng Ngắn" width="300">


                <div>
                    <span>UNISEX</span>
                    <span>XS-XXL</span>
                </div>

                <h3>Áo Khoác Kéo Khóa Dáng Ngắn</h3>

                <p><strong>1.275.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>
            
            <!-- Product 4 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/men/menimg/product4.png" alt="AirSense Áo Blazer" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-XL</span>
                </div>

                <h3>AirSense Áo Blazer</h3>

                <p><strong>1.471.000 VND</strong></p>

                <p> (đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="1">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="1">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 5 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/men/menimg/product5.png" alt="AIRism Cotton Áo Thun" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-L</span>
                </div>

                <h3>AIRism Cotton Áo Thun</h3>

                <p><strong>391.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 6 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/men/menimg/product6.png" alt="Áo Thun Cổ Henley" width="300">


                <div>
                    <span>UNISEX</span>
                    <span>XS-XXL</span>
                </div>

                <h3>Áo Thun Cổ Henley</h3>

                <p><strong>391.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="4">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

        </section>
    </body>
</html>
