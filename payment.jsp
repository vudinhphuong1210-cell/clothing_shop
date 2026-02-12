
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <form action="pay" method="post">
            <input type="hidden" name="orderId" value="${order.id}">

            <h4>Chọn phương thức thanh toán:</h4>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="paymentMethod" value="COD" checked>
                <label class="form-check-label">Thanh toán khi nhận hàng (COD)</label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="radio" name="paymentMethod" value="VNPAY">
                <label class="form-check-label">Thanh toán Online qua VNPAY</label>
            </div>

            <button type="submit" class="btn btn-primary mt-3">Hoàn tất đặt hàng</button>
        </form>
    </body>
</html>

