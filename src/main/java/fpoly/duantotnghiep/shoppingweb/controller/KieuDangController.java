package fpoly.duantotnghiep.shoppingweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("${admin.domain}/kieu-dang")
public class KieuDangController {
    @GetMapping("")
    public String show(){
        return "/admin/kieuDang";
    }
}
