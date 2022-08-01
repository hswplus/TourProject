package edu.ln.tour.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table (name = "tab_route")
public class Route implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer rid;//线路id，必输
    private String rname;//线路名称，必输
    @Column
    private Double price;//价格，必输
    @Column(name = "routeIntroduce")
    private String routeIntroduce;//线路介绍 会被驼峰式命名处理
    private String rflag;   //是否上架，必输，0代表没有上架，1代表是上架
    private String  rdate;   //上架时间
    private Integer count;//收藏数量
    private String rimage;//缩略图
    private Integer sid;//所属商家

    private List<Category> categoryList;//所属分类
    private Seller seller;//所属商家
    private List<RouteImg> routeImgList;//商品详情图片列表
}
