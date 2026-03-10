<%-- 
    Document   : baby
    Created on : Feb 28, 2026, 8:58:12 PM
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
                <img src="${pageContext.request.contextPath}/view/product/baby/babyimg/product1.png" alt="Bộ Bodysuit Kẻ" width="300">

                <div>
                    <span>Trẻ sơ sinh</span>
                    <span>0-24TH</span>
                </div>

                <h3>Bộ Bodysuit Kẻ</h3>

                <p><strong>200.000 VND</strong></p>

                <p> (đánh giá)</p>
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

            <!-- Product 2 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/baby/babyimg/product2.png" alt="Bộ Bodysuit Chấm PI" width="300">

                <div>
                    <span>Trẻ sơ sinh</span>
                    <span>0-24TH</span>
                </div>

                <h3>Bộ Bodysuit Chấm PI</h3>

                <p><strong>200.000 VND</strong></p>

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
                <img src="${pageContext.request.contextPath}/view/product/baby/babyimg/product3.png" alt="Bộ Bodysuit Hồng" width="300">


                <div>
                    <span>Trẻ sơ sinh</span>
                    <span>0-24TH</span>
                </div>

                <h3>Bộ Bodysuit Hồng</h3>

                <p><strong>200.000 VND</strong></p>

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
                <img src="${pageContext.request.contextPath}/view/product/baby/babyimg/product4.png" alt="Bộ Bodysuit Xanh Lục Kẻ" width="300">

                <div>
                    <span>Trẻ sơ sinh</span>
                    <span>0-24TH</span>
                </div>

                <h3>Bộ Bodysuit Xanh Lục Kẻ</h3>

                <p><strong>200.000 VND</strong></p>

                <p> (đánh giá)</p>
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

            <!-- Product 5 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/baby/babyimg/product5.png" alt="Bộ Bodysuit Chấm Vàng" width="300">

                <div>
                    <span>Trẻ sơ sinh</span>
                    <span>0-24TH</span>
                </div>

                <h3>Bộ Bodysuit Chấm Vàng</h3>

                <p><strong>200.000 VND</strong></p>

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
                <img src="${pageContext.request.contextPath}/view/product/baby/babyimg/product6.png" alt="Bộ Bodysuit Xanh Trời" width="300">


                <div>
                    <span>Trẻ sơ sinh</span>
                    <span>0-24TH</span>
                </div>

                <h3>Bộ BodysuitXanh Trời</h3>

                <p><strong>200.000 VND</strong></p>

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
