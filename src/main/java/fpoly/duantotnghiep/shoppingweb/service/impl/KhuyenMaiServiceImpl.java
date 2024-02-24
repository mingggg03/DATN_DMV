package fpoly.duantotnghiep.shoppingweb.service.impl;

import fpoly.duantotnghiep.shoppingweb.dto.reponse.KhuyenMaiResponse;
import fpoly.duantotnghiep.shoppingweb.dto.request.KhuyenMaiRequest;
import fpoly.duantotnghiep.shoppingweb.dto.request.KhuyenMaiRequestFilter;
import fpoly.duantotnghiep.shoppingweb.entitymanager.KhuyenMaiEntityManager;
import fpoly.duantotnghiep.shoppingweb.model.KhuyenMaiModel;
import fpoly.duantotnghiep.shoppingweb.repository.IKhuyenMaiRepository;
import fpoly.duantotnghiep.shoppingweb.repository.ISanPhamRepository;
import fpoly.duantotnghiep.shoppingweb.service.IKhuyenMaiService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KhuyenMaiServiceImpl implements IKhuyenMaiService {

    private final IKhuyenMaiRepository iKhuyenMaiRepository;

    private final ISanPhamRepository iSanPhamRepository;

    private final KhuyenMaiEntityManager khuyenMaiEntityManager;

    @Override
    public Page<KhuyenMaiResponse> findAll(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<KhuyenMaiModel> pageModel = iKhuyenMaiRepository.findAll(pageable);
        return pageModel.map(x -> new KhuyenMaiResponse(x));
    }

    @Override
    public KhuyenMaiResponse findById(String id) {
        KhuyenMaiModel getById = iKhuyenMaiRepository.findById(id).get();
        return new KhuyenMaiResponse(getById);
    }

    @Override
    public KhuyenMaiModel findById1(String id) {
        KhuyenMaiModel getById = iKhuyenMaiRepository.findById(id).get();
        return getById;
    }

    @Override
    public Page<KhuyenMaiResponse> locKM(KhuyenMaiRequestFilter khuyenMaiRequestFilter, Integer pageNumber, Integer limit) {
        return khuyenMaiEntityManager.filterKhuyenMaiEntity(khuyenMaiRequestFilter, pageNumber, limit);
    }

    @Override
    public void save(KhuyenMaiRequest khuyenMai) {
        iKhuyenMaiRepository.save(khuyenMai.mapToModel());
    }

    @Override
    public void capNhatTrangThai(KhuyenMaiModel km) {
        iKhuyenMaiRepository.save(km);
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateGiamGiaWithNgayBD() {
        for (var i : iKhuyenMaiRepository.findAllSanPhamWithKmWhereNgayBatDau()) {
            i.setTrangThai(0);
            iKhuyenMaiRepository.save(i);
        }
        System.out.println("Thành công");
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void updateGiamGiaWithNgayKT() {
        for (var i : iKhuyenMaiRepository.findAllSanPhamWithKmWhereNgayKetThuc()) {
            i.setTrangThai(1);
            iKhuyenMaiRepository.save(i);
        }
    }
}
