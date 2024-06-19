package com.example.webbanhang.service;


import com.example.webbanhang.model.PhongBan;
import com.example.webbanhang.repository.NhanVienRepository;
import com.example.webbanhang.repository.PhongBanRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class PhongBanService {
    private final PhongBanRepository phongbanRepository;

    /**
     * Retrieve all categories from the database.
     *
     * @return a list of categories
     */
    public List<PhongBan> getAllPhongBans() {
        return phongbanRepository.findAll();
    }

    /**
     * Retrieve a category by its id.
     *
     * @param id the id of the category to retrieve
     * @return an Optional containing the found category or empty if not found
     */
    public Optional<PhongBan> getPhongBanById(@NotNull Long id) {
        return phongbanRepository.findById(id);
    }

    /**
     * Add a new category to the database.
     *
     * @param phongban the category to add
     */
    public void addPhongBan(@NotNull PhongBan phongban) {
        phongbanRepository.save(phongban);
    }

    /**
     * Update an existing category.
     *
     * @param phongban the category with updated information
     */
    public void updatePhongBan(@NotNull @org.jetbrains.annotations.NotNull PhongBan phongban) {
        PhongBan existingPhongBan = phongbanRepository.findById(phongban.getId())
                .orElseThrow(() -> new IllegalStateException(
                        "PhongBan with ID " + phongban.getId() + " does not exist."
                ));
        existingPhongBan.setName(phongban.getName());
        phongbanRepository.save(existingPhongBan);
    }

    /**
     * Delete a category by its id.
     *
     * @param id the id of the category to delete
     */
    public void deletePhongBanById(@NotNull Long id) {
        if (!phongbanRepository.existsById(id)) {
            throw new IllegalStateException(
                    "PhongBan with ID " + id + " does not exist."
            );
        }
        phongbanRepository.deleteById(id);
    }
}
