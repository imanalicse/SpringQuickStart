package com.imanali.SpringQuickStart.util;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class CommonUtil {
    public static String getBaseUrl() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toString();
    }
}
