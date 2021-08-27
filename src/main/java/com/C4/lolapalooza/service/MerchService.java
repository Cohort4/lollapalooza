package com.C4.lolapalooza.service;

import com.C4.lolapalooza.dtos.MerchDTO;
import com.C4.lolapalooza.models.ProductType;
import org.springframework.http.ResponseEntity;


import java.io.IOException;
import java.util.List;

public interface MerchService {

    public List<MerchDTO> getMerchs();
    public MerchDTO getMerchById(Long id);
    public ResponseEntity<?> registerMerchs(ProductType productType, String productName,
                                            String descriptionProduct, String imageProduct, String tallesProduct,
                                            int stockProduct, Double priceProduct) throws IOException;
    public ResponseEntity<?> deleteMerchs(String productName);
    public ResponseEntity<?> editMerch(int editStock,Double editPrice,String nameProduct);
    public ResponseEntity<?> statusMerchs(String productName);


}
