<%-- 
    Document   : woman
    Created on : Feb 28, 2026, 8:57:30 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Woman Products</title>
    </head>
    <body>
        <jsp:include page="/view/home/componentHome/header.jsp"/>
        <section>

            <!-- Product 1 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/woman/womanimg/product1.png" alt="Áo Parka Chống Tia UV Bỏ Túi" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-XL</span>
                </div>

                <h3>Áo Parka Chống Tia UV Bỏ Túi</h3>

                <p><strong>784.000 VND</strong></p>

                <p> (đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 2 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/woman/womanimg/product2.png" alt="Áo Parka NANO" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-L</span>
                </div>

                <h3>Áo Parka NANO</h3>

                <p><strong>784.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 3 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/woman/womanimg/product3.png" alt="AIRism Áo Khoác Chống Tia UV" width="300">


                <div>
                    <span>UNISEX</span>
                    <span>XS-XXL</span>
                </div>

                <h3>AIRism Áo Khoác Chống Tia UV</h3>

                <p><strong>588.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>
            
            <!-- Product 4 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/woman/womanimg/product4.png" alt="AIRism Áo Hoodie Chống Tia UV" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-XL</span>
                </div>

                <h3>AIRism Áo Hoodie Chống Tia UV</h3>

                <p><strong>588.000 VND</strong></p>

                <p> (đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 5 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/woman/womanimg/product5.png" alt="Áo Parka Cản Gió Dáng Ngắn" width="300">

                <div>
                    <span>UNISEX</span>
                    <span>S-L</span>
                </div>

                <h3>Áo Parka Cản Gió Dáng Ngắn</h3>

                <p><strong>1.275.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 6 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/woman/womanimg/product6.png" alt="Áo Khoác Hai Mặt" width="300">


                <div>
                    <span>UNISEX</span>
                    <span>XS-XXL</span>
                </div>

                <h3>Áo Khoác Hai Mặt</h3>

                <p><strong>784.000 VND</strong></p>

                <p>(đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="2">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

        </section>
    </body>
</html>
