/**
 * 
 */
package com.mical.api;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.mical.api.validation.ValidationParameter;

/**
 * @Description:
 * @author micalliu
 * @date 2017年10月26日
 */
public interface IValidationService { // 缺省可按服务接口区分验证场景，如：@NotNull(groups = IValidationService.class)

    void save(ValidationParameter parameter);

    void update(ValidationParameter parameter);

    void delete(@Min(1) long id, @NotNull @Size(min = 2, max = 16) @Pattern(regexp = "^[a-zA-Z]+$") String operator);

    @interface Save {
    } // 与方法同名接口，首字母大写，用于区分验证场景，如：@NotNull(groups = IValidationService.Save.class)，可选

    @interface Update {
    } // 与方法同名接口，首字母大写，用于区分验证场景，如：@NotNull(groups = IValidationService.Update.class)，可选

}
