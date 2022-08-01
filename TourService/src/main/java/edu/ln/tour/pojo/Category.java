package edu.ln.tour.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tab_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private Integer cid;//分类id
    private String cname;//分类名称

    private List<Route> routeList;//分类关联的旅游产品
}
