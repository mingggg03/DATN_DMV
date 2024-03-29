package fpoly.duantotnghiep.shoppingweb.dto.reponse;

import fpoly.duantotnghiep.shoppingweb.model.MauSacModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MauSacDTOResponse {
    private String ma;
    private String ten;
    private Date ngayTao;
    private Date ngayCapNhat;

    public MauSacDTOResponse(MauSacModel model){
        ma = model.getMa();
        ten = model.getTen();
        ngayTao = model.getNgayTao();
        ngayCapNhat = model.getNgayCapNhat();

    }
}
