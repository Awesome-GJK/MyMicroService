package com.gjk.a.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 *
 * @author: gaojiankang
 * @date: 2022/11/23 9:48
 * @description:
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @NotNull(message = "用户Id不可为空")
    private Integer userId;

    @NotBlank(message = "用户姓名不可空")
    private String userName;

    @Valid
    private Dormitory dormitory;
}
