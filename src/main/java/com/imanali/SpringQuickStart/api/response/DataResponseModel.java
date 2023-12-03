package com.imanali.SpringQuickStart.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponseModel {
   private Boolean status;
   private Integer status_code;
   private Map<String, Object> data;
}
