<%-- 
    Document   : children
    Created on : Feb 28, 2026, 8:58:03 PM
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
                <img src="${pageContext.request.contextPath}/view/product/children/childrenimg/product1.png" alt="DRY-EX Áo Thun" width="300">

                <div>
                    <span>Trẻ em</span>
                    <span>4-5Y(110)-14Y(160)</span>
                </div>

                <h3>DRY-EX Áo Thun</h3>

                <p><strong>244.000 VND</strong></p>

                <p> (đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="3">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="3">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 2 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/children/childrenimg/product2.png" alt="DRY-EX Áo Thun Cotton" width="300">

                <div>
                    <span>Trẻ em</span>
                    <span>4-5Y(110)-14Y(160)</span>
                </div>

                <h3>DRY-EX Áo Thun Cotton</h3>

                <p><strong>244.000 VND</strong></p>

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
                <img src="${pageContext.request.contextPath}/view/product/children/childrenimg/product3.png" alt="AIRism Cotton Áo Thun In Họa Tiết" width="300">


                <div>
                    <span>Trẻ em</span>
                    <span>4-5Y(110)-14Y(160)</span>
                </div>

                <h3>AIRism Cotton Áo Thun In Họa Tiết</h3>

                <p><strong>195.000 VND</strong></p>

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
                <img src="${pageContext.request.contextPath}/view/product/children/childrenimg/product4.png" alt="AIRism Cotton Áo Thun In Họa Tiết" width="300">

                <div>
                    <span>Trẻ em</span>
                    <span>4-5Y(110)-14Y(160)</span>
                </div>

                <h3>AIRism Cotton Áo Thun In Họa Tiết</h3>

                <p><strong>195.000 VND</strong></p>

                <p> (đánh giá)</p>
                <div style="margin-top: 15px; display: flex; gap: 10px;">
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="3">
                         <input type="hidden" name="action" value="add">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #f8b400; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Giỏ hàng</button>
                    </form>
                    <form action="${pageContext.request.contextPath}/cart" method="GET" style="margin:0;">
                         <input type="hidden" name="id" value="3">
                         <input type="hidden" name="action" value="buyNow">
                         <input type="hidden" name="quantity" value="1">
                         <button type="submit" style="padding: 8px 12px; background-color: #e44d26; color: white; border: none; border-radius: 4px; cursor: pointer; font-weight: bold;">Mua ngay</button>
                    </form>
                </div>
            </div>

            <hr>

            <!-- Product 5 -->
            <div>
                <img src="${pageContext.request.contextPath}/view/product/children/childrenimg/product5.png" alt="AIRism Cotton Áo Thun In Họa Tiết" width="300">

                <div>
                    <span>Trẻ em</span>
                    <span>4-5Y(110)-14Y(160)</span>
                </div>

                <h3>AIRism Cotton Áo Thun In Họa Tiết</h3>

                <p><strong>195.000 VND</strong></p>

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
                <img src="${pageContext.request.contextPath}/view/product/children/childrenimg/product6.png" alt="AIRism Cotton Áo Thun" width="300">


                <div>
                    <span>Trẻ em</span>
                    <span>4-5Y(110)-14Y(160)</span>
                </div>

                <h3>AIRism Cotton Áo Thun</h3>

                <p><strong>195.000 VND</strong></p>

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
