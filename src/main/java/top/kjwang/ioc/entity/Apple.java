package top.kjwang.ioc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author kjwang
 * @Date 2023/3/12 08:56
 */

//lombok注解
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Apple {
    private String name;
    private String origin;
}
