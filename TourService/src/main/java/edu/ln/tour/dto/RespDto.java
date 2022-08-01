package edu.ln.tour.dto;
// 浏览器请求返回的数据封装
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespDto {
    int retStatus;
    String message;
    Object data;

    public RespDto(int retStatus, String message) {
        this.retStatus = retStatus;
        this.message = message;

    }
}
