package fpoly.duantotnghiep.shoppingweb.entitymanager;

import fpoly.duantotnghiep.shoppingweb.dto.reponse.KhuyenMaiResponse;
import fpoly.duantotnghiep.shoppingweb.dto.request.KhuyenMaiRequestFilter;
import fpoly.duantotnghiep.shoppingweb.model.KhuyenMaiModel;
import fpoly.duantotnghiep.shoppingweb.repository.IKhuyenMaiRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class KhuyenMaiEntityManager {

    @Autowired
    EntityManager entityManager;

    @Autowired
    IKhuyenMaiRepository iKhuyenMaiRepository;

    public Page<KhuyenMaiResponse> filterKhuyenMaiEntity(KhuyenMaiRequestFilter khuyenMaiRequestFilter, Integer pageNumber, Integer limit) {
        StringBuilder jpql = new StringBuilder("select k FROM KhuyenMaiModel k WHERE k.mucGiam > 0");
        if (khuyenMaiRequestFilter.getMaTen() != null)
            jpql.append(" And (k.ma like '%" + khuyenMaiRequestFilter.getMaTen() + "')");
        if (khuyenMaiRequestFilter.getLoaiMucGiam() != null) {
            if (khuyenMaiRequestFilter.getLoaiMucGiam() == 0)
                jpql.append(" And k.loai = 'PHAN TRAM'");
            if (khuyenMaiRequestFilter.getLoaiMucGiam() == 1)
                jpql.append(" And k.loai = 'TIEN'");
        }

        if (khuyenMaiRequestFilter.getMucGiam() != null && khuyenMaiRequestFilter.getMucGiamMax() == null)
            jpql.append(" And k.mucGiam = " + khuyenMaiRequestFilter.getMucGiam());
        if (khuyenMaiRequestFilter.getMucGiam() != null)
            jpql.append(" And k.mucGiam >= " + khuyenMaiRequestFilter.getMucGiam());
        if (khuyenMaiRequestFilter.getMucGiamMax() != null)
            jpql.append(" And k.mucGiam <= " + khuyenMaiRequestFilter.getMucGiam());
        if (khuyenMaiRequestFilter.getTrangThai() != null)
            jpql.append(" And k.trangThai = " + khuyenMaiRequestFilter.getTrangThai());
        if (khuyenMaiRequestFilter.getNgayBatDau() != null && khuyenMaiRequestFilter.getNgayBatDau() != null)
            jpql.append(" And k.ngayBatDau >=" + khuyenMaiRequestFilter.getNgayBatDau());
        if (khuyenMaiRequestFilter.getNgayBatDau() != null && khuyenMaiRequestFilter.getNgayBatDau() != null)
            jpql.append(" And k.ngayKetThuc <=" + khuyenMaiRequestFilter.getNgayKetThuc());
        if (khuyenMaiRequestFilter.getNgayKetThuc() != null && khuyenMaiRequestFilter.getNgayBatDau() == null)
            jpql.append(" And k.ngayKetThuc =" + khuyenMaiRequestFilter.getNgayKetThuc());
        if (khuyenMaiRequestFilter.getNgayKetThuc() == null && khuyenMaiRequestFilter.getNgayBatDau() != null)
            jpql.append(" And k.ngayBatDau =" + khuyenMaiRequestFilter.getNgayBatDau());

        if (khuyenMaiRequestFilter.getSort() != null) {
            if (khuyenMaiRequestFilter.getSort() == 0) jpql.append("ORDER BY k.mucGiam ASC");
            if (khuyenMaiRequestFilter.getSort() == 1) jpql.append("ORDER BY k.mucGiam DESC");
            if (khuyenMaiRequestFilter.getSort() == 2) jpql.append("ORDER BY k.ngayBatDau DESC");
            if (khuyenMaiRequestFilter.getSort() == 3) jpql.append("ORDER BY k.ngayKetThuc ASC");
            else if (khuyenMaiRequestFilter.getSort() == 4) jpql.append("ORDER BY k.ten ASC");
            else if (khuyenMaiRequestFilter.getSort() == 5) jpql.append("ORDER BY k.ten DESC");
        }
        Query query = entityManager.createQuery(String.valueOf(jpql));
        List<KhuyenMaiModel> listContent = query.getResultList();
        Pageable pageable = PageRequest.of(pageNumber, limit);
        Page<KhuyenMaiModel> pageKhuyenMai = iKhuyenMaiRepository.findAll(pageable);
        return new PageImpl<>((listContent.stream().skip(pageable.getOffset()).limit(limit).map(m -> new KhuyenMaiResponse(m)).collect(Collectors.toList())), pageable, pageKhuyenMai.getTotalElements());
    }
}
