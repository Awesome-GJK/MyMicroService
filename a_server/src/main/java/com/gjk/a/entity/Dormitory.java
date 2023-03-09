package com.gjk.a.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Dormitory
 *
 * @author: gaojiankang
 * @date: 2022/11/23 9:50
 * @description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dormitory {

    @NotNull(message = "宿舍Id不可为空")
    private Integer dormitoryId;

    @NotBlank(message = "宿舍名称不可空")
    private String dormitoryName;
}
