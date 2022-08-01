package edu.ln.tour.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResultDto<T> {
    int pageNo;// 页码号
    int pageSize;//页码大小
    int pageTotal; //总条数
    String queryString;// 查询条件
    List<T> data;

}
