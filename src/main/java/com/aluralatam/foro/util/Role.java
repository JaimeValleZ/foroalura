package com.aluralatam.foro.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum Role {

    ADMIN(Arrays.asList(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE)),
    STUDENT(Arrays.asList(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE)),
    TEACHER(Arrays.asList(Permission.CREATE, Permission.READ, Permission.UPDATE, Permission.DELETE));

    private List<Permission> permissions;


}
