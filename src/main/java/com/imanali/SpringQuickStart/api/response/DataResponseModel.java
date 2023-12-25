package com.imanali.SpringQuickStart.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataResponseModel {
   private Boolean status;

   @JsonProperty("status_code")
   private Integer statusCode;

   private Map<String, Object> data;
}
