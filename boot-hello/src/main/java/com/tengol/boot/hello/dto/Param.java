package com.tengol.boot.hello.dto;

import lombok.Data;

import java.util.List;

/**
 * Param
 *
 * @author dongrui
 * @date 2020/3/20 14:11
 */
@Data
public class Param {
    private Integer id;
    private String name;
    private List<String> addrList;
    private List<Integer> numList;
}
