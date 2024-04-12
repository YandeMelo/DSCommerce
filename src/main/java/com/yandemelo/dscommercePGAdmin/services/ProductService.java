package com.yandemelo.dscommercePGAdmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.yandemelo.dscommercePGAdmin.dto.CategoryDTO;
import com.yandemelo.dscommercePGAdmin.dto.ProductDTO;
import com.yandemelo.dscommercePGAdmin.dto.ProductMinDTO;
import com.yandemelo.dscommercePGAdmin.entities.Category;
import com.yandemelo.dscommercePGAdmin.entities.Product;
import com.yandemelo.dscommercePGAdmin.repositories.ProductRepository;
import com.yandemelo.dscommercePGAdmin.services.exceptions.DatabaseException;
import com.yandemelo.dscommercePGAdmin.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    //GET
    @Transactional(readOnly = true)
    public ProductDTO findById(Long id) {
        Product product = repository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso não encontrado."));
        return new ProductDTO(product);
    }

    //GET
    @Transactional(readOnly = true)
    public Page<ProductMinDTO> findAll(Pageable pageable) {
        Page<Product> result = repository.searchByName(pageable);
        return result.map(x -> new ProductMinDTO(x));
    }

    //POST
    @Transactional
    public ProductDTO insert(ProductDTO dto) {
        Product entity = new Product();
        copyDTOtoEntity(dto, entity);
        entity = repository.save(entity);
        return new ProductDTO(entity);
    }

    //PUT
    @Transactional
    public ProductDTO update(Long id, ProductDTO dto) {
        try {
            Product entity = repository.getReferenceById(id);
            copyDTOtoEntity(dto, entity);
            entity = repository.save(entity);
            return new ProductDTO(entity);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }
    }

    //DELETE
    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Recurso não encontrado.");
        }
        try {
            repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Falha de integridade referencial.");
        }
    }

    private void copyDTOtoEntity(ProductDTO dto, Product entity) {
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
        entity.setImgUrl(dto.getImgUrl());

        // Limpar a lista de Categorias e adicionar as categorias da nova lista (no caso do PUT)
        entity.getCategories().clear();
        for (CategoryDTO catDTO : dto.getCategories()) {
            Category cat = new Category();
            cat.setId(catDTO.getId());
            entity.getCategories().add(cat);
        }
    }
    
}
