package enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TitleNaming {
    PRODUCTS("Products"),
    YOUR_INFORMATION("Checkout: Your Information"),
    CHECKOUT_OVERVIEW("Checkout: Overview");

    private final String displayName;
}
