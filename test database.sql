select * from Shipping
select * from [Order]
select * from Customer
update [Order] set OrderStatus = 'SHIPPED' where OrderId = 2
select * from Payment

ALTER TABLE Payment
ADD TransactionNo NVARCHAR(255);
GO
delete from Payment where OrderId = 4
INSERT INTO [Order] (CustomerId, TotalAmount, OrderStatus, Address)
VALUES 
(1, 850000.00, 'PENDING', N'Số 1, Đường Cầu Giấy, Hà Nội');

INSERT INTO Account (UserName, Password, Email, Role) 
VALUES 
('khachhang2', '123456', 'kh2@gmail.com', 'Customer');

-- Insert Customer
INSERT INTO Customer (AccountId, FullName, Phone, Address)
VALUES 
(2, N'Trần Thị B', '0987654321', N'TP.HCM');