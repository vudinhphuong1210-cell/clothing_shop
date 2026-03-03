package constant;

public enum ProductStatus {
    ACTIVE,
    INACTIVE;
    
    public ProductStatus toggle() {
        return this == ACTIVE ? INACTIVE : ACTIVE;
    }
}
