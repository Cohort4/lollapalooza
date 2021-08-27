package com.C4.lolapalooza.service.ServiceImplements;


import com.C4.lolapalooza.dtos.MerchDTO;
import com.C4.lolapalooza.models.Merch;
import com.C4.lolapalooza.models.ProductType;
import com.C4.lolapalooza.repositories.MerchRepository;
import com.C4.lolapalooza.service.MerchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.stream.Collectors;

@Service
public class MerchServiceImpl implements MerchService {
    @Autowired
    MerchRepository merchRepository;

    @Override
    public List<MerchDTO> getMerchs() {
        return this.merchRepository.findAll().stream().map(x -> new MerchDTO(x)).collect(Collectors.toList());
    }

    @Override
    public MerchDTO getMerchById(Long id) {
        return this.merchRepository.findById(id).map(x -> new MerchDTO(x)).orElse(null);
    }

    @Override
    public ResponseEntity<?> registerMerchs(ProductType productType, String productName,
                                            String descriptionProduct, String imageProduct, String tallesProduct,
                                            int stockProduct, Double priceProduct)  {


        if (productName == null) {
            return new ResponseEntity<>("Detectamos problemas con los datos ingresados", HttpStatus.FORBIDDEN);
        }

        if (merchRepository.findByName(productName) != null) {
            return new ResponseEntity<>("Producto ya existente", HttpStatus.FORBIDDEN);
        }
        merchRepository.save(new Merch(productType, productName, descriptionProduct, imageProduct , List.of(tallesProduct), stockProduct, priceProduct,true));
        return new ResponseEntity<>("Producto creado.", HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<?> deleteMerchs(String productName) {
        if (merchRepository.findByName(productName) == null) {
            return new ResponseEntity<>("Producto No existe en el catalogo", HttpStatus.FORBIDDEN);
        } else {
            Merch merchToDelete = merchRepository.findByName(productName);
            merchRepository.delete(merchToDelete);
            return new ResponseEntity<>("Producto eliminado.", HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<?> statusMerchs(String productName) {
        if (merchRepository.findByName(productName) == null) {
            return new ResponseEntity<>("Producto No existe en el catalogo", HttpStatus.FORBIDDEN);
        } else {
            Merch merchToDelete = merchRepository.findByName(productName);
            if(merchToDelete.isStatus()==true) {
                merchToDelete.setStatus(false);
                merchRepository.save(merchToDelete);
                 }
            else
                {
                merchToDelete.setStatus(true);
                merchRepository.save(merchToDelete);
                }
            return new ResponseEntity<>("Producto eliminado.", HttpStatus.ACCEPTED);
        }
    }

    @Override
    public ResponseEntity<?> editMerch(int editStock, Double editPrice, String nameProduct) {
        if (merchRepository.findByName(nameProduct) == null) {
            return new ResponseEntity<>("Producto No existe en el catalogo", HttpStatus.FORBIDDEN);
        }
        Merch merchToedit = merchRepository.findByName(nameProduct);
        if (editPrice<0 || editPrice <=0){
            return new ResponseEntity<>("Error en los datos ingresados", HttpStatus.FORBIDDEN);
        }
        if(editPrice.equals(merchToedit.getPrice())){
            return new ResponseEntity<>("Ingreso mismo precio que el actual", HttpStatus.FORBIDDEN);
        }
        else {
            merchToedit.setPrice(editPrice);
            merchToedit.setStock(editStock);
            merchRepository.save(merchToedit);
            return new ResponseEntity<>("Producto editado.", HttpStatus.ACCEPTED);
        }
    }
}
