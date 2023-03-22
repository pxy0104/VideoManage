package com.pxy.ggkt.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author pxy
 * @software IntelliJ IDEA
 * @create 2023-03-16 10:17
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GgktException extends RuntimeException{
    private Integer code;
    private String msg;

}
