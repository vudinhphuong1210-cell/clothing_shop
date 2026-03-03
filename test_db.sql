USE ClothingShop;
GO

SET NOCOUNT ON;

PRINT '--- 1. SETTING UP TEST DATA ---';
-- Create Account & Customer
INSERT INTO Account (UserName, Password, Role) VALUES ('testuser', '123', 'Customer');
DECLARE @AccountId INT = SCOPE_IDENTITY();
INSERT INTO Customer (AccountId, FullName, Phone) VALUES (@AccountId, 'Test User', '0123456789');
DECLARE @CustomerId INT = SCOPE_IDENTITY();

-- Create Category & Product
INSERT INTO Category (CategoryName) VALUES ('Shirt');
DECLARE @CatId INT = SCOPE_IDENTITY();
INSERT INTO Product (CategoryId, ProductName, Price) VALUES (@CatId, 'T-Shirt', 150000);
DECLARE @ProductId INT = SCOPE_IDENTITY();

-- Create ProductStats with 10 items in stock
INSERT INTO ProductStats (ProductId, Size, Color, TotalInStock) VALUES (@ProductId, 'M', 'Red', 10);
DECLARE @ProductStatsId INT = SCOPE_IDENTITY();

PRINT 'Stock before order:';
SELECT TotalInStock, TotalSold FROM ProductStats WHERE ProductStatsId = @ProductStatsId;

PRINT '--- 2. CREATING ORDER (Qty: 2) ---';
DECLARE @OrderId INT;
EXEC sp_CreateOrder @CustomerId, '123 Test St', 300000, @OrderId OUTPUT;
EXEC sp_AddOrderDetail @OrderId, @ProductStatsId, 2, 150000;

PRINT 'Stock after order:';
SELECT TotalInStock, TotalSold FROM ProductStats WHERE ProductStatsId = @ProductStatsId;

PRINT '--- 3. CANCELLING ORDER ---';
UPDATE [Order] SET OrderStatus = 'Cancelled' WHERE OrderId = @OrderId;

PRINT 'Stock after cancellation:';
SELECT TotalInStock, TotalSold FROM ProductStats WHERE ProductStatsId = @ProductStatsId;

PRINT '--- 4. TEST CUSTOMER POINTS & DELIVERY ---';
DECLARE @OrderId2 INT;
EXEC sp_CreateOrder @CustomerId, '123 Test St', 150000, @OrderId2 OUTPUT;
EXEC sp_AddOrderDetail @OrderId2, @ProductStatsId, 1, 150000;

PRINT 'Points before delivery:';
SELECT Point FROM Customer WHERE CustomerId = @CustomerId;

UPDATE [Order] SET OrderStatus = 'Delivered' WHERE OrderId = @OrderId2;

PRINT 'Points after delivery (Total Amount: 150,000 / 10,000 = 15 points):';
SELECT Point FROM Customer WHERE CustomerId = @CustomerId;

PRINT '--- 5. TEST LOCK DELIVERED ORDER ---';
BEGIN TRY
    UPDATE [Order] SET Address = 'New Address' WHERE OrderId = @OrderId2;
    PRINT 'Update succeeded (THIS IS A BUG IF PRINTED)';
END TRY
BEGIN CATCH
    PRINT 'Update failed as expected: ' + ERROR_MESSAGE();
END CATCH
GO
