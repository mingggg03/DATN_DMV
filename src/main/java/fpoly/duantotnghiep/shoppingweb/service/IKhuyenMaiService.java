package fpoly.duantotnghiep.shoppingweb.service;

import fpoly.duantotnghiep.shoppingweb.dto.reponse.KhuyenMaiResponse;
import fpoly.duantotnghiep.shoppingweb.dto.request.KhuyenMaiRequest;
import fpoly.duantotnghiep.shoppingweb.dto.request.KhuyenMaiRequestFilter;
import fpoly.duantotnghiep.shoppingweb.model.KhuyenMaiModel;
import org.springframework.data.domain.Page;

public interface IKhuyenMaiService {

    public Page<KhuyenMaiResponse> findAll(int pageNumber, int pageSize);

    public KhuyenMaiResponse findById(String id);

    KhuyenMaiModel findById1(String id);

    Page<KhuyenMaiResponse> locKM(KhuyenMaiRequestFilter khuyenMaiRequestFilter, Integer pageNumber, Integer limit);

    public void save(KhuyenMaiRequest khuyenMai);

    void capNhatTrangThai(KhuyenMaiModel km);

    public void updateGiamGiaWithNgayBD();

    public void updateGiamGiaWithNgayKT();
}
