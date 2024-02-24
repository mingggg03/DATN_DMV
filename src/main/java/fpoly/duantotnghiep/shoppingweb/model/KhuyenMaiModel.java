package fpoly.duantotnghiep.shoppingweb.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Table(name = "khuyenmai")
public class KhuyenMaiModel {

    @Id
    @Column(name = "ma")
    private String ma;

    @Column(name = "ten")
    private String ten;

    @Column(name = "loai")
    private String loai;

    @Column(name = "mucgiam")
    private BigDecimal mucGiam;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "ngaybatdau")
    private Date ngayBatDau;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "ngayketthuc")
    private Date ngayKetThuc;

    @CreationTimestamp
    @Column(name = "ngaytao")
    private Date ngayTao;

    @UpdateTimestamp
    @Column(name = "ngaycapnhat")
    private Date ngayCapNhat;

    @Column(name = "trangthai")
    private Integer trangThai;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "khuyenmai_sanpham",
            joinColumns = {@JoinColumn(name = "khuyenmai")},
            inverseJoinColumns = {@JoinColumn(name = "sanpham")})
    private List<SanPhamModel> sanPham;
}
