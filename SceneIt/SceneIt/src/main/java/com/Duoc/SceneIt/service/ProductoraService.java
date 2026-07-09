package com.Duoc.SceneIt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Duoc.SceneIt.modelo.Productora;
import com.Duoc.SceneIt.repository.ProductoraRepository;

@Service
public class ProductoraService {

    @Autowired
    private ProductoraRepository productoraRepository;

    public List<Productora> getProductora(){
        return productoraRepository.findAll();
    }

    public Productora saveProductora(Productora productora){
        return productoraRepository.save(productora);
    }

    public Productora getProductoraId(Integer id){
        return productoraRepository.findById(id).orElse(null);
    }

    public Productora updateProductora(Productora productora){
        if(!productoraRepository.existsById(productora.getId_productora())){
            return null;
        }
        return productoraRepository.save(productora);
    }

    public void deleteProductora(Integer id){
        productoraRepository.deleteById(id);
    }

}
