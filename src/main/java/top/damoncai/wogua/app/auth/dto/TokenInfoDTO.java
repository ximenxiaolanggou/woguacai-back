package top.damoncai.wogua.app.auth.dto;

import lombok.Data;

@Data
public class TokenInfoDTO {

    private String tokenName;

    private String tokenValue;

    public TokenInfoDTO(String tokeName, String tokenValue) {
        this.tokenName = tokeName;
        this.tokenValue = tokenValue;
    }
}
