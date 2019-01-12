package com.fruit.sorb.vo;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * @ClassName: EasyUIResult
 * @Description:
 * @Author: lokn
 * @Date: 2019/1/6 22:54
 */
public class EasyUIResult {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    private Integer total;

    private List<?> rows;

    public EasyUIResult() {}

    public EasyUIResult(Integer total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public Integer getTotal() {
        return this.total;
    }

    public List<?> getRows() {
        return this.rows;
    }

    /**
     * @title: formatToList
     * @description: 集合转换化
     * @params: [jsonData, classz]
     * @return: com.fruit.sorb.vo.EasyUIResult
     * @author: lokn
     * @date: 2019/1/8 21:56
     * @version: v1.0
     */
    public static EasyUIResult formatToList(String jsonData, Class<?> classz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("rows");
            List<?> list = null;
            if (data.isArray() && data.size() > 0) {
                list = (List<?>) MAPPER.readValues(data.traverse(),
                        MAPPER.getTypeFactory().constructCollectionType(List.class, classz));
            }
            return new EasyUIResult(jsonNode.get("total").intValue(), list);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

}
